package de.emir.tuml.ucore.runtime.utils.visitor;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UVisitor;

/**
 * Abstract visitor class, with stub implementation.
 *
 */
public abstract class AbstractVisitor implements UVisitor {
    /**
     * @inheritDoc
     */
    @Override
    public boolean beginObject(UObject obj, UClass cl) {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void endObject(UObject obj, UClass cl) {
    }

    /**
     * @inheritDoc
     */
    @Override
    public void beginList(UObject root, UStructuralFeature feature) {
    }

    /**
     * @inheritDoc
     */
    @Override
    public void endList(UObject root, UStructuralFeature feature) {
    }

}
