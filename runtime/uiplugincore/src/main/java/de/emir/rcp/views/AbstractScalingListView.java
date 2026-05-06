package de.emir.rcp.views;


import de.emir.tuml.ucore.runtime.logging.ULog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The ScalingListView is an UI component for displaying values and corresponding labels in
 * a grid layout. The labels and values sizes scale within predefined bounds according to the available space.
 */
public abstract class AbstractScalingListView extends AbstractView {
    protected JScrollPane scrollPane;
    protected JPanel rootPanel;
    protected Color labelColor = UIManager.getColor("Label.foreground").darker();
    protected Color valueColor = UIManager.getColor("Label.foreground");
    private float minLabelSize = 20f;
    private float maxLabelSize = 26f;
    private float labelValueRatio = 4f;
    private boolean heightCalculation = false;
    private int baseRowCount = 0;
    protected Map<String, Entry> valueLabels = Collections.synchronizedMap(new LinkedHashMap<>());


    /**
     * Creates a new AbstractScalingListView instance.
     *
     * @param id Unique ID of the view.
     */
    public AbstractScalingListView(String id) {
        super(id);
    }

    /**
     * Toggles the height calulation of the fonts. When enabled, the height of the panel also
     * influences the label sizes.
     *
     * @param heightCalculation True if height should be incorporated for font size calculation.
     */
    protected void setHeightCalculation(boolean heightCalculation) {
        this.heightCalculation = heightCalculation;
        updateFonts();
    }

    /**
     * Sets the base font sizes for scaling. These specify how much the label and value fonts can grow.
     *
     * @param minValueSize Min font size to apply
     * @param maxValueSize Max font size to apply.
     */
    protected void setBaseFontSizes(float minValueSize, float maxValueSize) {
        this.minLabelSize = minValueSize;
        this.maxLabelSize = maxValueSize;
        SwingUtilities.invokeLater(this::updateFonts);
    }

    protected void setBaseRowCount(int count) {
        this.baseRowCount = count;
        updateFonts();
    }

    /**
     * Sets the label to value ratio. This is subtracted from the label size compared to the value font size in order
     * to make the value labels appear smaller.
     *
     * @param ratio Font size to subtract and apply to the label.
     */
    protected void setLabelValueRatio(float ratio) {
        this.labelValueRatio = ratio;
        updateFonts();
    }

    /**
     * Sets the string prototype for a given label. This prototype determines
     * the longest string the label can take and is used for calculating the scaling to
     * prevent labels from changing the layout depending on their size.
     *
     * @param key       Key of label to change prototype for.
     * @param prototype Prototype of label. For example for SOG 33.33kn XX.XXkn to calculate
     *                  the maximum space.
     */
    protected void setPrototype(String key, String prototype) {
        if (valueLabels.containsKey(key)) {
            valueLabels.get(key).valuePrototype = prototype;
            updateFonts();
        }
    }

    /**
     * Clears the panel and redraws based on currently registered components.
     */
    public void redraw() {
        if (rootPanel == null) return;
        rootPanel.removeAll();
        int row = 0;
        for (Map.Entry<String, Entry> entry : valueLabels.entrySet()) {
            addRow(entry.getValue().name, entry.getValue().value, row++);
        }
        GridBagConstraints filler = new GridBagConstraints();
        filler.gridx = 0;
        filler.gridy = row;
        filler.gridwidth = 2;
        filler.weighty = 1.0;
        rootPanel.add(Box.createVerticalGlue(), filler);
        updateFonts();
        rootPanel.revalidate();
        rootPanel.repaint();
    }

