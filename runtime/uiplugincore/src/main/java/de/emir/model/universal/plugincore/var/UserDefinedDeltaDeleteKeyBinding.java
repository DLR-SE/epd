package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface UserDefinedDeltaDeleteKeyBinding extends IUserDefinedDelta 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "oldBinding", associationType = AssociationType.PROPERTY)
	public AbstractKeyBinding getOldBinding();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "oldBinding", associationType = AssociationType.PROPERTY)
	public void setOldBinding(AbstractKeyBinding _oldBinding);
	
}
