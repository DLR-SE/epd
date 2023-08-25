package de.emir.rcp.properties.ui.editors.num;

import de.emir.rcp.properties.ui.editors.NumberEditor;

public class ByteEditor extends NumberEditor<Byte> {

	@Override
	protected Byte getEditorValue(String text) {
		return Byte.parseByte(text);
	}
	
}