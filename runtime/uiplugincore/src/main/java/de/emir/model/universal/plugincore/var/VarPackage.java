package de.emir.model.universal.plugincore.var;

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

import de.emir.model.universal.plugincore.PlugincorePackage;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.ConfigArray;
import de.emir.model.universal.plugincore.var.ConfigColor;
import de.emir.model.universal.plugincore.var.ConfigRectangle;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.model.universal.plugincore.var.ConfigPair;
import de.emir.model.universal.plugincore.var.ConfigPairSimple;
import de.emir.model.universal.plugincore.var.ConfigMap;
import de.emir.model.universal.plugincore.var.EditorKeyBinding;
import de.emir.model.universal.plugincore.var.GlobalKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.KeyBindings;
import de.emir.model.universal.plugincore.var.impl.ConfigColorImpl;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaAddKeyBinding;
import de.emir.model.universal.plugincore.var.impl.ConfigRectangleImpl;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaChangeKeyBinding;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaDeleteKeyBinding;
import de.emir.model.universal.plugincore.var.ViewKeyBinding;
import de.emir.model.universal.plugincore.var.impl.AbstractKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigArrayImpl;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.model.universal.plugincore.var.impl.ConfigObjectImpl;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.model.universal.plugincore.var.impl.ConfigPairSimpleImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigPairImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigMapImpl;
import de.emir.model.universal.plugincore.var.impl.GlobalKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.KeyBindingsImpl;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaDeleteKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaAddKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.EditorKeyBindingImpl;
import java.util.List;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaChangeKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.ViewKeyBindingImpl;
import java.util.Map;
import de.emir.tuml.ucore.runtime.RuntimePackage;

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
		* @return meta type for classifier ConfigColor
		*/
		UClass ConfigColor = VarPackage.theInstance.getConfigColor();
		/**
		* @generated
		* @return meta type for classifier ConfigRectangle
		*/
		UClass ConfigRectangle = VarPackage.theInstance.getConfigRectangle();
		/**
		* @generated
		* @return meta type for classifier ConfigObject
		*/
		UClass ConfigObject = VarPackage.theInstance.getConfigObject();
		/**
		* @generated
		* @return meta type for classifier ConfigPairSimple
		*/
		UClass ConfigPairSimple = VarPackage.theInstance.getConfigPairSimple();
		/**
		* @generated
		* @return meta type for classifier ConfigPair
		*/
		UClass ConfigPair = VarPackage.theInstance.getConfigPair();
		/**
		* @generated
		* @return meta type for classifier ConfigArray
		*/
		UClass ConfigArray = VarPackage.theInstance.getConfigArray();
		/**
		* @generated
		* @return meta type for classifier ConfigMap
		*/
		UClass ConfigMap = VarPackage.theInstance.getConfigMap();
		/**
		* @generated
		* @return meta type for classifier AbstractKeyBinding
		*/
		UClass AbstractKeyBinding = VarPackage.theInstance.getAbstractKeyBinding();
		/**
		* @generated
		* @return meta type for classifier EditorKeyBinding
		*/
		UClass EditorKeyBinding = VarPackage.theInstance.getEditorKeyBinding();
		/**
		* @generated
		* @return meta type for classifier GlobalKeyBinding
		*/
		UClass GlobalKeyBinding = VarPackage.theInstance.getGlobalKeyBinding();
		
		/**
		 * @generated
		 * @return feature descriptor red in type ConfigColor
		 */
		 UStructuralFeature ConfigColor_red = VarPackage.theInstance.getConfigColor_red();
		/**
		* @generated
		* @return meta type for classifier ViewKeyBinding
		*/
		UClass ViewKeyBinding = VarPackage.theInstance.getViewKeyBinding();
		/**
		* @generated
		* @return meta type for classifier UserDefinedDeltaAddKeyBinding
		*/
		UClass UserDefinedDeltaAddKeyBinding = VarPackage.theInstance.getUserDefinedDeltaAddKeyBinding();
		/**
		 * @generated
		 * @return feature descriptor green in type ConfigColor
		 */
		 UStructuralFeature ConfigColor_green = VarPackage.theInstance.getConfigColor_green();
		/**
		 * @generated
		 * @return meta type for interface IUserDefinedDelta
		 */
		UInterface IUserDefinedDelta = VarPackage.theInstance.getIUserDefinedDelta();
		/**
		* @generated
		* @return meta type for classifier KeyBindings
		*/
		UClass KeyBindings = VarPackage.theInstance.getKeyBindings();
		/**
		* @generated
		* @return meta type for classifier UserDefinedDeltaDeleteKeyBinding
		*/
		UClass UserDefinedDeltaDeleteKeyBinding = VarPackage.theInstance.getUserDefinedDeltaDeleteKeyBinding();
		/**
		* @generated
		* @return meta type for classifier UserDefinedDeltaChangeKeyBinding
		*/
		UClass UserDefinedDeltaChangeKeyBinding = VarPackage.theInstance.getUserDefinedDeltaChangeKeyBinding();
		/**
		 * @generated
		 * @return feature descriptor blue in type ConfigColor
		 */
		 UStructuralFeature ConfigColor_blue = VarPackage.theInstance.getConfigColor_blue();
		/**
		 * @generated
		 * @return feature descriptor alpha in type ConfigColor
		 */
		 UStructuralFeature ConfigColor_alpha = VarPackage.theInstance.getConfigColor_alpha();
		/**
		 * @generated
		 * @return feature descriptor x in type ConfigRectangle
		 */
		 UStructuralFeature ConfigRectangle_x = VarPackage.theInstance.getConfigRectangle_x();
		/**
		 * @generated
		 * @return feature descriptor y in type ConfigRectangle
		 */
		 UStructuralFeature ConfigRectangle_y = VarPackage.theInstance.getConfigRectangle_y();
		/**
		 * @generated
		 * @return feature descriptor width in type ConfigRectangle
		 */
		 UStructuralFeature ConfigRectangle_width = VarPackage.theInstance.getConfigRectangle_width();
		/**
		 * @generated
		 * @return feature descriptor height in type ConfigRectangle
		 */
		 UStructuralFeature ConfigRectangle_height = VarPackage.theInstance.getConfigRectangle_height();
		/**
		 * @generated
		 * @return feature descriptor value in type ConfigObject
		 */
		 UStructuralFeature ConfigObject_value = VarPackage.theInstance.getConfigObject_value();
		/**
		 * @generated
		 * @return feature descriptor key in type ConfigPair
		 */
		 UStructuralFeature ConfigPair_key = VarPackage.theInstance.getConfigPair_key();
		/**
		 * @generated
		 * @return feature descriptor key in type ConfigPairSimple
		 */
		 UStructuralFeature ConfigPairSimple_key = VarPackage.theInstance.getConfigPairSimple_key();
		/**
		 * @generated
		 * @return feature descriptor value in type ConfigPairSimple
		 */
		 UStructuralFeature ConfigPairSimple_value = VarPackage.theInstance.getConfigPairSimple_value();
		/**
		 * @generated
		 * @return feature descriptor value in type ConfigPair
		 */
		 UStructuralFeature ConfigPair_value = VarPackage.theInstance.getConfigPair_value();
		/**
		 * @generated
		 * @return feature descriptor array in type ConfigArray
		 */
		 UStructuralFeature ConfigArray_array = VarPackage.theInstance.getConfigArray_array();
		/**
		 * @generated
		 * @return feature descriptor map in type ConfigMap
		 */
		 UStructuralFeature ConfigMap_map = VarPackage.theInstance.getConfigMap_map();
		/**
		 * @generated
		 * @return feature descriptor editorID in type EditorKeyBinding
		 */
		 UStructuralFeature EditorKeyBinding_editorID = VarPackage.theInstance.getEditorKeyBinding_editorID();
		/**
		 * @generated
		 * @return feature descriptor key in type AbstractKeyBinding
		 */
		 UStructuralFeature AbstractKeyBinding_key = VarPackage.theInstance.getAbstractKeyBinding_key();
		/**
		 * @generated
		 * @return feature descriptor commandID in type AbstractKeyBinding
		 */
		 UStructuralFeature AbstractKeyBinding_commandID = VarPackage.theInstance.getAbstractKeyBinding_commandID();
		/**
		 * @generated
		 * @return feature descriptor mapSimple in type ConfigMap
		 */
		 UStructuralFeature ConfigMap_mapSimple = VarPackage.theInstance.getConfigMap_mapSimple();
		/**
		 * @generated
		 * @return feature descriptor viewID in type ViewKeyBinding
		 */
		 UStructuralFeature ViewKeyBinding_viewID = VarPackage.theInstance.getViewKeyBinding_viewID();
		/**
		 * @generated
		 * @return feature descriptor newBinding in type UserDefinedDeltaAddKeyBinding
		 */
		 UStructuralFeature UserDefinedDeltaAddKeyBinding_newBinding = VarPackage.theInstance.getUserDefinedDeltaAddKeyBinding_newBinding();
		/**
		 * @generated
		 * @return feature descriptor oldBinding in type UserDefinedDeltaDeleteKeyBinding
		 */
		 UStructuralFeature UserDefinedDeltaDeleteKeyBinding_oldBinding = VarPackage.theInstance.getUserDefinedDeltaDeleteKeyBinding_oldBinding();
		/**
		 * @generated
		 * @return feature descriptor remove in type UserDefinedDeltaChangeKeyBinding
		 */
		 UStructuralFeature UserDefinedDeltaChangeKeyBinding_remove = VarPackage.theInstance.getUserDefinedDeltaChangeKeyBinding_remove();
		/**
		 * @generated
		 * @return feature descriptor add in type UserDefinedDeltaChangeKeyBinding
		 */
		 UStructuralFeature UserDefinedDeltaChangeKeyBinding_add = VarPackage.theInstance.getUserDefinedDeltaChangeKeyBinding_add();
		/**
		 * @generated
		 * @return feature descriptor deltas in type KeyBindings
		 */
		 UStructuralFeature KeyBindings_deltas = VarPackage.theInstance.getKeyBindings_deltas();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mConfigColor = null;
	/**
	* @generated
	*/
	private UClass mConfigRectangle = null;
	/**
	* @generated
	*/
	private UClass mConfigObject = null;
	/**
	* @generated
	*/
	private UClass mConfigPairSimple = null;
	/**
	* @generated
	*/
	private UClass mConfigPair = null;
	/**
	* @generated
	*/
	private UClass mConfigArray = null;
	/**
	* @generated
	*/
	private UClass mConfigMap = null;
	/**
	* @generated
	*/
	private UClass mAbstractKeyBinding = null;
	/**
	* @generated
	*/
	private UClass mEditorKeyBinding = null;
	/**
	* @generated
	*/
	private UClass mGlobalKeyBinding = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier ConfigColor
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigColor_red = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigColor_green = null;
	/**
	* @generated
	*/
	private UClass mViewKeyBinding = null;
	/**
	 * @generated
	 */
	private UInterface mIUserDefinedDelta = null;
	/**
	* @generated
	*/
	private UClass mUserDefinedDeltaDeleteKeyBinding = null;
	/**
	* @generated
	*/
	private UClass mKeyBindings = null;
	/**
	* @generated
	*/
	private UClass mUserDefinedDeltaAddKeyBinding = null;
	/**
	* @generated
	*/
	private UClass mUserDefinedDeltaChangeKeyBinding = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigColor_blue = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigColor_alpha = null;
	
	//Features for classifier ConfigRectangle
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigRectangle_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigRectangle_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigRectangle_width = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigRectangle_height = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigObject_value = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigPairSimple_key = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigPairSimple_value = null;
	
	
	
	
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
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.plugincore.var");
		p.getContent().add(theInstance.mConfigColor);
		p.getContent().add(theInstance.mConfigRectangle);
		p.getContent().add(theInstance.mConfigObject);
		p.getContent().add(theInstance.mConfigPairSimple);
		p.getContent().add(theInstance.mConfigPair);
		p.getContent().add(theInstance.mConfigArray);
		p.getContent().add(theInstance.mConfigMap);
		p.getContent().add(theInstance.mAbstractKeyBinding);
		p.getContent().add(theInstance.mEditorKeyBinding);
		p.getContent().add(theInstance.mGlobalKeyBinding);
		p.getContent().add(theInstance.mViewKeyBinding);
		p.getContent().add(theInstance.mIUserDefinedDelta);
		p.getContent().add(theInstance.mUserDefinedDeltaAddKeyBinding);
		p.getContent().add(theInstance.mUserDefinedDeltaDeleteKeyBinding);
		p.getContent().add(theInstance.mUserDefinedDeltaChangeKeyBinding);
		p.getContent().add(theInstance.mKeyBindings);
		p.freeze();
		
		
		
		ULog.debug("... package VarPackage initialized");
		
		return theInstance;
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mConfigColor = UMetaBuilder.manual().createClass("ConfigColor", false, ConfigColor.class, ConfigColorImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigColor, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigColorImpl();
				}
			});
		
		mConfigRectangle = UMetaBuilder.manual().createClass("ConfigRectangle", false, ConfigRectangle.class, ConfigRectangleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigRectangle, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigRectangleImpl();
				}
			});
		
		mConfigObject = UMetaBuilder.manual().createClass("ConfigObject", false, ConfigObject.class, ConfigObjectImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigObject, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigObjectImpl();
				}
			});
		
		mConfigPairSimple = UMetaBuilder.manual().createClass("ConfigPairSimple", false, ConfigPairSimple.class, ConfigPairSimpleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigPairSimple, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigPairSimpleImpl();
				}
			});
		
		mConfigPair = UMetaBuilder.manual().createClass("ConfigPair", false, ConfigPair.class, ConfigPairImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigPair, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigPairImpl();
				}
			});
		
		mConfigArray = UMetaBuilder.manual().createClass("ConfigArray", false, ConfigArray.class, ConfigArrayImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigArray, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigArrayImpl();
				}
			});
		
		mConfigMap = UMetaBuilder.manual().createClass("ConfigMap", false, ConfigMap.class, ConfigMapImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mConfigMap, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ConfigMapImpl();
				}
			});
		
		mAbstractKeyBinding = UMetaBuilder.manual().createClass("AbstractKeyBinding", true, AbstractKeyBinding.class, AbstractKeyBindingImpl.class);
		
		mEditorKeyBinding = UMetaBuilder.manual().createClass("EditorKeyBinding", false, EditorKeyBinding.class, EditorKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEditorKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EditorKeyBindingImpl();
				}
			});
		
		mGlobalKeyBinding = UMetaBuilder.manual().createClass("GlobalKeyBinding", false, GlobalKeyBinding.class, GlobalKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGlobalKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GlobalKeyBindingImpl();
				}
			});
		
		mViewKeyBinding = UMetaBuilder.manual().createClass("ViewKeyBinding", false, ViewKeyBinding.class, ViewKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mViewKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ViewKeyBindingImpl();
				}
			});
		
		mIUserDefinedDelta = UMetaBuilder.manual().createInterface("IUserDefinedDelta", IUserDefinedDelta.class);
		
		mUserDefinedDeltaAddKeyBinding = UMetaBuilder.manual().createClass("UserDefinedDeltaAddKeyBinding", false, UserDefinedDeltaAddKeyBinding.class, UserDefinedDeltaAddKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mUserDefinedDeltaAddKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new UserDefinedDeltaAddKeyBindingImpl();
				}
			});
		
		mUserDefinedDeltaDeleteKeyBinding = UMetaBuilder.manual().createClass("UserDefinedDeltaDeleteKeyBinding", false, UserDefinedDeltaDeleteKeyBinding.class, UserDefinedDeltaDeleteKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mUserDefinedDeltaDeleteKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new UserDefinedDeltaDeleteKeyBindingImpl();
				}
			});
		
		mUserDefinedDeltaChangeKeyBinding = UMetaBuilder.manual().createClass("UserDefinedDeltaChangeKeyBinding", false, UserDefinedDeltaChangeKeyBinding.class, UserDefinedDeltaChangeKeyBindingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mUserDefinedDeltaChangeKeyBinding, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new UserDefinedDeltaChangeKeyBindingImpl();
				}
			});
		
		mKeyBindings = UMetaBuilder.manual().createClass("KeyBindings", false, KeyBindings.class, KeyBindingsImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mKeyBindings, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new KeyBindingsImpl();
				}
			});
		
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigPair_key = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigArray_array = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigMap_mapSimple = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigMap_map = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mConfigPair_value = null;




	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of ConfigColor
			mConfigColor_red = UMetaBuilder.manual().createFeature("red", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigColor_red, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigColor)instance).getRed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigColor)instance).setRed((int)value); } }
				);
			mConfigColor_green = UMetaBuilder.manual().createFeature("green", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigColor_green, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigColor)instance).getGreen(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigColor)instance).setGreen((int)value); } }
				);
			mConfigColor_blue = UMetaBuilder.manual().createFeature("blue", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigColor_blue, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigColor)instance).getBlue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigColor)instance).setBlue((int)value); } }
				);
			mConfigColor_alpha = UMetaBuilder.manual().createFeature("alpha", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigColor_alpha, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigColor)instance).getAlpha(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigColor)instance).setAlpha((int)value); } }
				);
			
			//Features of ConfigRectangle
			mConfigRectangle_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigRectangle_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigRectangle)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigRectangle)instance).setX((int)value); } }
				);
			mConfigRectangle_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigRectangle_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigRectangle)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigRectangle)instance).setY((int)value); } }
				);
			mConfigRectangle_width = UMetaBuilder.manual().createFeature("width", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigRectangle_width, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigRectangle)instance).getWidth(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigRectangle)instance).setWidth((int)value); } }
				);
			mConfigRectangle_height = UMetaBuilder.manual().createFeature("height", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigRectangle_height, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigRectangle)instance).getHeight(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigRectangle)instance).setHeight((int)value); } }
				);
			
			//Features of ConfigObject
			mConfigObject_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigObject_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigObject)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigObject)instance).setValue((String)value); } }
				);
			
			//Features of ConfigPairSimple
			mConfigPairSimple_key = UMetaBuilder.manual().createFeature("key", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigPairSimple_key, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigPairSimple)instance).getKey(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigPairSimple)instance).setKey((String)value); } }
				);
			mConfigPairSimple_value = UMetaBuilder.manual().createFeature("value", VarPackage.theInstance.getConfigObject(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigPairSimple_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigPairSimple)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigPairSimple)instance).setValue((ConfigObject)value); } }
				);
			
			//Features of ConfigPair
			mConfigPair_key = UMetaBuilder.manual().createFeature("key", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigPair_key, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigPair)instance).getKey(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigPair)instance).setKey((String)value); } }
				);
			mConfigPair_value = UMetaBuilder.manual().createFeature("value", PlugincorePackage.theInstance.getConfigVariable(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigPair_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigPair)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ConfigPair)instance).setValue((ConfigVariable)value); } }
				);
			
			//Features of ConfigArray
			mConfigArray_array = UMetaBuilder.manual().createFeature("array", VarPackage.theInstance.getConfigObject(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigArray_array, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigArray)instance).getArray(); } }, 
						null
				);
			
			//Features of ConfigMap
			mConfigMap_map = UMetaBuilder.manual().createFeature("map", VarPackage.theInstance.getConfigPair(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigMap_map, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigMap)instance).getMap(); } }, 
						null
				);
			mConfigMap_mapSimple = UMetaBuilder.manual().createFeature("mapSimple", VarPackage.theInstance.getConfigPairSimple(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mConfigMap_mapSimple, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ConfigMap)instance).getMapSimple(); } }, 
						null
				);
			
			//Features of AbstractKeyBinding
			mAbstractKeyBinding_commandID = UMetaBuilder.manual().createFeature("commandID", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAbstractKeyBinding_commandID, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AbstractKeyBinding)instance).getCommandID(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AbstractKeyBinding)instance).setCommandID((String)value); } }
				);
			mAbstractKeyBinding_key = UMetaBuilder.manual().createFeature("key", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAbstractKeyBinding_key, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AbstractKeyBinding)instance).getKey(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AbstractKeyBinding)instance).setKey((String)value); } }
				);
			
			//Features of EditorKeyBinding
			mEditorKeyBinding_editorID = UMetaBuilder.manual().createFeature("editorID", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEditorKeyBinding_editorID, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EditorKeyBinding)instance).getEditorID(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EditorKeyBinding)instance).setEditorID((String)value); } }
				);
			
			//Features of ViewKeyBinding
			mViewKeyBinding_viewID = UMetaBuilder.manual().createFeature("viewID", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mViewKeyBinding_viewID, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ViewKeyBinding)instance).getViewID(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ViewKeyBinding)instance).setViewID((String)value); } }
				);
			
			//Features of UserDefinedDeltaAddKeyBinding
			mUserDefinedDeltaAddKeyBinding_newBinding = UMetaBuilder.manual().createFeature("newBinding", VarPackage.theInstance.getAbstractKeyBinding(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mUserDefinedDeltaAddKeyBinding_newBinding, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((UserDefinedDeltaAddKeyBinding)instance).getNewBinding(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((UserDefinedDeltaAddKeyBinding)instance).setNewBinding((AbstractKeyBinding)value); } }
				);
			
			//Features of UserDefinedDeltaDeleteKeyBinding
			mUserDefinedDeltaDeleteKeyBinding_oldBinding = UMetaBuilder.manual().createFeature("oldBinding", VarPackage.theInstance.getAbstractKeyBinding(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mUserDefinedDeltaDeleteKeyBinding_oldBinding, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((UserDefinedDeltaDeleteKeyBinding)instance).getOldBinding(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((UserDefinedDeltaDeleteKeyBinding)instance).setOldBinding((AbstractKeyBinding)value); } }
				);
			
			//Features of UserDefinedDeltaChangeKeyBinding
			mUserDefinedDeltaChangeKeyBinding_remove = UMetaBuilder.manual().createFeature("remove", VarPackage.theInstance.getUserDefinedDeltaDeleteKeyBinding(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mUserDefinedDeltaChangeKeyBinding_remove, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((UserDefinedDeltaChangeKeyBinding)instance).getRemove(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((UserDefinedDeltaChangeKeyBinding)instance).setRemove((UserDefinedDeltaDeleteKeyBinding)value); } }
				);
			mUserDefinedDeltaChangeKeyBinding_add = UMetaBuilder.manual().createFeature("add", VarPackage.theInstance.getUserDefinedDeltaAddKeyBinding(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mUserDefinedDeltaChangeKeyBinding_add, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((UserDefinedDeltaChangeKeyBinding)instance).getAdd(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((UserDefinedDeltaChangeKeyBinding)instance).setAdd((UserDefinedDeltaAddKeyBinding)value); } }
				);
			
			//Features of KeyBindings
			mKeyBindings_deltas = UMetaBuilder.manual().createFeature("deltas", VarPackage.theInstance.getIUserDefinedDelta(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mKeyBindings_deltas, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((KeyBindings)instance).getDeltas(); } }, 
						null
				);
			
		}
		{ //assign features
			mConfigColor.getStructuralFeatures().add(mConfigColor_red);
			mConfigColor.getStructuralFeatures().add(mConfigColor_green);
			mConfigColor.getStructuralFeatures().add(mConfigColor_blue);
			mConfigColor.getStructuralFeatures().add(mConfigColor_alpha);
			mConfigRectangle.getStructuralFeatures().add(mConfigRectangle_x);
			mConfigRectangle.getStructuralFeatures().add(mConfigRectangle_y);
			mConfigRectangle.getStructuralFeatures().add(mConfigRectangle_width);
			mConfigRectangle.getStructuralFeatures().add(mConfigRectangle_height);
			mConfigObject.getStructuralFeatures().add(mConfigObject_value);
			mConfigPairSimple.getStructuralFeatures().add(mConfigPairSimple_key);
			mConfigPairSimple.getStructuralFeatures().add(mConfigPairSimple_value);
			mConfigPair.getStructuralFeatures().add(mConfigPair_key);
			mConfigPair.getStructuralFeatures().add(mConfigPair_value);
			mConfigArray.getStructuralFeatures().add(mConfigArray_array);
			mConfigMap.getStructuralFeatures().add(mConfigMap_map);
			mConfigMap.getStructuralFeatures().add(mConfigMap_mapSimple);
			mAbstractKeyBinding.getStructuralFeatures().add(mAbstractKeyBinding_commandID);
			mAbstractKeyBinding.getStructuralFeatures().add(mAbstractKeyBinding_key);
			mEditorKeyBinding.getStructuralFeatures().add(mEditorKeyBinding_editorID);
			mViewKeyBinding.getStructuralFeatures().add(mViewKeyBinding_viewID);
			mUserDefinedDeltaAddKeyBinding.getStructuralFeatures().add(mUserDefinedDeltaAddKeyBinding_newBinding);
			mUserDefinedDeltaDeleteKeyBinding.getStructuralFeatures().add(mUserDefinedDeltaDeleteKeyBinding_oldBinding);
			mUserDefinedDeltaChangeKeyBinding.getStructuralFeatures().add(mUserDefinedDeltaChangeKeyBinding_remove);
			mUserDefinedDeltaChangeKeyBinding.getStructuralFeatures().add(mUserDefinedDeltaChangeKeyBinding_add);
			mKeyBindings.getStructuralFeatures().add(mKeyBindings_deltas);
		}
		
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of ConfigObject
			UOperation operation = null;
			//operation : getValue(String)
			operation = UMetaBuilder.manual().createOperation("getValue", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getValue();
				}
			});
				mConfigObject.getOperations().add(operation);
			//operation : getAsInteger(int)
			operation = UMetaBuilder.manual().createOperation("getAsInteger", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getAsInteger();
				}
			});
				mConfigObject.getOperations().add(operation);
			//operation : getAsDouble(double)
			operation = UMetaBuilder.manual().createOperation("getAsDouble", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getAsDouble();
				}
			});
				mConfigObject.getOperations().add(operation);
			//operation : getAsFloat(float)
			operation = UMetaBuilder.manual().createOperation("getAsFloat", false, TypeUtils.getPrimitiveType(float.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getAsFloat();
				}
			});
				mConfigObject.getOperations().add(operation);
			//operation : getAsLong(long)
			operation = UMetaBuilder.manual().createOperation("getAsLong", false, TypeUtils.getPrimitiveType(long.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getAsLong();
				}
			});
				mConfigObject.getOperations().add(operation);
			//operation : getAsBoolean(boolean)
			operation = UMetaBuilder.manual().createOperation("getAsBoolean", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigObject)instance).getAsBoolean();
				}
			});
				mConfigObject.getOperations().add(operation);
		}
		{		//Operations of ConfigMap
			UOperation operation = null;
			//operation : put(void, String, Object)
			operation = UMetaBuilder.manual().createOperation("put", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((ConfigMap)instance).put((String)parameter[0], (Object)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1, UDirectionType.IN);
				mConfigMap.getOperations().add(operation);
			//operation : put(void, ConfigPair)
			operation = UMetaBuilder.manual().createOperation("put", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((ConfigMap)instance).put((ConfigPair)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "pair", VarPackage.theInstance.getConfigPair(), 0, 1, UDirectionType.IN);
				mConfigMap.getOperations().add(operation);
			//operation : put(void, ConfigPairSimple)
			operation = UMetaBuilder.manual().createOperation("put", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((ConfigMap)instance).put((ConfigPairSimple)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "pair", VarPackage.theInstance.getConfigPairSimple(), 0, 1, UDirectionType.IN);
				mConfigMap.getOperations().add(operation);
			//operation : get(Object, String)
			operation = UMetaBuilder.manual().createOperation("get", false, TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ConfigMap)instance).get((String)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				mConfigMap.getOperations().add(operation);
			//operation : remove(void, String)
			operation = UMetaBuilder.manual().createOperation("remove", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((ConfigMap)instance).remove((String)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				mConfigMap.getOperations().add(operation);
		}
		{		//Operations of AbstractKeyBinding
			UOperation operation = null;
			//operation : equals(boolean, Object)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AbstractKeyBinding)instance).equals((Object)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "obj", TypeUtils.getPrimitiveType(Object.class), 0, 1, UDirectionType.IN);
				mAbstractKeyBinding.getOperations().add(operation);
			//operation : copy(AbstractKeyBinding)
			operation = UMetaBuilder.manual().createOperation("copy", false, VarPackage.theInstance.getAbstractKeyBinding(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AbstractKeyBinding)instance).copy();
				}
			});
				mAbstractKeyBinding.getOperations().add(operation);
		}
		{		//Operations of IUserDefinedDelta
			UOperation operation = null;
			//operation : apply(void, AbstractKeyBinding)
			operation = UMetaBuilder.manual().createOperation("apply", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((IUserDefinedDelta)instance).apply((List<AbstractKeyBinding>)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "bindings", VarPackage.theInstance.getAbstractKeyBinding(), 0, -1, UDirectionType.IN);
				mIUserDefinedDelta.getOperations().add(operation);
		}
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mUserDefinedDeltaAddKeyBinding_newBinding = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mUserDefinedDeltaDeleteKeyBinding_oldBinding = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAbstractKeyBinding_commandID = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mUserDefinedDeltaChangeKeyBinding_remove = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mUserDefinedDeltaChangeKeyBinding_add = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mKeyBindings_deltas = null;




	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mConfigColor.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigRectangle.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigObject.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigPairSimple.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigPair.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigArray.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mConfigMap.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mAbstractKeyBinding.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		mEditorKeyBinding.setSuperType(VarPackage.theInstance.getAbstractKeyBinding());
		mGlobalKeyBinding.setSuperType(VarPackage.theInstance.getAbstractKeyBinding());
		mViewKeyBinding.setSuperType(VarPackage.theInstance.getAbstractKeyBinding());
		mUserDefinedDeltaAddKeyBinding.getInterfaces().add(VarPackage.theInstance.getIUserDefinedDelta());
		mUserDefinedDeltaDeleteKeyBinding.getInterfaces().add(VarPackage.theInstance.getIUserDefinedDelta());
		mUserDefinedDeltaChangeKeyBinding.getInterfaces().add(VarPackage.theInstance.getIUserDefinedDelta());
		mKeyBindings.setSuperType(PlugincorePackage.theInstance.getConfigVariable());
		
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mViewKeyBinding_viewID = null;




	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getConfigColor(){
		if (mConfigColor == null){
			mConfigColor = UCoreMetaRepository.getUClass(ConfigColor.class);
		}
		return mConfigColor;
	}
	/**
	* @generated
	*/
	public UClass getConfigRectangle(){
		if (mConfigRectangle == null){
			mConfigRectangle = UCoreMetaRepository.getUClass(ConfigRectangle.class);
		}
		return mConfigRectangle;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getConfigColor_red(){
		if (mConfigColor_red == null)
			mConfigColor_red = getConfigColor().getFeature("red");
		return mConfigColor_red;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mEditorKeyBinding_editorID = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAbstractKeyBinding_key = null;




	/**
	* @generated
	*/
	public UClass getConfigObject(){
		if (mConfigObject == null){
			mConfigObject = UCoreMetaRepository.getUClass(ConfigObject.class);
		}
		return mConfigObject;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigColor_green(){
		if (mConfigColor_green == null)
			mConfigColor_green = getConfigColor().getFeature("green");
		return mConfigColor_green;
	}

	/**
	* @generated
	*/
	public UClass getConfigPairSimple(){
		if (mConfigPairSimple == null){
			mConfigPairSimple = UCoreMetaRepository.getUClass(ConfigPairSimple.class);
		}
		return mConfigPairSimple;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigColor_blue(){
		if (mConfigColor_blue == null)
			mConfigColor_blue = getConfigColor().getFeature("blue");
		return mConfigColor_blue;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigColor_alpha(){
		if (mConfigColor_alpha == null)
			mConfigColor_alpha = getConfigColor().getFeature("alpha");
		return mConfigColor_alpha;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigRectangle_x(){
		if (mConfigRectangle_x == null)
			mConfigRectangle_x = getConfigRectangle().getFeature("x");
		return mConfigRectangle_x;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigRectangle_y(){
		if (mConfigRectangle_y == null)
			mConfigRectangle_y = getConfigRectangle().getFeature("y");
		return mConfigRectangle_y;
	}

	/**
	* @generated
	*/
	public UClass getConfigPair(){
		if (mConfigPair == null){
			mConfigPair = UCoreMetaRepository.getUClass(ConfigPair.class);
		}
		return mConfigPair;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigRectangle_width(){
		if (mConfigRectangle_width == null)
			mConfigRectangle_width = getConfigRectangle().getFeature("width");
		return mConfigRectangle_width;
	}

	/**
	* @generated
	*/
	public UClass getEditorKeyBinding(){
		if (mEditorKeyBinding == null){
			mEditorKeyBinding = UCoreMetaRepository.getUClass(EditorKeyBinding.class);
		}
		return mEditorKeyBinding;
	}

	/**
	* @generated
	*/
	public UClass getConfigArray(){
		if (mConfigArray == null){
			mConfigArray = UCoreMetaRepository.getUClass(ConfigArray.class);
		}
		return mConfigArray;
	}

	/**
	* @generated
	*/
	public UClass getUserDefinedDeltaDeleteKeyBinding(){
		if (mUserDefinedDeltaDeleteKeyBinding == null){
			mUserDefinedDeltaDeleteKeyBinding = UCoreMetaRepository.getUClass(UserDefinedDeltaDeleteKeyBinding.class);
		}
		return mUserDefinedDeltaDeleteKeyBinding;
	}

	/**
	* @generated
	*/
	public UClass getKeyBindings(){
		if (mKeyBindings == null){
			mKeyBindings = UCoreMetaRepository.getUClass(KeyBindings.class);
		}
		return mKeyBindings;
	}

	/**
	* @generated
	*/
	public UClass getConfigMap(){
		if (mConfigMap == null){
			mConfigMap = UCoreMetaRepository.getUClass(ConfigMap.class);
		}
		return mConfigMap;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getConfigRectangle_height(){
		if (mConfigRectangle_height == null)
			mConfigRectangle_height = getConfigRectangle().getFeature("height");
		return mConfigRectangle_height;
	}

	/**
	* @generated
	*/
	public UClass getViewKeyBinding(){
		if (mViewKeyBinding == null){
			mViewKeyBinding = UCoreMetaRepository.getUClass(ViewKeyBinding.class);
		}
		return mViewKeyBinding;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigPair_key(){
		if (mConfigPair_key == null)
			mConfigPair_key = getConfigPair().getFeature("key");
		return mConfigPair_key;
	}

	/**
	* @generated
	*/
	public UClass getGlobalKeyBinding(){
		if (mGlobalKeyBinding == null){
			mGlobalKeyBinding = UCoreMetaRepository.getUClass(GlobalKeyBinding.class);
		}
		return mGlobalKeyBinding;
	}

	/**
	* @generated
	*/
	public UClass getAbstractKeyBinding(){
		if (mAbstractKeyBinding == null){
			mAbstractKeyBinding = UCoreMetaRepository.getUClass(AbstractKeyBinding.class);
		}
		return mAbstractKeyBinding;
	}

	/**
	* @generated
	*/
	public UClass getUserDefinedDeltaAddKeyBinding(){
		if (mUserDefinedDeltaAddKeyBinding == null){
			mUserDefinedDeltaAddKeyBinding = UCoreMetaRepository.getUClass(UserDefinedDeltaAddKeyBinding.class);
		}
		return mUserDefinedDeltaAddKeyBinding;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigPair_value(){
		if (mConfigPair_value == null)
			mConfigPair_value = getConfigPair().getFeature("value");
		return mConfigPair_value;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigObject_value(){
		if (mConfigObject_value == null)
			mConfigObject_value = getConfigObject().getFeature("value");
		return mConfigObject_value;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigArray_array(){
		if (mConfigArray_array == null)
			mConfigArray_array = getConfigArray().getFeature("array");
		return mConfigArray_array;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigMap_map(){
		if (mConfigMap_map == null)
			mConfigMap_map = getConfigMap().getFeature("map");
		return mConfigMap_map;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigPairSimple_value(){
		if (mConfigPairSimple_value == null)
			mConfigPairSimple_value = getConfigPairSimple().getFeature("value");
		return mConfigPairSimple_value;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigPairSimple_key(){
		if (mConfigPairSimple_key == null)
			mConfigPairSimple_key = getConfigPairSimple().getFeature("key");
		return mConfigPairSimple_key;
	}

	/**
	* @generated
	*/
	public UInterface getIUserDefinedDelta(){
		if (mIUserDefinedDelta == null){
			mIUserDefinedDelta = UCoreMetaRepository.getUInterface(IUserDefinedDelta.class);
		}
		return mIUserDefinedDelta;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getEditorKeyBinding_editorID(){
		if (mEditorKeyBinding_editorID == null)
			mEditorKeyBinding_editorID = getEditorKeyBinding().getFeature("editorID");
		return mEditorKeyBinding_editorID;
	}

	/**
	* @generated
	*/
	public UClass getUserDefinedDeltaChangeKeyBinding(){
		if (mUserDefinedDeltaChangeKeyBinding == null){
			mUserDefinedDeltaChangeKeyBinding = UCoreMetaRepository.getUClass(UserDefinedDeltaChangeKeyBinding.class);
		}
		return mUserDefinedDeltaChangeKeyBinding;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getAbstractKeyBinding_key(){
		if (mAbstractKeyBinding_key == null)
			mAbstractKeyBinding_key = getAbstractKeyBinding().getFeature("key");
		return mAbstractKeyBinding_key;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getAbstractKeyBinding_commandID(){
		if (mAbstractKeyBinding_commandID == null)
			mAbstractKeyBinding_commandID = getAbstractKeyBinding().getFeature("commandID");
		return mAbstractKeyBinding_commandID;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getViewKeyBinding_viewID(){
		if (mViewKeyBinding_viewID == null)
			mViewKeyBinding_viewID = getViewKeyBinding().getFeature("viewID");
		return mViewKeyBinding_viewID;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getConfigMap_mapSimple(){
		if (mConfigMap_mapSimple == null)
			mConfigMap_mapSimple = getConfigMap().getFeature("mapSimple");
		return mConfigMap_mapSimple;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getUserDefinedDeltaAddKeyBinding_newBinding(){
		if (mUserDefinedDeltaAddKeyBinding_newBinding == null)
			mUserDefinedDeltaAddKeyBinding_newBinding = getUserDefinedDeltaAddKeyBinding().getFeature("newBinding");
		return mUserDefinedDeltaAddKeyBinding_newBinding;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getUserDefinedDeltaChangeKeyBinding_remove(){
		if (mUserDefinedDeltaChangeKeyBinding_remove == null)
			mUserDefinedDeltaChangeKeyBinding_remove = getUserDefinedDeltaChangeKeyBinding().getFeature("remove");
		return mUserDefinedDeltaChangeKeyBinding_remove;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getUserDefinedDeltaDeleteKeyBinding_oldBinding(){
		if (mUserDefinedDeltaDeleteKeyBinding_oldBinding == null)
			mUserDefinedDeltaDeleteKeyBinding_oldBinding = getUserDefinedDeltaDeleteKeyBinding().getFeature("oldBinding");
		return mUserDefinedDeltaDeleteKeyBinding_oldBinding;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getUserDefinedDeltaChangeKeyBinding_add(){
		if (mUserDefinedDeltaChangeKeyBinding_add == null)
			mUserDefinedDeltaChangeKeyBinding_add = getUserDefinedDeltaChangeKeyBinding().getFeature("add");
		return mUserDefinedDeltaChangeKeyBinding_add;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getKeyBindings_deltas(){
		if (mKeyBindings_deltas == null)
			mKeyBindings_deltas = getKeyBindings().getFeature("deltas");
		return mKeyBindings_deltas;
	}
}
