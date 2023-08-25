package de.emir.model.universal.crs;

import de.emir.model.universal.crs.AxisDirection;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "CoordinateSystemAxis")	
public interface CoordinateSystemAxis extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
	public void setDirection(AxisDirection _direction);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "direction", associationType = AssociationType.PROPERTY)
	public AxisDirection getDirection();
	/**
	 symbolic name of this axis (axisSymbol in S100) 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public void setName(String _name);
	/**
	 symbolic name of this axis (axisSymbol in S100) 
	 * @generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public String getName();
	/**
	 defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	@UMLProperty(name = "minimumRange", associationType = AssociationType.PROPERTY)
	public void setMinimumRange(double _minimumRange);
	/**
	 defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	@UMLProperty(name = "minimumRange", associationType = AssociationType.PROPERTY)
	public double getMinimumRange();
	/**
	 defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	@UMLProperty(name = "maximumRange", associationType = AssociationType.PROPERTY)
	public void setMaximumRange(double _maximumRange);
	/**
	 defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs 
	 * @generated 
	 */
	@UMLProperty(name = "maximumRange", associationType = AssociationType.PROPERTY)
	public double getMaximumRange();

	
}
