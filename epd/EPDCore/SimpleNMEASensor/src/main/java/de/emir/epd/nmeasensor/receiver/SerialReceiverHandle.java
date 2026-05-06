/**
 * 
 */
package de.emir.epd.nmeasensor.receiver;

import org.apache.logging.log4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.service.connection.interfaces.ReceiverListener;
import jssc.SerialPortList;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class SerialReceiverHandle implements IConnection {
    /** Logger. */
    private static final Logger LOG = ULog.getLogger(SerialReceiverHandle.class);

	/** Receiver listener. */
	protected de.emir.service.connection.interfaces.ReceiverListener listener;
	/** Value for receive loop. */
	protected volatile boolean run = true;
	/** Size of received packages. */
    protected Integer speed;
	/** Local address. */
	protected String address = "COM1";
    /** Selected serial port. */
    protected de.emir.service.connection.receiver.SerialReceiver serialHandler;
	
	/**
	 * Initialize the serial receiver
	 * @param listener listener for received messages
	 * @param port serial port
	 * @param speed baud rate
	 * @param packetsize max packet size (currently unused)
	 */
	public SerialReceiverHandle(final ReceiverListener listener, String port, Integer speed, Integer packetsize) {
		this.listener = listener;
		this.speed = speed;
		this.address = port;
        for (String portName : SerialPortList.getPortNames()) {
			LOG.debug("Available SerialPort: " + portName);
		}
	}

	/** (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
		serialHandler = new de.emir.service.connection.receiver.SerialReceiver(listener, address, speed, 65535);
        serialHandler.receive();
	}

	/*
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#stopReceiving()
	 */
	@Override
	public synchronized void stopReceiving() {
		serialHandler.stopReceiving();
	}

	/**
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#getListener()
	 */
	@Override
	public ReceiverListener getListener() {
		return this.listener;
	}
	
	@Override
	public void send(byte[] data) {
        throw new UnsupportedOperationException("Sending not yet implemented in class SerialReceiverHandle.");
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
	 * Get receivers assigned port.
	 * 
	 * @return the port
	 */
	public String getPort() {
		return this.address;
	}
}
