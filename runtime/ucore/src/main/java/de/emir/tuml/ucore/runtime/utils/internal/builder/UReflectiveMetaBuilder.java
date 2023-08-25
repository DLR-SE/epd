package de.emir.tuml.ucore.runtime.utils.internal.builder;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

public class UReflectiveMetaBuilder extends UMetaBuilder {

    public UClass buildClass(Class<? extends UObject>... clazzes) {
        UClassifier cl = null;
        for (Class<? extends UObject> clazz : clazzes) {
            cl = UCoreMetaRepository.getClassifier(clazz);
        }
        if (cl instanceof UClass)
            return (UClass) cl;
        return null;
    }

    public UEnum buildEnumeration(Class<?> clazz) {
        UClassifier cl = UCoreMetaRepository.getClassifier(clazz);
        if (cl != null && cl instanceof UEnum)
            return (UEnum) cl;
        return null;
    }

    public UInterface buildInterface(Class<?> clazz) {
        UClassifier cl = UCoreMetaRepository.getClassifier(clazz);
        if (cl != null && cl instanceof UInterface)
            return (UInterface) cl;
        return null;
    }

}
