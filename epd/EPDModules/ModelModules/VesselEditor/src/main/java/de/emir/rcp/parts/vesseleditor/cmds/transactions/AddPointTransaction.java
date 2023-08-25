package de.emir.rcp.parts.vesseleditor.cmds.transactions;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;

public class AddPointTransaction extends AbstractEditorTransaction {
    private Geometry mGeometry;
    private int mIndex0;
    private int mIndex1;
    private Coordinate target;

    private boolean mUndoable = false;
    private boolean mRedoable = false;


    public AddPointTransaction(Geometry geometry, int idx0, int idx1, Coordinate t) {
        mGeometry = geometry;
        mIndex0 = idx0;
        mIndex1 = idx1;
        target = t;
    }

    @Override
    public void run() {
        if (mGeometry instanceof Polygon) {
            Polygon poly = (Polygon) mGeometry;
            poly.getShell().getPoints().addCoordinate(mIndex1, target);
        } else if (mGeometry instanceof LinearRing) {
            LinearRing linearRing = (LinearRing) mGeometry;
            linearRing.getPoints().addCoordinate(mIndex1, target);
        } else if (mGeometry instanceof LineString) {
            LineString lineString = (LineString) mGeometry;
            lineString.getPoints().addCoordinate(mIndex1, target);
        } else if (mGeometry instanceof Point) {
            throw new UnsupportedOperationException("Geometry is not yet supported");
        } else {
            throw new UnsupportedOperationException("Geometry is not yet supported");
        }

        mUndoable = true;
        mRedoable = false;
    }

    @Override
    public void undo() {
        if (mGeometry instanceof Polygon) {
            Polygon poly = (Polygon) mGeometry;
            poly.getShell().getPoints().removeCoordinate(mIndex1);
        }
        mUndoable = false;
        mRedoable = true;
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
