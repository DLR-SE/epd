package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class EnumEditor extends AbstractPropertyEditor<Object> implements PropertyChangeListener {

	JComboBox<String>			mField = null;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mField == null){
			
			UCoreProperty prop = getUCoreProperty();
			Pointer ptr = prop.getPointer();
			final UEnum en = (UEnum) ptr.getPointedFeature().getType();
			
			mField = new JComboBox<>();
			for (UEnumerator lit : en.getLiterals())
				mField.addItem(lit.getName());
			
			Object value = getValue();
			if (value != null){
				String n = value.toString();
				mField.setSelectedItem(n);
			}
			mField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (getProperty().isEditable())
						setValue(en.createNewInstance(mField.getSelectedItem().toString()));
				}
			});
			mField.setEnabled(getProperty().isEditable());
			getProperty().addPropertyChangeListener(this);
		}
		return mField;
	}

	@Override
	public void dispose() {
		getProperty().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (mField.hasFocus())
			return ;//we do not want our input to be overwritten
		if (false == evt.getNewValue().equals(mField.getSelectedItem())) {
			if (SwingUtilities.isEventDispatchThread()) {
				mField.setSelectedItem(evt.getNewValue().toString());
			}else
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						mField.setSelectedItem(evt.getNewValue().toString());		
					}
				});
		}
	}

}
