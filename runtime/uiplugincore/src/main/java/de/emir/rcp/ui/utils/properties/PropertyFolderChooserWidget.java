package de.emir.rcp.ui.utils.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class PropertyFolderChooserWidget extends JPanel implements IPropertyWidget {

    /**
     * 
     */
    private static final long serialVersionUID = -8935435319289993420L;
    private JTextField textField;
    private IProperty property;

    private Logger log = ULog.getLogger(PropertyFolderChooserWidget.class);
    private PropertyChangeListener changeListener;

    private boolean dirty = false;

    public PropertyFolderChooserWidget(String label, String propertyContext, String propertyName, String defaultValue) {

        PropertyContext context = PropertyStore.getContext(propertyContext);

        property = context.getProperty(propertyName, defaultValue);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
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

        btnBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();

                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                String text = textField.getText();

                if (text != null && text.isEmpty() == false) {

                    File current = new File(text);

                    if (current.exists() && current.isDirectory()) {
                        chooser.setCurrentDirectory(current);
                    }
                }

                int result = chooser.showOpenDialog(PropertyFolderChooserWidget.this);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = chooser.getSelectedFile();

                    textField.setText(selectedFile.getAbsolutePath());
                    dirty = true;
                }

            }
        });

    }

    private void setTextfieldValue() {

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
    public IProperty getProperty() {
        return property;
    }
}
