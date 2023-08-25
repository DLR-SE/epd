package de.emir.model.universal.math;
import de.emir.model.universal.math.FunctionN;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**

 * Interface for a function with N input values. The input has to be provided using a vector with dimension N
 * @generated 
 */
@UMLInterface(name = "FunctionN")
public interface FunctionN extends UObject 
{
	
	
	
	/**
	
	 * calculates the function
	 * @param v input vector with v.dimension() == N
	 * @generated 
	 */
	double getValue(final Vector v);
}
