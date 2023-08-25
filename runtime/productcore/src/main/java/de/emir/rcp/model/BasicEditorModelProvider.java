package de.emir.rcp.model;

import com.google.common.eventbus.Subscribe;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.events.ActiveEditorChangedEvent;

/**
 * 
 * This is a basic editor model provider. It provides the currently active editors model and command stack
 * 
 * @author Florian
 *
 */
public class BasicEditorModelProvider extends AbstractModelProvider {

    private AbstractEditor currentEditor;

    public BasicEditorModelProvider() {

        EventManager.UI.register(this);

    }

    @Subscribe
    public void handleActiveEditorChangedEvent(ActiveEditorChangedEvent e) {
        currentEditor = e.getEditor();

        if (currentEditor != null) {
            setModel(currentEditor.getModel());
            setTransactionStack(currentEditor.getTransactionStack());
        } else {
            setModel(null);
            setTransactionStack(null);
        }

    }

    @Override
    public String getModelIdentifier() {
        if (currentEditor != null) {
            return currentEditor.getModelIdentifier();
        }

        return null;
    }

    @Override
    public void reloadActiveModel() {
        if (currentEditor != null) {
            currentEditor.reload();
            // we need! to inform all views that we have changed
            setModel(currentEditor.getModel());
            setTransactionStack(currentEditor.getTransactionStack());
        }
    }

}
