package de.emir.epd.mapview.views.map;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public interface IMapDrawable extends MouseListener, MouseMotionListener {

	public void setDirty(boolean isDirty);
	public void markDirty();
	public boolean isDirty();
	
	public void paint(BufferingGraphics2D g, IDrawContext c);
	
	public boolean isVisible();
	public Disposable subscribeVisibility(Consumer<Boolean> c);
}
