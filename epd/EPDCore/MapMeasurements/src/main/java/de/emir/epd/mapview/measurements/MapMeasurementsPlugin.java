package de.emir.epd.mapview.measurements;

import de.emir.epd.mapview.cmds.SetToolActiveStateCommand;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.measurements.settings.MeasurementsSettingsPage;
import de.emir.epd.mapview.measurements.tools.AngleTool;
import de.emir.epd.mapview.measurements.tools.DistanceTool;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.settings.ep.SettingsPageExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;

public class MapMeasurementsPlugin extends AbstractUIPlugin {

	@Override
	public void addExtensions() {

		//Settingspage Extensions
		SettingsPageExtensionPoint sep = ExtensionPointManager.getExtensionPoint(SettingsPageExtensionPoint.class);
		//register settingspage
		sep.page(MMBasics.MEASUREMENT_SETTINGS_PAGE_ID, MeasurementsSettingsPage.class)
				.label("Map Measurements");

		// Map View Tools
		MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);
		mvEP.tool(MMBasics.MAP_VIEW_TOOL_DISTANCE_MEASURE, DistanceTool.class);
		mvEP.tool(MMBasics.MAP_VIEW_TOOL_ANGLE_MEASURE, AngleTool.class);
		// Commands
		CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
		ICommandDescriptor mdtc = cmdEP.command(
				"measureDistanceToolCommand",
				"Activate Measure Distance Tool",
				new SetToolActiveStateCommand(MMBasics.MAP_VIEW_TOOL_DISTANCE_MEASURE)
		);
		ICommandDescriptor matc = cmdEP.command(
				"measureAngleToolCommand",
				"Activate Measure Angle Tool",
				new SetToolActiveStateCommand(MMBasics.MAP_VIEW_TOOL_ANGLE_MEASURE)
		);
		
		
		// Menu Entries
		MenuExtensionPoint mEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID)
				.separator("measureToolsSeparator")
				.after("lockViewOnCurrentSelection");
		mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID)
				.menuItem("measureDistanceToolItem", mdtc)
				.after("measureToolsSeparator")
				.icon("icons/emiricons/32/straighten.png");
		mEP.menuContribution(MVBasic.MAP_VIEW_TOOLBAR_ID)
				.menuItem("measureAngleToolItem", matc)
				.after("measureToolsSeparator")
				.icon("icons/emiricons/32/square_foot.png");
	}
	
	
}
