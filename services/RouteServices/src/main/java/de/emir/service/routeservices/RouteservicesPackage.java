package de.emir.service.routeservices;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.UObject;

import de.emir.model.domain.maritime.IEC61174Model;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.universal.IOModel;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.io.IOutputStream;
import de.emir.model.universal.io.IoPackage;
import de.emir.service.routeservices.ICSVRouteFile;
import de.emir.service.routeservices.IGMLRouteImport;
import de.emir.service.routeservices.IRTZRouteExport;
import de.emir.service.routeservices.IRTZRouteImport;
import de.emir.service.routeservices.IRouteExport;
import de.emir.service.routeservices.IRouteImport;
import de.emir.service.routeservices.ISimpleRouteFile;
import de.emir.service.routeservices.ISimpleRouteFileExport;
import de.emir.service.routeservices.RouteservicesPackage;
import de.emir.service.routeservices.impl.CSVRouteFileImpl;
import de.emir.service.routeservices.impl.GMLRouteImportImpl;
import de.emir.service.routeservices.impl.RTZRouteExportImpl;
import de.emir.service.routeservices.impl.RTZRouteImportImpl;
import de.emir.service.routeservices.impl.RouteExportImpl;
import de.emir.service.routeservices.impl.RouteImportImpl;
import de.emir.service.routeservices.impl.SimpleRouteFileExportImpl;
import de.emir.service.routeservices.impl.SimpleRouteFileImpl;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class RouteservicesPackage  
{
	
	/**
	 * @generated
	 */
	public static RouteservicesPackage theInstance = new RouteservicesPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier RouteImport
		*/
		UClass RouteImport = RouteservicesPackage.theInstance.getRouteImport();
		/**
		* @generated
		* @return meta type for classifier RouteExport
		*/
		UClass RouteExport = RouteservicesPackage.theInstance.getRouteExport();
		/**
		* @generated
		* @return meta type for classifier GMLRouteImport
		*/
		UClass GMLRouteImport = RouteservicesPackage.theInstance.getGMLRouteImport();
		/**
		* @generated
		* @return meta type for classifier SimpleRouteFile
		*/
		UClass SimpleRouteFile = RouteservicesPackage.theInstance.getSimpleRouteFile();
		/**
		* @generated
		* @return meta type for classifier CSVRouteFile
		*/
		UClass CSVRouteFile = RouteservicesPackage.theInstance.getCSVRouteFile();
		/**
		* @generated
		* @return meta type for classifier RTZRouteImport
		*/
		UClass RTZRouteImport = RouteservicesPackage.theInstance.getRTZRouteImport();
		/**
		* @generated
		* @return meta type for classifier RTZRouteExport
		*/
		UClass RTZRouteExport = RouteservicesPackage.theInstance.getRTZRouteExport();
		/**
		* @generated
		* @return meta type for classifier SimpleRouteFileExport
		*/
		UClass SimpleRouteFileExport = RouteservicesPackage.theInstance.getSimpleRouteFileExport();
		
		/**
		 * @generated
		 * @return feature descriptor dezimalNotation in type SimpleRouteFile
		 */
		 UStructuralFeature SimpleRouteFile_dezimalNotation = RouteservicesPackage.theInstance.getSimpleRouteFile_dezimalNotation();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mRouteImport = null;
	/**
	* @generated
	*/
	private UClass mRouteExport = null;
	/**
	* @generated
	*/
	private UClass mGMLRouteImport = null;
	/**
	* @generated
	*/
	private UClass mSimpleRouteFile = null;
	/**
	* @generated
	*/
	private UClass mCSVRouteFile = null;
	/**
	* @generated
	*/
	private UClass mRTZRouteImport = null;
	/**
	* @generated
	*/
	private UClass mRTZRouteExport = null;
	/**
	* @generated
	*/
	private UClass mSimpleRouteFileExport = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	
	//Features for classifier SimpleRouteFile
	/**
	 * @generated
	 */
	private UStructuralFeature mSimpleRouteFile_dezimalNotation = null;
	
	
	
	
	
	
	
	
	/**
	 * @generated
	 */
	public static RouteservicesPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package RouteservicesPackage ...");
		theInstance = new RouteservicesPackage();
		//initialize referenced models
		IEC61174Model.init();
		IOModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.service.routeservices");
		p.getContent().add(theInstance.mRouteImport);
		p.getContent().add(theInstance.mRouteExport);
		p.getContent().add(theInstance.mGMLRouteImport);
		p.getContent().add(theInstance.mSimpleRouteFile);
		p.getContent().add(theInstance.mCSVRouteFile);
		p.getContent().add(theInstance.mRTZRouteImport);
		p.getContent().add(theInstance.mRTZRouteExport);
		p.getContent().add(theInstance.mSimpleRouteFileExport);
		p.freeze();
		
		
		
		ULog.debug("... package RouteservicesPackage initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mRouteImport = UMetaBuilder.manual().createClass("RouteImport", true, IRouteImport.class, RouteImportImpl.class);
			mRouteImport.setDocumentation(" Imports a route from an external route definition ");
		
		mRouteExport = UMetaBuilder.manual().createClass("RouteExport", true, IRouteExport.class, RouteExportImpl.class);
			mRouteExport.setDocumentation("\r\n * Exports a route from internal representatin (IEC61174) to an external format\r\n ");
		
		mGMLRouteImport = UMetaBuilder.manual().createClass("GMLRouteImport", false, IGMLRouteImport.class, GMLRouteImportImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGMLRouteImport, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GMLRouteImportImpl();
				}
			});
		
		mSimpleRouteFile = UMetaBuilder.manual().createClass("SimpleRouteFile", false, ISimpleRouteFile.class, SimpleRouteFileImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSimpleRouteFile, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SimpleRouteFileImpl();
				}
			});
			mSimpleRouteFile.setDocumentation(" Loads a simple txt based route file, as it could be exported from the EPD\r\n * e.g: \r\n * Route Template\r\n * Start56 17.000N011 00.000E10.010.1,0.10.0\r\n * Stop56 17.000N012 20.000E0.0000.0,0.00.5\r\n ");
		
		mCSVRouteFile = UMetaBuilder.manual().createClass("CSVRouteFile", false, ICSVRouteFile.class, CSVRouteFileImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCSVRouteFile, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CSVRouteFileImpl();
				}
			});
		
		mRTZRouteImport = UMetaBuilder.manual().createClass("RTZRouteImport", false, IRTZRouteImport.class, RTZRouteImportImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRTZRouteImport, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RTZRouteImportImpl();
				}
			});
			mRTZRouteImport.setDocumentation(" Imports an RTZ (XML) route file\r\n * \r\n * <?xml version=\"1.0\"?>\r\n * <route xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" version=\"1.0\" xmlns=\"http://www.cirm.org/RTZ/1/0\">\r\n *   <routeInfo routeName=\"Barcelona - Las Palmas\" />\r\n *   <waypoints>\r\n *     <waypoint name=\"\" radius=\"0\">\r\n *       <position lat=\"41.3335786967608\" lon=\"2.16797561484445\" />\r\n *     </waypoint>\r\n *     <waypoint name=\"\" radius=\"0.2\">\r\n *       <position lat=\"41.3276183895413\" lon=\"2.16687065350792\" />\r\n *       <leg starboardXTD=\"0.1\" portsideXTD=\"0.1\" geometryType=\"Orthodrome\" speedMax=\"19\" />\r\n *     </waypoint>\r\n ");
		
		mRTZRouteExport = UMetaBuilder.manual().createClass("RTZRouteExport", false, IRTZRouteExport.class, RTZRouteExportImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRTZRouteExport, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RTZRouteExportImpl();
				}
			});
		
		mSimpleRouteFileExport = UMetaBuilder.manual().createClass("SimpleRouteFileExport", false, ISimpleRouteFileExport.class, SimpleRouteFileExportImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSimpleRouteFileExport, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SimpleRouteFileExportImpl();
				}
			});
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of SimpleRouteFile
			mSimpleRouteFile_dezimalNotation = UMetaBuilder.manual().createFeature("dezimalNotation", TypeUtils.getPrimitiveType(boolean.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSimpleRouteFile_dezimalNotation, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ISimpleRouteFile)instance).getDezimalNotation(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ISimpleRouteFile)instance).setDezimalNotation((boolean)value); } }
				);
				mSimpleRouteFile_dezimalNotation.setDocumentation(" if set to true, the importer expects the routes waypoints, to be defined in dezimal degress (53.84, 8.5) \r\n * otherwise the degree minute second notation (56\u00EF\u00BF\u00BD17.3''\r\n ");
			
		}
		{ //assign features
			mSimpleRouteFile.getStructuralFeatures().add(mSimpleRouteFile_dezimalNotation);
		}
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of RouteImport
			UOperation operation = null;
			//operation : importRoute(Route, Object, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("importRoute", false, Iec61174Package.theInstance.getRoute(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IRouteImport)instance).importRoute((Object)parameter[0], (CoordinateReferenceSystem)parameter[1]);
				}
			});
				operation.setDocumentation(" imports the route from an external route definition. \r\n * @return a valid route if the definition could be read or null if the route was not valid\r\n ");
				//Annotations for RouteImport:importRoute(Route, Object, CoordinateReferenceSystem)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "definition", TypeUtils.getPrimitiveType(Object.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mRouteImport.getOperations().add(operation);
		}
		{		//Operations of RouteExport
			UOperation operation = null;
			//operation : exportRoute(boolean, Route, OutputStream)
			operation = UMetaBuilder.manual().createOperation("exportRoute", true, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IRouteExport)instance).exportRoute((Route)parameter[0], (IOutputStream)parameter[1]);
				}
			});
				operation.setDocumentation("\r\n * Exports the route to the given data stream\r\n * @param route route to be exported\r\n * @param stream target to which the route should be exported\r\n ");
				UMetaBuilder.manual().addParameter(operation, "route", Iec61174Package.theInstance.getRoute(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "stream", IoPackage.theInstance.getOutputStream(), 0, 1, UDirectionType.INOUT);
				mRouteExport.getOperations().add(operation);
		}
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mGMLRouteImport.setSuperType(RouteservicesPackage.theInstance.getRouteImport());
		mSimpleRouteFile.setSuperType(RouteservicesPackage.theInstance.getRouteImport());
		mCSVRouteFile.setSuperType(RouteservicesPackage.theInstance.getRouteImport());
		mRTZRouteImport.setSuperType(RouteservicesPackage.theInstance.getRouteImport());
		mRTZRouteExport.setSuperType(RouteservicesPackage.theInstance.getRouteExport());
		mSimpleRouteFileExport.setSuperType(RouteservicesPackage.theInstance.getRouteExport());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getRouteImport(){
		if (mRouteImport == null){
			mRouteImport = UCoreMetaRepository.getUClass(IRouteImport.class);
		}
		return mRouteImport;
	}
	/**
	* @generated
	*/
	public UClass getRouteExport(){
		if (mRouteExport == null){
			mRouteExport = UCoreMetaRepository.getUClass(IRouteExport.class);
		}
		return mRouteExport;
	}
	/**
	* @generated
	*/
	public UClass getGMLRouteImport(){
		if (mGMLRouteImport == null){
			mGMLRouteImport = UCoreMetaRepository.getUClass(IGMLRouteImport.class);
		}
		return mGMLRouteImport;
	}
	/**
	* @generated
	*/
	public UClass getSimpleRouteFile(){
		if (mSimpleRouteFile == null){
			mSimpleRouteFile = UCoreMetaRepository.getUClass(ISimpleRouteFile.class);
		}
		return mSimpleRouteFile;
	}
	/**
	* @generated
	*/
	public UClass getCSVRouteFile(){
		if (mCSVRouteFile == null){
			mCSVRouteFile = UCoreMetaRepository.getUClass(ICSVRouteFile.class);
		}
		return mCSVRouteFile;
	}
	/**
	* @generated
	*/
	public UClass getRTZRouteImport(){
		if (mRTZRouteImport == null){
			mRTZRouteImport = UCoreMetaRepository.getUClass(IRTZRouteImport.class);
		}
		return mRTZRouteImport;
	}
	/**
	* @generated
	*/
	public UClass getRTZRouteExport(){
		if (mRTZRouteExport == null){
			mRTZRouteExport = UCoreMetaRepository.getUClass(IRTZRouteExport.class);
		}
		return mRTZRouteExport;
	}
	/**
	* @generated
	*/
	public UClass getSimpleRouteFileExport(){
		if (mSimpleRouteFileExport == null){
			mSimpleRouteFileExport = UCoreMetaRepository.getUClass(ISimpleRouteFileExport.class);
		}
		return mSimpleRouteFileExport;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getSimpleRouteFile_dezimalNotation(){
		if (mSimpleRouteFile_dezimalNotation == null)
			mSimpleRouteFile_dezimalNotation = getSimpleRouteFile().getFeature("dezimalNotation");
		return mSimpleRouteFile_dezimalNotation;
	}
}
