package de.emir.rcp.ui.utils.iconchooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Map;

/**
 * The IconChooser is a UI component which handles displaying and selecting of a predefined icon library (this
 * is usually the library of icons stored in the resource folder of a plugin).
 */
public class IconChooser extends JDialog {

    public static final int APPROVE_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    private int dialogResult = CANCEL_OPTION;
    private URI selectedURI;

    private final JList<URI> list;

    /**
     * Creates a new IconChooser.
     * @param owner Frame in which the dialog should be displayed.
     * @param icons Icons and their corresponding URIs which should be displayed in the selection and can be
     *              selected. This can usually be paired to the getIconSet method of the IconManager which
     *              retrieves a set of icons from a specific folder.
     */
    public IconChooser(Frame owner, Map<URI, BufferedImage> icons) {
        super(owner, "Select Icon", true);
        setLayout(new BorderLayout(10, 10));

        DefaultListModel<URI> model = new DefaultListModel<>();
        model.addElement(null);
        icons.keySet().forEach(model::addElement);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);

        list = new JList<>(model);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new IconCellRenderer(icons));
        int iconSize = 64;
        int padding = 16;
        list.setFixedCellWidth(iconSize + padding);
        list.setFixedCellHeight(iconSize + padding);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    approveSelection();
                }
            }
        });
        getRootPane().registerKeyboardAction(
                e -> cancelSelection(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        pack();
        setLocationRelativeTo(owner);
    }

    /**
     * Creates a new button panel. This is the container of the dialog where everything is stored in.
     * @return Button panel.
     */
    private JPanel createButtonPanel() {
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(e -> approveSelection());
        cancel.addActionListener(e -> cancelSelection());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(ok);
        panel.add(cancel);
        return panel;
    }

    /**
     * Sets the parameters after an icon was selected by the user.
     */
    private void approveSelection() {
        selectedURI = list.getSelectedValue();
        dialogResult = APPROVE_OPTION;
        dispose();
    }

    /**
     * Cancels the selection by the user.
     */
    private void cancelSelection() {
        dialogResult = CANCEL_OPTION;
        selectedURI = null;
        dispose();
    }

    /**
     * Shows the dialog and returns APPROVE_OPTION or CANCEL_OPTION.
     */
    public int showDialog() {
        setVisible(true);
        return dialogResult;
    }

    /**
     * Gets the URI of the icon which was selected by the user. If no icon was chosen, null is returned.
     * @return URI of selected icon or null if none was selected.
     */
    public URI getSelectedURI() {
        return selectedURI;
    }

    /**
     * Cell renderer for the icon selector.
     */
    private static class IconCellRenderer extends JLabel
            implements ListCellRenderer<URI> {

        private final Map<URI, BufferedImage> icons;

        /**
         * Creates a new icon cell renderer.
         * @param icons Map of icons and their corresponding URI which should be displayed.
         */
        IconCellRenderer(Map<URI, BufferedImage> icons) {
            this.icons = icons;
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        }

        /**
         * Renders the icons.
         * @param list The JList we're painting.
         * @param value The value returned by list.getModel().getElementAt(index).
         * @param index The cells index.
         * @param isSelected True if the specified cell was selected.
         * @param cellHasFocus True if the specified cell has the focus.
         * @return Rendered component.
         */
        @Override
        public Component getListCellRendererComponent(
                JList<? extends URI> list,
                URI value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            if(value == null) {
                setIcon(null);
                setText("No Icon");
            } else {
                setIcon(new ImageIcon(icons.get(value)));
                setText(null);
            }
            if (isSelected) {
                setOpaque(true);
                setBackground(list.getSelectionBackground());
            } else {
                setOpaque(false);
            }
            return this;
        }
    }
}
