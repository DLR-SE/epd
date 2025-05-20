package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Paint;

public class SetPaint implements IDrawCall {

	private Paint p;

	public SetPaint(Paint p) {
		this.p = p;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.setPaint(p);
		
	}

}
