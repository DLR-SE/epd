package de.emir.model.universal.spatial.delegate;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.IDelegateInterface;

/**
*	delegation interface
*	@generated
*/
public interface IPoseDelegationInterface extends IDelegateInterface{
	/**
	 *	@generated 
	 */
	String readableString(Pose self);
	/**
	 *	@generated 
	 */
	Pose copy(Pose self);
	/**
	 *	@generated 
	 */
	void set(Pose self, final Coordinate _coord, final Orientation _ori);
}
