package de.emir.rcp.editors.transactions;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.model.ModelTransactionStack;

/**
 * A editor transaction stack handles EditorTransactions of one editor. It manages the undo/redo functionality.
 * 
 * @author fklein
 *
 */
public class EditorTransactionStack extends ModelTransactionStack {

    private AbstractEditor editor;

    public EditorTransactionStack(AbstractEditor editor) {
        super(editor);
        this.editor = editor;
    }

    public void run(AbstractEditorTransaction cmd) {
        cmd.setEditor(editor);
        super.run(cmd);
    }

}
