package de.emir.epd.routemanager;

import java.util.List;

import de.emir.epd.routemanager.IRouteManager.IRouteAdapter;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

public interface IRouteAdapterManager extends IExtensionPoint{

	public void registerRouteAdapter(IRouteAdapter adapter);
	public void removeRouteAdapter(IRouteAdapter adpater);
	
	public List<IRouteAdapter> getRouteAdapters();
}
