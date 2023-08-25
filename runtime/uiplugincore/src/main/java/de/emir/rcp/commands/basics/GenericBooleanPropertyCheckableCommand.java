package de.emir.rcp.commands.basics;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * A generic command that can be used to bind a checkbox menu item with a boolean property
 * 
 * @author Florian
 *
 */
public class GenericBooleanPropertyCheckableCommand extends AbstractCheckableCommand {

    PropertyContext propContext;
    IProperty<Boolean> property;

    public GenericBooleanPropertyCheckableCommand(String ctxID, String propID) {

        propContext = PropertyStore.getContext(ctxID);
        property = propContext.getProperty(propID, false);

        Boolean initialState = property.getValue();
        setChecked(initialState);

        property.addPropertyChangeListener(evt -> setChecked((boolean) evt.getNewValue()));

    }

    @Override
    public void execute() {

        property.setValue(!property.getValue());

    }
}
