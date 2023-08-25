package de.emir.model.universal.units;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "SpeedUnit")	
public interface SpeedUnit extends UObject 
{
	SpeedUnit METER_PER_SECOND = new SpeedUnitImpl(DistanceUnit.METER, TimeUnit.SECOND);
	SpeedUnit METER_PER_MINUTE = new SpeedUnitImpl(DistanceUnit.METER, TimeUnit.MINUTE);
	SpeedUnit METER_PER_HOUR = new SpeedUnitImpl(DistanceUnit.METER, TimeUnit.HOUR);
	SpeedUnit KNOTS = new SpeedUnitImpl(DistanceUnit.NAUTICAL_MILES, TimeUnit.HOUR);
	SpeedUnit KMH = new SpeedUnitImpl(DistanceUnit.KILOMETER, TimeUnit.HOUR);
	SpeedUnit KM_PER_MINUTE = new SpeedUnitImpl(DistanceUnit.KILOMETER, TimeUnit.MINUTE);
	SpeedUnit KM_PER_SECOND = new SpeedUnitImpl(DistanceUnit.KILOMETER, TimeUnit.SECOND);
	SpeedUnit NAUTICALMILES_PER_SECOND = new SpeedUnitImpl(DistanceUnit.NAUTICAL_MILES, TimeUnit.SECOND);
	SpeedUnit NAUTICALMILES_PER_MINUTE = new SpeedUnitImpl(DistanceUnit.NAUTICAL_MILES, TimeUnit.MINUTE);
	
	
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "distanceUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setDistanceUnit(DistanceUnit _distanceUnit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "distanceUnit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public DistanceUnit getDistanceUnit();
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
	/**
	 *	@generated 
	 */
	void set(final DistanceUnit du, final TimeUnit tu);
	/**
	 *	@generated 
	 */
	void set(final SpeedUnit unit);
	/**
	 *	@generated 
	 */
	String readableString();
	
}
