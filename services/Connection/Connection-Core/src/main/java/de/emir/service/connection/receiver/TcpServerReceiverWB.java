/**
 * 
 */
package de.emir.service.connection.receiver;

import com.google.common.collect.EvictingQueue;
import de.emir.service.connection.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * TCP Server Receiver with writeback.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class TcpServerReceiverWB implements Receiver, Sender {
	/** Receiver listener. */
	private ReceiverListener listener;
	/** Sender listener. */
	private SenderListener slistener;
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
	/** Writer for TCP stream. */
	private BufferedWriter writer;
	/** Log handler. */
	static final Logger LOG = LoggerFactory.getLogger(TcpServerReceiverWB.class);
	/** Queue of messages to send. */
	private volatile EvictingQueue<String> sendingMessages;
	private String local;
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 */
	public TcpServerReceiverWB(final Listener listener, Integer port, Integer packetsize) {
		this.listener = (ReceiverListener) listener;
		this.slistener = (SenderListener) listener;
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
	public TcpServerReceiverWB(final Listener listener, Integer port, Integer packetsize, String localadr) {
		this.listener = (ReceiverListener) listener;
		this.slistener = (SenderListener) listener;
		this.port = port;
		this.packetsize = packetsize;
		this.local = localadr;
	}
	
	@Override
	public void disconnect() {
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
		if (writer != null) {
			try {
				writer.close();
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
	
	@Override
	public void connect() {
		reader = null;
		writer = null;
		try {
			connectionSocket = socket.accept();
			lastTimestamp = Calendar.getInstance().getTimeInMillis();
			reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
		} catch (Exception e) {
			LOG.error("Could not create server on port " + port, e);
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
					connect();
					while (run && getSendingMessages() != null) {
						String line = null;
						while (reader != null && (line = reader.readLine()) != null) {
							lastTimestamp = Calendar.getInstance().getTimeInMillis();
							listener.onReceived(instance, trimData(line.getBytes(), packetsize));
						}
						if (!connectionSocket.isConnected() || isTimeout() || reader == null) {
							connect();
							Thread.sleep(3000);
						}
						Thread.sleep(0);
					}
//					reader.close();
//					connectionSocket.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				} catch (InterruptedException ignored) {

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
	
	private synchronized EvictingQueue<String> getSendingMessages() {
		if (sendingMessages == null) {
			sendingMessages = EvictingQueue.create(127);
		}
		return sendingMessages;
	}

	@Override
	public synchronized void send(byte[] data) {
//		getSendingMessages().add(new String(data));
		if (writer != null/* && getSendingMessages().size() > 0*/) {
			try {
				String msg = new String(data);/*getSendingMessages().poll();*/
				writer.write(msg);
				writer.flush();
				LOG.debug("Sending: " + msg.trim() + " to port "
						+ port);
				slistener.onSent(TcpServerReceiverWB.this, true);
			} catch (NullPointerException e) {
				LOG.error("No data to write. ", e);
				slistener.onSent(this, true);
			} catch (Exception e) {
				LOG.error("Could not send data on port " + port, e);
				slistener.onSent(TcpServerReceiverWB.this, false);
			}
		}
//		thread.interrupt();
	}
}
