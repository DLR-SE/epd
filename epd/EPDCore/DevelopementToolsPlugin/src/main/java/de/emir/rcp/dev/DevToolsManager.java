package de.emir.rcp.dev;

import de.emir.rcp.commands.basics.RestartApplicationCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class DevToolsManager implements IService {

	public void init() {
		
		PropertyContext propContext = PropertyStore.getContext(Basic.DEV_PROP_CTX);
		IProperty<Boolean> property = propContext.getProperty(Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS, false);
		property.addPropertyChangeListener(evt -> requestRestart());
		
	}

	private void requestRestart() {
		
		RestartApplicationCommand cmd = new RestartApplicationCommand();
		
		CommandManager cmdMgr = ServiceManager.get(CommandManager.class);
		cmdMgr.executeCommand(cmd);
		
	}
	
}
