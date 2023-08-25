package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.Stroke;

public class SetStroke implements IDrawCall {

	private Stroke s;

	public SetStroke(Stroke s) {
		this.s = s;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.setStroke(s);
		
	}

}
