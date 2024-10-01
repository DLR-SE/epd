package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface KeyBindings extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "deltas", associationType = AssociationType.PROPERTY)
	public List<IUserDefinedDelta> getDeltas();

	
}
