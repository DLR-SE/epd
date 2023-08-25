/**
 * 
 */
package de.emir.epd.nmeasensor.receiver;

import de.emir.service.connection.interfaces.ReceiverListener;
import de.emir.service.connection.interfaces.Receiver;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public interface IConnection extends Receiver{
	void receive();
	
	ReceiverListener getListener();

	void stopReceiving();

	boolean getState();
	
	void send(byte[] data);
}
