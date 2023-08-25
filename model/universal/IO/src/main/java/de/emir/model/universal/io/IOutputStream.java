package de.emir.model.universal.io;

import java.nio.ByteBuffer;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "OutputStream")	
public interface IOutputStream extends UObject 
{
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	boolean isValid();
	
	/**
	 *	@generated 
	 */
	boolean isEmpty();
	
	/**
	 *	@generated 
	 */
	int size();
	
	/**
	 *	@generated 
	 */
	int write(final int v);
	
	/**
	 *	@generated 
	 */
	int write(final long v);
	
	/**
	 *	@generated 
	 */
	int write(final boolean v);
	
	/**
	 *	@generated 
	 */
	int write(final float v);
	
	/**
	 *	@generated 
	 */
	int write(final double v);
	
	/**
	 *	@generated 
	 */
	int write(final String v);


	ByteBuffer getByteBuffer();
	
	boolean write(byte[] data);
}
