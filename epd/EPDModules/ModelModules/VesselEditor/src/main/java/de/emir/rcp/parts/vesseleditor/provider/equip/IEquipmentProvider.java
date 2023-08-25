package de.emir.rcp.parts.vesseleditor.provider.equip;

import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.rcp.parts.vesseleditor.provider.ITransferableProvider;

import java.util.Collection;

public interface IEquipmentProvider extends ITransferableProvider {

    /**
     * collects the current existing equipment of the provided type, inside the pobj.
     *
     * @param pobj equipped object
     * @return list of equipment as created by this provider. May return null
     */
    Collection<LocatableObject> collectExistingEquipment(PhysicalObject pobj);


}
