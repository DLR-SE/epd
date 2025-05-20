package de.emir.epd.nmeasensor.receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import de.emir.service.connection.interfaces.ReceiverListener;
import org.apache.logging.log4j.Logger;

import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * TCP NMEA sensor
 */
//@ThreadSafe
public class TcpReceiverHandle implements IConnection {

	/** Logger. */
	private static Logger LOG = ULog.getLogger(TcpReceiverHandle.class);

    private static final int TCP_READ_TIMEOUT = 60000; // 1 min

    private volatile long reconnectInterval = 5000; // Default 5 sec
    private volatile String hostname;
    private volatile int port;
    private volatile OutputStream outputStream;

    private volatile Socket clientSocket = new Socket();

	private boolean terminated;

	private boolean stopped;

	private ReceiverListener listener;
	/** Instance of the receiver. */
	private IConnection instance = this;
	private Integer packetsize;

	private String local;

	private Thread thread;
    
    public enum Status {
        CONNECTED,
		DISCONNECTED
    };

    public TcpReceiverHandle() {
    }

    /**
	 * 
	 * @param listener
	 * @param port
	 * @param packetsize
	 */
	public TcpReceiverHandle(final ReceiverListener listener, String host, Integer port, Integer packetsize) {
		this.listener = listener;
		this.hostname = host;
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
	public TcpReceiverHandle(final ReceiverListener listener, String host, Integer port, Integer packetsize, String localadr) {
		this.listener = listener;
		this.hostname = host;
		this.port = port;
		this.packetsize = packetsize;
		this.local = localadr;
	}

    private void connect() throws IOException {
        try {
            clientSocket = new Socket();
            InetSocketAddress address = new InetSocketAddress(hostname, port);
            clientSocket.connect(address);
            clientSocket.setKeepAlive(true);
            clientSocket.setSoTimeout(TCP_READ_TIMEOUT);
            outputStream = clientSocket.getOutputStream();
            LOG.trace("NMEA source connected " + hostname + ":" + port);
        } catch (UnknownHostException e) {
            LOG.error("Unknown host: " + hostname + ": " + e.getMessage());
            throw e;
        } catch (IOException e) {
            LOG.trace("Could not connect to NMEA source: " + hostname + ": " + e.getMessage());
            throw e;
        }
    }

    private void disconnect() {
        if (clientSocket != null && getStatus() == Status.CONNECTED) {
            try {
                LOG.trace("Disconnecting source " + hostname + ":" + port);
                clientSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public Status getStatus() {
        return clientSocket.isConnected() ? Status.CONNECTED : Status.DISCONNECTED;
    }

    public long getReconnectInterval() {
        return reconnectInterval;
    }

    public void setReconnectInterval(long reconnectInterval) {
        this.reconnectInterval = reconnectInterval;
    }

	@Override
	public void receive() {
		thread = new Thread("NMEA-Receiver-Thread") {
			@Override
			public void run() {
				while (!TcpReceiverHandle.this.isStopped()) {
					try {

						disconnect();
						connect();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()));
						String line;
						
			
						
						while (!isStopped() && (line = reader.readLine()) != null) {

							try {
								listener.onReceived(instance, line.getBytes());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
						}
					} catch (IOException e) {
						LOG.trace("TCP NMEA sensor failed: " + e.getMessage() + " retry in " + reconnectInterval / 1000
								+ " seconds");
						try {
							Thread.sleep(reconnectInterval);
						} catch (InterruptedException intE) {
						}
					}
				}

				// Disconnect and flag that the sensor has terminated
				TcpReceiverHandle.this.disconnect();
				TcpReceiverHandle.this.flagTerminated();
				LOG.warn("TCP NMEA sensor terminated");
			}
		};
		thread.start();
	}

	@Override
	public ReceiverListener getListener() {
		return this.getListener();
	}

	@Override
	public void stopReceiving() {
		stop();
	}

	@Override
	public boolean getState() {
		return this.getStatus().equals(Status.CONNECTED);
	}
	
	/**
     * Returns if {@linkplain #stop()} has been called to request that the sensor stops.<br>
     * The sensor will not have completed until {@linkplain #hasTerminated()} returns true
     * 
     * @return if {@linkplain #stop()} has been called to request that the sensor stops
     */
    public synchronized boolean isStopped() {
        return stopped;
    }

    /**
     * Call this method to stop the sensor.<br>
     * The sensor will not have completed until {@linkplain #hasTerminated()} returns true
     */
    public synchronized void stop() {
        this.stopped = true;
    }

    /**
     * Returns if the sensor has terminated
     * 
     * @return if the sensor has terminated
     */
    public synchronized boolean hasTerminated() {
        return terminated;
    }

    /**
     * Used internally to flag that the sensor has terminated
     */
    protected void flagTerminated() {
        this.terminated = true;
    }

	@Override
	public void send(byte[] data) {
		throw new UnsupportedOperationException("Sending not yet implemented in class TcpReceiver.");
	}

}
