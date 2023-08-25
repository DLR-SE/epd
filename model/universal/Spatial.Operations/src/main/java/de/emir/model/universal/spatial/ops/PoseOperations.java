package de.emir.model.universal.spatial.ops;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.delegate.IPoseDelegationInterface;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Quaternion;

/**
 *	@generated 
 */
public class PoseOperations  implements IPoseDelegationInterface{
	
	/**
	 * @inheritDoc
	 * @generated
	*/
	public String readableString(Pose self)
	{
		//TODO: 
		throw new UnsupportedOperationException("readableString not yet implemented");
	}
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public Pose copy(Pose self)
	{
		return new PoseImpl(self);
	}
	/**
	 * @inheritDoc
	 * @generated not
	*/
	@Override
	public void set(Pose self, final Coordinate coord, final Orientation ori)
	{
		if(self.getCoordinate() == null)
			self.setCoordinate(coord);
		else
			self.getCoordinate().set(coord);
		
		if (self.getOrientation() == null)
			self.setOrientation(ori);
		else{
			if (self.getOrientation() instanceof Euler)
				((Euler)self.getOrientation()).set(ori.toEuler());
			else
				((Quaternion)self.getOrientation()).set(ori.toQuaternion());
		}
		
	}
}
