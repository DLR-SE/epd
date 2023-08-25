package de.emir.epd.ais.model;

import de.emir.model.universal.physics.Environment;
import de.emir.rcp.model.adapter.IReadAdapter;

/**
 * Provides AIS data to this plugin. Concrete implementations must be registered
 * using the AisReadAdapterExtensionPoint.
 * 
 * @author Florian
 *
 */
public interface IAisReadAdapter extends IReadAdapter<Object> {

	public Environment getTargetSet();
}
