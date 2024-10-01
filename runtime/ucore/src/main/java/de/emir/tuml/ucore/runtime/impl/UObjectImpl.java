package de.emir.tuml.ucore.runtime.impl;

import java.beans.PropertyChangeListener;
import java.util.*;

import de.emir.tuml.ucore.runtime.DelegateFactory;
import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.internal.InternalUObject;
import de.emir.tuml.ucore.runtime.lists.FeatureIterator;
import de.emir.tuml.ucore.runtime.lists.UContentIterator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.ITreeObserverOptions;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.TreeObserverOptions;

/**
 * @generated
 */
@UMLImplementation(classifier = UObject.class)
abstract public class UObjectImpl implements UObject {

    private static int sHashCounter = 0;

    /**
     * The internal UObject is used to hold the management functionality with respect to the container and
     * containingFeature, and therefore to handle the logic of UML associations (and properties)
     */
    private InternalUObject mInternalUObject = null;

    /**
     * @generated not
     */
//    private UStructuralFeature mUContainingFeature = null;

    protected IDelegateInterface mDelegate = null;

    /**
     * List of generic properties that can be used to store information within an instance, that are not captured by any
     * data model
     * 
     * @note to use this capability use the api functions: setPropertyValue(...), getPropertyValueAs...(),
     *       createProperty(...) and removeProperty()
     * @warn use those properties only if required, there is no semantic check and they will be always copied if the
     *       copy constructor is invoked
     */
    private HashMap<String, IProperty> mProperties = null;
    /**
     * if this instance is part of an composite association (composition) the container points to the owner of this
     * instance
     * 
     * @generated not
     */
    // private UObject mUContainer = null;

    /**
     * @generated not
     */
    // private UStructuralFeature mUContainingFeature = null;

    private int mLocalHashCode = sHashCounter++;
    
    protected boolean mHasListener = false;

    public static int	classifierDisposableCount = 0;
    public static int 	featureDisposableCount = 0;
    public static int 	treeListenerDisposableCount = 0;
    
    public static boolean									sDebugDisposables = false;

    public static WeakHashMap<UObject, Set<IDisposable>>	sObjectDisposableMap = new WeakHashMap<>();

    static void registerDisposable(UObject uobj, IDisposable d) {
    	Set<IDisposable> set = sObjectDisposableMap.get(uobj);
    	if (set == null) sObjectDisposableMap.put(uobj, set = new HashSet<>());
    	set.add(d);
    }

    static void removeDisposable(UObject uobj, IDisposable d) {
    	Set<IDisposable> set = sObjectDisposableMap.get(uobj);
    	if (set != null)
    		if (set.remove(d))
    			if (set.isEmpty())
    				sObjectDisposableMap.remove(uobj);
    }
    
    private interface UObjectDisposable extends IDisposable {
    	Object getListener();

		void setDisposed();
    }
    
    private static class PropertyDisposable implements IDisposable {
    	IProperty				property;
    	PropertyChangeListener	listener;
    	
    	public PropertyDisposable(IProperty p, PropertyChangeListener pcl) {
    		property = p;
    		listener = pcl;
    		p.addPropertyChangeListener(pcl);
		}
		@Override
		public void dispose() {
			if (property != null && listener != null)
				property.removePropertyChangeListener(listener);
			property = null;
			listener = null;
		}

		@Override
		public boolean isDisposed() {
			return property != null;
		}
    	
    }
    
    private static class ClassifierDisposable implements UObjectDisposable {
        final UObject instance;
        final IValueChangeListener listener;
        final int id;
        boolean disposed;

        public ClassifierDisposable(UObject inst, IValueChangeListener l) {
        	if (sDebugDisposables)
        		registerDisposable(inst, this);
            instance = inst;
            listener = l;
            id = classifierDisposableCount++;
        }

