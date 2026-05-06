package de.emir.rcp.views.targets;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.units.*;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.targets.basic.TargetTableBasic;
import de.emir.rcp.views.targets.model.TargetTableModel;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Contains the display and table logic for the Target Table.
 */
public class TargetTablePanel extends JPanel {
    private final TargetTableModel model = new TargetTableModel();
    private TableRowSorter<TargetTableModel> sorter;
    private final JTable table = new JTable(model);
    private final JTextField nameFilter = new JTextField(15);
    private final JTextField idFilter = new JTextField(10);
    private final JTextField typeFilter = new JTextField(15);
    private final List<GoToListener> listeners = new ArrayList<>();
    private static final Color ALERT_BACKGROUND = new Color(220, 60, 60);
    private static final int[] COL_MIN_WIDTHS = {60, 70, 60, 55, 60, 60, 65};
    private static final int[] COL_PREF_WIDTHS = {80, 100, 80, 60, 70, 70, 75};

    private final JLabel targetName = createValueLabel();
    private final JLabel targetCoordinates = createValueLabel();
    private final JLabel targetCOG = createValueLabel();
    private final JLabel targetSOG = createValueLabel();
    private final JLabel targetHeading = createValueLabel();
    private final JLabel targetROT = createValueLabel();
    private final JButton goToButton = new JButton("Go To");
    private PhysicalObject selectedTarget;
    private final ITreeValueChangeListener selectedTargetListener = notification -> {
        updateDetailPanel();
    };
    private static final PropertyContext ctx = PropertyStore.getContext(TargetTableBasic.PROP_CTX);
    private static final IProperty<Integer> TCPA_THRESHOLD = ctx.getProperty(TargetTableBasic.TCPA_DISPLAY_THRESHOLD, 500);
    private static final IProperty<Integer> CPA_THRESHOLD = ctx.getProperty(TargetTableBasic.CPA_DISPLAY_THRESHOLD, 100);
    private static final IProperty<Integer> DISTANCE_THRESHOLD = ctx.getProperty(TargetTableBasic.DISTANCE_DISPLAY_THRESHOLD, 100);
    private static final IProperty<Boolean> ENABLE_CALCULATIONS = ctx.getProperty(TargetTableBasic.ENABLE_CALCULATIONS, true);

    /**
     * Creates a new TargetTablePanel and registers listeners.
     */
    public TargetTablePanel() {
        super(new BorderLayout(6, 6));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setupTable();
        add(createFilterPanel(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createDetailPanel(), BorderLayout.SOUTH);
        if(!ENABLE_CALCULATIONS.getValue()) {
            formatTable();
        }
        ENABLE_CALCULATIONS.addPropertyChangeListener(e -> {
            formatTable();
        });

    }

    /**
     * Creates a value label. This is a label for values in the detail panel. A custom implementation
     * was needed to allow ellipsing (appending ... when a string is not fitting).
     * @return Newly created JLabel.
     */
    private JLabel createValueLabel() {
        JLabel label = new JLabel("-") {
            private String fullText = "-";

            /**
             * Sets the text for a label.
             * @param text  the single line of text this component will display.
             */
            @Override
            public void setText(String text) {
                this.fullText = (text != null) ? text : "-";
                applyTruncated();
            }

            /**
             * Does the layout for the label. If the space is not enough, it will truncate the string.
             */
            @Override
            public void doLayout() {
                super.doLayout();
                applyTruncated();
            }

            /**
             * Checks if the space provided for the label is wide enough for all of the text. If not,
             * the string is truncated and ... is appended.
             */
            private void applyTruncated() {
                if (getFont() != null) {
                    FontMetrics fm = getFontMetrics(getFont());
                    if (fm == null) {
                        super.setText(fullText);
                        return;
                    }
                    int availWidth = getWidth() - getInsets().left - getInsets().right;
                    if (availWidth <= 0) {
                        super.setText(fullText);
                        return;
                    }
                    String truncated = truncate(fullText, fm, availWidth);
                    setToolTipText(truncated.equals(fullText) ? null : fullText);
                    super.setText(truncated);
                }
            }

            /**
             * Truncates the string if the maximum width is smaller than the string itself.
             * @param text Text to truncate.
             * @param fm FontMetrics of the label.
             * @param maxWidth Maximum available width.
             * @return Truncated string if no space available, else full string.
             */
            private String truncate(String text, FontMetrics fm, int maxWidth) {
                if (fm.stringWidth(text) <= maxWidth) return text;
                String ellipsis = "...";
                int ellipsisWidth = fm.stringWidth(ellipsis);
                if (ellipsisWidth >= maxWidth) return ellipsis;
                StringBuilder sb = new StringBuilder(text);
                while (!sb.isEmpty() && fm.stringWidth(sb.toString()) + ellipsisWidth > maxWidth) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb + ellipsis;
            }

            @Override
            public Dimension getMinimumSize() {
                return new Dimension(30, super.getMinimumSize().height);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80, super.getPreferredSize().height);
            }
        };
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        return label;
    }

