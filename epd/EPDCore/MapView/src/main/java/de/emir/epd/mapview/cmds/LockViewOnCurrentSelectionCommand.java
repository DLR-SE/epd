package de.emir.epd.mapview.cmds;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class LockViewOnCurrentSelectionCommand extends AbstractCheckableCommand {

	private IProperty property;

	public LockViewOnCurrentSelectionCommand() {
		PropertyContext ctx = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		property = ctx.getProperty(MVBasic.MAP_VIEW_PROP_LOCK_VIEW_ON_CURRENT_SELECTION, false);

		property.addPropertyChangeListener(evt -> {
			boolean isLocked = (boolean) property.getValue();
			setChecked(isLocked);
		});
		
		setChecked((boolean) property.getValue());

	}

	@Override
	public void execute() {

		property.setValue(!(boolean) property.getValue());

	}

}
