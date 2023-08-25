package de.emir.model.universal.units;

import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "AngularSpeedUnit")	
public interface AngularSpeedUnit extends UObject 
{
	AngularSpeedUnit DEGREES_PER_SECOND = new AngularSpeedUnitImpl(AngleUnit.DEGREE, TimeUnit.SECOND);
	AngularSpeedUnit DEGREES_PER_MINUTE = new AngularSpeedUnitImpl(AngleUnit.DEGREE, TimeUnit.MINUTE);
	AngularSpeedUnit DEGREES_PER_HOUR 	= new AngularSpeedUnitImpl(AngleUnit.DEGREE, TimeUnit.HOUR);
	
	AngularSpeedUnit RADIANS_PER_SECOND = new AngularSpeedUnitImpl(AngleUnit.RADIAN, TimeUnit.SECOND);
	AngularSpeedUnit RADIANS_PER_MINUTE = new AngularSpeedUnitImpl(AngleUnit.RADIAN, TimeUnit.MINUTE);
	AngularSpeedUnit RADIANS_PER_HOUR 	= new AngularSpeedUnitImpl(AngleUnit.RADIAN, TimeUnit.HOUR);
	
	AngularSpeedUnit ROUNDS_PER_SECOND = new AngularSpeedUnitImpl(AngleUnit.REVOLUTION, TimeUnit.SECOND); ///TODO: This need the factor 360
	AngularSpeedUnit ROUNDS_PER_MINUTE = new AngularSpeedUnitImpl(AngleUnit.REVOLUTION, TimeUnit.MINUTE); ///TODO: This need the factor 360
	AngularSpeedUnit ROUNDS_PER_HOUR = new AngularSpeedUnitImpl(AngleUnit.REVOLUTION, TimeUnit.HOUR); ///TODO: This need the factor 360
	
	AngularSpeedUnit RPM = ROUNDS_PER_MINUTE;
	
	
	
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angleUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setAngleUnit(AngleUnit _angleUnit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angleUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngleUnit getAngleUnit();
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
	
	/**
	 *	@generated 
	 */
	void set(final AngleUnit au, final TimeUnit tu);
	/**
	 *	@generated 
	 */
	void set(final AngularSpeedUnit other);
	
}
