package de.emir.rcp.properties.impl;

import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ParameterProperty extends AbstractProperty {

	private UParameter 			mParameter;
	private Object 				mValue;

	public ParameterProperty(UParameter param) {
		this(param, null);
	}
	
	public ParameterProperty(UParameter param, Object value) {
		super();
		mParameter = param;
		mValue = value;
	}

	@Override
	public String getName() {
		return mParameter.getName();
	}

	@Override
	public String getDescription() {
		return mParameter.getDocumentation();
	}

	@Override
	public Class getType() {
		return mParameter.getType().getRepresentingClass();
	}

	@Override
	public Object getValue() {
		return mValue;
	}

	@Override
	public void setValue(Object value) {
		if (value != mValue){
			Object oldValue = mValue;
			mValue = value;
			firePropertyChange(oldValue, mValue);
		}
	}

	@Override
	public boolean isEditable() {
		return mParameter.getDirection() == UDirectionType.IN || mParameter.getDirection() == UDirectionType.INOUT;
	}

	@Override
	public String getCategory() {
		return "Paramter";
	}

	@Override
	public IProperty copy() {
		return new ParameterProperty(mParameter, mValue);
	}

}
