package de.emir.rcp.parts.vesseleditor.cmds.toggle;

import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

public class ToggleShapeEditCommand extends AbstractToggleCommand {
    private PropertyContext context;

    public ToggleShapeEditCommand() {
        super();
        context = PropertyStore.getContext(VesselEditorBasic.PROP_VESSEL_EDITOR);
        setChecked(true);
    }

    @Override
    public void execute() {
        Object obj = context.getValue(VesselEditorBasic.PROP_TOOGLE_SHAPE_EDIT);
        if (obj instanceof Boolean) {
            Boolean bool = (Boolean) obj;
            context.setValue(VesselEditorBasic.PROP_TOOGLE_SHAPE_EDIT, !bool);
        } else {
            context.setValue(VesselEditorBasic.PROP_TOOGLE_SHAPE_EDIT, false);
        }
    }


}
