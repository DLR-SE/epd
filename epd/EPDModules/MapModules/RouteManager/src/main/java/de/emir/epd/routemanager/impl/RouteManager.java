package de.emir.epd.routemanager.impl;

import de.emir.epd.routemanager.IRouteAdapterManager;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteManagerAccess;
import de.emir.epd.routemanager.impl.model.UnasignedRouteModel;
import de.emir.epd.routemanager.util.RouteViewUtil;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VoyageCharacteristicImpl;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RouteManager implements IRouteManager, IRouteManagerAccess, IService {

	
	public static final String PROP_VISIBLE = "RouteManager.Visible";
	
	private List<IRouteAdapter> 								_mAdapters = null;
	private HashMap<IRouteAccessModel, Disposable>				mStructureDisposables = new HashMap<>();
	
	private Consumer<PropertyChangeEvent>						mStructureConsumer = new Consumer<>() {
		@Override
		public void accept(PropertyChangeEvent t) throws Exception {
			if (t.getNewValue() != null && t.getNewValue() instanceof Route)
				observeRoute((Route)t.getNewValue());
			if (t.getOldValue() != null && t.getOldValue() instanceof Route)
				stopObservingRoute((Route)t.getOldValue());
			mStructureSubscription.onNext(t);
		}
	};
	
	private List<IRouteAccessModel> 							mModels = new ArrayList<>();
	private BehaviorSubject<PropertyChangeEvent>				mStructureSubscription = BehaviorSubject.create();
	private BehaviorSubject<PropertyChangeEvent>				mRouteChangeSubscription = BehaviorSubject.create();

	private class RouteObservation {
		ITreeValueChangeListener	treeListener;
		PropertyChangeListener		managedPropertiesListener;
		Route 						route;
		
		public RouteObservation(Route r) {
			route = r;
			r.registerTreeListener(treeListener = notification -> {
				try {
					mRouteChangeSubscription.onNext(
							new PropertyChangeEvent(
									r,
									notification.getFeature().getName(),
									notification.getOldValue(),
									notification.getNewValue()
							)
					);
				}catch(Exception | Error e) {e.printStackTrace();}
			});
			managedPropertiesListener = evt -> {
				try {
					PropertyChangeEvent pce = new PropertyChangeEvent(
							r,
							evt.getPropertyName(),
							evt.getOldValue(),
							evt.getNewValue()
					);
					mRouteChangeSubscription.onNext(pce);
				}catch(Exception | Error e) {e.printStackTrace();}
			};
			getOrCreateProperty(r, PROP_VISIBLE, true).addPropertyChangeListener(managedPropertiesListener);
			
		}
		void dispose() {
			route.removeTreeListener(treeListener);
			getOrCreateProperty(route, PROP_VISIBLE, true)
					.removePropertyChangeListener(managedPropertiesListener);
		}
	}
	
	private HashMap<Route, RouteObservation>									mRouteObservations = new HashMap<>();

	private UnasignedRouteModel 												mDefaultGroup;

	private String mUniqueID;
	
	public RouteManager(String uniqueID) {
		this(uniqueID, null);
	}
	
	
	public RouteManager(String uniqueID, AbstractModelProvider modelProvider) {
		mUniqueID = uniqueID;
		//the route manager always have a Unassigned - Group
		addModel(mDefaultGroup = createDefaultGroup());
		
		if (modelProvider != null) {
			//at this point there is already a binding between the modelprovider and the RouteManager
			//that has been etablished in RouteManagerPlugin
			if (modelProvider.getModel() != null)
				rebuildModel(modelProvider.getModel());
		 }
	}
	
	public void setRouteAdapters(Collection<IRouteAdapter> adapters) {
		_mAdapters = new ArrayList<>(adapters);
	}
	private List<IRouteAdapter> getRouteAdapters(){
		if (_mAdapters == null) return (ExtensionPointManager.getExtensionPoint(IRouteAdapterManager.class).getRouteAdapters());
		return _mAdapters;
	}
	
	@Override
	public void rebuildModel(Object model) {
		if (model != null) {
			//dispose all elements from the current model
			clearModel();
			//reinsert the default model
			addModel(mDefaultGroup);
			for (IRouteAdapter ada : getRouteAdapters()) {
				try {
					ada.onModelChange(model, this);
				}catch(Exception | Error e ) {
					e.printStackTrace();
				}
			}
		}
	}
	private void clearModel() {
		while(getRouteModels().isEmpty() == false)
			removeModel(getRouteModels().get(0)); //this also removes all listener
	}
	@Override
	public List<Route> getAllRoutes() {
		ArrayList<Route> out = new ArrayList<>();
		for (IRouteAccessModel m : getRouteModels()) {
			List<Route> tmp = m.getAllRoutes();
			if (tmp != null && tmp.isEmpty() == false)
				out.addAll(tmp);
		}
		return out;
	}

	@Override
	public List<IRouteAccessModel> getRouteModels() {
		return Collections.unmodifiableList(mModels);
	}

	@Override
	public IRouteAccessModel getRouteModel(String name) {
		for (IRouteAccessModel ram : getRouteModels()) {
			IRouteAccessModel res = _getRouteModel(ram, name);
			if (res != null)
				return res;
		}
		return null;
	}
	
	
	@Override
	public IRouteAccessModel getParent(Route route) {
		for (IRouteAccessModel ram : getRouteModels())
			if (ram.containsRoute(route))
				return _getParent(ram, route);
		return null;
	}
	
	private IRouteAccessModel _getParent(IRouteAccessModel ram, Route route) {
		if (ram.getOwnedRoutes() != null && ram.getOwnedRoutes().isEmpty() == false)
			for (Route r : ram.getOwnedRoutes())
				if (r == route)
					return ram;
		if (ram.getChildren() != null && ram.getChildren().isEmpty() == false)
			for (IRouteAccessModel c : ram.getChildren())
				if (c.containsRoute(route))
					return _getParent(c, route);
		return null;
	}


	@Override
	public List<Route> getAllVisibleRoutes() {
		//TODO: can we optimize this?
		ArrayList<Route> out = new ArrayList<>();
		for (Route r : getAllRoutes()) {
			if (isVisible(r))
				out.add(r);
		}
		return out;
	}

	@Override
	public boolean isVisible(Route route) {
		if (route == null) return false;
		IProperty<Boolean> p = (IProperty<Boolean>) route.getProperty(PROP_VISIBLE);
		if (p == null) 
			return true;
		return p.getValue();
	}

	@Override
	public VisibilityState isVisible(IRouteAccessModel model) {
		List<Route> allRoutes = model.getAllRoutes();
		int vis = 0, inVis = 0;
		if (allRoutes != null && allRoutes.isEmpty() == false) {
			for (Route r : allRoutes)
				if (isVisible(r)) vis++; 
				else inVis++;
		}
		if (vis == 0) return VisibilityState.Invisible;
		if (inVis == 0) return VisibilityState.Visible;
		return VisibilityState.PartialVisible;
	}

	@Override
	public boolean setVisible(Route route, boolean visible) {
		if (route == null) return false;
		route.setPropertyValue(PROP_VISIBLE, visible);
		return true;
	}

	@Override
	public boolean setVisible(IRouteAccessModel model, boolean visible) {
		List<Route> allRoutes = model.getAllRoutes();
		if (allRoutes != null && allRoutes.isEmpty() == false) {
			for (Route r : allRoutes)
				setVisible(r, visible);
			return true;
		} else {
			return false;
		}
	}
	
	protected void observeRoute(Route route) {
		if (mRouteObservations.containsKey(route) == false)
			mRouteObservations.put(route, new RouteObservation(route));
	}
	
	protected void stopObservingRoute(Route oldValue) {
		RouteObservation ro = mRouteObservations.remove(oldValue);
		if (ro != null) ro.dispose();
	}
	
	public <T> IProperty<T> getOrCreateProperty(Route r, String propVisible, T defaultValue) {
		IProperty<T> p = (IProperty<T>) r.getProperty(propVisible);
		if (p == null) {
			p = (IProperty<T>) r.addProperty(propVisible, propVisible, true);
			p.setValue(defaultValue);
		}
		return p;
	}
	
	@Override
	public boolean addModel(IRouteAccessModel model) {
		//for now, we register each model, however we may?! have to check if we already know this model
		if (model == null) return false;
		mModels.add(model);
		registerListener(model);
		for (Route r : model.getAllRoutes())
			observeRoute(r);
		mStructureSubscription.onNext(new PropertyChangeEvent(this, "models", null, model));
		return true;
	}
	
	@Override
	public boolean removeModel(IRouteAccessModel model) {
		if (model == null) return false;
		if (mModels.remove(model)) {
			removeListener(model);
			for (Route r : model.getAllRoutes())
				stopObservingRoute(r);
			model.dispose();
			mStructureSubscription.onNext(new PropertyChangeEvent(this, "models", model, null));
		}
		return false;
	}

	private void registerListener(IRouteAccessModel model) {
		mStructureDisposables.put(model, model.subscribeStructureChanges(mStructureConsumer));
		
		List<IRouteAccessModel> children = model.getChildren();
		if (children != null && children.isEmpty() == false)
			for (IRouteAccessModel c : children)
				registerListener(c);
	}
	
	private void removeListener(IRouteAccessModel model) {
		Disposable disp = mStructureDisposables.remove(model);
		if (disp != null)
			disp.dispose();

		List<IRouteAccessModel> children = model.getChildren();
		if (children != null && children.isEmpty() == false)
			for (IRouteAccessModel c : children)
				removeListener(c);
	}
	
	@Override
	public Disposable subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer) {
		return mStructureSubscription.subscribe(consumer);
	}


	@Override
	public Disposable subscribeRouteChanges(Consumer<PropertyChangeEvent> consumer) {
		return mRouteChangeSubscription.subscribe(consumer);
	}

	private IRouteAccessModel _getRouteModel(IRouteAccessModel ram, String name) {
		if (ram.getName().equals(name))
			return ram;
		if (ram.getChildren() != null && ram.getChildren().isEmpty() == false) {
			for (IRouteAccessModel c : ram.getChildren()) {
				IRouteAccessModel res = _getRouteModel(c, name);
				if (res != null)
					return res;
			}
		}
		return null;
	}

	@Override
	public void dispose() {
		//we do store the default group as if its a voyage and reuse the UCore serialisation methods
		VoyageCharacteristic vc = new VoyageCharacteristicImpl();
		vc.getRoutes().addAll(mDefaultGroup.getAllRoutes());
		XMLSerializer ser = new XMLSerializer();
		try {
			ser.writeFile(vc, getUnasignedFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private UnasignedRouteModel createDefaultGroup() {
		File f = getUnasignedFile();
		List<Route> routes = null;
		if (f.exists()) {
			XMLSerializer ser = new XMLSerializer();
			try {
				VoyageCharacteristic vc = (VoyageCharacteristic)ser.readFile(f);
				routes = vc.getRoutes();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return new UnasignedRouteModel(routes);
	}

	private File getUnasignedFile() {
		File homePath = ResourceManager.get(this).getHomePath().toFile();
		return new File(homePath.getAbsolutePath() + "/RouteManager/RouteManager_" + mUniqueID + ".xml");
	}
	
	@Override
	public RouteViewUtil getRouteViewUtil() {
		return RouteViewUtil.getInstance();
	}
}
