package de.emir.tuml.ucore.runtime.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

public class FeatureIterator implements Iterator<UObject>, Iterable<UObject> {

    protected UObject mObject;
    protected List<UStructuralFeature> mFeatures;
    protected int mFeatureIndex;

    protected UStructuralFeature mCFeature;
    protected UObject mCObject;

    @SuppressWarnings("rawtypes")
    protected List mCList;
    protected int mCListIndex = 0;

    public FeatureIterator(UObject obj, UClass cl, UAssociationType... types) {
        mObject = obj;
        mFeatures = new ArrayList<>();
        for (UAssociationType t : types)
            mFeatures.addAll(cl.getAllStructuralFeatures(t));
    }

    @Override
    public boolean hasNext() {
        if (mCList != null) {
            if (mCListIndex < mCList.size()) {
                mCObject = (UObject) mCList.get(mCListIndex++);
                return true;
            } else {
                mCList = null;
                mCObject = null;
                mCListIndex = 0;
            }
        }
        for (; mFeatureIndex < mFeatures.size(); mFeatureIndex++) {
            mCFeature = mFeatures.get(mFeatureIndex);
            Object obj = mCFeature.get(mObject);
            if (obj != null) {
                if (obj instanceof List) {
                    mCList = (List) obj;
                    mCListIndex = 0;
                    if (mCList.isEmpty()) {
                        mCObject = null;
                        mCList = null;
                        continue;
                    } else {
                        mCObject = (UObject) mCList.get(mCListIndex++);
                    }
                } else if (obj instanceof UObject)
                    mCObject = (UObject) obj;
            }
            if (mCObject != null) {
                // mFeatureIndex++;
                return true;
            }
        }
        return false;
    }

    @Override
    public UObject next() {
        if (mCList == null)
            mFeatureIndex++;
        return mCObject;
    }

    @Override
    public Iterator<UObject> iterator() {
        return this;
    }
}
