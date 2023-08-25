package de.emir.model.universal.physics;

import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.physics.Environment;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "EnvironmentLayer", isAbstract = true, parent = IdentifiedObject.class)	
public interface EnvironmentLayer extends IdentifiedObject 
{
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem getEnvironmentCRS();
	
	/**
	 *	@generated 
	 */
	Environment getEnvironment();
	
}
