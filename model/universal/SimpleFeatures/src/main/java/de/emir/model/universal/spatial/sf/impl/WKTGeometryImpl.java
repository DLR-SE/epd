package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.WKTGeometry;
import de.emir.model.universal.spatial.sf.delegate.IWKTGeometryDelegationInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 The WKTGeometry is a helper geometry, where the geometry is specified using a WKT (Well known text) String
 * the internal geometry (getNativeGeometry()) is determinated at runtime
 * @generated 
 */
@UMLImplementation(classifier = WKTGeometry.class)
public class WKTGeometryImpl extends GeometryImpl implements WKTGeometry  
{
	
	
	/**
	 *	@generated 
	 */
	private String mWkt = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WKTGeometryImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WKTGeometryImpl(final WKTGeometry _copy) {
		super(_copy);
		mWkt = _copy.getWkt();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WKTGeometryImpl(String _wkt) {
		super();
		mWkt = _wkt;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.WKTGeometry;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setWkt(String _wkt) {
		if (needNotification(SfPackage.Literals.WKTGeometry_wkt)){
			String _oldValue = mWkt;
			mWkt = _wkt;
			notify(_oldValue, mWkt, SfPackage.Literals.WKTGeometry_wkt, NotificationType.SET);
		}else{
			mWkt = _wkt;
		}
	}
	/**
	 *	@generated 
	 */
	public String getWkt() {
		return mWkt;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public CoordinateSequence getCoordinates()
	{
		IWKTGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: WKTGeometry");
		return delegate.getCoordinates(this);
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WKTGeometryImpl{" +
		" wkt = " + getWkt() + 
		"}";
	}
}
