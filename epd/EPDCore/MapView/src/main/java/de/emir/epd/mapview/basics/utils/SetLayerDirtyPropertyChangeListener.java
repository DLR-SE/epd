package de.emir.epd.mapview.basics.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.emir.epd.mapview.views.map.AbstractMapLayer;

public class SetLayerDirtyPropertyChangeListener implements PropertyChangeListener {

	private AbstractMapLayer layer;


	public SetLayerDirtyPropertyChangeListener(AbstractMapLayer layer) {
		this.layer = layer;
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		layer.setDirty(true);

	}

}
