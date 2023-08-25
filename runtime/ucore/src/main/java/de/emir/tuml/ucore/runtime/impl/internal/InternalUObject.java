package de.emir.tuml.ucore.runtime.impl.internal;

import java.util.ArrayList;
import java.util.HashMap;

import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.NotificationImpl;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;

public class InternalUObject {

    private static final int MAX_CONTAINER_MEMORY = 1;

    private UObjectImpl mInstance;
    private UStructuralFeature mContainingFeature;
    private UObject mContainer;

    static class ContainerStorage {
        // do not use the FeaturePointer, due to the bigger memory print, induced through the inheritance of
        // FeaturePointer from UObject
        public UObject container;
        public UStructuralFeature feature;
        public int index;

        public ContainerStorage(final UObject c, final UStructuralFeature f, final int i) {
            container = c;
            feature = f;
            index = i;
        }

		public synchronized void set(final UObject c, final UStructuralFeature f, final int i) {
			container = c; feature = f; index = i;
		}
    }

//    private HashMap<UAssociationType, ArrayList<ContainerStorage>> mPossibleContainers;
    private ContainerStorage[] mContainerOptions = null;

    public InternalUObject(UObjectImpl uObjectImpl) {
        mInstance = uObjectImpl;
    }

    public void basicSet(UObject oldValue, UObject newValue, UObjectImpl container, UStructuralFeature feature) {
        basicSet(oldValue, newValue, container, feature, -1);
    }

    public synchronized void basicSet(UObject oldValue, UObject newValue, UObjectImpl container,
            UStructuralFeature feature, int listIndex) {
        // general: we know that the feature is a reference, otherwise this method would not be called
        if (feature != null && feature.getAggregation() == UAssociationType.AGGREGATION)
            return;
        // remove the container and containing feature from the old value, and may change it to another value @see
        // UObjectImpl.basicSet(...)
        if (oldValue != null && oldValue instanceof UObjectImpl)
            if (oldValue.getUContainer() == mInstance)
                ((UObjectImpl) oldValue)._internalGetInternalUObject().removeFromContainer(true, newValue);

        if (newValue != null) {
            if (newValue instanceof UObjectImpl) {
            	InternalUObject iuobj = ((UObjectImpl)newValue)._internalGetInternalUObject();
            	UAssociationType aggType = feature.getAggregation();
            	
            	
            	if (aggType != UAssociationType.COMPOSITION) //if it's a composition it will be set for sure and we do not need to remember it
            		iuobj.rememberContainer(container, feature, -1, aggType);
            	
                // remove the newValue from its previous container
                if (aggType == UAssociationType.COMPOSITION) {
                    iuobj.removeFromContainer(false, newValue);
                    iuobj.setContainer(container, feature);
                } else if (iuobj.getContainer() == null) {
                    iuobj.setContainer(container, feature);
                }
            }
        }
    }

    private void removeFromContainer(boolean assignNew, UObject newValue) {
        removeFromContainer(mContainer, mContainingFeature, assignNew, newValue);
    }

    public void removeFromContainer(UObject newValue) {
        removeFromContainer(mContainer, mContainingFeature, true, newValue);
    }

    public synchronized void removeFromContainer(UObject container, UStructuralFeature feature, boolean assignNew,
            UObject newValue) {
        if (container == null)
            return;
        // check for newValue != null, otherwhise this will lead to an endless loop
        // no need to update if we still want to assign a new value in the next steps
        UAssociationType assoType = feature.getAggregation();
        if (!assignNew && newValue != null && feature != null
                && assoType == UAssociationType.COMPOSITION && feature.isMany() == false) {
            PointerOperations.assign(container, feature, null);
        }
        // remove the container and feature from the list of possible containers
        removePossibleContainers(container, feature, assoType);
        mContainer = null;
        mContainingFeature = null;

        if (assignNew) {
            ContainerStorage nextContainer = getNextContainer();
            if (nextContainer != null) {
                setContainer(nextContainer.container, nextContainer.feature);
            }
        }

    }

