package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class SetTransform implements IDrawCall {

	private AffineTransform t;

	public SetTransform(AffineTransform t) {
		this.t = t;
	}
	
	@Override
	public void run(Graphics2D g) {
		g.setTransform(t);
	}

}
