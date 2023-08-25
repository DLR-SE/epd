package de.emir.rcp.properties.impl;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AssociationProperty extends UCoreProperty {
	
	private Object mValueHint = null;

	public AssociationProperty(UObject instance, UStructuralFeature feature, int listIndex) {
		super(instance, feature, listIndex);
	}
	public AssociationProperty(UObject instance, UStructuralFeature feature) {
		super(instance, feature, -1);
	}
	
	public AssociationProperty(Pointer ptr) {
		super(ptr);
	}

	public UAssociationType getAssociationType(){
		return getPointer().getPointedFeature().getAggregation();
	}

	
	public Object getValueHint() { return mValueHint; }
	public void setValueHint(Object obj) { mValueHint = obj; }
	
	@Override
	public IProperty copy() {
		AssociationProperty ap = new AssociationProperty(getPointer());
		ap.mValueHint = mValueHint;
		return ap;
	}
}
