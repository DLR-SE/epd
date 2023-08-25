package de.emir.rcp.parts.vesseleditor.provider;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.impl.EulerImpl;

import java.net.URL;

public interface ITransferableProvider {
    String getLabel();

    URL getIconURL();

    String getToolTip();

    /**
     * creates a new instance of the provided element and insert it into the target object
     *
     * @param target      the object, that should be equipped
     * @param position    the position of the equipment
     * @param orientation the orientation of the equipment, if required
     * @return if or if not the equipment has been created
     */
    boolean place(PhysicalObject target, Coordinate position, EulerImpl orientation);
}
