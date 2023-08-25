package de.emir.model.domain.maritime.vessel;

/**
 *	@generated 
 */
public enum NavigationStatus  
{
	/**
	 *	@generated 
	 */
	UnderwayUsingEngine(0, "UnderwayUsingEngine", "UnderwayUsingEngine"), 
	
				/**
	 *	@generated 
	 */
	AtAnchor(1, "AtAnchor", "AtAnchor"), 
	
				/**
	 *	@generated 
	 */
	NotUnderCommand(2, "NotUnderCommand", "NotUnderCommand"), 
	
				/**
	 *	@generated 
	 */
	RestrictedManoeuverability(3, "RestrictedManoeuverability", "RestrictedManoeuverability"), 
	
				/**
	 *	@generated 
	 */
	ConstrainedByHerDraught(4, "ConstrainedByHerDraught", "ConstrainedByHerDraught"), 
	
				/**
	 *	@generated 
	 */
	Moored(5, "Moored", "Moored"), 
	
				/**
	 *	@generated 
	 */
	Aground(6, "Aground", "Aground"), 
	
				/**
	 *	@generated 
	 */
	EngagedInFising(7, "EngagedInFising", "EngagedInFising"), 
	
				/**
	 *	@generated 
	 */
	UnderwaySailing(8, "UnderwaySailing", "UnderwaySailing"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse9(9, "ReservedForFutureUse9", "ReservedForFutureUse9"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse10(10, "ReservedForFutureUse10", "ReservedForFutureUse10"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse11(11, "ReservedForFutureUse11", "ReservedForFutureUse11"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse12(12, "ReservedForFutureUse12", "ReservedForFutureUse12"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse13(13, "ReservedForFutureUse13", "ReservedForFutureUse13"), 
	
				/**
	 *	@generated 
	 */
	ReservedForFutureUse14(14, "ReservedForFutureUse14", "ReservedForFutureUse14"), 
	
				/**
	 *	@generated 
	 */
	NotDefined(15, "NotDefined", "NotDefined")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final NavigationStatus[] VALUE_TYPES = new NavigationStatus[]{
		UnderwayUsingEngine,
		AtAnchor,
		NotUnderCommand,
		RestrictedManoeuverability,
		ConstrainedByHerDraught,
		Moored,
		Aground,
		EngagedInFising,
		UnderwaySailing,
		ReservedForFutureUse9,
		ReservedForFutureUse10,
		ReservedForFutureUse11,
		ReservedForFutureUse12,
		ReservedForFutureUse13,
		ReservedForFutureUse14,
		NotDefined,
	};
			
			
	private NavigationStatus(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static NavigationStatus get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static NavigationStatus getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static NavigationStatus get(int value){
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
