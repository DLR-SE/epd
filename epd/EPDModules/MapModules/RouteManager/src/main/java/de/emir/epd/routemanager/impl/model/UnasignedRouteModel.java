package de.emir.epd.routemanager.impl.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class UnasignedRouteModel implements IRouteAccessModel {

	private ArrayList<Route> 							mRoutes = new ArrayList<>();
	private BehaviorSubject<PropertyChangeEvent>		mStructureSubscription = BehaviorSubject.create();
	
	public UnasignedRouteModel(List<Route> routes) {
		if (routes != null) {
			mRoutes.addAll(routes);
		}
	}

	@Override
	public String getName() {
		return RouteManagerBasic.UNASIGNED_ROUTE_GROUP;
	}

	@Override
	public String getDescription() {
		return "Route Group that holds a list of Routes that has not been assigned to any vessel yet";
	}

	@Override
	public List<IRouteAccessModel> getChildren() {
		return null; //no children allowed
	}

	@Override
	public List<Route> getOwnedRoutes() {
		return Collections.unmodifiableList(mRoutes);
	}

	@Override
	public List<Route> getAllRoutes() {
		return getOwnedRoutes();
	}

	@Override
	public boolean containsRoute(Route route) {
		for (Route r : getOwnedRoutes()) if (r == route) return true;
		return false;
	}

	@Override
	public Disposable subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer) {
		return mStructureSubscription.subscribe(consumer);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void assignRoute(Route route) {
		if (containsRoute(route))
			return ;
		mRoutes.add(route);
		try {
			mStructureSubscription.onNext(new PropertyChangeEvent(this, "Routes", null, route));
		}catch(Exception | Error e) { e.printStackTrace();}
	}

	@Override
	public void deleteRoute(Route route) {
		if (mRoutes.remove(route)) {
			try {
				mStructureSubscription.onNext(new PropertyChangeEvent(this, "Routes", route, null));
			}catch(Exception | Error e) { e.printStackTrace();}	
		}
	}

}
