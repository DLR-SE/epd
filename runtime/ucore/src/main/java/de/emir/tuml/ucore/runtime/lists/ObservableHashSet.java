package de.emir.tuml.ucore.runtime.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import de.emir.tuml.ucore.runtime.lists.ICollectionObserver.CHANGE_TYPE;

/**
 * Specialized HashMap that notifies about changes.
 * 
 * @note since the ICollectionObserver Interface is hold as general as possible here a list of notifications: - put(key,
 *       value) : notify(CHANGE_TYPE.ADD, null, null, key, value) - putAll(m) : notify(CHANGE_TYPE.ADD_MANY, null, m,
 *       null, null) - remove(key) : notify(CHANGE_TYPE.REMOVE, null, null, key, res) - clear() :
 *       notify(CHANGE_TYPE.CLEAR, null, null, null, null) - putIfAbsent(key, value) : notify(CHANGE_TYPE.ADD, key,
 *       oldValue, key, value) - remove(key, value) : notify(CHANGE_TYPE.REMOVE, key, value, null, null) - replace(key,
 *       oldValue, newValue) : notify(CHANGE_TYPE.ADD, key, oldValue, key, newValue) - replace(key, value) :
 *       notify(CHANGE_TYPE.ADD, key, oldValue, key, value) - replaceAll(function) : notify(CHANGE_TYPE.REMOVE_MANY,
 *       null, null, null, function)
 * @author sschweigert
 *
 * @param <K>
 * @param <V>
 */
public class ObservableHashSet<V> extends HashSet<V> {

    private static final long serialVersionUID = 3146243937671253996L;

    private List<ICollectionObserver> mListener = null; // create upon request

    public boolean registerListener(ICollectionObserver listener) {
        if (listener == null)
            return false; // nothing to do
        if (mListener == null)
            mListener = new ArrayList<>();
        synchronized (mListener) {
            if (mListener.contains(listener))
                return false; // just register once
            mListener.add(listener);
            return true;
        }
    }

    public boolean removeListener(ICollectionObserver listener) {
        if (listener == null)
            return false;
        if (mListener == null)
            return false; // nothing added at the moment
        synchronized (mListener) {
            boolean result = mListener.remove(listener);
            if (mListener.isEmpty())
                mListener = null;
            return result;
        }
    }

    protected void notify(CHANGE_TYPE type, V oldValue, V newValue) {
        if (mListener == null)
            return;// nothing to do
        synchronized (mListener) {
            for (ICollectionObserver<?> obs : mListener) {
                try {
                    obs.onCollectionChanged(this, type, oldValue, newValue);
                } catch (Exception | Error err) {
                    // DO nothing at the moment, expect printing the stacktrace
                    err.printStackTrace();
                }
            }
        }
    }

    @Override
	public boolean add(V e) {
		boolean res = super.add(e);
		if (res)
			notify(CHANGE_TYPE.ADD, null, e);
		return res;
	}

    @Override
    public boolean addAll(Collection<? extends V> c) {
    	boolean res = true;
		for (Object v : c )
    		res = res & add((V)v);
    	return res;
    }

    @Override
    public boolean remove(Object o) {
    	boolean res = super.remove(o);
    	if (res)
    		try {
    			notify(CHANGE_TYPE.REMOVE, (V)o, (V)null);
    		}catch(ClassCastException e) {}
    	return res;
    }

    @Override
    public void clear() {
        super.clear();
        notify(CHANGE_TYPE.CLEAR, null, null);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
    	boolean res = true;
    	for (Object v : c)
    		res = res & remove(v);
    	return res;
    }
        
}
