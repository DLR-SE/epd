package de.emir.epd.ais.model;

import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;

public class EPDAisModelAdapterPlugin extends AbstractUIPlugin {
	
	
	@Override
	public void addExtensions() {
		AisReadAdapterExtensionPoint ep = ExtensionPointManager.getExtensionPoint(AisReadAdapterExtensionPoint.class);
		ep.adapter(new EPDAisReadAdapter());
	}

}
