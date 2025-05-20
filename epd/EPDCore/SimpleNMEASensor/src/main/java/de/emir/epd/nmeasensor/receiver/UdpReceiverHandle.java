/**
 * 
 */
package de.emir.epd.nmeasensor.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import de.emir.service.connection.interfaces.ReceiverListener;
import org.apache.logging.log4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class UdpReceiverHandle implements IConnection {
	/** Receiver listener. */
	private ReceiverListener listener;
	/** Value for receive loop. */
	private volatile boolean run = true;
	/** Port to listen to. */
	private Integer port;
	/** Instance of the receiver. */
	private IConnection instance = this;
	/** Size of received packages. */
	private Integer packetsize;
	/** Handle to thread. */
	private Thread thread;
    /** Handle to the socket. */
    private DatagramSocket socket;
    /** Is close expected? */
    private boolean isCloseExpected = false;
    /** Logger. */
	private static Logger LOG = ULog.getLogger(UdpReceiverHandle.class);
	/** Local address. */
	private String local = System.getProperty("net.host", "0.0.0.0");
	
	/**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 */
	public UdpReceiverHandle(final ReceiverListener listener, Integer port, Integer packetsize) {
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
	 * @param local
	 */
	public UdpReceiverHandle(final ReceiverListener listener, Integer port, Integer packetsize, String localadr) {
		this.listener = listener;
		this.port = port;
		this.packetsize = packetsize;
		this.local = localadr;
	}

	/* (non-Javadoc)
	 * @see gateway.interfaces.Receiver#receive()
	 */
	public void receive() {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new DatagramSocket(port,
							InetAddress.getByName(local));
					while (run) {
						byte[] receiveData = new byte[packetsize];
						DatagramPacket receivePacket = new DatagramPacket(
								receiveData, receiveData.length);
						socket.receive(receivePacket);
						receivePacket.setData(trimData(receivePacket.getData(),
								receivePacket.getLength()));
						listener.onReceived(instance, receivePacket.getData());
						Thread.sleep(1);
					}
					socket.close();
				} catch (SocketException e) {
                    if (isCloseExpected) {
                        LOG.debug("Socket closed");
                    } else {
                        LOG.error("SocketException in receiver on port {}: ", port, e);
                    }
				} catch (IOException e) {
					LOG.error("IOException in receiver on port {}: ", port, e);
				} catch (InterruptedException e) {
					LOG.info("Receiver thread interrupted on port {}: ", port, e);
				} catch (Exception e) {
					LOG.error("Exception in receiver on port {}: ", port, e);
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

	@Override
	public void send(byte[] data) {
//		throw new UnsupportedOperationException("Sending not yet implemented in class UdpReceiver.");
		try {
			/*if (this.localAddress != null) {
				this.socket = new DatagramSocket(0, InetAddress.getByName(this.localAddress));
			} else*/ {
				this.socket = new DatagramSocket(0,
						InetAddress.getByName(System.getProperty("net.host",
								"0.0.0.0")));
			}
			InetAddress ipAddress = InetAddress.getByName(this.local);
			this.socket.send(new DatagramPacket(data, data.length, ipAddress, port));
			this.socket.disconnect();
			this.socket.close();
		} catch (UnknownHostException e) {
			LOG.error("UnknownHostException: ", e);
		} catch (SocketException e) {
			LOG.error("SocketException: ", e);
		} catch (Exception e) {
			LOG.error("Could not connect to socket.", e);
		}
	}
}
