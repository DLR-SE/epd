package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


public class StringEditor extends AbstractPropertyEditor<String> implements PropertyChangeListener {

	protected JTextField			mField = null;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mField == null){
			mField = new JTextField(getValue());
			if(mField.getText().length() > 30)
				mField.setColumns(30);
			
			
			mField.getDocument().addDocumentListener(new DocumentListener() {
				private Timer					mTimer = null;
				@Override
				public void removeUpdate(DocumentEvent e) { update(e); }				
				@Override
				public void insertUpdate(DocumentEvent e) { update(e); }
				@Override
				public void changedUpdate(DocumentEvent e) { update(e); }
				
				public void update(DocumentEvent e) {
					if (mTimer != null) {
						mTimer.cancel();
						mTimer.purge();
						mTimer = null;
					}
					mTimer = new Timer();
					
					mTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							try {
								updateTextInput(e.getDocument().getText(0, e.getDocument().getLength()));
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
							mTimer = null;
						}
					}, 500);
				}
			});
			mField.setEnabled(getProperty().isEditable());
			
			getProperty().addPropertyChangeListener(this);
		}
		return mField;
	}
	
	protected void updateTextInput(String text) {
		setValue(text);
		if (text.length() > 30)
			mField.setColumns(30);
	}

	@Override
	public void dispose() {
		getProperty().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (mField.hasFocus())
			return ; //if we have the focus, we do not want the values to be overwritten
		if (arg0.getNewValue().equals(mField.getText()) == false){
			if (SwingUtilities.isEventDispatchThread()) {
				mField.setText(arg0.getNewValue().toString());
			}else {
				SwingUtilities.invokeLater(new Runnable() {				
					@Override
					public void run() {
						mField.setText(arg0.getNewValue().toString());
					}
				});
			}
		}
	}

}
