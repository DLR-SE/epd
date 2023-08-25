/**
 * 
 */
package de.emir.service.connection.receiver;

import de.emir.service.connection.interfaces.Receiver;
import de.emir.service.connection.interfaces.ReceiverListener;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class HttpReceiver implements Receiver {
	private ReceiverListener listener;
	
	public HttpReceiver(final ReceiverListener listener) {
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
		// TODO
	}

	public ReceiverListener getListener() {
		return this.listener;
	}

	@Override
	public void stopReceiving() {
		// TODO
	}

	@Override
	public boolean getState() {
		// TODO
		return false;
	}

}
