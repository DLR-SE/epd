package de.emir.tuml.ucore.runtime;

public class BinaryBlob {

	private byte[] 	mData;
	
	public BinaryBlob(byte[] data) {
		mData = data;
	}
	
	public byte[] get() { return mData; }
	public void set(final byte[] data) { mData = data; }
	
	public byte get(int idx) { return mData[idx]; }
	public void set(int idx, byte value) { mData[idx] = value; }
	
	
	
}
