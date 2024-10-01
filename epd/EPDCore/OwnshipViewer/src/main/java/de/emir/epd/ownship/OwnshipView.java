package de.emir.epd.ownship;

import de.emir.epd.ais.AisTargetView;
import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.model.domain.maritime.vessel.VesselUtils;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class OwnshipView extends AisTargetView {

    protected ITreeValueChangeListener targetTreeListener;
    protected Vessel ownship;


    public OwnshipView(String id) {
        super(id);

    }

    @Override
    protected void addListeners() {
        ownship = EPDModelUtils.retrieveOwnship();
        targetTreeListener = notification -> updateInformation(ownship);

        EPDModelUtils.subscribeModelChange("ownship", event -> {
			if(event.getNewValue() instanceof Vessel) {
                updateCurrentTarget();
            }
		});

        updateCurrentTarget();
        
//        targetTreeListener = notification -> updateInformation(currentTarget);
    }

    @Override
    protected void updateCurrentTarget() {
        ownship = EPDModelUtils.retrieveOwnship();
        if (ownship != null) // We found an ownship
        {
            if (currentTarget != null) { // We have a current ship
                if (!ownship.equals(currentTarget)) { // They are not the same, so reregister treelistener
                    try {
                        currentTarget.removeTreeListener(targetTreeListener);
                        currentTarget = ownship;
                        currentTarget.registerTreeListener(targetTreeListener);
                    } catch (Exception e) {
                        ULog.error(e.getMessage());
                    }
                }
            } else { //No current ship is set yet
                currentTarget = ownship;
                currentTarget.registerTreeListener(targetTreeListener);
            }
        }
        updateInformation(currentTarget);
    }
    
    protected void updateInformation(Vessel updateTarget) {
        if (updateTarget == null) {

            nameLbl.setText("Unknown");
            cogLbl.setText("Unknown");
            sogLbl.setText("Unknown");
            mmsiLbl.setText("Unknown");

            typeLbl.setText("Unknown");
            destinationLbl.setText("Unknown");
            dimensionsLbl.setText("(Unknown, Unknown)");
            draughtLbl.setText("Unknown");
            sogLbl.setText("Unknown");
            cogLbl.setText("Unknown");
            headingLbl.setText("Unknown");
            rotLbl.setText("Unknown");

            return;
        }

        Vessel v = updateTarget;

        String name = v.getNameAsString();

        if (name != null) {
            nameLbl.setText(name);
        } else {
            nameLbl.setText("Unknown");
        }

        VesselType type = v.getType();
        if (type != null) {
            typeLbl.setText(type.getLabel());
        } else {
            typeLbl.setText("Unknown");
        }

        String destination = VesselUtils.getDestination(v);
        if (destination != null) {
            destinationLbl.setText(destination);
        } else {
            destinationLbl.setText("Unknown");
        }

        Length shipLength = PhysicalObjectUtils.getLength(v);
        Length shipWidth = PhysicalObjectUtils.getWidth(v);
        if (shipLength != null && shipWidth != null) {

            float length = (float) shipLength.getAs(DistanceUnit.METER);
            float width = (float) shipWidth.getAs(DistanceUnit.METER);
            dimensionsLbl.setText(String.format("(%.1fm, %.1fm)", length, width));

        } else {
            dimensionsLbl.setText("(Unknown, Unknown)");
        }

        Length draftLength = VesselUtils.getDraft(v);
        if (draftLength != null) {
            float draft = (float) draftLength.getAs(DistanceUnit.METER);
            draughtLbl.setText(String.format("%.1fm", draft));
        }

        Pose pose = v.getPose();
        if (pose != null) {
            Coordinate coord = pose.getCoordinate();
            if (coord != null) {
                posLbl.setText(
                        CRSUtils.toDegreeMinuteSecond(coord.getLatitude()) +
                        " / " +
                        CRSUtils.toDegreeMinuteSecond(coord.getLongitude())
                );
            } else {
                posLbl.setText("Unknown");
            }
        } else {
            posLbl.setText("Unknown");
        }

        Speed sogSpeed = PhysicalObjectUtils.getSOG(v);
        if (sogSpeed != null) {
            float sog = (float) sogSpeed.getAs(SpeedUnit.KNOTS);
            if (Float.compare(sog, 102.3f)==0) {
                sogLbl.setText("Not available");
            } else {
                sogLbl.setText(String.format("%.1fkn", sog));
            }
            
        } else {
            sogLbl.setText("Unknown");
        }

        Angle cogAngle = PhysicalObjectUtils.getCOG(v);
        if (cogAngle != null) {
            float cog = (float) cogAngle.getAs(AngleUnit.DEGREE);
            if (Float.compare(cog, 360.0f)==0) {
                cogLbl.setText("Not available");
            } else {
                cogLbl.setText(String.format("%.1f°", cog));
            }
            
        } else {
            cogLbl.setText("Unknown");
        }

        Angle headingAngle = PhysicalObjectUtils.getHeading(v);
        if (headingAngle != null) {
            float heading = (float) headingAngle.getAs(AngleUnit.DEGREE);
            if (Float.compare(heading, 511.0f)==0) {
                headingLbl.setText("Not available");
            } else {
                headingLbl.setText(String.format("%.1f°", heading));
            }
            
        } else {
            headingLbl.setText("Unknown");
        }

        AngularSpeed rotSpeed = PhysicalObjectUtils.getRateOfTurn(v);
        if (rotSpeed != null) {
            double rot = rotSpeed.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE);
            if (Double.compare(rot, -128.0) == 0) {
                rotLbl.setText("Not available");
            } else {
                rotLbl.setText(String.format("%.1f°/min", rot));
            }
            
        } else {
            rotLbl.setText("Unknown");
        }

        long mmsi = v.getMmsi();

        mmsiLbl.setText(mmsi + "");
    }

}
