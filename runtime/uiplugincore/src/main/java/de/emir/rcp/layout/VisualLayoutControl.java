package de.emir.rcp.layout;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.iconchooser.IconChooser;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Tab layout visualizer component which handles loading and saving of views if the Tab layout is enabled
 * under Layout>Tab Layout>Enable Tabs. This component creates layouts for each created tab and stores them in a separate
 * tab folder inside the layout folder.
 */
public class VisualLayoutControl extends OverflowToolbar {

    private static final Logger log = LogManager.getLogger(VisualLayoutControl.class);
    private final ButtonGroup group = new ButtonGroup();
    private final Map<LayoutProperties.LayoutProperty, LayoutTab> buttons = new HashMap<>();
    private LayoutTab selectedButton;
    private boolean layoutChanged;
    private final LayoutProperties properties = new LayoutProperties();
    private final File tabLayoutFolder;
    private final JButton addButton;
    private static final int MAX_NAME_LENGTH = 15;
    private static final int MAX_TRAILING_DIGITS = 5;
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[\\w\\s\\-]{0," + MAX_NAME_LENGTH + "}$");
    private static final Pattern TRAILING_DIGITS_PATTERN = Pattern.compile("\\d{" + (MAX_TRAILING_DIGITS + 1) + ",}$");
    private static final Set<String> excludedIconNames = Set.of("close.png", "edit_no_box_shadow.png", "arrow_right.png", "arrow_drop_down.png", "add.png");

