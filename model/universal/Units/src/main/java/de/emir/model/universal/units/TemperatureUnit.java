package de.emir.model.universal.units;

/**
 *	@generated not
 */
public enum TemperatureUnit  
{
	/**
	 *	@generated 
	 */
	KELVIN(0, "KELVIN", "KELVIN"), 
	
				/**
	 *	@generated 
	 */
	CELSIUS(1, "CELSIUS", "CELSIUS"), 
	
				/**
	 *	@generated 
	 */
	FARENHEIT(2, "FARENHEIT", "FARENHEIT")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final TemperatureUnit[] VALUE_TYPES = new TemperatureUnit[]{
		KELVIN,
		CELSIUS,
		FARENHEIT,
	};
			
			
	private TemperatureUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static TemperatureUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static TemperatureUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static TemperatureUnit get(int value){
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
