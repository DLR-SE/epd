package de.emir.rcp.views.ep;

import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.views.AbstractView;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * The ViewDescriptor contains information about view objects. The information supplied
 * by the descriptor is used at runtime to create views by the ViewManager.
 */
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
    private boolean reopenable = false;
    private ResourceManager mResourceManager;
    private int mIconSize = -1;
    private AbstractUIPlugin provider;

    private ImageIcon icon;

    /**
     * Creates a new ViewDescriptor.
     * @param id ID of the descriptor. This is the ID which the views are assigned to. If the reopenable flag is set to true,
     *           each instantiated views ID is appended by _InstanceX, such as _Instance1, _Instance2 etc.
     * @param viewClass View class which should be instantiated when triggered in the UI.
     * @param provider Plugin which loads the view.
     */
    public ViewDescriptor(String id, Class<? extends AbstractView> viewClass, AbstractUIPlugin provider) {
        this.id = id;
        this.viewClass = viewClass;
        this.provider = provider;
    }

    /**
     * Creates a viewable string. If the label is not set, the ID is returned, else the label.
     * @return Label or ID.
     */
    @Override
    public String toString() {
        if (label != null) {
            return label;
        }
        return id;
    }

    /**
     * Gets the identifier of the descriptor.
     * @return ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the view class which is assigned to the current descriptor.
     * @return Corresponding view class.
     */
    public Class<? extends AbstractView> getViewClass() {
        return viewClass;
    }

    /**
     * Adds a label to the view.
     * @param label Name of the view which should be displayed in the Open View dialog.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor label(String label) {
        this.label = label;
        return this;
    }

    /**
     * Adds an icon to the view.
     * @param iconPath Path of the icon of the view which should be displayed in the Open View dialog.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor icon(String iconPath) {
        return icon(iconPath, ResourceManager.get(getViewClass()));
    }

    /**
     * Adds an icon to the view using a custom ResourceManager.
     * @param iconPath Path of the icon of the view which should be displayed in the Open View dialog.
     * @param rmgr ResourceManager which should be used for loading the icon.
     * @return IViewDescriptor.
     */
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

    /**
     * Gets the icon assigned to the view.
     * @return Icon loaded for the view.
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Gets the label assigned to the view.
     * @return Label loaded for the view.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the maximizable flag of the view. If enabled, the view can be maximized.
     * @param isMaximizable Maximizable flag. Set to true if the view can be maximized.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor maximizable(boolean isMaximizable) {
        this.maximizable = isMaximizable;
        return this;
    }

    /**
     * Sets the minimizable flag of the view. If enabled, the view can be minimized.
     * @param isMinimizable Minimizable flag. Set to true if the view can be minimized.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor minimizable(boolean isMinimizable) {
        this.minimizable = isMinimizable;
        return this;

    }

    /**
     * Sets the externalizable flag of the view. If enabled, the view can be shown in a separate window.
     * @param isExternalizable Externalizable flag. Set to true if the view can be docked out in a separate window.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor externalizable(boolean isExternalizable) {
        this.externalizable = isExternalizable;
        return this;
    }

    /**
     * Sets the reopenable flag of the view. If enabled, the view can be opened multiple times.
     * @param isReopenable Reopenable flag. Set to true if the view can be opened multiple times.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor reopenable(boolean isReopenable) {
        this.reopenable = isReopenable;
        return this;
    }

    /**
     * Sets the closable flag of the view. If enabled, the view can be closed.
     * @param isCloseable Closable flag. Set to true if the view can be closed.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor closeable(boolean isCloseable) {
        this.closeable = isCloseable;
        return this;
    }

    /**
     * Sets the visible on start flag of the view. If enabled, the view is visible at start of the application.
     * @param isVisible VisibleOnStart flag. Set to true if the view is visible at start of the application.
     * @return IViewDescriptor.
     */
    @Override
    public IViewDescriptor visibleOnStart(boolean isVisible) {
        this.visibleOnStart = isVisible;
        return this;
    }

    /**
     * Checks if the view is closable.
     * @return Closable flag.
     */
    public boolean isCloseable() {
        return closeable;
    }

    /**
     * Checks if the view is maximizable.
     * @return Maximizable flag.
     */
    public boolean isMaximizable() {
        return maximizable;
    }

    /**
     * Checks if the view is minimizable.
     * @return Minimizable flag.
     */
    public boolean isMinimizable() {
        return minimizable;
    }

    /**
     * Checks if the view can be opened in a separate window.
     * @return Externalizable flag.
     */
    public boolean isExternalizable() {
        return externalizable;
    }

    /**
     * Checks if the view needs to be visible at start of the application.
     * @return VisibleOnStart flag.
     */
    public boolean isVisibleOnStart() {
        return visibleOnStart;
    }

    /**
     * Checks if the view can be opened multiple times.
     * @return Reopenable flag.
     */
    public boolean isReopenable() {
        return reopenable;
    }

    /**
     * Gets the plugin which registered the view.
     * @return Plugin that registered the view.
     */
    public AbstractUIPlugin getProvider() {
        return provider;
    }

}
