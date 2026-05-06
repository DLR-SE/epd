package de.emir.epd.ais;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.model.domain.maritime.vessel.VesselUtils;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractScalingListView;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;

/**
 * UI component for the AIS target info display.
 */
public class AisTargetView extends AbstractScalingListView {
    protected Vessel currentSelected;
    protected Vessel currentFocused;
    protected ITreeValueChangeListener targetTreeListener;
    protected Vessel currentTarget;

    /**
     * Creates a new AisTargetView.
     * @param id Identifier of the view to use. This should be unique
     *           for retrieving the view later on.
     */
    public AisTargetView(String id) {
        super(id);
        setBaseFontSizes(18f, 26f);
        setHeightCalculation(true);
        clearLabels();
        // Use prototype labels for all values to prevent scaling
        // when values change.
        setPrototype("NAME", "X".repeat(20));
        setPrototype("MMSI", "X".repeat(9));
        setPrototype("TYPE", "X".repeat(20));
        setPrototype("LOA/BOA", "(XXX.Xm, XXX.Xm)");
        setPrototype("DEST", "X".repeat(20));
        setPrototype("DRFT", "XXX.Xm");
        setPrototype("POS", "XX° XX' XX'' / XX° XX' XX''");
        setPrototype("SOG", "XXX.Xkn");
        setPrototype("COG", "XXX.X°");
        setPrototype("HDG", "XXX.X°");
        setPrototype("ROT", "XXX.X°/min");
        addListeners();
    }

    /**
     * Clears all labels back to their original state.
     */
    protected void clearLabels() {
        setEntry("NAME", "Unknown");
        setEntry("MMSI", "Unknown");
        setEntry("TYPE", "Unknown");
        setEntry("LOA/BOA", "Unknown");
        setEntry("DEST", "Unknown");
        setEntry("DRFT", "Unknown");
        setEntry("POS", "Unknown");
        setEntry("SOG", "Unknown");
        setEntry("COG", "Unknown");
        setEntry("HDG", "Unknown");
        setEntry("ROT", "Unknown");
        redraw();
    }

    /**
     * Adds model listeners in order to update the view dynamically
     * on model changes.
     */
    protected void addListeners() {
        targetTreeListener = notification -> updateInformation();
        SelectionManager sm = PlatformUtil.getSelectionManager();
        // Listen to map focus events fromm the selection manager
        // when the mouse hovers over a vessel.
        sm.subscribe(MVBasic.MAP_FOCUS_CTX, oo -> {
            if (oo.isPresent() && oo.get() instanceof Vessel) {
                currentFocused = (Vessel) oo.get();
                // If the current selected target changed, remove tree listeners for the old
                // target, set the new target and register the listeners.
                if (currentTarget == null){
                    currentTarget = currentFocused;
                    currentTarget.registerTreeListener(targetTreeListener);
                } else if (currentTarget != currentFocused)  {
                    currentTarget.removeTreeListener(targetTreeListener);
                    currentTarget = currentFocused;
                    currentTarget.registerTreeListener(targetTreeListener);
                }
            } else {
                currentFocused = null;
            }
            updateInformation();
        });

        // Listen to selection events from the selection manager when
        // a vessel was clicked.
        sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {
            if (oo.isPresent() && oo.get() instanceof Vessel) {
                currentSelected = (Vessel) oo.get();
                if (currentTarget != currentSelected && currentSelected != null) {
                    if (currentTarget != null) {
                        currentTarget.removeTreeListener(targetTreeListener);
                    }
                    currentTarget = currentSelected;
                    currentTarget.registerTreeListener(targetTreeListener);
                }
            } else {
                currentSelected = null;
            }
            updateInformation();
        });
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
        String NAME = v.getNameAsString();
        if (NAME != null) {
            setEntry("NAME", NAME);
        } else {
            setEntry("NAME", "Unknown");
        }
        VesselType type = v.getType();
        if (type != null) {
            setEntry("TYPE", type.getLabel());
        } else {
            setEntry("TYPE", "Unknown");
        }
        String DEST = VesselUtils.getDestination(v);
        if (DEST != null) {
            setEntry("DEST", DEST);
        } else {
            setEntry("DEST", "Unknown");
        }
        Length shipLength = PhysicalObjectUtils.getLength(v);
        Length shipWidth = PhysicalObjectUtils.getWidth(v);
        if (shipLength != null && shipWidth != null) {
            float length = (float) shipLength.getAs(DistanceUnit.METER);
            float width = (float) shipWidth.getAs(DistanceUnit.METER);
            setEntry("LOA/BOA", String.format("(%.1fm, %.1fm)", length, width));
        } else {
            setEntry("LOA/BOA", "Unknown");
        }
        Length draftLength = VesselUtils.getDraft(v);
        if (draftLength != null) {
            float draft = (float) draftLength.getAs(DistanceUnit.METER);
            setEntry("DRFT", String.format("%.1fm", draft));
        } else {
            setEntry("DRFT", "Unknown");
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

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

}
