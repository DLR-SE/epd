package de.emir.rcp.parts.vesseleditor.provider.equip.impl;

import de.emir.model.domain.maritime.vessel.DynamicSystem;
import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.domain.maritime.vessel.InternalCombustionEngine;
import de.emir.model.domain.maritime.vessel.PropulsionSystem;
import de.emir.model.domain.maritime.vessel.impl.DynamicSystemImpl;
import de.emir.model.domain.maritime.vessel.impl.InternalCombustionEngineImpl;
import de.emir.model.domain.maritime.vessel.impl.PropulsionSystemImpl;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.parts.vesseleditor.provider.equip.AbstractEquipmentProvider;
import de.emir.tuml.ucore.runtime.logging.ULog;

import java.util.ArrayList;
import java.util.Collection;

public class EngineEQProvider extends AbstractEquipmentProvider {

    public EngineEQProvider() {
        super("Internal Combustion Engine", "icons/InternalCombustionEngine.png", "Internal Combustion Engine");
    }

    @Override
    public boolean place(PhysicalObject target, Coordinate position, EulerImpl orientation) {
        DynamicSystem ds = target.getFirstCharacteristic(DynamicSystem.class, true);
        if (ds == null) {
            ULog.info("Create new Dynamic System for: " + target);
            target.getCharacteristics().add(ds = new DynamicSystemImpl());
        }
        PropulsionSystem ps = ds.getPropulsion();
        if (ps == null) {
            ULog.info("Create new Propulsion System for : " + target);
            ds.setPropulsion(ps = new PropulsionSystemImpl());
        }
        InternalCombustionEngine ice = new InternalCombustionEngineImpl();
        String name = "ICE_";
        if (target.getName() != null && target.getName().getCode() != null && target.getName().getCode().isEmpty() == false)
            name = target.getName().getCode() + "_ICE_";
        name = name + (ps.getEngines().size() + 1);
        ice.setName(name);


        if (ice.getPose() != null) {
            ice.getPose().set(position, orientation);
        } else {
            ice.setPose(new PoseImpl(position, orientation));
        }

        //TODO didn't work this way
//        SetValueTransaction setValueTransaction = new SetValueTransaction(ps, VesselPackage.Literals.ControlSurfaces_rudders, ice);
//        ModelManager.getModelProvider().getTransactionStack().run(setValueTransaction);
        ps.getEngines().add(ice);
        return true;
    }

    @Override
    public Collection<LocatableObject> collectExistingEquipment(PhysicalObject pobj) {
        DynamicSystem ds = pobj.getFirstCharacteristic(DynamicSystem.class, true);
        if (ds == null) return null;
        PropulsionSystem ps = ds.getPropulsion();
        if (ps == null) return null;
        ArrayList<LocatableObject> iceL = new ArrayList<>();
        for (Engine en : ps.getEngines())
            if (en instanceof InternalCombustionEngine)
                iceL.add(en);
        return iceL;
    }
}
