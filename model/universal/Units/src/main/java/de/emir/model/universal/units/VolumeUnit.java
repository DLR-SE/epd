package de.emir.model.universal.units;

/**
 *	@generated not
 */
public enum VolumeUnit  
{
	/**
	 *	@generated 
	 */
	CUBIC_METER(0, "CUBIC_METER", "CUBIC_METER"), 
	
				/**
	 *	@generated 
	 */
	BARREL(1, "BARREL", "BARREL"), 
	
				/**
	 *	@generated 
	 */
	CUBIC_FOOT(2, "CUBIC_FOOT", "CUBIC_FOOT"), 
	
				/**
	 *	@generated 
	 */
	CUBIC_DECIMETER(3, "CUBIC_DECIMETER", "CUBIC_DECIMETER"), 
	
				/**
	 *	@generated 
	 */
	LITRE(4, "LITRE", "LITRE"), 
	
				/**
	 *	@generated 
	 */
	GALLON(5, "GALLON", "GALLON"), 
	
				/**
	 *	@generated 
	 */
	PINT(6, "PINT", "PINT"), 
	
				/**
	 *	@generated 
	 */
	CUBIC_INCH(7, "CUBIC_INCH", "CUBIC_INCH"), 
	
				/**
	 *	@generated 
	 */
	CUBIC_CENTIMETER(8, "CUBIC_CENTIMETER", "CUBIC_CENTIMETER")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final VolumeUnit[] VALUE_TYPES = new VolumeUnit[]{
		CUBIC_METER,
		BARREL,
		CUBIC_FOOT,
		CUBIC_DECIMETER,
		LITRE,
		GALLON,
		PINT,
		CUBIC_INCH,
		CUBIC_CENTIMETER,
	};
			
			
	private VolumeUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static VolumeUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static VolumeUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static VolumeUnit get(int value){
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
	
	
	
	public double getFactorFromCubicMeter() {
		switch(this){
		case CUBIC_METER: return 1;
		case BARREL : return 1./0.158987;
		case CUBIC_CENTIMETER : return 1./0.000001;
		case CUBIC_DECIMETER : return 1./0.001;
		case CUBIC_FOOT : return 1./0.028316846592;
		case CUBIC_INCH: return 1./0.000016387064;
		case GALLON : return 1./0.158987294928;
		case LITRE : return 1./0.001;
		case PINT : return 1./0.000473176473;
		}
		throw new UnsupportedOperationException();
	}

	public double getFactorToCubicMeter() {
		switch(this){
		case CUBIC_METER: return 1;
		case BARREL : return 0.158987;
		case CUBIC_CENTIMETER : return 0.000001;
		case CUBIC_DECIMETER : return 0.001;
		case CUBIC_FOOT : return 0.028316846592;
		case CUBIC_INCH: return 0.000016387064;
		case GALLON : return 0.158987294928;
		case LITRE : return 0.001;
		case PINT : return 0.000473176473;		
		}
		throw new UnsupportedOperationException();
	}
}
