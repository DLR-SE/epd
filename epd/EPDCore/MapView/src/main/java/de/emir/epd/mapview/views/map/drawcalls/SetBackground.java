package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Color;
import java.awt.Graphics2D;

public class SetBackground implements IDrawCall {

	private Color c;

	public SetBackground(Color c) {
		this.c = c;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.setBackground(c);
		
	}

}
