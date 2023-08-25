package de.emir.service.routeservices;

import de.emir.service.routeservices.IRouteImport;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 Loads a simple txt based route file, as it could be exported from the EPD
 * e.g: 
 * Route Template
 * Start56 17.000N011 00.000E10.010.1,0.10.0
 * Stop56 17.000N012 20.000E0.0000.0,0.00.5
 * @generated 
 */
@UMLClass(name = "SimpleRouteFile", parent = IRouteImport.class)	
public interface ISimpleRouteFile extends IRouteImport 
{
	/**
	 if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) 
	 * otherwise the degree minute second notation (56ï¿½17.3''
	 * @generated 
	 */
	@UMLProperty(name = "dezimalNotation", associationType = AssociationType.PROPERTY)
	public void setDezimalNotation(boolean _dezimalNotation);
	/**
	 if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) 
	 * otherwise the degree minute second notation (56ï¿½17.3''
	 * @generated 
	 */
	@UMLProperty(name = "dezimalNotation", associationType = AssociationType.PROPERTY)
	public boolean getDezimalNotation();

	
}
