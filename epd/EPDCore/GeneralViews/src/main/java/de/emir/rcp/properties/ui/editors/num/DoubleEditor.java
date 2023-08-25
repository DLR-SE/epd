package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class DoubleEditor extends NumberEditor<Double> {

	
	public DoubleEditor() {
		super();
		mFormatString = "%1.4f";
	}
	
	@Override
	protected Double getEditorValue(String text) {
		try {
			return Double.parseDouble(text.trim());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}