package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "EngineBuildInformation")	
public interface EngineBuildInformation extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.COMPOSITE)
	public void setManufactor(RSIdentifier _manufactor);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.COMPOSITE)
	public RSIdentifier getManufactor();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildYear", associationType = AssociationType.COMPOSITE)
	public void setBuildYear(Time _buildYear);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildYear", associationType = AssociationType.COMPOSITE)
	public Time getBuildYear();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "model", associationType = AssociationType.PROPERTY)
	public void setModel(String _model);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "model", associationType = AssociationType.PROPERTY)
	public String getModel();

	
}
