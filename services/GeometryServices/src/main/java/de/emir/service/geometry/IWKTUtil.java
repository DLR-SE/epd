package de.emir.service.geometry;

import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "WKTUtil")	
public interface IWKTUtil extends UObject 
{
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Geometry loadWKT(final String wkt);
	
	/**
	 *	@generated 
	 */
	String exportWKT(final Geometry geom);
	
}
