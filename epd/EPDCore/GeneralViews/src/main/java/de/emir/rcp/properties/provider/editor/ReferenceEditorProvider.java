package de.emir.rcp.properties.provider.editor;

import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.ui.editors.ReferenceEditor;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ReferenceEditorProvider implements IPropertyEditorProvider
{

	@Override
	public IPropertyEditor provide(IProperty property) {
		if (property instanceof AssociationProperty){
			AssociationProperty assoProp = (AssociationProperty)property;			
			if (assoProp.getAssociationType() != UAssociationType.COMPOSITION){
				return new ReferenceEditor();
			}			
		}
		return null;
	}
}
