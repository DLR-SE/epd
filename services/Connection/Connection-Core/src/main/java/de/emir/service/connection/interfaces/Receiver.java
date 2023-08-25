/**
 * 
 */
package de.emir.service.connection.interfaces;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public interface Receiver {
	void receive();
	
	Listener getListener();

	void stopReceiving();

	boolean getState();
}
