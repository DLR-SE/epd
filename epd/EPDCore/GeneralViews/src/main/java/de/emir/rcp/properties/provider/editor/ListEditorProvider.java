package de.emir.rcp.properties.provider.editor;

import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.rcp.properties.ui.editors.ListEditor;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ListEditorProvider implements IPropertyEditorProvider
{

	@Override
	public IPropertyEditor provide(IProperty property) {
		if (property instanceof UCoreListProperty){
			return new ListEditor();
		}
		return null;
	}
}
