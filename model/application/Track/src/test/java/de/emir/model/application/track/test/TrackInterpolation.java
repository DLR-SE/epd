package de.emir.model.application.track.test;

import java.nio.charset.CoderResult;

import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.application.track.impl.TrackImpl;
import de.emir.model.application.track.impl.TrackPointImpl;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.TimeImpl;

public class TrackInterpolation {

	
	public static void main(String[] args) {
		
		Track track = new TrackImpl();
				
		TrackPoint tp = new TrackPointImpl();
		tp.setTime(new TimeImpl(10, TimeUnit.SECOND));
		tp.setCoordinate(new CoordinateImpl(10, 10, CRSUtils.WGS84_2D));
		tp.setCourse(new AngleImpl());
		tp.setSpeed(new SpeedImpl(1, SpeedUnit.METER_PER_SECOND));
		track.getElements().add(tp);
		
		tp = new TrackPointImpl();
		tp.setTime(new TimeImpl(20, TimeUnit.SECOND));
		tp.setCoordinate(new CoordinateImpl(11, 10, CRSUtils.WGS84_2D));
		tp.setCourse(new AngleImpl());
		tp.setSpeed(new SpeedImpl(18, SpeedUnit.METER_PER_SECOND));
		track.getElements().add(tp);
		
		TrackPoint tp1 = track.getPositionAt(new TimeImpl());
		
		
		tp1 = track.getPositionAt(new TimeImpl(15, TimeUnit.SECOND));
		
		
	}
}
