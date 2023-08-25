package de.emir.rcp.parts.vesseleditor.cmds;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;

public abstract class AbstractGeometryEditorCommand extends AbstractCommand {

    public AbstractGeometryEditorCommand() {
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID, o -> handleCanExecute());
        handleCanExecute();
    }

    protected void handleCanExecute() {
        setCanExecute(PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID) != null);
    }

}
