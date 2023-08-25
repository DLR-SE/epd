package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class LongEditor extends NumberEditor<Long> {
	
	@Override
	protected Long getEditorValue(String text) {
		return Long.parseLong(text);
	}
}