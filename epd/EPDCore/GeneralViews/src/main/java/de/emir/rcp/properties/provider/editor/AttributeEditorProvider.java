package de.emir.rcp.properties.provider.editor;

import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.rcp.properties.ui.editors.BooleanEditor;
import de.emir.rcp.properties.ui.editors.EnumEditor;
import de.emir.rcp.properties.ui.editors.StringEditor;
import de.emir.rcp.properties.ui.editors.num.ByteEditor;
import de.emir.rcp.properties.ui.editors.num.DoubleEditor;
import de.emir.rcp.properties.ui.editors.num.FloatEditor;
import de.emir.rcp.properties.ui.editors.num.IntegerEditor;
import de.emir.rcp.properties.ui.editors.num.LongEditor;
import de.emir.rcp.properties.ui.editors.num.ShortEditor;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.prop.IProperty;


public class AttributeEditorProvider implements IPropertyEditorProvider
{

	@Override
	public IPropertyEditor provide(IProperty property) {
//		if (property instanceof AttributeProperty){
		if (property instanceof UCoreListProperty)
			return null; //lists are handled using a different provider
			Class<?> cl = property.getType();
			if (cl == String.class)
				return new StringEditor();
			else if (cl == Long.class || cl == long.class)
				return new LongEditor();
			else if (cl == Integer.class || cl == int.class)
				return new IntegerEditor();
			else if (cl == double.class || cl == Double.class)
				return new DoubleEditor();
			else if (cl == float.class || cl == Float.class)
				return new FloatEditor();
			else if (cl == byte.class || cl == Byte.class)
				return new ByteEditor();
			else if (cl == short.class || cl == Short.class)
				return new ShortEditor();
			else if (cl == boolean.class || cl == Boolean.class)
				return new BooleanEditor();
			//not a primitive type, but best handled here: 
			if (property instanceof AttributeProperty)
				if (((AttributeProperty) property).getPointer().getType() instanceof UEnum)
					return new EnumEditor();
//		}
		return null;
	}
}
