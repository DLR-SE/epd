package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;

public class DrawRect implements IDrawCall {

	private int x;
	private int y;
	private int width;
	private int height;

	public DrawRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void run(Graphics2D g) {
		g.drawRect(x, y, width, height);
	}

}
