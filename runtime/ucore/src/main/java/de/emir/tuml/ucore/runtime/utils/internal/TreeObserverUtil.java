package de.emir.tuml.ucore.runtime.utils.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import java.util.ListIterator;

public class TreeObserverUtil implements IValueChangeListener<Object> {

	
	public interface ITreeObserverOptions {
		boolean shallRegister(UStructuralFeature feature);
		boolean shallRegisterListItem(UStructuralFeature feature, int listIdx);
	}
	
    public static class TreeObserverOptions implements ITreeObserverOptions {
        public boolean registerForAssociations = true;
        public boolean registerForAggregations = true;
        public boolean registerForProperties = true;
        public boolean registerForCompositions = true;
        public HashSet<UStructuralFeature> ignoredFeatures = new HashSet<>();

        public TreeObserverOptions() {
        }

        public TreeObserverOptions(boolean asso, boolean agge, boolean prop, boolean comp) {
            registerForAggregations = agge;
            registerForAssociations = asso;
            registerForCompositions = comp;
            registerForProperties = prop;
        }

        public TreeObserverOptions addIgnoredFeature(UStructuralFeature feature) {
            if (feature != null)
                ignoredFeatures.add(feature);
            return this;
        }

        public void removeIgnoredFeature(UStructuralFeature feature) {
            ignoredFeatures.remove(feature);
        }

        public boolean shallRegister(UStructuralFeature feature) {
            if (ignoredFeatures.contains(feature))
                return false;
            switch (feature.getAggregation()) {
            case AGGREGATION:
                return registerForAggregations;
            case ASSOCIATION:
                return registerForAssociations;
            case COMPOSITION:
                return registerForCompositions;
            case PROPERTY:
                return registerForProperties;
            }

            return true;
        }

		@Override
		public boolean shallRegisterListItem(UStructuralFeature feature, int listIdx) {
			return true; //register for all list items/elements
		}
    }

    public static final TreeObserverOptions OPTIONS_ALL = new TreeObserverOptions(true, true, true, true);
    public static final TreeObserverOptions OPTIONS_NO_AGGREGATIONS = new TreeObserverOptions(true, false, true, true);

    public static TreeObserverUtil registerListener(UObject obj, ITreeValueChangeListener listener) {
        return registerListener(obj, new TreeObserverUtil(listener));
    }

    public static TreeObserverUtil registerListener(UObject obj, TreeObserverUtil listener) {
        listener.register(obj);
        return listener;
    }

    public static <T extends UObject> TreeObserverUtil removeListener(UObject obj, TreeObserverUtil listener) {
        listener.remove(obj);
        return listener;
    }

    private ITreeValueChangeListener mDelegate;
    private Set<Integer> mClosedList = new HashSet<>();

    public TreeObserverUtil(ITreeValueChangeListener delegate) {
        mDelegate = delegate;
    }

    @Override
    public void onValueChange(Notification<Object> notification) {
        switch (notification.getType()) {
        case ADD:
            Object n = notification.getNewValue();
            if (n != null && n instanceof UObject)
                register((UObject) n); // added to a list, no remove required
            break;
        case ADD_MANY:
            Collection newValues = (Collection) notification.getNewValue();
            for (Object nl : newValues)
                if (nl != null && nl instanceof UObject)
                    register((UObject) nl);
            break;
        case REMOVE:
            Object o = notification.getOldValue();
            if (o != null && o instanceof UObject)
                remove((UObject) o);
            break;
        case REMOVE_MANY:
            Collection oldValues = (Collection) notification.getOldValue();
            for (Object ol : oldValues)
                if (ol != null && ol instanceof UObject)
                    remove((UObject) ol);
            break;
        case SET:
            Object oobj = notification.getOldValue();
            if (oobj != null && oobj instanceof UObject)
                remove((UObject) oobj);
            Object nobj = notification.getNewValue();
            if (nobj != null && nobj instanceof UObject)
                register((UObject) nobj);
            break;
        case UNSET:
            Object ooobj = notification.getOldValue();
            if (ooobj != null && ooobj instanceof UObject)
                remove((UObject) ooobj);
        }
        mDelegate.onValueChange(notification); // delegate to the real listener
    }

    public void register(UObject uobj) {
        register(uobj, TreeObserverUtil.OPTIONS_ALL);
    }

    public void register(UObject uobj, ITreeObserverOptions options) {
        // initial register to all existing children
        int hash = uobj.hashCode();
        if (mClosedList.contains(hash))
            return;
        mClosedList.add(hash);
        uobj.registerListener(this);

        UClass cl = uobj.getUClassifier();
        for (UStructuralFeature f : cl.getAllStructuralFeatures()) {
            if (f.isReference() && (options == null || options.shallRegister(f))) {
                Object f_value = f.get(uobj);
                if (f_value != null) {
                    if (f.isMany()) { // need to register in all list elements
                        List list = (List) f_value;
                        for (int i = 0; i < list.size(); i++) {
                        	Object el = list.get(i);
                            if (el != null && el instanceof UObject) {
                                UObject uel = (UObject) el;
                                if (options == null || options.shallRegisterListItem(f, i))
                                	register(uel, options);
                            }
                        }
                    } else {
                        if (f_value instanceof UObject)
                            register((UObject) f_value, options);
                    }
                }
            }
        }
    }

    public void remove(UObject uobj) {
        int hash = uobj.hashCode();
        if (mClosedList.contains(hash) == false) {
            return;
        }
        uobj.removeListener(this);
        mClosedList.remove(hash);

        UClass cl = uobj.getUClassifier();
        if (cl == null || cl.getAllStructuralFeatures() == null) {
            return;
        }
        synchronized (cl.getAllStructuralFeatures()) {
            ListIterator<UStructuralFeature> iFeature = cl.getAllStructuralFeatures().listIterator();
            while (iFeature.hasNext()) {
                UStructuralFeature f = iFeature.next();
                if (f.isReference()) {
                    Object f_value = f.get(uobj);
                    if (f_value != null) {
                        if (f.isMany()) { // need to register in all list elements
                            List list = (List) f_value;
                            synchronized (list) {
                                ListIterator<Object> iElement = list.listIterator();
                                while (iElement.hasNext()) {
                                    Object el = iElement.next();
                                    if (el != null && el instanceof UObject) {
                                        UObject uel = (UObject) el;
                                        remove(uel);
//                                        iElement.remove();
                                    }
                                }
                            }
                        } else {
                            if (f_value instanceof UObject) {
                                remove((UObject) f_value);
                            }
                        }
                    }
                }
            }
        }
    }

    public ITreeValueChangeListener getDelegate() {
        return mDelegate;
    }

}
