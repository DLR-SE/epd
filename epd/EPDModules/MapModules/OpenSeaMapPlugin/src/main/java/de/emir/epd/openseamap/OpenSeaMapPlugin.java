package de.emir.epd.openseamap;

import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;

public class OpenSeaMapPlugin extends AbstractUIPlugin {

	@Override
	public void registerExtensionPoints() {
	}

	@Override
	public void addExtensions() {
		// MapView EP
		MapViewExtensionPoint mvEP = ExtensionPointManager.getExtensionPoint(MapViewExtensionPoint.class);

		mvEP.layer("OpenSeaMapLayer", OpenSeaMapLayer.class)
				.label("OpenSeaMap")
				.icon("icons/unlicensed/openseamap-logo-32.png");
	}

}
