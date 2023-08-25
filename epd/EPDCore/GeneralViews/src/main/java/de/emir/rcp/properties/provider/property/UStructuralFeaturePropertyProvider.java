package de.emir.rcp.properties.provider.property;

import java.util.List;
import java.util.Map;

import de.emir.rcp.properties.IPropertyProvider;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class UStructuralFeaturePropertyProvider implements IPropertyProvider {

	@Override
	public void contributeProperties(Object _input, Map<String, IProperty> properties) {
		if (_input == null || _input instanceof UObject == false)
			return ;
		UObject input = (UObject)_input;
		UClass cl = input.getUClassifier();
		for (UStructuralFeature f : cl.getAllStructuralFeatures()){
//			if (f.getAggregation() == UAssociationType.COMPOSITION)
//				continue;
			Pointer ptr = PointerOperations.create(input, f, -1);
			IProperty property = createProperty(ptr);
			
			if (property != null)
				properties.put(f.getName(), property);
		}
	}
	
	
	

	
	public static IProperty createListProperty(Pointer ptr) {
		UCoreListProperty lp = new UCoreListProperty(ptr);
		List list = (List) ptr.getValue();
		if (list != null){
			for (int i = 0; i < list.size(); i++){
				IProperty sub = createProperty(PointerOperations.createListPointer(ptr, i));
				if (sub != null)
					lp.addChild(sub);
			}
		}
		return lp;
	}





	public static IProperty createProperty(Pointer ptr) {
		if (ptr == null || ptr.isValid() == false)
			return null;
		UStructuralFeature f = ptr.getPointedFeature();
		if (f.isMany()){
			return createListProperty(ptr);
		}else if (f.isAttribute())
			return new AttributeProperty(ptr);
		else
			return new AssociationProperty(ptr);
	}
}
