package de.emir.model.universal.units;

/**
 * Utility class to create LaTeX symbols for different units
 * @author sschweigert
 *
 */
public class UnitSymbolUtil {
	
	public static String findSymbol(Object value) {
		if (value instanceof AccelerationUnit) return getSymbol((AccelerationUnit)value);
		if (value instanceof AngleUnit) return getSymbol((AngleUnit)value);
		if (value instanceof AngularAccelerationUnit) return getSymbol((AngularAccelerationUnit)value);
		if (value instanceof AngularSpeedUnit) return getSymbol((AngularSpeedUnit)value);
		if (value instanceof DistanceUnit) return getSymbol((DistanceUnit)value);
		if (value instanceof MassUnit) return getSymbol((MassUnit)value);
		if (value instanceof SpeedUnit) return getSymbol((SpeedUnit)value);
		if (value instanceof TemperatureUnit) return getSymbol((TemperatureUnit)value);
		if (value instanceof TimeUnit) return getSymbol((TimeUnit)value);
		if (value instanceof VolumeUnit) return getSymbol((VolumeUnit)value);
		
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//														Complex Units																//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getSymbol(SpeedUnit unit){
		String tu = getSymbol(unit.getTimeUnit());
		String du = getSymbol(unit.getDistanceUnit());
		return "\\frac{"+du+"}{"+tu+"}";
	}
	public static String getSymbol(AngularSpeedUnit unit){
		if (unit.getAngleUnit() == AngleUnit.REVOLUTION && unit.getTimeUnit() == TimeUnit.MINUTE)
			return "rpm";
		String tu = getSymbol(unit.getTimeUnit());
		String au = getSymbol(unit.getAngleUnit());
		return "\\frac{"+au+"}{"+tu+"}";
	}
	
	public static String getSymbol(AccelerationUnit unit){
		DistanceUnit s_du = unit.getSpeedUnit().getDistanceUnit();
		TimeUnit s_tu = unit.getSpeedUnit().getTimeUnit();
		TimeUnit tu = unit.getTimeUnit();
		if (s_tu == tu){
			return "\\frac{"+getSymbol(s_du) + "}{" + getSymbol(tu)+"^{2}}";
		}else
			return "\\frac{"+getSymbol(s_du) + "}{" + getSymbol(tu)+getSymbol(s_tu) +"}";
	}
	public static String getSymbol(AngularAccelerationUnit unit){
		AngleUnit s_au = unit.getSpeedUnit().getAngleUnit();
		TimeUnit s_tu = unit.getSpeedUnit().getTimeUnit();
		TimeUnit tu = unit.getTimeUnit();
		if (s_tu == tu){
			return "\\frac{"+getSymbol(s_au) + "}{" + getSymbol(tu)+"^{2}}";
		}else
			return "\\frac{"+getSymbol(s_au) + "}{" + getSymbol(tu)+getSymbol(s_tu) +"}";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//														TRIVIAL UNITS																//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getSymbol(AngleUnit unit){
		switch(unit){
		case DEGREE: return "deg";
		case RADIAN: return "rad";
		case REVOLUTION: return "rounds";
		}
		return null;
	}
	public static String getSymbol(MassUnit unit){
		switch(unit){
		case GRAM: return "g";
		case KILOGRAM: return "kg";
		case TONNE: return "t";
		}
		return null;
	}
	public static String getSymbol(TimeUnit unit){
		switch(unit){
		case DAY: return "d";
		case HOUR: return "h";
		case MICROSECOND: return "\\mu s";
		case MILLISECOND: return "ms";
		case MINUTE: return "min";
		case NANOSECOND: return "ns";
		case SECOND: return "s";
		}
		return null;
	}
	
	public static String getSymbol(TemperatureUnit unit){
		switch(unit){
		case CELSIUS: return "\\circ C";
		case FARENHEIT : return "\\circ F";
		case KELVIN : return "K";
		}
		return null;
	}
	/**
	 * Create LaTeX strings for distance units
	 * \note even if this unit is trivial (only asccii symbols) it may be usefull to get it rendered to got a coherent picture for all units 
	 * @param unit
	 * @return
	 */
	public static String getSymbol(DistanceUnit unit){
		switch(unit){
		case CABLE:
			return "cable";
		case CENTIMETER:
			return "cm";
		case FOOT: 
			return "ft";
		case KILOMETER:
			return "km";
		case METER:
			return "m";
		case MILES: 
			return "miles";
		case MILLIMETER: 
			return "mm";
		case NAUTICAL_MILES:
			return "NM";
		case YARD:
			return "yard";
		}
		return null;
	}
	
	public static String getSymbol(VolumeUnit unit) {
		switch(unit) {
		case BARREL: return "bbl";
		case CUBIC_CENTIMETER : return "cm^{3}";
		case CUBIC_DECIMETER : return "dm^{3}";
		case CUBIC_FOOT : return "cft";
		case CUBIC_INCH : return "cuin";
		case CUBIC_METER : return "m^{3}";
		case GALLON : return "Imp. gal";
		case LITRE : return "L";
		case PINT : return "Imp. pt";
		}
		return null;
	}

	
}
