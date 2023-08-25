package de.emir.epd.nmeasensor.settings;

import javax.swing.JPanel;

import de.emir.epd.nmeasensor.data.ReceiverProperty;

/**
 * This abstract class hold common attributes and methods for the different receiver settings.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public abstract class AbstractReceiverSettings extends JPanel {
	/** Reference to the parent settings page. */
	protected NMEASensorSettingsPage caller;
	
	/** Set this flag to true after changes have been made. */
	protected boolean dirtyFlag;
	
	/** Every reference to the settings property. */
	protected ReceiverProperty receiverProperty;
	
	/**
	 * The dirty flag is true after changes have been made. 
	 * 
	 * @return <code>true</code> if the settings have changed.
	 */
	public boolean isDirty() {
		return dirtyFlag ;
	}
}