    /**
     * Checks whether a PhysicalObject is allowed in the table. This is currently limited to Target, TrackedTarget
     * and Vessel as these objects are mostly relevant.
     * @param target Target to check.
     * @return True if target is allowed, else false.
     */
    private boolean isAllowed(Object target) {
        // TODO check for other PhysicalObjects. These should all be working as long as they have a pose.
        return target instanceof ITarget || target instanceof ITrackedTarget || target instanceof Vessel;
    }

    /**
     * Adds a target to the table if it is one of the allowed classes. Check isAllowed() for all allowed classes.
     * @param target Target to add.
     */
    public void addTarget(PhysicalObject target) {
        if (isAllowed(target)) {
            if (this.model.containsTarget(target)) {
                SwingUtilities.invokeLater(() -> this.model.updateTarget(target));
                updateDetailPanel();
            } else {
                SwingUtilities.invokeLater(() -> this.model.addTarget(target));
            }
        }
    }

    /**
     * Sets the reference for calculations in the table for tcpa, cpa, distance and bearing. If null, no calculations
     * will be made.
     * @param target Target to set as calculation reference.
     */
    public void setReference(PhysicalObject target) {
        this.model.setReference(target);
    }

    /**
     * Clears all targets from the model.
     */
    public void clearTargets() {
        SwingUtilities.invokeLater(this.model::clearTargets);
    }

    /**
     * Removes a specific target from the model.
     * @param target Target to remove.
     */
    public void removeTarget(PhysicalObject target) {
        if (isAllowed(target)) {
            SwingUtilities.invokeLater(() -> this.model.removeTarget(target));
        }
    }

