package de.emir.model.universal.units;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.units.DirectedMeasure;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Velocity is the instantaneous rate of change of position with time in a specified direction.  
 * The magnitude of the vector representing a value for velocity is expressed in units of measure appropriate for speed.
 * \source ISO 19103 Conceptual Schema Language 
 * @generated 
 */
@UMLClass(name = "Velocity", parent = DirectedMeasure.class)	
public interface Velocity extends DirectedMeasure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setValue(Vector _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public Vector getValue();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(SpeedUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public SpeedUnit getUnit();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY)
	public void setCrs(CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY)
	public CoordinateReferenceSystem getCrs();
	/**
	 
	 * returns a vector which components are given in the provided SpeedUnit
	 * @note if the provided unit is the same as this.unit, this.value is returned. 
	 * @generated 
	 */
	Vector getAs(final SpeedUnit unit);
	/**
	 *	@generated 
	 */
	Vector interpolate(final Vector current, final Time etablished);
	/**
	 *	@generated 
	 */
	String readableString();
	
	/**
	 *	@generated 
	 */
	Speed getMagnitude();
	
	/**
	 *	@generated 
	 */
	void set(final Vector value, final SpeedUnit unit, final CoordinateReferenceSystem crs);
	/**
	 *	@generated 
	 */
	void set(final Velocity other);
	/**
	
	 * transforms the velocity into the <param>targetCRS</param> coordinate reference system
	 * if targetCRS equals to crs, a copy of this velocity is returned
	 * @param targetCRS destination coordinate reference system
	 * @return a new velocity, expressed in targetCRS
	 * @generated 
	 */
	Velocity get(final CoordinateReferenceSystem targetCRS);
	
}
