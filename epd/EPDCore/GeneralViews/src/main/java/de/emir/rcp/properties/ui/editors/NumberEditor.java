package de.emir.rcp.properties.ui.editors;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public abstract class NumberEditor<TYPE extends Number> extends AbstractPropertyEditor<TYPE>  {

	JTextField			mField = null;
	private Color 		mBackgroundColor;
	private boolean 	mInternalUpdate;
	
	/** Used to format the String, if not null */
	protected String 	mFormatString;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mField == null){
			
			
			//try to get the values for Mode, extensions and initial from feature annotations (need to be done before creation of the field)
			if (getProperty() instanceof UCoreProperty) {
				Pointer ptr = ((UCoreProperty)getProperty()).getPointer();
				if (ptr != null) {
					UStructuralFeature feature = ptr.getPointedFeature();
					if (feature != null) {
						UAnnotation formatAnno = feature.getAnnotation("NumberFormat");
						if (formatAnno != null && formatAnno.getDetail("format") != null) {
							mFormatString = formatAnno.getDetail("format").getValue();
						}
					}
				}
			}
			
			
			
			mField = new JTextField(getFormatedValue(), 5);
			
			mField.getDocument().addDocumentListener(new DocumentListener() {
				private Timer					mTimer = null;
				@Override
				public void removeUpdate(DocumentEvent e) { update(e); }				
				@Override
				public void insertUpdate(DocumentEvent e) { update(e); }
				@Override
				public void changedUpdate(DocumentEvent e) { update(e); }
				
				public void update(DocumentEvent e) {
					if (mInternalUpdate)
						return ;
					if (mTimer != null) {
						mTimer.cancel();
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
							if (mTimer != null)
								mTimer.cancel();
							mTimer = null;
						}
					}, 500);
				}
			});
			mBackgroundColor = mField.getBackground(); //remember here, so we do not have to take care about the LookAndFeel
			mField.setEnabled(getProperty().isEditable());
		}
		return mField;
	}

	protected void updateTextInput(String text) {
		try {
			TYPE ev = getEditorValue(getAsString());
			setValue(ev);
			mField.setBackground(mBackgroundColor);
		}catch (Exception e) {
			ULog.debug("Failed to parse String: " + getAsString() + " Error: " + e.getMessage());
			e.printStackTrace();
			mField.setBackground(Color.RED);
		}
	}
	
	protected String getAsString() {
		return mField.getText().replaceAll(",", ".");
	}

	protected abstract TYPE getEditorValue(String text);

	@Override
	public void dispose() {
	}
	
	
	@Override
	public boolean supportValueUpdates() {
		return true;
	}
	
	
	@Override
	public void updateEditorValue() {
		if (mField.hasFocus()) return ; //we do not want to overwrite the field
		mInternalUpdate = true;
		mField.setText(getFormatedValue());
		mInternalUpdate = false;
	}

	protected String getFormatedValue() {
		if (mFormatString != null) {
			try {
				return String.format(mFormatString, getValue());
			}catch(Exception e) {
				ULog.trace("Failed to format formatter: " + mFormatString + " value: " + getValue() + " Using fallback without formatter");
			}
		}
		return getValue() + "";
	}

}
