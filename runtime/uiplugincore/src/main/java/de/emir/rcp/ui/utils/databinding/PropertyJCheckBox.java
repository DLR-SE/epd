package de.emir.rcp.ui.utils.databinding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class PropertyJCheckBox extends JCheckBox {

    /**
     * 
     */
    private static final long serialVersionUID = -60510016204044804L;
    private IProperty property;
    private PropertyChangeListener propertyChangeListener;

    private Logger log = ULog.getLogger(PropertyJCheckBox.class);

    public PropertyJCheckBox(String label, String propertyContext, String propertyName, boolean defaultValue) {
        super(label);

        PropertyContext context = PropertyStore.getContext(propertyContext);

        property = context.getProperty(propertyName, defaultValue);

        init();

    }

    private void init() {

        propertyChangeListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                setValueFromProperty();

            }
        };

        property.addPropertyChangeListener(propertyChangeListener);

        setValueFromProperty();

        addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                setPropertyFromValue();

            }
        });

    }

    private void setValueFromProperty() {

        Object value = property.getValue();

        if (value instanceof Boolean == false) {
            log.error("Property is not of type of boolean");
            return;
        }

        setSelected((boolean) value);
    }

    private void setPropertyFromValue() {

        property.setValue(isSelected());

    }

}
