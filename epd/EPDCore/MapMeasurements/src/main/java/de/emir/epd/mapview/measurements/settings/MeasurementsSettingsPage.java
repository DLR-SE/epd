package de.emir.epd.mapview.measurements.settings;

import de.emir.epd.mapview.measurements.MMBasics;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import java.awt.*;

public class MeasurementsSettingsPage extends AbstractSettingsPage {

    protected PropertyContext measurementContext;
    protected JComboBox<DistanceUnit> unitChoiceBox;
    protected DistanceUnit currentUnit;

    public MeasurementsSettingsPage() {
        measurementContext = PropertyStore.getContext(MMBasics.MEASUREMENT_PROPERTY_CONTEXT);
        IProperty<String> prop = measurementContext.getProperty(MMBasics.MEASUREMENT_DISTANCE_PROPERTY, DistanceUnit.NAUTICAL_MILES.name());
        if (prop.getValue() == null){
            prop.setValue(DistanceUnit.NAUTICAL_MILES.name());
        }
        currentUnit = DistanceUnit.valueOf(prop.getValue());
    }

    @Override
    public Component fillPage() {
        final JPanel panel = new JPanel();

        // Root layout is box which puts each item on a new line
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        unitChoiceBox = new JComboBox<>();
        for(DistanceUnit unit : DistanceUnit.values()){
            unitChoiceBox.addItem(unit);
        }
        unitChoiceBox.setSelectedItem(currentUnit);

        JLabel label = new JLabel("Distance Unit");

        panel.add(label);
        panel.add(unitChoiceBox);

        return panel;
    }

    @Override
    public boolean isDirty() {
        DistanceUnit newUnit = (DistanceUnit) unitChoiceBox.getSelectedItem();
        return currentUnit.equals(newUnit) == false;
    }

    @Override
    public void finish() {
        DistanceUnit newUnit = (DistanceUnit) unitChoiceBox.getSelectedItem();
        measurementContext.setValue(MMBasics.MEASUREMENT_DISTANCE_PROPERTY, newUnit.name());
    }
}
