package de.emir.model.universal.math;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**
 *	@generated 
 */
@UMLInterface(name = "Vector")
public interface Vector extends UObject 
{
	
	/**
	 *	@generated 
	 */
	int dimensions();
	/**
	 *	@generated 
	 */
	double get(final int idx);
	/**
	 *	@generated 
	 */
	void set(final int idx, final double value);
	/**
	 *	@generated 
	 */
	void set(final Vector v);
	/**
	 *	@generated 
	 */
	Vector copy();
	/**
	 *	@generated 
	 */
	String readableString();
	/**
	 *	@generated 
	 */
	double getLength();
	/**
	 *	@generated 
	 */
	double getSquareLength();
	
	
	public double[] toArray();
}
