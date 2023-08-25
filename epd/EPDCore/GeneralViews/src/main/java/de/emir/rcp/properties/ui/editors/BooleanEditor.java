package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

public class BooleanEditor extends AbstractPropertyEditor<Boolean> {

	JCheckBox			mField = null;
	private boolean		mInternalUpdate = false;
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mField == null){
			mField = new JCheckBox("");
			mField.setSelected(getValue());
			mField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (getProperty().isEditable())
						setValue(mField.isSelected());
				}
			});			
			mField.setEnabled(getProperty().isEditable());
		}
		return mField;
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean supportValueUpdates() {
		return true;
	}
	
	@Override
	public void updateEditorValue() {
		if (mField.hasFocus())
			return ; //we don't want our input to be overwritten
		boolean nv = (boolean)getValue();
		if (nv != mField.isSelected()) {
			if (SwingUtilities.isEventDispatchThread())
				mField.setSelected(nv);
			else 
				SwingUtilities.invokeLater(new Runnable() { public void run() { mField.setSelected(nv);}});
		}
	}
	

}
