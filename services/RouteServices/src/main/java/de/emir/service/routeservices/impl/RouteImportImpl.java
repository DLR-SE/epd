package de.emir.service.routeservices.impl;

import java.util.Collection;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.service.routeservices.IRouteImport;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

/**
 * Imports a route from an external route definition
 * 
 * @generated
 */
@UMLImplementation(classifier = IRouteImport.class)
abstract public class RouteImportImpl extends UObjectImpl implements IRouteImport {

	/**
	 *	Default constructor
	 *	@generated
	 */
	public RouteImportImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RouteImportImpl(final IRouteImport _copy) {
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.RouteImport;
	}

	//////////////////////////////////////////////////////////////////
	// Operations //
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public abstract Route importRoute(final Object definition, final CoordinateReferenceSystem crs);

	/**
	 * Convinience Method that iterates over all known implementations of
	 * RouteImport and trys to import the route
	 * 
	 * @param rdef
	 * @param crs
	 * @return
	 */
	public static Route getRoute(String rdef, CoordinateReferenceSystem crs) {
		Collection<UClassifier> importer = UCoreMetaRepository
				.getClassesInheritFrom(RouteservicesPackage.Literals.RouteImport, true);
		for (UClassifier imp : importer) {
			try {
				if (imp instanceof UClass && ((UClass) imp).getAbstract() == false) {
					IRouteImport ri = (IRouteImport) ((UClass) imp).createNewInstance();
					if (ri != null) {
						Route r = ri.importRoute(rdef, crs);
						if (r != null)
							return r;
					}
				}
			} catch (Exception e) {
				// an exception is not nessesarily a bad thing, if there are
				// more than just one importer the possibility to first try the
				// wrong is != 0
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RouteImportImpl{" +
		"}";
	}
}
