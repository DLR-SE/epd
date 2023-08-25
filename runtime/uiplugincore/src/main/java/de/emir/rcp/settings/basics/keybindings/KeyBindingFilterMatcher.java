package de.emir.rcp.settings.basics.keybindings;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.rcp.ui.widgets.IFilterMatcher;

public class KeyBindingFilterMatcher implements IFilterMatcher {

    @Override
    public boolean matches(Object o, String filter) {

        if (o instanceof KeyBindingData) {

            KeyBindingData kbd = (KeyBindingData) o;
            CommandDescriptor cmd = kbd.getCommand();
            return cmd.getLabel().toLowerCase().contains(filter.toLowerCase());
        }

        return false;
    }

}
