package de.emir.rcp.properties;

import java.awt.Component;
import java.beans.PropertyChangeListener;

import de.emir.rcp.manager.PropertyManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class SettingsPropertyEditor implements IPropertyEditor {

	
	private IProperty			mReferenceProperty;
	
	private IProperty			mEditorProperty;
	private IPropertyEditor		mDelegateEditor;
	
	public SettingsPropertyEditor(IProperty referenceProperty, IPropertyEditor editor) {
		mDelegateEditor = editor;
		setProperty(referenceProperty);
	}
	
	public SettingsPropertyEditor(IProperty property) {
		this(property, null);
	}

	@Override
	public void setProperty(IProperty property) {
		mReferenceProperty = property;
		mEditorProperty = property.copy();
		if (mDelegateEditor == null)
			mDelegateEditor = PropertyManager.getInstance().getFirstEditor(mEditorProperty);
		else {
			mDelegateEditor.setProperty(mEditorProperty);
		}
	}

	@Override
	public IProperty getProperty() {
		return mDelegateEditor.getProperty();
	}

	@Override
	public Component getEditor() {
		return mDelegateEditor.getEditor();
	}

	@Override
	public void dispose() {
		mDelegateEditor.dispose();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		mDelegateEditor.addPropertyChangeListener(listener);
	}

	
	public boolean isDirty() {
		if (mReferenceProperty.getValue() == null) {
			return mEditorProperty.getValue() != null;
		}
		Object v1 = mReferenceProperty.getValue();
		Object v2 = mEditorProperty.getValue();
		if (v1.equals(v2))
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void finish() {
		if (isDirty()) {
			Object value = mEditorProperty.getValue();
			mReferenceProperty.setValue(value);
		}
	}
}
