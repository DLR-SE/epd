//package de.emir.model.universal.spatial.sf.ops;
//
//import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
//import de.emir.tuml.ucore.runtime.UClass;
//import de.emir.model.universal.spatial.Envelope;
//import de.emir.model.universal.spatial.impl.EnvelopeImpl;
//
///**
// *	@generated 
// */
//public class CoordinateSequenceOperations  implements ICoordinateSequenceDelegationInterface{
//
//	/**
//	 * @note this method is not optimized its allways recalculated
//	 * @inheritDoc
//	 * @generated not
//	*/
//	@Override
//	public Envelope getEnvelope(CoordinateSequence self)
//	{
//		if (self == null || self.getCoordinats().isEmpty())
//			return null;
//		Envelope env = new EnvelopeImpl(self.getCoordinats().get(0).copy());
//		for (int i = 1; i < self.getCoordinats().size(); i++)
//			env.expandLocal(self.getCoordinats().get(i));
//		return env;
//	}
//	
//}
