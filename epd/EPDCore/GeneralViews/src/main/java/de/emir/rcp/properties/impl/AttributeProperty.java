package de.emir.rcp.properties.impl;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AttributeProperty extends UCoreProperty {

	public AttributeProperty(UObject instance, UStructuralFeature feature, int listIndex) {
		super(instance, feature, listIndex);
	}

	public AttributeProperty(Pointer ptr) {
		super(ptr);
	}

}
