package de.emir.epd.ownship;

import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.rcp.views.AbstractScalingListView;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * The OwnshipView is a view component capable of displaying
 * important parameters of the ownship.
 */
public class OwnshipView extends AbstractScalingListView {
    protected ITreeValueChangeListener targetTreeListener;
    protected Vessel ownship;
    protected Vessel currentTarget;

    /**
     * Creates a new OwnshipView.
     * @param id Identifier of the view to use. This should be unique
     *           for retrieving the view later on.
     */
    public OwnshipView(String id) {
        super(id);
        setBaseFontSizes(18f, 26f);
        setHeightCalculation(true);
        clearLabels();
        // Use prototype labels for all values to prevent scaling
        // when values change.
        setPrototype("NAME", "X".repeat(20));
        setPrototype("MMSI", "X".repeat(9));
        setPrototype("POS", "XX° XX' XX'' / XX° XX' XX''");
        setPrototype("SOG", "XXX.Xkn");
        setPrototype("COG", "XXX.X°");
        setPrototype("HDG", "XXX.X°");
        setPrototype("ROT", "XXX.X°/min");
        addListeners();
    }

    /**
     * OnOpen handler which is called when the view is opened.
     */
    @Override
    public void onOpen() {

    }

    /**
     * OnClose handler which is called when the view is closed.
     */
    @Override
    public void onClose() {

    }

    /**
     * Adds model listeners in order to update the view dynamically
     * on model changes.
     */
    protected void addListeners() {
        ownship = EPDModelUtils.retrieveOwnship();
        targetTreeListener = notification -> updateInformation();
        EPDModelUtils.subscribeModelChange("ownship", event -> {
			if(event.getNewValue() instanceof Vessel) {
                updateCurrentTarget();
            }
		});
        updateCurrentTarget();
    }

    /**
     * Clears all labels back to their original state.
     */
    protected void clearLabels() {
        setEntry("NAME", "Unknown");
        setEntry("MMSI", "Unknown");
        setEntry("POS", "Unknown");
        setEntry("SOG", "Unknown");
        setEntry("COG", "Unknown");
        setEntry("HDG", "Unknown");
        setEntry("ROT", "Unknown");
        redraw();
    }

    /**
     * Removes listeners from the old target and registers them to
     * the new target on change of the current target.
     */
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
        if(rootPanel != null) updateInformation();
    }

    /**
     * Updates the information of all view components based on the currently
     * set target.
     */
    protected void updateInformation() {
        if (currentTarget == null) {
            clearLabels();
            return;
        }

        Vessel v = currentTarget;

        String name = v.getNameAsString();

        if (name != null) {
            setEntry("NAME", name);
        } else {
            setEntry("NAME", "Unknown");
        }

        Pose pose = v.getPose();
        if (pose != null) {
            Coordinate coord = pose.getCoordinate();
            if (coord != null) {
                setEntry("POS",
                        CRSUtils.toDegreeMinuteSecond(coord.getLatitude()) +
                                " / " +
                                CRSUtils.toDegreeMinuteSecond(coord.getLongitude()));
            } else {
                setEntry("POS", "Unknown");
            }
        } else {
            setEntry("POS", "Unknown");
        }

        Speed sogSpeed = PhysicalObjectUtils.getSOG(v);
        if (sogSpeed != null) {
            float sog = (float) sogSpeed.getAs(SpeedUnit.KNOTS);
            if (Float.compare(sog, 102.3f) == 0) {
                setEntry("SOG", "N/A");
            } else {
                setEntry("SOG", String.format("%.1fkn", sog));
            }
        } else {
            setEntry("SOG", "Unknown");
        }

        Angle cogAngle = PhysicalObjectUtils.getCOG(v);
        if (cogAngle != null) {
            float cog = (float) cogAngle.getAs(AngleUnit.DEGREE);
            if (Float.compare(cog, 360.0f) == 0) {
                setEntry("COG", "N/A");
            } else {
                setEntry("COG", String.format("%.1f°", cog));
            }
        } else {
            setEntry("COG", "Unknown");
        }

        Angle headingAngle = PhysicalObjectUtils.getHeading(v);
        if (headingAngle != null) {
            float heading = (float) headingAngle.getAs(AngleUnit.DEGREE);
            if (Float.compare(heading, 511.0f) == 0) {
                setEntry("HDG", "N/A");
            } else {
                setEntry("HDG",String.format("%.1f°", heading));
            }
        } else {
            setEntry("HDG", "Unknown");
        }

        AngularSpeed rotSpeed = PhysicalObjectUtils.getRateOfTurn(v);
        if (rotSpeed != null) {
            double rot = rotSpeed.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE);
            if (Double.compare(rot, -128.0) == 0) {
                setEntry("ROT", "N/A");
            } else {
                setEntry("ROT",String.format("%.1f°/min", rot));
            }

        } else {
            setEntry("ROT","Unknown");
        }

        long mmsi = v.getMmsi();

        setEntry("MMSI",mmsi + "");
        rootPanel.revalidate();
        rootPanel.repaint();
    }

}
