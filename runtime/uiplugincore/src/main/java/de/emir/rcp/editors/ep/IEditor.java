package de.emir.rcp.editors.ep;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface IEditor {
    /**
     * Specifies an icon for this editor.
     * 
     * @param path
     * @return
     */
    public IEditor icon(String path);

    /**
     * Specifies an icon for this editor.
     * 
     * @param path
     * @param rmgr
     *            ResourceManager used to locate the icon
     * @return
     */
    public IEditor icon(String path, ResourceManager rmgr);

    /**
     * Defines the size of the icon (if set)
     * 
     * @param size
     * @return
     */
    public IEditor iconSize(int size);

    /**
     * Adds an entry to the list of supported file extensions for this editor
     * 
     * @param ext
     *            The file extension without a dot. E.g. "txt"
     * @return
     */
    public IEditor fileExtension(String ext);

    /**
     * Specifies a label for this editor. This is displayed in the Open With dialog, for example
     * 
     * @param label
     * @return
     */
    IEditor label(String label);
}
