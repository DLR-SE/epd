package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class DrawLine implements IDrawCall {


	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public DrawLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.drawLine(x1, y1, x2, y2);
		
	}

}
