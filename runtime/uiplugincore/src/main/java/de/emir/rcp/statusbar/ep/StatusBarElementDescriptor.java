package de.emir.rcp.statusbar.ep;

import de.emir.rcp.statusbar.AbstractStatusBarElement;
import de.emir.runtime.plugin.AbstractUIPlugin;

public class StatusBarElementDescriptor implements IStatusBarElementDescriptor {
    private Class<? extends AbstractStatusBarElement> elementClass;
    private AbstractUIPlugin provider;

    public StatusBarElementDescriptor(Class<? extends AbstractStatusBarElement> elementClass) {
        this.elementClass = elementClass;
    }

    public Class<? extends AbstractStatusBarElement> getElementClass() {
        return elementClass;
    }

    public void setProvider(AbstractUIPlugin provider) {
        this.provider = provider;

    }

    public AbstractUIPlugin getProvider() {
        return provider;
    }
}
