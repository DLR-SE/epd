package de.emir.epd.routemanager.editor;

import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.model.transactions.SetValueTransaction;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Table model for a list of WayPoints of a route. It lets users edit wp name, coordinate,
 * radius, speed and xtd settings. Each change will be executed by the ModelTransactionStack
 * to make changes reversible.
 */
class WaypointTableModel extends AbstractTableModel {

    private static final double DEFAULT_SPEED = 10.0; // knots
    private static final double DEFAULT_XTD = 0.3; // nautical miles

    private final List<Waypoint> waypointList;

    private ModelTransactionStack ts;

    private final String[] columnNames = new String[]{
            "Name",
            "Latitude",
            "Longitude",
            "Rad",
            "Min Speed (KNOTS)",
            "Max Speed (KNOTS)",
            "XTD S",
            "XTD P"
    };
    private final Class<?>[] columnClass = new Class[]{
            String.class,
            Double.class,
            Double.class,
            Double.class,
            Double.class,
            Double.class,
            Double.class,
            Double.class
    };

    public WaypointTableModel(List<Waypoint> waypointList) {
        this.waypointList = waypointList;

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
        mp.subscribeTransactionStack(opt -> ts = opt.orElse(null));
        ts = mp.getTransactionStack();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return waypointList.size();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Waypoint row = waypointList.get(rowIndex);
        if (0 == columnIndex) { // name
            row.setName((String) aValue);
        } else if (1 == columnIndex) { // position x
            row.getPosition().setX((Double) aValue);
        } else if (2 == columnIndex) { // position y
            row.getPosition().setY((Double) aValue);
        } else if (3 == columnIndex) { // radius
            row.setRadius(((Double) aValue));
        } else if (4 == columnIndex) { // min speed

            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Speed speed = leg.getPlanSpeedMin();
            if (speed == null){
                leg.setPlanSpeedMin(speed = new SpeedImpl(DEFAULT_SPEED, SpeedUnit.KNOTS));
            }

            if (speed.getUnit() != SpeedUnit.KNOTS){
                speed.setUnit(SpeedUnit.KNOTS);
            }

            if (ts != null){
                ts.run(
                        new SetValueTransaction(
                                speed,
                                UnitsPackage.init().getMeasure_value(),
                                aValue
                        )
                );
            } else {
                speed.setValue((Double) aValue);
            }

        } else if (5 == columnIndex) { // max speed

            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Speed speed = leg.getPlanSpeedMax();
            if (speed == null){
                leg.setPlanSpeedMax(speed = new SpeedImpl(DEFAULT_SPEED, de.emir.model.universal.units.SpeedUnit.KNOTS));
            }

            if (speed.getUnit() != SpeedUnit.KNOTS){
                speed.setUnit(SpeedUnit.KNOTS);
            }

            if (ts != null){
                ts.run(
                        new SetValueTransaction(
                                speed,
                                UnitsPackage.init().getMeasure_value(),
                                aValue
                        )
                );
            } else {
                speed.setValue((Double) aValue);
            }
        } else if (6 == columnIndex) { // starboard xtd

            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }
            // cannot use getStarboardXTDNotNull as it does not set the distance within the object
            Distance distance = leg.getStarboardXTD();
            if (distance == null){
                leg.setStarboardXTD(distance = new DistanceImpl(DEFAULT_XTD, DistanceUnit.NAUTICAL_MILES));
            }

            if (distance.getUnit() != DistanceUnit.NAUTICAL_MILES){
                distance.setUnit(DistanceUnit.NAUTICAL_MILES);
            }

            if (ts != null){
                ts.run(
                        new SetValueTransaction(
                                distance,
                                UnitsPackage.init().getMeasure_value(),
                                aValue
                        )
                );
            } else {
                distance.setValue((Double) aValue);
            }
        } else if (7 == columnIndex) { // portside xtd

            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            // cannot use getPortsideXTDNotNull as it does not set the distance within the object
            Distance distance = leg.getPortsideXTD();
            if (distance == null){
                leg.setPortsideXTD(distance = new DistanceImpl(DEFAULT_XTD, DistanceUnit.NAUTICAL_MILES));
            }

            if (distance.getUnit() != DistanceUnit.NAUTICAL_MILES){
                distance.setUnit(DistanceUnit.NAUTICAL_MILES);
            }

            if (ts != null){
                ts.run(
                        new SetValueTransaction(
                                distance,
                                UnitsPackage.init().getMeasure_value(),
                                aValue
                        )
                );
            } else {
                distance.setValue((Double) aValue);
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Waypoint row = waypointList.get(rowIndex);
        if (0 == columnIndex) { // name
            return row.getName();
        } else if (1 == columnIndex) { // pos x
            return row.getPosition().getX();
        } else if (2 == columnIndex) { // pos y
            return row.getPosition().getY();
        } else if (3 == columnIndex) { // radius
            return row.getRadius();
        } else if (4 == columnIndex) { // min speed
            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Speed speed = leg.getPlanSpeedMin();
            if (speed == null){
                leg.setPlanSpeedMin(speed = new SpeedImpl(DEFAULT_SPEED, de.emir.model.universal.units.SpeedUnit.KNOTS));
            }

            return speed.getAs(SpeedUnit.KNOTS);
        } else if (5 == columnIndex) { // max speed
            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Speed speed = leg.getPlanSpeedMax();
            if (speed == null){
                leg.setPlanSpeedMax(speed = new SpeedImpl(DEFAULT_SPEED, de.emir.model.universal.units.SpeedUnit.KNOTS));
            }

            return speed.getAs(SpeedUnit.KNOTS);
        } else if (6 == columnIndex) { // starboard xtd
            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Distance distance = leg.getStarboardXTD();
            if (distance == null){
                leg.setStarboardXTD(distance = new DistanceImpl(0, DistanceUnit.NAUTICAL_MILES));
            }

            return distance.getAs(DistanceUnit.NAUTICAL_MILES);
        } else if (7 == columnIndex) { // portside xtd
            Leg leg = row.getLeg();
            if (leg == null) {
                row.setLeg(leg = new LegImpl());
            }

            Distance distance = leg.getPortsideXTD();
            if (distance == null){
                leg.setPortsideXTD(distance = new DistanceImpl(0, DistanceUnit.NAUTICAL_MILES));
            }

            return distance.getAs(DistanceUnit.NAUTICAL_MILES);
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
