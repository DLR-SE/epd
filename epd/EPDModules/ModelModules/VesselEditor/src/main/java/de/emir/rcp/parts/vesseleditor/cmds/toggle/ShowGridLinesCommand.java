package de.emir.rcp.parts.vesseleditor.cmds.toggle;

import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

public class ShowGridLinesCommand extends AbstractToggleCommand {
    private PropertyContext context;

    public ShowGridLinesCommand() {
        super();
        context = PropertyStore.getContext(VesselEditorBasic.PROP_VESSEL_EDITOR);
        setChecked(true);
    }

    @Override
    public void execute() {
        Object obj = context.getValue(VesselEditorBasic.PROP_SHOW_GRID_LINES);
        if (obj instanceof Boolean) {
            Boolean bool = (Boolean) obj;
            context.setValue(VesselEditorBasic.PROP_SHOW_GRID_LINES, !bool);
        } else {
            context.setValue(VesselEditorBasic.PROP_SHOW_GRID_LINES, false);
        }
    }
}
