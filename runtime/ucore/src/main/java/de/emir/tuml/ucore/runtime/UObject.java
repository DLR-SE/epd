package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;

import java.beans.PropertyChangeListener;
import java.util.Collection;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.ITreeObserverOptions;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.TreeObserverOptions;

/**
 * @generated
 */
@UMLClass(isAbstract = true)
public interface UObject {

    /**
     * the type of this object
     * 
     * @generated
     */
    @UMLProperty(name = "uClassifier", associationType = AssociationType.SHARED)
    public UClass getUClassifier();

    /**
     * if this instance is part of an composite association (composition) the container points to the owner of this
     * instance
     * 
     * @generated
     */
    @UMLProperty(name = "uContainer", associationType = AssociationType.SHARED)
    public UObject getUContainer();

    /**
     * @generated
     */
    @UMLProperty(name = "uContainingFeature", associationType = AssociationType.SHARED)
    public UStructuralFeature getUContainingFeature();

    /**
     * 
     * register a listener that is notified, whenever the value of an structural feature changes
     * 
     * @note an instance of listener can only be registered once, if the listener instance is already registered, this
     *       method has no effect
     * @note this listener will be notified for each change, if you would like to have a selective notification, use
     *       registerListener(feature, listener)
     * @generated not
     */
    IDisposable registerListener(final IValueChangeListener listener);

    /**
     * register a listener that is notified, whenever the value of an structural feature within the whole object tree
     * changes
     * 
     * @note an instance of listener can only be registered once, if the listener instance is already registered, this
     *       method has no effect
     * @note this listener will be notified for each change, if you would like to have a selective notification, use
     *       registerListener(feature, listener)
     */
    IDisposable registerTreeListener(final ITreeValueChangeListener listener);

    IDisposable registerTreeListener(final ITreeValueChangeListener listener, ITreeObserverOptions options);

    /**
     * 
     * registers a listener that is notified, only if the value of the given feature changes
     * 
     * @note an instance of IValueChangeListener can only be registered once per UStructuralFeature. If this listener
     *       instance is already registered, this method has no effect.
     * @generated not
     */
    IDisposable registerListener(final UStructuralFeature feature, final IValueChangeListener listener);

    /**
     * 
     * removes the listener
     * 
     * @note if the same instance of the listener has also been registered for an specific structural feature this
     *       registration is not touched.
     * @generated
     */
    void removeClassifierListener(final IValueChangeListener listener);

    /**
     * removes the listener
     * 
     * @note if the same instance of the listener has also been registered for an specific structural feature this
     *       registration is not touched.
     * 
     */
    void removeTreeListener(final ITreeValueChangeListener listener);

    /**
     * 
     * completly remove the listener from this UObject instance
     * 
     * @generated
     */
    void removeListener(final IValueChangeListener listener);

    /**
     * 
     * removes the listener from notification for the given feature.
     * 
     * @note if the same instance of the listener has also been registered for any other feature, or for the whole class
     *       this registrations are not touched
     * @generated
     */
    void removeListener(final UStructuralFeature feature, final IValueChangeListener listener);

    /**
     * 
     * removes all listener for the given feature
     * 
     * @note if a listener for the whole class has been registered, this registration is not touched.
     * @generated
     */
    void removeAllListener(final UStructuralFeature feature);

    /**
     * 
     * removes all listener that has not been registered for a specific structural feature.
     * 
     * @generated
     */
    void removeAllClassifierListener();

    /**
     * @generated
     */
    boolean needNotification(final UStructuralFeature feature);

    /**
     * @generated
     */
    Object unset(final UStructuralFeature feature);

    /**
     * @generated
     */
    Object uGet(final UStructuralFeature feature);

    /**
     * @generated
     */
    void uSet(final UStructuralFeature feature, final Object value);

    /**
     * 
     * Invokes an operation associated with this object (and defined in its UClassifier) in case the Operation has more
     * than one parameter, the value shall contain the paramter values as array, in the same order as the parameters. In
     * case the Operation has no parameter the value may be null or any value (its ignored).
     * 
     * @param operation
     *            the operation to be called on this instance
     * @param the
     *            parameter value(s) or null
     * @return the return value of the function or null if the operation is "void" operation
     * @generated
     */
    Object invoke(final UOperation operation, final Object value);

    /**
     * @generated
     */
    void uAdd(final UStructuralFeature feature, final Object value);

    /**
     * @generated
     */
    void uAdd(final UStructuralFeature feature, final int index, final Object value);

    /**
     * @generated
     */
    Object uGet(final UStructuralFeature feature, final int index);

    /**
     * returns an iterator that iterates over all (non null) complex structural features.
     * 
     * @param type
     *            minimum filter, only features, with a more rigid association type will be added into the iterator,
     *            following the order: - COMPOSITION : only composite features - ASSOCIATION: association and
     *            composition - AGGREGATION: aggregation, association and compositon - PROPERTY: all complex features
     * @return
     */
    public Iterable<UObject> getContentIterator(UAssociationType type);

    /**
     * returns an content iterator for association type compositation
     * 
     * @return
     */
    public Iterable<UObject> getContentIterator();

    <T extends IDelegateInterface> T getDelegate();

