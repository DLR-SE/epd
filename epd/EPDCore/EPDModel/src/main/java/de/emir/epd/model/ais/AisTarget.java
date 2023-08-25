/**
 * 
 */
package de.emir.epd.model.ais;

import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackCharacteristic;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.application.track.impl.TrackCharacteristicImpl;
import de.emir.model.application.track.impl.TrackImpl;
import de.emir.model.application.track.impl.TrackPointImpl;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.slf4j.Logger;

import java.util.List;

/**
 * Use Vessel as data model for AIS targets. This contains static helper methods for vessels. 
 * @TODO clean up and remove NMEA0183 dependencies from EPDModel
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 */
public class AisTarget {
	/** Logger. */
	private static Logger LOG = ULog.getLogger(AisTarget.class);

	/**
	 * This updates the AIS targets underlying vessel. You should first find the AIS
	 * target in the TargetSet by the MMSI, then update the values and finally call
	 * updateTarget with the modified Vessel object.
	 * 
	 * @param updatedVessel
	 *            The updated vessel
	 * @see de.emir.epd.modelmanager.RouteSet.TargetSet#getById(String)
	 */
	public static Vessel updateVessel(Vessel updatedVessel) {
		if (updatedVessel == null) {
			return null;
		}
//		result.setPropertyValue(NMEAFieldIds.TARGET_TIMESTAMP, System.currentTimeMillis());
		final Double lat = updatedVessel.getPose().getCoordinate().getLatitude();
		final Double lon = updatedVessel.getPose().getCoordinate().getLongitude();
		if (lat == null || lon == null || Math.abs(lat) < 0.0001d || Math.abs(lon) < 0.0001d) {
			return updatedVessel;
		}

		TrackCharacteristic tc = updatedVessel.getFirstCharacteristic(TrackCharacteristic.class, true);
		if (tc == null) {
			tc = new TrackCharacteristicImpl();
			updatedVessel.getCharacteristics().add(tc);
		}

		Track tr = tc.getTrack();
		if (tr == null) {
			tr = new TrackImpl();
			tc.setTrack(tr);
		}

		List<TrackPoint> tps = tr.getElements();
		TrackPoint tp = new TrackPointImpl();
		Pose pose = updatedVessel.getPose();

		if (pose != null) {
			Coordinate coordinate = pose.getCoordinate();

			if (coordinate != null) {
				tp.setCoordinate(
						new CoordinateImpl(
								coordinate.getLatitude(),
								coordinate.getLongitude(),
								coordinate.getCrs()
						)
				);
			}

			Orientation orientation = pose.getOrientation();

			if (orientation != null) {
				tp.setHeading(orientation.toEuler().getZ());
			}

			tp.setCourse(PhysicalObjectUtils.getCOG(updatedVessel));
			tp.setSpeed(PhysicalObjectUtils.getSOG(updatedVessel));
		}

		tp.setTime(new TimeImpl((double) System.currentTimeMillis(), TimeUnit.MILLISECOND));
		tps.add(tp);

		return updatedVessel;
	}

	public static void setCoordinate(PhysicalObject obj, Coordinate coord) {
		if (obj.getPose() == null) {
			obj.setPose(new PoseImpl());
		}
		
		Coordinate coordinate = obj.getPose().getCoordinate();

		if(coordinate == null) {
			coordinate = new CoordinateImpl();
			obj.getPose().setCoordinate(coordinate);
		}
		
		coordinate.setLatLon(coord.getLatitude(), coord.getLongitude());
	}

	public static Coordinate getCoordinate(PhysicalObject obj) {
		if (obj.getPose() == null) {
			return null;
		}

		return obj.getPose().getCoordinate();
	}

	public static Long getTimestamp(PhysicalObject obj) {
		if (!(obj instanceof Vessel)) return null;
		Vessel v = (Vessel) obj;

		TrackCharacteristic tc = v.getFirstCharacteristic(TrackCharacteristic.class, true);
		if (tc == null) {
			return null;
		}

		Track tr = tc.getTrack();
		if (tr == null) {
			tr = new TrackImpl();
			tc.setTrack(tr);
		}

		if (tr.getElements() != null && tr.getElements().size() > 0) {
//			return (long) tr.getElements().get(tr.getElements().size() - 1).getTime().getAs(TimeUnit.MILLISECOND);
			return (long) tr.last().getAs(TimeUnit.MILLISECOND);
		}

		return null;
	}

	public static void setTimestamp(PhysicalObject obj, long ts) {
		TrackCharacteristic tc = obj.getFirstCharacteristic(TrackCharacteristic.class, true);
		if (tc == null) {
			tc = new TrackCharacteristicImpl();
			obj.getCharacteristics().add(tc);
		}

		Track tr = tc.getTrack();
		if (tr == null) {
			tr = new TrackImpl();
			tc.setTrack(tr);
		}

		TrackPoint tp = null;
		if (tr.getElements().isEmpty()) {
			tp = new TrackPointImpl(new TimeImpl((double) ts, TimeUnit.MILLISECOND), getCoordinate(obj),
					PhysicalObjectUtils.getHeading(obj), PhysicalObjectUtils.getCOG(obj),
					PhysicalObjectUtils.getSOG(obj), null);
			tr.getElements().add(tp);
		} else {
			tp = tr.getElements().get(tr.getElements().size() - 1);
			tp.setTime(new TimeImpl((double) ts, TimeUnit.MILLISECOND));
		}
	}

	public static Track getTrack(Vessel v) {
		TrackCharacteristic tc = v.getFirstCharacteristic(TrackCharacteristic.class, true);

		if (tc == null) {
			return null;
		}

		return tc.getTrack();
	}
	
	public static Route getVoyage(Vessel v) {
		VoyageCharacteristic vc = v.getFirstCharacteristic(VoyageCharacteristic.class, true);

		if (vc == null) {
			return null;
		}

		return vc.getActiveRoute();
	}
}
