package de.emir.rcp.views.ep;

import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;

import de.emir.rcp.views.AbstractView;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class ViewDescriptor implements IViewDescriptor {

    private static final Logger log = ULog.getLogger(ViewDescriptor.class);

    private String id;
    private Class<? extends AbstractView> viewClass;
    private String label;

    private boolean maximizable = true;
    private boolean minimizable = true;
    private boolean externalizable = true;
    private boolean closeable = true;
    private boolean visibleOnStart = true;
    private ResourceManager mResourceManager;
    private int mIconSize = -1;
    private AbstractUIPlugin provider;

    private ImageIcon icon;

    public ViewDescriptor(String id, Class<? extends AbstractView> viewClass, AbstractUIPlugin provider) {
        this.id = id;
        this.viewClass = viewClass;
        this.provider = provider;
    }

    @Override
    public String toString() {
        if (label != null) {
            return label;
        }

        return id;
    }

    public String getId() {
        return id;
    }

    public Class<? extends AbstractView> getViewClass() {
        return viewClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#label(java.lang.String)
     */
    @Override
    public IViewDescriptor label(String label) {
        this.label = label;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#icon(java.lang.String)
     */
    @Override
    public IViewDescriptor icon(String iconPath) {
        return icon(iconPath, ResourceManager.get(getViewClass()));
    }

    @Override
    public IViewDescriptor icon(String iconPath, ResourceManager rmgr) {

        mResourceManager = rmgr;

        long start = System.currentTimeMillis();

        if (iconPath != null && !iconPath.isEmpty()) {
            URL iconURL = mResourceManager != null ? mResourceManager.getResource(iconPath)
                    : ResourceManager.get(getClass()).getResource(iconPath);
            if (iconURL != null) {
                int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
                icon = IconManager.getIcon(iconURL, iconSize);
            }
        }

        long delta = System.currentTimeMillis() - start;

        if (delta > 50) {
            log.warn("Loading Icon [" + iconPath + "] for view [" + id
                    + "] takes a long time. If you want to use an image from another plugin, it is recommended to specify a certain ResourceManager.");
        }

        return this;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getLabel() {
        return label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#maximizable(boolean)
     */
    @Override
    public IViewDescriptor maximizable(boolean isMaximizable) {
        this.maximizable = isMaximizable;
        return this;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#minimizable(boolean)
     */
    @Override
    public IViewDescriptor minimizable(boolean isMinimizable) {
        this.minimizable = isMinimizable;
        return this;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#externalizable(boolean)
     */
    @Override
    public IViewDescriptor externalizable(boolean isExternalizable) {
        this.externalizable = isExternalizable;
        return this;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#closeable(boolean)
     */
    @Override
    public IViewDescriptor closeable(boolean isCloseable) {
        this.closeable = isCloseable;
        return this;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.views.ep.IView#visibleOnStart(boolean)
     */
    @Override
    public IViewDescriptor visibleOnStart(boolean isVisible) {
        this.visibleOnStart = isVisible;
        return this;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public boolean isMaximizable() {
        return maximizable;
    }

    public boolean isMinimizable() {
        return minimizable;
    }

    public boolean isExternalizable() {
        return externalizable;
    }

    public boolean isVisibleOnStart() {
        return visibleOnStart;
    }

    public AbstractUIPlugin getProvider() {
        return provider;
    }

}