    /**
     * Convinence method for addProperty(qualifiedPropertyName, description, true);
     * 
     * @param qualifiedPropertyName
     * @param description
     * @return
     */
    IProperty addProperty(final String qualifiedPropertyName, final String description);

    /**
     * Adds a new property to this instance. the Property is defined through its qualified name, however the name of the
     * created property will be the last segment of the qualified name. If the qualified name contains more than one
     * segment for example: foo.bar; the method creates an empty property foo (if it does not yet exists) as well as an
     * empty property bar. if a new property foo is created this property will have the same description as the property
     * "bar".
     * 
     * @note if you need description in group properties (e.g. "foo" property) use the method seperatly
     * 
     * @param qualifiedPropertyName
     *            qualified name, seperated by "."
     * @param description
     * @param editable
     *            if the property is read only or can be manipulated
     * @return the downmost property that has been created
     */
    IProperty addProperty(final String qualifiedPropertyName, final String description, final boolean editable);

    /**
     * Returns the property with the qualified name. for example an qualifiedPropertyName of foo.bar would return the
     * property bar, that has a parent property foo.
     * 
     * @param qualifiedPropertyName
     *            qualified name sepereaded by dots (".")
     * @return an existing property or null if one of the properties within the requested hierarchy could not be found
     *         (either foo or bar in the example above)
     */
    IProperty getProperty(final String qualifiedPropertyName);

    /**
     * Checks if a property with the qualified name is available. for example an qualifiedPropertyName
     * of foo.bar would check if foo is available and if foo contains bar.
     *
     * @param qualifiedPropertyName
     *            qualified name sepereaded by dots (".")
     * @return boolean if a property with qualifiedPropertyName is available
     *         (foo and bar in the example above)
     */
    boolean hasProperty(final String qualifiedPropertyName);

    /**
     * Sets the value of an property. If the property does not yet exists, the property will be created
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @param value
     */
    void setPropertyValue(final String qualifiedPropertyName, final String value);

    /**
     * Sets the value of an property. If the property does not yet exists, the property will be created
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @param value
     */
    void setPropertyValue(final String qualifiedPropertyName, final Number value);

    /**
     * Sets the value of an property. If the property does not yet exists, the property will be created
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @param value
     */
    void setPropertyValue(final String qualifiedPropertyName, final boolean value);

    /**
     * returns the value of an existing property as String
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @return String if the property could be found, null otherwise
     * @throws ClassCastException
     *             if the property has been used to store an value that can not be tranformed to an String
     */
    String getPropertyValueAsString(final String qualifiedPropertyName) throws ClassCastException;

    /**
     * returns the value of an existing Property as a number
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @return a number if the property could be found, null otherwise
     * @throws ClassCastException
     *             if the property has not been used to store the expected value
     */
    <T extends Number> T getPropertyValueAsNumber(final String qualifiedPropertyName) throws ClassCastException;

    /**
     * returns the value of an existing Property as a boolean
     * 
     * @param qualifiedPropertyName
     *            qualified name seperated by dots (".") for example: foo.bar
     * @return true or false if the property could be found, null otherwise
     * @throws ClassCastException
     *             if the property has not been used to store the expected value
     */
    boolean getPropertyValueAsBoolean(final String qualifiedPropertyName) throws ClassCastException;

    IProperty removeProperty(final IProperty property);

    /**
     * returns all top level properties, e.g. properties assigned to this object
     * 
     * @note this method does not return sub properties as elements of the collection, to receive them you have to use
     *       the IProperty-API
     * @return null if there are no properties assigned to this object, otherwise all top level properties
     */
    Collection<IProperty> getAllProperties();

    /**
     * returns the index'.th element of this instance, that is type of (or inherits) from the given class.
     * 
     * To find the concrete value, the UObject iterates (within each call) through all UStructuralFeatures and checks if
     * the result type inherits the specified type. If that is the case and the value is not null the index value is
     * decremented until the index is <= 0. if the index is <= 0 the result is returned.
     * 
     * With regard to list features the TypePointer iterates through all instances of the list (always decrement the
     * index if the type match) before going to the next feature.
     *
     *
     * @param clazz
     *            class to search
     * @param index
     *            number of components that match the type condition that shall be skipped
     * @return the first instance that inherits (or match) the given clazz, or null if no instance could be found
     */
    <T extends UObject> T uComponent(Class<T> clazz, int index);

    /**
     * Same as <code>uComponent(Class<T> clazz, int index) but accepts the name of the class as argument This is merly a
     * utility function to be accessed by scripts or if the Class is known but not part of the dependency tree
     * 
     * @param className
     *            name of the class to search, e.g. name of the class as part of the UCoreMetaRepository
     * @param index
     *            number of components that match the type condition that shall be skipped
     * @return the first instance that inherits (or match) the given clazz, or null if no instance could be found
     */
    default <T extends UObject> T uComponent(String className, int index) {
        UClassifier cl = UCoreMetaRepository.findClassifierBySimpleName(className);
        if (cl != null)
            return uComponent((Class<T>) cl.getRepresentingClass(), index);
        return null;
    }

	public IDisposable oberserveProperty(String fqPropName, PropertyChangeListener pcl);
}
