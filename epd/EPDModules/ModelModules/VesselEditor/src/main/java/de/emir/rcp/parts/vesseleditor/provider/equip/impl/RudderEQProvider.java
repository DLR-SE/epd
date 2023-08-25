package de.emir.rcp.parts.vesseleditor.provider.equip.impl;

import de.emir.model.domain.maritime.vessel.ControlSurfaces;
import de.emir.model.domain.maritime.vessel.DynamicSystem;
import de.emir.model.domain.maritime.vessel.Rudder;
import de.emir.model.domain.maritime.vessel.impl.ControlSurfacesImpl;
import de.emir.model.domain.maritime.vessel.impl.DynamicSystemImpl;
import de.emir.model.domain.maritime.vessel.impl.RudderImpl;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.parts.vesseleditor.provider.equip.AbstractEquipmentProvider;
import de.emir.tuml.ucore.runtime.logging.ULog;

import java.util.ArrayList;
import java.util.Collection;

public class RudderEQProvider extends AbstractEquipmentProvider {

    public RudderEQProvider() {
        super("Rudder", "icons/Rudder.png", "Rudder");
    }

    @Override
    public boolean place(PhysicalObject target, Coordinate position, EulerImpl orientation) {
        DynamicSystem ds = target.getFirstCharacteristic(DynamicSystem.class, true);
        if (ds == null) {
            ULog.info("Create new Dynamic System for: " + target);
            target.getCharacteristics().add(ds = new DynamicSystemImpl());
        }
        ControlSurfaces cs = ds.getControl();
        if (cs == null) {
            ULog.info("Create new ControlSurfaces for : " + target);
            ds.setControl(cs = new ControlSurfacesImpl());
        }

        Rudder rudder = new RudderImpl();
        String name = "Rudder_";
        if (target.getName() != null && target.getName().getCode() != null && target.getName().getCode().isEmpty() == false)
            name = target.getName().getCode() + "_Rudder_";
        name = name + (cs.getRudders().size() + 1);
        rudder.setName(name);
        if (rudder.getPose() != null)
            rudder.getPose().set(position, orientation);
        else
            rudder.setPose(new PoseImpl(position, orientation));

//        SetValueTransaction setValueTransaction = new SetValueTransaction(cs, VesselPackage.Literals.ControlSurfaces_rudders, rudder);
//        ModelManager.getModelProvider().getTransactionStack().run(setValueTransaction);
        cs.getRudders().add(rudder);
        return true;
    }

    @Override
    public Collection<LocatableObject> collectExistingEquipment(PhysicalObject pobj) {
        DynamicSystem ds = pobj.getFirstCharacteristic(DynamicSystem.class, true);
        if (ds == null) return null;
        ControlSurfaces cs = ds.getControl();
        if (cs == null) return null;
        return new ArrayList<>(cs.getRudders());
    }
}
