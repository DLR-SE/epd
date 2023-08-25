package de.emir.rcp.manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import org.slf4j.Logger;

import de.emir.rcp.commands.basics.ExternalBrowserCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.menu.CustomJButton;
import de.emir.rcp.menu.CustomJButtonMenu;
import de.emir.rcp.menu.CustomJCheckBoxMenuItem;
import de.emir.rcp.menu.CustomJMenu;
import de.emir.rcp.menu.CustomJMenuItem;
import de.emir.rcp.menu.CustomJRadioButton;
import de.emir.rcp.menu.CustomJRadioButtonMenuItem;
import de.emir.rcp.menu.CustomJToggleButton;
import de.emir.rcp.menu.CustomJToolbarSeparator;
import de.emir.rcp.menu.CustomMenuUtil;
import de.emir.rcp.menu.DynamicJMenu;
import de.emir.rcp.menu.ep.DynamicMenu;
import de.emir.rcp.menu.ep.Menu;
import de.emir.rcp.menu.ep.MenuContribution;
import de.emir.rcp.menu.ep.MenuEntry;
import de.emir.rcp.menu.ep.MenuEntryData;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.menu.ep.MenuItem;
import de.emir.rcp.menu.ep.MenuItemType;
import de.emir.rcp.menu.ep.RadioGroup;
import de.emir.rcp.menu.ep.RadioGroupElement;
import de.emir.rcp.menu.ep.Separator;
import de.emir.rcp.menu.ep.util.PopupMenuSeparatorVisibilityHandler;
import de.emir.rcp.views.AboutDialog;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * Manages all menu entries defined via the MenuExtensionPoint.
 * 
 * @author fklein
 * @author Behrensen, Stefan <stefan.behrensen@dlr.de>
 *
 */
public class MenuManager implements IService {
    private static final Logger LOG = ULog.getLogger(MenuManager.class);

    private MenuExtensionPoint menuExtensionPoint = new MenuExtensionPoint();
    private JMenuBar mMainMenu = new JMenuBar();
    private JPanel mTopPanel = new JPanel();
    private JToolBar mToolBar = new JToolBar();
    private Map<String, List<MenuEntryData>> rootToEntriesMap = new HashMap<>();

    // If a fill is requested before the menu extensions have been loaded
    private Map<String, List<Object>> menusToFill = new HashMap<>();
    private boolean entriesCreated = false;

    public MenuManager() {

    }

    private void addMenuToFill(Object menu, String id) {
        List<Object> menuList = menusToFill.get(id);
        if (menuList == null) {
            menuList = new ArrayList<>();
            menusToFill.put(id, menuList);
        }
        menuList.add(menu);
    }

    public void fillToolbar(JToolBar tb, String id) {
        if (id.startsWith(Basic.TOOLBAR_IDENTIFIER) == false) {
            throw new UnsupportedOperationException("Toolbar IDs have to start with " + Basic.TOOLBAR_IDENTIFIER
                    + " use Basic.MENUS.TOOLBAR_IDENTIFIER as IDs prefix.");
        }

        if (entriesCreated == false) {

            addMenuToFill(tb, id);
            return;
        }

        fillRoot(tb, id);
    }

    public void fillPopup(JPopupMenu pm, String id) {
        if (id.startsWith(Basic.POPUP_IDENTIFIER) == false) {
            throw new UnsupportedOperationException("Popup Menu IDs have to start with " + Basic.POPUP_IDENTIFIER
                    + " use Basic.MENUS.POPUP_IDENTIFIER as IDs prefix.");
        }

        if (entriesCreated == false) {

            addMenuToFill(pm, id);
            return;
        }

        fillRoot(pm, id);
        addSeparatorVisibleStateHandler(pm);
    }

