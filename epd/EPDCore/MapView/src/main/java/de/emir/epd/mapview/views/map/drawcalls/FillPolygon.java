package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class FillPolygon implements IDrawCall {

	private Polygon p;

	public FillPolygon(Polygon p) {
		this.p = p;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.fillPolygon(p);
		
	}

}
