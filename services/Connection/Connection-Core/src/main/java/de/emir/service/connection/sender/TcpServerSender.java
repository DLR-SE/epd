package de.emir.service.connection.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import de.emir.service.connection.Util;
import de.emir.service.connection.interfaces.Sender;
import de.emir.service.connection.interfaces.SenderListener;

/**
 * This is the sender to put data to a UDP socket.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public class TcpServerSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LogManager.getLogger(TcpServerSender.class);
	private static final int MAX_RETRIES = 5;
	/** Sender listener. */
	private SenderListener listener;
	/** Server socket for all incoming connections. */
	private ServerSocket serverSocket;
//	/** Socket for individual channel. */
//	private Socket socket;
	/** Port for sending to. */
	private int port;
	/** Local address. */
	private String localAddress = null;
	/** Handle to thread. */
	private volatile Thread thread;
	/** Value for connection loop. */
	private volatile boolean run = true;
	/** List of connected threads. */
	private volatile List<ServerThread> serverThreads;

	/**
	 * Constructor for Sender.
	 * 
	 * @param adr
	 *            IP Address or Server name as a string.
	 * @param p
	 *            Destination port.
	 */
	public TcpServerSender(final SenderListener listener, int p) {
		this.listener = listener;
		this.port = p;
	}

	/**
	 * Constructor for Sender.
	 * 
	 * @param adr
	 *            IP Address or Server name as a string.
	 * @param p
	 *            Destination port.
	 * @param localadr
	 *            The local address to bind the port to
	 */
	public TcpServerSender(final SenderListener listener, int p, String localadr) {
		this.listener = listener;
		this.port = p;
		this.localAddress = localadr;
	}

	/**
	 * Method for connecting with the receiver.
	 */
	public void connect() {
//		socket = null;
		serverSocket = null;
//		serverThreads = new Vector<ServerThread>();
		serverThreads = Collections.synchronizedList(new ArrayList<ServerThread>());
		try {
			serverSocket = new ServerSocket(this.port);
		} catch (SocketException e) {
			LOG.error("SocketException: ", e);
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (IOException e) {
			LOG.error("IOException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket. ", e);
		}

		thread = new Thread() {
			@Override
			public void run() {
				try {
					while (run) {
						try {
							Socket socket = serverSocket.accept();
							LOG.info("new connection");
							ServerThread serverThread = new ServerThread(socket);
							serverThreads.add(serverThread);
							serverThread.start();
						} catch (SocketException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						Thread.sleep(10);
					}
				} catch (InterruptedException e) {
					LOG.warn("interrupted");
				}
			}
		};
		run = true;
		thread.start();
	}

	/**
	 * Method for disconnecting from the receiver.
	 */
	public void disconnect() {
		try {
			synchronized (serverThreads) {
				serverThreads.removeIf(e -> (e.isDisconnected()));
				for (ServerThread serverThread : serverThreads) {
					serverThread.disconnect();
				}
			}
			this.serverSocket.close();
		} catch (Exception e) {
			LOG.error("Exception: ", e);
		}
	}
	
	public synchronized void handleConnectionLoss(ServerThread st) {
		st.disconnect();
		try {
			st.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gateway.interfaces.Sender#send(java.lang.Byte[])
	 */
	public synchronized void send(byte[] data) {
		serverThreads.removeIf(e -> (e.isDisconnected()));
		for (ServerThread serverThread : serverThreads) {
			serverThread.send(data);
		}
	}

	/**
	 * Method for sending a given NMEA or GPX message as String.
	 * 
	 * @param message
	 *            NMEA sentence or GPX as a String.
	 */
	public void sendString(String message) {
		byte[] sendData;
		sendData = message.getBytes();
		send(sendData);
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param p
	 *            the port to set
	 */
	public void setPort(int p) {
		this.port = p;
	}

	/**
	 * Get the listener.
	 * 
	 * @return the listener
	 */
	public SenderListener getListener() {
		return this.listener;
	}

	/**
	 * @return the localAddress
	 */
	public String getLocalAddress() {
		return localAddress;
	}

	/**
	 * @param localAddress
	 *            the localAddress to set
	 */
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	class ServerThread extends Thread {
		//ServerSocket ss = null;
		Socket socket = null;
		OutputStream os = null;
		DataOutputStream outWriter = null;
		BufferedReader inputStream = null;
		long lastTimestamp = 0L;
		private volatile boolean disconnected = false;

		public ServerThread(Socket s) {
			this.socket = s;
		}
		
		private BufferedReader connect() {
			BufferedReader reader = null;

			try {
				//s = ss.accept();
				os = this.socket.getOutputStream();
				outWriter = new DataOutputStream(os);
				lastTimestamp = Calendar.getInstance().getTimeInMillis();
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				return reader;
			} catch (IOException e) {
				LOG.error("Could not create server on port " + port, e);
				return null;
			} catch (Exception e) {
				LOG.error("Could not create server on port " + port, e);
				return null;
			}
		}
		
		public void disconnect() {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outWriter != null) {
					outWriter.close();
				}
				if (socket != null) {
					socket.close();
					LOG.info("Socket Closed");
				}
			} catch (IOException e) {
				LOG.error("Socket Close Error", e);
			} finally {
				disconnected = true;
			}
			this.interrupt();
		}

		public void send(byte[] data) {
			try {
				int i = 0;
				while (socket == null || !socket.isConnected() && i < MAX_RETRIES) {
					connect();
					i++;
					Thread.sleep(3000);
				}
				outWriter.write(data);
				outWriter.flush();
				LOG.debug("Sending: " + new String(data).trim() + " to port " + port);
				listener.onSent(TcpServerSender.this, true);
			} catch (NullPointerException e) {
				LOG.warn("No data to write. ", e);
				listener.onSent(TcpServerSender.this, true);
			} catch (IOException e) {
				LOG.error("Could not send data. ", e);
				listener.onSent(TcpServerSender.this, false);
				this.disconnected = true;
				handleConnectionLoss(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				LOG.error("Could not send data. ", e);
				listener.onSent(TcpServerSender.this, false);
				this.disconnected = true;
				handleConnectionLoss(this);
			} 
		}
		
		public void run() {
			connect();
		}

		public boolean isDisconnected() {
			return disconnected;
		}
	}
}