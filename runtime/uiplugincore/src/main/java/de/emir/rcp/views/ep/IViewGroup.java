package de.emir.rcp.views.ep;

import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public interface IViewGroup {

    IViewGroup group(String id);

    IViewGroup group(String id, String label);

    IViewGroup label(String label);

    IViewGroup icon(String iconPath, ResourceManager rmgr);

    IViewDescriptor view(String id, IViewDescriptor view);

    IViewDescriptor view(String id, Class<? extends AbstractView> viewClass);
}
