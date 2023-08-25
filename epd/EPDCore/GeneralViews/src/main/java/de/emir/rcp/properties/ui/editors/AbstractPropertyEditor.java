package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;

import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public abstract class AbstractPropertyEditor<VALUE_TYPE> implements IPropertyEditor
{

	protected IProperty mProperty = null;
	private HashSet<PropertyChangeListener>		mListeners = new HashSet<>();


	
	@Override
	public void setProperty(IProperty property) {
		mProperty = property;
	}

	@Override
	public IProperty getProperty() {
		return mProperty;
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		if (listener != null && mListeners.contains(listener) == false){
			mListeners.add(listener);
			getProperty().addPropertyChangeListener(listener);
		}
	}
	
	private Component mComponent = null;
	
	private PropertyChangeListener mChangeListener = new PropertyChangeListener() {		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			//forward changes immediately
			for (PropertyChangeListener pcl : mListeners) pcl.propertyChange(evt);
			if (supportValueUpdates())
				PropertyManager.getInstance().registerForUpdate(AbstractPropertyEditor.this);
		}
	};
	
	@Override
	public Component getEditor() {
		if (mComponent == null) {
			mComponent = createComponent();
			if (mComponent == null)
				return null;
			mComponent.addHierarchyListener(new HierarchyListener() {
				//detect if we are still visible and if not, call dispose to notify the editor about that
				@Override
				public void hierarchyChanged(HierarchyEvent e) {
					if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
		                if (!e.getComponent().isDisplayable()) {
	                		getProperty().removePropertyChangeListener(mChangeListener);
		                    dispose();
		                }else {
		                	getProperty().addPropertyChangeListener(mChangeListener);
		                }
		            }
				}
			});
		}
		return mComponent;
	}
	

	protected abstract Component createComponent();

	protected UCoreProperty getUCoreProperty() {
		if (mProperty instanceof UCoreProperty)
			return (UCoreProperty)mProperty;
		return null;
	}

	protected void setValue(VALUE_TYPE value){
		getProperty().setValue(value);
	}
	protected VALUE_TYPE getValue(){
		try{
			return (VALUE_TYPE) getProperty().getValue();
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * This method is intended to be overwritten by all subclasses. 
	 * The method will be called when the underlaying property value has changed within the model
	 * To avoid the calling of this method, specialized subclasses my return false, within the method <code>supportValueUpdates()</code>.
	 * 
	 * @note this method shall only be called from within the UI-Thread
	 */
	public void updateEditorValue() {
		//empty implementation to not break existing subclasses
	}
	
	/** 
	 * This method is called to check if the editor is able to handle value updates from the model. 
	 * return true, if an update is supported. In such a case the method <code>updateEditorValue()</code> is called after an update has been detected
	 * otherwise return false;
	 * 
	 * @note the default value is false, thus to support updates the subclass has to overwrite this method
	 * @return
	 */
	public boolean supportValueUpdates() {
		return false; //standard implementation avoids too much calls to the property manager
	}


	
}
