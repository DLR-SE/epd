package de.emir.model.universal;

import de.emir.model.universal.crs.CrsPackage;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.MathModel;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**

* The S-100 standard has been designed for the producers and users of hydrographic information, however its principles can be extended to many other forms of geographic information including maps, and text documents.
* The location of an object in the S-100 standard is defined by means of coordinates. Those coordinates relate a feature to a position. This Part describes all elements that are necessary to fully define the referencing by means of coordinate systems and datums. It defines the conceptual schema for the description of spatial referencing by coordinates and describes the minimum data required to define 1-, 2- and 3-dimensional spatial coordinate references.
* In addition to the elements necessary to define a coordinate reference system this Part also describes operations to transform coordinates from one coordinate reference system to another. This includes operations for datum transformation and map projections.
* Coordinate reference systems, as well as single elements to define them, may be registered in a register or defined by an organization in a document. This Part describes how those elements are identified.
* \source S-100 Standard Version 2 Part 6
 * @generated 
 */
public class CRSModel implements UCorePlugin {
	
	/**
	 * @generated
	 */
	public static void init(){
		if (initialized)
			return ;
		initialized = true;
		ULog.debug("initialize model CRS ... ");
		//initialize referenced models
		CoreModel.init();
		MathModel.init();
		
		//initialize sub packages
		CrsPackage.theInstance.init();
		ULog.debug("... model CRS initialized");
	}

	/**
	 * @generated 
	 */
	@Override
	public void initializePlugin(){
		//Method is called by plugin loader
		init();
	}

	/**
	 * @generated
	 */
	private static boolean initialized = false;
}
