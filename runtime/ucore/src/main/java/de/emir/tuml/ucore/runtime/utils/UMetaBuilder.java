package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.utils.internal.builder.UManualMetaBuilder;
import de.emir.tuml.ucore.runtime.utils.internal.builder.UReflectiveMetaBuilder;

public abstract class UMetaBuilder {

    private static UManualMetaBuilder sManualBuilder = new UManualMetaBuilder();
    private static UReflectiveMetaBuilder sReflectiveBuilder = new UReflectiveMetaBuilder();

    public static UManualMetaBuilder manual() {
        return sManualBuilder;
    }

    public static UReflectiveMetaBuilder reflective() {
        return sReflectiveBuilder;
    }

}
