package de.emir.model.universal.core;

import de.emir.model.universal.core.impl.AbstractFeatureImpl;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.model.universal.core.impl.AbstractInformationImpl;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.core.impl.FeatureChangeObserverImpl;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.core.impl.IdentifiedReferenceImpl;
import de.emir.model.universal.core.impl.MDIdentifierImpl;
import de.emir.model.universal.core.impl.MemberObserverImpl;
import de.emir.model.universal.core.impl.ModelPathImpl;
import de.emir.model.universal.core.impl.NamedElementImpl;
import de.emir.model.universal.core.impl.ObserverImpl;
import de.emir.model.universal.core.impl.RSIdentifierImpl;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.model.universal.core.AbstractFeature;
import de.emir.model.universal.core.AbstractInformation;
import de.emir.model.universal.core.Condition;
import de.emir.model.universal.core.FeatureChangeObserver;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.ModelPath;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.model.universal.core.IdentifiedReference;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.core.Variable;
import de.emir.model.universal.core.JavaScriptCondition;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.model.universal.core.Observer;
import de.emir.model.universal.core.MDIdentifier;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.universal.core.NamedElement;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.impl.JavaScriptConditionImpl;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 *	@generated 
 */
public class CorePackage  
{
	
	/**
	 * @generated
	 */
	public static CorePackage theInstance = new CorePackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier MDIdentifier
		*/
		UClass MDIdentifier = CorePackage.theInstance.getMDIdentifier();
		/**
		* @generated
		* @return meta type for classifier RSIdentifier
		*/
		UClass RSIdentifier = CorePackage.theInstance.getRSIdentifier();
		/**
		* @generated
		* @return meta type for classifier NamedElement
		*/
		UClass NamedElement = CorePackage.theInstance.getNamedElement();
		/**
		* @generated
		* @return meta type for classifier IdentifiedObject
		*/
		UClass IdentifiedObject = CorePackage.theInstance.getIdentifiedObject();
		/**
		 * @generated
		 * @return meta type for interface Condition
		 */
		UInterface Condition = CorePackage.theInstance.getCondition();
		/**
		 * @generated
		 * @return meta type for interface ModelReference
		 */
		UInterface ModelReference = CorePackage.theInstance.getModelReference();
		/**
		* @generated
		* @return meta type for classifier IdentifiedReference
		*/
		UClass IdentifiedReference = CorePackage.theInstance.getIdentifiedReference();
		/**
		* @generated
		* @return meta type for classifier ModelPath
		*/
		UClass ModelPath = CorePackage.theInstance.getModelPath();
		/**
		* @generated
		* @return meta type for classifier Observer
		*/
		UClass Observer = CorePackage.theInstance.getObserver();
		/**
		* @generated
		* @return meta type for classifier FeatureChangeObserver
		*/
		UClass FeatureChangeObserver = CorePackage.theInstance.getFeatureChangeObserver();
		/**
		* @generated
		* @return meta type for classifier MemberObserver
		*/
		UClass MemberObserver = CorePackage.theInstance.getMemberObserver();
		/**
		* @generated
		* @return meta type for classifier AbstractInformation
		*/
		UClass AbstractInformation = CorePackage.theInstance.getAbstractInformation();
		/**
		* @generated
		* @return meta type for classifier AbstractFeature
		*/
		UClass AbstractFeature = CorePackage.theInstance.getAbstractFeature();
		/**
		* @generated
		* @return meta type for classifier Variable
		*/
		UClass Variable = CorePackage.theInstance.getVariable();
		/**
		* @generated
		* @return meta type for classifier JavaScriptCondition
		*/
		UClass JavaScriptCondition = CorePackage.theInstance.getJavaScriptCondition();
		
