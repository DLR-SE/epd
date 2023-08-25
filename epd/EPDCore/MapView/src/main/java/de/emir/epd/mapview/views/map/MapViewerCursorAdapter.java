package de.emir.epd.mapview.views.map;

import java.awt.Cursor;

import javax.swing.JComponent;

public class MapViewerCursorAdapter implements ICursorAdapter {

	private JComponent component;
	
	private Cursor requestedCursor;

	public MapViewerCursorAdapter(JComponent c) {
		this.component = c;
	}

	@Override
	public void setCursor(Cursor cursor) {
		
		requestedCursor = cursor;
		
	}
	
	public void clear() {
		requestedCursor = null;
	}
	
	public void setCursor() {
		
		if(requestedCursor != null) {
			component.setCursor(requestedCursor);
		} else {
			component.setCursor(Cursor.getDefaultCursor());
		}
		
	}
	
	
	
}