    public void fillMenuBar(JMenuBar mb, String id) {
        if (id.startsWith(Basic.MENU_IDENTIFIER) == false) {
            throw new UnsupportedOperationException("MenuBar IDs have to start with " + Basic.MENU_IDENTIFIER
                    + " use Basic.MENU_IDENTIFIER as IDs prefix.");
        }

        if (entriesCreated == false) {

            addMenuToFill(mb, id);
            return;
        }

        fillRoot(mb, id);

        // Do last: Add a listener that removes unnecessary Separators before the
        // submenu is shown
        for (Component comp : mb.getComponents()) {
            if (comp instanceof JMenu) {
                JPopupMenu popup = ((JMenu) comp).getPopupMenu();
                if (popup != null) {
                    popup.addPopupMenuListener(new PopupMenuSeparatorVisibilityHandler(popup));
                }
            }
        }
    }

    private List<MenuEntryData> getMenuEntriesOfRoot(String rootID) {
        List<MenuEntryData> entries = rootToEntriesMap.get(rootID);
        if (entries == null) {
            entries = new ArrayList<>();
            rootToEntriesMap.put(rootID, entries);
        }
        return entries;
    }

    public MenuExtensionPoint getMenuExtensionPoint() {
        return menuExtensionPoint;
    }

    public void init(JFrame mainWindow) {
        mainWindow.setJMenuBar(mMainMenu);
        mTopPanel.setLayout(new BorderLayout());
        mainWindow.add(mTopPanel, BorderLayout.NORTH);
        JLabel label = new JLabel();
        ResourceManager resourceManager = ResourceManager.get(AboutDialog.class);
        ImageIcon icon = new ImageIcon(resourceManager.getImageIcon("branding/DLR-Programm_Icon.png").getImage().getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH));
        label.setIcon(icon);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
        			ExternalBrowserCommand browseDLRCmd = new ExternalBrowserCommand(new URI("https://www.dlr.de/se"));
        			browseDLRCmd.execute();
        		} catch (URISyntaxException e) {
        			LOG.error("Not a valid URI.", e);
        		}
            }
        });
        mTopPanel.add(label, BorderLayout.EAST);
        mTopPanel.add(mToolBar, BorderLayout.CENTER);
        mToolBar.setFloatable(false);

    }

    private void fillRoot(Object root, String rootID) {

        if (rootID.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        List<MenuEntryData> entries = getMenuEntriesOfRoot(rootID);

        Map<String, Object> createdElements = new HashMap<>();
        createdElements.put(rootID, root);

        for (MenuEntryData med : entries) {
            createEntry(med.path, med.menuEntry, createdElements);
        }

    }

    /**
     * Fills the menus. Should be run after extension point inflation
     */
    public void fillEntryMap() {

        Map<String, MenuContribution> mcs = menuExtensionPoint.getMenuContributions();

        // First we create paths from all contributions

        List<MenuEntryData> paths = new ArrayList<>();

        for (Entry<String, MenuContribution> entry : mcs.entrySet()) {
            Map<String, MenuEntry> cont = entry.getValue().getEntries();

            for (Entry<String, MenuEntry> menuEntry : cont.entrySet()) {

                String path = entry.getKey() + "." + menuEntry.getKey();

                paths.add(new MenuEntryData(path, menuEntry.getValue()));

                // Submenu
                if (menuEntry.getValue() instanceof Menu) {
                    createPath(paths, path, (Menu) menuEntry.getValue());
                }

            }

        }

        Collections.sort(paths);

        // Order by before / after settings

        for (MenuEntryData med : paths) {
            String after = med.menuEntry.getAfter();
            if (after == null) {
                continue;
            }

            String folderPath = removeLastSegment(med.path);

            String siblingPath = folderPath + "." + after;

            MenuEntryData sibling = findEntry(paths, siblingPath);

            if (sibling == null) {
                LOG.error("Setting menu order for item [" + med.path + "] failed. Sibling [" + siblingPath
                        + "] not found");
            }

            double siblingIndex = -1;
            if (sibling != null && sibling.menuEntry != null)
                siblingIndex = sibling.menuEntry.getAddedIndex();

            double newIndex = siblingIndex + med.menuEntry.getAddedIndex() / 1000000;
            med.menuEntry.setAddedIndex(newIndex);

        }

        for (MenuEntryData med : paths) {
            String before = med.menuEntry.getBefore();
            if (before == null) {
                continue;
            }

            String folderPath = removeLastSegment(med.path);

            String siblingPath = folderPath + "." + before;

            MenuEntryData sibling = findEntry(paths, siblingPath);

            if (sibling == null) {
                LOG.error("Setting menu order for item [" + med.path + "] failed. Sibling [" + siblingPath
                        + "] not found");
            } else {

                double siblingIndex = sibling.menuEntry.getAddedIndex();

                double newIndex = siblingIndex - 1 + med.menuEntry.getAddedIndex() / 1000000;
                med.menuEntry.setAddedIndex(newIndex);
            }
        }

        // Now we have a list with absolute paths for all entries.
        // We order them by their fragment counts, their adding order and their
        // after/before settings
        Collections.sort(paths);

        // We are able to fill the menus in the correct order now

        for (MenuEntryData med : paths) {
            String path = med.path;

            String rootID = getRootPathPart(path);
            List<MenuEntryData> entries = getMenuEntriesOfRoot(rootID);
            entries.add(med);
        }

        fillMenuBar(mMainMenu, Basic.MENU_MAIN_MENU);
        fillToolbar(mToolBar, Basic.MENU_MAIN_TOOLBAR);

        entriesCreated = true;

        fillWaitingMenus();

    }

    private void fillWaitingMenus() {
        for (Entry<String, List<Object>> e : menusToFill.entrySet()) {
            String id = e.getKey();
            List<Object> menuList = e.getValue();
            for (Object m : menuList) {

                if (m instanceof JToolBar) {
                    fillToolbar((JToolBar) m, id);
                } else if (m instanceof JMenuBar) {
                    fillMenuBar((JMenuBar) m, id);
                } else if (m instanceof JPopupMenu) {
                    fillPopup((JPopupMenu) m, id);
                }
            }

        }

    }

    private MenuEntryData findEntry(List<MenuEntryData> data, String path) {

        for (MenuEntryData med : data) {

            if (med.path.equals(path)) {
                return med;
            }

        }
        return null;
    }

    private String removeLastSegment(String path) {
        int lastPoint = path.lastIndexOf(".");
        if (lastPoint == -1) {
            return path;
        }

        return path.substring(0, lastPoint);

    }

    private boolean isToolbar(String path) {
        return path.startsWith(Basic.TOOLBAR_IDENTIFIER);
    }

    private void createToolbarEntry(String path, MenuEntry entry, Object menu, Map<String, Object> items) {

        if (menu instanceof JToolBar) {
            // Root

            JComponent c = null;

            if (entry instanceof Menu) {

                c = createButtonMenu(entry, path);

            } else if (entry instanceof Separator) {

                int orientation = ((JToolBar) menu).getOrientation();
                int oo = CustomMenuUtil.getOppositeOrientation(orientation);
                c = new CustomJToolbarSeparator(oo, path, entry.getPlugin());
            } else if (entry instanceof MenuItem) {

                c = createButtonMenuItem(entry, path);

            }

            if (c != null) {

                ((JToolBar) menu).add(c);
                items.put(path, c);

            }

        } else if (menu instanceof CustomJButtonMenu) {
            // Roots children
            JComponent c = null;

            if (entry instanceof Menu) {
                c = new CustomJMenu(((Menu) entry).getLabel(), ((Menu) entry).getTooltip(), path, entry.getPlugin());
            } else if (entry instanceof Separator) {
                c = new JSeparator();
            } else if (entry instanceof MenuItem) {

                c = createMenuItem(entry, path);

            }

            if (c != null) {

                ((CustomJButtonMenu) menu).addToPopup(c);
                items.put(path, c);

            }

        } else if (menu instanceof JMenu) {

            JComponent c = null;

            if (entry instanceof Menu) {
                c = new CustomJMenu(((Menu) entry).getLabel(), ((Menu) entry).getTooltip(), path, entry.getPlugin());
            } else if (entry instanceof Separator) {
                c = new JSeparator();
            } else if (entry instanceof MenuItem) {

                c = createMenuItem(entry, path);

            }

            if (c != null) {

                ((JMenu) menu).add(c);
                items.put(path, c);

            }

        } else {
            LOG.error("Can't create menu entry. Parent has to be a valid container");
            return;
        }

    }

    private void createRadioGroup(RadioGroup<?> entry, Object menu, String path) {

        List<?> elements = entry.getElements();

        ButtonGroup bg = new ButtonGroup();

        for (Object object : elements) {
            RadioGroupElement<?> e = (RadioGroupElement<?>) object;

            if (menu instanceof JToolBar) {

                CustomJRadioButton<?> c = new CustomJRadioButton<>(e.label, e.icon, e.value, e.tooltip, path,
                        entry.getPlugin());
                c.setCommandID(entry.getCommandID());

                ((JToolBar) menu).add(c);
                bg.add(c);

            } else if (menu instanceof CustomJButtonMenu) {

                CustomJRadioButtonMenuItem<?> c = new CustomJRadioButtonMenuItem<>(e.label, e.icon, e.value, e.tooltip,
                        path, entry.getPlugin());
                c.setCommandID(entry.getCommandID());
                ((CustomJButtonMenu) menu).addToPopup(c);
                bg.add(c);

            } else if (menu instanceof JMenu) {

                CustomJRadioButtonMenuItem<?> c = new CustomJRadioButtonMenuItem<>(e.label, e.icon, e.value, e.tooltip,
                        path, entry.getPlugin());
                c.setCommandID(entry.getCommandID());
                ((JMenu) menu).add(c);
                bg.add(c);

            } else if (menu instanceof JMenuBar) {

                CustomJRadioButtonMenuItem<?> c = new CustomJRadioButtonMenuItem<>(e.label, e.icon, e.value, e.tooltip,
                        path, entry.getPlugin());
                c.setCommandID(entry.getCommandID());
                ((JMenuBar) menu).add(c);
                bg.add(c);

            }

        }

    }

    private JComponent createButtonMenu(MenuEntry entry, String path) {

        return new CustomJButtonMenu(((Menu) entry).getLabel(), ((Menu) entry).getIcon(), ((Menu) entry).getTooltip(),
                path, entry.getPlugin());

    }

    private JComponent createButtonMenuItem(MenuEntry entry, String path) {
        JComponent c = null;

        ImageIcon icon = ((MenuItem) entry).getIcon();
        if (((MenuItem) entry).getMenuItemType() == MenuItemType.TOGGLE) {
            c = new CustomJToggleButton(((MenuItem) entry).getLabel(), icon, ((MenuItem) entry).getTooltip(), path,
                    entry.getPlugin());
            ((CustomJToggleButton) c).setCommand(((MenuItem) entry).getCommand());
        } else {
            c = new CustomJButton(((MenuItem) entry).getLabel(), icon, ((MenuItem) entry).getTooltip(), path,
                    entry.getPlugin());
            ((CustomJButton) c).setCommand(((MenuItem) entry).getCommand());
        }

        return c;

    }

    private void createEntry(String path, MenuEntry entry, Map<String, Object> items) {

        String pathWithoutID = removeLastSegment(path);

        Object menu = getMenu(pathWithoutID, items);

        if (menu == null) {
            LOG.error("Can't create menu entry. Parent not found (" + pathWithoutID + ")");
            return;
        }

        if (entry instanceof RadioGroup) {

            createRadioGroup((RadioGroup<?>) entry, menu, path);
            return;
        }

        if (isToolbar(path) == true) {
            createToolbarEntry(path, entry, menu, items);
            return;
        }

        if (menu instanceof JMenu == false && menu instanceof JMenuBar == false
                && menu instanceof JPopupMenu == false) {
            LOG.error("Can't create menu entry. Parent has to be a valid container");
            return;
        }

        createMenuEntry(path, entry, menu, items);

    }

    private void createMenuEntry(String path, MenuEntry entry, Object parent, Map<String, Object> items) {

        JComponent c = null;

        if (entry instanceof DynamicMenu) {
            c = new DynamicJMenu((DynamicMenu) entry, parent);
        } else if (entry instanceof Menu) {
            c = new CustomJMenu(((Menu) entry).getLabel(), ((Menu) entry).getTooltip(), path, entry.getPlugin());
        } else if (entry instanceof Separator) {
            c = new JSeparator();
        } else if (entry instanceof MenuItem) {
            c = createMenuItem(entry, path);
        }

        if (c != null) {

            // Add to parent
            if (parent instanceof JMenuBar) {
                ((JMenuBar) parent).add(c);
                items.put(path, c);
            } else if (parent instanceof JMenu) {
                ((JMenu) parent).add(c);
                items.put(path, c);
            } else if (parent instanceof JPopupMenu) {
                ((JPopupMenu) parent).add(c);
                items.put(path, c);
            }

            // Accesses its parent, so it has to be called after adding
            if (c instanceof DynamicJMenu) {
                ((DynamicJMenu) c).init();
            }

        }

    }

    private JComponent createMenuItem(MenuEntry entry, String path) {

        JComponent c = null;

        ImageIcon icon = ((MenuItem) entry).getIcon();
        if (((MenuItem) entry).getMenuItemType() == MenuItemType.TOGGLE) {
            c = new CustomJCheckBoxMenuItem(((MenuItem) entry).getLabel(), icon, ((MenuItem) entry).getTooltip(), path,
                    entry.getPlugin());
            ((CustomJCheckBoxMenuItem) c).setCommand(((MenuItem) entry).getCommand());
        } else {
            c = new CustomJMenuItem(((MenuItem) entry).getLabel(), icon, ((MenuItem) entry).getTooltip(), path,
                    entry.getPlugin());
            ((CustomJMenuItem) c).setCommand(((MenuItem) entry).getCommand());
        }

        return c;
    }

    private Object getMenu(String path, Map<String, Object> items) {
        return items.get(path);
    }

    private String getRootPathPart(String path) {
        return path.split("\\.")[0];
    }

    /**
     * Checks if a given path only contains of "toolbar:XY", "menu:XY" or "popup:XY" and therefore points to a menu root
     * element
     * 
     * @param path
     * @return
     */
    private boolean isRootPath(String path) {
        return path.indexOf(".") == -1;
    }

    /**
     * Recursive fill submenu paths
     * 
     * @param paths
     * @param base
     * @param menu
     */
    private void createPath(List<MenuEntryData> paths, String base, Menu menu) {

        Map<String, MenuEntry> cont = menu.getEntries();

        for (Entry<String, MenuEntry> menuEntry : cont.entrySet()) {

            String path = base + "." + menuEntry.getKey();
            paths.add(new MenuEntryData(path, menuEntry.getValue()));

            // Submenu
            if (menuEntry.getValue() instanceof Menu) {
                createPath(paths, path, (Menu) menuEntry.getValue());
            }

        }
    }

    /**
     * This Listener controls the visibility of menu separators. Separators at the beginning or end of the menu will be
     * hidden. If there are multiple separators next to each other, only one will be shown
     * 
     * @param popupMenu
     */
    private void addSeparatorVisibleStateHandler(JPopupMenu popupMenu) {

        popupMenu.addPopupMenuListener(new PopupMenuSeparatorVisibilityHandler(popupMenu));

    }
}
