package de.emir.epd.mapview.views.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.mapview.views.map.IMapDrawable;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public abstract class AbstractMapViewTool implements MouseWheelListener, KeyListener, IMapDrawable {

	private boolean enabled;
	private boolean dirty;
	private String id;
	
	private boolean useable = true;
	private PublishSubject<Boolean> useableSubject = PublishSubject.create();
	
	
	public Disposable subscribeUseable(Consumer<Boolean> c) {
		return useableSubject.subscribe(c);
	}
	
	public boolean isUseable() {
		return useable;
	}
	
	protected void setUseable(boolean useable) {
		this.useable = useable;
		useableSubject.onNext(useable);
	}
	
	
	public abstract void init(MapViewerWithTools viewer);

	public abstract void activate();

	public abstract void deactivate();

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	public void paint(BufferingGraphics2D g, IDrawContext c) {
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public abstract void modelChanged();

	public void setDirty(boolean isDirty) {
		this.dirty = isDirty;
	}

	public void markDirty() {
		setDirty(true);
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public final boolean isVisible() {
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	@Override
	public Disposable subscribeVisibility(Consumer<Boolean> c) {
		return null;
	}

}
