package de.emir.service.connection.interfaces;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public interface SenderListener extends Listener {
	void onSent(Sender source, Boolean success);
}
