package de.emir.model.universal.units;

/**
 *	@generated not
 */
public enum MassUnit  
{
	/**
	 *	@generated 
	 */
	KILOGRAM(0, "KILOGRAM", "KILOGRAM"), 
	
				/**
	 *	@generated 
	 */
	GRAM(1, "GRAM", "GRAM"), 
	
				/**
	 *	@generated 
	 */
	TONNE(2, "TONNE", "TONNE")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final MassUnit[] VALUE_TYPES = new MassUnit[]{
		KILOGRAM,
		GRAM,
		TONNE,
	};
			
			
	private MassUnit(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static MassUnit get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static MassUnit getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static MassUnit get(int value){
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
	
	public double getFactorFromKilogram() {
		switch(this){
		case GRAM : return 1000;
		case KILOGRAM : return 1;
		case TONNE : return 0.001;
		}
		throw new UnsupportedOperationException();
	}

	public double getFactorToKilogram() {
		switch(this){
		case GRAM : return 0.001;
		case KILOGRAM : return 1;
		case TONNE : return 1000;
		}
		throw new UnsupportedOperationException();
	}

	public String getSymbol() {
		switch(this){
		case GRAM : return "[g]";
		case KILOGRAM : return "[kg]";
		case TONNE : return "[t]";
		}
		throw new UnsupportedOperationException();
	}
}
