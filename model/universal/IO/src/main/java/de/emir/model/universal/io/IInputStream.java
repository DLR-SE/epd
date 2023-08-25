package de.emir.model.universal.io;

import java.nio.ByteBuffer;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "InputStream")	
public interface IInputStream extends UObject 
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
	boolean readBoolean();
	
	/**
	 *	@generated 
	 */
	int readInt();
	
	/**
	 *	@generated 
	 */
	long readLong();
	
	/**
	 *	@generated 
	 */
	float readFloat();
	
	/**
	 *	@generated 
	 */
	double readDouble();
	
	/**
	 *	@generated 
	 */
	String readString();
	
	ByteBuffer getByteBuffer();

	byte[] toArray();
}
