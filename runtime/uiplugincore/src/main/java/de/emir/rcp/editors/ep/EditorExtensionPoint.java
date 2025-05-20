package de.emir.rcp.editors.ep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * The Editor ExtensionPoint is used to register additional editors to the application. See {@link Editor} for further
 * information
 * 
 * @author fklein
 *
 */
public class EditorExtensionPoint implements IExtensionPoint {

    private Logger log = ULog.getLogger(EditorExtensionPoint.class);

    private Map<String, Editor> editors = new HashMap<>();

    /**
     * Adds an editor to the application
     * 
     * @param id
     *            A unique identifier
     * @param editorClass
     *            The class of the editor
     * @return A representation of the added editor
     */
    public IEditor editor(String id, Class<? extends AbstractEditor> editorClass, String label) {
        if (editors.containsKey(id) == true) {

            log.error("An editor with id [" + id + "] already exists.");
            return null;
        }

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();

        Editor editor = new Editor(id, editorClass, label, plugin);

        editors.put(id, editor);

        log.debug("Editor with id [" + id + "] added.");

        return editor;

    }

    public Map<String, Editor> getEditors() {
        return editors;
    }

    /**
     * Returns an editor supporting the specified file extension
     * 
     * @param extension
     * @return
     */
    public Editor getEditorForExtension(String extension) {

        for (Editor e : editors.values()) {
            List<String> extensions = e.getFileExtensions();

            for (String ext : extensions) {
                if (ext.equals(extension)) {
                    return e;
                }
            }
        }

        return null;

    }

}
