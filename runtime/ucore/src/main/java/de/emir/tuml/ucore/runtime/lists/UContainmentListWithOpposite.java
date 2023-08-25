package de.emir.tuml.ucore.runtime.lists;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;

public class UContainmentListWithOpposite<T extends UObject> extends UContainmentList<T> {

    private UStructuralFeature mOppositeFeature;

    public UContainmentListWithOpposite(UObject owner, UStructuralFeature feature, UStructuralFeature oppositeFeature) {
        super(owner, feature);
        if (oppositeFeature == null)
            throw new NullPointerException("Opposite Feature may not be null");
        mOppositeFeature = oppositeFeature;
    }

    @Override
    protected boolean _internalAdd(int index, T element) {
        if (super._internalAdd(index, element)) {
            // mOppositeFeature.uSet(getOwner());
            PointerOperations.assign(element, mOppositeFeature, getOwner());
            return true;
        }
        return false;
    }

}
