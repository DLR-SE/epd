package de.emir.model.application.track.impl;

import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackPackage;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;

import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Track.class)
public class TrackImpl extends UObjectImpl implements Track  
{
	
	
	/**
	 ordered list of elements  
	 * @generated 
	 */
	private List<TrackPoint> mElements = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackImpl(final Track _copy) {
		mElements = _copy.getElements();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackImpl(List<TrackPoint> _elements) {
		mElements = _elements; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TrackPackage.Literals.Track;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 ordered list of elements  
	 * @generated 
	 */
	public List<TrackPoint> getElements() {
		if (mElements == null) {
			mElements = ListUtils.<TrackPoint>asList(this, TrackPackage.theInstance.getTrack_elements()); 
		}
		return mElements;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Time first()
	{
		if (getElements().isEmpty())
			return null;
		return new TimeImpl(getElements().get(0).getTime());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Time last()
	{
		if (getElements().isEmpty() || getElements().get(getElements().size() -1) == null || getElements().get(getElements().size() -1).getTime() == null)
			return null;
		return new TimeImpl(getElements().get(getElements().size()-1).getTime());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public TrackPoint getPositionAt(final Time p)
	{
		//TODO: Check if calculations are correct
		Time first = first();
		if (p.equals(first))
			return new TrackPointImpl(getElements().get(0));
		Time last = last();
		if (p.equals(last))
			return new TrackPointImpl(getElements().get(getElements().size()-1));
		if (p.smaller(first()) || p.greater(last())) {
			//lies in the past, before the first measurement
			TrackPoint tp = getElements().get(0);
			Time deltaTime = tp.getTime().sub(p);
			Angle neg_az = tp.getCourse() != null ? tp.getCourse().add(180, AngleUnit.DEGREE) : null;
			if (neg_az == null) neg_az = tp.getHeading() != null ? tp.getHeading().add(180, AngleUnit.DEGREE) : null;
			if (neg_az == null) 
				return null; //can not estimate, since we have no course 
			if (deltaTime.getValue() < 0)
				neg_az.addLocal(180, AngleUnit.DEGREE);
			Speed s = tp.getSpeed();
			if (s == null) 
				return null; //also we can not estimate, since we have not speed
			
			Distance distance = s.integrate(deltaTime);
			
			Coordinate start = tp.getCoordinate().getTarget(distance, neg_az);
			TrackPoint out = new TrackPointImpl(tp);
			out.setTime(new TimeImpl(p));
			out.setCoordinate(start);
			return out;
		}
		for (int i = 1; i < getElements().size(); i++) {
			TrackPoint tp1 = getElements().get(i-1);
			if (tp1.getTime().greater(p))
				continue;
			TrackPoint tp2 = getElements().get(i);
			if (tp2.getTime().greater(p)) {
				Coordinate c1 = tp1.getCoordinate();
				Coordinate c2 = tp2.getCoordinate();
				Angle a = c1.getAzimuth(c2);
				
				if (a == null) {
                    return null;
                }
				
				Distance d = c1.getDistance(c2);
				double dt = p.sub(tp1.getTime()).getAs(TimeUnit.SECOND);
				double st = tp2.getTime().sub(tp1.getTime()).getAs(TimeUnit.SECOND);
				double f = dt / st;
				
				Coordinate cp = c1.getTarget(new DistanceImpl(d.multiply(f).getAs(DistanceUnit.METER), DistanceUnit.METER), a);
				
				TrackPointImpl out = new TrackPointImpl();
				out.setCoordinate(cp);
//				if (tp1.getCourse() != null && tp2.getCourse() != null)
//					out.setCourse(tp1.getCourse().add(tp2.getCourse().sub(tp1.getCourse()).getAs(AngleUnit.DEGREE) * f, AngleUnit.DEGREE));
//				if (tp1.getHeading() != null && tp2.getHeading() != null){
//					out.setHeading(tp1.getHeading().add(tp2.getHeading().sub(tp1.getCourse()).getAs(AngleUnit.DEGREE) * f, AngleUnit.DEGREE));
//				}
				out.setTime(new TimeImpl(p));
				if (tp1.getCourse() != null) {
				    out.setCourse(new AngleImpl(tp1.getCourse()));
                }else {
                    out.setCourse(new AngleImpl(a));
                }
				
				if(tp1.getHeading() != null) {
				    out.setHeading(new AngleImpl(tp1.getHeading())); 
				}else {
                    out.setHeading(new AngleImpl(a));
                }
				
				if (tp1.getSpeed() != null) {
				    out.setSpeed(new SpeedImpl(tp1.getSpeed()));
                }else {
                  Time diff = tp2.getTime().sub(tp2.getTime());
                  Distance distance = tp1.getCoordinate().getDistance(tp2.getCoordinate());
                  
                  out.setSpeed(new SpeedImpl(distance.getAs(DistanceUnit.METER) / diff.getAs(TimeUnit.SECOND), SpeedUnit.METER_PER_SECOND));
                }
				
//				if (tp1.getSpeed() != null && tp2.getSpeed() != null) {
//                    double s1_ms = tp1.getSpeed().getAs(SpeedUnit.METER_PER_SECOND);
//                    double s2_ms = tp2.getSpeed().getAs(SpeedUnit.METER_PER_SECOND);
//                    double ds_ms = s2_ms - s1_ms; 
//                    double tmp = ds_ms * f;
//                    out.setSpeed(new SpeedImpl(tmp, SpeedUnit.METER_PER_SECOND));
//                }


				return out;
			}
		}	
		return null;		
	}


	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackImpl{" +
		"}";
	}
}
