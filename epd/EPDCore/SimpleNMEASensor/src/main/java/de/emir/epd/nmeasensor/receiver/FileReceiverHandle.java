/**
 * 
 */
package de.emir.epd.nmeasensor.receiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import de.emir.service.connection.interfaces.ReceiverListener;
import org.apache.logging.log4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Client for line based file input.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class FileReceiverHandle implements IConnection {
	/** Receiver listener. */
	private ReceiverListener listener;
	/** Value for receive loop. */
	private volatile boolean run = true;
	/** Instance of the receiver. */
	private IConnection instance = this;
	/** Handle to thread. */
	private Thread thread;
	/** Last timestamp. */
	private long lastTimestamp;
	/** Demo file. */
	private File file;
	/** Timeout time. */
	private int timeout;
	/** Repeat value. */
	private int repeat;
	/** Repeat counter. */
	private int runs;
	/** Log handler. */
	static final Logger LOG = ULog.getLogger(FileReceiverHandle.class);

	/**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 */
	public FileReceiverHandle(final ReceiverListener listener, String filename, int timeout, int repeat) {
		this.listener = listener;
		this.file = new File(filename);
		this.timeout = timeout;
		this.repeat = repeat;
		this.runs = 0;
	}

	private boolean isTimeout() {
		long now = Calendar.getInstance().getTimeInMillis();
		return ((int) (now - lastTimestamp)) > timeout;
	}

	public void receive() {
		thread = new Thread() {
			@Override
			public void run() {
				runs = 0;
				do {
					try {
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
						while (run) {
							String line;
							while ((line = reader.readLine()) != null) {
								listener.onReceived(instance, line.getBytes());
								Thread.sleep(timeout);
							}
							reader = new BufferedReader(
									new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
						}
						reader.close();
					} catch (IOException e) {
						LOG.error("IOException", e);
					} catch (InterruptedException e) {
						LOG.debug("Stopped file receiver thread.", e);
					}
					runs++;
				} while (runs < repeat || repeat < 0);
			}
		};
		run = true;
		thread.start();
	}

	public void stopReceiving() {
		this.run = false;
		if (thread != null) {
			this.thread.interrupt();
		}
	}

	public ReceiverListener getListener() {
		return this.listener;
	}

	/**
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	private byte[] trimData(byte[] data, int length) {
		if (data.length <= length) {
			return data;
		}
		byte[] temp = new byte[length];
		for (int x = 0; x < temp.length; x++) {
			temp[x] = data[x];
		}
		return temp;
	}

	public boolean getState() {
		return run;
	}

	@Override
	public void send(byte[] data) {
		throw new UnsupportedOperationException("Sending not yet implemented in class FileReceiver.");
	}
}
