package de.emir.rcp.ui.utils.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;

import org.slf4j.Logger;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * 
 * This class can be used within a settings page to bind a boolean property value to a checkbox
 * 
 * @author Florian
 *
 */
public class PropertyCheckboxWidget extends JCheckBox implements IPropertyWidget {

    /**
     * 
     */
    private static final long serialVersionUID = -60510016204044804L;
    private IProperty<Boolean> property;
    private PropertyChangeListener propertyChangeListener;

    private Logger log = ULog.getLogger(PropertyCheckboxWidget.class);

    private boolean dirty = false;

    public PropertyCheckboxWidget(String label, String propertyContext, String propertyName, boolean defaultValue) {
        super(label);

        PropertyContext context = PropertyStore.getContext(propertyContext);

        property = context.getProperty(propertyName, defaultValue);

        init();

    }

    private void init() {

        propertyChangeListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                setSelectedValue();

            }
        };

        property.addPropertyChangeListener(propertyChangeListener);

        setSelectedValue();

        addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                dirty = true;

            }
        });

    }

    private void setSelectedValue() {
        Object value = property.getValue();
        if (value instanceof Boolean == false) {
            log.error("Property is not of type of boolean");
            return;
        }

        setSelected((boolean) value);
        dirty = false;

    }

    public void reset() {
        setSelectedValue();
    }

    public void finish() {

        property.setValue(isSelected());
        property.removePropertyChangeListener(propertyChangeListener);

    }

    public boolean isDirty() {
        return dirty;
    }

}
