package de.emir.service.geometry;

import de.emir.model.universal.CRSModel;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.MathModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.service.geometry.impl.GeometryFactory;
import de.emir.service.geometry.impl.GeometryTransform;
import de.emir.service.geometry.impl.QuadTree;
import de.emir.service.geometry.impl.SimpleFeatureGeometryProvider;
import de.emir.service.geometry.impl.SpatialIndex;
import de.emir.service.geometry.impl.SpatialIndexVisitor;
import de.emir.service.geometry.impl.WKTUtil;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

/**
 *	@generated 
 */
public class GeometryPackage  
{
	/**
	 * @generated
	 */
	public static GeometryPackage theInstance = new GeometryPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier GeometryTransform
		*/
		UClass GeometryTransform = GeometryPackage.theInstance.getGeometryTransform();
		/**
		* @generated
		* @return meta type for classifier WKTUtil
		*/
		UClass WKTUtil = GeometryPackage.theInstance.getWKTUtil();
		/**
		 * @generated
		 * @return meta type for interface GeometryProvider
		 */
		UInterface GeometryProvider = GeometryPackage.theInstance.getGeometryProvider();
		/**
		* @generated
		* @return meta type for classifier GeometryFactory
		*/
		UClass GeometryFactory = GeometryPackage.theInstance.getGeometryFactory();
		/**
		* @generated
		* @return meta type for classifier SimpleFeatureGeometryProvider
		*/
		UClass SimpleFeatureGeometryProvider = GeometryPackage.theInstance.getSimpleFeatureGeometryProvider();
		/**
		* @generated
		* @return meta type for classifier SpatialIndexVisitor
		*/
		UClass SpatialIndexVisitor = GeometryPackage.theInstance.getSpatialIndexVisitor();
		/**
		* @generated
		* @return meta type for classifier SpatialIndex
		*/
		UClass SpatialIndex = GeometryPackage.theInstance.getSpatialIndex();
		/**
		* @generated
		* @return meta type for classifier QuadTree
		*/
		UClass QuadTree = GeometryPackage.theInstance.getQuadTree();
		/**
		 * @generated
		 * @return feature descriptor provider in type GeometryFactory
		 */
		 UStructuralFeature GeometryFactory_provider = GeometryPackage.theInstance.getGeometryFactory_provider();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mGeometryTransform = null;
	/**
	* @generated
	*/
	private UClass mWKTUtil = null;
	/**
	 * @generated
	 */
	private UInterface mGeometryProvider = null;
	/**
	* @generated
	*/
	private UClass mGeometryFactory = null;
	/**
	* @generated
	*/
	private UClass mSimpleFeatureGeometryProvider = null;
	/**
	* @generated
	*/
	private UClass mSpatialIndexVisitor = null;
	/**
	* @generated
	*/
	private UClass mSpatialIndex = null;
	/**
	* @generated
	*/
	private UClass mQuadTree = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	//Features for classifier GeometryFactory
	/**
	 * @generated
	 */
	private UStructuralFeature mGeometryFactory_provider = null;
	
	/**
	 * @generated
	 */
	private static void initAnnotations(){
		ULog.debug("generate Annotations");
	}



	/**
	 * @generated
	 */
	public static GeometryPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package GeometryPackage ...", 1);
		theInstance = new GeometryPackage();
		//initialize referenced models
		CRSModel.init();
		CoreModel.init();
		MathModel.init();
		SpatialModel.init();
		UnitsModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.service.geometry");
		p.getContent().add(theInstance.mGeometryTransform);
		p.getContent().add(theInstance.mWKTUtil);
		p.getContent().add(theInstance.mGeometryProvider);
		p.getContent().add(theInstance.mGeometryFactory);
		p.getContent().add(theInstance.mSimpleFeatureGeometryProvider);
		p.getContent().add(theInstance.mSpatialIndexVisitor);
		p.getContent().add(theInstance.mSpatialIndex);
		p.getContent().add(theInstance.mQuadTree);
		p.freeze();
		
		theInstance.initializeAccessors();
		
		GeometryPackage.initAnnotations();
		
