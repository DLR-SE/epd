package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;

/**
 *	@generated 
 */
@UMLClass	
public interface UserDefinedDeltaAddKeyBinding extends IUserDefinedDelta 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "newBinding", associationType = AssociationType.PROPERTY)
	public AbstractKeyBinding getNewBinding();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "newBinding", associationType = AssociationType.PROPERTY)
	public void setNewBinding(AbstractKeyBinding _newBinding);

}
