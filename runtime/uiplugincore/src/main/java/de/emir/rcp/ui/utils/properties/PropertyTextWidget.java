package de.emir.rcp.ui.utils.properties;

import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Property editor for string properties that represent (longer) texts. If the properties value does not match the
 * UI value, `isDirty` will be set to true. Note that the properties value is not directly modified on user input.
 * It is only written to the property if `finish()` is called!
 */
public class PropertyTextWidget extends JPanel implements IPropertyWidget<String> {

    private static final long serialVersionUID = -8935435319289993420L;

    protected JTextField textField;
    protected IProperty<String> property;

    protected boolean isDirty = false;

    // set to true, to disable listener calls
    protected boolean isListenerDisabled = false;

    public PropertyTextWidget(String propertyContext, String propertyName, String defaultValue) {
        this(
                PropertyStore
                        .getContext(propertyContext)
                        .getProperty(propertyName, defaultValue)
        );
    }

    /**
     * @wbp.parser.constructor
     */
    public PropertyTextWidget(IProperty<String> property) {
        this.property = property;
        init();
    }

    protected void init() {
        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[]{1.0};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        // gbc_textField.insets = new Insets(5, 0, 0, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        add(textField, gbc_textField);
        textField.setColumns(10);

        property.addPropertyChangeListener(evt -> setTextFieldValue());

        textField.addActionListener(e -> {
            Object oldValue = property.getValue();
            Object newValue = textField.getText();
            isDirty = !oldValue.equals(newValue);
            firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkChange();
            }

            private void checkChange() {

                if (isListenerDisabled){
                    return;
                }

                Object oldValue = property.getValue();
                Object newValue = textField.getText();
                isDirty = !oldValue.equals(newValue);
                firePropertyChange(PROPERTY_VALUE_CHANGE_NAME, oldValue, newValue);
            }
        });

        setTextFieldValue();
    }

    protected void setTextFieldValue() {
        if (isListenerDisabled){
            return;
        }

        String value = property.getValue();

        if (value == null) {
            textField.setText("");
            isDirty = false;
            return;
        }

        textField.setText(value);
        isDirty = false;
    }

    public void reset() {
        setTextFieldValue();
    }

    public void finish() {
        isListenerDisabled = true;
        property.setValue(textField.getText());
        isListenerDisabled = false;
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isEnabled() {
        return textField.isEnabled();
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        textField.setEnabled(isEnabled);
    }

    public String getValue() {
        return textField.getText();
    }

    @Override
    public IProperty<String> getProperty() {
        return property;
    }
}
