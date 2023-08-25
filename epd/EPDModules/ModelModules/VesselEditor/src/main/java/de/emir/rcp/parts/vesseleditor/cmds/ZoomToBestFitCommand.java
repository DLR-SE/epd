package de.emir.rcp.parts.vesseleditor.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;

public class ZoomToBestFitCommand extends AbstractCommand {
    private PropertyContext context;

    public ZoomToBestFitCommand() {
        super();
        context = PropertyStore.getContext(VesselEditorBasic.PROP_VESSEL_EDITOR);
    }


    @Override
    public void execute() {
        context.setValue(VesselEditorBasic.PROP_ZOOM_TO_BEST_FIT, true);
    }
}
