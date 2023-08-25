package de.emir.tuml.ucore.runtime.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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
public class ObservableHashMap<K, V> extends HashMap<K, V> {

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

    protected void notify(CHANGE_TYPE type, Object oldKey, Object oldValue, Object newKey, Object newValue) {
        if (mListener == null)
            return;// nothing to do
        synchronized (mListener) {
            Entry<K, V> o = new SimpleEntry(oldKey, oldValue);
            Entry<K, V> n = new SimpleEntry(newKey, newValue);

            for (ICollectionObserver<?> obs : mListener) {
                try {
                    obs.onCollectionChanged(this, type, o, n);
                } catch (Exception | Error err) {
                    // DO nothing at the moment, expect printing the stacktrace
                    err.printStackTrace();
                }
            }
        }
    }

    @Override
    public V put(K key, V value) {
        V res = super.put(key, value);
        notify(CHANGE_TYPE.ADD, null, null, key, value);
        return res;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        notify(CHANGE_TYPE.ADD_MANY, null, m, null, null);
    }

    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            V res = super.remove(key);
            notify(CHANGE_TYPE.REMOVE, null, null, key, res);
            return res;
        }
        return null;
    }

    @Override
    public void clear() {
        super.clear();
        notify(CHANGE_TYPE.CLEAR, null, null, null, null);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V oldValue = super.putIfAbsent(key, value);
        notify(CHANGE_TYPE.ADD, key, oldValue, key, value);
        return oldValue;
    }

    @Override
    public boolean remove(Object key, Object value) {
        boolean res = super.remove(key, value);
        if (res)
            notify(CHANGE_TYPE.REMOVE, key, value, null, null);
        return res;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        boolean res = super.replace(key, oldValue, newValue);
        if (res)
            notify(CHANGE_TYPE.ADD, key, oldValue, key, newValue);
        return res;
    }

    @Override
    public V replace(K key, V value) {
        V oldValue = super.replace(key, value);
        notify(CHANGE_TYPE.ADD, key, oldValue, key, value);
        return oldValue;
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        super.replaceAll(function);
        notify(CHANGE_TYPE.REMOVE_MANY, null, null, null, function);
    }

}
