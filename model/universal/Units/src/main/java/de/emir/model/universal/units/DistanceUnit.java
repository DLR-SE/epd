package de.emir.model.universal.units;

/**

 * Any of the measuring systems to measure the length, distance between two entities.  
 * Example are the English System of feet and inches or the metric system of  millimeters, centimeters and meters, 
 * also the System International (SI) System of Units.
 * @generated not
 */
public enum DistanceUnit  
{
	/**
	 *	@generated 
	 */
	METER(0, "METER", "METER"), 
	
				/**
	 *	@generated 
	 */
	KILOMETER(1, "KILOMETER", "KILOMETER"), 
	
				/**
	 *	@generated 
	 */
	CENTIMETER(2, "CENTIMETER", "CENTIMETER"), 
	
				/**
	 *	@generated 
	 */
	MILLIMETER(3, "MILLIMETER", "MILLIMETER"), 
	
				/**
	 also called statue mile or land mile. One mile is exactly 1609.344 meters 
	 * @generated 
	 */
	MILES(4, "MILES", "MILES"), 
	
				/**
	 a nautical mile is exactly 1852 meters or 1.15 miles 
	 * @generated 
	 */
	NAUTICAL_MILES(5, "NAUTICAL_MILES", "NAUTICAL_MILES"), 
	
				/**
	 One foot is exaclty 30.48 centimeters 
	 * @generated 
	 */
	FOOT(6, "FOOT", "FOOT"), 
	
				/**
	 one yard is defined as 3 foot or 0.9144m 
	 * @generated 
	 */
	YARD(7, "YARD", "YARD"), 
	
				/**
	 one cable is a tenth 1/10 of a nautical mile therefore 1 kbl = 185.2 meters 
	 * @generated 
	 */
	CABLE(8, "CABLE", "CABLE")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final DistanceUnit[] VALUE_TYPES = new DistanceUnit[]{
		METER,
		KILOMETER,
		CENTIMETER,
		MILLIMETER,
		MILES,
		NAUTICAL_MILES,
		FOOT,
		YARD,
		CABLE,
	};
			
			
	private DistanceUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static DistanceUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static DistanceUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static DistanceUnit get(int value){
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
	public double getFactorFromMeter() {
		switch(this){
		case CENTIMETER : return 10;
		case KILOMETER : return 0.001;
		case METER : return 1;
		case MILES : return 0.000621371192;
		case MILLIMETER : return 1000;
		case NAUTICAL_MILES : return 0.000539956803;
		case FOOT : return 3.28084;
		case CABLE : return 0.00455672207641;
		case YARD : return 1.0936133;
		}
		throw new UnsupportedOperationException();
	}

	public double getFactorToMeter() {
		switch(this){
		case CENTIMETER : return 0.01;
		case KILOMETER : return 1000;
		case METER : return 1;
		case MILES : return 1609.344;
		case MILLIMETER : return 0.001;
		case NAUTICAL_MILES : return 1852;
		case FOOT : return 0.3048;
		case CABLE : return 219.456;
		case YARD : return 0.9144;		
		}
		throw new UnsupportedOperationException();
	}
}
