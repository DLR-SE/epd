package de.emir.rcp.ui.utils.properties;

import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * A property editor that creates a UI for password inputs for a property. If the properties value does not match the
 * UI value, `isDirty` will be set to true. Note that the properties value is not directly modified on user input. It
 * is only written to the property if `finish()` is called!
 */
public class PropertyPasswordWidget extends PropertyTextWidget {

    private static final long serialVersionUID = -8935435319289993420L;

    /**
     * @wbp.parser.constructor
     */
    public PropertyPasswordWidget(IProperty<String> property) {
        super(property);
    }

    public PropertyPasswordWidget(String propertyContext, String propertyName, String defaultValue) {
        super(propertyContext, propertyName, defaultValue);
    }

    protected void init() {
        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[]{1.0};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        textField = new JPasswordField();
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
}
