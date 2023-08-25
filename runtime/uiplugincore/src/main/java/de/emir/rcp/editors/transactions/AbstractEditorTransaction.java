package de.emir.rcp.editors.transactions;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.model.AbstractModelTransaction;

/**
 * An abstract editor transaction with undo/redo functionality. This is not the same as an abstract command. Editor
 * commands have to be executed by the commandstack of an editor.
 * 
 * @author fklein
 *
 */
public abstract class AbstractEditorTransaction extends AbstractModelTransaction {

    private AbstractEditor editor;

    protected void setEditor(AbstractEditor editor) {
        this.editor = editor;
    }

    protected AbstractEditor getEditor() {
        return editor;
    }

}
