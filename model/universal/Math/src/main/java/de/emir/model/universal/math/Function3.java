package de.emir.model.universal.math;
import de.emir.model.universal.math.Function3;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**

 * Interface for a mathematical function with three input values
 * @generated 
 */
@UMLInterface(name = "Function3")
public interface Function3 extends UObject 
{
	
	
	
	/**
	 *	@generated 
	 */
	double getValue(final double x, final double y, final double z);
}
