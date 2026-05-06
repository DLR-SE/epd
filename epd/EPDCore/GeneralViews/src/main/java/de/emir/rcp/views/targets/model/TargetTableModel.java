package de.emir.rcp.views.targets.model;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.units.*;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.targets.basic.TargetTableBasic;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Table model for the target table. This handles modeling of the rows based on the stored physical objects.
 */
public class TargetTableModel extends AbstractTableModel {
    // For better visualization, distance is referred to as Range (RNG) in the table.
    private final String[] columns = {"TYPE", "NAME", "ID", "BRG", "RNG", "CPA", "TCPA"};
    private final List<TargetRow> targets = Collections.synchronizedList(new ArrayList<>());
    // The reference determines the current point with which distance, bearing, tcpa and cpa are calculated from. If
    // the reference is not set, these will not be calculated.
    private PhysicalObject reference;
    private final Object lock = new Object();
    private final ITreeValueChangeListener updateListener = notification -> updateModel();
    private static final PropertyContext ctx = PropertyStore.getContext(TargetTableBasic.PROP_CTX);
    private static final IProperty<Integer> TCPA_ALERT_THRESHOLD = ctx.getProperty(TargetTableBasic.TCPA_ALERT_THRESHOLD, 60);
    private static final IProperty<Double> CPA_ALERT_THRESHOLD = ctx.getProperty(TargetTableBasic.CPA_ALERT_THRESHOLD, 0.5);
    private static final IProperty<Double> DIST_ALERT_THRESHOLD = ctx.getProperty(TargetTableBasic.DISTANCE_ALERT_THRESHOLD, 0.1);

    // If alerts are disabled, the alert flags for each row will not be set. If calculations are disabled, the whole
    // calculation of tcpa, cpa, bearing and distance will not happen.
    private static final IProperty<Boolean> ENABLE_ALERTS = ctx.getProperty(TargetTableBasic.ENABLE_ALERTS, true);
    private static final IProperty<Boolean> ENABLE_CALCULATIONS = ctx.getProperty(TargetTableBasic.ENABLE_CALCULATIONS, true);

    /**
     * Creates a new target table model.
     */
    public TargetTableModel() {
        PropertyChangeListener propertyListener = evt -> updateModel();
        ENABLE_ALERTS.addPropertyChangeListener(propertyListener);
        ENABLE_CALCULATIONS.addPropertyChangeListener(propertyListener);
        TCPA_ALERT_THRESHOLD.addPropertyChangeListener(propertyListener);
        CPA_ALERT_THRESHOLD.addPropertyChangeListener(propertyListener);
        DIST_ALERT_THRESHOLD.addPropertyChangeListener(propertyListener);
    }

    /**
     * Gets the number of rows for the model.
     * @return Number of rows.
     */
    @Override
    public int getRowCount() {
        return targets.size();
    }

    /**
     * Gets the number of columns for the model.
     * @return Number of columns.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Gets the name of a column at index c.
     * @param c  the column being queried.
     * @return Name of the column.
     */
    @Override
    public String getColumnName(int c) {
        return columns[c];
    }

    /**
     * Updates the model. This issues a bearing, cpa, tcpa and distance recalculation call to all rows.
     */
    public void updateModel() {
        if (targets.isEmpty()) return;
        // TODO check if parallel stream is the right way here as is does not guarantee parallelization.
        List<CalculationResult> results = new ArrayList<>(targets).parallelStream()
                .map(row -> row.updateCalculations(reference))
                .toList();
        SwingUtilities.invokeLater(() -> {
            for(CalculationResult result : results) {
                if(result != null && result.row != null) result.row.applyResult(result);
            }
            fireTableRowsUpdated(0, targets.size() - 1 > 0 ? targets.size() -1 : 0);
        });
    }

    /**
     * Gets the physicalobject associated to a specific row of the table.
     * @param row Row to get object for.
     * @return Object if found, else null;
     */
    public PhysicalObject getTargetAt(int row) {
        if (row >= this.targets.size() || row < 0) {
            return null;
        } else {
            return this.targets.get(row).target;
        }
    }

