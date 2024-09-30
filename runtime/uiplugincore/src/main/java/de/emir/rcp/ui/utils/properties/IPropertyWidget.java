package de.emir.rcp.ui.utils.properties;

import de.emir.tuml.ucore.runtime.prop.IProperty;

public interface IPropertyWidget {
    
    public IProperty getProperty();

    public boolean isDirty();

    public void finish();

    public void reset();

}
