package de.emir.model.universal.io.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import de.emir.model.universal.io.IInputStream;
import de.emir.model.universal.io.IoPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IInputStream.class)
public class InputStreamImpl extends UObjectImpl implements IInputStream  
{
	
	private BufferedInputStream mBuffer;
	private ByteArrayInputStream mArrayInputStream;
			
	/**
	 *	Default constructor
	 *	@generated not
	 */
	public InputStreamImpl(){
		super();
		mBuffer = new BufferedInputStream(mArrayInputStream = new ByteArrayInputStream(new byte[]{}));
	}
	
	/**
	 *	Default copy constructor
	 *	@generated not
	 */
	public InputStreamImpl(final IInputStream _copy) {
		throw new UnsupportedOperationException("Not yet implemented");
//		mBuffer = new ByteArrayInputStream(_copy.getByteBuffer().array());
	}
	
	
	public InputStreamImpl(final InputStream in){
		mBuffer = new BufferedInputStream(in);
	}
	
	public InputStreamImpl(byte[] data) {
		mBuffer = new BufferedInputStream(mArrayInputStream = new ByteArrayInputStream(data));
	}

	public InputStreamImpl(byte[] receiveData, int length) {
		mBuffer = new BufferedInputStream(mArrayInputStream = new ByteArrayInputStream(receiveData, 0, length), length);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return IoPackage.Literals.InputStream;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isValid()
	{
		return mBuffer != null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isEmpty()
	{
		try {
			return mBuffer == null || mBuffer.available() == 0;
		} catch (IOException e) {
		}
		return true; //in this case an exception has been thrown, so we won't be able to read anything
	}

	/**
	 * @inheritDoc 
	 * @generated not
	 */
	@Override
	public int size()
	{
		try {
			return mBuffer != null ? mBuffer.available() : 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean readBoolean()
	{
		//TODO: 
		throw new UnsupportedOperationException("readBoolean not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int readInt()
	{
		//TODO: 
		throw new UnsupportedOperationException("readInt not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public long readLong()
	{
		//TODO: 
		throw new UnsupportedOperationException("readLong not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public float readFloat()
	{
		//TODO: 
		throw new UnsupportedOperationException("readFloat not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public double readDouble()
	{
		//TODO: 
		throw new UnsupportedOperationException("readDouble not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public String readString()
	{
		//TODO: 
		throw new UnsupportedOperationException("readString not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "InputStreamImpl{" +
		"}";
	}

	@Override
	public ByteBuffer getByteBuffer() {
		return ByteBuffer.wrap(toArray());
	}

	@Override
	public byte[] toArray() {
		try {
			mArrayInputStream.reset();
			byte[] data = new byte[mBuffer.available()];			
			mBuffer.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
