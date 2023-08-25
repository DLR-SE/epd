package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class Rotate implements IDrawCall {



	private double theta;

	public Rotate(double theta) {
		this.theta = theta;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.rotate(theta);
		
	}

}