    /**
     * Updates the detail panel. If a target is set as the selectedTarget, detailed information of the specific
     * target will be displayed in the panel below the table. For every null value, - is inserted as a placeholder.
     */
    private void updateDetailPanel() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            clearDetail();
            return;
        }
        int modelRow;
        try {
            modelRow = table.convertRowIndexToModel(viewRow);
        } catch (IndexOutOfBoundsException e) {
            clearDetail();
            return;
        }
        PhysicalObject prevSelectedTarget = selectedTarget;
        selectedTarget = model.getTargetAt(modelRow);
        if (selectedTarget == null) {
            clearDetail();
            return;
        }
        // If the target changed and a target was there before, unregister the tree listener to stop receiving updates
        // for the old target.
        if (prevSelectedTarget != selectedTarget) {
            if (prevSelectedTarget != null) prevSelectedTarget.removeTreeListener(selectedTargetListener);
            selectedTarget.registerTreeListener(selectedTargetListener);
        }
        String name = selectedTarget.getNameAsString();
        targetName.setText(name != null ? name : "-");
        if (selectedTarget.getPose() != null && selectedTarget.getPose().getCoordinate() != null) {
            String readablePosition = CRSUtils.toDegreeMinuteSecond(selectedTarget.getPose().getCoordinate().getLatitude()) + " / " +
                    CRSUtils.toDegreeMinuteSecond(selectedTarget.getPose().getCoordinate().getLongitude());
            targetCoordinates.setText(readablePosition);
        } else {
            targetCoordinates.setText("-");
        }
        Angle cog = PhysicalObjectUtils.getCOG(selectedTarget);
        targetCOG.setText(cog != null ? String.format("%.1f °", cog.getAs(AngleUnit.DEGREE)) : "-");
        AngularSpeed rot = PhysicalObjectUtils.getRateOfTurn(selectedTarget);
        targetROT.setText(rot != null ? String.format("%.1f °/min", rot.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE)) : "-");
        Speed sog = PhysicalObjectUtils.getSOG(selectedTarget);
        targetSOG.setText(sog != null ? String.format("%.1f kn", sog.getAs(SpeedUnit.KNOTS)) : "-");
        Angle heading = PhysicalObjectUtils.getHeading(selectedTarget);
        targetHeading.setText(heading != null ? String.format("%.1f °", heading.getAs(AngleUnit.DEGREE)) : "-");
        goToButton.setEnabled(true);
        repaint();
        revalidate();
    }

    /**
     * Creates the filter panel. This panel allows filtering on the table by type, id and name.
     * @return Filter panel component.
     */
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.weightx = 0;
        filterPanel.add(new JLabel("Type:"), gbc);
        gbc.weightx = 1.0;
        filterPanel.add(typeFilter, gbc);
        gbc.weightx = 0;
        filterPanel.add(new JLabel("ID:"), gbc);
        gbc.weightx = 0.5;
        filterPanel.add(idFilter, gbc);
        gbc.weightx = 0;
        filterPanel.add(new JLabel("Name:"), gbc);
        gbc.weightx = 1.0;
        filterPanel.add(nameFilter, gbc);
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> {
            nameFilter.setText("");
            idFilter.setText("");
            typeFilter.setText("");
        });
        gbc.weightx = 0;
        filterPanel.add(clearBtn, gbc);
        // Listeners for the filter fields.
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                applyFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                applyFilter();
            }

            public void changedUpdate(DocumentEvent e) {
                applyFilter();
            }
        };
        nameFilter.getDocument().addDocumentListener(dl);
        idFilter.getDocument().addDocumentListener(dl);
        typeFilter.getDocument().addDocumentListener(dl);
        return filterPanel;
    }

    /**
     * Initially creates the detail panel used for displaying detailed information on a selected target.
     * @return Created detail panel.
     */
    private JPanel createDetailPanel() {
        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Target Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] labels = {"NAME:", "POS:", "COG:", "SOG:", "HDG:", "ROT"};
        JLabel[] values = {targetName, targetCoordinates, targetCOG, targetSOG, targetHeading, targetROT};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = i % 3 * 2;
            gbc.gridy = i / 3;
            gbc.weightx = 0;
            detailPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx++;
            gbc.weightx = (i == 4) ? 0.1 : 0.3;
            detailPanel.add(values[i], gbc);
        }
        goToButton.setEnabled(false);
        goToButton.addActionListener(e -> {
            if (selectedTarget != null) listeners.forEach(l -> l.goTo(selectedTarget));
        });
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        detailPanel.add(goToButton, gbc);
        return detailPanel;
    }

    /**
     * Sets up the table component, registers listeners and sorters as well as formatters.
     */
    private void setupTable() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.setRowHeight(table.getRowHeight() + 10);
        sorter = new TableRowSorter<>(model);

        for (int col : new int[]{3, 4, 5, 6}) {
            sorter.setComparator(col, Comparator.comparingDouble(a -> (Double) a));
        }
        table.setRowSorter(sorter);
        // Renderers for the table. If a value is invalid, - is used as a placeholder.
        AlertRenderer defaultRenderer = new AlertRenderer(SwingConstants.LEFT, false);
        AlertRenderer brgRenderer = new AlertRenderer(SwingConstants.RIGHT, false) {
            @Override
            protected String format(Object value) {
                if (value instanceof Double d) return String.format("%.1f °", d);
                return "-";
            }
        };
        AlertRenderer rngRenderer = new AlertRenderer(SwingConstants.RIGHT, false) {
            @Override
            protected String format(Object value) {
                if (value instanceof Double d && d < DISTANCE_THRESHOLD.getValue() && d >= 0) return String.format("%.2f NM", d);
                return "-";
            }
        };
        AlertRenderer cpaRenderer = new AlertRenderer(SwingConstants.RIGHT, true) {
            @Override
            protected String format(Object value) {
                if (value instanceof Double d && d < CPA_THRESHOLD.getValue() && d >= 0) return String.format("%.2f NM", d);
                return "-";
            }
        };
        AlertRenderer tcpaRenderer = new AlertRenderer(SwingConstants.RIGHT, true) {
            @Override
            protected String format(Object value) {
                if (value instanceof Double d && d < TCPA_THRESHOLD.getValue() && d >= 0) {
                    long total = Math.round(d);
                    long m = total / 60, s = total % 60;
                    return String.format("%dM%02dS", m, s);
                }
                return "-";
            }
        };
        table.getColumnModel().getColumn(0).setCellRenderer(defaultRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(defaultRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(defaultRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(brgRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rngRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(cpaRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(tcpaRenderer);
        formatTable();
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) updateDetailPanel();
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && selectedTarget != null)
                    listeners.forEach(l -> l.goTo(selectedTarget));
            }
        });
    }

    /**
     * Formats the table based on the ENABLE_CALCULATIONS property. If it is set to false, calculation columns
     * are collapsed.
     */
    private void formatTable() {
        for (int i = 0; i < COL_PREF_WIDTHS.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            if(!ENABLE_CALCULATIONS.getValue() && (i == 3 || i == 4 || i == 5 || i ==6)) {
                col.setMinWidth(0);
                col.setPreferredWidth(0);
                col.setMaxWidth(0);
            } else {
                col.setMinWidth(COL_MIN_WIDTHS[i]);
                col.setPreferredWidth(COL_PREF_WIDTHS[i]);
                col.setMaxWidth(Integer.MAX_VALUE);
            }

        }
    }

    /**
     * Applies the table filter. This filters the table based on the textfield values established in the filter panel.
     */
    private void applyFilter() {
        String nameText = nameFilter.getText().trim();
        String idText = idFilter.getText().trim();
        String typeText = typeFilter.getText().trim();
        List<RowFilter<TargetTableModel, Integer>> filters = new ArrayList<>();
        if (!typeText.isEmpty())
            filters.add(RowFilter.regexFilter("(?i)" + typeText, 0));
        if (!nameText.isEmpty())
            filters.add(RowFilter.regexFilter("(?i)" + nameText, 1));
        if (!idText.isEmpty())
            filters.add(RowFilter.regexFilter("(?i)" + idText, 2));
        sorter.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
    }

    /**
     * Clears the detail panel and resets the values.
     */
    private void clearDetail() {
        if (selectedTarget != null) selectedTarget.removeTreeListener(selectedTargetListener);
        selectedTarget = null;
        for (JLabel l : new JLabel[]{targetName, targetCoordinates, targetCOG,
                targetSOG, targetHeading})
            l.setText("-");
        goToButton.setEnabled(false);
    }

    /**
     * Adds a goto listener to the table. This is fired with a selected target if the goto button is clicked
     * or the target row is double clicked.
     * @param listener Listener to add.
     */
    public void addGoToListener(GoToListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a goto listener from the table.
     * @param listener Listener to remove.
     */
    public void removeGoToListener(GoToListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Interface for the GoToListener. This listener is fired when the GoTo button is clicked for a specific target
     * or the target is double clicked in the table.
     */
    public interface GoToListener {

        /**
         * Gets fired when the GoTo button is clicked for a specific target or the target is double clicked in the table.
         * @param object PhysicalObject which was selected.
         */
        void goTo(PhysicalObject object);
    }

    /**
     * Rendering component for the table. This is an alert aware implementation which allows table cells to be colored
     * in the alert color when an alert is active.
     */
    private class AlertRenderer extends DefaultTableCellRenderer {
        private final boolean colorByAlert;

        /**
         * Creates a new AlertRenderer.
         * @param alignment SwingConstants alignment for aligning the cell, for example SwingConstants.LEFT or
         *                  SwingConstants.RIGHT.
         * @param colorByAlert Checks if the cell should be colored when an alert is active.
         */
        AlertRenderer(int alignment, boolean colorByAlert) {
            super();
            setHorizontalAlignment(alignment);
            this.colorByAlert = colorByAlert;
        }

        /**
         * Formats a string based on the value. If the value is null, - is inserted as a placeholder.
         * @param value Value to format.
         * @return Formatted string.
         */
        protected String format(Object value) {
            return value != null ? value.toString() : "-";
        }

        /**
         *
         * Returns the default table cell renderer.
         * <p>
         * During a printing operation, this method will be called with
         * <code>isSelected</code> and <code>hasFocus</code> values of
         * <code>false</code> to prevent selection and focus from appearing
         * in the printed output. To do other customization based on whether
         * or not the table is being printed, check the return value from
         * {@link JComponent#isPaintingForPrint()}.
         *
         * @param table      the <code>JTable</code>
         * @param value      the value to assign to the cell at
         *                   <code>[row, column]</code>
         * @param isSelected true if cell is selected
         * @param hasFocus   true if cell has focus
         * @param row        the row of the cell to render
         * @param column     the column of the cell to render
         * @return the default table cell renderer
         * @see JComponent#isPaintingForPrint()
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            TargetTableModel.TargetRow rowData = model.getRowData(table.convertRowIndexToModel(row));
            boolean alert = false;
            if (colorByAlert && rowData != null) {
                alert = switch (column) {
                    case 5 -> rowData.cpaAlert;
                    case 6 -> rowData.tcpaAlert;
                    default -> false;
                };
            }
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setText(format(value));

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else if (alert) {
                setBackground(ALERT_BACKGROUND);
                setForeground(table.getForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
}
