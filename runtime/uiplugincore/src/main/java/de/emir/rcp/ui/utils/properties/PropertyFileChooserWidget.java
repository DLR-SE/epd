package de.emir.rcp.ui.utils.properties;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * A class that creates a file chooser UI for String properties. It can be used if the String should represent a
 * file path. If the properties value does not match the UI value, `isDirty` will be set to true. Note that the
 * properties value is not directly modified on user input. It is only written to the property if `finish()` is
 * called!
 */
public class PropertyFileChooserWidget extends JPanel implements IPropertyWidget<String> {

    private static final long serialVersionUID = -8935435319289993420L;

    protected final JTextField textField;
    protected final IProperty<String> property;

    protected boolean isDirty = false;

    // set to true, to disable listener calls
    protected boolean isListenerDisabled = false;

    public PropertyFileChooserWidget(String label, String propertyContext, String propertyName, String defaultValue,
                                     FileFilter ff) {

        PropertyContext context = PropertyStore.getContext(propertyContext);

        property = context.getProperty(propertyName, defaultValue);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblLabel = new JLabel(label + ": ");
        GridBagConstraints gbc_lblLabel = new GridBagConstraints();
        gbc_lblLabel.insets = new Insets(5, 5, 0, 5);
        gbc_lblLabel.anchor = GridBagConstraints.EAST;
        gbc_lblLabel.gridx = 0;
        gbc_lblLabel.gridy = 0;
        add(lblLabel, gbc_lblLabel);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(5, 0, 0, 5);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        add(textField, gbc_textField);

        JButton btnBrowse = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
        gbc_btnBrowse.insets = new Insets(5, 0, 0, 5);
        gbc_btnBrowse.gridx = 2;
        gbc_btnBrowse.gridy = 0;
        add(btnBrowse, gbc_btnBrowse);

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

        btnBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();

                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (ff != null) {
                    chooser.setFileFilter(ff);
                }
                String text = textField.getText();

                if (text != null && !text.isEmpty()) {

                    File current = new File(text);

                    if (current.exists() && current.getParentFile().isDirectory()) {
                        chooser.setCurrentDirectory(current.getParentFile());
                    }
                }

                int result = chooser.showOpenDialog(PropertyFileChooserWidget.this);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = chooser.getSelectedFile();

                    textField.setText(selectedFile.getAbsolutePath());

                    isDirty = true;

                    firePropertyChange(
                            PROPERTY_VALUE_CHANGE_NAME,
                            text,
                            selectedFile.getAbsolutePath()
                    );
                }

            }
        });

    }

    private void setTextFieldValue() {
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
    public IProperty<String> getProperty() {
        return property;
    }
}
