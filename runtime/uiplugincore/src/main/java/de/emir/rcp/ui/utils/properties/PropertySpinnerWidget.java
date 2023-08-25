/**
 * 
 */
package de.emir.rcp.ui.utils.properties;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class PropertySpinnerWidget extends JSpinner implements IPropertyWidget {
    private static final long serialVersionUID = -1784073412977777732L;
    private IProperty property;
    private Logger log = ULog.getLogger(PropertySpinnerWidget.class);
    private PropertyChangeListener changeListener;
    private boolean dirty = false;

    /**
     * 
     * @param property
     */
    public PropertySpinnerWidget(IProperty<?> property) {
        this.property = property;
        init();
    }

    /**
     * 
     * @param propertyContext
     * @param propertyName
     * @param defaultValue
     */
    public PropertySpinnerWidget(String propertyContext, String propertyName, Integer defaultValue) {
        PropertyContext context = PropertyStore.getContext(propertyContext);
        property = context.getProperty(propertyName, defaultValue);
        init();
    }

    /**
     * 
     * @param property
     * @param model
     */
    public PropertySpinnerWidget(IProperty property, SpinnerModel model) {
        super(model);
        this.property = property;
        init();
    }

    /**
     * 
     * @param propertyContext
     * @param propertyName
     * @param defaultValue
     * @param model
     */
    public PropertySpinnerWidget(String propertyContext, String propertyName, Integer defaultValue,
            SpinnerModel model) {
        super(model);
        PropertyContext context = PropertyStore.getContext(propertyContext);
        property = context.getProperty(propertyName, defaultValue);
        init();
    }

    private void init() {
        changeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setValueFromProperty();
            }
        };
        property.addPropertyChangeListener(changeListener);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                dirty = true;
            }
        });
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dirty = true;
            }
        });
        setValueFromProperty();
    }

    private void setValueFromProperty() {
        Object value = property.getValue();
        if (value instanceof Integer == false) {
            log.error("Property is not of type integer");
            return;
        }
        this.setValue(value);
        dirty = false;
    }

    public void reset() {
        setValueFromProperty();
    }

    @SuppressWarnings("unchecked")
    public void finish() {
        property.setValue(this.getValue());
        property.removePropertyChangeListener(changeListener);
    }

    public boolean isDirty() {
        return dirty;
    }
}
