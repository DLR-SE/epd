package de.emir.epd.mapview.views.map;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import java.awt.*;

public class LayerController {
	
	private IMapDrawable layer;
	private BufferingGraphics2D buffer;
	private Dimension size;

	public LayerController(IMapDrawable layer) {
		this.layer = layer;
	}

	public void setSize(Dimension size) {

		this.size = size;
		layer.setDirty(true);
	}
	
	public boolean isDirty() {
		return layer.isDirty();
	}
	public boolean isVisible() {
		return layer.isVisible();
	}
	
	public void handlePaint(IDrawContext c) {

		if(layer.isVisible() && (layer.isDirty() == true || buffer == null) && size != null) {

			if(size.getWidth() < 1 || size.getHeight() < 1) {
				return;
			}
			
			try {
				BufferingGraphics2D tmpBuffer = new BufferingGraphics2D();
	
				layer.paint(tmpBuffer, c);
				layer.setDirty(false);
				
				buffer = tmpBuffer;

			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public Disposable subscribeVisibility(Consumer<Boolean> c) {
		return layer.subscribeVisibility(c);
	}

	public IMapDrawable getLayer() {
		return layer;
	}

	public void paint(Graphics2D g) {

		if(buffer != null) {
			buffer.paintTo(g);
		}
		
	}
	/*
	 * Drop the buffer. This will result in a redraw at the next draw call
	 */
	public void dropBuffer() {
		this.buffer = null;
	}
	
}
