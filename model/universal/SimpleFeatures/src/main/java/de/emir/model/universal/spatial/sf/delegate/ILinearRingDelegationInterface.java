package de.emir.model.universal.spatial.sf.delegate;

import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.delegate.ILineStringDelegationInterface;

/**
*	delegation interface
*	@generated
*/
public interface ILinearRingDelegationInterface extends ILineStringDelegationInterface{
	/**
	 *	@generated 
	 */
	boolean isClosed(LinearRing self);
	/**
	 closes the ring by copying the first coordinate to the point list, if not already closed 
	 * @generated 
	 */
	void close(LinearRing self);
}
