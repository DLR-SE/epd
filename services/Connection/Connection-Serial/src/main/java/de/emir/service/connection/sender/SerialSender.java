package de.emir.service.connection.sender;

import java.io.IOException;
import java.io.OutputStream;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import de.emir.service.connection.interfaces.Sender;
import de.emir.service.connection.interfaces.SenderListener;

/**
 * This is the sender to write data to a serial port.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public class SerialSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LogManager.getLogger(SerialSender.class);
	/** Sender listener. */
	private SenderListener listener;
	/** Selected serial port. */
	private SerialPort serialPort;
	/** The serial port address. (e.g. /dev/ttyS0) */
	private String address;
	/** The baudrate. */
	private int speed;
	/** The line break. */
	private String lineBreak = System.lineSeparator();
	
	/**
	 * Constructor for serial writer.
	 * 
	 * @param adr
	 *            the serial port name as a string
	 * @param speed
	 *            baudrate
	 */
	public SerialSender(final SenderListener listener, String adr, int speed) {
		for (String portName : SerialPortList.getPortNames()) {
			LOG.info("Available SerialPort: " + portName);
			if (portName.equals(adr)) {
				this.serialPort = new SerialPort(adr);
			}
		}
		this.listener = listener;
		this.address = adr;
		this.speed = speed;
	}

	/**
	 * Method for connecting with the serial port.
	 */
	public void connect() {
		try {
	        serialPort.openPort();
	        LOG.info("Port Opened: " + serialPort.getPortName());
	        serialPort.setParams(this.speed, 
	                             SerialPort.DATABITS_8,
	                             SerialPort.STOPBITS_1,
	                             SerialPort.PARITY_NONE);
	        LOG.info("Parameters Set, baudrate: " + this.speed);
	    }
	    catch (SerialPortException e) {
	    	LOG.error("SerialPortException: ", e);
	    }
	}
	
	/**
	 * Method for disconnecting from the receiver.
	 */
	public void disconnect() {
		try {
			serialPort.closePort();
			LOG.info("Closed Serial Port");
		} catch (Exception e) {
			LOG.error("Exception: ", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see gateway.interfaces.Sender#send(java.lang.Byte[])
	 */
	@Override
	public void send(byte[] data) {
		try {
			serialPort.writeBytes(normalize(data));
		} catch (SerialPortException e) {
			LOG.error("SerialPortException: ", e);
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
	
	public String normalize(String message) {
		return message.trim() + lineBreak;
	}
	
	public byte[] normalize(byte[] message) {
		String result = new String(message);
		return normalize(result).getBytes();
	}
	
	/**
	 * Get the listener.
	 * @return the listener
	 */
	public SenderListener getListener() {
		return this.listener;
	}

	public String getLineBreak() {
		return lineBreak;
	}

	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}
}