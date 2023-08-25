package de.emir.tuml.ucore.runtime.access;

import de.emir.tuml.ucore.runtime.UObject;

public interface IFeatureGetter {

    Object get(UObject instance);
}
