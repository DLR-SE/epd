package de.emir.model.universal.core.var;

import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.core.Variable;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 The PointerVariable is similar to an UObjectVariable (if pointed to an primitive type may also to the others)
 * however a pointer variable can never store its own values and thus is a reference to another value. 
 * @generated 
 */
@UMLClass(parent = Variable.class)	
public interface PointerVariable extends Variable 
{
	/**
	 path to the represented value
	 * @generated 
	 */
	@UMLProperty(name = "path", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public ModelPath getPath();
	/**
	 path to the represented value
	 * @generated 
	 */
	@UMLProperty(name = "path", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setPath(ModelPath _path);
	
}
