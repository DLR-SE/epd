package de.emir.model.universal.units;

/**
 *	@generated 
 */
public enum AngleUnit  
{
	/**
	 *	@generated 
	 */
	RADIAN(0, "RADIAN", "RADIAN"), 
	
				/**
	 *	@generated 
	 */
	DEGREE(1, "DEGREE", "DEGREE"), 
	
				/**
	 *	@generated 
	 */
	REVOLUTION(2, "REVOLUTION", "REVOLUTION")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final AngleUnit[] VALUE_TYPES = new AngleUnit[]{
		RADIAN,
		DEGREE,
		REVOLUTION,
	};
			
			
	private AngleUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static AngleUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AngleUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AngleUnit get(int value){
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
