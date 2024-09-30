package de.emir.service.routeservices.impl;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.service.routeservices.IRouteImport;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

import java.util.Arrays;
import java.util.Collection;

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
	 * Retrieves a {@link Route} by attempting to import it using all known implementations of
	 * {@link IRouteImport}. This method then iterates over all available route importers and uses the
	 * appropriate one based on the provided file extension.
	 *
	 * @param rdef the route definition as a string, which is used by the importer to create a {@link Route}
	 * @param crs the coordinate reference system to be used for the imported route
	 * @param fileExtension the file extension of the route definition to determine the appropriate importer
	 * @return a {@link Route} if the import was successful, or {@code null} if no suitable importer
	 *         was found or an error occurred during the import process
	 */
	public static Route getRoute(String rdef, CoordinateReferenceSystem crs, String fileExtension) {
		Collection<UClassifier> importer = UCoreMetaRepository
				.getClassesInheritFrom(RouteservicesPackage.Literals.RouteImport, true);
		for (UClassifier imp : importer) {
			try {
				if (imp instanceof UClass && ((UClass) imp).getAbstract() == false) {
					IRouteImport ri = (IRouteImport) ((UClass) imp).createNewInstance();
					String[] fileNameExtensions = ri.getFileExtension().getExtensions();
					if (Arrays.asList(fileNameExtensions).contains(fileExtension)) {
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
