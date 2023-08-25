package de.emir.rcp.editor.model.form.part;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

import de.emir.rcp.editor.model.IFormEditorPart;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.views.properties.JPropertySheet;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class PropertySheetFormProvider implements IFormEditorPart {

	@Override
	public Component createComposite(Pointer pointer) {
		if (pointer == null || pointer.isValid() == false)
			return null;
		JPropertySheet ps = new JPropertySheet();
		Map<String, IProperty> properties = PropertyManager.getInstance().collectProperties(pointer.getValue());
		ArrayList<IProperty> sortedProperties = new ArrayList<>();
		PropertyManager.getInstance().sort(properties, sortedProperties);
		ps.addProperties(sortedProperties);
		return ps;
	}

}
