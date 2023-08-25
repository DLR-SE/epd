package de.emir.rcp.properties.impl;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class UCoreListProperty extends UCoreProperty {

	public UCoreListProperty(UObject instance, UStructuralFeature feature) {
		super(instance, feature, -1);
	}
	
	public UCoreListProperty(Pointer ptr){
		super(ptr);
	}

}
