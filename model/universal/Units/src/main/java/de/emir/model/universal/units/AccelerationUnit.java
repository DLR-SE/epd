package de.emir.model.universal.units;

import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AccelerationUnitImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "AccelerationUnit")	
public interface AccelerationUnit extends UObject 
{
	
	public AccelerationUnit METER_PER_SQUARE_SECOND = new AccelerationUnitImpl(TimeUnit.SECOND, SpeedUnit.METER_PER_SECOND);
	public AccelerationUnit METER_PER_SQUARE_MINUTE = new AccelerationUnitImpl(TimeUnit.MINUTE, SpeedUnit.METER_PER_MINUTE);
	public AccelerationUnit METER_PER_SQUARE_HOUR = new AccelerationUnitImpl(TimeUnit.HOUR, SpeedUnit.METER_PER_HOUR);
	
	public AccelerationUnit KM_PER_SQUARE_SECOND = new AccelerationUnitImpl(TimeUnit.SECOND, SpeedUnit.KM_PER_SECOND);
	public AccelerationUnit KM_PER_SQUARE_MINUTE = new AccelerationUnitImpl(TimeUnit.MINUTE, SpeedUnit.KM_PER_MINUTE);
	public AccelerationUnit KM_PER_SQUARE_HOUR = new AccelerationUnitImpl(TimeUnit.HOUR, SpeedUnit.KMH);
	
	public AccelerationUnit NAUTICALMILES_PER_SQUARE_SECOND = new AccelerationUnitImpl(TimeUnit.SECOND, SpeedUnit.NAUTICALMILES_PER_SECOND);
	public AccelerationUnit NAUTICALMILES_PER_SQUARE_MINUTE = new AccelerationUnitImpl(TimeUnit.MINUTE, SpeedUnit.NAUTICALMILES_PER_MINUTE);
	public AccelerationUnit NAUTICALMILES_PER_SQUARE_HOUR = new AccelerationUnitImpl(TimeUnit.HOUR, SpeedUnit.KNOTS);
	
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speedUnit", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public void setSpeedUnit(SpeedUnit _speedUnit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "speedUnit", associationType = AssociationType.COMPOSITE, lowerBound = 1)
	public SpeedUnit getSpeedUnit();
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

	
	double getFactorToMeterPerSquareSecond();
	double getFactorFromMeterPerSquareSecond();
}
