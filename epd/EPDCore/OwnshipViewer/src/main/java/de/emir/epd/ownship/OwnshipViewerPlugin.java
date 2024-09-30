package de.emir.epd.ownship;

import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.nmeasensor.NMEAVesselManager;
import de.emir.epd.ownship.ids.OwnshipBasics;
import de.emir.epd.ownship.settings.OwnshipViewerSettingsPage;
import de.emir.epd.ownship.settings.OwnshipViewerSettingsPage.OwnshipSource;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OwnshipViewerPlugin extends AbstractUIPlugin {
	@Override
	public void initializePlugin() {
		super.initializePlugin();
//		ServiceManager.register(new AisTargetManager());
	}
	
	@Override
	public void registerExtensionPoints() {
		super.registerExtensionPoints();
//		ExtensionPointManager.registerExtensionPoint("AisModelAdapterEP", ServiceManager.get(AisTargetManager.class).getAdapterExtensionPoint());
	}

	@Override
	public void addExtensions() {

		// AIS Target View
		ViewExtensionPoint vEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);

		vEP.view(OwnshipBasics.OWNSHIP_VIEWER_VIEW_ID, OwnshipView.class)
				.label("Ownship Info")
				.icon("icons/emiricons/32/sailing.png");

		// MapView EP

		MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);

		mvEP.layer("OwnshipLayer", OwnshipLayer.class)
				.label("Ownship")
				.icon("icons/emiricons/32/sailing.png");

		// Settings EP

		SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);

		spEP.page(OwnshipBasics.OWNSHIP_VIEWER_SETTINGS_PAGE_ID, OwnshipViewerSettingsPage.class)
				.label("Ownship")
				.icon("icons/emiricons/32/sailing.png", ResourceManager.get(getClass()));

	}
	
	class PropListener implements PropertyChangeListener {
		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			Object providerModel = PlatformUtil.getModelManager().getModelProvider().getModel();
			if(providerModel instanceof EPDModel){
				try {
					Vessel ownship = retrieveOwnship((EPDModel) providerModel, (String) evt.getNewValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void preWindowOpen() {
		PropertyContext context = PropertyStore.getContext(OwnshipBasics.OWNSHIP_VIEWER_PROP_CONTEXT);
		// If the ownship source settings or ownship MMSI was set to null by accident, revert to default values.
		IProperty prop = context.getProperty(OwnshipBasics.OWNSHIP_VIEWER_PROP_AIS_TARGET, "211724970");
		if(prop.getValue() == null) {
			context.setValue(OwnshipBasics.OWNSHIP_VIEWER_PROP_AIS_TARGET, "211724970");
		}
		IProperty sourceProp = context.getProperty(OwnshipBasics.OWNSHIP_VIEWER_PROP_OWNSHIP_SOURCE, OwnshipSource.AISTARGET.name());
		if(sourceProp.getValue() == null) {
			context.setValue(OwnshipBasics.OWNSHIP_VIEWER_PROP_OWNSHIP_SOURCE, OwnshipSource.AISTARGET.name());
		}
		prop.addPropertyChangeListener(new PropListener());
		Object providerModel = PlatformUtil.getModelManager().getModelProvider().getModel();
		if(providerModel instanceof EPDModel){
			try {
				Vessel ownship = retrieveOwnship((EPDModel) providerModel, (String) prop.getValue());
				NMEAVesselManager.registerOwnShip(ownship);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		try {
//			IAisReadAdapter mra = ServiceManager.get(AisTargetManager.class).getModelReadAdapter();
//
//			if (mra == null) {
//				return;
//			}
//
//			mra.subscribeChanged(oo -> {
//				Object obj = oo.get();
//				if (obj instanceof Environment) {
//					Environment targetSet = (Environment) obj;
//				}
//			});
//		} catch (NullPointerException e) {
////			LOG.error("No ModelReadAdapter available.", e);
//			e.printStackTrace();
//		}
	}

	private Vessel getOwnship(EPDModel model) {
		return EPDModelUtils.getOwnship(model.getEnvironment());
	}
	
	private Vessel retrieveOwnship(EPDModel model, String id) {
		return EPDModelUtils.retrieveOwnship(model.getEnvironment(), id);
	}
}
