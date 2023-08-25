package de.emir.rcp.properties.impl;

import java.beans.PropertyChangeListener;
import java.util.HashMap;

import de.emir.rcp.cmd.SetValueCommand;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.ui.editors.cmd.PropertyEditorChangeTransaction;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class UCoreProperty extends AbstractProperty 
{
	private Pointer mPointer;
	private HashMap<PropertyChangeListener, IDisposable> mListenerMap = new HashMap<>();
	private IDisposable mPointerDisposable;

	public UCoreProperty(UObject instance, UStructuralFeature feature, int listIndex) {
		this(PointerOperations.create(instance, feature, listIndex));
	}
	
	public UCoreProperty(Pointer ptr) {
		mPointer = ptr;
	}

	@Override
	public String getName() {
		return mPointer.getPointedFeature().getName();
	}

	@Override
	public String getDescription() {
		return mPointer.getPointedFeature().getDocumentation();
	}
	

	@Override
	public Class<?> getType() {
		UType type = mPointer.getType();
		if (type == null) 
			type = mPointer.getExpectedType();
		return type.getRepresentingClass();
	}

	@Override
	public Object getValue() {
		return mPointer.getValue();
	}

	@Override
	public void setValue(Object value) {
		AbstractEditor editor = PlatformUtil.getEditorManager().getActiveEditor();
		if (editor != null) {
			//if we could identify an editor, we will do the change inside the editors context
			PropertyEditorChangeTransaction pecc = new PropertyEditorChangeTransaction(getPointer(), value, editor);
			editor.getTransactionStack().run(pecc);
		}else {		
			//otherwise, just create a simple (non - editor) command
			CommandManager cm = ServiceManager.get(CommandManager.class);
			cm.executeCommand(new SetValueCommand(getPointer(), value));
		}
	}

	@Override
	public boolean isEditable() {
		return mPointer.getPointedFeature().isReadOnly() == false;
	}

	@Override
	public String getCategory() {
		return mPointer.getPointedFeature().getOwner().getName();
	}

	public Pointer getPointer() {
		return mPointer;
	}
	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		super.addPropertyChangeListener(listener);	
		if (getPointer() != null && getPointer().isValid()){
			IValueChangeListener vcl = new IValueChangeListener() {
				@Override
				public void onValueChange(Notification notification) {
					firePropertyChange(notification.getOldValue(), notification.getNewValue());
				}
			};
			mPointerDisposable = PointerOperations.observePointer(getPointer(), vcl);
			mListenerMap.put(listener, mPointerDisposable);
		}
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		if(getPointer() != null && getPointer().isValid() && mListenerMap.containsKey(listener)){
			IDisposable disp = mListenerMap.remove(listener);
			if (disp != null)
				disp.dispose();
		}
		super.removePropertyChangeListener(listener);
	}

	@Override
	public IProperty copy() {
		return new UCoreProperty(getPointer());
	}

}
