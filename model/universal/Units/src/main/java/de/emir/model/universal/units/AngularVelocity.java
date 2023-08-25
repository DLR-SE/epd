package de.emir.model.universal.units;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.DirectedMeasure;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.model.universal.units.Euler;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * AngularVelocity is the instantaneous rate of change of angular displacement with time in a specified direction.
 * \source ISO 19103 Conceptual Schema Language 
 * @generated 
 */
@UMLClass(name = "AngularVelocity", parent = DirectedMeasure.class)	
public interface AngularVelocity extends DirectedMeasure 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setValue(Euler _value);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "value", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public Euler getValue();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setUnit(AngularSpeedUnit _unit);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "unit", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public AngularSpeedUnit getUnit();
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
	void set(final Euler value, final AngularSpeedUnit unit, final CoordinateReferenceSystem crs);
	/**
	 *	@generated 
	 */
	void set(final AngularVelocity other);
	/**
	 Returns the changed angle for each axis in a given time
	 * @deprecated use integrate instead
	 * @generated 
	 */
	Euler getChange(final Time elapsed);
	/**
	 Returns the changed angle for each axis in a given time 
	 * @generated 
	 */
	Euler integrate(final Time elapsed);
	/**
	 *	@generated 
	 */
	AngularSpeed getX();
	
	/**
	 *	@generated 
	 */
	AngularSpeed getY();
	
	/**
	 *	@generated 
	 */
	AngularSpeed getZ();
	/**
	 *	@generated 
	 */
	Euler getAs(final AngularSpeedUnit unit);
	
}
