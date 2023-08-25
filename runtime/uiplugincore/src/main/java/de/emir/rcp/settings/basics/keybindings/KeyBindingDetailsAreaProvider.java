package de.emir.rcp.settings.basics.keybindings;

import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.rcp.ui.widgets.IDetailsAreaProvider;

public class KeyBindingDetailsAreaProvider implements IDetailsAreaProvider {

    @Override
    public AbstractDetailsContentPanel<?> getDetailsPanel(Object o) {

        if (o instanceof KeyBindingData) {
            return new KeyBindingContentPanel((KeyBindingData) o);
        }

        return null;
    }

}
