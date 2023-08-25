package de.emir.rcp.parts.vesseleditor.view.helper;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

import java.awt.*;

public class DragWrapper {
    private Shape shape;
    private Coordinate coordinate;
    private Coordinate original;
    private DragProperties dragProperties;

    public <T extends DragProperties> DragWrapper(Shape shape, Coordinate coordinate, T dragProperties) {
        this.shape = shape;
        this.coordinate = coordinate;
        this.original = new CoordinateImpl(this.coordinate);
        this.dragProperties = dragProperties;
    }

    public void execute(UStructuralFeature horizontalFeature, UStructuralFeature verticalFeature) {
        CompoundTransaction compoundTransaction = new CompoundTransaction();

        if (dragProperties.isTransform3D()) {

            if (horizontalFeature == null || verticalFeature == null) return;

            double x = (double) coordinate.uGet(horizontalFeature);
            double y = (double) coordinate.uGet(verticalFeature);

            coordinate.set(original.getX(), original.getY(), original.getZ());

            compoundTransaction.add(new SetValueTransaction(coordinate, horizontalFeature, x));
            compoundTransaction.add(new SetValueTransaction(coordinate, verticalFeature, y));
        } else if (dragProperties.isTransformSingle()) {
            if (dragProperties.isTransformX()) {
                double x = coordinate.getX();

                coordinate.set(original);

                compoundTransaction.add(new SetValueTransaction(coordinate, SpatialPackage.Literals.Coordinate_x, x));
            } else if (dragProperties.isTransformY()) {
                double y = coordinate.getY();

                coordinate.set(original);

                compoundTransaction.add(new SetValueTransaction(coordinate, SpatialPackage.Literals.Coordinate_y, y));
            } else {
                double z = coordinate.getZ();

                coordinate.set(original);

                compoundTransaction.add(new SetValueTransaction(coordinate, SpatialPackage.Literals.Coordinate_z, z));
            }
        } else {
            double x = coordinate.getX();
            double y = coordinate.getY();

            coordinate.set(original);

            compoundTransaction.add(new SetValueTransaction(coordinate, SpatialPackage.Literals.Coordinate_x, x));
            compoundTransaction.add(new SetValueTransaction(coordinate, SpatialPackage.Literals.Coordinate_y, y));
        }

        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(compoundTransaction);
    }

    public void moveCoordinate(Coordinate target, UStructuralFeature horizontalFeature, UStructuralFeature verticalFeature) {
        if (dragProperties.isTransform3D()) {
            if (horizontalFeature == null || verticalFeature == null) return;

            coordinate.uSet(horizontalFeature, target.getX());
            coordinate.uSet(verticalFeature, target.getY());
        } else if (dragProperties.isTransformSingle()) {
            if (dragProperties.isTransformX()) {
                coordinate.setX(target.getX());
            } else if (dragProperties.isTransformY()) {
                coordinate.setY(target.getY());
            } else {
                coordinate.setZ(target.getZ());
            }
        } else {
            coordinate.set(target);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Coordinate getOriginal() {
        return original;
    }

    public <T extends DragProperties> T getDragProperties() {
        return (T) dragProperties;
    }
}
