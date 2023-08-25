package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class IntegerEditor extends NumberEditor<Integer> {

	@Override
	protected Integer getEditorValue(String text) {
		return Integer.parseInt(text);
	}
	
}