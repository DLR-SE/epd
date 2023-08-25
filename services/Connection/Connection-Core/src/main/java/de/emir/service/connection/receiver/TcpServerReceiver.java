/**
 * 
 */
package de.emir.service.connection.receiver;

import de.emir.service.connection.interfaces.Receiver;
import de.emir.service.connection.interfaces.ReceiverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class TcpServerReceiver implements Receiver {
	/** Receiver listener. */
	private ReceiverListener listener;
	/** Value for receive loop. */
	private volatile boolean run = true;
	/** Port to listen to. */
	private Integer port;
	/** Instance of the receiver. */
	private Receiver instance = this;
	/** Size of received packages. */
	private Integer packetsize;
	/** Handle to thread. */
	private Thread thread;
	/** Server socket. */
	private ServerSocket socket;
	/** Communication socket. */
	private Socket connectionSocket;
	/** The timeout. */
	private long timeout = 30000;
	/** The last timestamp. */
	private long lastTimestamp = 0;
	/** Reader for TCP stream. */
	private BufferedReader reader;
	/** Log handler. */
	static final Logger LOG = LoggerFactory.getLogger(TcpServerReceiver.class);
	/** Local address. */
	private String local = System.getProperty("net.host", "0.0.0.0");
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 */
	public TcpServerReceiver(final ReceiverListener listener, Integer port, Integer packetsize) {
		this.listener = listener;
		this.port = port;
		this.packetsize = packetsize;
		this.local = System.getProperty("net.host", "0.0.0.0");
	}
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 * @localadr
	 */
	public TcpServerReceiver(final ReceiverListener listener, Integer port, Integer packetsize, String localadr) {
		this.listener = listener;
		this.port = port;
		this.packetsize = packetsize;
		this.local = localadr;
	}
	
	private void disconnect() {
		try {
			connectionSocket.shutdownInput();
			connectionSocket.shutdownOutput();
		} catch (Exception e) {
			LOG.error("Exception during disconnect: ", e);
		}
		if (reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				LOG.error("Exception during disconnect: ", e);
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
				LOG.error("Exception during disconnect: ", e);
			}
		}
		if (connectionSocket != null) {
			try {
				connectionSocket.close();
			} catch (Exception e) {
				LOG.error("Exception during disconnect: ", e);
			}
		}
		LOG.info("Sockets closed");
	}
	
	private BufferedReader connect() {
		BufferedReader reader = null;
		
		try {
			connectionSocket = socket.accept();
			lastTimestamp = Calendar.getInstance().getTimeInMillis();
			reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			return reader;
		} catch (Exception e) {
			LOG.error("Could not create server on port " + port, e);
			return null;
		}
	}
	
	private boolean isTimeout() {
		long now = Calendar.getInstance().getTimeInMillis();
		return ((int) (now - lastTimestamp)) > timeout;
	}

	/* (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new ServerSocket(port, 10,
							InetAddress.getByName(local));
					reader = connect();
					while (run) {
						String line = null;
						while (reader != null && (line = reader.readLine()) != null) {
							lastTimestamp = Calendar.getInstance().getTimeInMillis();
							listener.onReceived(instance, trimData(line.getBytes(), packetsize));
						}
						if (!connectionSocket.isConnected() || isTimeout() || reader == null) {
							reader = connect();
							Thread.sleep(3000);
						}
						Thread.sleep(0);
					}
//					reader.close();
//					connectionSocket.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				} catch (InterruptedException e) {

				}
			}
		};
		run = true;
		thread.start();
	}

	/*
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#stopReceiving()
	 */
	@Override
	public synchronized void stopReceiving() {
		this.run = false;
		if (thread != null) {
		    this.thread.interrupt();
        }
		disconnect();
	}

	/*
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#getListener()
	 */
	@Override
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

	/**
	 * Get the running state of this receiver.
	 * 
	 * @return the current state
	 */
	@Override
    public synchronized boolean getState() {
		return run;
	}

	/**
	 * Get this receivers assigned port.
	 * 
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @return the timeout
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
