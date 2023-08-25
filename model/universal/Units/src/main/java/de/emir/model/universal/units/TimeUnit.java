package de.emir.model.universal.units;

/**

 * A TimeUnit represents time durations at a given unit of granularity (...). 
 * A nanosecond is defined as one thousandth of a microsecond, 
 * a microsecond as one thousandth of a millisecond, 
 * a millisecond as one thousandth of a second, 
 * a minute as sixty seconds, 
 * an hour as sixty minutes, 
 * and a day as twenty four hours. 
 * 
 * @cite[doc. java.util.concurrent] 
 * @generated not
 */
public enum TimeUnit  
{
	/**
	 *	@generated 
	 */
	NANOSECOND(0, "NANOSECOND", "NANOSECOND"), 
	
				/**
	 *	@generated 
	 */
	MICROSECOND(1, "MICROSECOND", "MICROSECOND"), 
	
				/**
	 *	@generated 
	 */
	MILLISECOND(2, "MILLISECOND", "MILLISECOND"), 
	
				/**
	 *	@generated 
	 */
	SECOND(3, "SECOND", "SECOND"), 
	
				/**
	 *	@generated 
	 */
	MINUTE(4, "MINUTE", "MINUTE"), 
	
				/**
	 *	@generated 
	 */
	HOUR(5, "HOUR", "HOUR"), 
	
				/**
	 *	@generated 
	 */
	DAY(6, "DAY", "DAY")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final TimeUnit[] VALUE_TYPES = new TimeUnit[]{
		NANOSECOND,
		MICROSECOND,
		MILLISECOND,
		SECOND,
		MINUTE,
		HOUR,
		DAY,
	};
			
			
	private TimeUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static TimeUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static TimeUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static TimeUnit get(int value){
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
	
	
	
	public double getFactorToSecond() {
		switch(this){
		case DAY : return 60.0 * 60.0 * 24.0;
		case HOUR : return 60.0 * 60.0;
		case MINUTE: return 60.0;
		case SECOND : return 1;
		case MILLISECOND : return 1e-3;
		case MICROSECOND : return 1e-6;
		case NANOSECOND : return 1e-9;
		}
		throw new UnsupportedOperationException();
	}
	public double getFactorFromSecond() {
		switch(this){
		case DAY : return 1.0 / (60.0 * 60.0 * 24.0);
		case HOUR : return 1.0 / (60.0 * 60.0);
		case MINUTE : return 1.0 / 60.0;
		case SECOND : return 1;
		case MILLISECOND : return 1e3;
		case MICROSECOND : return 1e6;
		case NANOSECOND : return 1e9;
		}
		throw new UnsupportedOperationException();
	}

	public String getSymbol() {
		switch(this){
		case DAY : return "d";
		case HOUR : return "h";
		case MINUTE : return "m";
		case SECOND : return "s";
		case MILLISECOND : return "ms";
		case MICROSECOND : return "us";
		case NANOSECOND : return "ns";
		}
		throw new UnsupportedOperationException();
	}
}
