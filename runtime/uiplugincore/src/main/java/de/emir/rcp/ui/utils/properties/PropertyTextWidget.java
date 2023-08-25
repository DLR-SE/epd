package de.emir.rcp.ui.utils.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * @author Florian
 *
 */
public class PropertyTextWidget extends JPanel implements IPropertyWidget {

    /**
     * 
     */
    private static final long serialVersionUID = -8935435319289993420L;
    protected JTextField textField;
    protected IProperty property;

    private Logger log = ULog.getLogger(PropertyTextWidget.class);
    protected PropertyChangeListener changeListener;

    boolean dirty = false;

    /**
     * @wbp.parser.constructor
     */
    public PropertyTextWidget(IProperty property) {

        this.property = property;
        init();
    }

    public PropertyTextWidget(String propertyContext, String propertyName, String defaultValue) {

        PropertyContext context = PropertyStore.getContext(propertyContext);

        property = context.getProperty(propertyName, defaultValue);

        init();
    }

    protected void init() {

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        // gbc_textField.insets = new Insets(5, 0, 0, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        add(textField, gbc_textField);
        textField.setColumns(10);

        changeListener = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                setTextfieldValue();

            }
        };

        property.addPropertyChangeListener(changeListener);

        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                dirty = true;
            }
        });
        setTextfieldValue();

    }

    protected void setTextfieldValue() {

        Object value = property.getValue();

        if (value == null) {
            textField.setText("");
            dirty = false;
            return;
        }

        if (value instanceof String == false) {
            log.error("Property is not of type of String");
            return;
        }

        textField.setText((String) value);
        dirty = false;

    }

    public void reset() {
        setTextfieldValue();
    }

    public void finish() {

        property.setValue(textField.getText());
        property.removePropertyChangeListener(changeListener);

    }

    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        textField.setEnabled(isEnabled);
    }

    @Override
    public boolean isEnabled() {
        return textField.isEnabled();
    }
    
    public String getValue() {
    	return textField.getText();
    }

}