        @Override
        public void dispose() {
            instance.removeClassifierListener(listener);
            //setDisposed is called by removeListener
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
        @Override
        public Object getListener() {
        	return listener;
        }
        @Override
        public void setDisposed() {
        	disposed = true;
            classifierDisposableCount--;
            if (sDebugDisposables)
            	removeDisposable(instance, this);
        }
        
        @Override
        public String toString() {
        	return "ClassifierDisposable: " + id + ":" + instance + ":" + listener; 
        }
    }

    private static class FeatureDisposable implements UObjectDisposable {
        final UStructuralFeature feature;
        final UObject instance;
        final IValueChangeListener listener;
        final int id;
        boolean disposed;
        
        public FeatureDisposable(UObject inst, UStructuralFeature f, IValueChangeListener l) {
        	if (sDebugDisposables)
        		registerDisposable(inst, this);
            instance = inst;
            listener = l;
            feature = f;
            id = featureDisposableCount++;
        }

        @Override
        public void dispose() {
            instance.removeListener(feature, listener);
            //setDisposed is called by removeListener
        }
        @Override
        public void setDisposed() {
        	disposed = true;
        	featureDisposableCount--;
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
        @Override
        public Object getListener() {
        	return listener;
        }
        
        @Override
        public String toString() {
        	return "FeatureDisposable: " + id + ":" + instance + ":" + feature + ":" + listener; 
        }
    }

    private static class TreeListenerDisposable implements UObjectDisposable {
        final UObject instance;
        final ITreeValueChangeListener listener;
        final int	id;
        boolean disposed;

        public TreeListenerDisposable(UObject inst, ITreeValueChangeListener l) {
            instance = inst;
            listener = l;
            id = treeListenerDisposableCount++;
            if (sDebugDisposables)
            	registerDisposable(instance, this);
        }

        @Override
        public void dispose() {
            instance.removeTreeListener(listener);
            //setDisposed is called by removeListener
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }
        @Override
        public Object getListener() {
        	return listener;
        }
        @Override
        public void setDisposed() {
        	disposed = true;
            treeListenerDisposableCount--;	
            if (sDebugDisposables)
            	removeDisposable(instance, this);
        }
        
        @Override
        public String toString() {
        	return "TreeListenerDisposable: " + id + ":" + instance + ":" + listener; 
        }
    }

    private static class DisposableList extends ArrayList<UObjectDisposable> {

        @Override
        public synchronized boolean add(UObjectDisposable uObjectDisposable) {
            return super.add(uObjectDisposable);
        }

        @Override
        public synchronized boolean remove(Object o) {
            return super.remove(o);
        }

        public boolean contains(IValueChangeListener listener) {
    		return get(listener) != null;
    	}

		public synchronized UObjectDisposable get(IValueChangeListener listener) {
			for (UObjectDisposable disp : this)
    			if (disp.getListener() == listener)
    				return disp;
    		return null;
		}

		public synchronized UObjectDisposable get(ITreeValueChangeListener listener) {
			for (UObjectDisposable disp : this)
    			if (disp.getListener() == listener)
    				return disp;
    		return null;
		}
    }

    private DisposableList _mClassifierListener = null;
    private DisposableList _mTreeListener = null;
    private HashMap<UStructuralFeature, DisposableList> _mFeatureListener = null;
    
    
    /**
     * Default constructor
     * 
     * @generated not
     */
    public UObjectImpl() {
        super();
    }

