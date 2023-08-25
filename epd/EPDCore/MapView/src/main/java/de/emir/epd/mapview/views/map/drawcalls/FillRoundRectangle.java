package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class FillRoundRectangle implements IDrawCall {



	private int x;
	private int y;
	private int width;
	private int height;
	private int arcWidth;
	private int arcHeight;

	public FillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		
	}

}
