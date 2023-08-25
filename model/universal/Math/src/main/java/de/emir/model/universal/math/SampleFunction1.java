package de.emir.model.universal.math;

import de.emir.model.universal.math.BorderBehavior;
import de.emir.model.universal.math.Function1;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass	
public interface SampleFunction1 extends Function1 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "borderBehavior", associationType = AssociationType.COMPOSITE)
	public void setBorderBehavior(BorderBehavior _borderBehavior);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "borderBehavior", associationType = AssociationType.COMPOSITE)
	public BorderBehavior getBorderBehavior();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "samples", associationType = AssociationType.COMPOSITE)
	public List<Vector2D> getSamples();
	/**
	 optional human readable description of the function" 
	 * @generated 
	 */
	@UMLProperty(name = "label", associationType = AssociationType.PROPERTY)
	public void setLabel(String _label);
	/**
	 optional human readable description of the function" 
	 * @generated 
	 */
	@UMLProperty(name = "label", associationType = AssociationType.PROPERTY)
	public String getLabel();
	/**
	 optional human readable description of the domain for example "Time [s]" 
	 * @generated 
	 */
	@UMLProperty(name = "domainLabel", associationType = AssociationType.PROPERTY)
	public void setDomainLabel(String _domainLabel);
	/**
	 optional human readable description of the domain for example "Time [s]" 
	 * @generated 
	 */
	@UMLProperty(name = "domainLabel", associationType = AssociationType.PROPERTY)
	public String getDomainLabel();
	/**
	 optional human readable description of the range for example "Temperature [degrees]" 
	 * @generated 
	 */
	@UMLProperty(name = "rangeLabel", associationType = AssociationType.PROPERTY)
	public void setRangeLabel(String _rangeLabel);
	/**
	 optional human readable description of the range for example "Temperature [degrees]" 
	 * @generated 
	 */
	@UMLProperty(name = "rangeLabel", associationType = AssociationType.PROPERTY)
	public String getRangeLabel();
	/**
	
	 * Convenience method to add a new sample. The function creates a new Vector2D and insert it into the 
	 * samples list. If there is already an entry with the same x-value the existing entry will be changed for the new y
	 * @generated 
	 */
	Vector2D addSample(final double x, final double y);
	
}
