package de.emir.rcp.editors.ep;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class Editor implements IEditor {

    private String id;
    private Class<? extends AbstractEditor> editorClass;
    private String iconPath;
    private ResourceManager mResourceManager;
    private int mIconSize = -1;

    private List<String> fileExtensions = new ArrayList<>();
    private String label;
    private AbstractUIPlugin provider;

    public Editor(String id, Class<? extends AbstractEditor> editorClass, String label, AbstractUIPlugin provider) {
        this.id = id;
        this.editorClass = editorClass;
        this.label = label;
        this.provider = provider;
    }

    @Override
    public IEditor label(String label) {
        this.label = label;
        return this;
    }

    @Override
    public IEditor icon(String path) {
        return icon(path, ResourceManager.get(provider));
    }

    @Override
    public IEditor icon(String path, ResourceManager rmgr) {
        mResourceManager = rmgr;
        this.iconPath = path;
        return this;
    }

    @Override
    public IEditor iconSize(int size) {
        mIconSize = size;
        return this;
    }

    public Class<? extends AbstractEditor> getEditorClass() {
        return editorClass;
    }

    public ImageIcon getIcon() {
        if (iconPath != null && !iconPath.isEmpty()) {
            URL iconURL = mResourceManager != null ? mResourceManager.getResource(iconPath)
                    : ResourceManager.get(getClass()).getResource(iconPath);
            if (iconURL != null) {
                int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
                return IconManager.getIcon(iconURL, iconSize);
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getFileExtensions() {
        return this.fileExtensions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.editors.ep.IEditor#fileExtension(java.lang.String)
     */
    @Override
    public IEditor fileExtension(String ext) {
        fileExtensions.add(ext);
        return this;
    }

    @Override
    public String toString() {
        return label == null ? id : label;
    }

    public AbstractUIPlugin getProvider() {
        return provider;
    }

}
