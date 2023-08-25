package de.emir.rcp.properties.impl;

import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class DelegateReadOnlyProperty extends AbstractProperty{

		IProperty<?> mDelegate;
		
		public DelegateReadOnlyProperty(IProperty<?> delegate) {
			super();
			mDelegate = delegate;
		}
		
		@Override
		public String getName() {
			return mDelegate.getName();
		}

		@Override
		public String getDescription() {
			return mDelegate.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public Object getValue() {
			Object v = mDelegate.getValue();
			return v != null ? v.toString() : null;
		}

		@Override
		public void setValue(Object value) {
			throw new UnsupportedOperationException("Unsupported Operation: Property is readOnly");
		}

		@Override
		public boolean isEditable() {
			return false;
		}

		@Override
		public String getCategory() {
			return mDelegate.getCategory();
		}

		@Override
		public IProperty copy() {
			return new DelegateReadOnlyProperty(mDelegate);
		}

}
