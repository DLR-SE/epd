package de.emir.model.universal.units;

import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AngularAccelerationUnitImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "AngularAccelerationUnit")	
public interface AngularAccelerationUnit extends UObject 
{
	public static AngularAccelerationUnit DEGREE_PER_SQUARE_SECOND = new AngularAccelerationUnitImpl(AngularSpeedUnit.DEGREES_PER_SECOND, TimeUnit.SECOND);
	public static AngularAccelerationUnit DEGREE_PER_SQUARE_MINUTE = new AngularAccelerationUnitImpl(AngularSpeedUnit.DEGREES_PER_MINUTE, TimeUnit.MINUTE);
	
	public static AngularAccelerationUnit RADIANS_PER_SQUARE_SECOND = new AngularAccelerationUnitImpl(AngularSpeedUnit.RADIANS_PER_SECOND, TimeUnit.SECOND);
	public static AngularAccelerationUnit RADIANS_PER_SQUARE_MINUTE = new AngularAccelerationUnitImpl(AngularSpeedUnit.RADIANS_PER_MINUTE, TimeUnit.MINUTE);
	
	public static AngularAccelerationUnit ROUNDS_PER_SQUARE_SECOND = new AngularAccelerationUnitImpl(AngularSpeedUnit.ROUNDS_PER_SECOND, TimeUnit.SECOND);
	public static AngularAccelerationUnit ROUNDS_PER_SQUARE_MINUTE = new AngularAccelerationUnitImpl(AngularSpeedUnit.ROUNDS_PER_MINUTE, TimeUnit.MINUTE);
	
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speedUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setSpeedUnit(AngularSpeedUnit _speedUnit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speedUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngularSpeedUnit getSpeedUnit();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "timeUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setTimeUnit(TimeUnit _timeUnit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "timeUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public TimeUnit getTimeUnit();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	String readableString();
	
}