		/**
		 * @generated
		 * @return feature descriptor code in type MDIdentifier
		 */
		 UStructuralFeature MDIdentifier_code = CorePackage.theInstance.getMDIdentifier_code();
		/**
		 * @generated
		 * @return feature descriptor version in type RSIdentifier
		 */
		 UStructuralFeature RSIdentifier_version = CorePackage.theInstance.getRSIdentifier_version();
		/**
		 * @generated
		 * @return feature descriptor codeSpace in type RSIdentifier
		 */
		 UStructuralFeature RSIdentifier_codeSpace = CorePackage.theInstance.getRSIdentifier_codeSpace();
		/**
		 * @generated
		 * @return feature descriptor name in type NamedElement
		 */
		 UStructuralFeature NamedElement_name = CorePackage.theInstance.getNamedElement_name();
		/**
		 * @generated
		 * @return feature descriptor allias in type IdentifiedObject
		 */
		 UStructuralFeature IdentifiedObject_allias = CorePackage.theInstance.getIdentifiedObject_allias();
		/**
		 * @generated
		 * @return feature descriptor remarks in type IdentifiedObject
		 */
		 UStructuralFeature IdentifiedObject_remarks = CorePackage.theInstance.getIdentifiedObject_remarks();
		/**
		 * @generated
		 * @return feature descriptor observers in type IdentifiedObject
		 */
		 UStructuralFeature IdentifiedObject_observers = CorePackage.theInstance.getIdentifiedObject_observers();
		/**
		 * @generated
		 * @return feature descriptor identifier in type IdentifiedObject
		 */
		 UStructuralFeature IdentifiedObject_identifier = CorePackage.theInstance.getIdentifiedObject_identifier();
		/**
		 * @generated
		 * @return feature descriptor pointerString in type ModelPath
		 */
		 UStructuralFeature ModelPath_pointerString = CorePackage.theInstance.getModelPath_pointerString();
		/**
		 * @generated
		 * @return feature descriptor rootInstance in type ModelPath
		 */
		 UStructuralFeature ModelPath_rootInstance = CorePackage.theInstance.getModelPath_rootInstance();
		/**
		 * @generated
		 * @return feature descriptor referencedIdentifier in type IdentifiedReference
		 */
		 UStructuralFeature IdentifiedReference_referencedIdentifier = CorePackage.theInstance.getIdentifiedReference_referencedIdentifier();
		/**
		 * @generated
		 * @return feature descriptor informationObjects in type AbstractFeature
		 */
		 UStructuralFeature AbstractFeature_informationObjects = CorePackage.theInstance.getAbstractFeature_informationObjects();
		/**
		 * @generated
		 * @return feature descriptor operations in type Observer
		 */
		 UStructuralFeature Observer_operations = CorePackage.theInstance.getObserver_operations();
		/**
		 * @generated
		 * @return feature descriptor reference in type FeatureChangeObserver
		 */
		 UStructuralFeature FeatureChangeObserver_reference = CorePackage.theInstance.getFeatureChangeObserver_reference();
		/**
		 * @generated
		 * @return feature descriptor pointerString in type MemberObserver
		 */
		 UStructuralFeature MemberObserver_pointerString = CorePackage.theInstance.getMemberObserver_pointerString();
		/**
		 * @generated
		 * @return feature descriptor script in type JavaScriptCondition
		 */
		 UStructuralFeature JavaScriptCondition_script = CorePackage.theInstance.getJavaScriptCondition_script();
		/**
		 * @generated
		 * @return feature descriptor reference in type JavaScriptCondition
		 */
		 UStructuralFeature JavaScriptCondition_reference = CorePackage.theInstance.getJavaScriptCondition_reference();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mMDIdentifier = null;
	/**
	* @generated
	*/
	private UClass mRSIdentifier = null;
	/**
	* @generated
	*/
	private UClass mNamedElement = null;
	/**
	* @generated
	*/
	private UClass mIdentifiedObject = null;
	/**
	 * @generated
	 */
	private UInterface mCondition = null;
	/**
	 * @generated
	 */
	private UInterface mModelReference = null;
	/**
	* @generated
	*/
	private UClass mIdentifiedReference = null;
	/**
	* @generated
	*/
	private UClass mModelPath = null;
	/**
	* @generated
	*/
	private UClass mObserver = null;
	/**
	* @generated
	*/
	private UClass mFeatureChangeObserver = null;
	/**
	* @generated
	*/
	private UClass mMemberObserver = null;
	/**
	* @generated
	*/
	private UClass mAbstractInformation = null;
	/**
	* @generated
	*/
	private UClass mAbstractFeature = null;
	/**
	* @generated
	*/
	private UClass mVariable = null;
	/**
	* @generated
	*/
	private UClass mJavaScriptCondition = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier MDIdentifier
	/**
	 * @generated
	 */
	private UStructuralFeature mMDIdentifier_code = null;
	
	//Features for classifier RSIdentifier
	/**
	 * @generated
	 */
	private UStructuralFeature mRSIdentifier_version = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRSIdentifier_codeSpace = null;
	
	//Features for classifier NamedElement
	/**
	 * @generated
	 */
	private UStructuralFeature mNamedElement_name = null;
	
