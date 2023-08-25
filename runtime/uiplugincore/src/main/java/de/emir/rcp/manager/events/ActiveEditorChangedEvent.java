package de.emir.rcp.manager.events;

import de.emir.rcp.editors.AbstractEditor;

public class ActiveEditorChangedEvent {

    private AbstractEditor editor;

    public ActiveEditorChangedEvent(AbstractEditor editor) {

        this.editor = editor;

    }

    public AbstractEditor getEditor() {
        return editor;
    }

}
