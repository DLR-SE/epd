package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 * @generated
 */
@UMLClass(parent = UType.class)
public interface UPrimitiveType extends UType {

    Object parseString(String value);
    
    String toString(Object value);

    Object getDefaultValue();

}
