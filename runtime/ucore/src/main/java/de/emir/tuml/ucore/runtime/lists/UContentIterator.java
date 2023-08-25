package de.emir.tuml.ucore.runtime.lists;

import java.util.List;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;

public class UContentIterator extends FeatureIterator {

    public UContentIterator(UObject obj, UClass cl) {
        super(obj, cl, UAssociationType.PROPERTY);
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
                mFeatureIndex++;
            }
        }
        for (; mFeatureIndex < mFeatures.size(); mFeatureIndex++) {
            mCFeature = mFeatures.get(mFeatureIndex);
            Object obj = mCFeature.get(mObject);
            if (obj instanceof UObject && ((UObject) obj).getUContainer() != mObject)
                continue;
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

}
