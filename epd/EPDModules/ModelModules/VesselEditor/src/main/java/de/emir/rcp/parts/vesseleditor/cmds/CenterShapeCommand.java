package de.emir.rcp.parts.vesseleditor.cmds;

import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

public class CenterShapeCommand extends AbstractGeometryEditorCommand {
    private PropertyContext context;

    public CenterShapeCommand() {
        super();
        context = PropertyStore.getContext(VesselEditorBasic.PROP_VESSEL_EDITOR);
    }

    @Override
    public void execute() {
        context.setValue(VesselEditorBasic.PROP_CENTER_ON_SHAPE, true);
    }
}
