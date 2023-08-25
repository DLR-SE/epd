package de.emir.rcp.parts.vesseleditor.cmds;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.View;

public class ChangeViewCommand extends AbstractGeometryEditorCommand {
    private View mSide;

    public ChangeViewCommand(View side) {
        super();
        mSide = side;
    }

    @Override
    public void execute() {
        PlatformUtil.getSelectionManager().setSelection(VesselEditorBasic.CTX_VIEW_SELECTION_ID, mSide);
    }

}
