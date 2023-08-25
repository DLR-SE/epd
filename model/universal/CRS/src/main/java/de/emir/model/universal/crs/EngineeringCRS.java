package de.emir.model.universal.crs;

import de.emir.model.universal.crs.LocalCRS;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**

 * A contextually local coordinate reference system which can be divided into two broad categories: 
 * - earth-fixed systems applied to engineering activities on or near the surface of the earth; 
 * - CRSs on moving platforms such as road vehicles, vessels, aircraft or spacecraft.
 * \source ISO 19111:2007
 * @generated 
 */
@UMLClass(name = "EngineeringCRS", isAbstract = true, parent = LocalCRS.class)	
public interface EngineeringCRS extends LocalCRS 
{
	
}
