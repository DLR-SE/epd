package de.emir.model.universal.units;

/**
 *	@generated 
 */
public enum PowerUnit  
{
	/**
	 *	@generated 
	 */
	WATT(0, "WATT", "WATT"), 
	
				/**
	 *	@generated 
	 */
	MILLI_WATT(1, "MILLI_WATT", "MILLI_WATT"), 
	
				/**
	 *	@generated 
	 */
	MICRO_WATT(2, "MICRO_WATT", "MICRO_WATT"), 
	
				/**
	 *	@generated 
	 */
	NANNO_WATT(3, "NANNO_WATT", "NANNO_WATT"), 
	
				/**
	 *	@generated 
	 */
	KILO_WATT(4, "KILO_WATT", "KILO_WATT"), 
	
				/**
	 *	@generated 
	 */
	MEGA_WATT(5, "MEGA_WATT", "MEGA_WATT"), 
	
				/**
	 *	@generated 
	 */
	GIGA_WATT(6, "GIGA_WATT", "GIGA_WATT")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final PowerUnit[] VALUE_TYPES = new PowerUnit[]{
		WATT,
		MILLI_WATT,
		MICRO_WATT,
		NANNO_WATT,
		KILO_WATT,
		MEGA_WATT,
		GIGA_WATT,
	};
			
			
	private PowerUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static PowerUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static PowerUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static PowerUnit get(int value){
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
