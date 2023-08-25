package de.emir.tuml.ucore.runtime.utils.visitor;

import java.util.HashSet;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UVisitor;

/**
 * Abstract visitor class, that chaches the already visited objects (inside beginObject) and visit each object only
 * once.
 * 
 * @author sschweigert
 *
 */
public abstract class AbstractVisitor implements UVisitor {
    private HashSet<UObject> mVisited = new HashSet<UObject>();

    @Override
    public boolean beginObject(UObject obj, UClass cl) {
        return mVisited.add(obj);
    }

    @Override
    public void endObject(UObject obj, UClass cl) {
    }

    @Override
    public void beginList(UObject root, UStructuralFeature feature) {
    }

    @Override
    public void endList(UObject root, UStructuralFeature feature) {
    }
}
