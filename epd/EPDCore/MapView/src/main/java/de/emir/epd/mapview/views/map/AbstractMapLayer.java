package de.emir.epd.mapview.views.map;

import java.awt.event.MouseEvent;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public abstract class AbstractMapLayer implements IMapDrawable {

	private boolean visible = true;

	private boolean dirty = true;

	private IProperty layersProperty;

	private String id;

	protected ICursorAdapter cursorAdapter;
	
	private PublishSubject<Boolean> visibilitySubject = PublishSubject.create();

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setDirty(boolean isDirty) {
		this.dirty = isDirty;
	}
	
	public void markDirty() {
		setDirty(true);
	}

	public boolean isDirty() {
		return dirty;
	}
	
	public Disposable subscribeVisibility(Consumer<Boolean> c) {
		return visibilitySubject.subscribe(c);
	}

	protected final void init() {

		PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		layersProperty = context.getProperty(MVBasic.MAP_VIEW_VISIBLE_LAYERS_PROP + "_" + getId(), true);

		checkVisibility();

		layersProperty.addPropertyChangeListener(evt -> checkVisibility());

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	

	private void checkVisibility() {
		boolean value = (boolean) layersProperty.getValue();

		if (value != visible) {
			visible = value;
			setDirty(true);
			visibilitySubject.onNext(visible);
		}

	}

	/**
	 * Defines if the user can switch the visibility of this layer manually
	 * 
	 * @return
	 */
	public abstract boolean isVisibilityUserControlled();

	/**
	 * Called when the underlying model of the Model Manager has been replaced.
	 * If the layer has registered listeners, they should be removed here and
	 * added to the new model. This action is necessary, for example, if the
	 * ModelProvider of the Model Manager is linked to the currently active
	 * editor, and it changes.
	 */
	public abstract void modelChanged();

	public void setCursorAdapter(ICursorAdapter cursorAdapter) {
		this.cursorAdapter = cursorAdapter;
		
	}

}