    /**
     * Updates a specific target. This also adds the target if it does not exist.
     * @param target Target to update.
     */
    public void updateTarget(PhysicalObject target) {
        if (target == reference) {
            // If the reference has changed update the calculations for all objects.
            updateModel();
            fireTableRowsUpdated(0, targets.size() - 1 > 0 ? targets.size() -1 : 0);
        } else {
            TargetRow row = addOrGetTarget(target);
            row.applyResult(row.updateCalculations(reference));
            int rowIndex = this.targets.indexOf(row);
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    /**
     * Adds a target to the model.
     * @param target Target to add.
     */
    public void addTarget(PhysicalObject target) {
        addOrGetTarget(target);
    }

    /**
     * Adds a target row to the model if it does not exist or return an existing target row.
     * @param target Target to get row for.
     * @return Target row.
     */
    private TargetRow addOrGetTarget(PhysicalObject target) {
        synchronized (lock) {
            TargetRow row = this.targets.stream().filter(t -> t.target.hashCode() == target.hashCode()).findFirst().orElse(null);
            if (row == null) {
                row = new TargetRow(target);
                target.registerTreeListener(updateListener);
                this.targets.add(row);
                int rowIndex = this.targets.indexOf(row);
                fireTableRowsInserted(rowIndex, rowIndex);
            }
            return row;
        }
    }

    /**
     * Gets the target row object at a given index. In comparison to getTargetAt this returns the row data wrapper which
     * includes cached information.
     * @param row Row to get target row object for.
     * @return Target row object if found, else null.
     */
    public TargetRow getRowData(int row) {
        if (row < 0 || row >= targets.size()) return null;
        return targets.get(row);
    }

    /**
     * Removes a target from the model.
     * @param target Target to remove row data for.
     */
    public void removeTarget(PhysicalObject target) {
        synchronized (lock) {
            TargetRow row = this.targets.stream().filter(t -> t.target == target).findFirst().orElse(null);
            if (row != null) {
                if (row.target != null) row.target.removeTreeListener(updateListener);
                int rowIndex = this.targets.indexOf(row);
                this.targets.remove(row);
                fireTableRowsDeleted(rowIndex, rowIndex);
            }
        }
    }

    /**
     * Checks if the row data contains a given target.
     * @param target Target to check.
     * @return True if row data for target exists, else false.
     */
    public boolean containsTarget(PhysicalObject target) {
        List<TargetTableModel.TargetRow> copy = new ArrayList<>(this.targets);
        TargetRow row = copy.stream().filter(t -> t.target.hashCode() == target.hashCode()).findFirst().orElse(null);
        return row != null;
    }

    /**
     * Clears the target row data.
     */
    public void clearTargets() {
        synchronized (lock) {
            if (!this.targets.isEmpty()) {
                int lastIndex =  targets.size() - 1 > 0 ? targets.size() -1 : 0;
                this.targets.clear();
                fireTableRowsDeleted(0, lastIndex);
            }
        }
    }

    /**
     * Sets the current reference object. This determines the starting point for all tcpa, bearing, distance and cpa
     * calculations. Afterwards, all calculations are reloaded.
     * @param reference Reference point from which to do calculations.
     */
    public void setReference(PhysicalObject reference) {
        this.reference = reference;
        updateModel();
    }

    /**
     * Gets the class of a specific column.
     * @param columnIndex  the column being queried.
     * @return Double for all classes containing double values, else string,
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 3, 4, 5, 6 -> Double.class;
            default -> String.class;
        };
    }

    /**
     * Gets the value for a given table cell.
     * @param row        the row whose value is to be queried.
     * @param col     the column whose value is to be queried.
     * @return Value for the given cell.
     */
    @Override
    public Object getValueAt(int row, int col) {
        if (row < 0 || row >= targets.size()) return null;
        TargetRow data = targets.get(row);
        return switch (col) {
            case 0 -> data.target != null ? data.target.getUClassifier().getName() : null;
            case 1 -> data.target != null ? data.target.getNameAsString() : null;
            case 2 -> data.id;
            case 3 -> data.bearing;
            case 4 -> data.distance;
            case 5 -> data.cpa;
            case 6 -> data.tcpa;
            default -> null;
        };
    }

    /**
     * Resolves the ID for a PhysicalObject. For Vessel, it is the MMSI and for Target/TrackedTarget the id. Else
     * the nameAsString method is used.
     * @param t Object to check.
     * @return ID/MMSI/Name of the object depending on its class.
     */
    private String resolveId(PhysicalObject t) {
        return switch (t) {
            case ITarget it -> it.getId();
            case ITrackedTarget itt -> itt.getId();
            case Vessel v -> String.valueOf(v.getMmsi());
            default -> t.getNameAsString();
        };
    }

    /**
     * Caching class for storing calculation results before applying them to the table. This allows parallel calculation
     * of results and separation from calculating and applying with the EDT thread.
     */
    class CalculationResult {
        Double bearing, distance, cpa, tcpa;
        boolean distAlert, cpaAlert, tcpaAlert;
        TargetRow row;

        CalculationResult(TargetRow row) {
            this.row = row;
        }
    }

    /**
     * Wrapper class for storing row data for each PhysicalObject.
     * @implNote Sorting seems to be broken for the table for null values. In order to establish sorting the
     * way intended (i.e. critical values before unknown values) Double.MAX_VALUE is used as a placeholder for
     * unknown values.
     */
    public class TargetRow {
        public PhysicalObject target;
        public String id;
        public Double bearing, distance, cpa, tcpa;
        public boolean distAlert, cpaAlert, tcpaAlert;

        /**
         * Creates a new TargetRow.
         * @param target Target to use as base.
         */
        TargetRow(PhysicalObject target) {
            this.target = target;
            this.id = resolveId(target);
            this.bearing = this.distance = this.cpa = this.tcpa = Double.MAX_VALUE;
        }

        /**
         * Performs tcpa, cpa, distance and bearing calculations for a given target row. This does not apply
         * the values to the TargetRow yet but returns them cached. In order to apply, use the applyResult method.
         * @implNote Sorting seems to be broken for the table for null values. In order to establish sorting the
         * way intended (i.e. critical values before unknown values) Double.MAX_VALUE is used as a placeholder for
         * unknown values.
         * @param ref Reference to calculate values for.
         * @return Calculation result.
         */
        CalculationResult updateCalculations(PhysicalObject ref) {
            CalculationResult res = null;
            if (ENABLE_CALCULATIONS.getValue()) {
                res = new CalculationResult(this);
                res.bearing = res.distance = res.cpa = res.tcpa = null;
                res.distAlert = res.cpaAlert = res.tcpaAlert = false;
                if (ref != null && ref.getPose() != null && ref.getPose().getCoordinate() != null
                        && target != null && target.getPose() != null && target.getPose().getCoordinate() != null) {
                    Angle bearingCalc = PhysicalObjectUtils.getAbsoluteBearing(reference.getPose().getCoordinate(), target.getPose().getCoordinate());
                    if (bearingCalc != null) {
                        res.bearing = bearingCalc.getAs(AngleUnit.DEGREE);
                    } else {
                        res.bearing = Double.MAX_VALUE;
                    }
                    Distance distanceCalc = reference.getPose().getCoordinate().getDistance(target.getPose().getCoordinate());
                    if (distanceCalc != null) {
                        res.distance = distanceCalc.getAs(DistanceUnit.NAUTICAL_MILES);
                        if (res.distance < 0) res.distance = Double.MAX_VALUE;
                        res.distAlert = res.distance < DIST_ALERT_THRESHOLD.getValue() && ENABLE_ALERTS.getValue();
                    } else {
                        res.distance = Double.MAX_VALUE;
                    }
                    Angle course1 = PhysicalObjectUtils.getCOG(reference);
                    Angle course2 = PhysicalObjectUtils.getCOG(target);
                    Speed speed1 = PhysicalObjectUtils.getSOG(reference);
                    Speed speed2 = PhysicalObjectUtils.getSOG(target);
                    if (course1 != null && course2 != null && speed1 != null && speed2 != null) {
                        Distance cpaCalc = PhysicalObjectUtils.getDistanceAtClosedPointOfApproach(reference.getPose().getCoordinate(),
                                course1, speed1,
                                target.getPose().getCoordinate(),
                                course2, speed2);
                        if (cpaCalc != null) {
                            res.cpa = cpaCalc.getAs(DistanceUnit.NAUTICAL_MILES);
                            if (res.cpa < 0) res.cpa = Double.MAX_VALUE;
                            res.cpaAlert = res.cpa < CPA_ALERT_THRESHOLD.getValue() && ENABLE_ALERTS.getValue();
                        } else {
                            res.cpa = Double.MAX_VALUE;
                        }
                        Time tcpaCalc = PhysicalObjectUtils.getTimeToClosedPointOfApproach(reference.getPose().getCoordinate(),
                                course1, speed1,
                                target.getPose().getCoordinate(),
                                course2, speed2);
                        if (tcpaCalc != null) {
                            res.tcpa = tcpaCalc.getAs(TimeUnit.SECOND);
                            if (res.tcpa < 0) res.tcpa = Double.MAX_VALUE;
                            res.tcpaAlert = res.tcpa < TCPA_ALERT_THRESHOLD.getValue() && ENABLE_ALERTS.getValue();
                        } else {
                            res.tcpa = Double.MAX_VALUE;
                        }
                    } else {
                        res.tcpa = Double.MAX_VALUE;
                        res.cpa = Double.MAX_VALUE;
                    }
                } else {
                    res.bearing = Double.MAX_VALUE;
                    res.distance = Double.MAX_VALUE;
                    res.cpa = Double.MAX_VALUE;
                    res.tcpa = Double.MAX_VALUE;
                }

            }
            return res;
        }

        /**
         * Applies the calculation result from updateCalculations if it is not null.
         * @param res Result to apply.
         */
        void applyResult(CalculationResult res) {
            if(res == null) return;
            this.bearing = res.bearing;
            this.distance = res.distance;
            this.cpa = res.cpa;
            this.tcpa = res.tcpa;
            this.distAlert = res.distAlert;
            this.tcpaAlert = res.tcpaAlert;
            this.cpaAlert = res.cpaAlert;
        }
    }
}
