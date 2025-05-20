package de.emir.service.connection.sender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.vlkan.rfos.RotatingFileOutputStream;
import com.vlkan.rfos.RotationConfig;

import de.emir.service.connection.interfaces.Listener;
import de.emir.service.connection.interfaces.Sender;
import de.emir.service.connection.interfaces.SenderListener;

/**
 * This is the sender to write data rotating files.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 * 
 */
public class FileRotationSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LogManager.getLogger(FileRotationSender.class);
	private SenderListener listener;
	private String destination;
	private RotatingFileOutputStream stream;
	private boolean isAddTS = false;
	private boolean compress = true;
	private static final TimeZone TZ = TimeZone.getTimeZone("GMT0");
	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
	
	public FileRotationSender(final SenderListener listener, String adr, boolean addTS, boolean compress) {
		this.listener = listener;
		this.destination = adr;
		this.isAddTS  = addTS;
		this.compress = compress;
		DF.setTimeZone(TZ);
	}

	@Override
	public void send(byte[] data) {
//		String[] lines = new String(data).split("\\s+");
		String[] lines = new String(data).split("\\r\\n|\\n|\\r");
		for (String line : lines) {
			if (isAddTS) {
				line = DF.format(new Date()) + " " + line.trim();
			}
			try {
				stream.write((line.trim() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				LOG.error("Could not write to file.", e);
			}
			
		}
	}

	@Override
	public Listener getListener() {
		return this.listener;
	}

	@Override
	public void connect() {
		try {
			if (this.compress) {
				RotationConfig config = RotationConfig.builder().file(this.destination)
						.filePattern(this.destination + "-%d{yyyy-MM-dd-HH}.nmea.gz").compress(true)
						.policy(HourlyRotationPolicy.getInstance()).build();

				this.stream = new RotatingFileOutputStream(config);
			} else {
				RotationConfig config = RotationConfig.builder().file(this.destination)
						.filePattern(this.destination + "-%d{yyyy-MM-dd-HH}.nmea").compress(false)
						.policy(HourlyRotationPolicy.getInstance()).build();

				this.stream = new RotatingFileOutputStream(config);
			}
		} catch (Exception e) {
			LOG.error("Could not open file for writing.", e);
		}
	}

	@Override
	public void disconnect() {
		try {
			stream.flush();
			stream.close();
		} catch (Exception e) {
			LOG.error("Error closing file.", e);
		}
	}
}
