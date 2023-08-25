package de.emir.epd.nmeasensor;

import de.emir.epd.alert.manager.Alert;
import de.emir.epd.alert.manager.AlertManager;
import de.emir.epd.alert.manager.AlertState;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.settings.NMEASensorSettingsPage;
import de.emir.epd.nmeasensor.settings.SensorsInfoPage;
import de.emir.rcp.settings.ep.ISettingsPageDescriptor;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class NMEASensorPlugin extends AbstractUIPlugin {
	private static NMEASensors SENSORS;

	@Override
	public void initializePlugin() {
		super.initializePlugin();
		de.emir.model.application.track.TrackPackage.init();
	}
	
	@Override
	public void postWindowOpen() {
		SENSORS = new NMEASensors();

		//yes this looks really bad, but the current time needs to be final/effectively final :D
		Long[] currentTime = {System.currentTimeMillis()};

		//this thread just checks if ais messages are received in the last 10 seconds
		//if no ais messages are received, set the error alert state
		Thread thread = new Thread(() -> {
			while (true){
				synchronized (currentTime){
				    long t = System.currentTimeMillis();
					if (t - currentTime[0] > 10000){
						SwingUtilities.invokeLater(() -> AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.ERROR));
					}else {
						SwingUtilities.invokeLater(() -> AlertManager.setState(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, AlertState.OK));
					}
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		thread.setName("AisAlertManagerThread");
		thread.start();

		//TODO filter out ownship ais messages
		NMEAOutput.subscribeSentences(nmeaSensorSentencePair -> {
			synchronized (currentTime){
				currentTime[0] = System.currentTimeMillis();
			}
		});
	}
	
	public static NMEASensors getNMEASensors() {
		return SENSORS;
	}

	@Override
	public void registerExtensionPoints() {
	}

	@Override
	public void addExtensions() {
		
		ResourceManager rmgr = ResourceManager.get(getClass());
		
		// Settings EP
		SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
		ISettingsPageDescriptor sensorPage = spEP.page(NMEASensorIds.SENSORS_INFO_PAGE_ID, SensorsInfoPage.class)
						.label("Sensors").icon("icons/emiricons/32/settings_input_component.png");
		sensorPage.page(NMEASensorIds.NMEA_SENSOR_SETTINGS_PAGE_ID, NMEASensorSettingsPage.class)
				.label("NMEA0183")
				.icon("icons/emiricons/32/settings_input_antenna.png", rmgr);

		Alert ownship = new Alert(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID,"Ownship", AlertState.UNKNOWN);
		Alert ais = new Alert(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID,"AIS", AlertState.UNKNOWN);

		AlertManager.registerAlert(ownship);
		AlertManager.registerAlert(ais);

		AlertManager.setVisible(NMEASensorIds.NMEA_SENSOR_OWNSHIP_ALERT_ID, true);
		AlertManager.setVisible(NMEASensorIds.NMEA_SENSOR_AIS_ALERT_ID, true);

	}

}
