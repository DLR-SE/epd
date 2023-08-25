package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class Scale implements IDrawCall {

	

	private double x;
	private double y;

	public Scale(double sx, double sy) {
		this.x = sx;
		this.y = sy;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.scale(x, y);
		
	}

}
