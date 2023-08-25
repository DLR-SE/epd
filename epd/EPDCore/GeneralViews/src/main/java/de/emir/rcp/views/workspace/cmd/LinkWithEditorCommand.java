package de.emir.rcp.views.workspace.cmd;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.workspace.WorkspaceView;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class LinkWithEditorCommand extends AbstractCheckableCommand {

	
	private PropertyContext propContext;
	private IProperty<Boolean> property;

	public LinkWithEditorCommand() {
		
		propContext = PropertyStore.getContext(WorkspaceView.PROPERTY_CONTEXT);
		property = propContext.getProperty(WorkspaceView.LINK_SELECTION_WITH_EDITOR_PROPERTY, false);
		
		property.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				
				setChecked((boolean) evt.getNewValue());
				
			}
		});
		
		setChecked((boolean) property.getValue());
	}
	
	@Override
	public void execute() {
		
		property.setValue(!property.getValue());

	}

}
