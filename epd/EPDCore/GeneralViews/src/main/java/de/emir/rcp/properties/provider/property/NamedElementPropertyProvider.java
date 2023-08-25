package de.emir.rcp.properties.provider.property;

import java.util.Map;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.NamedElement;
import de.emir.model.universal.core.impl.RSIdentifierImpl;
import de.emir.rcp.properties.IPropertyProvider;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class NamedElementPropertyProvider implements IPropertyProvider {

	@Override
	public void contributeProperties(Object input, Map<String, IProperty> properties) {
		if (input instanceof NamedElement && properties.containsKey("code") == false){
			if (((NamedElement) input).getName() == null)
				((NamedElement)input).setName(new RSIdentifierImpl());
			Pointer ptr = PointerOperations.create((UObject)input, CorePackage.Literals.NamedElement_name, CorePackage.Literals.MDIdentifier_code);
			properties.put("code", new AttributeProperty(ptr));
		}
	}

}
