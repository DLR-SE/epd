package de.emir.rcp;

import de.emir.rcp.cmds.ShowPluginDialogCommand;
import de.emir.rcp.commands.basics.GenericBooleanPropertyCheckableCommand;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.dev.DevToolsManager;
import de.emir.rcp.dev.ids.DevToolBasics;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.menu.ep.IMenu;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class DevelopementToolsPlugin extends AbstractUIPlugin {

	
	@Override
	public void initializePlugin() {
		ServiceManager.register(new DevToolsManager());
	}
	
	@Override
	public void addExtensions() {

		MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);

		ICommandDescriptor showPluginDialogCMD = cmdEP.command(
				ShowPluginDialogCommand.ID,
				ShowPluginDialogCommand.LABEL,
				new ShowPluginDialogCommand()
		);
		ICommandDescriptor toggleShowMenuContributionsCMD = cmdEP.command(
				DevToolBasics.SHOW_CONTR_CMD_ID,
				"Toggle show menu contributions",
				new GenericBooleanPropertyCheckableCommand(Basic.DEV_PROP_CTX, Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS)
		);

		IMenu devMenu = menuEP.menuContribution(Basic.MENU_MAIN_MENU)
				.menu("devTools", "DevTools")
				.after("layout");
		devMenu.menuItem("showPluginDialog", showPluginDialogCMD)
				.label(ShowPluginDialogCommand.LABEL);
		devMenu.menuItem("showMenuContributions", toggleShowMenuContributionsCMD)
				.label("Show Menu Contributions");

	}
	
	@Override
	public void postAddExtensions() {
		ServiceManager.get(DevToolsManager.class).init();
	}
}
