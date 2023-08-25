package de.emir.epd.mapview.views.map.drawcalls;

import java.awt.Font;
import java.awt.Graphics2D;

public class SetFont implements IDrawCall {

	private Font f;

	public SetFont(Font f) {
		this.f =  f;
	}
	
	@Override
	public void run(Graphics2D g) {
		
		g.setFont(f);
		
	}

}
