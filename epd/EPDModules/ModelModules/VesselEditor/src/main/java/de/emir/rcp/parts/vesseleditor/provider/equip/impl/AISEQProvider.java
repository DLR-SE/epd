package de.emir.rcp.parts.vesseleditor.provider.equip.impl;

import de.emir.model.application.sense.Sensor;
import de.emir.model.application.sense.SensorPort;
import de.emir.model.application.sense.impl.SensorImpl;
import de.emir.model.application.sensor.ais.AISPort;
import de.emir.model.application.sensor.ais.impl.AISPortImpl;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.parts.vesseleditor.provider.equip.AbstractEquipmentProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AISEQProvider extends AbstractEquipmentProvider {

    public AISEQProvider() {
        super(
                "AISEmitter",
                "icons/AIS.png",
                "Automatic Identification System Emitter"
        );
    }

    @Override
    public boolean place(PhysicalObject target, Coordinate position, EulerImpl orientation) {
        Sensor sensor = new SensorImpl();
        sensor.setName("AIS_Sensor");

        AISPort port = new AISPortImpl();
        port.setName("AIS_Port");
        sensor.getPorts().add(port);

        Pose pose = sensor.getPose();
        if (pose == null) {
            pose = new PoseImpl();
            sensor.setPose(pose);
        }
        pose.set(position, orientation);

        target.getChildren().add(sensor);
        return true;
    }

    @Override
    public Collection<LocatableObject> collectExistingEquipment(PhysicalObject target) {
        Sensor sensor = null;
        AISPort port;
        List<PhysicalObject> children = target.getChildren();
        for (PhysicalObject obj : children) {
            if (obj instanceof Sensor) {
                for (SensorPort p : ((Sensor) obj).getPorts()) {
                    if (p instanceof AISPort) {
                        sensor = (Sensor) obj;
                        port = (AISPort) p;
                        break;
                    }
                }
            }
        }
        List<LocatableObject> sensors = new ArrayList<>();
        if (sensor != null) {
            sensors.add(sensor);
        }

        return sensors;
    }
}
