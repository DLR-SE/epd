package de.emir.epd.ais;

import de.emir.epd.ais.cmd.impl.HideAISRouteCommand;
import de.emir.epd.ais.cmd.impl.HideAllRoutesCommand;
import de.emir.epd.ais.cmd.impl.OpenShowRouteCommand;
import de.emir.epd.ais.cmd.impl.ShowAllAisRoutesCommand;
import de.emir.epd.ais.ids.AisBasics;
import de.emir.epd.ais.manager.AisTargetManager;
import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.menu.ep.IMenuItem;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.rcp.views.ep.IViewGroup;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.slf4j.Logger;

public class AisViewerPlugin extends AbstractUIPlugin {

	private static Logger LOG = ULog.getLogger(AisViewerPlugin.class);

	@Override
	public void initializePlugin() {
		ServiceManager.register(new AisTargetManager());
	}
	
	@Override
	public void registerExtensionPoints() {
		ExtensionPointManager.registerExtensionPoint("AisModelAdapterEP", ServiceManager.get(AisTargetManager.class).getAdapterExtensionPoint());
	}

	@Override
	public void addExtensions() {

		ResourceManager rmgr = ResourceManager.get(AisViewerPlugin.class);

		// AIS Target View
		ViewExtensionPoint vEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);

		IViewGroup group = vEP.group(AisBasics.AIS_VIEWER_VIEW_GROUP_ID)
				.label("AIS")
				.icon("icons/emiricons/32/settings_input_antenna.png", rmgr);

		group.view(AisBasics.AIS_VIEWER_TARGET_VIEW_ID, AisTargetView.class)
				.label("AIS Target")
				.icon("icons/emiricons/32/settings_input_antenna.png");

		// MapView EP
		MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);

		mvEP.layer("AisLayer", AisLayer.class)
				.label("AIS")
				.icon("icons/emiricons/32/settings_input_antenna.png")
				.settingsPanel(AisLayerSettingsPanel.class);

		CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
		ICommandDescriptor hideAisRouteCmd = cmdEP.command(
				"hideAisRouteCommand", "Hide Route", new HideAISRouteCommand()
		);
		ICommandDescriptor hideAllAisRouteCmd = cmdEP.command(
				"hideAllAisRouteCommand", "Hide All Routes", new HideAllRoutesCommand()
		);
		ICommandDescriptor openShowRouteCmd = cmdEP.command(
				"openShowRouteCommand", "Show Route", new OpenShowRouteCommand()
		);
		ICommandDescriptor showAllAisRoutesCmd = cmdEP.command(
				"showAllAisRoutesCommand", "Show All Routes", new ShowAllAisRoutesCommand()
		);

		MenuExtensionPoint mEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		mEP.menuContribution(AisBasics.AIS_VIEWER_POPUP_ID)
				.menuItem("hideAisRouteCommand", hideAisRouteCmd)
				.label("Hide Route");
		mEP.menuContribution(AisBasics.AIS_VIEWER_POPUP_ID)
				.menuItem("hideAllAisRouteCommand", hideAllAisRouteCmd)
				.label("Hide All Routes");
		mEP.menuContribution(AisBasics.AIS_VIEWER_POPUP_ID)
				.menuItem("openShowRouteCommand", openShowRouteCmd)
				.label("Show Route");
		mEP.menuContribution(AisBasics.AIS_VIEWER_POPUP_ID)
				.menuItem("showAllAisRoutesCommand", showAllAisRoutesCmd)
				.label("Show All Routes");

        // Settings EP
		SettingsPageExtensionPoint spEP = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);

		spEP.page(AisBasics.AIS_VIEWER_SETTINGS_PAGE_ID, AisViewerSettingsPage.class)
				.label("AIS")
				.icon("icons/emiricons/32/settings_input_antenna.png", ResourceManager.get(getClass()));
	}

	@Override
	public void preWindowOpen() {
		try {
//			ServiceManager.get(AisTargetManager.class).getModelReadAdapter()
//					.subscribeChanged(new Consumer<Optional<Object>>() {
//						@Override
//						public void accept(Optional<Object> o) throws Exception {
//							if (o.isPresent() == false) {
//								return;
//							}
//
//							Object obj = o.get();
//							if (obj instanceof TargetSet) {
//								TargetSet targetSet = (TargetSet) obj;
//								IRouteManager.getDefaultRouteManager()
//										.rebuildModel(Arrays.asList(targetSet.getAisTargets()));
//							}
//						}
//					});
			IAisReadAdapter mra = ServiceManager.get(AisTargetManager.class).getModelReadAdapter();

			if (mra == null) {
				return;
			}

			//TODO check if this works with all plugins, with map+nmea+aisview this works just fine
//			mra.subscribeChanged(oo -> {
//				Object obj = oo.get();
//				if (obj instanceof Environment) {
//					Environment targetSet = (Environment) obj;
//					IRouteManager.getDefaultRouteManager()
//							.rebuildModel(Arrays.asList(EPDModelUtils.getAisTargets(targetSet)));
//				}
//			});
		} catch (NullPointerException e) {
			LOG.error("No ModelReadAdapter available.", e);
		}
	}
}
