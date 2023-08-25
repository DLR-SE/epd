package de.emir.model.universal.crs;

/**
 *	@generated 
 */
public enum AxisDirection  
{
	/**
	
	     * Axis positive direction is north. In a geographic or projected CRS,
	     * north is defined through the geodetic datum. In an engineering CRS,
	     * north may be defined with respect to an engineering object rather
	     * than a geographical direction.
	     * [GeoAPI]
	 * @generated 
	 */
	NORTH(0, "NORTH", "NORTH"), 
	
				/**
	
	     * Axis positive direction is &pi;/2 radians clockwise from north.
	     * This is usually used for Grid X coordinates and Longitude.
	     * [GeoAPI]
	 * @generated 
	 */
	EAST(1, "EAST", "EAST"), 
	
				/**
	
	     * Axis positive direction is &pi; radians clockwise from north.
	     * [GeoAPI]
	 * @generated 
	 */
	SOUTH(2, "SOUTH", "SOUTH"), 
	
				/**
	
	     * Axis positive direction is 3&pi;/2 radians clockwise from north.
	     * This is usually used for Grid X coordinates and Longitude.
	     * [GeoAPI]
	 * @generated 
	 */
	WEST(3, "WEST", "WEST"), 
	
				/**
	
	     * Axis positive direction is up relative to gravity.
	     * [GeoAPI]
	 * @generated 
	 */
	UP(4, "UP", "UP"), 
	
				/**
	
	     * Axis positive direction is down relative to gravity.
	     * [GeoAPI]
	 * @generated 
	 */
	DOWN(5, "DOWN", "DOWN"), 
	
				/**
	
	     * Axis positive direction is in the equatorial plane from the centre of the
	     * modelled earth towards the intersection of the equator with the prime meridian.
	     * [GeoAPI]
	 * @generated 
	 */
	GEOCENTRIC_X(6, "GEOCENTRIC_X", "GEOCENTRIC_X"), 
	
				/**
	
	     * Axis positive direction is in the equatorial plane from the centre of the
	     * modelled earth towards the intersection of the equator and the meridian &pi;/2
	     * radians eastwards from the prime meridian.
	     * [GeoAPI]
	 * @generated 
	 */
	GEOCENTRIC_Y(7, "GEOCENTRIC_Y", "GEOCENTRIC_Y"), 
	
				/**
	
	     * Axis positive direction is from the centre of the modelled earth parallel to
	     * its rotation axis and towards its North Pole.
	     * [GeoAPI]
	 * @generated 
	 */
	GEOCENTRIC_Z(8, "GEOCENTRIC_Z", "GEOCENTRIC_Z"), 
	
				/**
	
	     * Axis positive direction is right in display.
	     * [GeoAPI]
	 * @generated 
	 */
	DISPLAY_LEFT(9, "DISPLAY_LEFT", "DISPLAY_LEFT"), 
	
				/**
	
	     * Axis positive direction is left in display.
	     * [GeoAPI]
	 * @generated 
	 */
	DISPLAY_RIGHT(10, "DISPLAY_RIGHT", "DISPLAY_RIGHT"), 
	
				/**
	
	     * Axis positive direction is towards top of approximately vertical display surface.
	     * [GeoAPI]
	 * @generated 
	 */
	DISPLAY_UP(11, "DISPLAY_UP", "DISPLAY_UP"), 
	
				/**
	
	     * Axis positive direction is towards bottom of approximately vertical display surface.
	     * [GeoAPI]
	 * @generated 
	 */
	DISPLAY_DOWN(12, "DISPLAY_DOWN", "DISPLAY_DOWN")
	
			;
	
	
	
	
	
	private int 	mValue 	= -1;
	private String 	mName	= null;
	private String 	mLabel 	= null;
	
	public String getName() 	{ return mName; }
	public String getLabel() 	{ return mLabel; }
	public int getValue() 		{ return mValue; }
	
	
	private static final AxisDirection[] VALUE_TYPES = new AxisDirection[]{
		NORTH,
		EAST,
		SOUTH,
		WEST,
		UP,
		DOWN,
		GEOCENTRIC_X,
		GEOCENTRIC_Y,
		GEOCENTRIC_Z,
		DISPLAY_LEFT,
		DISPLAY_RIGHT,
		DISPLAY_UP,
		DISPLAY_DOWN,
	};
			
			
	private AxisDirection(int value, String name, String label) {
		mValue = value;
		mName = name;
		if (label == null || label.isEmpty())
			mLabel = mName;
		else
			mLabel = label;	
	}
	
	public static AxisDirection get(String name){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mName.equals(name))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AxisDirection getByLabel(String label){
		for (int i = 0; i < VALUE_TYPES.length; ++i){
			if (VALUE_TYPES[i].mLabel.equals(label))
				return VALUE_TYPES[i];
		}
		return null;
	}
	
	public static AxisDirection get(int value){
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