	//Features for classifier IdentifiedObject
	/**
	 * @generated
	 */
	private UStructuralFeature mIdentifiedObject_allias = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mIdentifiedObject_remarks = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mIdentifiedObject_observers = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mIdentifiedObject_identifier = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mModelPath_rootInstance = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mModelPath_pointerString = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mIdentifiedReference_referencedIdentifier = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mObserver_operations = null;
	//Features for classifier AbstractFeature
	/**
	 * @generated
	 */
	private UStructuralFeature mAbstractFeature_informationObjects = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMemberObserver_pointerString = null;
	
	/**
	 * @generated
	 */
	private UStructuralFeature mJavaScriptCondition_script = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mJavaScriptCondition_reference = null;
	
	
	
	/**
	 * @generated
	 */
	public static CorePackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package CorePackage ...");
		theInstance = new CorePackage();
		//initialize referenced models
		UCoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.core");
		p.getContent().add(theInstance.mMDIdentifier);
		p.getContent().add(theInstance.mRSIdentifier);
		p.getContent().add(theInstance.mNamedElement);
		p.getContent().add(theInstance.mIdentifiedObject);
		p.getContent().add(theInstance.mCondition);
		p.getContent().add(theInstance.mModelReference);
		p.getContent().add(theInstance.mIdentifiedReference);
		p.getContent().add(theInstance.mModelPath);
		p.getContent().add(theInstance.mObserver);
		p.getContent().add(theInstance.mFeatureChangeObserver);
		p.getContent().add(theInstance.mMemberObserver);
		p.getContent().add(theInstance.mAbstractInformation);
		p.getContent().add(theInstance.mAbstractFeature);
		p.getContent().add(theInstance.mVariable);
		p.getContent().add(theInstance.mJavaScriptCondition);
		p.freeze();
		
		
		
		ULog.debug("... package CorePackage initialized");
		
