package de.emir.model.application.sensor.ais;

/**

 * AIS Emitter Class. Commercial ships are required to use A, private ships and small vessels usually use B.
 * @generated 
 */
public enum AISEmitterClass  
{
	/**
	 *	@generated 
	 */
	A(0, "A", "A"), 
	
				/**
	 *	@generated 
	 */
	B(1, "B", "B")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final AISEmitterClass[] VALUE_TYPES = new AISEmitterClass[]{
		A,
		B,
	};
			
			
	private AISEmitterClass(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static AISEmitterClass get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AISEmitterClass getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AISEmitterClass get(int value){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mValue == value)
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	@Override
	public String toString() {
		return mLabel;
	}
}
