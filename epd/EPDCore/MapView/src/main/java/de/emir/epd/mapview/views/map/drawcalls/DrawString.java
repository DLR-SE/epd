package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class DrawString implements IDrawCall {

	private String s;
	private float x;
	private float y;

	public DrawString(String s, float x, float y) {
		this.s = s;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.drawString(s, x, y);
		
	}

}
