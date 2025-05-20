package de.emir.model.universal.core.var;

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

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.core.var.BoolVariable;
import de.emir.model.universal.core.var.DoubleVariable;
import de.emir.model.universal.core.var.FloatVariable;
import de.emir.model.universal.core.var.IntVariable;
import de.emir.model.universal.core.var.ObjectVariable;
import de.emir.model.universal.core.var.PointerVariable;
import de.emir.model.universal.core.var.StringVariable;
import de.emir.model.universal.core.var.UObjectVariable;
import de.emir.model.universal.core.var.impl.BoolVariableImpl;
import de.emir.model.universal.core.var.impl.DoubleVariableImpl;
import de.emir.model.universal.core.var.impl.FloatVariableImpl;
import de.emir.model.universal.core.var.impl.IntVariableImpl;
import de.emir.model.universal.core.var.impl.ObjectVariableImpl;
import de.emir.model.universal.core.var.impl.PointerVariableImpl;
import de.emir.model.universal.core.var.impl.StringVariableImpl;
import de.emir.model.universal.core.var.impl.UObjectVariableImpl;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 *	@generated 
 */
public class VarPackage  
{
	
	/**
	 * @generated
	 */
	public static VarPackage theInstance = new VarPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier IntVariable
		*/
		UClass IntVariable = VarPackage.theInstance.getIntVariable();
		/**
		* @generated
		* @return meta type for classifier BoolVariable
		*/
		UClass BoolVariable = VarPackage.theInstance.getBoolVariable();
		/**
		* @generated
		* @return meta type for classifier FloatVariable
		*/
		UClass FloatVariable = VarPackage.theInstance.getFloatVariable();
		/**
		* @generated
		* @return meta type for classifier DoubleVariable
		*/
		UClass DoubleVariable = VarPackage.theInstance.getDoubleVariable();
		/**
		* @generated
		* @return meta type for classifier StringVariable
		*/
		UClass StringVariable = VarPackage.theInstance.getStringVariable();
		/**
		* @generated
		* @return meta type for classifier ObjectVariable
		*/
		UClass ObjectVariable = VarPackage.theInstance.getObjectVariable();
		/**
		* @generated
		* @return meta type for classifier UObjectVariable
		*/
		UClass UObjectVariable = VarPackage.theInstance.getUObjectVariable();
		/**
		* @generated
		* @return meta type for classifier PointerVariable
		*/
		UClass PointerVariable = VarPackage.theInstance.getPointerVariable();
		
		/**
		 * @generated
		 * @return feature descriptor value in type IntVariable
		 */
		 UStructuralFeature IntVariable_value = VarPackage.theInstance.getIntVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type BoolVariable
		 */
		 UStructuralFeature BoolVariable_value = VarPackage.theInstance.getBoolVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type FloatVariable
		 */
		 UStructuralFeature FloatVariable_value = VarPackage.theInstance.getFloatVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type DoubleVariable
		 */
		 UStructuralFeature DoubleVariable_value = VarPackage.theInstance.getDoubleVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type StringVariable
		 */
		 UStructuralFeature StringVariable_value = VarPackage.theInstance.getStringVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type ObjectVariable
		 */
		 UStructuralFeature ObjectVariable_value = VarPackage.theInstance.getObjectVariable_value();
		/**
		 * @generated
		 * @return feature descriptor value in type UObjectVariable
		 */
		 UStructuralFeature UObjectVariable_value = VarPackage.theInstance.getUObjectVariable_value();
		/**
		 * @generated
		 * @return feature descriptor path in type PointerVariable
		 */
		 UStructuralFeature PointerVariable_path = VarPackage.theInstance.getPointerVariable_path();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mIntVariable = null;
	/**
	* @generated
	*/
	private UClass mBoolVariable = null;
	/**
	* @generated
	*/
	private UClass mFloatVariable = null;
	/**
	* @generated
	*/
	private UClass mDoubleVariable = null;
	/**
	* @generated
	*/
	private UClass mStringVariable = null;
	/**
	* @generated
	*/
	private UClass mObjectVariable = null;
	/**
	* @generated
	*/
	private UClass mUObjectVariable = null;
	/**
	* @generated
	*/
	private UClass mPointerVariable = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier IntVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mIntVariable_value = null;
	
	//Features for classifier BoolVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mBoolVariable_value = null;
	
	//Features for classifier FloatVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mFloatVariable_value = null;
	
	//Features for classifier DoubleVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mDoubleVariable_value = null;
	