    /**
     * Default attribute constructor
     * 
     * @generated not
     */
    public UObjectImpl(UClass _uClassifier, UObject _uContainer, UStructuralFeature _uContainingFeature) {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated not
     */
    public UObjectImpl(final UObject _copy) {
        super();
        // we do copy the properties
        @SuppressWarnings("rawtypes")
        Collection<IProperty> _cp = _copy.getAllProperties();
        if (_cp != null) {
            mProperties = new HashMap<>();
            for (IProperty _p : _cp) {
                mProperties.put(_p.getName(), _p.copy());
            }
        }
    }

    @Override
    public int hashCode() {
        // remark SoS: using a specialized hash seems to increase the performance of serialisation on large datasets
        // (e.g. more conflicts based on the hash)
        // however to use it correctly we have to have a more valid hash than just counting the objects
        return mLocalHashCode;
    }

    @Override
    public <T extends IDelegateInterface> T getDelegate() {
        if (mDelegate == null)
            mDelegate = DelegateFactory.createDelegate(this);
        return (T) mDelegate;
//    	return null;
    }

    /**
     * the type of this object
     * 
     * @generated not
     */
    @Override
    public abstract UClass getUClassifier();

    /**
     * the type of this object
     * 
     * @generated not
     */
    // private UClass mUClassifier = null;

    /**
     * if this instance is part of an composite association (composition) the container points to the owner of this
     * instance
     * 
     * @generated not
     */
    // private void setUContainer(UObject _uContainer) {
    // Notification<UObject> notification = basicSet(mUContainer, _uContainer,
    // RuntimePackage.Literals.UObject_uContainer);
    // mUContainer = _uContainer;
    // if (notification != null){
    // dispatchNotification(notification);
    // }
    // }

    /**
     * if this instance is part of an composite association (composition) the container points to the owner of this
     * instance
     * 
     * @generated not
     */
    // private void setUContainer(UObject _uContainer) {
    // Notification<UObject> notification = basicSet(mUContainer, _uContainer,
    // RuntimePackage.Literals.UObject_uContainer);
    // mUContainer = _uContainer;
    // if (notification != null){
    // dispatchNotification(notification);
    // }
    // }

    /**
     * if this instance is part of an composite association (composition) the container points to the owner of this
     * instance
     * 
     * @generated not
     */
    @Override
    public UObject getUContainer() {
        return _internalGetInternalUObject().getContainer();
    }

    // /**
    // * @generated
    // */
    // private void setUContainingFeature(UStructuralFeature _uContainingFeature) {
    // if (needNotification(RuntimePackage.Literals.UObject_uContainingFeature)){
    // mUContainingFeature = notify(mUContainingFeature, _uContainingFeature,
    // RuntimePackage.Literals.UObject_uContainingFeature, NotificationType.SET);
    // }else{
    // mUContainingFeature = _uContainingFeature;
    // }
    // }

    /**
     * @generated not
     */
    @Override
    public UStructuralFeature getUContainingFeature() {
        return _internalGetInternalUObject().getContainingFeature();
    }

    /**
     * @generated not
     */
    private void setUContainingFeature(UStructuralFeature _uContainingFeature) {
    	throw new UnsupportedOperationException();
//        Notification<UStructuralFeature> notification = basicSet(mUContainingFeature, _uContainingFeature,
//                RuntimePackage.Literals.UObject_uContainingFeature);
//        mUContainingFeature = _uContainingFeature;
//        if (notification != null) {
//            dispatchNotification(notification);
//        }
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean needNotification(final UStructuralFeature _feature) {
    	if (mHasListener) {
	        if (_mClassifierListener != null)
	            return true;
	        if (_mFeatureListener != null && _feature != null && _mFeatureListener.get(_feature) != null)
	            return true;
    	}
        return false;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void removeClassifierListener(final IValueChangeListener _listener) {
        if (_listener == null || _mClassifierListener == null)
            return;
        synchronized (_mClassifierListener) {
            // classifier get is already synchronized
            UObjectDisposable disp = _mClassifierListener.get(_listener);
            if (disp != null) {
                _mClassifierListener.remove(disp);
                disp.setDisposed();
                if (_mClassifierListener.isEmpty())
                    _mClassifierListener = null;
                checkNeedsNotification();
            }
        }
    }

    // int setSize = 0;

    /**
     * @inheritDoc
     * @generated not
     */
    @SuppressWarnings("rawtypes")
    @Override
    public IDisposable registerListener(final IValueChangeListener _listener) {
        if (_listener == null)
            return null;
        if (_mClassifierListener == null) {
            _mClassifierListener = new DisposableList();
        }
        synchronized (_mClassifierListener) {
            // first adding into a copy of the set and later replace the set. This way we want to avoid concurrent
            // modification exceptions
//            try {
//            	if (_mClassifierListener.contains(_listener))
//            		return _mClassifierListener.get(_listener);
            	ClassifierDisposable disp = new ClassifierDisposable(this, _listener);
                _mClassifierListener.add(disp);
                checkNeedsNotification();
                return disp;
//            } catch (NullPointerException npe) {
//                ULog.error("mClassifierListener is NULL");
//            }
        }
//        return null;
    }

    public IDisposable registerTreeListener(final ITreeValueChangeListener listener) {
        return registerTreeListener(listener, TreeObserverUtil.OPTIONS_ALL);
    }

    public IDisposable registerTreeListener(final ITreeValueChangeListener listener, ITreeObserverOptions options) {
        if (listener == null)
            return null;
        if (_mTreeListener == null) 
        	_mTreeListener = new DisposableList();
//        UObjectDisposable d = _mTreeListener.get(listener);
//        if (d != null) 
//        	return d;
        UObjectDisposable d = new TreeListenerDisposable(this, listener);

        _mTreeListener.add(d);

        TreeObserverUtil tou = new TreeObserverUtil(listener);
        tou.register(this, options); //this will also register ClassifierListener
        checkNeedsNotification();
        return d;
    }

    public synchronized void removeTreeListener(final ITreeValueChangeListener listener) {
        if (listener == null)
            return;

        if (_mClassifierListener == null)
            return;

        synchronized (_mClassifierListener) {
            List<UObjectDisposable> list = new ArrayList<>(_mClassifierListener);
            ListIterator<UObjectDisposable> iter = list.listIterator();
            while (iter.hasNext()) {
                UObjectDisposable item = iter.next();
                if (item.getListener() instanceof TreeObserverUtil) {
                    TreeObserverUtil tou = (TreeObserverUtil) item.getListener();
                    if (tou.getDelegate() == listener) {
                        tou.remove(this);
                    }
                }
            }
        }

        if (_mTreeListener != null){
            UObjectDisposable disp = _mTreeListener.get(listener);
            if (disp != null) {
                _mTreeListener.remove(disp);
                disp.setDisposed();
                if (_mTreeListener.isEmpty())
                    _mTreeListener = null;
            }
        }

        checkNeedsNotification();
    }
    
    
    private synchronized void checkNeedsNotification() {
    	mHasListener = _mClassifierListener != null || _mFeatureListener != null || _mTreeListener != null;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public Object invoke(final UOperation operation, final Object value) {
        // TODO:
        //
        // * Invokes an operation associated with this object (and defined in its UClassifier)
        // * in case the Operation has more than one parameter, the value shall contain the paramter values as array, in
        // the same
        // * order as the parameters. In case the Operation has no parameter the value may be null or any value (its
        // ignored).
        // * @param operation the operation to be called on this instance
        // * @param the parameter value(s) or null
        // * @return the return value of the function or null if the operation is "void" operation
        //
        throw new UnsupportedOperationException("invoke not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @SuppressWarnings("rawtypes")
    @Override
    public synchronized IDisposable registerListener(final UStructuralFeature _feature, final IValueChangeListener _listener) {
        if (_listener == null || _feature == null)
            return null;

        if (_mFeatureListener == null)
            _mFeatureListener = new HashMap<>();

        // get is already synchronized
        DisposableList list = _mFeatureListener.get(_feature);

        if (list == null)
            _mFeatureListener.put(_feature, list = new DisposableList());

//        UObjectDisposable d = list.get(_listener);
//        if (d != null)
//            return d; //this listener has already been registered
        UObjectDisposable d = new FeatureDisposable(this, _feature, _listener);
        list.add(d);
        checkNeedsNotification();
        return d;
        //}
    }

    /**
     * @generated not
     */
    // private void setUContainingFeature(UStructuralFeature _uContainingFeature) {
    // Notification<UStructuralFeature> notification = basicSet(mUContainingFeature, _uContainingFeature,
    // RuntimePackage.Literals.UObject_uContainingFeature);
    // mUContainingFeature = _uContainingFeature;
    // if (notification != null){
    // dispatchNotification(notification);
    // }
    // }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void removeAllListener(final UStructuralFeature _feature) {
        if (_feature == null || _mFeatureListener == null)
            return;
        synchronized (_mFeatureListener) {
        	DisposableList list = _mFeatureListener.remove(_feature);
        	if (list != null) {
        		for (UObjectDisposable d : list) d.setDisposed();
        		if (_mFeatureListener.isEmpty())
                    _mFeatureListener = null;		
        	}
		}
        checkNeedsNotification();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void removeListener(final UStructuralFeature _feature, final IValueChangeListener _listener) {
        
        if (_feature == null || _listener == null || _mFeatureListener == null)
            return;

        if (_mFeatureListener.containsKey(_feature) == false) {
            return;
        }

        // get is already synchronized
        DisposableList list = _mFeatureListener.get(_feature);
        if (list != null) {
            UObjectDisposable d = list.get(_listener);
            if (d != null) {
                list.remove(d);
                d.setDisposed();
                if (list.isEmpty()) {
                    _mFeatureListener.remove(_feature);
                    checkNeedsNotification();
                }
            }
        }
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void removeListener(final IValueChangeListener _listener) {
        if (_listener == null)
            return;
        removeClassifierListener(_listener);

        // cannot remove feature listener if no list exists
        if (_mFeatureListener != null){
            for (UStructuralFeature f : getUClassifier().getAllStructuralFeatures())
                removeListener(f, _listener);
        }

        checkNeedsNotification();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void removeAllClassifierListener() {
        if (_mClassifierListener == null){
            return;
        }

        synchronized (_mClassifierListener) {
        	//we need to set all disposables to disposed
        	for (UObjectDisposable d : _mClassifierListener)
        		d.setDisposed();
        	_mClassifierListener = null;
        	checkNeedsNotification();
        }
    }

    /**
     * @inheritDoc
     * @generated
     */
    public Object unset(final UStructuralFeature feature) {
        // TODO:
        throw new UnsupportedOperationException("unset not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object uGet(final UStructuralFeature _feature) {
        if (_feature == null)
            throw new NullPointerException("Feature may not be null");
        return _feature.get(this);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public Object uGet(final UStructuralFeature _feature, final int _index) {
        Object obj = uGet(_feature);
        if (obj == null || obj instanceof List == false)
            return null;
        return ((List) obj).get(_index);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void uSet(final UStructuralFeature _feature, final Object _value) {
        if (_feature == null)
            throw new NullPointerException("Feature may not be null");
        _feature.set(this, _value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends UObject> T uComponent(Class<T> clazz, int index) {
        UClass cl = getUClassifier();
        int idx = index;
        UType expectedType = UCoreMetaRepository.findClass(clazz);
        if (expectedType == null)
            throw new NullPointerException("Could not find the UObject-Type of: " + clazz.getName());
        for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
            // first find the candidate
            UType featureType = feature.getType();
            if (expectedType.inherits(featureType)) { // feature is a possible candidate
                Object featureValue = feature.get(this);
                if (featureValue != null) {
                    if (feature.isMany()) {
                        List l = (List) featureValue;
                        for (int i = 0; i < l.size(); i++) {
                            UObject listValue = (UObject) l.get(i); // TODO: is that a valid cast?
                            if (listValue != null && listValue.getUClassifier().inherits(expectedType)) {
                                idx--;
                                if (idx < 0) {
                                    return (T) listValue;
                                }
                            }
                        }
                    } else {
                        UObject ufv = (UObject) featureValue; // TODO: valid cast?
                        if (ufv.getUClassifier().inherits(expectedType)) {
                            idx--;
                            if (idx < 0)
                                return (T) ufv;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UObjectImpl{" + "}";
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void uAdd(final UStructuralFeature _feature, final Object _value) {
        Object list_obj = uGet(_feature);
        if (list_obj != null && list_obj instanceof List) {
            ((List) list_obj).add(_value);
        }
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void uAdd(final UStructuralFeature _feature, final int _index, final Object _value) {
        Object list_obj = uGet(_feature);
        if (list_obj != null && list_obj instanceof List) {
            ((List) list_obj).add(_index, _value);
        }
    }

    /**
     * This method creates a notification if required and more important, handles the internal references, that can be
     * accessed with getUContainer() and getUContainingFeature() <br>
     * An notification will be created, if a call to needNotification(...) returns true. The notification should be
     * dispatched, using the method: dispatchNotification(...), by the caller <br>
     * Depending on the type of the features aggregation (e.g. ASSOCIATION, AGGREGATION, COMPOSITION, PROPERTY) this
     * methods, changes the container and containing feature of newValue and oldValue - <b>COMPOSITION</b> : if newValue
     * already has an container, it will be removed from the old container NOTE: this may result in an invalid model,
     * according to specified mulitplicities. This Object will be set as new container of newValue, the feature will be
     * the new value of containingFeasture. On the other hand, those values will be changed for the oldValue. If the
     * oldValue has been also assigned to an ASSOCIATION or PROPERTY feature, these features, will become the new
     * container for oldValue - <b>ASSOCIATION, AGGREGATION, PROPERTY</b> : if feature is a reference (e.g. points to an
     * complex type) newValue will remember that it is assigned to this instance (to be a possible replacement if the
     * container changes; see above). oldValue on the other hand will forgot that it has been assigned to this object
     * 
     * @param oldValue
     *            previous value of feature
     * @param newValue
     *            new value of the feature
     * @param feature
     *            the feature to assign the newValue
     * @return a notification that should be dispatched (using dispatchNotification) by the caller, or null if no
     *         notification is required; e.g. needNotification(...) returns false
     */
    protected <T> Notification<T> basicSet(final T oldValue, T newValue, UStructuralFeature feature) {
        if (feature != null && feature.isAttribute() == false
                && feature.getAggregation() != UAssociationType.AGGREGATION) {
            // we can safely cast, since isAttribute() returns false
            _internalGetInternalUObject().basicSet((UObject) oldValue, (UObject) newValue, this, feature, -1);
        }
        if (needNotification(feature))
            return new NotificationImpl<T>(oldValue, newValue, this, feature, NotificationType.SET);
        return null;
    }

    /**
     * Creates a new notification and dispatch it to all registered listener
     * 
     * @param oldValue
     * @param newValue
     * @param feature
     * @param type
     * @return the newValue parameter to support a short calling sequence: mX = notify(oldX, newX, feature, type);
     */
    protected <T> T notify(final T oldValue, T newValue, UStructuralFeature feature, NotificationType type) {
        if (oldValue == newValue)
            return newValue;
        final Notification<T> notification = new NotificationImpl<T>(oldValue, newValue, this, feature, type);
        dispatchNotification(notification);
        return newValue;
    }

    public static long dispatchedNotificationCounter = 0;
    /**
     * Sends an notification to all interested listener (e.g. listener, that has been registered for either an specific
     * feature or for changes in any feature) notification order is: - FIFO on specific feature listener - FIFO on
     * general object listener
     * 
     * @param notification
     *            notification to be dispatched to all interested listener
     */
    public <T> void dispatchNotification(Notification<T> notification) {
    	dispatchedNotificationCounter++;
        // first notify the feature
        if (_mFeatureListener != null && notification.getFeature() != null) {
            DisposableList _list = _mFeatureListener.get(notification.getFeature());
            if (_list != null) {
            	ArrayList<UObjectDisposable> list = new ArrayList<>(_list);
                for (UObjectDisposable l : list) {
                    try {
                        ((FeatureDisposable)l).listener.onValueChange(notification);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // in the second pass notify those listener, that shall be notified about each feature
        if (_mClassifierListener != null) {
          synchronized (_mClassifierListener) {
            ArrayList<UObjectDisposable> list = new ArrayList<>(_mClassifierListener);
            for (UObjectDisposable l : list) {
                try {
                    ((ClassifierDisposable)l).listener.onValueChange(notification);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

    @Override
    public synchronized Iterable<UObject> getContentIterator() {
        return new UContentIterator(this, getUClassifier()); // (UAssociationType.COMPOSITION);
    }

    @Override
    public Iterable<UObject> getContentIterator(UAssociationType type) {
        return new FeatureIterator(this, getUClassifier(), type);
    }

    public Iterable<UObject> getContentIterator(UAssociationType... type) {
        return new FeatureIterator(this, getUClassifier(), type);
    }

    //////////////////////////////////////////////////////////////////////////
    // public but internal methods
    // those methods, need to be public, since they are called from external
    // classes, but should only be used within this framework
    //////////////////////////////////////////////////////////////////////////

    public InternalUObject _internalGetInternalUObject() {
        if (mInternalUObject == null)
            mInternalUObject = new InternalUObject(this);
        return mInternalUObject;
    }

    public boolean _hasContainer() {
        if (mInternalUObject == null)
            return false;
        return mInternalUObject.getContainer() != null;
    }

    // public void internalRemoveFromContainer() { //called by UContainmentList
    //
    // }
    //
    // public void internalSetContainer(UObject container, UStructuralFeature feature) {//called by UContainmentList
    //// setUContainer(container);
    //// setUContainingFeature(feature);
    // }
    //
    // public void _internalUnsetContainerAndFeature() {
    // mInternalUObject.unsetContainerAndFeature();
    // }
    //
    // public void _internalRememberContainer(UObjectImpl container, UStructuralFeature feature) {
    // mInternalUObject.rememberContainer(container, feature);
    // }

    //////////////////////////////////////////////////////////////////
    // Property Handling //
    //////////////////////////////////////////////////////////////////

    private IProperty _getOrCreateProperty(QualifiedName qn, String desc, boolean editable, boolean create) {
        // returns one of the top level properties for this element, based on the qualified name. if the property does
        // not yet exits, it will be created
        if (qn == null || qn.isEmpty())
            throw new UnsupportedOperationException("require a valid property name");
        if (mProperties == null) {
            if (create)
                mProperties = new HashMap<>();
            else
                return null; // there are no properties available
        }

        IProperty prop = mProperties.get(qn.firstSegment());
        if (prop == null) {
            if (create) {
                ULog.trace("create new property: " + qn.firstSegment());
                prop = new GenericProperty(qn.firstSegment(), desc, editable);
                mProperties.put(qn.firstSegment(), prop);
            } else
                return null;
        }
        if (qn.numSegments() == 1)
            return prop;
        return _getOrCreateProperty(prop, qn.removeSegmentsFromStart(1), desc, editable, create);
    }

    private IProperty _getOrCreateProperty(IProperty parent, QualifiedName qn, String desc, boolean editable,
            boolean create) {
        // returns or creates a sub property of the parent property
        if (qn == null || qn.isEmpty())
            throw new UnsupportedOperationException("require a valid property name");
        if (parent == null || parent instanceof AbstractProperty == false)
            throw new NullPointerException("Require valid parent property");
        String n = qn.firstSegment();
        IProperty prop = null;
        if (parent.getSubProperties() != null) {
            for (Object sub_obj : parent.getSubProperties()) {
                IProperty<?> sub = (IProperty) sub_obj;
                if (sub.getName().equals(n)) {
                    prop = sub;
                    break;
                }
            }
        }
        if (prop == null) { // could not find an existing child, thus we create a new one
            if (create) {
                ULog.trace("create new sub property: " + n + " in parent: " + parent.getName());
                prop = new GenericProperty(qn.firstSegment(), desc, editable);
                ((AbstractProperty) parent).addChild(prop);
            } else
                return null;
        }
        if (qn.numSegments() == 1)
            return prop;
        return _getOrCreateProperty(prop, qn.removeSegmentsFromStart(1), desc, editable, create);// recursive call but
                                                                                                 // with shorter
                                                                                                 // qualified name
    }

    @Override
    public boolean hasProperty(String qualifiedPropertyName) {
        return getProperty(qualifiedPropertyName) != null;
    }
    
    @Override
    public void addProperty(IProperty property) {
        if (property == null || property.getName() == null || property.getName().isEmpty())
            throw new UnsupportedOperationException("require a valid property name");
        if (mProperties == null) {
            mProperties = new HashMap<>();
        }
        mProperties.put(property.getName(), property);
    }

    @Override
    public IProperty addProperty(String qualifiedPropertyName, String description) {
        return addProperty(qualifiedPropertyName, description, true);
    }

    @Override
    public IProperty addProperty(String qualifiedPropertyName, String description, boolean editable) {
        return _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(qualifiedPropertyName, "\\."), description,
                editable, true);
    }

    @Override
    public IProperty removeProperty(IProperty property) {
        // returns null, if the property has not been removed, otherwise the property is returned
        if (property == null)
            return null;
        if (property.getParentProperty() == null) {
            IProperty removed = mProperties.remove(property.getName());
            if (removed != null) {
                if (mProperties.isEmpty())
                    mProperties = null; // remove the instance to spare the memory and to speed up lookup and copies
                return null;
            }
        } else {
            IProperty parent = property.getParentProperty();
            if (parent instanceof AbstractProperty) {
                ((AbstractProperty) parent).removeChild(property);
                if (parent.getName().equals(parent.getDescription()) && parent.getSubProperties().isEmpty()) {
                    // we can also remove the parent (has been created automatically)
                    removeProperty(parent);
                }
                return property;
            }
        }
        return null;
    }

    @Override
    public Collection<IProperty> getAllProperties() {
        if (mProperties == null)
            return null;
        return mProperties.values();
    }

    @Override
    public IProperty getProperty(String qualifiedPropertyName) {
        return _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(qualifiedPropertyName, "\\."), null, false,
                false);
    }

    private void _setPropertyValue(String qualifiedPropertyName, Object value) {
        IProperty prop = _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(qualifiedPropertyName, "\\."),
                qualifiedPropertyName, true, true);
        assert (prop != null);
        prop.setValue(value);
    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, String value) {
        _setPropertyValue(qualifiedPropertyName, value);
    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, Number value) {
        _setPropertyValue(qualifiedPropertyName, value);
    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, boolean value) {
        _setPropertyValue(qualifiedPropertyName, value);
    }

    private Object _getPropertyValue(String qualifiedPropertyName) {
        IProperty prop = _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(qualifiedPropertyName, "\\."), qualifiedPropertyName, true, false);
        assert (prop != null);
        return prop.getValue();
    }

    @Override
    public String getPropertyValueAsString(String qualifiedPropertyName) {
        return _getPropertyValue(qualifiedPropertyName).toString();
    }

    @Override
    public <T extends Number> T getPropertyValueAsNumber(String qualifiedPropertyName) {
        return (T) _getPropertyValue(qualifiedPropertyName);
    }

    @Override
    public boolean getPropertyValueAsBoolean(String qualifiedPropertyName) {
        return (boolean) _getPropertyValue(qualifiedPropertyName);
    }
    
    
    public IDisposable oberserveProperty(String fqPropName, PropertyChangeListener pcl) {
    	IProperty prop = _getOrCreateProperty(QualifiedNameImpl.createWithRegEx(fqPropName, "\\."), fqPropName, true, false);
    	if (prop == null)
    		return null;
    	return new PropertyDisposable(prop, pcl); //the disposable will also register the listener
    }

}
