package de.emir.epd.targetview.panels;

import de.emir.epd.targetview.panels.table.*;
import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.*;
import java.awt.*;

/**
 * Panel component for editing the Target sensor references.  These settings influence how targets which are
 * only referenced by a bearing and distance to a reference target are handled. Using these settings,
 * the user can assign a fixed position, reference target received from the data or ownship as a position
 * reference to locate these targets.
 */
public class ReferenceSettingsPanel extends JPanel {
    private final JTable table;
    private final ReferenceSettingsTableModel tableModel;
    private final ReferenceSettingsCoordinateEditor latEditor = new ReferenceSettingsCoordinateEditor(ReferenceSettingsCoordinateEditor.CoordinateType.LATITUDE);
    private final ReferenceSettingsCoordinateEditor lonEditor = new ReferenceSettingsCoordinateEditor(ReferenceSettingsCoordinateEditor.CoordinateType.LONGITUDE);

    /**
     * Initiates the panel and registers listeners.
     */
    public ReferenceSettingsPanel() {
        setLayout(new BorderLayout());
        tableModel = new ReferenceSettingsTableModel();
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(1).setCellEditor(new ReferenceSettingsModeEditor());
        table.getColumnModel().getColumn(1).setCellRenderer(new ReferenceSettingsModeRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(latEditor);
        table.getColumnModel().getColumn(3).setCellEditor(lonEditor);
        table.getModel().addTableModelListener(e -> table.repaint());
        updateTableHeight();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton();
        addButton.setToolTipText("Add");
        addButton.setIcon(new ImageIcon(ReferenceSettingsPanel.class.getResource("/icons/emiricons/32/add.png")));
        JButton deleteButton = new JButton();
        deleteButton.setToolTipText("Remove");
        deleteButton.setIcon(new ImageIcon(ReferenceSettingsPanel.class.getResource("/icons/emiricons/32/delete.png")));
        JButton duplicateButton = new JButton();
        duplicateButton.setToolTipText("Duplicate");
        duplicateButton.setIcon(new ImageIcon(ReferenceSettingsPanel.class.getResource("/icons/emiricons/32/content_copy.png")));

        // Handle adding new configurations.
        addButton.addActionListener(e -> {
            tableModel.addRow(new ReferenceSettingsTableModel.Entry("SensorID", ReferenceSettingsTableModel.Mode.OWNSHIP));
            updateTableHeight();
        });

        // Handle deleting configurations.
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
                updateTableHeight();
            }
        });

        // Handle duplicating configurations.
        duplicateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                ReferenceSettingsTableModel.Entry entry = tableModel.getEntryAt(row);
                tableModel.addRow(new ReferenceSettingsTableModel.Entry(entry.getSensorIdentifier() + "_copy", entry.getCoordinate(), entry.getMode()));
                updateTableHeight();
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JLabel header = new JLabel("Target Reference Settings");
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JTextArea descriptionText = new JTextArea(
                "Configure reference settings for each sensor. By default, targets based on bearing and distance " +
                        "use the ownship position as their reference.\n" +
                        "To customize this behavior (for example, for stationary RADARs), enter the exact name of the configured sensor " +
                        "(such as the name of the NMEA0183 sensor) and choose the appropriate reference mode.\n" +
                        "OWNSHIP uses the ownship's current position.\n" +
                        "FIXED uses a specific latitude and longitude.\n" +
                        "REFERENCE uses a reference target provided in the target data."
        );
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setEditable(false);
        descriptionText.setFocusable(false);
        descriptionText.setOpaque(false);
        descriptionText.setFont(descriptionText.getFont().deriveFont(Font.PLAIN, 12f));
        descriptionText.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(duplicateButton);
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(descriptionText, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        tableModel.load();
    }

    /**
     * Checks if the configuration is dirty, i.e. if values were changed in the UI compared to the settings stored in the
     * properties.
     */
    public boolean isDirty() {
        if (latEditor.isInvalid() || lonEditor.isInvalid()) {
            ULog.error("Invalid values detected.");
            return true;
        } else {
            return tableModel.isDirty();
        }
    }

    /**
     * Dynamically updates the table height to make the layout more dynamic. This is based on the number of entities
     * present in the table.
     */
    private void updateTableHeight() {
        int rowCount = table.getRowCount();
        int minVisibleRows = Math.max(rowCount, 1);
        int rowHeight = table.getRowHeight();
        int headerHeight = table.getTableHeader().getPreferredSize().height;
        int totalHeight = (minVisibleRows * rowHeight) + headerHeight;
        Dimension preferredSize = new Dimension(table.getPreferredSize().width, totalHeight);
        table.setPreferredScrollableViewportSize(preferredSize);
        table.revalidate();
    }

    /**
     * Saves the current configuration to the properties storage.
     */
    public void save() {
        if (latEditor.isInvalid() || lonEditor.isInvalid()) {
            ULog.error("Invalid values detected.");
        } else {
            tableModel.save();
        }
    }
}
