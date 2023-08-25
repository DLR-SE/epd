package de.emir.epd.routemanager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.emir.epd.routemanager.IRouteAdapterManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAdapter;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class RouteAdapterManager implements IRouteAdapterManager {

	private List<IRouteAdapter> 		mAdapters = new ArrayList<IRouteAdapter>();
	@Override
	public void registerRouteAdapter(IRouteAdapter adapter) {
		if (adapter != null && mAdapters.contains(adapter)==false) {
			ULog.info("Register RouteAdapter:"  + adapter.getClass().getName());
			mAdapters.add(adapter);
		}		
	}

	@Override
	public void removeRouteAdapter(IRouteAdapter adpater) {
		if (mAdapters.remove(adpater)) {
			ULog.info("Removed RouteAdapter: " + adpater.getClass().getName());
		}
	}

	@Override
	public List<IRouteAdapter> getRouteAdapters() {
		return Collections.unmodifiableList(mAdapters);
	}

}
