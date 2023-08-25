package de.emir.epd.mapview.cmds;

import java.util.Map;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class SetAllLayerVisibleStateCommand extends AbstractCommand {

	private PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
	private boolean setVisible;
	
	public SetAllLayerVisibleStateCommand(boolean setVisible) {
		this.setVisible = setVisible;
	}
	
	@Override
	public void execute() {
		
		Map<String, MapLayer> layers = ServiceManager.get(MapViewManager.class).getExtensionPoint().getLayers();
		
		for (MapLayer layer : layers.values()) {
			
			IProperty<Boolean> property = context.getProperty(MVBasic.MAP_VIEW_VISIBLE_LAYERS_PROP + "_" + layer.getId(), true);
			property.setValue(setVisible);
			
		}
		
	}

}