		ULog.debug(-1, "... package GeometryPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mGeometryTransform = UMetaBuilder.manual().createClass("GeometryTransform", false, IGeometryTransform.class, GeometryTransform.class);
		mWKTUtil = UMetaBuilder.manual().createClass("WKTUtil", false, IWKTUtil.class, WKTUtil.class);
		mGeometryProvider = UMetaBuilder.manual().createInterface("GeometryProvider", IGeometryProvider.class);
		mGeometryFactory = UMetaBuilder.manual().createClass("GeometryFactory", false, IGeometryFactory.class, GeometryFactory.class);
		mSimpleFeatureGeometryProvider = UMetaBuilder.manual().createClass("SimpleFeatureGeometryProvider", false, ISimpleFeatureGeometryProvider.class, SimpleFeatureGeometryProvider.class);
		mSpatialIndexVisitor = UMetaBuilder.manual().createClass("SpatialIndexVisitor", true, ISpatialIndexVisitor.class, SpatialIndexVisitor.class);
		mSpatialIndex = UMetaBuilder.manual().createClass("SpatialIndex", true, ISpatialIndex.class, SpatialIndex.class);
		mQuadTree = UMetaBuilder.manual().createClass("QuadTree", false, IQuadTree.class, QuadTree.class);
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of GeometryFactory
			mGeometryFactory_provider = UMetaBuilder.manual().createFeature("provider", GeometryPackage.theInstance.getGeometryProvider(), UAssociationType.COMPOSITION, 0, -1);
			
		}
		{ //assign features
			mGeometryFactory.getStructuralFeatures().add(mGeometryFactory_provider);
		}
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mSimpleFeatureGeometryProvider.getInterfaces().add(GeometryPackage.theInstance.getGeometryProvider());
		mQuadTree.setSuperType(GeometryPackage.theInstance.getSpatialIndex());
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void initializeAccessors()
	{
		{ // instance creators
			UMetaBuilder.manual().setInstanceCreator(mGeometryTransform, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GeometryTransform();
				}
			});
			UMetaBuilder.manual().setInstanceCreator(mWKTUtil, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WKTUtil();
				}
			});
			UMetaBuilder.manual().setInstanceCreator(mGeometryFactory, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GeometryFactory();
				}
			});
			UMetaBuilder.manual().setInstanceCreator(mSimpleFeatureGeometryProvider, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SimpleFeatureGeometryProvider();
				}
			});
			UMetaBuilder.manual().setInstanceCreator(mQuadTree, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new QuadTree();
				}
			});
		}// instance creators
		
		{ // feature acessors
			UMetaBuilder.manual().setFeatureAccessor(mGeometryFactory_provider, 
					new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IGeometryFactory)instance).getProvider(); } }, 
					null
			);
		}
	}



	/**
	* @generated
	*/
	public UClass getWKTUtil(){
		if (mWKTUtil == null){
			mWKTUtil = UCoreMetaRepository.getUClass(IWKTUtil.class);
		}
		return mWKTUtil;
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getGeometryTransform(){
		if (mGeometryTransform == null){
			mGeometryTransform = UCoreMetaRepository.getUClass(IGeometryTransform.class);
		}
		return mGeometryTransform;
	}
	/**
	* @generated
	*/
	public UInterface getGeometryProvider(){
		if (mGeometryProvider == null){
			mGeometryProvider = UCoreMetaRepository.getUInterface(IGeometryProvider.class);
		}
		return mGeometryProvider;
	}
	/**
	* @generated
	*/
	public UClass getGeometryFactory(){
		if (mGeometryFactory == null){
			mGeometryFactory = UCoreMetaRepository.getUClass(IGeometryFactory.class);
		}
		return mGeometryFactory;
	}
	/**
	* @generated
	*/
	public UClass getSimpleFeatureGeometryProvider(){
		if (mSimpleFeatureGeometryProvider == null){
			mSimpleFeatureGeometryProvider = UCoreMetaRepository.getUClass(ISimpleFeatureGeometryProvider.class);
		}
		return mSimpleFeatureGeometryProvider;
	}
	/**
	* @generated
	*/
	public UClass getSpatialIndexVisitor(){
		if (mSpatialIndexVisitor == null){
			mSpatialIndexVisitor = UCoreMetaRepository.getUClass(ISpatialIndexVisitor.class);
		}
		return mSpatialIndexVisitor;
	}
	/**
	* @generated
	*/
	public UClass getSpatialIndex(){
		if (mSpatialIndex == null){
			mSpatialIndex = UCoreMetaRepository.getUClass(ISpatialIndex.class);
		}
		return mSpatialIndex;
	}
	/**
	* @generated
	*/
	public UClass getQuadTree(){
		if (mQuadTree == null){
			mQuadTree = UCoreMetaRepository.getUClass(IQuadTree.class);
		}
		return mQuadTree;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getGeometryFactory_provider(){
		if (mGeometryFactory_provider == null)
			mGeometryFactory_provider = getGeometryFactory().getFeature("provider");
		return mGeometryFactory_provider;
	}
}
