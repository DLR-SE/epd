package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class FloatEditor extends NumberEditor<Float> {

	
	public FloatEditor() {
		super();
		mFormatString = "%1.4f";
	}
	
	@Override
	protected Float getEditorValue(String text) {
		return Float.parseFloat(text);
	}
	
}