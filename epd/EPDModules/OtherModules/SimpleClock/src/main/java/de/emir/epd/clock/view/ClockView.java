package de.emir.epd.clock.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.zone.ZoneRulesException;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import de.emir.epd.clock.ids.ClockBasics;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * UI component for displaying the clock.
 */
public class ClockView extends AbstractView {

    private static final ZoneId TZ_UTC = ZoneId.of("UTC");
    // Default color for local clock and world clock.
    private static final Color DEFAULT_BLUE = new Color(0x4D9EFF);
    private static final Color DEFAULT_GRAY = new Color(0x888888);

    private ClockLabel localTimeLabel;
    private ClockLabel utcTimeLabel;
    private boolean syncPending = false;
    private static IProperty<String> localTimeZoneProperty = PropertyStore.getContext(ClockBasics.CLOCK_PROP_CONTEXT)
            .getProperty(ClockBasics.CLOCK_PROP_TZ, ZoneId.systemDefault().toString());
    private ZoneId tzLocal;
    private final PropertyChangeListener propertyChangeListener;
    private JPanel panel;
    private JLabel lblTzLocal;

    public ClockView(String id) {
        super(id);
        updateLocalTimeZoneProperty();
        this.propertyChangeListener = evt -> updateLocalTZLabels();

        localTimeZoneProperty.addPropertyChangeListener(this.propertyChangeListener);
    }
    
    /**
     * Re-read the local timezone property and ensure it is valid.
     */
    private void updateLocalTimeZoneProperty() {
    	ULog.debug("Update timezone to {}.", localTimeZoneProperty.getValue());
    	try {
    		tzLocal = ZoneId.of(localTimeZoneProperty.getValue());
    	} catch (ZoneRulesException e) {
    		ULog.warn("Could not set timezone to {}. Using system default.", localTimeZoneProperty.getValue(), e);
    		tzLocal = ZoneId.systemDefault();
    	}
    }
    
