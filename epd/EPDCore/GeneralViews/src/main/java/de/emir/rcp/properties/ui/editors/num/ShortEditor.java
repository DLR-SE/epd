package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class ShortEditor extends NumberEditor<Short> {

	@Override
	protected Short getEditorValue(String text) {
		return Short.parseShort(text);
	}
}