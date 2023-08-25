package de.emir.tuml.ucore.runtime.adapter;

import java.util.HashSet;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class UModelAdapter {

    public static class AdapterOptions {
        public AdapterOptions(Class<?> clazz) {
            this(UCoreMetaRepository.getClassifier(clazz));
        }

        public AdapterOptions(UClassifier classifier) {
            expectedClassifier = classifier;
        }

        public final UClassifier expectedClassifier;
        public int maximumDepth = 5;

        public boolean expandCompositions = true;
        public boolean expandComplexProperties = true;
        public boolean expandAssociations = true;
        public boolean expandAggregations = false;
    }

    public interface IObjectCallback {

        boolean accept(Object value, UObject parent, UStructuralFeature feature, int listIndex, int depth);

    }

    public void visitModel(UObject root, IObjectCallback cb, AdapterOptions options) {
        HashSet<Object> closedSet = new HashSet<>();
        collectElements(root, cb, closedSet, options, options.maximumDepth);
    }

    public void disposeModel(UObject root) {
        // for now we do nothing, but may inherited classes can use this method to free listener or some other stuff
    }

    protected void collectElements(UObject parent, IObjectCallback cb, HashSet<Object> closedSet,
            AdapterOptions options, int depth) {
        if (depth == 0)
            return;

        UClass cl = parent.getUClassifier();
        for (UStructuralFeature f : cl.getAllStructuralFeatures()) {
            if (!expandFeature(f, options))
                continue;
            Object obj = f.get(parent);
            if (obj == null)
                continue;
            if (!closedSet.add(obj))
                continue;// we allready checked this element
            if (f.isMany()) {
                List l = (List) obj;
                for (int i = 0; i < l.size(); i++) {
                    Object le = l.get(i);
                    if (le == null)
                        continue;
                    if (!closedSet.add(le))
                        continue;
                    if (le instanceof UObject) {
                        UClass ucl = ((UObject) le).getUClassifier();
                        if (ucl.inherits(options.expectedClassifier))
                            notifyCallback(cb, le, parent, f, i, depth);

                        collectElements((UObject) le, cb, closedSet, options, depth - 1);
                    }
                }
            } else {
                if (obj instanceof UObject) {
                    UClass ucl = ((UObject) obj).getUClassifier();
                    if (ucl.inherits(options.expectedClassifier)) {
                        notifyCallback(cb, obj, parent, f, -1, depth);
                    }
                    collectElements((UObject) obj, cb, closedSet, options, depth - 1);
                }
            }
        }
    }

    private void notifyCallback(IObjectCallback callback, Object instance, UObject parent, UStructuralFeature feature,
            int listIndex, int depth) {
        callback.accept(instance, parent, feature, listIndex, depth);
    }

    protected boolean expandFeature(UStructuralFeature f, AdapterOptions options) {
        switch (f.getAggregation()) {
        case AGGREGATION:
            return options.expandAggregations;
        case ASSOCIATION:
            return options.expandAssociations;
        case COMPOSITION:
            return options.expandCompositions;
        case PROPERTY:
            return options.expandComplexProperties;
        }
        return false;
    }
}