    /**
     * Re-read the local timezone property and apply it to the clock view.
     */
    private void updateLocalTZLabels() {
    	updateLocalTimeZoneProperty();
    	if (lblTzLocal == null || localTimeLabel == null || panel == null) {
    		// only continue if ui components are present
    		return;
    	}
    	lblTzLocal.setText(tzLocal.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
        localTimeLabel.setZone(tzLocal);
        panel.repaint();
    }

    /**
     * Creates the contents of the ClockView.
     *
     * @return Components used for displaying in the view.
     */
    @Override
    public Component createContent() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(4, 12, 4, 12));

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 1};
        gbl.rowHeights = new int[]{1, 1};
        gbl.columnWeights = new double[]{0.0, 1.0};
        gbl.rowWeights = new double[]{1.0, 1.0};
        panel.setLayout(gbl);

        // Tries to get the theme colors for the clock. If it fails, a preconfigured default color should be used.
        Color localColor = UIManager.getColor("Component.accentColor");
        if (localColor == null) localColor = UIManager.getColor("TabbedPane.focusColor");
        if (localColor == null) localColor = DEFAULT_BLUE;
        Color utcColor = UIManager.getColor("Label.foreground");
        if (utcColor == null) utcColor = DEFAULT_GRAY;
        utcColor = utcColor.darker();

        // Local time configuration.
        // Should we use the timezone name here or just LOCAL? Timezone seems better.
        lblTzLocal = new JLabel(tzLocal.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
        lblTzLocal.setHorizontalAlignment(SwingConstants.LEFT);
        lblTzLocal.setForeground(localColor);
        GridBagConstraints gbcLocalTimeZoneLabel = new GridBagConstraints();
        gbcLocalTimeZoneLabel.anchor = GridBagConstraints.WEST;
        gbcLocalTimeZoneLabel.insets = new Insets(0, 0, 5, 4);
        gbcLocalTimeZoneLabel.gridx = 0;
        gbcLocalTimeZoneLabel.gridy = 0;
        panel.add(lblTzLocal, gbcLocalTimeZoneLabel);
        localTimeLabel = new ClockLabel(tzLocal);
        localTimeLabel.setFont(UIManager.getFont("TextArea.font").deriveFont(Font.BOLD, (float) ClockBasics.MIN_CLOCK_SIZE));
        localTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        localTimeLabel.setForeground(localColor);
        GridBagConstraints gbcLocalLabel = new GridBagConstraints();
        gbcLocalLabel.fill = GridBagConstraints.BOTH;
        gbcLocalLabel.weightx = 1.0;
        gbcLocalLabel.weighty = 1.0;
        gbcLocalLabel.insets = new Insets(0, 0, 5, 0);
        gbcLocalLabel.gridx = 1;
        gbcLocalLabel.gridy = 0;
        panel.add(localTimeLabel, gbcLocalLabel);

        // Worldwide time configuration.
        JLabel tzUtc = new JLabel(TZ_UTC.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
        tzUtc.setHorizontalAlignment(SwingConstants.LEFT);
        tzUtc.setForeground(utcColor);
        GridBagConstraints gbcUTCTimeZoneLabel = new GridBagConstraints();
        gbcUTCTimeZoneLabel.anchor = GridBagConstraints.WEST;
        gbcUTCTimeZoneLabel.insets = new Insets(0, 0, 0, 4);
        gbcUTCTimeZoneLabel.gridx = 0;
        gbcUTCTimeZoneLabel.gridy = 1;
        panel.add(tzUtc, gbcUTCTimeZoneLabel);
        utcTimeLabel = new ClockLabel(TZ_UTC);
        utcTimeLabel.setFont(UIManager.getFont("TextArea.font").deriveFont(Font.BOLD, (float) ClockBasics.MIN_CLOCK_SIZE));
        utcTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        utcTimeLabel.setForeground(utcColor);
        GridBagConstraints gbcUTCLabel = new GridBagConstraints();
        gbcUTCLabel.fill = GridBagConstraints.BOTH;
        gbcUTCLabel.weightx = 1.0;
        gbcUTCLabel.weighty = 1.0;
        gbcUTCLabel.insets = new Insets(0, 0, 0, 0);
        gbcUTCLabel.gridx = 1;
        gbcUTCLabel.gridy = 1;
        panel.add(utcTimeLabel, gbcUTCLabel);

        panel.addComponentListener(new ComponentAdapter() {
            /**
             * Overrides the resize method. This should fix issues where resizing was only possible
             * in little steps due to ongoing size calculations in the background.
             * @param e the event to be processed.
             */
            @Override
            public void componentResized(ComponentEvent e) {
                if (!syncPending) {
                    syncPending = true;
                    SwingUtilities.invokeLater(() -> {
                        syncPending = false;
                        syncClocks(panel);
                    });
                }
            }
        });
        return panel;
    }

    /**
     * Syncs both clock labels to the root panel. This ensures that the clocks scale correctly.
     *
     * @param panel Panel to use for estimating available size.
     */
    private void syncClocks(JPanel panel) {
        Insets insets = panel.getInsets();
        // Used fixed values from estimation here. Could maybe be improved.
        int tzColumnWidth = 45;
        int availableWidth = panel.getWidth() - insets.left - insets.right - tzColumnWidth;
        int availableHeight = (panel.getHeight() - insets.top - insets.bottom - 10) / 2;
        if (availableWidth <= 0 || availableHeight <= 0) return;
        int syncSize = Math.min(
                localTimeLabel.computeBestSize(availableWidth, availableHeight),
                utcTimeLabel.computeBestSize(availableWidth, availableHeight)
        );
        localTimeLabel.applyLayout(availableWidth, availableHeight, syncSize);
        utcTimeLabel.applyLayout(availableWidth, availableHeight, syncSize);
    }

    @Override
    public void onClose() {
    }

    @Override
    public void onOpen() {
    }
}