package de.emir.rcp.views.console.cmd;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.console.ConsoleView;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class LockTailingCommand extends AbstractCheckableCommand {

	PropertyContext propContext = PropertyStore.getContext(ConsoleView.PROPERTY_CONTEXT_ID);
	IProperty<Boolean> property = null;
	
	public LockTailingCommand() {
		property = propContext.getProperty(ConsoleView.TAILING_PROPERTY, false);
		Boolean initialState = property.getValue();
		if (initialState == null) initialState = false;
		setChecked(initialState);
		
		property.addPropertyChangeListener(evt -> setChecked((boolean) evt.getNewValue()));

	}
	
	@Override
	public void execute() {

		property.setValue(!property.getValue());
		
	}

}
