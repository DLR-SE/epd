/**
 * 
 */
package de.emir.epd.nmeasensor.receiver;

import org.apache.logging.log4j.Logger;

import de.emir.model.universal.io.IInputStream;
import de.emir.model.universal.io.IOutputStream;
import de.emir.model.universal.io.impl.OutputStreamImpl;

import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.service.connection.interfaces.ReceiverListener;
import de.emir.service.connection.receiver.SerialReceiver;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class SerialReceiverHandle implements IConnection {
	/** Receiver listener. */
	private de.emir.service.connection.interfaces.ReceiverListener listener;
	/** Value for receive loop. */
	private volatile boolean run = true;
	/** Size of received packages. */
	private Integer speed;
    /** Is close expected? */
    private boolean isCloseExpected = false;
    /** Logger. */
	private static Logger LOG = ULog.getLogger(SerialReceiverHandle.class);
	/** Local address. */
	private String address = "COM1";
    /** Selected serial port. */
//	private SerialTransportHandler serialHandler;
    private de.emir.service.connection.receiver.SerialReceiver serialHandler;
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param speed
	 * @param packetsize
	 */
	public SerialReceiverHandle(final ReceiverListener listener, String port, Integer speed, Integer packetsize) {
		this.listener = listener;
		this.speed = speed;
		this.address = port;
        for (String portName : SerialPortList.getPortNames()) {
			LOG.debug("Available SerialPort: " + portName);
		}
	}

	/* (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
		serialHandler = new de.emir.service.connection.receiver.SerialReceiver(listener, address, speed, 65535);
        serialHandler.receive();
        /*
		serialHandler.setListen(true);
		serialHandler.setBaudRate(speed);
		serialHandler.setSerialPort(address);
		serialHandler.setRespondHandler(new RespondHandler() {
			@Override
			public boolean handleRequest(IInputStream incoming, IOutputStream outgoing) {
				if (incoming != null && incoming.size() != 0) {
					listener.onReceived(SerialReceiver.this, incoming.getByteBuffer().array());
				}
				return false;
			}
		});
		serialHandler.startListening();*/
	}

	/*
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#stopReceiving()
	 */
	@Override
	public synchronized void stopReceiving() {
		serialHandler.stopReceiving();
	}

	/*
	 * (non-Javadoc)
	 * @see de.emir.commons.connection.interfaces.Receiver#getListener()
	 */
	@Override
	public ReceiverListener getListener() {
		return this.listener;
	}
	
	@Override
	public void send(byte[] data) {
		//serialHandler.send(new OutputStreamImpl(data));
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
	public String getPort() {
		return this.address;
	}
}
