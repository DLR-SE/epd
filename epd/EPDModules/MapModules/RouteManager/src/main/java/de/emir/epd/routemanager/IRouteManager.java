package de.emir.epd.routemanager;

import java.beans.PropertyChangeEvent;
import java.util.List;

import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.util.RouteViewUtil;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public interface IRouteManager extends IService {
	
	/**
	 * Provide access to the default route manager (used by the Routes view and the route layer)
	 * @return
	 */
	public static IRouteManager getDefaultRouteManager() {
		return ServiceManager.getByID(RouteManagerBasic.DEFAULT_ROUTE_MANAGER);
	}
		
	public interface IRouteAccessModel {
		String 						getName();
		String						getDescription();
		
		List<IRouteAccessModel> 	getChildren();
		
		List<Route>					getOwnedRoutes();
		List<Route> 				getAllRoutes();	
		boolean						containsRoute(Route route);
		
		/**
		 * Subscribe changes in the structure of this specific model, e.g. if a child or an owned route is added / removed
		 * @param consumer
		 * @return
		 */
		Disposable					subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer);
		/**
		 * called to notify the model that it is no longer valid
		 */
		void 						dispose();
		
		/**
		 * Assigns the given route into this route group. 
		 * If this instance does not own any route (e.g. is a group of groups) it shall be added to the first child
		 * @note the assignment shall be done as model transaction
		 * @param route
		 */
		void 						assignRoute(Route route);
		/**
		 * Removes the given route from this group. 
		 * @note this deletion shall be done as model transaction
		 * @param route
		 */
		void						deleteRoute(Route route);
		
	}
	
	public interface IRouteManagerAccess {
		public boolean addModel(IRouteAccessModel model);
		public boolean removeModel(IRouteAccessModel model);
	}
	public interface IRouteAdapter {
		void onModelChange(Object rootModel, IRouteManagerAccess mgr);
	}
	
	public enum VisibilityState {
		Visible, PartialVisible, Invisible
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
    //								Access the Routes													//	
    //////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** 
	 * Returns all known routes, independend of any parent
	 * @return unmodifiable list of all routes
	 */
	public List<Route>	getAllRoutes();
	
	/**
	 * returns all top level route models
	 * @return unmodifiable list of all available top level route models
	 */
	public List<IRouteAccessModel> getRouteModels();
	
	/**
	 * Convenience method: 
	 * @return an unmodifiable list of all routes that are visible
	 */
	public List<Route> getAllVisibleRoutes();
	
	/**
	 * returns the RouteAccessModel with the given name or null. 
	 * @note This method also looks into all child models of getRouteModels()
	 * @param name
	 * @return the IRouteAccessModel with getName() == name or null if no group could be found
	 */
	public IRouteAccessModel getRouteModel(String name);
	
	/**
	 * Returns the group (IRouteAccessModel) that owns the given route
	 * @param route
	 * @return the route that owns the given route (result.containsRoute(route) == true) or null if the condition could not be fulfilled by any group
	 */
	public IRouteAccessModel getParent(Route route);
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//						(Read) Access the Route Properties											//	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @param route route to be checked
	 * @return true if the route is marked as visible
	 */
	public boolean isVisible(final Route route);
	
	/**
	 * 
	 * @param model model to be checked
	 * @return the state of the model. a model is: 
	 *  - visible if all owned routes are visible or all children are visible
	 *  - invisible if all owned routes or all children are visible
	 *  - PartialVisible if at least one route or child is visible
	 */
	public VisibilityState isVisible(IRouteAccessModel model);
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//						(Write) Access the Route Properties											//	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setVisible(final Route route, final boolean visible);
	
	public boolean setVisible(final IRouteAccessModel model, final boolean visible);

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//						Notifications about changed Properties										//	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Disposable subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer);
	
	public Disposable subscribeRouteChanges(Consumer<PropertyChangeEvent> consumer);

	
	public void rebuildModel(Object model);

	/** called when the Manager is no longer needed. This method shall persist the default group for the next call*/
	public void dispose();

	

	public RouteViewUtil getRouteViewUtil();
	
}
