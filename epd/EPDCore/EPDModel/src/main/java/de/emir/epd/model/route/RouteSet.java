package de.emir.epd.model.route;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;

/**
 * Use this to handle routes for the RouteManager.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class RouteSet extends Observable implements IValueChangeListener, Observer { // TODO: implements Collection, Iterator
	private Collection<Route> queue = Collections.synchronizedCollection(new PriorityQueue<Route>());

	/**
	 * Get an item of this set by its id.
	 * 
	 * @param id
	 *            the id to search for
	 * @return the Target object with the given id
	 */
	public Route getById(String id) {
		Iterator<Route> it = queue.iterator();
		while (it.hasNext()) {
			Route item = it.next();
			if (item.getName().equals(id)) {
				return item;
			}
		}
		return null;
	}

	public Route retrieveById(String id) {
		Route result = getById(id);
		
		if (result == null) {
			result = new RouteImpl();
			queue.add(result);
		}

		return result;
	}

	public RouteSet(RouteSet targetset) {
		super();
		if (targetset != null && targetset.size() > 0) {
			Iterator<Route> it = targetset.iterator();
			while (it.hasNext()) {
				queue.add(new RouteImpl(it.next()));
			}
		}
	}

	public RouteSet() {
		super();
	}

	public boolean add(Route e) {
		e.registerListener(this);
//		e.addObserver(this);
		boolean result = queue.add(e);
		setChanged();
		notifyObservers();
		return result;
	}

	public boolean remove(Object o) {
		((Route) o).removeListener(this);
		boolean result = queue.remove(o);

		setChanged();
		notifyObservers();

		return result;
	}

	public void clear() {
		queue.clear();
		setChanged();
		notifyObservers();
	}

	@Override
	public RouteSet clone() {
		return new RouteSet(this);
	}

	public int size() {
		return queue.size();
	}

	public Iterator<Route> iterator() {
		return queue.iterator();
	}
	
	public Route[] getAisTargets() {
		return queue.toArray(new Route[0]);
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers();
	}

	@Override
	public void onValueChange(Notification arg0) {
		notifyObservers();
	}

}
