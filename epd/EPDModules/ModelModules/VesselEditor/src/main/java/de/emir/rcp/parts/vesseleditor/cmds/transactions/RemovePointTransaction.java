package de.emir.rcp.parts.vesseleditor.cmds.transactions;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;

public class RemovePointTransaction extends AbstractEditorTransaction {
    private Geometry mGeometry;
    private int mIndex1;
    private Coordinate target;

    private boolean mUndoable = false;
    private boolean mRedoable = false;


    public RemovePointTransaction(Geometry geometry, Coordinate t) {
        mGeometry = geometry;

        for (int i = 0; i < mGeometry.numCoordinates(); i++) {
            if (mGeometry.getCoordinate(i) == t) {
                mIndex1 = i;
                break;
            }
        }
        target = t;
    }

    @Override
    public void run() {
        //TODO cant add points to geometries, so i dont know :D
        if (mGeometry instanceof Polygon) {
            Polygon poly = (Polygon) mGeometry;
            poly.getShell().getPoints().removeCoordinate(target);
        } else {
            throw new UnsupportedOperationException("Other geometries than Polygon are not yet supported");
        }
        mUndoable = true;
        mRedoable = false;
    }

    @Override
    public void undo() {
        if (mGeometry instanceof Polygon) {
            Polygon poly = (Polygon) mGeometry;
            poly.getShell().getPoints().addCoordinate(mIndex1, target);
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
