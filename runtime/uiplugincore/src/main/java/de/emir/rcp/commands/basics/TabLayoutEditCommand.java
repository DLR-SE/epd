package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * Explicitly toggles the tab layout edit mode. This unlocks the tabs and allows editing of each tab, moving, deleting and
 * adding of new tabs. Note: for simplicity reasons, this command is currently not directly executable within the ui but instead
 * linked to the Lock/Unlock Layout command which also toggles the tab layout edit property.
 */
public class TabLayoutEditCommand extends AbstractCheckableCommand {

    /**
     * Executes the command and toggles the tab layout edit property. This always sets the property to the opposite boolean value it currently has 
     * (i.e. from true to false and vice versa).
     */
    @Override
    public void execute() {
        IProperty<Boolean> tabLayoutActiveProperty = PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX).getProperty(Basic.TAB_LAYOUT_EDIT_MODE_PROP, false);
        tabLayoutActiveProperty.setValue(!tabLayoutActiveProperty.getValue());
    }
}
