package de.emir.rcp.ui.utils.properties;

import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;

/**
 *
 * This class can be used within a settings page to bind a boolean property value to a checkbox. If the value
 * of the checkbox does not match the properties value, `isDirty` will be set to true. Note that the properties
 * value is not directly modified on user input. It is only written to the property if `finish()` is called!
 *
 * @param <T>
 *
 */
public class PropertyComboboxWidget<T> extends JComboBox<T> implements IPropertyWidget<T> {

    private final IProperty<T> property;

    protected boolean isDirty = false;

    // set to true, to disable listener calls
    protected boolean isListenerDisabled = false;

    /**
     * Creates a new PropertyComboboxWidget and registers the underlying property if necessary.
     *
     * @param propertyContext Name of property context to use for retrieving and storing the property.
     * @param propertyName    Name of the property to link to the widget.
     * @param defaultValue    Default value for the property.
     */
    public PropertyComboboxWidget(String propertyContext, String propertyName, T defaultValue) {
        this(PropertyStore.getContext(propertyContext).getProperty(propertyName, defaultValue));
    }

    /**
     * Creates a new PropertyComboboxWidget and links it to an existing property.
     *
     * @param property Property to link to the widget.
     */
    public PropertyComboboxWidget(IProperty<T> property) {
        super();

        this.property = property;

        property.addPropertyChangeListener(evt -> setSelectedValue());

        setSelectedValue();

        // both listeners (action, item) are required to avoid inconsistencies
        // see: https://stackoverflow.com/a/9883189
        addActionListener(e -> {

            if (isListenerDisabled){
                return;
            }

            Object newValue = this.getSelectedItem();
            Object oldValue = this.property.getValue();
            isDirty = newValue != oldValue;
            firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
        });

        addItemListener(e -> {

            if (isListenerDisabled){
                return;
            }

            Object newValue = this.getSelectedItem();
            Object oldValue = this.property.getValue();
            isDirty = newValue != oldValue;
            firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
        });
    }

    /**
     * Sets the selected value for the combobox based on the current property value.
     */
    private void setSelectedValue() {

        if (isListenerDisabled){
            return;
        }

        Object value = property.getValue();
        this.setSelectedItem(value);
        isDirty = false;
    }

    /**
     * Resets the combobox value and retrieves the value from the current underlying property value.
     */
    public void reset() {
        setSelectedValue();
    }

    /**
     * Saves the property to the PropertyStore and disposes the widget.
     */
    public void finish() {
        try {
            isListenerDisabled = true;
            property.setValue((T) this.getSelectedItem());
        } catch (ClassCastException e) {
            ULog.error("Could not cast selected item of {} to generic type.", this.getSelectedItem().getClass(), e);
        } finally {
            isListenerDisabled = false;
        }
    }

    /**
     * Checks if the widget is dirty, i.e. the widget value was changed by the user and does not currently
     * match the one of the underlying property. If it is dirty, saving requires call to the finish() method.
     *
     * @return True if value was changed.
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * Gets the linked property of the widget.
     *
     * @return Property linked to the widget.
     */
    @Override
    public IProperty<T> getProperty() {
        return property;
    }
}
