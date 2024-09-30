package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaAddKeyBinding;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaDeleteKeyBinding;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface UserDefinedDeltaChangeKeyBinding extends IUserDefinedDelta 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "add", associationType = AssociationType.PROPERTY)
	public UserDefinedDeltaAddKeyBinding getAdd();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "remove", associationType = AssociationType.PROPERTY)
	public UserDefinedDeltaDeleteKeyBinding getRemove();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "add", associationType = AssociationType.PROPERTY)
	public void setAdd(UserDefinedDeltaAddKeyBinding _add);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "remove", associationType = AssociationType.PROPERTY)
	public void setRemove(UserDefinedDeltaDeleteKeyBinding _remove);
	
}
