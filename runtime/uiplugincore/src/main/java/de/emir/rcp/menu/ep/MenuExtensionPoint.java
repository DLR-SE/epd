package de.emir.rcp.menu.ep;

import java.util.HashMap;
import java.util.Map;

import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

public class MenuExtensionPoint implements IExtensionPoint {

    private Map<String, MenuContribution> menuContributions = new HashMap<>();

    public MenuExtensionPoint() {

    }

    public IMenuContribution menuContribution(String url) {

        // TODO: Validate url to avoid adding wrong paths?

        MenuContribution result = menuContributions.get(url);
        if (result == null) {

            result = new MenuContribution();
            menuContributions.put(url, result);
        }
        return result;
    }

    public Map<String, MenuContribution> getMenuContributions() {
        return menuContributions;
    }

}
