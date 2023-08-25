package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

public interface UVisitor {

    /**
     * Checks, weather a feature should be visited or not
     * 
     * @param parent
     * @param feature
     * @return true if the object shall be be visited, false otherwise
     * @note if this method returns false, children of the pointed feature will not visited
     */
    public boolean shouldVisit(UObject parent, UStructuralFeature feature);

    /**
     * Indicates that a new UObject is started, and the following visit calls, represent children below this object
     * 
     * @param object
     * @param cl
     * @return true, if the object shall be further visited
     */
    public boolean beginObject(UObject object, UClass cl);

    public void endObject(UObject object, UClass cl);

    /**
     * Indicates that the visitor starts, visiting a multi feature (feature.isMany() == true)
     * 
     * @param parent
     * @param feature
     */
    public void beginList(UObject parent, UStructuralFeature feature);

    public void endList(UObject parent, UStructuralFeature feature);

    /**
     * visit an element
     * 
     * @param parent
     *            the parent object
     * @param feature
     *            feature of the parent object, that points to the value
     * @param list_index
     *            list index of the value, if the value is part of an list or < 0
     * @param value
     *            the current value
     */
    public void visit(UObject parent, UStructuralFeature feature, int list_index, Object value);

}
