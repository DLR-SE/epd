package de.emir.model.universal.physics;

import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.LocatableObject;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "PhysicalObject", parent = LocatableObject.class)	
public interface PhysicalObject extends LocatableObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "characteristics", associationType = AssociationType.COMPOSITE)
	public List<ICharacteristic> getCharacteristics();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "capabilities", associationType = AssociationType.COMPOSITE)
	public List<ICapability> getCapabilities();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "children", associationType = AssociationType.COMPOSITE)
	public List<PhysicalObject> getChildren();
	
	/**
	 *	@generated 
	 */
	ICharacteristic getFirstCharacteristic(final UClassifier type, final boolean includeSubTypes);
	/**
	 *	@generated 
	 */
	List<ICharacteristic> getAllCharacteristics(final UClassifier type, final boolean includeSubTypes);
	/**
	 *	@generated 
	 */
	ICapability getFirstCapability(final UClassifier type, final boolean includeSubTypes);
	/**
	 *	@generated 
	 */
	List<ICapability> getAllCapabilities(final UClassifier type, final boolean includeSubTypes);
	
	public <T extends ICharacteristic> T getFirstCharacteristic(Class<? extends T> clazz, boolean includeSubTypes);
	default public <T extends ICharacteristic> T getFirstCharacteristic(Class<? extends T> clazz) { return getFirstCharacteristic(clazz, true); }
	
	public <T extends ICapability> T getFirstCapability(Class<? extends T> clazz, boolean includeSubTypes);
	default public <T extends ICapability> T getFirstCapability(Class<? extends T> clazz) { return getFirstCapability(clazz, true); }
}
