package de.emir.epd.routemanager.editor;

import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class WaypointTableModel extends AbstractTableModel {
    private final List<Waypoint> waypointList;

    private final String[] columnNames = new String[]{"Name", "Latitude", "Longitude", "Rad",
            "Min Speed (KNOTS) ", "Max Speed (KNOTS) ", "XTD S", "XTD P"};
    private final Class[] columnClass = new Class[]{String.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,};

    public WaypointTableModel(List<Waypoint> waypointList) {
        this.waypointList = waypointList;
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
        if (0 == columnIndex) {
            row.setName((String) aValue);
        } else if (1 == columnIndex) {
            row.getPosition().setX((Double) aValue);
        } else if (2 == columnIndex) {
            row.getPosition().setY((Double) aValue);
        } else if (3 == columnIndex) {
            row.setRadius(((Double) aValue));
        } else if (4 == columnIndex) {
            try {
                row.getLeg().getPlanSpeedMin().set(((Double) aValue), SpeedUnit.KNOTS);
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                    row.getLeg().getPlanSpeedMin().set(((Double) aValue), SpeedUnit.KNOTS);
                }
            }
        } else if (5 == columnIndex) {
            try {
                row.getLeg().getPlanSpeedMax().set(((Double) aValue), SpeedUnit.KNOTS);
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                    row.getLeg().getPlanSpeedMax().set(((Double) aValue), SpeedUnit.KNOTS);
                }
            }
        } else if (6 == columnIndex) {
            try {
                row.getLeg().getStarboardXTD().setValue(((Double) aValue));
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                    row.getLeg().getStarboardXTD().setValue(((Double) aValue));
                }
            }
        } else if (7 == columnIndex) {
            try {
                row.getLeg().getPortsideXTD().setValue(((Double) aValue));
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                    row.getLeg().getPortsideXTD().setValue(((Double) aValue));
                }
            }
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Waypoint row = waypointList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getName();
        } else if (1 == columnIndex) {
            return row.getPosition().getX();
        } else if (2 == columnIndex) {
            return row.getPosition().getY();
        } else if (3 == columnIndex) {
            return row.getRadius();
        } else if (4 == columnIndex) {
            try {
                return row.getLeg().getPlanSpeedMin().getAs(SpeedUnit.KNOTS);
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                }

                row.getLeg().setPlanSpeedMin(new SpeedImpl(10.0, de.emir.model.universal.units.SpeedUnit.KNOTS));
                return row.getLeg().getPlanSpeedMin().getAs(SpeedUnit.KNOTS);
            }
        } else if (5 == columnIndex) {
            try {
                return row.getLeg().getPlanSpeedMax().getAs(SpeedUnit.KNOTS);
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                }
                row.getLeg().setPlanSpeedMax(new SpeedImpl(10.0, de.emir.model.universal.units.SpeedUnit.KNOTS));
                return row.getLeg().getPlanSpeedMax().getAs(SpeedUnit.KNOTS);
            }
        } else if (6 == columnIndex) {
            try {
                return row.getLeg().getStarboardXTD().getValue();
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                }
                row.getLeg().setStarboardXTD(new DistanceImpl(0.3, DistanceUnit.NAUTICAL_MILES));
                return row.getLeg().getStarboardXTD().getValue();
            }
        } else if (7 == columnIndex) {
            try {
                return row.getLeg().getPortsideXTD().getValue();
            } catch (NullPointerException e) {
                if (row.getLeg() == null) {
                    row.setLeg(new LegImpl());
                }
                row.getLeg().setPortsideXTD(new DistanceImpl(0.3, DistanceUnit.NAUTICAL_MILES));
                return row.getLeg().getPortsideXTD().getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
