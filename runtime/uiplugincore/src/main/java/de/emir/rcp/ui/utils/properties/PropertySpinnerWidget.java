package de.emir.rcp.ui.utils.properties;

import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A property editor that creates an editor for numbers. This uses Javas spinner widget. If the properties value does
 * not match the UI value, `isDirty` will be set to true. Note that the properties value is not directly modified on
 * user input. It is only written to the property if `finish()` is called!
 *
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class PropertySpinnerWidget<T extends Number> extends JSpinner implements IPropertyWidget<T> {
    private static final long serialVersionUID = -1784073412977777732L;

    protected final IProperty<T> property;
    protected boolean isDirty = false;

    // set to true, to disable listener calls
    protected boolean isListenerDisabled = false;

    /**
     * Creates a new property editor with a spinner widget.
     *
     * @param propertyContext property context id
     * @param propertyName    unique property name
     * @param defaultValue    default value if property does not exist
     */
    public PropertySpinnerWidget(String propertyContext, String propertyName, T defaultValue) {
        this(propertyContext, propertyName, defaultValue, new SpinnerNumberModel());
    }

    /**
     * Creates a new property editor with a spinner widget.
     *
     * @param propertyContext property context id
     * @param propertyName    unique property name
     * @param defaultValue    default value if property does not exist
     * @param model           spinner model
     */
    public PropertySpinnerWidget(String propertyContext, String propertyName, T defaultValue, SpinnerModel model) {
        this(
                PropertyStore
                        .getContext(propertyContext)
                        .getProperty(propertyName, defaultValue),
                model
        );
    }

    /**
     * Creates a new property editor with a spinner widget.
     *
     * @param property property to modify
     */
    public PropertySpinnerWidget(IProperty<T> property) {
        this(property, new SpinnerNumberModel());
    }


    /**
     * Creates a new property editor with a spinner widget.
     *
     * @param property property to modify
     * @param model    spinner model
     */
    public PropertySpinnerWidget(IProperty<T> property, SpinnerModel model) {
        super(model);
        this.property = property;

        property.addPropertyChangeListener(evt -> setValueFromProperty());

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (isListenerDisabled){
                    return;
                }

                Object oldValue = property.getValue();
                Object newValue = getValue();
                isDirty = !oldValue.equals(newValue);
                firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
            }
        });

        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (isListenerDisabled){
                    return;
                }

                Object oldValue = property.getValue();
                Object newValue = getValue();
                isDirty = !oldValue.equals(newValue);
                firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
            }
        });
        setValueFromProperty();
    }

    private void setValueFromProperty() {
        if (isListenerDisabled){
            return;
        }

        T value = property.getValue();
        this.setValue(value);
        isDirty = false;
    }

    public void reset() {
        setValueFromProperty();
    }

    @SuppressWarnings("unchecked")
    public void finish() {
        isListenerDisabled = true;
        property.setValue((T) this.getValue());
        isListenerDisabled = false;
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public IProperty<T> getProperty() {
        return property;
    }
}
