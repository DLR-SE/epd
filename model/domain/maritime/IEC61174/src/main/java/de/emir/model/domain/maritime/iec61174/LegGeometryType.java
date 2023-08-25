package de.emir.model.domain.maritime.iec61174;

/**
 *	@generated 
 */
public enum LegGeometryType  
{
	/**
	 *	@generated 
	 */
	Loxodome(0, "Loxodome", "Loxodome"), 
	
				/**
	 *	@generated 
	 */
	Orthodome(1, "Orthodome", "Orthodome")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final LegGeometryType[] VALUE_TYPES = new LegGeometryType[]{
		Loxodome,
		Orthodome,
	};
			
			
	private LegGeometryType(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static LegGeometryType get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static LegGeometryType getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static LegGeometryType get(int value){
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
