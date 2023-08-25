package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Shape;

public class DrawShape implements IDrawCall {

	private Shape s;

	public DrawShape(Shape s) {
		this.s = s;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.draw(s);
		
	}

}
