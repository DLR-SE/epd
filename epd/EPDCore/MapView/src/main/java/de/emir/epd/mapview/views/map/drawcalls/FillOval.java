package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class FillOval implements IDrawCall {

	private int x;
	private int y;
	private int width;
	private int height;

	public FillOval(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.fillOval(x, y, width, height);
		
	}

}
