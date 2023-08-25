package de.emir.service.connection.sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.emir.service.connection.Util;
import de.emir.service.connection.interfaces.Sender;
import de.emir.service.connection.interfaces.SenderListener;

/**
 * This is the sender to put data to a UDP socket.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public class UdpSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LoggerFactory.getLogger(UdpSender.class);
	/** Sender listener. */
	private SenderListener listener;
	/** DatagramSocket. */
	private DatagramSocket socket;
	/** IP Address or Server name as a string. */
	private String destination;
	/** IP Address or Server name as a InetAddress object. */
	private InetAddress ipAddress;
	/** Port for sending to. */
	private int port;
	/** Local address. */
	private String localAddress = null;

	/**
	 * Constructor for Sender.
	 * 
	 * @param adr
	 *            IP Address or Server name as a string.
	 * @param p
	 *            Destination port.
	 */
	public UdpSender(final SenderListener listener, String adr, int p) {
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
	public UdpSender(final SenderListener listener, String adr, int p,
			String localadr) {
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
			if (this.localAddress != null) {
				this.socket = new DatagramSocket(0, InetAddress.getByName(this.localAddress));
			} else {
				this.socket = new DatagramSocket(0,
						InetAddress.getByName(System.getProperty("net.host",
								"0.0.0.0")));
			}
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (SocketException e) {
			LOG.error("SocketException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket.", e);
		}

		try {
			this.ipAddress = InetAddress.getByName(this.destination);
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket.", e);
		}
	}

	/**
	 * Method for disconnecting from the receiver.
	 */
	public void disconnect() {
		try {
			this.socket.disconnect();
			this.socket.close();
		} catch (Exception e) {
			LOG.error("Exception during disconnect: ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gateway.interfaces.Sender#send(java.lang.Byte[])
	 */
	public void send(byte[] data) {
		try {
			this.socket.send(new DatagramPacket(data, data.length,
					this.ipAddress, port));
			LOG.debug("Sending: " + data.length + " to port "
					+ this.port);
			this.listener.onSent(this, true);
		} catch (IOException e) {
			LOG.error("Could not send data.", e);
			this.listener.onSent(this, false);
		} catch (Exception e) {
			LOG.error("Could not send data.", e);
			this.listener.onSent(this, false);
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
	 * @param localAddress the localAddress to set
	 */
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
}