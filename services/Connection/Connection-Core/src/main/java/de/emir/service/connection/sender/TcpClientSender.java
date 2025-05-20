package de.emir.service.connection.sender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

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
public class TcpClientSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LogManager.getLogger(TcpClientSender.class);
	private static final int MAX_RETRIES = 5;
	/** Sender listener. */
	private SenderListener listener;
	/** Socket. */
	private volatile Socket socket;
	/** IP Address or Server name as a string. */
	private String destination;
	/** IP Address or Server name as a InetAddress object. */
	private InetAddress ipAddress;
	/** Port for sending to. */
	private int port;
	/** Local address. */
	private String localAddress = null;
	private volatile OutputStream os;
	private volatile DataOutputStream outWriter;

	/**
	 * Constructor for Sender.
	 * 
	 * @param adr
	 *            IP Address or Server name as a string.
	 * @param p
	 *            Destination port.
	 */
	public TcpClientSender(final SenderListener listener, String adr, int p) {
		this.listener = listener;
		this.destination = adr;
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
	public TcpClientSender(final SenderListener listener, String adr, int p, String localadr) {
		this.listener = listener;
		this.destination = adr;
		this.port = p;
		this.localAddress = localadr;
	}

	/**
	 * Method for connecting with the receiver.
	 */
	public void connect() {
		try {
			if (localAddress != null) {
				this.socket = new Socket(
						InetAddress.getByName(this.destination), port,
						InetAddress.getByName(this.localAddress), 0);
			} else {
				this.socket = new Socket(
						InetAddress.getByName(this.destination), port,
						InetAddress.getByName(System.getProperty("net.host", "0.0.0.0")), 0);
			}
		} catch (SocketException e) {
			LOG.error("SocketException: ", e);
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (IOException e) {
			LOG.error("IOException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket. ", e);
		}

		try {
			this.ipAddress = InetAddress.getByName(this.destination);
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket. ", e);
		}
	}

	/**
	 * Method for disconnecting from the receiver.
	 */
	public void disconnect() {
		try {
			this.outWriter.flush();
			this.outWriter.close();
			this.socket.close();
			this.os.close();
		} catch (Exception e) {
			LOG.error("Exception: ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gateway.interfaces.Sender#send(java.lang.Byte[])
	 */
	public void send(byte[] data) {
		outWriter = null;
		try {
			int i = 0;
			while (socket == null || !socket.isConnected()) { //  && i < MAX_RETRIES) {
				connect();
				i++;
				Thread.sleep(3000);
			}
			os = this.socket.getOutputStream();
			outWriter = new DataOutputStream(os);
			outWriter.write(data);
			outWriter.flush();
			LOG.debug("Sending: " + new String(data).trim() + " to port "
					+ this.port);
			this.listener.onSent(this, true);
		} catch (NullPointerException e) {
			LOG.warn("No data to write. ", e);
			listener.onSent(TcpClientSender.this, true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error("Could not send data. ", e);
			this.listener.onSent(this, false);
            try {
                outWriter.close();
                this.disconnect();
            } catch (Exception e2) {
                LOG.error("Could not disconnect in a clean manner.", e2);
            } finally {
                socket = null;
            }
		}
		// if (outWriter != null) {
		// outWriter.close();
		// }
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
	 * @return the address
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param addr
	 *            the address to set destination
	 */
	public void setDestination(String addr) {
		this.destination = addr;
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
}