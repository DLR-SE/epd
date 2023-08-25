package de.emir.epd.routemanager.impl.model;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.transactions.AddValueTransaction;
import de.emir.rcp.model.transactions.RemoveValueTransaction;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class VoyageBasedRouteModel implements IRouteAccessModel, IValueChangeListener {

	private BehaviorSubject<PropertyChangeEvent>		mStructureSubscription = BehaviorSubject.create();
	
	private VoyageCharacteristic 						mVoyage;
	private HashMap<Route, ITreeValueChangeListener>	mRouteListeners = new HashMap<>();

	public VoyageBasedRouteModel(VoyageCharacteristic voyage) {
		mVoyage = voyage;
		
		mVoyage.registerListener(this);	
	}
	
	
	@Override
	public void dispose() {
		mVoyage.removeListener(this);
	}
	@Override
	public String getName() {
		return "Voyage";
	}

	@Override
	public String getDescription() {
		return "Voyage";
	}

	@Override
	public List<IRouteAccessModel> getChildren() {
		return null;
	}

	@Override
	public List<Route> getOwnedRoutes() {
		return Collections.unmodifiableList(mVoyage.getRoutes());
	}

	@Override
	public List<Route> getAllRoutes() {
		return getOwnedRoutes();
	}

	@Override
	public Disposable subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer) {
		return mStructureSubscription.subscribe(consumer);
	}

	public VoyageCharacteristic getVoyage() {
		return mVoyage;
	}
	
	@Override
	public void onValueChange(Notification notification) {
		try {
			mStructureSubscription.onNext(new PropertyChangeEvent(notification.getInstance(), notification.getFeature().getName(), notification.getOldValue(), notification.getNewValue()));
		}catch(Exception e) {e.printStackTrace();}
	}


	@Override
	public boolean containsRoute(Route route) {
		for (Route r : getOwnedRoutes())
			if (r == route)
				return true;
		return false;
	}


	@Override
	public void assignRoute(Route route) {
		ModelTransactionStack ts = PlatformUtil.getModelManager().getModelProvider().getTransactionStack();
		AddValueTransaction transaction = new AddValueTransaction(getVoyage(), VesselPackage.Literals.VoyageCharacteristic_routes, route);
		ts.run(transaction);
	}


	@Override
	public void deleteRoute(Route route) {
		ModelTransactionStack ts = PlatformUtil.getModelManager().getModelProvider().getTransactionStack();
		RemoveValueTransaction transaction = new RemoveValueTransaction(getVoyage(), VesselPackage.Literals.VoyageCharacteristic_routes, route);
		ts.run(transaction);
				
	}

}
