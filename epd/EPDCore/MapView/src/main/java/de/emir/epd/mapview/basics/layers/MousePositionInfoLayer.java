package de.emir.epd.mapview.basics.layers;

import java.awt.Point;
import java.awt.event.MouseEvent;

import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;

public class MousePositionInfoLayer extends AbstractMapLayer {

	private int mouseX;
	private int mouseY;

	public MousePositionInfoLayer() {

	}
	
	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		Point mp = c.getMousePosition();
		
		if(mp == null) {
			return;
		}
		c.drawPositionInfo(g, mouseX, mouseY);
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	@Override
	public void modelChanged() {

	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		setDirty(true);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		setDirty(true);

	}

}
