package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Image;

public class DrawImage implements IDrawCall {

	

	private Image img;
	private int dx1;
	private int dy1;
	private int dx2;
	private int dy2;
	private int sx1;
	private int sy1;
	private int sx2;
	private int sy2;

	public DrawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		this.img = img;
		this.dx1 = dx1;
		this.dy1 = dy1;
		this.dx2 = dx2;
		this.dy2 = dy2;
		this.sx1 = sx1;
		this.sy1 = sy1;
		this.sx2 = sx2;
		this.sy2 = sy2;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		
	}

}
