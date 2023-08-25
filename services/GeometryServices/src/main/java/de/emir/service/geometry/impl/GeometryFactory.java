package de.emir.service.geometry.impl;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Geometry;
import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.IGeometryFactory;
import de.emir.service.geometry.IGeometryProvider;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IGeometryFactory.class)
public class GeometryFactory extends UObjectImpl implements IGeometryFactory , IGeometryProvider 
{
	
	
	/**
	 *	@generated 
	 */
	private List<IGeometryProvider> mProvider = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GeometryFactory(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GeometryFactory(final IGeometryFactory _copy) {
		mProvider = _copy.getProvider();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GeometryFactory(List<IGeometryProvider> _provider) {
		mProvider = _provider; 
	}
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.GeometryFactory;
	}
	
	/**
	 *	@generated 
	 */
	@Override
	public List<IGeometryProvider> getProvider() {
		if (mProvider == null) {
			mProvider = new UContainmentList<IGeometryProvider>(this, GeometryPackage.theInstance.getGeometryFactory_provider()); 
		}
		return mProvider;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Geometry createGeometry(final Object native_geom, final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem dstCRS)
	{
		if (native_geom == null)
			return null;
		for (IGeometryProvider p : getProvider()){
			Geometry res = p.createGeometry(native_geom, sourceCRS, dstCRS);
			if (res != null){
				if (res.getCRS() != dstCRS){
					res.applyCRS(dstCRS);
					res.recursiveSetCRS(dstCRS);
				}
				return res;	
			}
		}
		return null;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GeometryFactory{" +
		"}";
	}
}
