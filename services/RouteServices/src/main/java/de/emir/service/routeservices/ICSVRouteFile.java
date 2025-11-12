package de.emir.service.routeservices;

import de.emir.service.routeservices.IRouteImport;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 * Simple CSV route importer. This importer expects a ';'-delimited file with the following fields:
 * "name; latitude; longitude; speedMin; speedMax; portsideXTD; starboardXTD". All fields must be
 * specified in this order, where name is a String and all other fields are doubles. The parser will
 * check the first line for the correct header naming. The parser is able to process empty and
 * missing fields, as long as at least 3 field (name, lat, lon) are present. Thus, shorter lines
 * (less than all fields) and empty fields (;;) are allowed. Speed values are expected to be in
 * knots. Distance values must be nautical miles.
 *
 *	@generated
 */
@UMLClass(name = "CSVRouteFile", parent = IRouteImport.class)	
public interface ICSVRouteFile extends IRouteImport 
{
	
}
