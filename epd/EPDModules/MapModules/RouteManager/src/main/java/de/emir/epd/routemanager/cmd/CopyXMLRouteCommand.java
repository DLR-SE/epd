package de.emir.epd.routemanager.cmd;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.OutputStream;

import org.apache.logging.log4j.Logger;

import de.emir.epd.routemanager.impl.RouteManager;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

/**
 * The CopyXMLRouteCommand serializes the selected route object using the
 * XMLSerializer and copies the string to the system clipboard.
 *
 */
public class CopyXMLRouteCommand extends AbstractSelectedSingleRouteCommand {
	private static final Logger LOG = ULog.getLogger(RouteManager.class);

	public CopyXMLRouteCommand() {
	}

	/**
	 * Runs import command. Is executed when the according button is pressed.
	 * 
	 * Serializes in RouteListView selected route as an XML string and copies this string to the clipboard.
	 */
	@Override
	public void execute() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.trace("Running in Headless-Mode, no clipboard available!");
			return;
		}

		// get selected route
		Route selectedRoute = getSelectedRoute(); // if multiple routes are selected only the first one will be returned from getSelectedRoute()
		if (selectedRoute == null) {
			LOG.trace("No route selected!");
			return;
		}

		// prepare outputstream
		OutputStream output = new OutputStream() {
			private final StringBuilder stringBuilder = new StringBuilder();

			@Override
			public void write(int nByte){
				stringBuilder.append((char) nByte);
			}

			@Override
			public String toString() {
				return stringBuilder.toString();
			}
		};

		// Serialize selected route object
		XMLSerializer serializer = new XMLSerializer();
		serializer.serialize(selectedRoute, output);
		String representation = output.toString();

		try {
			// Copy xml string to clipboard
			StringSelection selection = new StringSelection(representation);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		} catch (IllegalStateException e) {
			LOG.debug("Clipboard can't be opened! Probably another program uses it currently.");
			// sometimes clipboard can't be opened, if another program uses it right now
			return;
		}
	}
}
