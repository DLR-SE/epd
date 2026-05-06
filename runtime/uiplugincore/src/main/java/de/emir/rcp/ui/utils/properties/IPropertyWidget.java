package de.emir.rcp.ui.utils.properties;

import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * Base interface for UI editors for IProperties. Most widgets are designed to not directly
 * apply UI settings to a property. Instead, changes are cached internally and only set in
 * the property when finish() is called. To check if settings are different between UI and
 * property, check it with isDirty().
 * @param <T> property value type
 */
public interface IPropertyWidget<T> {

    /**
     * Property change event name when the user changes a value in a property widget.
     * The Property widget only applies settings if `finish()` is called. Thus, the
     * internal property is not always synchronous with the UI. This name is used in
     * property change events as a name, so that the value could be manually set.
     */
    public static final String PROPERTY_VALUE_CHANGE_NAME = "IPropertyWidgetValue";

    IProperty<T> getProperty();

    /**
     * Check if UI and property value are different.
     * @return if UI and property value are different
     */
    boolean isDirty();

    /**
     * Apply UI value to the property.
     */
    void finish();

    /**
     * Read the property value and update the UI.
     */
    void reset();

}
