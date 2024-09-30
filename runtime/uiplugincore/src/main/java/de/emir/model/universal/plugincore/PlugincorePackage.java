package de.emir.model.universal.plugincore;

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

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.RuntimePackage;

/**
 *	@generated 
 */
public class PlugincorePackage  
{
	
	/**
	 * @generated
	 */
	public static PlugincorePackage theInstance = new PlugincorePackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier ConfigVariable
		*/
		UClass ConfigVariable = PlugincorePackage.theInstance.getConfigVariable();
		
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mConfigVariable = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	/**
	 * @generated
	 */
	public static PlugincorePackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package PlugincorePackage ...", 1);
		theInstance = new PlugincorePackage();
		//initialize referenced models
		UCoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.plugincore");
		p.getContent().add(theInstance.mConfigVariable);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package PlugincorePackage initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mConfigVariable = UMetaBuilder.manual().createClass("ConfigVariable", true, ConfigVariable.class, ConfigVariableImpl.class);
			mConfigVariable.setDocumentation("\n * An ConfigVariable can be used to store somewhat complex types which are often needed.\n * The variable's name and documentation are to be taken from the UNamedElement\n ");
		
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
		}
		{ //assign features
		}
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mConfigVariable.setSuperType(RuntimePackage.theInstance.getUNamedElement());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getConfigVariable(){
		if (mConfigVariable == null){
			mConfigVariable = UCoreMetaRepository.getUClass(ConfigVariable.class);
		}
		return mConfigVariable;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
}
