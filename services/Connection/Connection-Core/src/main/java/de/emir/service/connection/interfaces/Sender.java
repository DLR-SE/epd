/**
 * 
 */
package de.emir.service.connection.interfaces;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public interface Sender {
	void send(byte[] data);
	
	Listener getListener();

	void connect();
	
	void disconnect();
}
