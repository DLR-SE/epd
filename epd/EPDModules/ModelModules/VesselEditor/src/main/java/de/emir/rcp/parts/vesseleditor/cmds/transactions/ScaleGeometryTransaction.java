package de.emir.rcp.parts.vesseleditor.cmds.transactions;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.service.geometry.impl.GeometryTransform;

public class ScaleGeometryTransaction extends AbstractEditorTransaction {
    private Geometry mGeometry;
    private double mSx;
    private double mSy;

    private boolean mUndoable = false;
    private boolean mRedoable = false;

    public ScaleGeometryTransaction(Geometry geometry, double sx, double sy) {
        mGeometry = geometry;
        mSx = sx;
        mSy = sy;
    }

    @Override
    public void run() {
        doTheScaling(mGeometry, mSx, mSy);
        mUndoable = true;
        mRedoable = false;
    }


    @Override
    public void undo() {
        doTheScaling(mGeometry, 1.0 / mSx, 1.0 / mSy);
        mUndoable = false;
        mRedoable = true;
    }

    private void doTheScaling(Geometry geom, double sx, double sy) {
        //we need to change the CRS of the geometry, since we don't want to scale it in local space. Therefore we remember the current CRS, replace it and put it back when we are finished
        CoordinateReferenceSystem originalCRS = geom.getCRS();
        try {
            if (originalCRS != null && originalCRS instanceof Engineering2D == false)
                throw new UnsupportedOperationException("Expected some engineering2D CRS for the geometry to be scaled");
            Engineering2D newCRS = new Engineering2DImpl(); //we move the shape to the point 0,0 with not rotation by replacing the CRS
            geom.recursiveSetCRS(newCRS);
            GeometryTransform gt = new GeometryTransform();
            gt.scaleGeometryLocal(new Vector2DImpl(sx, sy), geom);
        } finally {
            geom.recursiveSetCRS(originalCRS);
        }
    }

    @Override
    public void redo() {
        run();
    }

    @Override
    public boolean canUndo() {
        return mUndoable;
    }

    @Override
    public boolean canRedo() {
        return mRedoable;
    }

}
