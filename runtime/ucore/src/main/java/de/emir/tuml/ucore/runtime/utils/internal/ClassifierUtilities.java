package de.emir.tuml.ucore.runtime.utils.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class ClassifierUtilities {

    private boolean mDirty = true;
    private HashMap<UClassifier, HashSet<UClassifier>> mInheritMap = new HashMap<>();

    /**
     * notify the utility class, that something within the UCoreMetaRepository has changed
     */
    public void markDirty() {
        mDirty = true;
    }

    /**
     * returns all classifiers, that inherit from parent
     * 
     * @param parent
     *            the parent classifier
     * @param instantiableOnly
     *            if set to true, only those classifiers will be returned, that can be instantiated
     * @return
     */
    public synchronized Collection<UClassifier> getClassesInheritFrom(UClassifier parent, boolean instantiableOnly) {
        if (mDirty || mInheritMap.containsKey(parent) == false) {
            HashSet<UClassifier> set = new HashSet<UClassifier>();
            for (UClassifier cl : UCoreMetaRepository.getAllClassifier()) {
                if (cl.inherits(parent)) {
                    if (instantiableOnly) {
                        if (cl instanceof UInterface)
                            continue;
                        if (cl instanceof UClass && ((UClass) cl).getAbstract())
                            continue;
                    }
                    set.add(cl);
                }
            }

            mInheritMap.put(parent, set);
        }
        return mInheritMap.get(parent);
    }

}
