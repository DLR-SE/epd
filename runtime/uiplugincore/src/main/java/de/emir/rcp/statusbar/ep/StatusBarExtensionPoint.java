package de.emir.rcp.statusbar.ep;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.statusbar.AbstractStatusBarElement;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

public class StatusBarExtensionPoint implements IExtensionPoint {

    private List<StatusBarElementDescriptor> elements = new ArrayList<>();

    public IStatusBarElementDescriptor element(Class<? extends AbstractStatusBarElement> elementClass) {

        if (elementClass == null) {
            throw new NullPointerException("StatusBar element class can't be null.");
        }

        StatusBarElementDescriptor result = new StatusBarElementDescriptor(elementClass);

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
        result.setProvider(plugin);

        elements.add(result);

        return result;
    }

    public List<StatusBarElementDescriptor> getElements() {
        return elements;
    }

}
