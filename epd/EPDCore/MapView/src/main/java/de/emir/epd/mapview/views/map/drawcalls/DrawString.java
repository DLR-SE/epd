package de.emir.epd.mapview.views.map.drawcalls;

import de.emir.tuml.ucore.runtime.logging.ULog;

import java.awt.Graphics2D;

public class DrawString implements IDrawCall {

	private String s;
	private float x;
	private float y;

	public DrawString(String s, float x, float y) {
		this.s = s;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run(Graphics2D g) {
		if(s != null) {
			g.drawString(s, x, y);
		} else {
			ULog.error("Attempted to draw null string. Not executing call.");
		}
	}

}
