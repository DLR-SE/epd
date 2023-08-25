package de.emir.epd.model;

import com.google.common.eventbus.Subscribe;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.events.ActiveEditorChangedEvent;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.NullDirtyStateProvider;

/**
 * The EPDModelProvider provides the backend of the EPD. If an editor is open,
 * its model is returned instead. This ModelProvider offers the possibility to
 * use data from both the backend and editors.
 * 
 * @author Florian
 *
 */
public class EPDModelProvider extends AbstractModelProvider {
	private EPDModel model;
	private ModelTransactionStack mcs = new ModelTransactionStack(new NullDirtyStateProvider());
	private AbstractEditor currentEditor;
	
	

	public EPDModelProvider() {
		this.model = new EPDModel();

		setModel(model);
		setTransactionStack(mcs);

		EventManager.UI.register(this);

	}

	@Subscribe
	public void handleActiveEditorChangedEvent(ActiveEditorChangedEvent e) {
		currentEditor = e.getEditor();

		if (currentEditor != null) {
			setModel(currentEditor.getModel());
			setTransactionStack(currentEditor.getTransactionStack());
		} else {
			setModel(model);
			setTransactionStack(mcs);
		}

	}

	@Override
	public String getModelIdentifier() {

		if (currentEditor == null) {
			return "EPDModel";
		}

		return currentEditor.getModelIdentifier();
	}

	@Override
	public void reloadActiveModel() {

	}

}
