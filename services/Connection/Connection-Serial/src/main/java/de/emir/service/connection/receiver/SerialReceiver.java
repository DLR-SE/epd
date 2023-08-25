/**
 * 
 */
package de.emir.service.connection.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.emir.service.connection.interfaces.Receiver;
import de.emir.service.connection.interfaces.ReceiverListener;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class SerialReceiver implements Receiver {
	/** Receiver listener. */
	private ReceiverListener listener;
	/** Value for receive loop. */
	private volatile boolean run = true;
	/** Port to listen to. */
	private Integer port;
	/** Instance of the receiver. */
	private Receiver instance = this;
	/** Size of received packages. */
	private Integer speed;
	/** Handle to thread. */
	private Thread thread;
    /** Is close expected? */
    private boolean isCloseExpected = false;
    /** Log handler. */
	static final Logger LOG = LoggerFactory.getLogger(SerialReceiver.class);
	/** Local address. */
	private String address = "COM1";
    /** Selected serial port. */
	private SerialPort serialPort;
	/** Max Packetsize. */
	public static final int MAX_PACKETSIZE = 65535;
	/** The line break. */
	private String lineBreak = System.lineSeparator();
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param speed
	 * @param localadr
	 */
	public SerialReceiver(final ReceiverListener listener, Integer port, Integer speed, String localadr) {
		this.listener = listener;
		this.port = port;
		this.speed = speed;
		this.address = localadr;
        for (String portName : SerialPortList.getPortNames()) {
			LOG.info("Available SerialPort: " + portName);
			if (portName.equals(localadr)) {
				this.serialPort = new SerialPort(localadr);
			}
		}
	}
	
	public SerialReceiver(final ReceiverListener listener, String port, Integer speed, Integer packetsize) {
		this.listener = listener;
		this.speed = speed;
		this.address = port;
        for (String portName : SerialPortList.getPortNames()) {
			LOG.info("Available SerialPort: " + portName);
			if (portName.equals(port)) {
				this.serialPort = new SerialPort(port);
			}
		}
	}

	/* (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
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
		thread = new Thread() {
			private StringBuffer buffer = new StringBuffer();
			@Override
			public void run() {
				try {
					while (run) {
						byte[] receiveData = serialPort.readBytes();
						if (receiveData != null && receiveData.length > 0) {
							for (int i = 0; i < receiveData.length; i++) {
								buffer.append((char) receiveData[i]);
		                        if (buffer.toString().endsWith(SerialReceiver.this.getLineBreak()) || buffer.length() > MAX_PACKETSIZE) {
		                        	listener.onReceived(instance, buffer.toString().getBytes());
		                            buffer = new StringBuffer();
		                        }
		                    }
						}
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {
					LOG.info("Receiver thread interrupted on port {}: ", serialPort, e);
				} catch (Exception e) {
					LOG.error("Exception in receiver on port {}: ", serialPort, e);
				} finally {
                    isCloseExpected = false;
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
            try {
                isCloseExpected = true;

                this.thread.interrupt();
//                this.thread.join();
            } catch (Exception e) {
                LOG.error("Error stopping receiver: ", e);
            }
        }
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
	
	public String getLineBreak() {
		return lineBreak;
	}

	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}
}
