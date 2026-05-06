package de.emir.epd.nmeasensor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.nmeasensor.data.ReceiverType;
import de.emir.epd.nmeasensor.data.SentenceType;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.receiver.FileReceiverHandle;
import de.emir.epd.nmeasensor.receiver.IConnection;
import de.emir.epd.nmeasensor.receiver.SerialReceiverHandle;
import de.emir.epd.nmeasensor.receiver.TcpReceiverHandle;
import de.emir.epd.nmeasensor.receiver.UdpReceiverHandle;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.service.connection.interfaces.Receiver;
import de.emir.service.connection.interfaces.ReceiverListener;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import net.sf.marineapi.ais.event.AbstractAISMessageListener;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.nmea.event.SentenceEvent;
import net.sf.marineapi.nmea.event.SentenceListener;
import net.sf.marineapi.nmea.io.ExceptionListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.SentenceId;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class NMEASensor implements ReceiverListener, SentenceListener {
	/** Reference to the connection for this sensor. **/
	private volatile IConnection nmeaReceiver;
	/** The property storing all settings for this sensor. **/
	private IProperty<?> nmeaSensor;
	/** Reference to model storing all AIS Targets. **/
	private Environment aisTargets;
	/** Listener for changes in settings for this sensor. **/
	private PropListener propListener;
	/** List of allowed sentence types in this sensor. **/
    private List<SentenceType> allowList = new ArrayList<>();
    /** Property context for the stored settings. **/
    private PropertyContext ctx = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
    /** Actual NMEA0183 sentence reader. **/
	private volatile SentenceReader sentenceReader;
	/** InputStream attached to SentenceReader. **/
    private volatile PipedInputStream is;
    /** OutputStream attached to IConnection. **/
    private volatile PipedOutputStream os;
    /** PipeWriter to shovel data from IConnection to SentenceReader through InputStream and OutputStream. **/
    private WriteToPipe writer;
    /** Remember if settings have change and this sensor should be restartet. */
	private boolean needsRestart = true;
	
	/**
	 * This contains the NMEA sensor with receiver and parser.
	 */
	public NMEASensor(IProperty<?> sourceProperty) {
		Object o = PlatformUtil.getModelManager().getModelProvider().getModel();

		if (o instanceof EPDModel) {
			EPDModel model = (EPDModel) o;
			this.aisTargets = model.getAisTargetSet();
		} else {
			// TODO if there is no EPDModel, aisTargets cannot be created!
			this.aisTargets = new EnvironmentImpl();
		}

		this.nmeaSensor = sourceProperty;
		this.propListener = new PropListener();

		receive();
	}
    
	/**
	 * Find the path for this sensors settings in property store.
	 * 
	 * @return the path for this sensors settings in the property store by its name
	 */
    public String getNamePath() {
        return NMEASensorIds.NMEA_SENSOR_PROP + "." + nmeaSensor.getName();
    }
    
    /**
     * Get the connection type of this sensor.
     * 
     * @return the given connection type. Default is ReceiverType.UDP
     */
    public ReceiverType getType() {
        IProperty<String> typeProp = ctx.getProperty(
                getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_TYPE, ReceiverType.UDP.name());
        return ReceiverType.valueOf(typeProp.getValue());
    }
    
    /**
     * The state of this sensor.
     * 
     * @return <CODE>True</Code> if this sensor is activated in its settings
     */
    public boolean isActive() {
        IProperty<Boolean> activeProp = ctx.getProperty(
                getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE, false);
        return activeProp.getValue();
    }
    
    /**
     * Generic method to get an attribute from the property store.
     * 
     * @param attributeName the attribute to get from the property store
     * @return a settings attribute for this sensor
     */
    public Object getAttribute(String attributeName) {
        IProperty prop = ctx.getProperty(getNamePath() + "." + attributeName, null);
        return prop.getValue();
    }
    
    /**
     * Generic method to get an attribute from the property store but with default value.
     * @param attributeName the attribute to get from the property store
     * @param def the default value
     * @return a settings attribute for this sensor or the default value if no attribute is found in the property store
     */
    public Object getAttributeOrDefault(String attributeName, Object def) {
        IProperty prop = ctx.getProperty(getNamePath() + "." + attributeName, def);
        return prop.getValue();
    }

    /**
     * The listener to react to settings changes in the property store.
     */
	class PropListener implements PropertyChangeListener {
		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			ULog.info("NMEASensor Property changed.", evt);
			if (!evt.getNewValue().equals(evt.getOldValue())) {
				ULog.info("Set to restart sensor {}", getNamePath());
				needsRestart = true;
			}
            // Only inform about active receivers and stop them for changes
            if (nmeaReceiver != null && nmeaReceiver.getState() && !isActive()) {
                ULog.debug("Changes in Receiver {} {}:{}", getNamePath(), evt.getPropertyName(), evt.getNewValue());
                try {
                    ULog.info("Stop {} receiver {}", getType().name(), nmeaSensor.getName());
                    nmeaReceiver.stopReceiving();
                } catch (Exception e) {
                    ULog.error("Could not stop receiver " + getNamePath(), e);
                }
            }
			receive();
		}
	}
	
	/**
	 * This method starts the receiver for this sensor.
	 */
	public void receive() {
		// Check if this is already running and everything is in order.
		if (nmeaReceiver != null && nmeaReceiver.getState() == isActive()) {
			// Do nothing if this is already connected. Call stopReceiving beforehand if you want this to change. 
			ULog.debug("State of {} is {}", nmeaSensor.getName(), nmeaReceiver.getState());
			return;
		}
		this.needsRestart = false;
		this.nmeaSensor.addPropertyChangeListener(propListener);
		ULog.debug("New sensor property: {}", this.nmeaSensor);
		String[] sentences = ((String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SENTENCES, "")).split(",");
		Set<Integer> aisSentences = new HashSet<>(); 
		allowList.clear();
        for (String sentence : sentences) {
            if (sentence.isEmpty() || sentence.isBlank()) continue;
            try {
                allowList.add(SentenceType.valueOf(sentence));
                aisSentences.add(Integer.valueOf(SentenceType.valueOf(sentence).getDetail()));
            } catch (IllegalArgumentException e) {
                ULog.warn("No SenenceType for name \"{}\", ignoring entry.", sentence);
                continue;
            }
        }
        
		try {
			is = new PipedInputStream();
			os = new PipedOutputStream(is);
			sentenceReader = new SentenceReader(is);
			writer = new WriteToPipe(os);
			
			sentenceReader.addSentenceListener(new AbstractAISMessageListener<AISMessage>() {
				@Override
				public void onMessage(AISMessage msg) {
					if (aisSentences.contains(Integer.valueOf(msg.getMessageType()))) {
						NMEAOutput.notifyAis(NMEASensor.this, msg);
					}
				}});
			for (SentenceType t : allowList) {
				if (!(t.getType().equals(SentenceId.VDM) || t.getType().equals(SentenceId.VDO))) {
					sentenceReader.addSentenceListener(this, t.getType());
					// AIS is handeled through the AbstractAISMessageListener
				}
			}
			sentenceReader.setExceptionListener(new ExceptionListener() {
				@Override
				public void onException(Exception e) {
					ULog.error("Exception in sentenceReader. ", e);
				}});
			sentenceReader.start();
			EPDModelUtils.clear(this.aisTargets);
			
			switch (getType()) {
			case UDP:
				String localAdr = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_INTERFACE, "0.0.0.0");
				int udpport = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 7003);
				int packetsize = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535);
				nmeaReceiver = new UdpReceiverHandle(this, udpport, packetsize, localAdr);
				ULog.debug("UDP Receiver {}:{} {}", localAdr, udpport, packetsize);
				break;
			case TCP:
				String hostName = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_HOST, "127.0.0.1");
				int tcpport = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PORT, 16100);
				nmeaReceiver = new TcpReceiverHandle(this, hostName, tcpport, Constants.PACKETSIZE);
				ULog.debug("TCP Receiver {}:{}", hostName, tcpport);
				break;
			case Serial:
				String serialPort = (String) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_SERIALPORT, "COM1");
				int baudrate = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_BAUDRATE, 9600);
				int maxPacketsize = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_PACKETSIZE, 65535);
				nmeaReceiver = new SerialReceiverHandle(this, serialPort, baudrate, maxPacketsize);
				ULog.debug("Serial Receiver {} {} {}", serialPort, baudrate, maxPacketsize);
				break;
			case File:
				String filename = (String) getAttributeOrDefault(
                        NMEASensorIds.NMEA_SENSOR_PROP_FILENAME, "nmea.txt");
				int delay = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_DELAY, 1000);
				int repeat = (int) getAttributeOrDefault(NMEASensorIds.NMEA_SENSOR_PROP_REPEAT, -1);
				nmeaReceiver = new FileReceiverHandle(this, filename, delay, repeat);
				ULog.debug("File Receiver {} {} {}", filename, delay, repeat);
				break;
			}
            
			if (isActive()) {
                // UDP can only send or recieve
				if (getType().equals(ReceiverType.UDP) && (boolean) getAttributeOrDefault(
                        NMEASensorIds.NMEA_SENSOR_PROP_OUTPUT, false)) {
					return;
				}
				ULog.info("Start {} receiver {}", getType().name(), nmeaSensor.getName());
				ULog.debug("Receiver property name {}", getNamePath());
				nmeaReceiver.receive();
				writer.start();
			}
		} catch (Exception e) {
			ULog.error("Could not start NMEA receiver {}.", getNamePath(), e);
		}
	}
	
	/**
	 * Stop and remove this sensors receiver.
	 */
	public void remove() {
		if (nmeaReceiver != null && nmeaReceiver.getState()) nmeaReceiver.stopReceiving();
		if (nmeaSensor != null) nmeaSensor.removePropertyChangeListener(this.propListener);
		if (writer != null) writer.stopWriter();
	}
	
	/**
	 * Notify the observers about a new received NMEA sentence.
	 * 
	 * @param sentence the received NMEA sentence
	 */
	private void notify(Sentence sentence) {
		NMEAOutput.notify(this, sentence);
	}
    
	/**
	 * Gets triggered when this sensors connection receives data.
	 */
    @Override
    public void onReceived(Receiver source, byte[] data) {
        if (source.equals(nmeaReceiver)) {
			try {
				if (data != null && data.length > 0) {
                    byte[] msg = new String(data).concat("\r\n").getBytes();
                    writer.write(msg);
				}
			} catch (Exception e) {
				ULog.error("Exception while handling message. ", e);
			}
		}
    }

    /**
     * Get this sensors receiver.
     * 
     * @return this sensors receiver
     */
	public IConnection getNmeaReceiver() {
		return nmeaReceiver;
	}

	@Override
	public void readingPaused() {
		ULog.debug("SentenceReader: readingPaused");
	}

	@Override
	public void readingStarted() {
		ULog.debug("SentenceReader: readingStarted");
	}

	@Override
	public void readingStopped() {
		ULog.debug("SentenceReader: readingStopped");
	}

	@Override
	public void sentenceRead(SentenceEvent event) {
		if (event != null && event.getSentence() != null) {
			notify(event.getSentence());
		}
	}

	/**
	 * Helper class to allow the forwarding of byte array data from the eMIR receiver to the MarineAPI SentenceReader.
	 */
	class WriteToPipe extends Thread {
		/** The outputstream to write to (target will be the MarineAPI StreamReader). **/
		private final PipedOutputStream os;
        /** If no new messages arrive we wait this long to check the thread running state. Default is 1s */
        private int waitTimeoutMilliseconds = 1000;
        /** Semaphore like queue containing messages to be processed **/
        private final LinkedBlockingQueue<byte[]> blockingQueue = new LinkedBlockingQueue<>();
        /** State. **/
        private volatile boolean run = true;

		
		/**
		 * Constructor for this writer.
		 * 
		 * @param os the outputstream to write to
		 */
		public WriteToPipe(final PipedOutputStream os) {
			this(os, 1000);
		}

        /**
         * Constructor for this writer.
         *
         * @param os the outputstream to write to
         * @param waitTimeoutMilliseconds timeout for waiting for new messages
         */
        public WriteToPipe(final PipedOutputStream os, final int waitTimeoutMilliseconds) {
            this(os, waitTimeoutMilliseconds, null);
        }

        /**
         * Constructor for this writer.
         *
         * @param os the outputstream to write to
         * @param initialMessage the data to write
         */
        public WriteToPipe(final PipedOutputStream os, final byte[] initialMessage) {
            this(os, 1000, initialMessage);
        }


        /**
         * Constructor for this writer.
         *
         * @param os the outputstream to write to
         * @param waitTimeoutMilliseconds timeout for waiting for new messages
         * @param initialMessage the data to write
         */
        public WriteToPipe(final PipedOutputStream os, final int waitTimeoutMilliseconds, final byte[] initialMessage) {
            this.os = os;
            if (initialMessage != null){
                try {
                    this.blockingQueue.put(initialMessage);
                } catch (InterruptedException e) {
                    // it is okay to catch here since: Even if the thread gets interrupted we
                    // might only lose this message
                    ULog.error(e);
                }
            }
            this.waitTimeoutMilliseconds = waitTimeoutMilliseconds;
            this.run = true;
        }


		/**
		 * Place the data to write in this writer object. This will be written to the outputstream once the thread gets
		 * to it. (Which should be immediately.)
		 * 
		 * @param msg the data to write
		 */
		public void write(final byte[] msg) {
            try {
                // wait automatically if queue is full
                blockingQueue.put(msg);
            } catch (InterruptedException e) {
                // it is okay to catch here since: Even if the thread gets interrupted we
                // might only lose this message
                ULog.error(e);
            }
		}
		
		/**
		 * Stop this writer thread.
		 */
		public void stopWriter() {
			this.run = false;
            // wake up the thread
            this.interrupt();
		}
		
		/**
		 * Run this writer thread to continuously check for new data and write it into the outputstream.
		 */
		public void run() {
			try {
                // run until stop is requested
                while (this.run) {
                    // wait for new messages until timeout
                    byte[] msg = this.blockingQueue.poll(this.waitTimeoutMilliseconds, TimeUnit.MILLISECONDS);
                    if (msg != null){
                        // write new message
                        this.os.write(msg);
                        this.os.flush();
                    }
                }
				os.close();
			} catch (IOException e) {
				ULog.error("Exception while writing to outputstream.", e);
			} catch (InterruptedException e) {
                ULog.error("Interruption while writing to outputstream.", e);
            }
		}
	}

	public boolean needsRestart() {
		return this.needsRestart ;
	}
}