	//Features for classifier StringVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mStringVariable_value = null;
	
	//Features for classifier ObjectVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mObjectVariable_value = null;
	
	//Features for classifier UObjectVariable
	/**
	 * @generated
	 */
	private UStructuralFeature mUObjectVariable_value = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPointerVariable_path = null;
	/**
	 * @generated
	 */
	public static VarPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package VarPackage ...");
		theInstance = new VarPackage();
		//initialize referenced models
		UCoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.core.var");
		p.getContent().add(theInstance.mIntVariable);
		p.getContent().add(theInstance.mBoolVariable);
		p.getContent().add(theInstance.mFloatVariable);
		p.getContent().add(theInstance.mDoubleVariable);
		p.getContent().add(theInstance.mStringVariable);
		p.getContent().add(theInstance.mObjectVariable);
		p.getContent().add(theInstance.mUObjectVariable);
		p.getContent().add(theInstance.mPointerVariable);
		p.freeze();
		
		
		
		ULog.debug("... package VarPackage initialized");
		
		return theInstance;
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mIntVariable = UMetaBuilder.manual().createClass("IntVariable", false, IntVariable.class, IntVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mIntVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new IntVariableImpl();
				}
			});
		
		mBoolVariable = UMetaBuilder.manual().createClass("BoolVariable", false, BoolVariable.class, BoolVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mBoolVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new BoolVariableImpl();
				}
			});
		
		mFloatVariable = UMetaBuilder.manual().createClass("FloatVariable", false, FloatVariable.class, FloatVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mFloatVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new FloatVariableImpl();
				}
			});
		
		mDoubleVariable = UMetaBuilder.manual().createClass("DoubleVariable", false, DoubleVariable.class, DoubleVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDoubleVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DoubleVariableImpl();
				}
			});
		
		mStringVariable = UMetaBuilder.manual().createClass("StringVariable", false, StringVariable.class, StringVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mStringVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new StringVariableImpl();
				}
			});
		
		mObjectVariable = UMetaBuilder.manual().createClass("ObjectVariable", false, ObjectVariable.class, ObjectVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObjectVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObjectVariableImpl();
				}
			});
		
		mUObjectVariable = UMetaBuilder.manual().createClass("UObjectVariable", false, UObjectVariable.class, UObjectVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mUObjectVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new UObjectVariableImpl();
				}
			});
		
		mPointerVariable = UMetaBuilder.manual().createClass("PointerVariable", false, PointerVariable.class, PointerVariableImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPointerVariable, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PointerVariableImpl();
				}
			});
			mPointerVariable.setDocumentation(" The PointerVariable is similar to an UObjectVariable (if pointed to an primitive type may also to the others)\r\n * however a pointer variable can never store its own values and thus is a reference to another value. \r\n ");
		
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of IntVariable
			mIntVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mIntVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IntVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((IntVariable)instance).setValue((int)value); } }
				);
			
			//Features of BoolVariable
			mBoolVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(boolean.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mBoolVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((BoolVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((BoolVariable)instance).setValue((boolean)value); } }
				);
			
			//Features of FloatVariable
			mFloatVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(float.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mFloatVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((FloatVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((FloatVariable)instance).setValue((float)value); } }
				);
			
			//Features of DoubleVariable
			mDoubleVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDoubleVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DoubleVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DoubleVariable)instance).setValue((double)value); } }
				);
			
			//Features of StringVariable
			mStringVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mStringVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((StringVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((StringVariable)instance).setValue((String)value); } }
				);
			
			//Features of ObjectVariable
			mObjectVariable_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(Object.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mObjectVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ObjectVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ObjectVariable)instance).setValue((Object)value); } }
				);
			
			//Features of UObjectVariable
			mUObjectVariable_value = UMetaBuilder.manual().createFeature("value", RuntimePackage.theInstance.getUObject(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mUObjectVariable_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((UObjectVariable)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((UObjectVariable)instance).setValue((UObject)value); } }
				);
			
			//Features of PointerVariable
			mPointerVariable_path = UMetaBuilder.manual().createFeature("path", CorePackage.theInstance.getModelPath(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPointerVariable_path, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PointerVariable)instance).getPath(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PointerVariable)instance).setPath((ModelPath)value); } }
				);
				mPointerVariable_path.setDocumentation(" path to the represented value");
			
		}
		{ //assign features
			mIntVariable.getStructuralFeatures().add(mIntVariable_value);
			mBoolVariable.getStructuralFeatures().add(mBoolVariable_value);
			mFloatVariable.getStructuralFeatures().add(mFloatVariable_value);
			mDoubleVariable.getStructuralFeatures().add(mDoubleVariable_value);
			mStringVariable.getStructuralFeatures().add(mStringVariable_value);
			mObjectVariable.getStructuralFeatures().add(mObjectVariable_value);
			mUObjectVariable.getStructuralFeatures().add(mUObjectVariable_value);
			mPointerVariable.getStructuralFeatures().add(mPointerVariable_path);
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
		mIntVariable.setSuperType(CorePackage.theInstance.getVariable());
		mBoolVariable.setSuperType(CorePackage.theInstance.getVariable());
		mFloatVariable.setSuperType(CorePackage.theInstance.getVariable());
		mDoubleVariable.setSuperType(CorePackage.theInstance.getVariable());
		mStringVariable.setSuperType(CorePackage.theInstance.getVariable());
		mObjectVariable.setSuperType(CorePackage.theInstance.getVariable());
		mUObjectVariable.setSuperType(CorePackage.theInstance.getVariable());
		mPointerVariable.setSuperType(CorePackage.theInstance.getVariable());
		
	}

	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getIntVariable(){
		if (mIntVariable == null){
			mIntVariable = UCoreMetaRepository.getUClass(IntVariable.class);
		}
		return mIntVariable;
	}
	/**
	* @generated
	*/
	public UClass getBoolVariable(){
		if (mBoolVariable == null){
			mBoolVariable = UCoreMetaRepository.getUClass(BoolVariable.class);
		}
		return mBoolVariable;
	}
	/**
	* @generated
	*/
	public UClass getFloatVariable(){
		if (mFloatVariable == null){
			mFloatVariable = UCoreMetaRepository.getUClass(FloatVariable.class);
		}
		return mFloatVariable;
	}
	/**
	* @generated
	*/
	public UClass getDoubleVariable(){
		if (mDoubleVariable == null){
			mDoubleVariable = UCoreMetaRepository.getUClass(DoubleVariable.class);
		}
		return mDoubleVariable;
	}
	/**
	* @generated
	*/
	public UClass getStringVariable(){
		if (mStringVariable == null){
			mStringVariable = UCoreMetaRepository.getUClass(StringVariable.class);
		}
		return mStringVariable;
	}
	/**
	* @generated
	*/
	public UClass getObjectVariable(){
		if (mObjectVariable == null){
			mObjectVariable = UCoreMetaRepository.getUClass(ObjectVariable.class);
		}
		return mObjectVariable;
	}
	/**
	* @generated
	*/
	public UClass getUObjectVariable(){
		if (mUObjectVariable == null){
			mUObjectVariable = UCoreMetaRepository.getUClass(UObjectVariable.class);
		}
		return mUObjectVariable;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getIntVariable_value(){
		if (mIntVariable_value == null)
			mIntVariable_value = getIntVariable().getFeature("value");
		return mIntVariable_value;
	}

	/**
	* @generated
	*/
	public UClass getPointerVariable(){
		if (mPointerVariable == null){
			mPointerVariable = UCoreMetaRepository.getUClass(PointerVariable.class);
		}
		return mPointerVariable;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getBoolVariable_value(){
		if (mBoolVariable_value == null)
			mBoolVariable_value = getBoolVariable().getFeature("value");
		return mBoolVariable_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getFloatVariable_value(){
		if (mFloatVariable_value == null)
			mFloatVariable_value = getFloatVariable().getFeature("value");
		return mFloatVariable_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDoubleVariable_value(){
		if (mDoubleVariable_value == null)
			mDoubleVariable_value = getDoubleVariable().getFeature("value");
		return mDoubleVariable_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getStringVariable_value(){
		if (mStringVariable_value == null)
			mStringVariable_value = getStringVariable().getFeature("value");
		return mStringVariable_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getObjectVariable_value(){
		if (mObjectVariable_value == null)
			mObjectVariable_value = getObjectVariable().getFeature("value");
		return mObjectVariable_value;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getPointerVariable_path(){
		if (mPointerVariable_path == null)
			mPointerVariable_path = getPointerVariable().getFeature("path");
		return mPointerVariable_path;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getUObjectVariable_value(){
		if (mUObjectVariable_value == null)
			mUObjectVariable_value = getUObjectVariable().getFeature("value");
		return mUObjectVariable_value;
	}
}
