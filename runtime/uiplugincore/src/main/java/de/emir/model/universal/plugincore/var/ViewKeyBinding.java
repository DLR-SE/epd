package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = AbstractKeyBinding.class)	
public interface ViewKeyBinding extends AbstractKeyBinding 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "viewID", associationType = AssociationType.PROPERTY)
	public void setViewID(String _viewID);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "viewID", associationType = AssociationType.PROPERTY)
	public String getViewID();

	
}
