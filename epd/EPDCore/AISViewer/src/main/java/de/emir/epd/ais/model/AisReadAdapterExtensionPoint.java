package de.emir.epd.ais.model;

import de.emir.rcp.model.adapter.AdapterExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class AisReadAdapterExtensionPoint extends AdapterExtensionPoint<IAisReadAdapter> {

	public AisReadAdapterExtensionPoint() {
		super(ULog.getLogger(AisReadAdapterExtensionPoint.class));
	}

}
