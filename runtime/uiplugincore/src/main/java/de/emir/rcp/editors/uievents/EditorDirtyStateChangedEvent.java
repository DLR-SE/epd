package de.emir.rcp.editors.uievents;

import de.emir.rcp.editors.AbstractEditor;

public class EditorDirtyStateChangedEvent {
    private boolean isDirty;
    private AbstractEditor editor;

    public EditorDirtyStateChangedEvent(AbstractEditor editor, boolean isDirty) {
        this.editor = editor;
        this.isDirty = isDirty;
    }

    public AbstractEditor getEditor() {
        return editor;
    }

    public boolean isDirty() {
        return isDirty;
    }
}
