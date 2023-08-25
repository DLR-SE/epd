package de.emir.model.universal.math.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.math.BorderBehavior;
import de.emir.model.universal.math.Function1;
import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.SampleFunction1;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;


/**
 *	@generated 
 */
@UMLImplementation(classifier = SampleFunction1.class)
public class SampleFunction1Impl extends UObjectImpl implements SampleFunction1 , Function1 
{
	
	
	/**
	 *	@generated not
	 */
	private BorderBehavior mBorderBehavior = BorderBehavior.LINEAR_EXTRAPOLATE;
	/**
	 *	@generated 
	 */
	private List<Vector2D> mSamples = null;
	/**
	 optional human readable description of the function" 
	 * @generated 
	 */
	private String mLabel = "";
	/**
	 optional human readable description of the domain for example "Time [s]" 
	 * @generated 
	 */
	private String mDomainLabel = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SampleFunction1Impl(){
		super();
		//set the default values and assign them to this instance 
		setBorderBehavior(mBorderBehavior);
	}



	/**
	 optional human readable description of the range for example "Temperature [degrees]" 
	 * @generated not
	 */
	private String mRangeLabel = "";

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SampleFunction1Impl(final SampleFunction1 _copy) {
		mLabel = _copy.getLabel();
		mDomainLabel = _copy.getDomainLabel();
		mRangeLabel = _copy.getRangeLabel();
		mBorderBehavior = _copy.getBorderBehavior();
		mSamples = _copy.getSamples();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SampleFunction1Impl(String _label, String _domainLabel, String _rangeLabel, BorderBehavior _borderBehavior, List<Vector2D> _samples) {
		mLabel = _label;
		mDomainLabel = _domainLabel;
		mRangeLabel = _rangeLabel;
		mBorderBehavior = _borderBehavior; 
		mSamples = _samples; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return MathPackage.Literals.SampleFunction1;
	}

	/**
	 optional human readable description of the domain for example "Time [s]" 
	 * @generated 
	 */
	public String getDomainLabel() {
		return mDomainLabel;
	}

	/**
	 optional human readable description of the function" 
	 * @generated 
	 */
	public void setLabel(String _label) {
		if (needNotification(MathPackage.Literals.SampleFunction1_label)){
			String _oldValue = mLabel;
			mLabel = _label;
			notify(_oldValue, mLabel, MathPackage.Literals.SampleFunction1_label, NotificationType.SET);
		}else{
			mLabel = _label;
		}
	}

	/**
	 optional human readable description of the function" 
	 * @generated 
	 */
	public String getLabel() {
		return mLabel;
	}

	/**
	 optional human readable description of the domain for example "Time [s]" 
	 * @generated 
	 */
	public void setDomainLabel(String _domainLabel) {
		if (needNotification(MathPackage.Literals.SampleFunction1_domainLabel)){
			String _oldValue = mDomainLabel;
			mDomainLabel = _domainLabel;
			notify(_oldValue, mDomainLabel, MathPackage.Literals.SampleFunction1_domainLabel, NotificationType.SET);
		}else{
			mDomainLabel = _domainLabel;
		}
	}
	
	/**
	 *	@generated 
	 */
	public void setBorderBehavior(BorderBehavior _borderBehavior) {
		if (needNotification(MathPackage.Literals.SampleFunction1_borderBehavior)){
			BorderBehavior _oldValue = mBorderBehavior;
			mBorderBehavior = _borderBehavior;
			notify(_oldValue, mBorderBehavior, MathPackage.Literals.SampleFunction1_borderBehavior, NotificationType.SET);
		}else{
			mBorderBehavior = _borderBehavior;
		}
	}

	/**
	 *	@generated 
	 */
	public BorderBehavior getBorderBehavior() {
		return mBorderBehavior;
	}

	/**
	 optional human readable description of the range for example "Temperature [degrees]" 
	 * @generated 
	 */
	public String getRangeLabel() {
		return mRangeLabel;
	}

	/**
	 optional human readable description of the range for example "Temperature [degrees]" 
	 * @generated 
	 */
	public void setRangeLabel(String _rangeLabel) {
		if (needNotification(MathPackage.Literals.SampleFunction1_rangeLabel)){
			String _oldValue = mRangeLabel;
			mRangeLabel = _rangeLabel;
			notify(_oldValue, mRangeLabel, MathPackage.Literals.SampleFunction1_rangeLabel, NotificationType.SET);
		}else{
			mRangeLabel = _rangeLabel;
		}
	}
	/**
	 *	@generated 
	 */
	public List<Vector2D> getSamples() {
		if (mSamples == null) {
			mSamples = new UContainmentList<Vector2D>(this, MathPackage.theInstance.getSampleFunction1_samples()); 
		}
		return mSamples;
	}


	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	private ITreeValueChangeListener mListener = null; //unfortunatly we have to use a tree listener, since the vector instances may also change
	private boolean mSorting = false;
	class MyListener implements ITreeValueChangeListener {
		@Override
		public void onValueChange(Notification<Object> notification) {
			//we need to sort the map
			if (!mSorting)
				doSort();
		}
	}
	
	private double			mMinX = Double.NaN;
	private double			mMaxX = Double.NaN;
	
	private void doSort() {
		mSorting = true;
		
		Collections.sort(getSamples(), new Comparator<Vector2D>() {
			@Override
			public int compare(Vector2D o1, Vector2D o2) {
				return Double.compare(o1.getX(), o2.getX());
			}
		});
		if (getSamples().size() >= 2) {
			mMinX = getSamples().get(0).getX();
			mMaxX = getSamples().get(getSamples().size()-1).getX();
		}
		mSorting = false;
	}
	private void initialize() {
		doSort();
		if (mListener == null)
			registerTreeListener(mListener = new MyListener());
	}
	
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public double getValue(final double x)
	{
		if (getSamples().size() < 2)
			return Double.NaN;
		
		if (mListener == null) {
			initialize();
		}
		//do we have to do a border behaviour
		if (x < mMinX || x > mMaxX) {
			return borderBehaviour(x);
		}
		
		//find the index nearest to the x value
		int[] indices = getIndex(x);
		if (indices[0] == indices[1])
			return getSamples().get(indices[0]).getY();
		
		return interpolate(indices, x);
		
	}



	private double interpolate(int[] indices, double x) {
		Vector2D s1 = getSamples().get(indices[0]);
		Vector2D s2 = getSamples().get(indices[1]);
		double y1 = s1.getY(), x1 = s1.getX(), x2 = s2.getX(), y2 = s2.getY();
		return y1 + (y2-y1) * (x - x1) / (x2-x1);
	}

	private int[] getIndex(double key) {
		//perform a binary search 
		List<Vector2D> list = getSamples();
		int lo = 0;
        int hi = list.size() - 1;
        int mid = 0;  
        while (lo <= hi) {
        	mid = lo + (hi - lo) / 2;
            double x = list.get(mid).getX();
            if (x == key) return new int[] {mid, mid};
            if 		(key < x) hi = mid -1;
            else if (key > x) lo = mid + 1;
            
        }
		return new int[] {hi, lo};
	}

	private double borderBehaviour(double x) {
		switch (getBorderBehavior()) {
		case EXCEPTION:
			throw new UnsupportedOperationException("Value out of range");
		case MIN_MAX_VALUE:
			if (x < mMinX) return getSamples().get(0).getY(); 
			return getSamples().get(getSamples().size()-1).getY();
		case NaN:
			return Double.NaN;
		default:
			if (x < mMinX)
				return interpolate(new int[] {1, 0}, x);
			return interpolate(new int[] {getSamples().size()-2,  getSamples().size()-1}, x);
		}
	}

	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Vector2D addSample(final double x, final double y)
	{
		//@note this could be improved in terms of speed, but its not called during runtime?!
		for (Vector2D v : getSamples()) {
			if (Math.abs(v.getX()-x) < 0.0000001) {
				v.setY(y);
				return v;
			}
		}
		Vector2D newSample = new Vector2DImpl(x, y);
		getSamples().add(newSample);
		
		return newSample;
	}
	
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SampleFunction1Impl{" +
		" label = " + getLabel() + 
		" domainLabel = " + getDomainLabel() + 
		" rangeLabel = " + getRangeLabel() + 
		"}";
	}
}
