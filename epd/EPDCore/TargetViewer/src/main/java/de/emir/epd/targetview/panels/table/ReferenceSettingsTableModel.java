package de.emir.epd.targetview.panels.table;

import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Internal model of the reference settings table editor.
 */
public class ReferenceSettingsTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Sensor Identifier", "Mode", "Latitude", "Longitude"};
    private boolean dirty = false;
    private final List<Entry> entries = new ArrayList<>();
    private final PropertyContext context = PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT);
    private final IProperty<String> parentProperty = context.getProperty(TargetBasics.TARGET_VIEWER_PROP_REFERENCE_TARGETS, "");

    /**
     * Adds a new for to the table model based on an entry.
     *
     * @param entry Entry to insert into the table.
     */
    public void addRow(Entry entry) {
        entries.add(entry);
        dirty = true;
        fireTableRowsInserted(entries.size() - 1, entries.size() - 1);
    }

    /**
     * Removes a row from the table model.
     *
     * @param row Index of row to remove.
     */
    public void removeRow(int row) {
        entries.remove(row);
        dirty = true;
        fireTableRowsDeleted(row, row);
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return entries.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entry entry = entries.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> entry.sensorIdentifier;
            case 1 -> entry.mode;
            case 2 -> entry.coordinate != null ? entry.coordinate.getLatitude() : null;
            case 3 -> entry.coordinate != null ? entry.coordinate.getLongitude() : null;
            default -> null;
        };
    }

    /**
     * Returns a name for the current column-
     *
     * @param column the column being queried
     * @return a string containing the name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Checks if the current cell is editable. If Latitude or Longitude values are queried (column 2 or 3), the
     * cell is only marked as editable if the selected mode is FIXED. This is to prevent entering coordinates
     * for OWNSHIP or REFERENCE mode.
     *
     * @param rowIndex    the row being queried.
     * @param columnIndex the column being queried.
     * @return false if not editable, else true.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        try {
            if (columnIndex == 2 || columnIndex == 3) {
                return entries.get(rowIndex).mode.equals(Mode.FIXED);
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * Sets the value of a given column and row.
     *
     * @param aValue      value to assign to cell.
     * @param rowIndex    row of cell.
     * @param columnIndex column of cell.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Entry entry = entries.get(rowIndex);
            switch (columnIndex) {
                case 0 -> entry.sensorIdentifier = aValue.toString();
                case 1 -> {
                    entry.mode = (Mode) aValue;
                    if (entry.mode != Mode.FIXED) {
                        entry.coordinate = null;
                    } else if (entry.coordinate == null) {
                        entry.coordinate = new CoordinateImpl();
                    }
                }
                case 2 -> {
                    if (entry.coordinate == null) entry.coordinate = new CoordinateImpl();
                    entry.coordinate.setLatLon((Double) aValue, entry.coordinate.getLongitude());
                }
                case 3 -> {
                    if (entry.coordinate == null) entry.coordinate = new CoordinateImpl();
                    entry.coordinate.setLatLon(entry.coordinate.getLatitude(), (Double) aValue);
                }
            }
            dirty = true;
            fireTableCellUpdated(rowIndex, columnIndex);
        } catch (ClassCastException e) {
            ULog.error("Could not update target reference settings table. Wrong type specified.");
        } catch (IndexOutOfBoundsException e) {
            ULog.error(String.format("No entry in row %d exists", rowIndex));
        }
    }

    /**
     * Returns the class of the queried column.
     *
     * @param columnIndex the column being queried
     * @return The class of the objects stored in the column.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Mode.class;
            case 2, 3 -> Double.class;
            default -> Object.class;
        };
    }

    /**
     * Gets the entry of a given row.
     *
     * @param row Row index to query.
     * @return Entry of the row. If no row is found, null is returned.
     */
    public Entry getEntryAt(int row) {
        return entries.get(row);
    }

    /**
     * Saves the current contents of the table model to the property storage.
     */
    public void save() {
        if (parentProperty.getSubProperties() != null) {
            parentProperty.getSubProperties().clear();
        }
        for (ReferenceSettingsTableModel.Entry entry : entries) {
            ((AbstractProperty<String>) parentProperty).addChild(entry.toProperties());
        }
        // Set the property value for a short time to trigger property change listeners.
        // This is done since subproperties do not trigger the property change listener of the parent property.
        String prevValue = parentProperty.getValue();
        parentProperty.setValue("Update");
        parentProperty.setValue(prevValue);
        dirty = false;
    }

    /**
     * Loads the current contents of the table model from the property storage.
     */
    public void load() {
        entries.clear();
        if (parentProperty.getSubProperties() != null) {
            for (IProperty<?> property : parentProperty.getSubProperties()) {
                Entry entry = Entry.fromProperties(property);
                entries.add(entry);
            }
        }
        dirty = false;
    }

    /**
     * Checks if the current table model matched the stored config. If not, the model is flagged as save needed.
     *
     * @return True if no saving is required, else false.
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Data model representing the values of each row.
     */
    public static class Entry {
        String sensorIdentifier;
        Coordinate coordinate;
        Mode mode;

        /**
         * Creates a new entry object.
         *
         * @param sensorIdentifier Name of the sensor to set reference mode for.
         * @param coordinate       Coordinate of the reference to use.
         * @param mode             Reference mode to set.
         */
        public Entry(String sensorIdentifier, Coordinate coordinate, Mode mode) {
            this.sensorIdentifier = sensorIdentifier;
            this.coordinate = coordinate;
            this.mode = mode;
        }

        /**
         * Creates a new entry object with an empty coordinate reference point.
         *
         * @param sensorIdentifier Name of the sensor to set reference mode for.
         * @param mode             Reference mode to set.
         */
        public Entry(String sensorIdentifier, Mode mode) {
            this(sensorIdentifier, null, mode);
        }

        /**
         * Gets the name of the sensor for which the reference mode was configured.
         *
         * @return Name of the sensor matching the configuration.
         */
        public String getSensorIdentifier() {
            return sensorIdentifier;
        }

        /**
         * Gets the reference position for the configuration entry.
         *
         * @return Coordinate if set, else null.
         */
        public Coordinate getCoordinate() {
            return coordinate;
        }

        /**
         * Gets the mode for the configuration entry.
         *
         * @return Reference mode which was set for the configuration.
         */
        public Mode getMode() {
            return mode;
        }

        /**
         * Converts the Entry object to a properties configuration entry.
         *
         * @return Property entry representing the Entry variables. This is used for writing to the config.
         */
        public IProperty<String> toProperties() {
            AbstractProperty<String> property = (AbstractProperty<String>) PropertyStore.getContext(TargetBasics.TARGET_VIEWER_PROP_CONTEXT).getProperty("TargetReferenceProperty" + UUID.randomUUID(), "");
            property.addChild(new GenericProperty<>("sensorIdentifier", "", true, this.sensorIdentifier));
            property.addChild(new GenericProperty<>("latitude", "", true, this.coordinate != null ? String.valueOf(this.coordinate.getLatitude()) : ""));
            property.addChild(new GenericProperty<>("longitude", "", true, this.coordinate != null ? String.valueOf(this.coordinate.getLongitude()) : ""));
            property.addChild(new GenericProperty<>("mode", "", true, this.mode.name()));
            return property;
        }

        /**
         * Loads an Entry object from the properties configuration.
         *
         * @param properties Parent property which stores all Entry configurations.
         * @return Entry based on passed property configuration.
         */
        public static Entry fromProperties(IProperty<?> properties) {
            String sensorIdentifier = "";
            Mode mode = Mode.OWNSHIP;
            Coordinate coordinate = null;
            List<IProperty<?>> subProperties = properties.getSubProperties();
            Double lat = null, lon = null;
            for (IProperty<?> property : subProperties) {
                switch (property.getName()) {
                    case "sensorIdentifier" -> sensorIdentifier = String.valueOf(property.getValue());
                    case "latitude" -> {
                        if (property.getValue() != null && !String.valueOf(property.getValue()).isEmpty())
                            lat = Double.parseDouble(String.valueOf(property.getValue()));
                    }
                    case "longitude" -> {
                        if (property.getValue() != null && !String.valueOf(property.getValue()).isEmpty())
                            lon = Double.parseDouble(String.valueOf(property.getValue()));
                    }
                    case "mode" -> mode = Mode.valueOf(String.valueOf(property.getValue()));
                }
            }
            if (lat != null && lon != null) {
                coordinate = new CoordinateImpl();
                coordinate.setLatLon(lat, lon);
            }
            return new Entry(sensorIdentifier, coordinate, mode);
        }

        /**
         * Overrides the hashcode in order to compare two entry objects based on the contained values.
         *
         * @return a hash code value for this object.
         * @implSpec As far as is reasonably practical, the {@code hashCode} method defined
         * by class {@code Object} returns distinct integers for distinct objects.
         * @see Object#equals(Object)
         * @see System#identityHashCode
         */
        @Override
        public int hashCode() {
            return Objects.hash(sensorIdentifier, mode, coordinate);
        }

        /**
         * Overrides the equals in order to compare two entry objects based on the contained values.
         *
         * @see #hashCode()
         * @see HashMap
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry other)) return false;
            return Objects.equals(sensorIdentifier, other.sensorIdentifier)
                    && mode == other.mode
                    && Objects.equals(coordinate, other.coordinate);
        }
    }

    /**
     * Reference mode representation used for determining which reference position should be used for target
     * georeferencing. FIXED represents a fixed coordinate as the reference
     * target for targets which contain a bearing and distance. REFERENCE refers to the usage of reference targets
     * supplied with the data source for target referencing. OWNSHIP refers to the usage of the models internal ownship
     * object as the data source for target referencing.
     */
    public enum Mode {
        OWNSHIP,
        FIXED,
        REFERENCE
    }
}
