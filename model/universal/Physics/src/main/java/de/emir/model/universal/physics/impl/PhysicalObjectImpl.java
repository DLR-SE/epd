package de.emir.model.universal.physics.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.PoseChangeCapability;
import de.emir.model.universal.physics.impl.LocatableObjectImpl;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.spatial.Pose;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;


/**
 *	@generated 
 */
@UMLImplementation(classifier = PhysicalObject.class)
public class PhysicalObjectImpl extends LocatableObjectImpl implements PhysicalObject  
{
	
	
	/**
	 *	@generated 
	 */
	private List<ICharacteristic> mCharacteristics = null;
	/**
	 *	@generated 
	 */
	private List<ICapability> mCapabilities = null;
	/**
	 *	@generated 
	 */
	private List<PhysicalObject> mChildren = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PhysicalObjectImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PhysicalObjectImpl(final PhysicalObject _copy) {
		super(_copy);
		mCharacteristics = _copy.getCharacteristics();
		mCapabilities = _copy.getCapabilities();
		mChildren = _copy.getChildren();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PhysicalObjectImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, RelativeEngineering2D _ownedCoordinateSystem, Pose _pose, List<ICharacteristic> _characteristics, List<ICapability> _capabilities, List<PhysicalObject> _children) {
		super(_name,_allias,_remarks,_observers,_identifier,_ownedCoordinateSystem,_pose);
		mCharacteristics = _characteristics; 
		mCapabilities = _capabilities; 
		mChildren = _children; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.PhysicalObject;
	}

	/**
	 *	@generated 
	 */
	public List<ICharacteristic> getCharacteristics() {
		if (mCharacteristics == null) {
			mCharacteristics = new UContainmentList<ICharacteristic>(this, PhysicsPackage.theInstance.getPhysicalObject_characteristics()); 
		}
		return mCharacteristics;
	}

	/**
	 *	@generated 
	 */
	public List<ICapability> getCapabilities() {
		if (mCapabilities == null) {
			mCapabilities = new UContainmentList<ICapability>(this, PhysicsPackage.theInstance.getPhysicalObject_capabilities()); 
		}
		return mCapabilities;
	}

	/**
	 *	@generated 
	 */
	public List<PhysicalObject> getChildren() {
		if (mChildren == null) {
			mChildren = new UContainmentList<PhysicalObject>(this, PhysicsPackage.theInstance.getPhysicalObject_children()); 
		}
		return mChildren;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public ICharacteristic getFirstCharacteristic(final UClassifier type, final boolean includeSubTypes)
	{
		for (ICharacteristic c : getCharacteristics()) {
			if (c == null)
				continue;
			if (c.getClass() == type.getRepresentingClass())
				return c;
			if (includeSubTypes && c.getUClassifier().inherits(type))//TypeUtils.inherits(c.getClass(), type.getRepresentingClass()))
				return c;
		}
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public List<ICharacteristic> getAllCharacteristics(final UClassifier type, final boolean includeSubTypes)
	{
		ArrayList<ICharacteristic> out = new ArrayList<>();
		for (ICharacteristic c : getCharacteristics()) {
			if (c.getClass() == type.getRepresentingClass())
				out.add(c);
			else if (includeSubTypes && TypeUtils.inherits(c.getClass(), type.getRepresentingClass()))
				out.add(c);
		}
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public ICapability getFirstCapability(final UClassifier type, final boolean includeSubTypes)
	{
		for (ICapability c : getCapabilities()) {
			if (c.getClass() == type.getRepresentingClass())
				return c;
			if (includeSubTypes && TypeUtils.inherits(c.getClass(), type.getRepresentingClass()))
				return c;
		}
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public List<ICapability> getAllCapabilities(final UClassifier type, final boolean includeSubTypes)
	{
		ArrayList<ICapability> out = new ArrayList<>();
		for (ICapability c : getCapabilities()) {
			if (c.getClass() == type.getRepresentingClass())
				out.add(c);
			else if (includeSubTypes && TypeUtils.inherits(c.getClass(), type.getRepresentingClass()))
				out.add(c);
		}
		return out;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public PhysicalObject getParent()
	{
		//TODO: 
		throw new UnsupportedOperationException("getParent not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public PoseChangeCapability getPoseChangeCapability()
	{
		//TODO: 
		throw new UnsupportedOperationException("getPoseChangeCapability not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Pose getWorldPose()
	{
		if (getParent() == null)
			return getLocalPose();
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Pose getLocalPose()
	{
		return getPose();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setWorldPose(final Pose wp)
	{
		PoseChangeCapability pcc = getPoseChangeCapability();
		if (pcc == null)
			throw new NullPointerException("Static Object");
		if (getParent() == null)
			setPose(wp);
		else
			throw new UnsupportedOperationException("Not yet implemented"); //TODO: Implement
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PhysicalObjectImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	@Override
	public <T extends ICharacteristic> T getFirstCharacteristic(Class<? extends T> clazz, boolean includeSubTypes) {
		UClassifier cl = UCoreMetaRepository.getClassifier(clazz);
		if (cl != null )
			return (T) getFirstCharacteristic(cl, includeSubTypes);
		else
			ULog.debug("Could not find UClassifier for: " + clazz.getName());
		return null;
	}
	
	@Override
	public <T extends ICapability> T getFirstCapability(Class<? extends T> clazz, boolean includeSubTypes) {
		UClassifier cl = UCoreMetaRepository.getClassifier(clazz);
		if (cl != null )
			return (T) getFirstCapability(cl, includeSubTypes);
		else
			ULog.debug("Could not find UClassifier for: " + clazz.getName());
		return null;
	}
}
