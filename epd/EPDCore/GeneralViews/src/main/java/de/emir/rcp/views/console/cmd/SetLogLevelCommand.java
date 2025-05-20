package de.emir.rcp.views.console.cmd;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import de.emir.rcp.commands.AbstractRadioGroupCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.console.ConsoleView;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.apache.logging.log4j.Level;

public class SetLogLevelCommand extends AbstractRadioGroupCommand<Level> {

	public SetLogLevelCommand() {
		
		PropertyContext propContext = PropertyStore.getContext(ConsoleView.PROPERTY_CONTEXT_ID);
		IProperty<String> property = propContext.getProperty(ConsoleView.LOG_LEVEL_PROPERTY, "INFO");
		
		setValue(Level.toLevel(property.getValue()));
		
		property.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Level l = Level.getLevel(evt.getNewValue().toString());
				setValue(l); //internal state of the drop down menu
			}
		});
		execute(Level.toLevel(property.getValue()));
	}
	
	
	@Override
	public void execute(Level userObject) {
		//need to change the global log level here, for that we use the ULog interface
		//@note: for now we just change the console log and let the file handlers (if there are any) untouched
		ULog.getInstance().changeAllLogLevel(userObject);
		
		PropertyContext propContext = PropertyStore.getContext(ConsoleView.PROPERTY_CONTEXT_ID);
		propContext.setValue(ConsoleView.LOG_LEVEL_PROPERTY, userObject.toString());
		
	}

	@Override
	public boolean isSelected(Level value) {
		return value == getValue();
		
	}



}
