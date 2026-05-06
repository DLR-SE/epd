package de.emir.epd.nmeasensor.settings;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.properties.PropertyFileChooserWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class FileReceiverSettings extends AbstractReceiverSettings {
	private PropertyFileChooserWidget filenameTextField;
	private PropertySpinnerWidget delaySpinner;
	private PropertySpinnerWidget repeatSpinner;
	private SpinnerModel delaySpinnerModel;
	private SpinnerModel repeatSpinnerModel;
    private int initialDelay = 1000;
	private int initialRepeat = -1;

	public FileReceiverSettings(NMEASensorSettingsPage caller) {
		setBorder(new EmptyBorder(8, 8, 8, 8));
		this.caller = caller;
		setPreferredSize(new Dimension(240, 120));
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{37, 157, 0};
		gridBagLayout.rowHeights = new int[]{30, 20, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
        repeatSpinner = new PropertySpinnerWidget(
                NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_REPEAT,
                -1
        );
        initialRepeat = (int) repeatSpinner.getValue();
				
        filenameTextField = new PropertyFileChooserWidget(
                "Filename", NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
            caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_FILENAME,
            "nmea.txt", null /*new FileNameExtensionFilter("All files", "*")*/
        );
        filenameTextField.setMaximumSize(new Dimension(60, 48));
        GridBagConstraints gbc_filenameTextField = new GridBagConstraints();
        gbc_filenameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_filenameTextField.anchor = GridBagConstraints.NORTH;
        gbc_filenameTextField.insets = new Insets(0, 0, 5, 0);
        gbc_filenameTextField.gridwidth = 2;
        gbc_filenameTextField.gridx = 0;
        gbc_filenameTextField.gridy = 0;
        add(filenameTextField, gbc_filenameTextField);

        delaySpinner = new PropertySpinnerWidget(
                NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT,
                caller.getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_DELAY,
                1000
        );
        initialDelay = (int) delaySpinner.getValue();

        JLabel lblDelay = new JLabel("Delay");
        lblDelay.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_lblDelay = new GridBagConstraints();
        gbc_lblDelay.anchor = GridBagConstraints.WEST;
        gbc_lblDelay.insets = new Insets(0, 0, 5, 5);
        gbc_lblDelay.gridx = 0;
        gbc_lblDelay.gridy = 1;
        add(lblDelay, gbc_lblDelay);

        GridBagConstraints gbc_delaySpinner = new GridBagConstraints();
        gbc_delaySpinner.anchor = GridBagConstraints.NORTH;
        gbc_delaySpinner.fill = GridBagConstraints.HORIZONTAL;
        gbc_delaySpinner.insets = new Insets(0, 0, 5, 0);
        gbc_delaySpinner.gridx = 1;
        gbc_delaySpinner.gridy = 1;
        add(delaySpinner, gbc_delaySpinner);

        JLabel lblRepeat = new JLabel("Repeat");
        lblRepeat.setHorizontalAlignment(SwingConstants.TRAILING);
        GridBagConstraints gbc_lblRepeat = new GridBagConstraints();
        gbc_lblRepeat.anchor = GridBagConstraints.WEST;
        gbc_lblRepeat.insets = new Insets(0, 0, 0, 5);
        gbc_lblRepeat.gridx = 0;
        gbc_lblRepeat.gridy = 2;
        add(lblRepeat, gbc_lblRepeat);

        GridBagConstraints gbc_repeatSpinner = new GridBagConstraints();
        gbc_repeatSpinner.anchor = GridBagConstraints.NORTH;
        gbc_repeatSpinner.fill = GridBagConstraints.HORIZONTAL;
        gbc_repeatSpinner.gridx = 1;
        gbc_repeatSpinner.gridy = 2;
        add(repeatSpinner, gbc_repeatSpinner);
	}
	
    @Override
	public void readValues() {
		dirtyFlag = (
                filenameTextField.isDirty()
                || delaySpinner.isDirty()
				|| repeatSpinner.isDirty()
        );
		PropertyStore.save();
	}
    
    @Override
    public boolean isDirty() {
        readValues();
        return dirtyFlag;
    }
	
    @Override
	public void init() {
		dirtyFlag = false;
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(delaySpinner, "#");
		editor.getFormat().setGroupingUsed(false);
		delaySpinner.setEditor(editor);
		delaySpinnerModel = new SpinnerNumberModel(initialDelay, 1, 256000, 1);
		delaySpinner.setModel(delaySpinnerModel);
		delaySpinnerModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
		JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(repeatSpinner, "#");
		editor2.getFormat().setGroupingUsed(false);
		repeatSpinner.setEditor(editor2);
		repeatSpinnerModel = new SpinnerNumberModel(initialRepeat, -1, 65535, 1);
		repeatSpinner.setModel(repeatSpinnerModel);
		repeatSpinnerModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				readValues();
			}
		});
	}

    @Override
    public void finish() {
        filenameTextField.finish();
        delaySpinner.finish();
        repeatSpinner.finish();
    }
}
