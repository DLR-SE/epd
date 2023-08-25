package de.emir.rcp.other.navtool;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class FixData {
	GenericProperty<Time>		time;
	GenericProperty<Angle> 		ownCourse;
	GenericProperty<Speed>		ownSpeed;
	GenericProperty<Distance>	targetDistance;
	GenericProperty<Angle>		targetBearing;
	
	GenericProperty<Angle>		targetCourse;
	GenericProperty<Speed>		targetSpeed;
	GenericProperty<Time>		tcpa;
	GenericProperty<Distance>	dcpa;
	
	Coordinate					ownCoordinate;
	Coordinate					targetCoordiante;
	
	public FixData() {
		this(new TimeImpl(0, TimeUnit.MINUTE), new AngleImpl(0, AngleUnit.DEGREE), new SpeedImpl(0, SpeedUnit.KNOTS), new DistanceImpl(0, DistanceUnit.NAUTICAL_MILES), new AngleImpl(0, AngleUnit.DEGREE));
	}
	public FixData(FixData f) {
		this(new TimeImpl(f.time.get()), new AngleImpl(f.ownCourse.get()), new SpeedImpl(f.ownSpeed.get()), new DistanceImpl(f.targetDistance.get()), new AngleImpl(f.targetBearing.get()));
	}

	public FixData(Time _time, Angle _cog, Speed _sog, Distance _dist, Angle _bear) {
		time = new GenericProperty<>("Time", "Time of the measurement", true, _time);
		ownCourse = new GenericProperty<>("OwnCourse", "Own course at given time", true, _cog);
		ownSpeed = new GenericProperty<>("Own Speed", "Own Speed at given time", true, _sog);
		targetDistance = new GenericProperty<>("Target Distance", "measured distance of target vessel at given time", true, _dist);
		targetBearing = new GenericProperty<>("Target Bearing", "measured absolute target bearing at given time", true, _bear);
	}
}
