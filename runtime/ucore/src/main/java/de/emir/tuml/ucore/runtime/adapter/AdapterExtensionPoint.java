package de.emir.tuml.ucore.runtime.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;

import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * A specialization of the general IExtensionPoint interface. Use this class to define model adapter extension points.
 * Model adapter extension points are different from normal extension points in that it is possible to check whether
 * multiple adapters have been registered for the same purpose. This allows the plugin system to offer the product
 * developer a user interface for selecting the currently active adapter.
 * 
 * @author Florian
 *
 */
public abstract class AdapterExtensionPoint<T extends IAdapter> implements IExtensionPoint {

    private Logger log;

    private List<T> adapters = new ArrayList<>();

    private T activeAdapter;

    public AdapterExtensionPoint(Logger log) {
        this.log = log;

        if (this.log == null) {
            this.log = ULog.getLogger(AdapterExtensionPoint.class);
        }
    }

    public List<T> getAdapters() {
        return adapters;
    }

    public List<T> getOrderedAdapters() {
        ArrayList<T> copy = new ArrayList<>(getAdapters());
        Collections.sort(copy, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return copy;
    }

    public void setActiveAdapter(T a) {
        activeAdapter = a;

        // TODO: Store in config
    }

    public void adapter(T a) {
        adapters.add(a);

        // TODO: set active adapter from config if found

        if (activeAdapter == null) {

            activeAdapter = adapters.get(0);

        }
    }

    public T getActiveAdapter() {
        return activeAdapter;
    }

}
