package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class DrawArc implements IDrawCall {

	private int x;
	private int y;
	private int width;
	private int height;
	private int startAngle;
	private int arcAngle;

	public DrawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.drawArc(x, y, width, height, startAngle, arcAngle);
		
	}

}
