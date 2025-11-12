# UCore
TODO: Add general explanation of what UCore/TUML is, and what it does.

This pages provides an overview of common concepts that are related to the UCore-Module.

## Table of Contents
<!-- TOC -->
* [UCore](#ucore)
  * [Table of Contents](#table-of-contents)
* [Runtime Properties](#runtime-properties)
  * [Property API](#property-api)
  * [UCore-Properties and eMIR-S-100](#ucore-properties-and-emir-s-100)
* [Pointer](#pointer)
* [Pointer Strings](#pointer-strings-)
  * [Backus - Naur Form](#backus---naur-form-)
    * [Description](#description)
    * [Examples](#examples)
  * [User Interface](#user-interface-)
* [Extensions](#extensions)
  * [UCore Extensions](#ucore-extensions)
* [Observer / Listener](#observer--listener)
  * [TreeObserver](#treeobserver)
    * [TreeObserverOptions](#treeobserveroptions)
    * [TreeObserverWhiteListOptions](#treeobserverwhitelistoptions)
    * [TargetTypeTreeObserverOptions](#targettypetreeobserveroptions)
<!-- TOC -->
# Runtime Properties

Sometimes it is required to store additional information to an DataModel instance, that is not directly related to any DataModel.
For example layout information for a user interface. Even if it is possible to store those information in other files, this would result in a major increase of synchronisation effort.
To ease this problem, the UCore Runtime module provides an easy-to-use Property interface, that allows to annotate name - value pairs to any UObject, where the name would be some fully qualified name
like: "MyVisualisation.layout.center.x" and the value may be a double value of 42.0.

## Property API
The Property concepts are not available from tuml but manually added to the generated UCore - Interface.
The Property itself consists of:
- name : String
- description : String
- value : Object

However, it is recommended to use the API methods from UObject instead of the methods provided by IProperty, since the UObject API as well as the serialisation does only support a limited set
of values, that is: any number (int, float, double, ...), strings and boolean. Other Object values will result in an exception during serialisation and / or deserialisation.

```
class UObject
{
public:

	...

	/**
	 * Convinence method for addProperty(qualifiedPropertyName, description, true);
	 * @param qualifiedPropertyName
	 * @param description
	 * @return
	 */
	IProperty addProperty(final String qualifiedPropertyName, final String description);
	/**
	 * Adds a new property to this instance. the Property is defined through its qualified name, however the name of the created property will be the last segment of the qualified name. 
	 * If the qualified name contains more than one segment for example: foo.bar; the method creates an empty property foo (if it does not yet exists) as well as an empty property bar. 
	 * if a new property foo is created this property will have the same description as the property "bar". 
	 * 
	 * @note if you need description in group properties (e.g. "foo" property) use the method seperatly
	 * 
	 * @param qualifiedPropertyName qualified name, seperated by "."
	 * @param description
	 * @param editable if the property is read only or can be manipulated
	 * @return the downmost property that has been created
	 */
	IProperty addProperty(final String qualifiedPropertyName, final String description, final boolean editable);
	/**
	 * Returns the property with the qualified name. 
	 * for example an qualifiedPropertyName of foo.bar would return the property bar, that has a parent property foo. 
	 * @param qualifiedPropertyName qualified name sepereaded by dots (".")
	 * @return an existing property or null if one of the properties within the requested hierarchy could not be found (either foo or bar in the example above)
	 */
	IProperty getProperty(final String qualifiedPropertyName);
		
	/**
	 * Sets the value of an property. If the property does not yet exists, the property will be created
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @param value
	 */
	void setPropertyValue(final String qualifiedPropertyName, final String value);
	/**
	 * Sets the value of an property. If the property does not yet exists, the property will be created
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @param value
	 */
	void setPropertyValue(final String qualifiedPropertyName, final Number value);
	/**
	 * Sets the value of an property. If the property does not yet exists, the property will be created
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @param value
	 */
	void setPropertyValue(final String qualifiedPropertyName, final boolean value);
	
	/**
	 * returns the value of an existing property as String
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @return String if the property could be found, null otherwise
	 * @throws ClassCastException if the property has been used to store an value that can not be tranformed to an String
	 */
	String getPropertyValueAsString(final String qualifiedPropertyName) throws ClassCastException;
	/**
	 * returns the value of an existing Property as a number
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @return a number if the property could be found, null otherwise
	 * @throws ClassCastException if the property has not been used to store the expected value
	 */
	<T extends Number> T getPropertyValueAsNumber(final String qualifiedPropertyName) throws ClassCastException;
	/**
	 * returns the value of an existing Property as a boolean
	 * @param qualifiedPropertyName qualified name seperated by dots (".") for example: foo.bar
	 * @return true or false if the property could be found, null otherwise
	 * @throws ClassCastException if the property has not been used to store the expected value
	 */
	boolean getPropertyValueAsBoolean(final String qualifiedPropertyName) throws ClassCastException;
	
	IProperty removeProperty(final IProperty property);
	/**
	 * returns all top level properties, e.g. properties assigned to this object 
	 * @note this method does not return sub properties as elements of the collection, to receive them you have to use the IProperty-API
	 * @return null if there are no properties assigned to this object, otherwise all top level properties
	 */
	Collection<IProperty> getAllProperties();
	
	...

}
```

```java
public interface IProperty extends Serializable, Cloneable {

    public String getName();

    public String getDescription();

    public Class<?> getType();

    public Object getValue();

    public void setValue(Object value);

    public boolean isEditable();

    public String getCategory();

    
    
    public void addPropertyChangeListener(PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

    public IProperty getParentProperty();

    public Collection<IProperty> getSubProperties();

    /**
     * creates a deep copy of the property
     * @return
     */
	public IProperty copy();
}
```

## UCore-Properties and eMIR-S-100
The Properties concept of UCore is not directly eMIR-S-100 compatible, however the eMIR-S-100 provides the concept of InformationTypes that could be used to store additional information for an object.
We did decide against such an Option out of two reasons:
1) to support InformationTypes we need to either change the Meta Model to allow all Classes to hold zero or many InformationType instances and thus need an aditional Meta Type (besides class|struct|interface|primitive).
or we would need create such an association in each root level class (e.g. each class that has no parent classifier)
2) if the metadata would be part of the DataModel, those packages|projects need to be available in order to load a serialized instance. This however would imply (reminding the example above)
that visualisation packages|projects need to be available even if this information are not used by an application.

Nevertheless, the qualifiedName - value pair is intended to ease the transition from a property based representation to an DataModel based representation of those information.

# Pointer
The pointers used in eMIR are a mixture of the pointer concept from C++ and a path within a tree structure.
They are mainly used to point to certain places within the data model that shall be further processed by the underlying program.

The pointers offer some advantages over a simple reference:
- Pointers point to an element in a subtree of the data model depending on a root element. The pointer remains valid even if an instance within the subtree changes.
- Pointers can point to elements that are not currently available. The known data structures, which are on the way from the root element to the pointer destination, can be used to derive which information is available in any case. In this case, a minimum set of available features (members/variables) can be accessed.
- Pointers can be easily bent over by replacing the root element



# Pointer Strings 
Within the user interface, pointers are usually specified by a root element and a pointer string. The root element serves as the starting point of a data model subtree. The pointer string specifies how to navigate within the subtree to reach the actual data element.
The PointerString follows the following extended Backus-Naur form, based on the grammars of [XText](http://www.eclipse.org/Xtext/documentation/301_grammarlanguage.html).

## Backus - Naur Form 


```
Pointer				:= StructuralElement ( '.' StructuralElement)*
StructuralElement 	:= ( StructuralFeature | Operation | Component) ':' INT
StructuralFeature 	:= [UStructuralFeature | ID] ('<' [UClassifier|ID] '>')?
Operation 			:= [UOperation | ID] '(' ')'
Component 			:= ‘UComponent '<' UClassifier '>'

terminal INT :=  ('-')?('0'..'9')+;
terminal ID  := ('^'|'~')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
```


### Description


1. 	Each pointer can consist of a chain of structural elements of any length, separated by points.
      Each StructuralElement specifies a minimum data type from whose structural elements (member variables and operations) the next structural element can be selected.
2. 	A StructuralElement can be either a member variable (StructuralFeature), operation without arguments (Operation) or a component (Component - see below).
      Optionally, a list index can be accessed if the returned element is a list. An exception is the index 0, which is valid even if the structural element is not a list.
3. 	StructuralFeatures always refer to a member variable (de.emir.tuml.ucore.runtime.UStructuralFeature) as specified in a *.TUML file.
      Optionally, an expected data type can be specified ('<'[Classifier] '>' reference to an existing de.emir.tuml.ucore.runtime.UClassifier).
      In this case, the classifier is used to check the pointer string. (A cast does not take place).
4. 	An  Operation refers to an operation from the data model (de.emir.tuml.ucore.runtime.UOperation), also specified in the *.TUML file.
      The restriction is that the operation must not have any parameters.
5. 	If a Component is used within the pointer, it is a dynamic pointer that refers to the INT'te instance that inherits from the specified classifier.
      For this, the StructuralFeature, which contains the instance, does not have to be known.


### Examples

The following list shows some examples of valid pointer Strings, all with an PhysicalObject as root element.

| Pointer String                                                                 | Description                                                                                                                                                               |
|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pose.coordinate.x                                                              | points to the x coordinate of an pose                                                                                                                                     |
| pose.getCoordinate().getX()                                                    | points to the same x coordinate of the pose as the example above, but can not be written                                                                                  |
| characteristics:0.angularVelocity.value.z.value                                | assumes the first characteristic to be of type de.emir.model.universal.physics.DynamicObjectCharacteristic and points to the value of the z angle of its angular velocity |
| characteristics\<DynamicObjectCharacteristic\>:0.angularVelocity.value.z.value | same angular velocity pointer as above, but now ensures that the type of the 0 .th characteristic is of the correct type                                                  |
| UComponent\<DynamicObjectCharacteristic\>.linearVelocity.value.x               | get the first instance of an DynamicObjectCharacteristic and points to the x component of its linear velocity                                                             |
| UComponent\<DynamicObjectCharacteristic\>:0.getLinearVelocity().value.x        | same x value as above                                                                                                                                                     |
| UComponent\<DynamicObjectCharacteristic\>:1.linearVelocity.value.x             | if there are more DynamicObjectCharacteristic's available in the root element, this Pointer points to the second characteristic                                           |

## User Interface 
To simplify the specification of pointer strings in the user interface, there are special editors as shown in the following figure.

![Example of PointerString editor](images/PointerStringEditor.png)

The pointer string is specified via a text field (1).
The text field usually has an auto-completion function that can be started by pressing CTRL + SPACE.
Based on the existing pointer string the current data type is determined and a list of possible member variables is displayed (2).
(The list may not be complete if the current type is not yet known.).

If available, the documentation of the structural element is displayed in another popup window (3).
If the specific underlying instance is known, valid index values and their current instances (4) are displayed to simplify the selection of the correct index.

# Extensions
The class de.emir.tuml.ucore.runtime.UCoreExtensionManager provides access to runtime extensions, that can be registered and accessed from anywhere at runtime.

Each project that makes use of those extensions shall provide some documentation for the registered extensions and group them by using the UCoreExtensions group.

## UCore Extensions
The following Extensions are used by the UCore Project:
- ClassPathProvider:
The de.emir.tuml.ucore.runtime.resources.ClassPathProvider is used by de.emir.tuml.ucore.runtime.resources.ResourceManager to search resources inside the classpath
 
# Observer / Listener
To every ```UObject``` a listener can be registered. This can be used to react to changes within a model.  
There are two kinds of listeners:
1) Feature Listener are called whenever the value of the feature of the UObject is changed.
2) Classifier listener are called whenever <b> any </b> value of <b> any </b> feature is changed.

The method ```registerListener(final IValueChangeListener _listener)``` will register a classifier listener to the
```UObject``` while ```registerListener(final UStructuralFeature _feature, final IValueChangeListener _listener)``` will
register a feature listener to the ```UObject``` that only listens to changes of in the feature of that ```UObject```.
The listener need to implement ```IValueChangeListener``` especially the method ```onValueChange(final Notification<T> notification)```.
Though an anonymous lambda expression can be used as well.
## TreeObserver
In order to listen to a whole tree, tree listeners may be employed. These will add listeners to the to UObjects of the tree
and register and remove listeners when the tree is changing. A tree listener is added by registering it to the root ````UObject````
of the tree with ````registerTreeListener(final ITreeValueChangeListener listener)````.

Since a tree listener is, by default, called on any change in the tree, options may be added. These options are used to 
decide whether a classifier listener or a feature listener shall be added to a given UObject. Thus, it is used to ignore certain UObject in the 
tree with regard to the listener. Options are added by using the method ````registerTreeListener(final ITreeValueChangeListener listener, TreeObserverUtil.ITreeObserverOptions options)````.
Any subtree of a disregarded feature or object is disregarded as well.
### TreeObserverOptions
The TreeObserverOptions default implementation can filter the tree by the association type (e.g. composite) and may have an 
ignore list of features to disregard.
### TreeObserverWhiteListOptions
This Observer options has a whitelist of ````UStructuralFeatures```` any feature not contained within that whitelist is disregarded.
This may be employed if it is clear what exactly shall be registered to and has a lean implementation in contrast to more complex ObserverOptions.
### TargetTypeTreeObserverOptions
If listening to a certain class or defined classes is of interest, and it is unclear this observer options may be employed.
It allows registration to elements in the tree of the target classes as well as any feature that <b> can </b> own a subtree containing any instance of the target class.
This ObserverOptions uses reflections to evaluate features and is therefore computationally more expensive. Though it stores previous findings.
Therefore, this ObserverOption should be reused as often as possible and not be newly created if possible.

An exemplary use of this option would be to identify any UObject of type Vessel in a model. Listeners will be dynamically registered
to anything that may own a vessel and removal, addition and replacement of vessels can be tracked using this ObserverOption. 