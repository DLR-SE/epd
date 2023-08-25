package de.emir.rcp.views.ep;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface IViewDescriptor {

    IViewDescriptor label(String label);

    IViewDescriptor icon(String iconPath);

    IViewDescriptor icon(String iconPath, ResourceManager rmgr);

    IViewDescriptor maximizable(boolean isMaximizable);

    IViewDescriptor minimizable(boolean isMinimizable);

    IViewDescriptor externalizable(boolean isExternalizable);

    IViewDescriptor closeable(boolean isCloseable);

    IViewDescriptor visibleOnStart(boolean isVisible);

}
