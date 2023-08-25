package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Image;

public class DrawImageXY implements IDrawCall {

	

	private int x;
	private int y;
	private Image img;

	public DrawImageXY(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.drawImage(img, x, y, null);
		
	}

}
