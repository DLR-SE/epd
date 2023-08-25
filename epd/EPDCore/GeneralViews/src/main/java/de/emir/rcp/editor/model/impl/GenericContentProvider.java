package de.emir.rcp.editor.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.editor.model.IContentProvider;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class GenericContentProvider implements IContentProvider
{
	private boolean mExpandCompositions = true;
	private boolean mExpandComplexProperties = true;
	private boolean mExpandAssociations = true;
	
	private boolean mExpandAttributes = false;
	private boolean mExpandAggregations = false;
	
	@Override
	public boolean hasChildren(Pointer object) {
		if (object instanceof UObject){
            UClass cl = ((UObject)object).getUClassifier();
			for (UStructuralFeature feature : cl.getAllStructuralFeatures()){
				Object value = feature.get((UObject)object);
				if (value != null){
					if (isChild(feature, value))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks an object shall appear as a separate element in the underlying view structure
	 * @param feature the structural feature of the parent, that is currently expanded
	 * @param value the value of the feature
	 * @return true, if the value shall be expanded into a separated element
	 */
	protected boolean isChild(UStructuralFeature feature, Object value) {
		switch(feature.getAggregation()){
		case AGGREGATION:
			return mExpandAggregations;
		case ASSOCIATION:
			return mExpandAssociations;
		case COMPOSITION:
			return mExpandCompositions;
		case PROPERTY:
			if (feature.isAttribute())
				return mExpandAttributes;
			return mExpandComplexProperties;
		}
		return false;
	}

	@Override
	public List<Pointer> getChildren(Pointer ptr) {
		if (ptr == null || ptr.isValid() == false)
			return null;
		UType type = ptr.getType();
		if (type instanceof UClassifier == false)
			return null; //primitive values does not have any children
		ArrayList<Pointer> children = new ArrayList<>(); //stores the result
		UClassifier cl = (UClassifier)type;
		//for faster access, we do use the underlaying uobject instead of the pointer values
		//this should be faster, at least if the pointer is assumed to be a pointer chain, from the root
		//that would iterate from root each time
		Object obj = ptr.getValue();
		if (obj instanceof UObject) {
			UObject uobj = (UObject)ptr.getValue(); //cast should be safe since it's an UClassifer
			for (UStructuralFeature feature : cl.getAllStructuralFeatures()){
				Object value = feature.get(uobj);
				if (value != null){
					if (isChild(feature, value)){ //decide upon settings for this provider if we add it
						if (feature.isMany()){
							List<?> l = (List<?>)value;
							for (int i = 0; i < l.size(); i++){
								children.add(PointerOperations.createChain(ptr, feature, i));
							}
						}else
							children.add(PointerOperations.createChain(ptr, feature));
					}
				}
			}
		}
		return children;
	}

	public boolean isExpandCompositions() {
		return mExpandCompositions;
	}

	public void setExpandCompositions(boolean mExpandCompositions) {
		this.mExpandCompositions = mExpandCompositions;
	}

	public boolean isExpandComplexProperties() {
		return mExpandComplexProperties;
	}

	public void setExpandComplexProperties(boolean mExpandComplexProperties) {
		this.mExpandComplexProperties = mExpandComplexProperties;
	}

	public boolean isExpandAssociations() {
		return mExpandAssociations;
	}

	public void setExpandAssociations(boolean mExpandAssociations) {
		this.mExpandAssociations = mExpandAssociations;
	}

	public boolean isExpandAttributes() {
		return mExpandAttributes;
	}

	public void setExpandAttributes(boolean mExpandAttributes) {
		this.mExpandAttributes = mExpandAttributes;
	}

	public boolean isExpandAggregations() {
		return mExpandAggregations;
	}

	public void setExpandAggregations(boolean mExpandAggregations) {
		this.mExpandAggregations = mExpandAggregations;
	}

	
	
}
