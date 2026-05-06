package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * Command for toggling the tab lab layout functionality. The tab layout is an alternative navigation method
 * for loading layouts which stores then in user-accessible tabs for easier access than the conventional layout
 * system. This functionality exists in parallel to the conventional layout system which is used when the tab layout
 * is toggled off.
 */
public class TabLayoutCommand extends AbstractCheckableCommand {

    /**
     * Creates a new TabLayoutCommand and binds the listener for the tab layout property to the command.
     */
    public TabLayoutCommand() {
        PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX).getProperty(Basic.TAB_LAYOUT_ACTIVE_PROP, false).addPropertyChangeListener(evt -> {
            this.setChecked((boolean) evt.getNewValue());
        });
    }

    /**
     * Executes the command and toggles the tab layout property. This always sets the property to the opposite boolean value it currently has 
     * (i.e. from true to false and vice versa).
     */
    @Override
    public void execute() {
        IProperty<Boolean> tabLayoutActiveProperty = PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX).getProperty(Basic.TAB_LAYOUT_ACTIVE_PROP, false);
        tabLayoutActiveProperty.setValue(!tabLayoutActiveProperty.getValue());
    }
}
