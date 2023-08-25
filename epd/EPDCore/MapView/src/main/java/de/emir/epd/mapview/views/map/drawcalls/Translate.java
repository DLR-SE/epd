package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class Translate implements IDrawCall {

	

	private double x;
	private double y;

	public Translate(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.translate(x, y);
		
	}

}
