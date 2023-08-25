package de.emir.tuml.ucore.runtime.utils.internal.lists;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;

public class LazyReferenceContainmentList<T extends UObject> extends UContainmentList<T> {
    public interface FeatureResolver {
        UStructuralFeature getFeature();
    }

    private FeatureResolver mResolver;

    public LazyReferenceContainmentList(UObject owner, FeatureResolver resolver) {
        super(owner);
        mResolver = resolver;
    }

    @Override
    protected boolean _internalAdd(int index, T element) {
        if (getOwningFeature() == null) {
            UStructuralFeature f = mResolver.getFeature();
            if (f != null) {
                setOwningFeature(f);
            }
        }
        return super._internalAdd(index, element);
    }

}