    private void removePossibleContainers(UObject container, UStructuralFeature feature, UAssociationType assoType) {
    	if (mContainerOptions == null) 
    		return ;
    	int idx = getAggregationTypeIndex(assoType);
    	if (idx < 0)
    		return ;
    	ContainerStorage cs = mContainerOptions[idx];
    	if (cs == null)
    		return ;
    	if (cs.container == container && cs.feature == feature) {
    		mContainerOptions[idx] = null;
    		if (mContainerOptions[0] == mContainerOptions[1] && mContainerOptions[1] == mContainerOptions[2])
    			mContainerOptions = null; //faster fail
    	}
    	
//        if (mPossibleContainers == null)
//            return;
//        ArrayList<ContainerStorage> list = mPossibleContainers.get(feature.getAggregation());
//        synchronized (mPossibleContainers) {
//            if (list != null && list.isEmpty() == false) {
//                int idx = -1;
//                for (int i = 0; i < list.size(); i++) {
//                    ContainerStorage fp = list.get(i);
//                    if (fp != null && fp.feature == feature) {
//                        if (fp.container == container) {
//                            idx = i;
//                            break;
//                        }
//                    }
//                }
//                if (idx >= 0) {
//                    list.remove(idx);
//                }
//            }
//        }
    }
    private static int getAggregationTypeIndex(final UAssociationType t) {
    	switch (t) {
		case ASSOCIATION: 	return 0;
		case AGGREGATION: 	return 1;
		case PROPERTY 	: 	return 2;
		}
    	return -1;
    }

    private ContainerStorage getNextContainer() {
    	if (mContainerOptions == null)
    		return null;
    	//aggregationType is not ordererd in the correct order thus we have to check the correct order manually
    	if (mContainerOptions[0] != null)
    		return mContainerOptions[0]; //ASSOCIATION
    	else if (mContainerOptions[2] != null) //PROPERTY
    		return mContainerOptions[2];
    	else if (mContainerOptions[1] != null)
    		return mContainerOptions[1]; //AGGREGATION
    	return null;

//        ContainerStorage fp = getNextContainer(UAssociationType.ASSOCIATION);
//        if (fp == null)
//            fp = getNextContainer(UAssociationType.PROPERTY);
//        if (fp == null)
//            fp = getNextContainer(UAssociationType.AGGREGATION);
//        return fp;
    }

//    private ContainerStorage getNextContainer(UAssociationType aggregation) {
//        if (mPossibleContainers == null)
//            return null;
//        ArrayList<ContainerStorage> list = mPossibleContainers.get(aggregation);
//        if (list == null || list.isEmpty())
//            return null;
//        return list.get(0);
//    }
    public void rememberContainer(UObjectImpl container, UStructuralFeature feature, int listIndex, UAssociationType aggregationType) {
    	int idx = getAggregationTypeIndex(aggregationType);
    	if (idx < 0) 
    		return ; //we do not remember COMPOSITE's they are set directly
    	if (mContainerOptions == null)
    		mContainerOptions = new ContainerStorage[3]; //one for each aggregation type expect association
    	
    	if (mContainerOptions[idx] == null)
    		mContainerOptions[idx] = new ContainerStorage(container, feature, listIndex);
    }


    public void setContainer(UObject owner, UStructuralFeature owningFeature) {
        mContainer = owner;
        mContainingFeature = owningFeature;
        if (mInstance.needNotification(RuntimePackage.Literals.UObject_uContainer)  || mInstance.needNotification(RuntimePackage.Literals.UObject_uContainingFeature)) {
            mInstance.dispatchNotification(new NotificationImpl<UObject>(null, owner, mInstance, RuntimePackage.Literals.UObject_uContainer, NotificationType.SET));
        }
    }

    public UStructuralFeature getContainingFeature() {
        return mContainingFeature;
    }

    public UObject getContainer() {
        return mContainer;
    }

}
