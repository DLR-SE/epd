package de.emir.service.connection.interfaces;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public interface ReceiverListener extends Listener {
	void onReceived(Receiver source, byte[] data);
}
