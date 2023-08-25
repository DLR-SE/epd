package de.emir.service.connection.sender;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.emir.service.connection.interfaces.Listener;
import de.emir.service.connection.interfaces.Sender;
import de.emir.service.connection.interfaces.SenderListener;

public class FileSender implements Sender {
	/** Log handler. */
	static final Logger LOG = LoggerFactory.getLogger(FileSender.class);
	private SenderListener listener;
	private String destination;
	private PrintWriter printWriter;
	private boolean isAddTS = false;
	private static final TimeZone TZ = TimeZone.getTimeZone("GMT0");
	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
	
	public FileSender(final SenderListener listener, String adr, boolean addTS) {
		this.listener = listener;
		this.destination = adr;
		this.isAddTS  = addTS;
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
			printWriter.println(line.trim());
			printWriter.flush();
		}
	}

	@Override
	public Listener getListener() {
		return this.listener;
	}

	@Override
	public void connect() {
		try {
			FileWriter fileWriter = new FileWriter(this.destination, false);
			printWriter = new PrintWriter(fileWriter);
		} catch (IOException e) {
			LOG.error("Could not open file for writing.", e);
		}
	}

	@Override
	public void disconnect() {
		try {
			printWriter.close();
		} catch (Exception e) {
			LOG.error("Error closing file.", e);
		}
	}
}
