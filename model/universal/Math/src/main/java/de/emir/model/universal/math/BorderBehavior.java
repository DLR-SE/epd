package de.emir.model.universal.math;

/**
 *	@generated 
 */
public enum BorderBehavior  
{
	/**
	 the function returns the value of the first or last sample 
	 * @generated 
	 */
	MIN_MAX_VALUE(0, "MIN_MAX_VALUE", "MIN_MAX_VALUE"), 
	
				/**
	 the function does a linear interpolation (based on nearest two values, e.g. sample[1] & sample[0] for 
	 * x < sample[0].x and sample[n-1] & sample[n] if x > sample[n].x
	 * @generated 
	 */
	LINEAR_EXTRAPOLATE(1, "LINEAR_EXTRAPOLATE", "LINEAR_EXTRAPOLATE"), 
	
				/**
	 The function returns Not a Number (NaN) if the value is out of the sample range 
	 * @generated 
	 */
	NaN(2, "NaN", "NaN"), 
	
				/**
	 The function throws an InvalidArgument Exception 
	 * @generated 
	 */
	EXCEPTION(3, "EXCEPTION", "EXCEPTION")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final BorderBehavior[] VALUE_TYPES = new BorderBehavior[]{
		MIN_MAX_VALUE,
		LINEAR_EXTRAPOLATE,
		NaN,
		EXCEPTION,
	};
			
			
	private BorderBehavior(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static BorderBehavior get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static BorderBehavior getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static BorderBehavior get(int value){
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
