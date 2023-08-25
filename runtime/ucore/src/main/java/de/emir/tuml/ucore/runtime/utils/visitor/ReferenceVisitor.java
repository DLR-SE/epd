package de.emir.tuml.ucore.runtime.utils.visitor;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UVisitor;

public abstract class ReferenceVisitor extends AbstractVisitor implements UVisitor {
    @Override
    public boolean shouldVisit(UObject parent, UStructuralFeature feature) {
        return feature.isReference();
    }
}
