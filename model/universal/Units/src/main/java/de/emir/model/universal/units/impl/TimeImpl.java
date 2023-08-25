package de.emir.model.universal.units.impl;

import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @generated
 */
@UMLImplementation(classifier = Time.class)
public class TimeImpl extends MeasureImpl implements Time {

	
	public static Time now(){
		return new TimeImpl(System.currentTimeMillis(), TimeUnit.MILLISECOND);
	}
	
	
	/**
	 * @generated not
	 */
	private TimeUnit mUnit = TimeUnit.MILLISECOND;

	
//	private static int counter = 0;
	/**
	 *	Default constructor
	 *	@generated not
	 */
	public TimeImpl(){
		super();
//		counter++;
//		if (counter % 10000 == 0) System.out.println("Time Instances: " + counter);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TimeImpl(final Time _copy) {
		super(_copy);
		mUnit = _copy.getUnit();
//		counter++;
//		if (counter % 10000 == 0) System.out.println("Time Instances: " + counter);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TimeImpl(double _value, TimeUnit _unit) {
		super(_value);
		mUnit = _unit; 
//		counter++;
//		if (counter % 10000 == 0) System.out.println("Time Instances: " + counter);
	}
	
	
	public TimeImpl(LocalTime tod) {
		this(tod.getHour() * 60 * 60 + tod.getMinute() * 60 + tod.getSecond(), TimeUnit.SECOND);
//		counter++;
//		if (counter % 10000 == 0) System.out.println("Time Instances: " + counter);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return UnitsPackage.Literals.Time;
	}

	/**
	 *	@generated 
	 */
	public void setUnit(TimeUnit _unit) {
		if (needNotification(UnitsPackage.Literals.Time_unit)){
			TimeUnit _oldValue = mUnit;
			mUnit = _unit;
			notify(_oldValue, mUnit, UnitsPackage.Literals.Time_unit, NotificationType.SET);
		}else{
			mUnit = _unit;
		}
	}

	/**
	 *	@generated 
	 */
	public TimeUnit getUnit() {
		return mUnit;
	}

	//////////////////////////////////////////////////////////////////
	// Operations //
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public double getAs(final TimeUnit targetUnit)
	{
		if (targetUnit == getUnit())
			return getValue();
		return mUnit.getFactorToSecond() * getValue() * targetUnit.getFactorFromSecond();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final double value, final TimeUnit unit)
	{
		setValue(value);
		setUnit(unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void set(final Time value)
	{
		set(value.getValue(), value.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean smaller(final Time other)
	{
		return getValue() < other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean greater(final Time other)
	{
		return getValue() > other.getAs(mUnit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean equals(final Time other, final double epsilon)
	{
		return Math.abs(getValue() - other.getAs(getUnit())) < epsilon;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Time)
			return equals((Time)obj, Double.MIN_NORMAL);
		return super.equals(obj);
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Time add(final Time other)
	{
		return new TimeImpl(getValue() + other.getAs(getUnit()), getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final Time other)
	{
		addLocal(other.getValue(), other.getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void addLocal(final double value, final TimeUnit unit)
	{
		set(getAs(unit) + value, unit);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Time sub(final Time other)
	{
		return new TimeImpl(getValue() - other.getAs(getUnit()), getUnit());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void subLocal(final Time other)
	{
		setValue(getValue() - other.getAs(getUnit()));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String readableString()
	{
		return "Time: " + getValue() + ": " + getUnit().toString();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getYear()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getYear();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getMonth()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getMonthValue();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getDay()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getDayOfMonth();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getHourOfDay()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getHour();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getMinuteOfHour()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getMinute();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getMillisecondsOfSecond()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getNano() / 1000000;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public int getSecondsOfMinute()
	{
		return getDateTime().atOffset(ZoneOffset.UTC).getSecond();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TimeImpl{" +
		" value = " + getValue() + 
		"}";
	}

	@Override
	public int compareTo(Time o) {
		return Double.compare(getValue(), o.getAs(mUnit));
	}
	
	@Override
	public Instant getDateTime() {
		return Instant.ofEpochMilli((long)getAs(TimeUnit.MILLISECOND));
	}
}
