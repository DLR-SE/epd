package de.emir.model.universal.spatial.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.NativeCRSImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.NotificationImpl;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;

import java.util.ArrayList;
import java.util.List;


/**

 * A coordinate sequence serves as container for the coordinates, forming a geometry
 * @generated 
 */
@UMLImplementation(classifier = CoordinateSequence.class)
public class CoordinateSequenceImpl extends UObjectImpl implements CoordinateSequence  
{
	
	
	/**
	 *	@generated not
	 */
	private CoordinateReferenceSystem mCrs = null;
	/**
	 *	@generated not 
	 */
	private List<Double> mXCoordinates = null;
	/**
	 *	@generated not 
	 */
	private List<Double> mYCoordinates = null;
	/**
	 *	@generated not
	 */
	private List<Double> mZCoordinates = null;
	
	
	private boolean 			mDirty = true;
	private Envelope			mEnvelope = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CoordinateSequenceImpl(){
		super();
		//set the default values and assign them to this instance 
		setCrs(mCrs);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CoordinateSequenceImpl(final CoordinateSequence _copy) {
		getXCoordinates().addAll(_copy.getXCoordinates());
		getYCoordinates().addAll(_copy.getYCoordinates());
		getZCoordinates().addAll(_copy.getZCoordinates());
		mCrs = _copy.getCrs();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CoordinateSequenceImpl(CoordinateReferenceSystem _crs, List<Double> _xCoordinates, List<Double> _yCoordinates, List<Double> _zCoordinates) {
		getXCoordinates().addAll(_xCoordinates);
		getYCoordinates().addAll(_yCoordinates);
		getZCoordinates().addAll(_zCoordinates);
		mCrs = _crs; 
	}
	
	public CoordinateSequenceImpl(List<Coordinate> coordinates) {
		this();
		for (Coordinate c : coordinates)
			addCoordinate(c);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.CoordinateSequence;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated not
	 */
	public void setCrs(CoordinateReferenceSystem _crs) {
		Notification<CoordinateReferenceSystem> notification = basicSet(mCrs, _crs, SpatialPackage.Literals.CoordinateSequence_crs);
		mCrs = _crs;
		mDirty = true;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public CoordinateReferenceSystem getCrs() {
		return mCrs;
	}
	
	class CSList extends ArrayList<Double> {
		private UStructuralFeature mFeature = null;
		public CSList(UStructuralFeature f) { mFeature = f; }
		@Override
		public Double set(int index, Double element) {
			Double ov = super.get(index);
			try {
				return super.set(index, element);
			}finally {
				mDirty = true;
				if (needNotification(mFeature))
					dispatchNotification(new NotificationImpl<>(ov, element, CoordinateSequenceImpl.this, mFeature, NotificationType.SET));
			}
		}
		@Override
		public Double remove(int index) {			
			Double ov = super.get(index);
			try {
				return super.remove(index);
			}finally {
				mDirty = true;	
				if (needNotification(mFeature))
					dispatchNotification(new NotificationImpl<>(ov, null, CoordinateSequenceImpl.this, mFeature, NotificationType.REMOVE));
			}
		}
		@Override
		public boolean add(Double e) {
			try {
				return super.add(e);
			}finally {
				mDirty = true;	
				if (needNotification(mFeature))
					dispatchNotification(new NotificationImpl<>(null, e, CoordinateSequenceImpl.this, mFeature, NotificationType.ADD));				
			}
		}
		@Override
		public void add(int index, Double e) {
			Double ov = super.get(index);
			try {
				super.add(index, e);
			}finally {
				mDirty = true;	
				if (needNotification(mFeature))
					dispatchNotification(new NotificationImpl<>(ov, e, CoordinateSequenceImpl.this, mFeature, NotificationType.ADD));
			}
		}
	}
	
	/**
	 *	@generated not
	 */
	public List<Double> getXCoordinates() {
		if (mXCoordinates == null) {
			mXCoordinates = new CSList(SpatialPackage.Literals.CoordinateSequence_xCoordinates); 
		}
		return mXCoordinates;
	}
	/**
	 *	@generated not
	 */
	public List<Double> getYCoordinates() {
		if (mYCoordinates == null) {
			mYCoordinates = new CSList(SpatialPackage.Literals.CoordinateSequence_yCoordinates); 
		}
		return mYCoordinates;
	}
	/**
	 *	@generated not
	 */
	public List<Double> getZCoordinates() {
		if (mZCoordinates == null) {
			mZCoordinates = new CSList(SpatialPackage.Literals.CoordinateSequence_zCoordinates); 
		}
		return mZCoordinates;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	class LinkedCoordinate extends CoordinateImpl {
		int mIdx = -1;
		public LinkedCoordinate(int idx) {
			mIdx = idx;
		}
		@Override
		public void setX(double _x) {
			super.setX(_x);
			getXCoordinates().set(mIdx, _x);
		}
		@Override
		public void setY(double _y) {
			super.setY(_y);
			getYCoordinates().set(mIdx, _y);
		}
		@Override
		public void setZ(double _z) {
			super.setZ(_z);
			if (getZCoordinates().isEmpty() == false)
				getZCoordinates().set(mIdx, _z);
		}
		@Override
		public double getX() {
			return getXCoordinates().get(mIdx);
		}
		@Override
		public double getY() {
			return getYCoordinates().get(mIdx);
		}
		@Override
		public double getZ() {
			if (getZCoordinates().isEmpty())
				return Double.NaN;
			return getZCoordinates().get(mIdx);
		}
		
		@Override
		public CoordinateReferenceSystem getCrs() {
			return CoordinateSequenceImpl.this.getCrs();
		}
	}
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Coordinate getCoordinate(final int idx)
	{
		return new LinkedCoordinate(idx);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setCoordinate(final int idx, final Coordinate value)
	{
		CoordinateReferenceSystem crs = getCrs(); 
		Coordinate thisCRS = crs != null ? value.get(crs) : value;//if no crs available, we just use x,y,z - note: this may leeds to errors or unexpected behaviour if different CRS coordinates will be added
		double x = thisCRS.getX();
		double y = thisCRS.getY();
		double z = thisCRS.getZ();
		getXCoordinates().set(idx, x);
		getYCoordinates().set(idx, y);
		int d = dimension();
		if (d == -1) { //first entry
			if (z == z) //not nan
				getZCoordinates().set(idx, z);
		}else if (d == 3)
			getZCoordinates().set(idx, z);
		mDirty = true;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void addCoordinate(final Coordinate value)
	{
		addCoordinate(numCoordinates(), value);
	}
	
	@Override
	public void addCoordinate(int idx, Coordinate value) {
		CoordinateReferenceSystem crs = getCrs(); 
		Coordinate thisCRS = crs != null ? value.get(crs) : value;//if no crs available, we just use x,y,z - note: this may leeds to errors or unexpected behaviour if different CRS coordinates will be added
		
		if (value == null  || thisCRS == null) {
			//throw new Exception();
			System.err.println("Could not add coordinate " + value);
			return;
		}
		
		double x = thisCRS.getX();
		double y = thisCRS.getY();
		double z = thisCRS.getZ();
		if (idx == getXCoordinates().size()) {

			int d = dimension();
			if (d == -1) { //first entry
				if (!Double.isNaN(z)) //not nan
					getZCoordinates().add(z);
			}else if (d == 3) {
				getZCoordinates().add(z);
			}
			getXCoordinates().add(x);
			getYCoordinates().add(y);
		}else {
			int d = dimension();
			if (d == -1) { //first entry
				if (z == z) //not nan
					getZCoordinates().add(idx, z);
			} else if (d == 3) {
				getZCoordinates().add(idx, z);
			}
			getXCoordinates().add(idx, x);
			getYCoordinates().add(idx, y);
		}
		mDirty = true;
	}

	@Override
	public void removeCoordinate(Coordinate coord) {
		int idx = getIndexOf(coord);
		if (idx >= 0)
			removeCoordinate(idx);
	}

	@Override
	public int getIndexOf(Coordinate coord) {
		double xx = coord.getX(), yy = coord.getY(), zz = coord.getZ();
		for (int i = 0; i < numCoordinates(); i++) {
			if (getXCoordinates().get(i) == xx && getYCoordinates().get(i) == yy) {
				if (dimension() == 3) {
					if (zz == getZCoordinates().get(i))
						return i;
				}else
					return i;
			}
		}
		return -1;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void removeCoordinate(final int idx)
	{
		int d = dimension();
		getXCoordinates().remove(idx);
		getYCoordinates().remove(idx);
		if (d == 3)
			getZCoordinates().remove(idx); //otherwise there is no value in zCoordinates
		mDirty = true;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope getEnvelope()
	{
		if (getXCoordinates().isEmpty())
			return null; //no coordinates, no envelope
		if (mEnvelope == null) { 
			mEnvelope = new EnvelopeImpl(getCoordinate(0));
			mDirty = true;
		}
		if (mDirty) {
			double mix, miy, miz;
			mix = miy = miz = Double.MAX_VALUE;
			double max, may, maz;
			max = may = maz = -Double.MAX_VALUE;
			if (dimension() == 2) {
				miz = maz = Double.NaN;
				for (int i = 0; i < numCoordinates(); i++) {
					double x = getXCoordinates().get(i);
					double y = getYCoordinates().get(i);
					max = Math.max(max, x); mix = Math.min(mix, x);
					may = Math.max(may, y); miy = Math.min(miy, y);
				}
			}else {
				for (int i = 0; i < numCoordinates(); i++) {
					double x = getXCoordinates().get(i);
					double y = getYCoordinates().get(i);
					double z = getZCoordinates().get(i);
					max = Math.max(max, x); mix = Math.min(mix, x);
					may = Math.max(may, y); miy = Math.min(miy, y);
					maz = Math.max(maz, z); miz = Math.min(miz, z);
				}
			}
			mEnvelope.setXYZ(mix, miy, miz, max, may, maz);
			mEnvelope.setCRS(getCrs());
			mDirty = false;
		}
		return mEnvelope;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CoordinateSequenceImpl{" +
		" xCoordinates = " + getXCoordinates() + 
		" yCoordinates = " + getYCoordinates() + 
		" zCoordinates = " + getZCoordinates() + 
		"}";
	}

	@Override
	public int numCoordinates() {
		return getXCoordinates().size();
	}

	@Override
	public int dimension() {
		if (mCrs != null) {
			return mCrs.dimension();
		}
		if (getXCoordinates().isEmpty())
			return -1;
		if (getZCoordinates().isEmpty())
			return 2;
		double z0 = getZCoordinates().get(0);
		if (z0 != z0) //is NaN ?
			return 2;
		return 3;
	}

	
}
