package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

import java.util.*;

public class UVisitorUtil {

    /**
     * Visit an UObject and its entire tree of UStructuralFeatures using a Visitor.
     * @param root root object to visit.
     * @param visitor visitor to use.
     */
    public static void visit(UObject root, UVisitor visitor) {
        visit(root, visitor, new HashSet<>());
    }

    /**
     * Visit an UObject and its entire tree of UStructuralFeatures using a Visitor. Skip Objects in visited and add visited UObjects to visitedUObjects.
     * @param root object to visit.
     * @param visitor to use.
     * @param visitedUObjects is the Collection for already visited UObjects.
     */
    public static void visit(UObject root, UVisitor visitor, Collection<UObject> visitedUObjects) {
        if (root == null) { // nothing to visit
            return;
        }

        Deque<UObject> toVisit = new ArrayDeque<>();
        toVisit.add(root);

        while (!toVisit.isEmpty()) {
            root = toVisit.pop();

            // check if root has already been visited
            if (!visitedUObjects.add(root)) {
                continue; // already visited
            }

            UClass cl = root.getUClassifier();
            if (visitor.beginObject(root, cl)) {
                for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
                    if (!visitor.shouldVisit(root, feature)) {
                        continue;
                    }

                    Object valueOfFeature = feature.get(root);
                    if (valueOfFeature == null) {
                        continue;
                    }

                    if (feature.isMany()) {
                        if (!(valueOfFeature instanceof List<?>)) {
                            throw new UnsupportedOperationException("isMany feature must implement List");
                        }

                        List<Object> listOfValues = (List<Object>) valueOfFeature;
                        visitor.beginList(root, feature);
                        for (int idx = 0; idx < listOfValues.size(); idx++) {
                            Object listValue = listOfValues.get(idx);
                            visitor.visit(root, feature, idx, listValue);
                            if (listValue instanceof UObject uValue) {
                                toVisit.add(uValue);
                            }
                        }
                        visitor.endList(root, feature);

                    } else {
                        visitor.visit(root, feature, -1, valueOfFeature);
                        if (feature.isReference() && valueOfFeature instanceof UObject toVisitObject) {
                            toVisit.add(toVisitObject);
                        }
                    }
                }
                visitor.endObject(root, cl);
            }
        }
    }
}