    private final IProperty<Boolean> tabEditMode =
            PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX)
                    .getProperty(Basic.TAB_LAYOUT_EDIT_MODE_PROP, false);
    private final IProperty<Boolean> tabMode =
            PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX)
                    .getProperty(Basic.TAB_LAYOUT_ACTIVE_PROP, false);
    private final IProperty<String> lastSelectedView =
            PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX)
                    .getProperty(Basic.TAB_LAYOUT_LAST_SELECTED_VIEW, "");

    /**
     * Creates a new VisualLayoutControl component.
     */
    public VisualLayoutControl() {
        properties.loadConfiguration();
        tabLayoutFolder = ResourceManager.get(getClass()).getHomePath().resolve("layouts/tabs").toFile();
        if (!tabLayoutFolder.exists() && !tabLayoutFolder.mkdirs()) {
            JOptionPane.showMessageDialog(this,
                    "Couldn't create layout folder. Check access rights.");
        }
        addButton = new JButton(IconManager.getIcon(this, "icons/emiricons/32/add.png"));
        addButton.setFocusable(false);
        addButton.addActionListener(e -> addLayout());
        tabEditMode.addPropertyChangeListener(e -> rebuildToolbar());
        tabEditMode.setValue(!PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().isViewsLocked());
        PlatformUtil.getWindowManager().getMainWindow().getLayoutLockState().subscribeLockState(e -> {
            tabEditMode.setValue(!e);
        });
        tabMode.addPropertyChangeListener(e -> {
            if ((boolean) e.getNewValue()) {
                PlatformUtil.getWindowManager().getMainWindow().saveLayout();
                rebuildToolbar();
                restoreSelection();
                checkButtons();
            } else {
                PlatformUtil.getWindowManager().getMainWindow().loadLayout();
            }
        });
        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
        mw.addLayoutChangeListener(() -> layoutChanged = true);
        mw.addCloseListener(new MainWindow.ICloseListener() {
            @Override
            public void aboutToClose() {
                // Do nothing since it is not relevant here.
            }

            /**
             * Saves the last selected layout if the application is closed and the tab layout is currently active.
             */
            @Override
            public void closed() {
                if (tabMode.getValue() && selectedButton != null && layoutChanged) {
                    try {
                        saveLayout(getProperty(selectedButton));
                    } catch (IOException e) {
                        log.error("Error while saving layout: {}.", e.getMessage());
                    }
                }
            }
        });

    }

    /**
     * Initiates the layout control, i.e. rebuilds the toolbar from config, restores previous selections and
     * adds default tabs if none do exist.
     */
    public void init() {
        rebuildToolbar();
        restoreSelection();
        checkButtons();
    }

    /**
     * Checks if the tab layout is currently active.
     *
     * @return True if active, else false.
     */
    public boolean tabLayoutActive() {
        return tabMode.getValue();
    }

    /**
     * Checks if the tab layout edit mode is currently active.
     *
     * @return True if active, else false.
     */
    public boolean tabLayoutEditModeActive() {
        return tabEditMode.getValue();
    }

    /**
     * Checks if the button row currently contains buttons. If not, a Default Button will be added and the default layout
     * will be loaded.
     */
    private void checkButtons() {
        if (tabMode.getValue()) {
            if (buttons.isEmpty()) {
                log.warn("Found no tab layout. Creating default.");
                LayoutProperties.LayoutProperty defaultProperty = new LayoutProperties.LayoutProperty();
                defaultProperty.setName(getUniqueName("Default"));
                File layout = copyTemplate(
                        ResourceManager.get(getClass())
                                .getResource("layouts/starter-layout.xml"),
                        defaultProperty.getName() + ".xml");
                defaultProperty.setFilePath(ResourceManager.get(getClass())
                        .getHomePath()
                        .relativize(layout.toPath())
                        .toString());
                try {
                    loadLayout(defaultProperty);
                    properties.addProperty(defaultProperty);
                    properties.saveConfiguration();
                    rebuildToolbar();
                    buttons.get(defaultProperty).setSelected(true);
                    lastSelectedView.setValue(defaultProperty.getId().toString());
                } catch (IOException e) {
                    log.error("Error while loading layout: {}.", e.getMessage());
                    JOptionPane.showMessageDialog(this,
                            "Couldn't load layout: " + e.getMessage() + ".");
                }
            }
        }
    }

    /**
     * Rebuilds the toolbar, i.e. reorders the buttons after the layout properties were changed or their order.
     */
    private void rebuildToolbar() {
        if (tabMode.getValue()) {
            removeAll();
            for (LayoutProperties.LayoutProperty lp : properties.getLayouts()) {
                LayoutTab button = getOrCreateButton(lp);
                // As VisualLayoutControl builds layout changes outside of the overflow toolbar (i.e. activation
                // of nested buttons for LayoutTab) the size needs to be reset externally here to prevent
                // buttons retaining overflow size when the overflow is not present anymore if layout is locked.
                resetSize(button);
                button.setUnlocked(tabEditMode.getValue());
                add(button);
            }
            if (tabEditMode.getValue()) {
                add(addButton);
            }
            forceOverflowUpdate();
            revalidate();
            repaint();
        }
    }

    /**
     * Creates or gets a ToggleButton depending on if it exists. Also installs mouse motion listeners in order
     * to make the position of the button editable by dragging the button when in edit mode.
     *
     * @param lp LayoutProperties to equip to the button. These should be configured either through
     *           loading from config or by letting the user configure with configureLayoutProperty through a dialog.
     * @return Newly created or already existing toggle button.
     */
    private LayoutTab getOrCreateButton(LayoutProperties.LayoutProperty lp) {
        return buttons.computeIfAbsent(lp, p -> {
            LayoutTab b = new LayoutTab(p.getName());
            b.setFocusable(false);
            b.setMargin(new Insets(0, 20, 0, 20));

            try {
                b.setIcon(IconManager.getIcon(getClass(), p.getIconPath()));
            } catch (Exception e) {
                log.warn("Icon was not found. Defaulting to empty icon.");
            }

            b.addActionTabListener(new LayoutTab.ActionTabListener() {

                /**
                 * Process LayoutTab edit using the edit dialogue.
                 * @param source LayoutTab from which the click is originating from.
                 */
                @Override
                public void onEdit(LayoutTab source) {
                    String oldName = lp.getName();
                    if (configureLayoutProperty(lp) != JOptionPane.OK_OPTION) return;
                    b.setText(lp.getName());
                    try {
                        if (lp.getIconPath() != null) {
                            b.setIcon(IconManager.getIcon(getClass(), lp.getIconPath()));
                        } else {
                            b.setIcon(null);
                        }
                    } catch (Exception ignored) {

                    }
                    if (!oldName.equals(lp.getName())) {
                        File f = new File(
                                ResourceManager.get(getClass()).getHomePath() + File.separator + lp.getFilePath());
                        File newFile = new File(
                                tabLayoutFolder.toPath() + File.separator + lp.getName() + ".xml");
                        if (f.renameTo(newFile)) {
                            lp.setFilePath(ResourceManager.get(getClass())
                                    .getHomePath()
                                    .relativize(newFile.toPath())
                                    .toString());
                        } else {
                            log.error("Renaming of tab layout failed.");
                        }
                    }
                    LayoutProperties.LayoutProperty property = properties.getLayouts().stream().filter(l -> l.getId().equals(lp.getId())).findFirst().orElse(null);
                    if (property != null) {
                        property.setName(lp.getName());
                        property.setFilePath(lp.getFilePath());
                        property.setIconPath(lp.getIconPath());
                    } else {
                        properties.addProperty(lp);
                    }
                    properties.saveConfiguration();
                    rebuildToolbar();
                }

                /**
                 * Processes LayoutTab delete which deletes button and underlying layout file.
                 * @param source LayoutTab from which the click is originating from.
                 */
                @Override
                public void onDelete(LayoutTab source) {
                    Path filePath = Paths.get(ResourceManager.get(getClass()).getHomePath() + File.separator + lp.getFilePath());
                    try {
                        Files.deleteIfExists(filePath);
                    } catch (IOException ex) {
                        log.error("Error while deleting layout file: {}.", ex.getMessage());
                    }
                    properties.removeProperty(lp);
                    properties.saveConfiguration();
                    buttons.remove(lp);
                    rebuildToolbar();
                    checkButtons();
                }
            });

            b.addActionListener(e -> onButtonClicked(b, p));
            MouseAdapter moveAdapter = new MouseAdapter() {

                /**
                 * Changes the cursor of the mouse to a move symbol if the mouse is dragged from a button to another.
                 * This should indicate that the button is movable.
                 * @param e MouseEvent to process.
                 */
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (tabEditMode.getValue()) {
                        SwingUtilities.getRootPane(b).setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                        super.mouseDragged(e);
                    }
                }

                /**
                 * Checks if the mouse is released after a drag on top of another button in the bar. If the mouse
                 * is released on a specific button the button from which the drag was initiated is moved to the new
                 * position. If the cursor is in the right half of the other button, the moved button will be inserted
                 * after the other button, if in the left it will be inserted before.
                 * @param e MouseEvent to process.
                 */
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (tabEditMode.getValue()) {
                        // Checks if the mouse cursor is released on top of a button.
                        for (Map.Entry<LayoutProperties.LayoutProperty, LayoutTab> entry : buttons.entrySet()) {
                            if (lp != entry.getKey()) {
                                LayoutTab button = entry.getValue();
                                Point p = SwingUtilities.convertPoint(
                                        e.getComponent(),
                                        e.getPoint(),
                                        button
                                );
                                if (button.contains(p)) {
                                    boolean leftHalf = p.x < button.getWidth() / 2;
                                    // If the cursor is released in the left half of the button the dragged button
                                    // will be inserted before, else after.
                                    if (leftHalf) {
                                        properties.insertBefore(lp, entry.getKey());
                                    } else {
                                        properties.insertAfter(lp, entry.getKey());
                                    }
                                    rebuildToolbar();
                                    break;
                                }
                            }
                        }
                        // Resetting the cursor after release to end move action.
                        JRootPane pane = SwingUtilities.getRootPane(b);
                        if (pane != null) pane.setCursor(Cursor.getDefaultCursor());
                    }
                }
            };
            b.addMouseMotionListener(moveAdapter);
            b.addMouseListener(moveAdapter);
            group.add(b);
            return b;
        });
    }

    /**
     * Click handler for the button. If edit mode is active, it opens the button editor, else it saves the current layout
     * and changes to the layout of the clicked button.
     *
     * @param button Button to toggle.
     * @param lp     Properties associated to the button which should be loaded.
     */
    private void onButtonClicked(LayoutTab button, LayoutProperties.LayoutProperty lp) {
        if (tabMode.getValue()) {
            try {
                if (selectedButton != null && layoutChanged) {
                    saveLayout(getProperty(selectedButton));
                }
                loadLayout(lp);
                selectedButton = button;
                lastSelectedView.setValue(lp.getId().toString());
                layoutChanged = false;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Couldn't load layout: " + e.getMessage() + ". Removing layout.");
                log.error("Error while changing layout: {}.", e.getMessage());
                LayoutProperties.LayoutProperty prop = getProperty(button);
                properties.removeProperty(prop);
                properties.saveConfiguration();
                buttons.remove(prop);
                rebuildToolbar();
                checkButtons();
            }
        }
    }

    /**
     * Adds a layout to the tab layout. This opens a configure window where the user can configure a tab. After
     * configuring, the layout will be added to the configuration.
     */
    private void addLayout() {
        if (!tabEditMode.getValue()) return;
        LayoutProperties.LayoutProperty lp = new LayoutProperties.LayoutProperty();
        if (configureLayoutProperty(lp) != JOptionPane.OK_OPTION) return;
        File layout = copyTemplate(
                ResourceManager.get(getClass())
                        .getResource("layouts/starter-layout.xml"),
                lp.getName() + ".xml");
        lp.setFilePath(ResourceManager.get(getClass())
                .getHomePath()
                .relativize(layout.toPath())
                .toString());
        properties.addProperty(lp);
        properties.saveConfiguration();
        rebuildToolbar();
    }

    /**
     * Loads a layout from LayoutProperties.
     *
     * @param lp Properties to retrieve configuration from.
     */
    private void loadLayout(LayoutProperties.LayoutProperty lp) throws IOException {
        File f = new File(
                ResourceManager.get(getClass()).getHomePath() + File.separator + lp.getFilePath());
        if (f.exists()) {
            try {
                PlatformUtil.getWindowManager().getMainWindow().loadLayout(f);
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new IOException("Layout file does not exist");
        }
    }

    /**
     * Saves a layout to the configuration.
     *
     * @param lp LayoutProperties to use for extracting layout configuration.
     */
    private void saveLayout(LayoutProperties.LayoutProperty lp) throws IOException {
        if (lp != null) {
            File f = new File(
                    ResourceManager.get(getClass()).getHomePath() + File.separator + lp.getFilePath());
            PlatformUtil.getWindowManager().getMainWindow().saveLayout(f);
        } else {
            throw new IOException("Properties should not be null");
        }
    }

    /**
     * Gets the layout property assigned to a specific ToggleButton.
     *
     * @param button Button to retrieve property from.
     * @return Corresponding LayoutProperty.
     */
    private LayoutProperties.LayoutProperty getProperty(LayoutTab button) {
        return buttons.entrySet().stream()
                .filter(e -> e.getValue() == button)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * Copies a template file to a specified file inside the EPD home directory.
     *
     * @param template Template URL to load file from.
     * @param name     Path of file to save to. This is relative to the EPD home directory.
     * @return Newly created file.
     */
    private File copyTemplate(URL template, String name) {
        Path target = tabLayoutFolder.toPath().resolve(name);
        try (InputStream in = template.openStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Template copy failed: {}", e.getMessage());
        }
        return target.toFile();
    }

    /**
     * Restores the selection, i.e. loads the layout properties again, creates all buttons and restores the last selected button.
     */
    private void restoreSelection() {
        if (lastSelectedView.getValue().isEmpty()) return;
        try {
            UUID id = UUID.fromString(lastSelectedView.getValue());
            properties.getLayouts().stream()
                    .filter(lp -> lp.getId().equals(id))
                    .findFirst()
                    .map(this::getOrCreateButton)
                    .ifPresent(b -> {
                        try {
                            loadLayout(getProperty(b));
                            b.setSelected(true);
                            selectedButton = b;
                        } catch (IOException e) {
                            log.error("Error while loading layout: {}.", e.getMessage());
                            JOptionPane.showMessageDialog(this,
                                    "Couldn't load layout: " + e.getMessage() + ".");
                        }
                    });
        } catch (IllegalArgumentException e) {
            log.error("Invalid layout UUID received when restoring selection for tab layout.");
        }
    }

    /**
     * Configures a layout property. This opens a dialog window where the user can configure the name and icon
     * associated to a specific tab.
     *
     * @param lp LayoutProperty to populate.
     * @return 0 if user confirmed the dialog, 2 if canceled.
     */
    private int configureLayoutProperty(LayoutProperties.LayoutProperty lp) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        String originalName = lp.getName();
        JTextField tf = new JTextField(lp.getName() == null ? "" : lp.getName());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.weightx = 0.3;
        JLabel iconContainer = new JLabel();
        if (lp.getIconPath() == null) {
            iconContainer.setIcon(null);
            iconContainer.setText("?");
        } else {
            iconContainer.setText(null);
            iconContainer.setIcon(IconManager.getIcon(getClass(), lp.getIconPath()));
        }
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.7;
        panel.add(iconContainer, constraints);
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(new JLabel("Layout Name:"), constraints);
        constraints.gridy = 1;
        panel.add(tf, constraints);
        constraints.gridy = 2;
        JButton chooseIcon = new JButton("Choose Icon...");
        panel.add(chooseIcon, constraints);
        chooseIcon.addActionListener(e -> {
            IconChooser chooser = new IconChooser(
                    PlatformUtil.getWindowManager().getActiveFrame(),
                    IconManager.getIconSet(ResourceManager.class, "icons/emiricons/32")
                            .entrySet()
                            .stream()
                            .filter(
                                    entry -> {
                                        String key = entry.getKey().toString();
                                        String fileName = key.substring(key.lastIndexOf('/') + 1);
                                        return !excludedIconNames.contains(fileName);
                                    })
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            if (chooser.showDialog() == IconChooser.APPROVE_OPTION) {
                URI uri = chooser.getSelectedURI();
                if (uri != null) {
                    String fileName = uri.toString().substring(uri.toString().lastIndexOf('/') + 1);
                    lp.setIconPath("icons/emiricons/32/" + fileName);
                    iconContainer.setText(null);
                    iconContainer.setIcon(IconManager.getIcon(getClass(), lp.getIconPath()));
                } else {
                    lp.setIconPath(null);
                    iconContainer.setText("?");
                    iconContainer.setIcon(null);
                }
            }
        });

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, panel, "Edit Layout",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

            if (result != JOptionPane.OK_OPTION) {
                return JOptionPane.CANCEL_OPTION;
            }
            if (isValidLayoutName(tf.getText())) {
                if (originalName == null || !originalName.equals(tf.getText()) || tf.getText().isEmpty()) {
                    lp.setName(getUniqueName(tf.getText()));
                } else {
                    lp.setName(tf.getText());
                }
                return JOptionPane.OK_OPTION;
            }
            JOptionPane.showMessageDialog(this,
                    "Name is invalid. It must be smaller than 15 and only contain letters, digits (no more than 5), spaces or - and _.",
                    "Invalid Name", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if a name for the layout is valid. It checks for code injection, a maximum of 15 letters and maximum
     * tailing digits.
     *
     * @param name Name to check.
     * @return True if check passed.
     */
    private boolean isValidLayoutName(String name) {
        if (name == null) name = "";
        if (!VALID_NAME_PATTERN.matcher(name).matches()) return false;
        return !TRAILING_DIGITS_PATTERN.matcher(name).find();
    }

    /**
     * Gets a unique name for a layout. If the name is already taken, it appends an index which is incremented, i.e. Default
     * becomes Default1, Default2 etc.
     *
     * @param base Name which should be assigned.
     * @return Unique name with index if it already exists.
     */
    private String getUniqueName(String base) {
        if (base.isEmpty()) base = "Default";
        Pattern pattern = Pattern.compile("^(.*?)(\\d+)?$");
        Matcher matcher = pattern.matcher(base);
        String nameBase = base;
        int index = 1;
        if (matcher.matches()) {
            nameBase = matcher.group(1);
            if (matcher.group(2) != null) {
                index = Integer.parseInt(matcher.group(2)) + 1;
            }
        }
        String name = base;
        while (properties.nameExists(name)) {
            name = nameBase + index++;
        }
        return name;
    }

}
