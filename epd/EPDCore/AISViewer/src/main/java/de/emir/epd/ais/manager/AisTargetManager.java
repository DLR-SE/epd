package de.emir.epd.ais.manager;

import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.epd.ais.model.AisReadAdapterExtensionPoint;

public class AisTargetManager implements IService {
	
	private AisReadAdapterExtensionPoint adapterEP = new AisReadAdapterExtensionPoint();
	
	public AisReadAdapterExtensionPoint getAdapterExtensionPoint() {
		return adapterEP;
	}
	
	public IAisReadAdapter getModelReadAdapter() {
		return adapterEP.getActiveAdapter();
	}
	
	
	
	
}
