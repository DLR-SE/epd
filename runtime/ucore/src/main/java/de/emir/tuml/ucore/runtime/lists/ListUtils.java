package de.emir.tuml.ucore.runtime.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

public class ListUtils {

    public static <T extends UObject> List<T> asList(UObject owner, UStructuralFeature owningFeature) {
        return new UContainmentList<T>(owner, owningFeature);
    }

    public static <T extends UObject, U extends T> List<T> asList(UObject owner, UStructuralFeature owningFeature,
            U... a) {
        List<T> al = new UContainmentList<T>(owner, owningFeature);
        if (a != null)
            al.addAll(Arrays.asList(a)); // can't use Arrays.asList directly, since it creates a unmodifiable list.
        return al;
    }

    public static <T> List<T> asList(T... a) {
        ArrayList<T> al = new ArrayList<T>();
        al.addAll(Arrays.asList(a)); // can't use Arrays.asList directly, since it creates a unmodifiable list.
        return al;
    }
}
