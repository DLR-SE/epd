package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class KeyBindingContentPanel extends AbstractDetailsContentPanel<KeyBindingData> {

    private ImageIcon icon;
    private JTable table;
    private JButton removeButton;

    public KeyBindingContentPanel(KeyBindingData o) {
        super(o);

        icon = IconManager.getIcon(this, "icons/emiricons/32/keyboard.png", IconManager.preferedSmallIconSize());

    }

    @Override
    public String getTitle() {

        CommandDescriptor cmd = getObject().getCommand();
        return cmd.getLabel();
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {

        JPanel panel = new JPanel();

        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWeights = new double[] { 1.0 };
        gbl_panel.rowWeights = new double[] { 1.0 };
        panel.setLayout(gbl_panel);

        table = new JTable();

        table.setModel(new KeyBindingTableModel(getObject()));
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);

        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(50, 50, 50));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setShowGrid(true);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        table.addMouseListener(new KeyBindingTableMouseListener());
        table.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                Point point = e.getPoint();

                int row = table.rowAtPoint(point);

                if (row > -1) {
                    table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    table.setCursor(Cursor.getDefaultCursor());
                }

            }

        });
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table.getSelectionModel().addListSelectionListener(e -> checkDeleteButtonState());

        JScrollPane sc = new JScrollPane(table);
        sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 0;

        panel.add(sc, gbc_sc);

        JToolBar jtb = new JToolBar();

        jtb.setFloatable(false);

        JButton addButton = new JButton();
        addButton.setIcon(IconManager.getIcon(this, "icons/emiricons/32/add.png", IconManager.preferedSmallIconSize()));
        addButton.setToolTipText("Add Key Binding");

        removeButton = new JButton();
        removeButton.setIcon(IconManager.getIcon(this, "icons/emiricons/32/delete.png", IconManager.preferedSmallIconSize()));
        removeButton.setToolTipText("Remove selected Key Binding(s)");
        removeButton.setEnabled(false);
        GridBagConstraints gbc_toolbar = new GridBagConstraints();
        gbc_toolbar.fill = GridBagConstraints.BOTH;
        gbc_toolbar.gridx = 0;
        gbc_toolbar.gridy = 2;

        jtb.add(addButton);
        jtb.add(removeButton);

        panel.add(jtb, gbc_toolbar);

        addButton.addActionListener(e -> addKeyBinding());
        removeButton.addActionListener(e -> removeKeyBinding());

        return panel;
    }

    private void addKeyBinding() {
        Window parentWindow = SwingUtilities.windowForComponent(table);
        AbstractKeyBinding kb = KeyBindingsUtil.showContextDialog(parentWindow);

        if (kb == null) {
            return;
        }

        kb.setCommandID(getObject().getCommand().getId());

        EnterKeyBindingDialog dialog2 = new EnterKeyBindingDialog(PlatformUtil.getWindowManager().getMainWindow(), kb);
        String newKeys = dialog2.getSelectedKeys();

        if (newKeys == null) {
            return;
        }

        kb.setKey(newKeys);
        
        kb.setName(kb.getCommandID()+kb.getKey()); // We need any name to store the keybinding.

        getObject().addBinding(kb);
        int rows = table.getRowCount();

        // Update selection
        table.getSelectionModel().removeSelectionInterval(rows - 1, rows - 1);
        table.getSelectionModel().setSelectionInterval(rows - 1, rows - 1);

    }

    private void removeKeyBinding() {
        int minIndex = table.getSelectionModel().getMinSelectionIndex();
        int maxIndex = table.getSelectionModel().getMaxSelectionIndex();

        List<AbstractKeyBinding> bindingsToDelete = new ArrayList<>();

        for (int i = minIndex; i <= maxIndex; i++) {
            bindingsToDelete.add(((KeyBindingTableModel) table.getModel()).getBindingInRow(i));

        }

        for (AbstractKeyBinding kb : bindingsToDelete) {
            getObject().removeBinding(kb);
        }

        table.addNotify();
        checkDeleteButtonState();

    }

    private void checkDeleteButtonState() {
        int minIndex = table.getSelectionModel().getMinSelectionIndex();
        int maxIndex = table.getSelectionModel().getMaxSelectionIndex();

        boolean isABindingSelected = false;

        for (int i = minIndex; i <= maxIndex; i++) {

            if (table.getModel().getValueAt(i, 0) != null) {
                isABindingSelected = true;
                break;
            }

        }

        removeButton.setEnabled(isABindingSelected);

    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

}
