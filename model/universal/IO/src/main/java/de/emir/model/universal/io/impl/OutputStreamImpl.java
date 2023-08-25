package de.emir.model.universal.io.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import de.emir.model.universal.io.IOutputStream;
import de.emir.model.universal.io.IoPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IOutputStream.class)
public class OutputStreamImpl extends UObjectImpl implements IOutputStream  
{
	
	private ByteArrayOutputStream mBuffer;
			
	/**
	 *	Default constructor
	 *	@generated not
	 */
	public OutputStreamImpl(){
		super();
		mBuffer = new ByteArrayOutputStream();
	}
	
	public OutputStreamImpl(ByteArrayOutputStream byteArrayOutputStream) {
		mBuffer = byteArrayOutputStream;
	}

	
	/**
	 *	Default copy constructor
	 *	@generated  not
	 */
	public OutputStreamImpl(final IOutputStream _copy) {
		mBuffer = new ByteArrayOutputStream();
		try {
			mBuffer.write(_copy.getByteBuffer().array());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public OutputStreamImpl(byte[] bytes) {
		this();
		write(bytes);		
	}
	
	public OutputStreamImpl(byte[] bytes, int offset, int length) {
		this();
		write(bytes, offset, length);		
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return IoPackage.Literals.OutputStream;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isValid() {
		return mBuffer != null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isEmpty() {
		return mBuffer == null || mBuffer.size() == 0;
	}


	/**
	 * @inheritDoc
	 * @generated
	 */
	public int size()
	{
		//TODO: 
		throw new UnsupportedOperationException("size not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final int v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final long v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final boolean v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final float v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final double v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public int write(final String v)
	{
		//TODO: 
		throw new UnsupportedOperationException("write not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "OutputStreamImpl{" +
		"}";
	}

	@Override
	public ByteBuffer getByteBuffer() {
		return ByteBuffer.wrap(mBuffer.toByteArray());
	}
	
	@Override
	public boolean write(byte[] data) {
		try {
			mBuffer.write(data);
			return true;
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean write(byte[] data, int offset, int length) {
		try {
			mBuffer.write(data, offset, length);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ByteArrayOutputStream getStream(){
		return mBuffer;
	}
}
