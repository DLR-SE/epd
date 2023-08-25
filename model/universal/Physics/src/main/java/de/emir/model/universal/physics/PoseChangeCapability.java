package de.emir.model.universal.physics;

import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "PoseChangeCapability", parent = Capability.class)	
public interface PoseChangeCapability extends Capability 
{
	
	/**
	 *	@generated 
	 */
	boolean setLocation(final Coordinate coordinate);

	/**
	 *	@generated 
	 */
	boolean setOrientation(final Orientation rotation);
	
}
