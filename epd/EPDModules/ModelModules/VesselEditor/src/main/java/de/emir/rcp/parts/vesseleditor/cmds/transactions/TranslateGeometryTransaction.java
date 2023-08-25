package de.emir.rcp.parts.vesseleditor.cmds.transactions;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.service.geometry.impl.GeometryTransform;

public class TranslateGeometryTransaction extends AbstractEditorTransaction {

    private boolean mUndoable = false;
    private boolean mRedoable = false;

    private Coordinate position;
    private Geometry geometry;

    private Coordinate original;

    public TranslateGeometryTransaction(Geometry geometry, Coordinate positon) {
        this.geometry = geometry;
        this.position = positon;

        this.original = new CoordinateImpl(positon);
    }

    @Override
    public void run() {
        translate(geometry, position);
        mUndoable = true;
        mRedoable = false;
    }


    @Override
    public void undo() {
        translate(geometry, original);
        mUndoable = false;
        mRedoable = true;
    }

    private void translate(Geometry geometry, Coordinate coordinate) {
        GeometryOperations operations = geometry.getDelegate();
        if (operations != null) {
            operations.invalidate();
        }


        GeometryTransform gt = new GeometryTransform();
        gt.transformGeometryLocal(coordinate, geometry);
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
