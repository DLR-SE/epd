package de.emir.rcp.ui.utils.properties;

import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;

/**
 *
 * This class can be used within a settings page to bind a boolean property value to a checkbox. If the value
 * of the checkbox does not match the properties value, `isDirty` will be set to true. Note that the properties
 * value is not directly modified on user input. It is only written to the property if `finish()` is called!
 *
 */
public class PropertyCheckboxWidget extends JCheckBox implements IPropertyWidget<Boolean> {

    private static final long serialVersionUID = -60510016204044804L;

    protected final IProperty<Boolean> property;

    protected boolean isDirty = false;

    // set to true, to disable listener calls
    protected boolean isListenerDisabled = false;

    public PropertyCheckboxWidget(String label, String propertyContext, String propertyName, boolean defaultValue) {
        this(
                label,
                PropertyStore
                        .getContext(propertyContext)
                        .getProperty(propertyName, defaultValue)
        );
    }

    public PropertyCheckboxWidget(String label, IProperty<Boolean> property) {
        super(label);

        this.property = property;

        property.addPropertyChangeListener(evt -> setSelectedValue());

        setSelectedValue();

        // both listeners (action, item) are required to avoid inconsistencies
        // see: https://stackoverflow.com/a/9883189
        addActionListener(e -> {

            if (isListenerDisabled){
                return;
            }

            boolean newValue = isSelected();
            boolean oldValue = this.property.getValue();
            isDirty = newValue != oldValue;
            firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
        });

        addItemListener(e -> {

            if (isListenerDisabled){
                return;
            }

            boolean newValue = isSelected();
            boolean oldValue = this.property.getValue();
            isDirty = newValue != oldValue;
            firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
        });
    }

    private void setSelectedValue() {

        if (isListenerDisabled){
            return;
        }

        boolean value = property.getValue();
        setSelected(value);
        isDirty = false;
    }

    public void reset() {
        setSelectedValue();
    }

    public void finish() {
        isListenerDisabled = true;
        property.setValue(isSelected());
        isListenerDisabled = false;
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public IProperty<Boolean> getProperty() {
        return property;
    }
}
