/**
 * 
 */
package de.emir.epd.routemanager.cmd;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.Logger;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.cmd.popup.ImportRouteClipboardCmdWizard;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

/**
 * ImportRouteClipboardCommand loads clipboard route object and runs wizard to select import target for route.
 * Main method is execute.
 * 1. Imports the clipboard object (-> method getRouteFromClipboard)
 * 2. Runs wizard and initializes it (-> method runImportRouteWizard)
 */
public class ImportRouteClipboardCommand extends AbstractCommand {
	private static final Logger LOG = ULog.getLogger(ImportRouteClipboardCommand.class);
	private final IRouteManager mRouteManager;
	private String mClipboardText = "";
	private Route mClipboardRoute = null;

	public ImportRouteClipboardCommand(IRouteManager rm) {
		mRouteManager = rm;
	}

	@Override
	public void execute() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.trace("Running in Headless-Mode, no clipboard available!");
			return;
		}
		Route clipboardRoute = getRouteFromClipboard();
		if (clipboardRoute == null){
			LOG.error("Could not get route from clipboard!");
			return;
		}
		runImportRouteWizard();
	}
	
	/**
	 * Imports xml string serialized route object from clipboard
	 * @return Route object (loaded from clipboard)
	 */
	private Route getRouteFromClipboard() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String clipboardData;
		UObject clipboardObj = null;
		try {
			clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
			mClipboardText = clipboardData;
			ByteArrayInputStream clipboardDataStream = new ByteArrayInputStream(clipboardData.getBytes(StandardCharsets.UTF_8));
			XMLSerializer serializer = new XMLSerializer();
			clipboardObj = serializer.deserialize(clipboardDataStream);
		} catch (UnsupportedFlavorException | IOException e) {
			LOG.error("Could not deserialize content of clipboard");
			return null;
		}

		if (clipboardObj == null) {
			LOG.error("Content of clipboard not permitted");
			return null;
		}
		Route newRoute = null;
		try {
			newRoute = (Route) clipboardObj;
		} catch (ClassCastException e) {
			LOG.error("Can't import route from clipboard. Cast to route not working.");
			return null;
		}
		newRoute.setName("Copy_" + newRoute.getName());
		mClipboardRoute = newRoute;
		return newRoute;
	}
	
	/**
	 * Runs wizard to select route import target
	 */
	private void runImportRouteWizard() {
		List<IRouteAccessModel> routeAMs = mRouteManager.getRouteModels();
		ImportRouteClipboardCmdWizard wizard = new ImportRouteClipboardCmdWizard(
				PlatformUtil.getWindowManager().getActiveFrame(), routeAMs, mClipboardRoute, mClipboardText);
		wizard.initialize();
	}
}
