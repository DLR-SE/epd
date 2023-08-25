package de.emir.rcp.parts.vesseleditor.provider;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.cmds.transactions.TranslateGeometryTransaction;
import de.emir.rcp.parts.vesseleditor.utils.GeometryUtil;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import java.net.URL;

public class ReferencePointProvider implements ITransferableProvider {
    @Override
    public String getLabel() {
        return "Reference Point";
    }

    @Override
    public URL getIconURL() {
        return ResourceManager.get(getClass()).getResource("/icons/emiricons/32/location_on.png");
    }

    @Override
    public String getToolTip() {
        return "Reference point";
    }

    @Override
    public boolean place(PhysicalObject target, Coordinate position, EulerImpl orientation) {
        Object o = PlatformUtil.getSelectionManager().getSelectedObject(VesselEditorBasic.CTX_VIEW_SELECTION_ID);

        if (o instanceof View) {
            View view = (View) o;
            ObjectSurfaceInformation osi = (ObjectSurfaceInformation) PlatformUtil.getSelectionManager().
                    getSelectedObject(VesselEditorBasic.CTX_OBJECT_SURFACE_SELECTION_ID);
            Geometry geometry = GeometryUtil.getGeometry(view, osi);
            PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(new TranslateGeometryTransaction(geometry, position));
            return true;
        }

        return false;
    }
}
