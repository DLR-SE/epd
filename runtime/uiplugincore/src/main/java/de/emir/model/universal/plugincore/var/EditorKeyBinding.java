package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = AbstractKeyBinding.class)	
public interface EditorKeyBinding extends AbstractKeyBinding 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "editorID", associationType = AssociationType.PROPERTY)
	public void setEditorID(String _editorID);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "editorID", associationType = AssociationType.PROPERTY)
	public String getEditorID();

	
}
