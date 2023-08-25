package de.emir.service.routeservices.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import de.emir.service.routeservices.RouteservicesPackage;
import java.util.Collection;

import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.io.impl.OutputStreamImpl;
import de.emir.service.routeservices.IRouteExport;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;


/**

 * Exports a route from internal representatin (IEC61174) to an external format
 * @generated 
 */
@UMLImplementation(classifier = IRouteExport.class)
abstract public class RouteExportImpl extends UObjectImpl implements IRouteExport  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RouteExportImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RouteExportImpl(final IRouteExport _copy) {
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return RouteservicesPackage.Literals.RouteExport;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RouteExportImpl{" +
		"}";
	}
	
	public boolean exportToFile(Route route, File file) throws IOException{
		OutputStreamImpl ds = new OutputStreamImpl();
		if (exportRoute(route, ds)){
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(ds.getByteBuffer().array());
			fos.close();
			return true;
		}
		return false;
	}
	public String exportToString(Route route){
		OutputStreamImpl ds = new OutputStreamImpl();
		if (exportRoute(route, ds)){
			return new String(ds.getByteBuffer().array());
		}
		return null;
	}
	
	
	
	
	
	
	/**
     * Convinience Method that iterates over all known implementations of
     * RouteExport and trys to export the route based on the fileextension
     *
     * @param route
     * @param file
     * @return success
     */
    public static boolean exportRoute(Route route, File file) {
        Collection<UClassifier> exporter = UCoreMetaRepository.getClassesInheritFrom(RouteservicesPackage.Literals.RouteExport, true);
        for (UClassifier imp : exporter) {
            try {
                if (imp instanceof UClass && ((UClass) imp).getAbstract() == false) {
                    IRouteExport ri = (IRouteExport) ((UClass) imp).createNewInstance();
                    if (ri.getFilter().accept(file) == false) {
                        continue;
                    }

                    if (ri != null) {
                        OutputStreamImpl ds = new OutputStreamImpl();
                        if (ri.exportRoute(route, ds)) {
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write(ds.getByteBuffer().array());
                            fos.close();
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                // an exception is not nessesarily a bad thing, if there are
                // more than just one importer the possibility to first try the
                // wrong is != 0
//                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
