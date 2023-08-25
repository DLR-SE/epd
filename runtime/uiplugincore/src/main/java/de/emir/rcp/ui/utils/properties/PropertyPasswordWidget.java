package de.emir.rcp.ui.utils.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
public class PropertyPasswordWidget extends PropertyTextWidget implements IPropertyWidget {

    /**
     * 
     */
    private static final long serialVersionUID = -8935435319289993420L;

    private Logger log = ULog.getLogger(PropertyPasswordWidget.class);

    /**
     * @wbp.parser.constructor
     */
    public PropertyPasswordWidget(IProperty property) {
        super(property);
    }

    public PropertyPasswordWidget(String propertyContext, String propertyName, String defaultValue) {
        super(propertyContext, propertyName, defaultValue);
    }

    protected void init() {

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        setLayout(gridBagLayout);

        textField = new JPasswordField();
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

}