		return theInstance;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mFeatureChangeObserver_reference = null;
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mMDIdentifier = UMetaBuilder.manual().createClass("MDIdentifier", false, MDIdentifier.class, MDIdentifierImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMDIdentifier, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MDIdentifierImpl();
				}
			});
			mMDIdentifier.setDocumentation(" Identifier from ISO 19115-3 Metadata\r\n * \\note for now the CI_Citation (also ISO 19115-3) is missing and should be added if required\r\n ");
		
		mRSIdentifier = UMetaBuilder.manual().createClass("RSIdentifier", false, RSIdentifier.class, RSIdentifierImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRSIdentifier, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RSIdentifierImpl();
				}
			});
		
		mNamedElement = UMetaBuilder.manual().createClass("NamedElement", true, NamedElement.class, NamedElementImpl.class);
		
		mIdentifiedObject = UMetaBuilder.manual().createClass("IdentifiedObject", true, IdentifiedObject.class, IdentifiedObjectImpl.class);
			mIdentifiedObject.setDocumentation(" Any object that can be identified shall inherit from this class ");
		
		mCondition = UMetaBuilder.manual().createInterface("Condition", Condition.class);
			mCondition.setDocumentation("\r\n * The state of an environment or situation in which a Performer performs\r\n ");
		
		mModelReference = UMetaBuilder.manual().createInterface("ModelReference", ModelReference.class);
		
		mIdentifiedReference = UMetaBuilder.manual().createClass("IdentifiedReference", false, IdentifiedReference.class, IdentifiedReferenceImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mIdentifiedReference, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new IdentifiedReferenceImpl();
				}
			});
		
		mModelPath = UMetaBuilder.manual().createClass("ModelPath", false, ModelPath.class, ModelPathImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mModelPath, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ModelPathImpl();
				}
			});
			mModelPath.setDocumentation("\r\n * A ModelPath can be used to reference to a position within the model tree or any subtree. \r\n * Thereby it is the direct representation of the Pointer (defined in UCore), but without the need\r\n * of knowing the UStructuralFeatures. \r\n *  \r\n ");
		
		mObserver = UMetaBuilder.manual().createClass("Observer", true, Observer.class, ObserverImpl.class);
			mObserver.setDocumentation("\r\n * An Observer is used to observe value changes and notifies a value change listener\r\n ");
			//Annotations for Observer
			mObserver.createAnnotation("Deprecated");
		
		mFeatureChangeObserver = UMetaBuilder.manual().createClass("FeatureChangeObserver", false, FeatureChangeObserver.class, FeatureChangeObserverImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mFeatureChangeObserver, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new FeatureChangeObserverImpl();
				}
			});
			mFeatureChangeObserver.setDocumentation("\r\n * Observes one structural feature, if the feature's value change.\r\n * Therefore the FeatureChangeObserver is the direct representation of the UObject Observation pattern.  \r\n ");
			//Annotations for FeatureChangeObserver
			mFeatureChangeObserver.createAnnotation("Deprecated");
		
		mMemberObserver = UMetaBuilder.manual().createClass("MemberObserver", false, MemberObserver.class, MemberObserverImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMemberObserver, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MemberObserverImpl();
				}
			});
			//Annotations for MemberObserver
			mMemberObserver.createAnnotation("Deprecated");
		
		mAbstractInformation = UMetaBuilder.manual().createClass("AbstractInformation", true, AbstractInformation.class, AbstractInformationImpl.class);
		
		mAbstractFeature = UMetaBuilder.manual().createClass("AbstractFeature", true, AbstractFeature.class, AbstractFeatureImpl.class);
		
		mVariable = UMetaBuilder.manual().createClass("Variable", true, Variable.class, VariableImpl.class);
			mVariable.setDocumentation("\r\n * An Variable can be used to store (or reference) a single value. \r\n * The variable's name and documentation are to be taken from the IdentifiedObject\r\n ");
		
		mJavaScriptCondition = UMetaBuilder.manual().createClass("JavaScriptCondition", false, JavaScriptCondition.class, JavaScriptConditionImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mJavaScriptCondition, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new JavaScriptConditionImpl();
				}
			});
			//Annotations for JavaScriptCondition
			mJavaScriptCondition.createAnnotation("Deprecated");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of MDIdentifier
			mMDIdentifier_code = UMetaBuilder.manual().createFeature("code", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMDIdentifier_code, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MDIdentifier)instance).getCode(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((MDIdentifier)instance).setCode((String)value); } }
				);
				mMDIdentifier_code.setDocumentation(" Identifier code or name, often from a controlled list or pattern defined by a code space.  ");
			
			//Features of RSIdentifier
			mRSIdentifier_version = UMetaBuilder.manual().createFeature("version", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRSIdentifier_version, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RSIdentifier)instance).getVersion(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RSIdentifier)instance).setVersion((String)value); } }
				);
				mRSIdentifier_version.setDocumentation(" which is a version for the identifier  ");
			mRSIdentifier_codeSpace = UMetaBuilder.manual().createFeature("codeSpace", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRSIdentifier_codeSpace, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RSIdentifier)instance).getCodeSpace(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RSIdentifier)instance).setCodeSpace((String)value); } }
				);
				mRSIdentifier_codeSpace.setDocumentation(" which unambiguously defines the namespace for the identifier  ");
			
			//Features of NamedElement
			mNamedElement_name = UMetaBuilder.manual().createFeature("name", CorePackage.theInstance.getRSIdentifier(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mNamedElement_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((NamedElement)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((NamedElement)instance).setName((RSIdentifier)value); } }
				);
				mNamedElement_name.setDocumentation(" The primary name by which the object can be identified ");
			
			//Features of IdentifiedObject
			mIdentifiedObject_allias = UMetaBuilder.manual().createFeature("allias", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mIdentifiedObject_allias, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IdentifiedObject)instance).getAllias(); } }, 
						null
				);
				mIdentifiedObject_allias.setDocumentation(" An alternative name of the object ");
			mIdentifiedObject_remarks = UMetaBuilder.manual().createFeature("remarks", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mIdentifiedObject_remarks, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IdentifiedObject)instance).getRemarks(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((IdentifiedObject)instance).setRemarks((String)value); } }
				);
				mIdentifiedObject_remarks.setDocumentation(" Comments on or information about the object ");
			mIdentifiedObject_observers = UMetaBuilder.manual().createFeature("observers", CorePackage.theInstance.getMemberObserver(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mIdentifiedObject_observers, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IdentifiedObject)instance).getObservers(); } }, 
						null
				);
				mIdentifiedObject_observers.setDocumentation("\r\n * Member Observer may be used to observe changes of structural features within this object\r\n * @deprecated will be removed soon, as not used\r\n ");
				//Annotations for IdentifiedObject:observers
				mIdentifiedObject_observers.createAnnotation("Deprecated");
			mIdentifiedObject_identifier = UMetaBuilder.manual().createFeature("identifier", CorePackage.theInstance.getRSIdentifier(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mIdentifiedObject_identifier, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IdentifiedObject)instance).getIdentifier(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((IdentifiedObject)instance).setIdentifier((RSIdentifier)value); } }
				);
				mIdentifiedObject_identifier.setDocumentation(" An identifier that references the (external) definition of the object ");
			
			//Features of IdentifiedReference
			mIdentifiedReference_referencedIdentifier = UMetaBuilder.manual().createFeature("referencedIdentifier", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mIdentifiedReference_referencedIdentifier, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IdentifiedReference)instance).getReferencedIdentifier(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((IdentifiedReference)instance).setReferencedIdentifier((String)value); } }
				);
				mIdentifiedReference_referencedIdentifier.setDocumentation(" contains the code of an RSIdentifier of an NamedElement ");
			
			//Features of ModelPath
			mModelPath_pointerString = UMetaBuilder.manual().createFeature("pointerString", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mModelPath_pointerString, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ModelPath)instance).getPointerString(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ModelPath)instance).setPointerString((String)value); } }
				);
				mModelPath_pointerString.setDocumentation(" textual description from the instance, using the features (and list indices) to the \r\n * required / observed feature\r\n * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*\r\n ");
			mModelPath_rootInstance = UMetaBuilder.manual().createFeature("rootInstance", RuntimePackage.theInstance.getUObject(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mModelPath_rootInstance, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ModelPath)instance).getRootInstance(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ModelPath)instance).setRootInstance((UObject)value); } }
				);
				mModelPath_rootInstance.setDocumentation(" Root element from which the pointerString starts and defines the Tree or SubTree root ");
			
			//Features of Observer
			mObserver_operations = UMetaBuilder.manual().createFeature("operations", RuntimePackage.theInstance.getIValueChangeListener(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mObserver_operations, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Observer)instance).getOperations(); } }, 
						null
				);
			
			//Features of FeatureChangeObserver
			mFeatureChangeObserver_reference = UMetaBuilder.manual().createFeature("reference", CorePackage.theInstance.getModelPath(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mFeatureChangeObserver_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((FeatureChangeObserver)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((FeatureChangeObserver)instance).setReference((ModelPath)value); } }
				);
			
			//Features of MemberObserver
			mMemberObserver_pointerString = UMetaBuilder.manual().createFeature("pointerString", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMemberObserver_pointerString, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MemberObserver)instance).getPointerString(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((MemberObserver)instance).setPointerString((String)value); } }
				);
				mMemberObserver_pointerString.setDocumentation(" textual description from the instance, using the features (and list indices) to the \r\n * required / observed feature\r\n * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*\r\n ");
			
			//Features of AbstractFeature
			mAbstractFeature_informationObjects = UMetaBuilder.manual().createFeature("informationObjects", CorePackage.theInstance.getAbstractInformation(), UAssociationType.ASSOCIATION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mAbstractFeature_informationObjects, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AbstractFeature)instance).getInformationObjects(); } }, 
						null
				);
			
			//Features of JavaScriptCondition
			mJavaScriptCondition_script = UMetaBuilder.manual().createFeature("script", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mJavaScriptCondition_script, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((JavaScriptCondition)instance).getScript(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((JavaScriptCondition)instance).setScript((String)value); } }
				);
				mJavaScriptCondition_script.setDocumentation(" The script to evaluate.\r\n * There are two options to define a script:\r\n * - define the full function like: <code> function eval(reference) { if (reference.getSpeed().getValue() > 5) return boolean; } </code> this method is used if the first word of the script starts with the term \"function\"\r\n * - define only the body of the script like: <code> if (reference.getSpeed().getValue() > 5) </code> in this case the optional reference (if not null) is called \"reference\". This method is used if the first word of the script is NOT \"function\" \r\n ");
			mJavaScriptCondition_reference = UMetaBuilder.manual().createFeature("reference", RuntimePackage.theInstance.getUObject(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mJavaScriptCondition_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((JavaScriptCondition)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((JavaScriptCondition)instance).setReference((UObject)value); } }
				);
				mJavaScriptCondition_reference.setDocumentation(" an optional reference value that is provided as function parameter to the script function ");
			
		}
		{ //assign features
			mMDIdentifier.getStructuralFeatures().add(mMDIdentifier_code);
			mRSIdentifier.getStructuralFeatures().add(mRSIdentifier_version);
			mRSIdentifier.getStructuralFeatures().add(mRSIdentifier_codeSpace);
			mNamedElement.getStructuralFeatures().add(mNamedElement_name);
			mIdentifiedObject.getStructuralFeatures().add(mIdentifiedObject_allias);
			mIdentifiedObject.getStructuralFeatures().add(mIdentifiedObject_remarks);
			mIdentifiedObject.getStructuralFeatures().add(mIdentifiedObject_observers);
			mIdentifiedObject.getStructuralFeatures().add(mIdentifiedObject_identifier);
			mIdentifiedReference.getStructuralFeatures().add(mIdentifiedReference_referencedIdentifier);
			mModelPath.getStructuralFeatures().add(mModelPath_pointerString);
			mModelPath.getStructuralFeatures().add(mModelPath_rootInstance);
			mObserver.getStructuralFeatures().add(mObserver_operations);
			mFeatureChangeObserver.getStructuralFeatures().add(mFeatureChangeObserver_reference);
			mMemberObserver.getStructuralFeatures().add(mMemberObserver_pointerString);
			mAbstractFeature.getStructuralFeatures().add(mAbstractFeature_informationObjects);
			mJavaScriptCondition.getStructuralFeatures().add(mJavaScriptCondition_script);
			mJavaScriptCondition.getStructuralFeatures().add(mJavaScriptCondition_reference);
		}
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of NamedElement
			UOperation operation = null;
			//operation : setName(void, String)
			operation = UMetaBuilder.manual().createOperation("setName", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((NamedElement)instance).setName((String)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				mNamedElement.getOperations().add(operation);
			//operation : hasValidName(boolean)
			operation = UMetaBuilder.manual().createOperation("hasValidName", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((NamedElement)instance).hasValidName();
				}
			});
				operation.setDocumentation(" @Brief: Convenience Method to check if the element has a valid name\r\n * Checks if the element has a valid name. \r\n * A name is valid if: \r\n * - getName() != null\r\n * - getName().getCode() != null \r\n * - getName().getCode().isEmpty() == false\r\n * @return\r\n ");
				//Annotations for NamedElement:hasValidName(boolean)
				operation.createAnnotation("const");
				mNamedElement.getOperations().add(operation);
			//operation : getNameAsString(String)
			operation = UMetaBuilder.manual().createOperation("getNameAsString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((NamedElement)instance).getNameAsString();
				}
			});
				operation.setDocumentation(" @Brief Convenience Method to return the code of this element \r\n * @return the value of getName().getCode() if hasValidName() returns true, null otherwise\r\n ");
				//Annotations for NamedElement:getNameAsString(String)
				operation.createAnnotation("const");
				mNamedElement.getOperations().add(operation);
		}
		{		//Operations of IdentifiedObject
			UOperation operation = null;
			//operation : hasAlias(boolean, String)
			operation = UMetaBuilder.manual().createOperation("hasAlias", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IdentifiedObject)instance).hasAlias((String)parameter[0]);
				}
			});
				//Annotations for IdentifiedObject:hasAlias(boolean, String)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				mIdentifiedObject.getOperations().add(operation);
		}
		{		//Operations of Condition
			UOperation operation = null;
			//operation : evaluate(boolean)
			operation = UMetaBuilder.manual().createOperation("evaluate", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Condition)instance).evaluate();
				}
			});
				//Annotations for Condition:evaluate(boolean)
				operation.createAnnotation("const");
				mCondition.getOperations().add(operation);
		}
		{		//Operations of ModelReference
			UOperation operation = null;
			//operation : getPointer(Pointer)
			operation = UMetaBuilder.manual().createOperation("getPointer", false, UtilsPackage.theInstance.getPointer(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ModelReference)instance).getPointer();
				}
			});
				operation.setDocumentation("\r\n * returns a pointer to the referenced object or null if the object could not be found or there is any other \r\n * error within the configuration of the ModelReference \r\n * @returns a pointer to the referenced element or null\r\n ");
				//Annotations for ModelReference:getPointer(Pointer)
				operation.createAnnotation("const");
				mModelReference.getOperations().add(operation);
		}
		{		//Operations of ModelPath
			UOperation operation = null;
			//operation : checkPointerString(boolean)
			operation = UMetaBuilder.manual().createOperation("checkPointerString", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ModelPath)instance).checkPointerString();
				}
			});
				operation.setDocumentation("\r\n * Checks the pointerString, if it follows the required grammar and can create a valid pointer. \r\n ");
				//Annotations for ModelPath:checkPointerString(boolean)
				operation.createAnnotation("const");
				mModelPath.getOperations().add(operation);
		}
		{		//Operations of MemberObserver
			UOperation operation = null;
			//operation : checkPointerString(boolean)
			operation = UMetaBuilder.manual().createOperation("checkPointerString", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((MemberObserver)instance).checkPointerString();
				}
			});
				operation.setDocumentation("\r\n * Checks the pointerString, if it follows the required grammar and can create a valid pointer. \r\n ");
				//Annotations for MemberObserver:checkPointerString(boolean)
				operation.createAnnotation("const");
				mMemberObserver.getOperations().add(operation);
			//operation : getPointer(Pointer)
			operation = UMetaBuilder.manual().createOperation("getPointer", false, UtilsPackage.theInstance.getPointer(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((MemberObserver)instance).getPointer();
				}
			});
				operation.setDocumentation("\r\n * Creates a pointer from the given information, e.g. parses the pointerString depending on the \r\n * root instance. \r\n * @returns a pointer to the referenced element or null, if either the pointerString is invalid or the rootInstance is not set\r\n ");
				//Annotations for MemberObserver:getPointer(Pointer)
				operation.createAnnotation("const");
				mMemberObserver.getOperations().add(operation);
		}
		{		//Operations of Variable
			UOperation operation = null;
			//operation : setObjectValue(void, Object)
			operation = UMetaBuilder.manual().createOperation("setObjectValue", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Variable)instance).setObjectValue((Object)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation("\r\n * This method expect the value to be in the correct type, otherwise it may throws an Exception\r\n ");
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1, UDirectionType.IN);
				mVariable.getOperations().add(operation);
			//operation : getValueAsObject(Object)
			operation = UMetaBuilder.manual().createOperation("getValueAsObject", false, TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Variable)instance).getValueAsObject();
				}
			});
				//Annotations for Variable:getValueAsObject(Object)
				operation.createAnnotation("const");
				mVariable.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mRSIdentifier.setSuperType(CorePackage.theInstance.getMDIdentifier());
		mIdentifiedObject.setSuperType(CorePackage.theInstance.getNamedElement());
		mIdentifiedReference.getInterfaces().add(CorePackage.theInstance.getModelReference());
		mModelPath.getInterfaces().add(CorePackage.theInstance.getModelReference());
		mObserver.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mFeatureChangeObserver.setSuperType(CorePackage.theInstance.getObserver());
		mMemberObserver.setSuperType(CorePackage.theInstance.getObserver());
		mAbstractInformation.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mAbstractFeature.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mVariable.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mJavaScriptCondition.getInterfaces().add(CorePackage.theInstance.getCondition());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getMDIdentifier(){
		if (mMDIdentifier == null){
			mMDIdentifier = UCoreMetaRepository.getUClass(MDIdentifier.class);
		}
		return mMDIdentifier;
	}
	/**
	* @generated
	*/
	public UClass getRSIdentifier(){
		if (mRSIdentifier == null){
			mRSIdentifier = UCoreMetaRepository.getUClass(RSIdentifier.class);
		}
		return mRSIdentifier;
	}



	/**
	* @generated
	*/
	public UClass getModelPath(){
		if (mModelPath == null){
			mModelPath = UCoreMetaRepository.getUClass(ModelPath.class);
		}
		return mModelPath;
	}
	/**
	* @generated
	*/
	public UClass getNamedElement(){
		if (mNamedElement == null){
			mNamedElement = UCoreMetaRepository.getUClass(NamedElement.class);
		}
		return mNamedElement;
	}
	/**
	* @generated
	*/
	public UClass getIdentifiedObject(){
		if (mIdentifiedObject == null){
			mIdentifiedObject = UCoreMetaRepository.getUClass(IdentifiedObject.class);
		}
		return mIdentifiedObject;
	}
	/**
	* @generated
	*/
	public UInterface getCondition(){
		if (mCondition == null){
			mCondition = UCoreMetaRepository.getUInterface(Condition.class);
		}
		return mCondition;
	}



	/**
	* @generated
	*/
	public UClass getIdentifiedReference(){
		if (mIdentifiedReference == null){
			mIdentifiedReference = UCoreMetaRepository.getUClass(IdentifiedReference.class);
		}
		return mIdentifiedReference;
	}
	/**
	* @generated
	*/
	public UClass getAbstractInformation(){
		if (mAbstractInformation == null){
			mAbstractInformation = UCoreMetaRepository.getUClass(AbstractInformation.class);
		}
		return mAbstractInformation;
	}



	/**
	* @generated
	*/
	public UInterface getModelReference(){
		if (mModelReference == null){
			mModelReference = UCoreMetaRepository.getUInterface(ModelReference.class);
		}
		return mModelReference;
	}
	/**
	* @generated
	*/
	public UClass getAbstractFeature(){
		if (mAbstractFeature == null){
			mAbstractFeature = UCoreMetaRepository.getUClass(AbstractFeature.class);
		}
		return mAbstractFeature;
	}



	/**
	* @generated
	*/
	public UClass getObserver(){
		if (mObserver == null){
			mObserver = UCoreMetaRepository.getUClass(Observer.class);
		}
		return mObserver;
	}



	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getMDIdentifier_code(){
		if (mMDIdentifier_code == null)
			mMDIdentifier_code = getMDIdentifier().getFeature("code");
		return mMDIdentifier_code;
	}



	/**
	* @generated
	*/
	public UClass getMemberObserver(){
		if (mMemberObserver == null){
			mMemberObserver = UCoreMetaRepository.getUClass(MemberObserver.class);
		}
		return mMemberObserver;
	}



	/**
	* @generated
	*/
	public UClass getJavaScriptCondition(){
		if (mJavaScriptCondition == null){
			mJavaScriptCondition = UCoreMetaRepository.getUClass(JavaScriptCondition.class);
		}
		return mJavaScriptCondition;
	}



	/**
	* @generated
	*/
	public UClass getVariable(){
		if (mVariable == null){
			mVariable = UCoreMetaRepository.getUClass(Variable.class);
		}
		return mVariable;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRSIdentifier_version(){
		if (mRSIdentifier_version == null)
			mRSIdentifier_version = getRSIdentifier().getFeature("version");
		return mRSIdentifier_version;
	}



	/**
	* @generated
	*/
	public UClass getFeatureChangeObserver(){
		if (mFeatureChangeObserver == null){
			mFeatureChangeObserver = UCoreMetaRepository.getUClass(FeatureChangeObserver.class);
		}
		return mFeatureChangeObserver;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRSIdentifier_codeSpace(){
		if (mRSIdentifier_codeSpace == null)
			mRSIdentifier_codeSpace = getRSIdentifier().getFeature("codeSpace");
		return mRSIdentifier_codeSpace;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getNamedElement_name(){
		if (mNamedElement_name == null)
			mNamedElement_name = getNamedElement().getFeature("name");
		return mNamedElement_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getIdentifiedObject_allias(){
		if (mIdentifiedObject_allias == null)
			mIdentifiedObject_allias = getIdentifiedObject().getFeature("allias");
		return mIdentifiedObject_allias;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getIdentifiedObject_remarks(){
		if (mIdentifiedObject_remarks == null)
			mIdentifiedObject_remarks = getIdentifiedObject().getFeature("remarks");
		return mIdentifiedObject_remarks;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getModelPath_rootInstance(){
		if (mModelPath_rootInstance == null)
			mModelPath_rootInstance = getModelPath().getFeature("rootInstance");
		return mModelPath_rootInstance;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getIdentifiedObject_observers(){
		if (mIdentifiedObject_observers == null)
			mIdentifiedObject_observers = getIdentifiedObject().getFeature("observers");
		return mIdentifiedObject_observers;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getModelPath_pointerString(){
		if (mModelPath_pointerString == null)
			mModelPath_pointerString = getModelPath().getFeature("pointerString");
		return mModelPath_pointerString;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getIdentifiedObject_identifier(){
		if (mIdentifiedObject_identifier == null)
			mIdentifiedObject_identifier = getIdentifiedObject().getFeature("identifier");
		return mIdentifiedObject_identifier;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAbstractFeature_informationObjects(){
		if (mAbstractFeature_informationObjects == null)
			mAbstractFeature_informationObjects = getAbstractFeature().getFeature("informationObjects");
		return mAbstractFeature_informationObjects;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getObserver_operations(){
		if (mObserver_operations == null)
			mObserver_operations = getObserver().getFeature("operations");
		return mObserver_operations;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getFeatureChangeObserver_reference(){
		if (mFeatureChangeObserver_reference == null)
			mFeatureChangeObserver_reference = getFeatureChangeObserver().getFeature("reference");
		return mFeatureChangeObserver_reference;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getIdentifiedReference_referencedIdentifier(){
		if (mIdentifiedReference_referencedIdentifier == null)
			mIdentifiedReference_referencedIdentifier = getIdentifiedReference().getFeature("referencedIdentifier");
		return mIdentifiedReference_referencedIdentifier;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getMemberObserver_pointerString(){
		if (mMemberObserver_pointerString == null)
			mMemberObserver_pointerString = getMemberObserver().getFeature("pointerString");
		return mMemberObserver_pointerString;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getJavaScriptCondition_script(){
		if (mJavaScriptCondition_script == null)
			mJavaScriptCondition_script = getJavaScriptCondition().getFeature("script");
		return mJavaScriptCondition_script;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getJavaScriptCondition_reference(){
		if (mJavaScriptCondition_reference == null)
			mJavaScriptCondition_reference = getJavaScriptCondition().getFeature("reference");
		return mJavaScriptCondition_reference;
	}
}
