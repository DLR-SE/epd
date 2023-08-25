package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Shape;

public class FillShape implements IDrawCall {

	private Shape s;

	public FillShape(Shape s) {
		this.s = s;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.fill(s);
		
	}

}
