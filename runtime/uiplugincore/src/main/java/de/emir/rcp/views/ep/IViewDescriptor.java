package de.emir.rcp.views.ep;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * The ViewDescriptor contains information about view objects. The information supplied
 * by the descriptor is used at runtime to create views by the ViewManager.
 */
public interface IViewDescriptor {

    /**
     * Adds a label to the view.
     * @param label Name of the view which should be displayed in the Open View dialog.
     * @return IViewDescriptor.
     */
    IViewDescriptor label(String label);

    /**
     * Adds an icon to the view.
     * @param iconPath Path of the icon of the view which should be displayed in the Open View dialog.
     * @return IViewDescriptor.
     */
    IViewDescriptor icon(String iconPath);

    /**
     * Adds an icon to the view using a custom ResourceManager.
     * @param iconPath Path of the icon of the view which should be displayed in the Open View dialog.
     * @param rmgr ResourceManager which should be used for loading the icon.
     * @return IViewDescriptor.
     */
    IViewDescriptor icon(String iconPath, ResourceManager rmgr);

    /**
     * Sets the maximizable flag of the view. If enabled, the view can be maximized.
     * @param isMaximizable Maximizable flag. Set to true if the view can be maximized.
     * @return IViewDescriptor.
     */
    IViewDescriptor maximizable(boolean isMaximizable);

    /**
     * Sets the minimizable flag of the view. If enabled, the view can be minimized.
     * @param isMinimizable Minimizable flag. Set to true if the view can be minimized.
     * @return IViewDescriptor.
     */
    IViewDescriptor minimizable(boolean isMinimizable);

    /**
     * Sets the externalizable flag of the view. If enabled, the view can be shown in a separate window.
     * @param isExternalizable Externalizable flag. Set to true if the view can be docked out in a separate window.
     * @return IViewDescriptor.
     */
    IViewDescriptor externalizable(boolean isExternalizable);

    /**
     * Sets the reopenable flag of the view. If enabled, the view can be opened multiple times.
     * @param isReopenable Reopenable flag. Set to true if the view can be opened multiple times.
     * @return IViewDescriptor.
     */
    IViewDescriptor reopenable(boolean isReopenable);

    /**
     * Sets the closable flag of the view. If enabled, the view can be closed.
     * @param isCloseable Closable flag. Set to true if the view can be closed.
     * @return IViewDescriptor.
     */
    IViewDescriptor closeable(boolean isCloseable);

    /**
     * Sets the visible on start flag of the view. If enabled, the view is visible at start of the application.
     * @param isVisible VisibleOnStart flag. Set to true if the view is visible at start of the application.
     * @return IViewDescriptor.
     */
    IViewDescriptor visibleOnStart(boolean isVisible);

}