    /**
     * Adds a row to the layout.
     *
     * @param keyLabel   Label of the value to add. This is a descriptor of the value.
     * @param valueLabel Value to add.
     * @param row        Row in which to add the combination.
     */
    private void addRow(JLabel keyLabel, JLabel valueLabel, int row) {
        keyLabel.setForeground(labelColor);
        keyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        keyLabel.setMinimumSize(new Dimension(0, keyLabel.getPreferredSize().height));
        GridBagConstraints gbcKey = new GridBagConstraints();
        gbcKey.anchor = GridBagConstraints.BASELINE;
        gbcKey.gridx = 0;
        gbcKey.gridy = row;
        gbcKey.insets = new Insets(4, 0, 4, 4);
        gbcKey.fill = GridBagConstraints.HORIZONTAL;
        gbcKey.weightx = 0.2;
        rootPanel.add(keyLabel, gbcKey);
        valueLabel.setForeground(valueColor);
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);
        valueLabel.setMinimumSize(new Dimension(0, valueLabel.getPreferredSize().height));
        GridBagConstraints gbcVal = new GridBagConstraints();
        gbcVal.fill = GridBagConstraints.HORIZONTAL;
        gbcVal.anchor = GridBagConstraints.BASELINE;
        gbcVal.weightx = 0.8;
        gbcVal.gridx = 1;
        gbcVal.gridy = row;
        gbcVal.insets = new Insets(4, 0, 4, 0);
        rootPanel.add(valueLabel, gbcVal);
    }

    /**
     * Sets or adds the value of an entry.
     *
     * @param key   Key to add. This is the descriptor of the value shown to the user.
     * @param value Value to add or update.
     */
    public void setEntry(String key, String value) {
        Entry valueLabel = valueLabels.computeIfAbsent(key, k -> new Entry(key, value));
        valueLabel.setValue(value);
    }

    /**
     * Removes an entry from the list.
     *
     * @param key Key of entry to remove.
     */
    public void removeEntry(String key) {
        valueLabels.remove(key);
    }

    /**
     * Rescales all fonts to match the current constraints.
     */
    protected void updateFonts() {
        if (scrollPane == null) return;
        int viewportWidth = scrollPane.getViewport().getWidth();
        int viewportHeight = scrollPane.getViewport().getHeight();
        if (viewportWidth <= 0 || viewportHeight <= 0) {
            Window w = SwingUtilities.getWindowAncestor(scrollPane);
            if (w != null) {
                viewportWidth = w.getWidth();
                viewportHeight = w.getHeight();
            }
            if (viewportWidth <= 0 || viewportHeight <= 0) return;
        }
        Insets panelInsets = rootPanel.getInsets();
        int scrollbarWidth = scrollPane.getVerticalScrollBarPolicy() == JScrollPane.VERTICAL_SCROLLBAR_NEVER ? 0
                : scrollPane.getVerticalScrollBar().getPreferredSize().width;
        int availableWidth = viewportWidth - scrollbarWidth - panelInsets.left - panelInsets.right;
        int valueColWidth = (int) (availableWidth * 0.8);
        int totalRows = valueLabels.size();
        if (totalRows == 0) return;
        float valueSize = maxLabelSize;
        // Calculates the font size on available height when activated. This uses
        if (heightCalculation) {
            int scalingRowCount = (baseRowCount > 0 && baseRowCount <= totalRows) ? baseRowCount : totalRows;
            int availableHeight = viewportHeight - panelInsets.top - panelInsets.bottom;
            final int ROW_INSETS = 8;
            float lowestLabelSize = minLabelSize;
            float highestLabelSize = maxLabelSize;
            Optional<Entry> referenceEntry = valueLabels.values().stream().findFirst();
            if(referenceEntry.isPresent()) {
                JLabel referenceLabel = referenceEntry.get().value;
                while (highestLabelSize - lowestLabelSize > 0.5f) {
                    float mid = (lowestLabelSize + highestLabelSize) / 2f;
                    Font testFont = referenceLabel.getFont().deriveFont(Font.BOLD, mid);
                    FontMetrics fm = referenceLabel.getFontMetrics(testFont);
                    int realRowHeight = fm.getHeight() + ROW_INSETS;
                    int totalContentHeight = scalingRowCount * realRowHeight;
                    if (totalContentHeight <= availableHeight) {
                        lowestLabelSize = mid;
                    } else {
                        highestLabelSize = mid;
                    }
                }
            }
            valueSize = lowestLabelSize;
        }
        // Calculate font size on available width.
        valueSize = fitFontSizeToWidth(valueLabels.values(), false, valueSize, valueColWidth, minLabelSize);
        float labelSize = (valueSize - labelValueRatio) > 0 ? (valueSize - labelValueRatio) : valueSize;
        for (Entry entry : valueLabels.values()) {
            entry.name.setFont(entry.name.getFont().deriveFont(labelSize));
            entry.value.setFont(entry.value.getFont().deriveFont(Font.BOLD, valueSize));
        }
        rootPanel.revalidate();
        rootPanel.repaint();
    }

    /**
     * Calculates the maximum size of the font depending on the available space.
     *
     * @param entries      Entries to sync against each other.
     * @param useNameLabel True if the size for the key should be calculated.
     * @param startSize    Maximum font size.
     * @param maxWidth     Maximum available width.
     * @param minSize      Minimum font size.
     * @return Font size for given width.
     */
    private float fitFontSizeToWidth(Iterable<Entry> entries, boolean useNameLabel,
                                     float startSize, int maxWidth, float minSize) {
        if (maxWidth <= 0) return Math.max(startSize, minSize);
        float size = startSize;
        while (size > minSize) {
            boolean fits = true;
            for (Entry entry : entries) {
                JLabel label = useNameLabel ? entry.name : entry.value;
                Font testFont = useNameLabel ? label.getFont().deriveFont(size) : label.getFont().deriveFont(Font.BOLD, size);
                FontMetrics fm = label.getFontMetrics(testFont);
                String labelString;
                if (!useNameLabel && entry.valuePrototype != null && !entry.valuePrototype.isEmpty()) {
                    labelString = entry.valuePrototype;
                } else {
                    labelString = label.getText();
                }
                if (fm.stringWidth(labelString) > maxWidth) {
                    fits = false;
                    break;
                }
            }
            if (fits) break;
            size -= 0.5f;
        }
        return Math.max(size, minSize);
    }


    /**
     * Populates the content of the view.
     *
     * @return Content of the view which should be displayed.
     */
    @Override
    public Component createContent() {
        rootPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                int viewportWidth = scrollPane != null ? scrollPane.getViewport().getWidth() : d.width;
                return new Dimension(Math.min(d.width, viewportWidth), d.height);
            }

            @Override
            public Dimension getMaximumSize() {
                Dimension d = super.getMaximumSize();
                int viewportWidth = scrollPane != null ? scrollPane.getViewport().getWidth() : d.width;
                return new Dimension(Math.min(d.width, viewportWidth), d.height);
            }
        };
        rootPanel.setBorder(new EmptyBorder(4, 12, 4, 12));
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 1};
        gbl.columnWeights = new double[]{0.0, 1.0};
        rootPanel.setLayout(gbl);
        scrollPane = new JScrollPane(rootPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFonts();
            }
        });
        SwingUtilities.invokeLater(this::redraw);
        return scrollPane;
    }

    /**
     * Placeholder for a list row. This includes a label which describes the value
     * (also known as key in this view) and a label displaying a value.
     */
    public class Entry {
        JLabel name;
        JLabel value;
        String valuePrototype;

        /**
         * Creates a new entry.
         *
         * @param name  Text of the descriptor label.
         * @param value Text of the value label.
         */
        Entry(String name, String value) {
            this.name = new JLabel(name + ":");
            this.value = new JLabel(value);
            try {
                this.value.setFont(UIManager.getFont("large.font"));
            } catch (NullPointerException e) {
                ULog.warn("Could not find fonts for ScalingListView. Defaulting to default font.");
            }
        }

        /**
         * Sets the value of the entry.
         *
         * @param value New value to set to.
         */
        void setValue(String value) {
            this.value.setText(value);
        }
    }
}


