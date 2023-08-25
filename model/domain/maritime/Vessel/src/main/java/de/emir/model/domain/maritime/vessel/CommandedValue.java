package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Represents a generic value that has been commanded
 * (typically by the captain) to be achieved in the future
 * @generated 
 */
@UMLClass(isAbstract = true)	
public interface CommandedValue extends UObject 
{

	/**
	 time of creation 
	 * @generated 
	 */
	@UMLProperty(name = "creationTime", associationType = AssociationType.COMPOSITE)
	public Time getCreationTime();

	/**
	 time of creation 
	 * @generated 
	 */
	@UMLProperty(name = "creationTime", associationType = AssociationType.COMPOSITE)
	public void setCreationTime(Time _creationTime);

	/**
	 Optional source of the command (for example the captain) 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public void setSource(ModelReference _source);

	/**
	 Optional source of the command (for example the captain) 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public ModelReference getSource();
	
}
