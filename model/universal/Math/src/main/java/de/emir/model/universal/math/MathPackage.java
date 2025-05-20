package de.emir.model.universal.math;

import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.model.universal.math.impl.Matrix2Impl;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.math.impl.Matrix3Impl;
import de.emir.model.universal.math.impl.Matrix4Impl;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.math.impl.Vector4DImpl;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.model.universal.math.BorderBehavior;
import de.emir.model.universal.math.Function1;
import de.emir.model.universal.math.Function2;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.model.universal.math.FunctionN;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.universal.math.Function3;
import de.emir.model.universal.math.Matrix2;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.model.universal.math.SampleFunction1;
import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.StringFunction1;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.Vector4D;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.model.universal.math.Matrix4;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.model.universal.math.impl.SampleFunction1Impl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.universal.math.impl.StringFunction1Impl;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

/**
 *	@generated 
 */
public class MathPackage  
{
	
	/**
	 * @generated
	 */
	public static MathPackage theInstance = new MathPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		 * @generated
		 * @return meta type for interface Vector
		 */
		UInterface Vector = MathPackage.theInstance.getVector();
		/**
		* @generated
		* @return meta type for classifier Vector2D
		*/
		UClass Vector2D = MathPackage.theInstance.getVector2D();
		/**
		* @generated
		* @return meta type for classifier Vector3D
		*/
		UClass Vector3D = MathPackage.theInstance.getVector3D();
		/**
		* @generated
		* @return meta type for classifier Vector4D
		*/
		UClass Vector4D = MathPackage.theInstance.getVector4D();
		/**
		* @generated
		* @return meta type for classifier Matrix2
		*/
		UClass Matrix2 = MathPackage.theInstance.getMatrix2();
		/**
		* @generated
		* @return meta type for classifier Matrix3
		*/
		UClass Matrix3 = MathPackage.theInstance.getMatrix3();
		/**
		* @generated
		* @return meta type for classifier Matrix4
		*/
		UClass Matrix4 = MathPackage.theInstance.getMatrix4();
		/**
		 * @generated
		 * @return meta type for interface Function1
		 */
		UInterface Function1 = MathPackage.theInstance.getFunction1();
		/**
		 * @generated
		 * @return meta type for interface Function2
		 */
		UInterface Function2 = MathPackage.theInstance.getFunction2();
		
		/**
		 * @generated
		 * @return feature descriptor x in type Vector2D
		 */
		 UStructuralFeature Vector2D_x = MathPackage.theInstance.getVector2D_x();
		/**
		 * @generated
		 * @return meta type for interface FunctionN
		 */
		UInterface FunctionN = MathPackage.theInstance.getFunctionN();
		/**
		* @generated
		* @return meta type for classifier StringFunction1
		*/
		UClass StringFunction1 = MathPackage.theInstance.getStringFunction1();
		/**
		 * @generated
		 * @return meta type for interface Function3
		 */
		UInterface Function3 = MathPackage.theInstance.getFunction3();
		/**
		* @generated
		* @return meta type for classifier SampleFunction1
		*/
		UClass SampleFunction1 = MathPackage.theInstance.getSampleFunction1();
		/**
		* @generated
		* @return meta type for enumeration BorderBehavior
		*/
		UEnum BorderBehavior = MathPackage.theInstance.getBorderBehavior();
		/**
		 * @generated
		 * @return feature descriptor y in type Vector2D
		 */
		 UStructuralFeature Vector2D_y = MathPackage.theInstance.getVector2D_y();
		/**
		 * @generated
		 * @return feature descriptor x in type Vector3D
		 */
		 UStructuralFeature Vector3D_x = MathPackage.theInstance.getVector3D_x();
		/**
		 * @generated
		 * @return feature descriptor y in type Vector3D
		 */
		 UStructuralFeature Vector3D_y = MathPackage.theInstance.getVector3D_y();
		/**
		 * @generated
		 * @return feature descriptor z in type Vector3D
		 */
		 UStructuralFeature Vector3D_z = MathPackage.theInstance.getVector3D_z();
		/**
		 * @generated
		 * @return feature descriptor x in type Vector4D
		 */
		 UStructuralFeature Vector4D_x = MathPackage.theInstance.getVector4D_x();
		/**
		 * @generated
		 * @return feature descriptor y in type Vector4D
		 */
		 UStructuralFeature Vector4D_y = MathPackage.theInstance.getVector4D_y();
		/**
		 * @generated
		 * @return feature descriptor z in type Vector4D
		 */
		 UStructuralFeature Vector4D_z = MathPackage.theInstance.getVector4D_z();
		/**
		 * @generated
		 * @return feature descriptor w in type Vector4D
		 */
		 UStructuralFeature Vector4D_w = MathPackage.theInstance.getVector4D_w();
		/**
		 * @generated
		 * @return feature descriptor m11 in type Matrix2
		 */
		 UStructuralFeature Matrix2_m11 = MathPackage.theInstance.getMatrix2_m11();
		/**
		 * @generated
		 * @return feature descriptor m12 in type Matrix2
		 */
		 UStructuralFeature Matrix2_m12 = MathPackage.theInstance.getMatrix2_m12();
		/**
		 * @generated
		 * @return feature descriptor m21 in type Matrix2
		 */
		 UStructuralFeature Matrix2_m21 = MathPackage.theInstance.getMatrix2_m21();
		/**
		 * @generated
		 * @return feature descriptor m22 in type Matrix2
		 */
		 UStructuralFeature Matrix2_m22 = MathPackage.theInstance.getMatrix2_m22();
		/**
		 * @generated
		 * @return feature descriptor m11 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m11 = MathPackage.theInstance.getMatrix3_m11();
		/**
		 * @generated
		 * @return feature descriptor m12 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m12 = MathPackage.theInstance.getMatrix3_m12();
		/**
		 * @generated
		 * @return feature descriptor m13 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m13 = MathPackage.theInstance.getMatrix3_m13();
		/**
		 * @generated
		 * @return feature descriptor m21 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m21 = MathPackage.theInstance.getMatrix3_m21();
		/**
		 * @generated
		 * @return feature descriptor m22 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m22 = MathPackage.theInstance.getMatrix3_m22();
		/**
		 * @generated
		 * @return feature descriptor m23 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m23 = MathPackage.theInstance.getMatrix3_m23();
		/**
		 * @generated
		 * @return feature descriptor m31 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m31 = MathPackage.theInstance.getMatrix3_m31();
		/**
		 * @generated
		 * @return feature descriptor m32 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m32 = MathPackage.theInstance.getMatrix3_m32();
		/**
		 * @generated
		 * @return feature descriptor m33 in type Matrix3
		 */
		 UStructuralFeature Matrix3_m33 = MathPackage.theInstance.getMatrix3_m33();
		/**
		 * @generated
		 * @return feature descriptor m11 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m11 = MathPackage.theInstance.getMatrix4_m11();
		/**
		 * @generated
		 * @return feature descriptor m12 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m12 = MathPackage.theInstance.getMatrix4_m12();
		/**
		 * @generated
		 * @return feature descriptor m13 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m13 = MathPackage.theInstance.getMatrix4_m13();
		/**
		 * @generated
		 * @return feature descriptor m14 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m14 = MathPackage.theInstance.getMatrix4_m14();
		/**
		 * @generated
		 * @return feature descriptor m21 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m21 = MathPackage.theInstance.getMatrix4_m21();
		/**
		 * @generated
		 * @return feature descriptor m22 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m22 = MathPackage.theInstance.getMatrix4_m22();
		/**
		 * @generated
		 * @return feature descriptor m23 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m23 = MathPackage.theInstance.getMatrix4_m23();
		/**
		 * @generated
		 * @return feature descriptor m24 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m24 = MathPackage.theInstance.getMatrix4_m24();
		/**
		 * @generated
		 * @return feature descriptor m31 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m31 = MathPackage.theInstance.getMatrix4_m31();
		/**
		 * @generated
		 * @return feature descriptor m32 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m32 = MathPackage.theInstance.getMatrix4_m32();
		/**
		 * @generated
		 * @return feature descriptor m33 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m33 = MathPackage.theInstance.getMatrix4_m33();
		/**
		 * @generated
		 * @return feature descriptor m34 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m34 = MathPackage.theInstance.getMatrix4_m34();
		/**
		 * @generated
		 * @return feature descriptor m41 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m41 = MathPackage.theInstance.getMatrix4_m41();
		/**
		 * @generated
		 * @return feature descriptor m42 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m42 = MathPackage.theInstance.getMatrix4_m42();
		/**
		 * @generated
		 * @return feature descriptor m43 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m43 = MathPackage.theInstance.getMatrix4_m43();
		/**
		 * @generated
		 * @return feature descriptor m44 in type Matrix4
		 */
		 UStructuralFeature Matrix4_m44 = MathPackage.theInstance.getMatrix4_m44();
		/**
		 * @generated
		 * @return feature descriptor borderBehavior in type SampleFunction1
		 */
		 UStructuralFeature SampleFunction1_borderBehavior = MathPackage.theInstance.getSampleFunction1_borderBehavior();
		/**
		 * @generated
		 * @return feature descriptor label in type SampleFunction1
		 */
		 UStructuralFeature SampleFunction1_label = MathPackage.theInstance.getSampleFunction1_label();
		/**
		 * @generated
		 * @return feature descriptor domainLabel in type SampleFunction1
		 */
		 UStructuralFeature SampleFunction1_domainLabel = MathPackage.theInstance.getSampleFunction1_domainLabel();
		/**
		 * @generated
		 * @return feature descriptor rangeLabel in type SampleFunction1
		 */
		 UStructuralFeature SampleFunction1_rangeLabel = MathPackage.theInstance.getSampleFunction1_rangeLabel();
		/**
		 * @generated
		 * @return feature descriptor definition in type StringFunction1
		 */
		 UStructuralFeature StringFunction1_definition = MathPackage.theInstance.getStringFunction1_definition();
		/**
		 * @generated
		 * @return feature descriptor samples in type SampleFunction1
		 */
		 UStructuralFeature SampleFunction1_samples = MathPackage.theInstance.getSampleFunction1_samples();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * @generated
	 */
	private UInterface mVector = null;
	/**
	* @generated
	*/
	private UClass mVector2D = null;
	/**
	* @generated
	*/
	private UClass mVector3D = null;
	/**
	* @generated
	*/
	private UClass mVector4D = null;
	/**
	* @generated
	*/
	private UClass mMatrix2 = null;
	/**
	* @generated
	*/
	private UClass mMatrix3 = null;
	/**
	* @generated
	*/
	private UClass mMatrix4 = null;
	/**
	 * @generated
	 */
	private UInterface mFunction1 = null;
	/**
	 * @generated
	 */
	private UInterface mFunction2 = null;
	/**
	 * @generated
	 */
	private UInterface mFunction3 = null;
	/**
	 * @generated
	 */
	private UInterface mFunctionN = null;
	/**
	* @generated
	*/
	private UClass mStringFunction1 = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Vector2D
	/**
	 * @generated
	 */
	private UStructuralFeature mVector2D_x = null;
	/**
	* @generated
	*/
	private UEnum mBorderBehavior = null;
	/**
	* @generated
	*/
	private UClass mSampleFunction1 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector2D_y = null;
	
	//Features for classifier Vector3D
	/**
	 * @generated
	 */
	private UStructuralFeature mVector3D_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector3D_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector3D_z = null;
	
	//Features for classifier Vector4D
	/**
	 * @generated
	 */
	private UStructuralFeature mVector4D_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector4D_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector4D_z = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVector4D_w = null;
	
	//Features for classifier Matrix2
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix2_m11 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix2_m12 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix2_m21 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix2_m22 = null;
	
	//Features for classifier Matrix3
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m11 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m12 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m13 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m21 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m22 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m23 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m31 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m32 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix3_m33 = null;
	
	//Features for classifier Matrix4
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m11 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m12 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m13 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m14 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m21 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m22 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m23 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m24 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m31 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m32 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m33 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m34 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m41 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m42 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m43 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMatrix4_m44 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mStringFunction1_definition = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSampleFunction1_label = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSampleFunction1_domainLabel = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSampleFunction1_rangeLabel = null;
	
	/**
	 * @generated
	 */
	public static MathPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package MathPackage ...");
		theInstance = new MathPackage();
		//initialize referenced models
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.math");
		p.getContent().add(theInstance.mVector);
		p.getContent().add(theInstance.mVector2D);
		p.getContent().add(theInstance.mVector3D);
		p.getContent().add(theInstance.mVector4D);
		p.getContent().add(theInstance.mMatrix2);
		p.getContent().add(theInstance.mMatrix3);
		p.getContent().add(theInstance.mMatrix4);
		p.getContent().add(theInstance.mFunction1);
		p.getContent().add(theInstance.mFunction2);
		p.getContent().add(theInstance.mFunction3);
		p.getContent().add(theInstance.mFunctionN);
		p.getContent().add(theInstance.mStringFunction1);
		p.getContent().add(theInstance.mBorderBehavior);
		p.getContent().add(theInstance.mSampleFunction1);
		p.freeze();
		
		
		
		ULog.debug("... package MathPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mVector = UMetaBuilder.manual().createInterface("Vector", Vector.class);
		
		mVector2D = UMetaBuilder.manual().createClass("Vector2D", false, Vector2D.class, Vector2DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVector2D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Vector2DImpl();
				}
			});
			//Annotations for Vector2D
			mVector2D.createAnnotation("struct");
		
		mVector3D = UMetaBuilder.manual().createClass("Vector3D", false, Vector3D.class, Vector3DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVector3D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Vector3DImpl();
				}
			});
			//Annotations for Vector3D
			mVector3D.createAnnotation("struct");
		
		mVector4D = UMetaBuilder.manual().createClass("Vector4D", false, Vector4D.class, Vector4DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVector4D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Vector4DImpl();
				}
			});
			//Annotations for Vector4D
			mVector4D.createAnnotation("struct");
		
		mMatrix2 = UMetaBuilder.manual().createClass("Matrix2", false, Matrix2.class, Matrix2Impl.class);
			UMetaBuilder.manual().setInstanceCreator(mMatrix2, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Matrix2Impl();
				}
			});
			//Annotations for Matrix2
			mMatrix2.createAnnotation("struct");
		
		mMatrix3 = UMetaBuilder.manual().createClass("Matrix3", false, Matrix3.class, Matrix3Impl.class);
			UMetaBuilder.manual().setInstanceCreator(mMatrix3, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Matrix3Impl();
				}
			});
			//Annotations for Matrix3
			mMatrix3.createAnnotation("struct");
		
		mMatrix4 = UMetaBuilder.manual().createClass("Matrix4", false, Matrix4.class, Matrix4Impl.class);
			UMetaBuilder.manual().setInstanceCreator(mMatrix4, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Matrix4Impl();
				}
			});
			//Annotations for Matrix4
			mMatrix4.createAnnotation("struct");
		
		mFunction1 = UMetaBuilder.manual().createInterface("Function1", Function1.class);
			mFunction1.setDocumentation("\r\n * Interface for a mathematical function with one input value\r\n ");
		
		mFunction2 = UMetaBuilder.manual().createInterface("Function2", Function2.class);
			mFunction2.setDocumentation("\r\n * Interface for a mathematical function with two input values\r\n ");
		
		mFunction3 = UMetaBuilder.manual().createInterface("Function3", Function3.class);
			mFunction3.setDocumentation("\r\n * Interface for a mathematical function with three input values\r\n ");
		
		mFunctionN = UMetaBuilder.manual().createInterface("FunctionN", FunctionN.class);
			mFunctionN.setDocumentation("\r\n * Interface for a function with N input values. The input has to be provided using a vector with dimension N\r\n ");
		
		mStringFunction1 = UMetaBuilder.manual().createClass("StringFunction1", false, StringFunction1.class, StringFunction1Impl.class);
			UMetaBuilder.manual().setInstanceCreator(mStringFunction1, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new StringFunction1Impl();
				}
			});
			//Annotations for StringFunction1
			mStringFunction1.createAnnotation("struct");
		
		mBorderBehavior = UMetaBuilder.manual().createEnumeration("BorderBehavior", BorderBehavior.class);
		
		mSampleFunction1 = UMetaBuilder.manual().createClass("SampleFunction1", false, SampleFunction1.class, SampleFunction1Impl.class);
			UMetaBuilder.manual().setInstanceCreator(mSampleFunction1, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SampleFunction1Impl();
				}
			});
			//Annotations for SampleFunction1
			mSampleFunction1.createAnnotation("struct");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Vector2D
			mVector2D_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector2D_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector2D)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector2D)instance).setX((double)value); } }
				);
			mVector2D_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector2D_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector2D)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector2D)instance).setY((double)value); } }
				);
			
			//Features of Vector3D
			mVector3D_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector3D_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector3D)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector3D)instance).setX((double)value); } }
				);
			mVector3D_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector3D_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector3D)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector3D)instance).setY((double)value); } }
				);
			mVector3D_z = UMetaBuilder.manual().createFeature("z", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector3D_z, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector3D)instance).getZ(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector3D)instance).setZ((double)value); } }
				);
			
			//Features of Vector4D
			mVector4D_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector4D_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector4D)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector4D)instance).setX((double)value); } }
				);
			mVector4D_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector4D_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector4D)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector4D)instance).setY((double)value); } }
				);
			mVector4D_z = UMetaBuilder.manual().createFeature("z", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector4D_z, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector4D)instance).getZ(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector4D)instance).setZ((double)value); } }
				);
			mVector4D_w = UMetaBuilder.manual().createFeature("w", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVector4D_w, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vector4D)instance).getW(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vector4D)instance).setW((double)value); } }
				);
			
			//Features of Matrix2
			mMatrix2_m11 = UMetaBuilder.manual().createFeature("m11", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix2_m11, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix2)instance).getM11(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix2)instance).setM11((double)value); } }
				);
			mMatrix2_m12 = UMetaBuilder.manual().createFeature("m12", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix2_m12, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix2)instance).getM12(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix2)instance).setM12((double)value); } }
				);
			mMatrix2_m21 = UMetaBuilder.manual().createFeature("m21", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix2_m21, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix2)instance).getM21(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix2)instance).setM21((double)value); } }
				);
			mMatrix2_m22 = UMetaBuilder.manual().createFeature("m22", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix2_m22, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix2)instance).getM22(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix2)instance).setM22((double)value); } }
				);
			
			//Features of Matrix3
			mMatrix3_m11 = UMetaBuilder.manual().createFeature("m11", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m11, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM11(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM11((double)value); } }
				);
			mMatrix3_m12 = UMetaBuilder.manual().createFeature("m12", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m12, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM12(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM12((double)value); } }
				);
			mMatrix3_m13 = UMetaBuilder.manual().createFeature("m13", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m13, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM13(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM13((double)value); } }
				);
			mMatrix3_m21 = UMetaBuilder.manual().createFeature("m21", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m21, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM21(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM21((double)value); } }
				);
			mMatrix3_m22 = UMetaBuilder.manual().createFeature("m22", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m22, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM22(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM22((double)value); } }
				);
			mMatrix3_m23 = UMetaBuilder.manual().createFeature("m23", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m23, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM23(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM23((double)value); } }
				);
			mMatrix3_m31 = UMetaBuilder.manual().createFeature("m31", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m31, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM31(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM31((double)value); } }
				);
			mMatrix3_m32 = UMetaBuilder.manual().createFeature("m32", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m32, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM32(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM32((double)value); } }
				);
			mMatrix3_m33 = UMetaBuilder.manual().createFeature("m33", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix3_m33, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix3)instance).getM33(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix3)instance).setM33((double)value); } }
				);
			
			//Features of Matrix4
			mMatrix4_m11 = UMetaBuilder.manual().createFeature("m11", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m11, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM11(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM11((double)value); } }
				);
			mMatrix4_m12 = UMetaBuilder.manual().createFeature("m12", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m12, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM12(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM12((double)value); } }
				);
			mMatrix4_m13 = UMetaBuilder.manual().createFeature("m13", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m13, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM13(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM13((double)value); } }
				);
			mMatrix4_m14 = UMetaBuilder.manual().createFeature("m14", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m14, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM14(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM14((double)value); } }
				);
			mMatrix4_m21 = UMetaBuilder.manual().createFeature("m21", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m21, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM21(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM21((double)value); } }
				);
			mMatrix4_m22 = UMetaBuilder.manual().createFeature("m22", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m22, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM22(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM22((double)value); } }
				);
			mMatrix4_m23 = UMetaBuilder.manual().createFeature("m23", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m23, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM23(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM23((double)value); } }
				);
			mMatrix4_m24 = UMetaBuilder.manual().createFeature("m24", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m24, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM24(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM24((double)value); } }
				);
			mMatrix4_m31 = UMetaBuilder.manual().createFeature("m31", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m31, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM31(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM31((double)value); } }
				);
			mMatrix4_m32 = UMetaBuilder.manual().createFeature("m32", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m32, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM32(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM32((double)value); } }
				);
			mMatrix4_m33 = UMetaBuilder.manual().createFeature("m33", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m33, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM33(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM33((double)value); } }
				);
			mMatrix4_m34 = UMetaBuilder.manual().createFeature("m34", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m34, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM34(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM34((double)value); } }
				);
			mMatrix4_m41 = UMetaBuilder.manual().createFeature("m41", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m41, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM41(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM41((double)value); } }
				);
			mMatrix4_m42 = UMetaBuilder.manual().createFeature("m42", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m42, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM42(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM42((double)value); } }
				);
			mMatrix4_m43 = UMetaBuilder.manual().createFeature("m43", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m43, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM43(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM43((double)value); } }
				);
			mMatrix4_m44 = UMetaBuilder.manual().createFeature("m44", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMatrix4_m44, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Matrix4)instance).getM44(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Matrix4)instance).setM44((double)value); } }
				);
			
			//Features of StringFunction1
			mStringFunction1_definition = UMetaBuilder.manual().createFeature("definition", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mStringFunction1_definition, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((StringFunction1)instance).getDefinition(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((StringFunction1)instance).setDefinition((String)value); } }
				);
				mStringFunction1_definition.setDocumentation(" textual definition of the function. \r\n * The definition string needs to have at least one x or X variable and no other non digit values\r\n * for example\r\n * log(x) - y * (sqrt(x^cos(x)))\r\n * @ntoe x and X will be handled as the same variable\r\n ");
			
			//Features of SampleFunction1
			mSampleFunction1_label = UMetaBuilder.manual().createFeature("label", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSampleFunction1_label, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SampleFunction1)instance).getLabel(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SampleFunction1)instance).setLabel((String)value); } }
				);
				mSampleFunction1_label.setDocumentation(" optional human readable description of the function\" ");
			mSampleFunction1_domainLabel = UMetaBuilder.manual().createFeature("domainLabel", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSampleFunction1_domainLabel, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SampleFunction1)instance).getDomainLabel(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SampleFunction1)instance).setDomainLabel((String)value); } }
				);
				mSampleFunction1_domainLabel.setDocumentation(" optional human readable description of the domain for example \"Time [s]\" ");
			mSampleFunction1_rangeLabel = UMetaBuilder.manual().createFeature("rangeLabel", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSampleFunction1_rangeLabel, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SampleFunction1)instance).getRangeLabel(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SampleFunction1)instance).setRangeLabel((String)value); } }
				);
				mSampleFunction1_rangeLabel.setDocumentation(" optional human readable description of the range for example \"Temperature [degrees]\" ");
			mSampleFunction1_borderBehavior = UMetaBuilder.manual().createFeature("borderBehavior", MathPackage.theInstance.getBorderBehavior(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSampleFunction1_borderBehavior, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SampleFunction1)instance).getBorderBehavior(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SampleFunction1)instance).setBorderBehavior((BorderBehavior)value); } }
				);
			mSampleFunction1_samples = UMetaBuilder.manual().createFeature("samples", MathPackage.theInstance.getVector2D(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mSampleFunction1_samples, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SampleFunction1)instance).getSamples(); } }, 
						null
				);
			
		}
		{ //assign features
			mVector2D.getStructuralFeatures().add(mVector2D_x);
			mVector2D.getStructuralFeatures().add(mVector2D_y);
			mVector3D.getStructuralFeatures().add(mVector3D_x);
			mVector3D.getStructuralFeatures().add(mVector3D_y);
			mVector3D.getStructuralFeatures().add(mVector3D_z);
			mVector4D.getStructuralFeatures().add(mVector4D_x);
			mVector4D.getStructuralFeatures().add(mVector4D_y);
			mVector4D.getStructuralFeatures().add(mVector4D_z);
			mVector4D.getStructuralFeatures().add(mVector4D_w);
			mMatrix2.getStructuralFeatures().add(mMatrix2_m11);
			mMatrix2.getStructuralFeatures().add(mMatrix2_m12);
			mMatrix2.getStructuralFeatures().add(mMatrix2_m21);
			mMatrix2.getStructuralFeatures().add(mMatrix2_m22);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m11);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m12);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m13);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m21);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m22);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m23);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m31);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m32);
			mMatrix3.getStructuralFeatures().add(mMatrix3_m33);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m11);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m12);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m13);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m14);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m21);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m22);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m23);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m24);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m31);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m32);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m33);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m34);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m41);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m42);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m43);
			mMatrix4.getStructuralFeatures().add(mMatrix4_m44);
			mStringFunction1.getStructuralFeatures().add(mStringFunction1_definition);
			mSampleFunction1.getStructuralFeatures().add(mSampleFunction1_label);
			mSampleFunction1.getStructuralFeatures().add(mSampleFunction1_domainLabel);
			mSampleFunction1.getStructuralFeatures().add(mSampleFunction1_rangeLabel);
			mSampleFunction1.getStructuralFeatures().add(mSampleFunction1_borderBehavior);
			mSampleFunction1.getStructuralFeatures().add(mSampleFunction1_samples);
		}
		
		UMetaBuilder.manual().addLiteral(mBorderBehavior, "MIN_MAX_VALUE", 0, BorderBehavior.MIN_MAX_VALUE);
			mBorderBehavior.getLiteral("MIN_MAX_VALUE").setDocumentation(" the function returns the value of the first or last sample ");
		UMetaBuilder.manual().addLiteral(mBorderBehavior, "LINEAR_EXTRAPOLATE", 1, BorderBehavior.LINEAR_EXTRAPOLATE);
			mBorderBehavior.getLiteral("LINEAR_EXTRAPOLATE").setDocumentation(" the function does a linear interpolation (based on nearest two values, e.g. sample[1] & sample[0] for \r\n * x < sample[0].x and sample[n-1] & sample[n] if x > sample[n].x\r\n ");
		UMetaBuilder.manual().addLiteral(mBorderBehavior, "NaN", 2, BorderBehavior.NaN);
			mBorderBehavior.getLiteral("NaN").setDocumentation(" The function returns Not a Number (NaN) if the value is out of the sample range ");
		UMetaBuilder.manual().addLiteral(mBorderBehavior, "EXCEPTION", 3, BorderBehavior.EXCEPTION);
			mBorderBehavior.getLiteral("EXCEPTION").setDocumentation(" The function throws an InvalidArgument Exception ");
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of Vector
			UOperation operation = null;
			//operation : dimensions(int)
			operation = UMetaBuilder.manual().createOperation("dimensions", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).dimensions();
				}
			});
				//Annotations for Vector:dimensions(int)
				operation.createAnnotation("const");
				mVector.getOperations().add(operation);
			//operation : get(double, int)
			operation = UMetaBuilder.manual().createOperation("get", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).get((int)parameter[0]);
				}
			});
				//Annotations for Vector:get(double, int)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mVector.getOperations().add(operation);
			//operation : set(void, int, double)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector)instance).set((int)parameter[0], (double)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector.getOperations().add(operation);
			//operation : set(void, Vector)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector)instance).set((Vector)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				mVector.getOperations().add(operation);
			//operation : copy(Vector)
			operation = UMetaBuilder.manual().createOperation("copy", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).copy();
				}
			});
				//Annotations for Vector:copy(Vector)
				operation.createAnnotation("const");
				mVector.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).readableString();
				}
			});
				//Annotations for Vector:readableString(String)
				operation.createAnnotation("const");
				mVector.getOperations().add(operation);
			//operation : getLength(double)
			operation = UMetaBuilder.manual().createOperation("getLength", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).getLength();
				}
			});
				//Annotations for Vector:getLength(double)
				operation.createAnnotation("const");
				mVector.getOperations().add(operation);
			//operation : getSquareLength(double)
			operation = UMetaBuilder.manual().createOperation("getSquareLength", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector)instance).getSquareLength();
				}
			});
				//Annotations for Vector:getSquareLength(double)
				operation.createAnnotation("const");
				mVector.getOperations().add(operation);
		}
		{		//Operations of Vector2D
			UOperation operation = null;
			//operation : add(Vector2D, Vector2D)
			operation = UMetaBuilder.manual().createOperation("add", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).add((Vector2D)parameter[0]);
				}
			});
				//Annotations for Vector2D:add(Vector2D, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : addLocal(void, Vector2D)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).addLocal((Vector2D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : sub(Vector2D, Vector2D)
			operation = UMetaBuilder.manual().createOperation("sub", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).sub((Vector2D)parameter[0]);
				}
			});
				//Annotations for Vector2D:sub(Vector2D, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : subLocal(void, Vector2D)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).subLocal((Vector2D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : mult(Vector2D, Vector2D)
			operation = UMetaBuilder.manual().createOperation("mult", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).mult((Vector2D)parameter[0]);
				}
			});
				//Annotations for Vector2D:mult(Vector2D, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : multLocal(void, Vector2D)
			operation = UMetaBuilder.manual().createOperation("multLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).multLocal((Vector2D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : mult(Vector2D, double)
			operation = UMetaBuilder.manual().createOperation("mult", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).mult((double)parameter[0]);
				}
			});
				//Annotations for Vector2D:mult(Vector2D, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : multLocal(void, double)
			operation = UMetaBuilder.manual().createOperation("multLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).multLocal((double)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : div(Vector2D, Vector2D)
			operation = UMetaBuilder.manual().createOperation("div", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).div((Vector2D)parameter[0]);
				}
			});
				//Annotations for Vector2D:div(Vector2D, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : divLocal(void, Vector2D)
			operation = UMetaBuilder.manual().createOperation("divLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).divLocal((Vector2D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : div(Vector2D, double)
			operation = UMetaBuilder.manual().createOperation("div", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).div((double)parameter[0]);
				}
			});
				//Annotations for Vector2D:div(Vector2D, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "scalar", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : divLocal(void, double)
			operation = UMetaBuilder.manual().createOperation("divLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).divLocal((double)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "scalar", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : rotateLocalCW(void, double)
			operation = UMetaBuilder.manual().createOperation("rotateLocalCW", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).rotateLocalCW((double)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" rotates this instance clock wise (CW) around the given radian ");
				UMetaBuilder.manual().addParameter(operation, "angle_radian", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : rotateCW(Vector2D, double)
			operation = UMetaBuilder.manual().createOperation("rotateCW", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).rotateCW((double)parameter[0]);
				}
			});
				operation.setDocumentation(" rotates a copy of this vector around the given radian in clock wise order ");
				//Annotations for Vector2D:rotateCW(Vector2D, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "angle_radian", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : rotateLocalCCW(void, double)
			operation = UMetaBuilder.manual().createOperation("rotateLocalCCW", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).rotateLocalCCW((double)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" rotates this instance counter clock wise (CCW) around the given radian ");
				UMetaBuilder.manual().addParameter(operation, "angle_radian", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : rotateCCW(Vector2D, double)
			operation = UMetaBuilder.manual().createOperation("rotateCCW", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).rotateCCW((double)parameter[0]);
				}
			});
				operation.setDocumentation(" rotates a copy of this vector around the given radian in counter clock wise order ");
				//Annotations for Vector2D:rotateCCW(Vector2D, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "angle_radian", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : dot(double, Vector2D)
			operation = UMetaBuilder.manual().createOperation("dot", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).dot((Vector2D)parameter[0]);
				}
			});
				//Annotations for Vector2D:dot(double, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : cross(double, Vector2D)
			operation = UMetaBuilder.manual().createOperation("cross", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).cross((Vector2D)parameter[0]);
				}
			});
				operation.setDocumentation(" Performs a cross product calculation with another vector, \r\n * in 2D this produces a scalar\r\n ");
				//Annotations for Vector2D:cross(double, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mVector2D.getOperations().add(operation);
			//operation : normalize(Vector2D)
			operation = UMetaBuilder.manual().createOperation("normalize", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector2D)instance).normalize();
				}
			});
				//Annotations for Vector2D:normalize(Vector2D)
				operation.createAnnotation("const");
				mVector2D.getOperations().add(operation);
			//operation : normalizeLocal(void)
			operation = UMetaBuilder.manual().createOperation("normalizeLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector2D)instance).normalizeLocal();
					return null;
				}
			});
				mVector2D.getOperations().add(operation);
		}
		{		//Operations of Vector3D
			UOperation operation = null;
			//operation : set(void, double, double, double)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).set((double)parameter[0], (double)parameter[1], (double)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : add(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("add", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).add((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:add(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : addLocal(void, Vector3D)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).addLocal((Vector3D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : sub(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("sub", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).sub((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:sub(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : subLocal(void, Vector3D)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).subLocal((Vector3D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : mult(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("mult", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).mult((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:mult(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : multLocal(void, Vector3D)
			operation = UMetaBuilder.manual().createOperation("multLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).multLocal((Vector3D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : mult(Vector3D, double)
			operation = UMetaBuilder.manual().createOperation("mult", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).mult((double)parameter[0]);
				}
			});
				//Annotations for Vector3D:mult(Vector3D, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : multLocal(void, double)
			operation = UMetaBuilder.manual().createOperation("multLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).multLocal((double)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : div(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("div", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).div((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:div(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : divLocal(void, Vector3D)
			operation = UMetaBuilder.manual().createOperation("divLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).divLocal((Vector3D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : cross(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("cross", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).cross((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:cross(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : crossLocal(void, Vector3D)
			operation = UMetaBuilder.manual().createOperation("crossLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).crossLocal((Vector3D)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : normalize(Vector3D)
			operation = UMetaBuilder.manual().createOperation("normalize", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).normalize();
				}
			});
				//Annotations for Vector3D:normalize(Vector3D)
				operation.createAnnotation("const");
				mVector3D.getOperations().add(operation);
			//operation : normalizeLocal(void)
			operation = UMetaBuilder.manual().createOperation("normalizeLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).normalizeLocal();
					return null;
				}
			});
				mVector3D.getOperations().add(operation);
			//operation : dot(double, Vector3D)
			operation = UMetaBuilder.manual().createOperation("dot", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).dot((Vector3D)parameter[0]);
				}
			});
				//Annotations for Vector3D:dot(double, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : rotateLocalCW(void, double, double, double)
			operation = UMetaBuilder.manual().createOperation("rotateLocalCW", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).rotateLocalCW((double)parameter[0], (double)parameter[1], (double)parameter[2]);
					return null;
				}
			});
				operation.setDocumentation(" rotates this instance clock wise (CW) \r\n * @note this actually corresponds to a multiplication with a quaternion build from euler angles\r\n *   \r\n * @param xAxis rotation around the x - Axis in radians\r\n * @param yAxis rotation around the y - axis in radians\r\n * @param zAxis rotation around the z - axis in radians\r\n ");
				UMetaBuilder.manual().addParameter(operation, "xAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "yAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "zAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : rotateCW(Vector3D, double, double, double)
			operation = UMetaBuilder.manual().createOperation("rotateCW", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).rotateCW((double)parameter[0], (double)parameter[1], (double)parameter[2]);
				}
			});
				operation.setDocumentation(" rotates a copy of this vector around the given radians in clock wise order\r\n *  @note this actually corresponds to a multiplication with a quaternion build from euler angles\r\n *   \r\n * @param xAxis rotation around the x - Axis in radians\r\n * @param yAxis rotation around the y - axis in radians\r\n * @param zAxis rotation around the z - axis in radians\r\n *");
				//Annotations for Vector3D:rotateCW(Vector3D, double, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "xAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "yAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "zAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : rotateLocalCCW(void, double, double, double)
			operation = UMetaBuilder.manual().createOperation("rotateLocalCCW", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Vector3D)instance).rotateLocalCCW((double)parameter[0], (double)parameter[1], (double)parameter[2]);
					return null;
				}
			});
				operation.setDocumentation(" rotates this instance counter clock wise (CCW) \r\n  * @note this actually corresponds to a multiplication with a quaternion build from euler angles\r\n *   \r\n * @param xAxis rotation around the x - Axis in radians\r\n * @param yAxis rotation around the y - axis in radians\r\n * @param zAxis rotation around the z - axis in radians\r\n *");
				UMetaBuilder.manual().addParameter(operation, "xAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "yAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "zAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : rotateCCW(Vector3D, double, double, double)
			operation = UMetaBuilder.manual().createOperation("rotateCCW", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).rotateCCW((double)parameter[0], (double)parameter[1], (double)parameter[2]);
				}
			});
				operation.setDocumentation(" rotates a copy of this vector around the given radians\r\n * @note this actually corresponds to a multiplication with a quaternion build from euler angles\r\n *   \r\n * @param xAxis rotation around the x - Axis in radians\r\n * @param yAxis rotation around the y - axis in radians\r\n * @param zAxis rotation around the z - axis in radians\r\n *");
				//Annotations for Vector3D:rotateCCW(Vector3D, double, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "xAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "yAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "zAxis", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
			//operation : getRotationToDirection(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("getRotationToDirection", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Vector3D)instance).getRotationToDirection((Vector3D)parameter[0]);
				}
			});
				operation.setDocumentation(" Builds a direction vector from (this) rotation vector.\r\n * This vector is assumed to be a rotation vector composed of 3 Euler angle rotations, in degrees.\r\n * The implementation performs the same calculations as using a matrix to do the rotation.\r\n * @param[in] forwards  The direction representing \"forwards\" which will be rotated by this vector.\r\n * @return A direction vector calculated by rotating the forwards direction by the 3 Euler angles (in radian) represented by this vector. \r\n *");
				UMetaBuilder.manual().addParameter(operation, "direction", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mVector3D.getOperations().add(operation);
		}
		{		//Operations of Function1
			UOperation operation = null;
			//operation : getValue(double, double)
			operation = UMetaBuilder.manual().createOperation("getValue", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Function1)instance).getValue((double)parameter[0]);
				}
			});
				//Annotations for Function1:getValue(double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mFunction1.getOperations().add(operation);
		}
		{		//Operations of Function2
			UOperation operation = null;
			//operation : getValue(double, double, double)
			operation = UMetaBuilder.manual().createOperation("getValue", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Function2)instance).getValue((double)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for Function2:getValue(double, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mFunction2.getOperations().add(operation);
		}
		{		//Operations of Function3
			UOperation operation = null;
			//operation : getValue(double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("getValue", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Function3)instance).getValue((double)parameter[0], (double)parameter[1], (double)parameter[2]);
				}
			});
				//Annotations for Function3:getValue(double, double, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mFunction3.getOperations().add(operation);
		}
		{		//Operations of FunctionN
			UOperation operation = null;
			//operation : getValue(double, Vector)
			operation = UMetaBuilder.manual().createOperation("getValue", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((FunctionN)instance).getValue((Vector)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * calculates the function\r\n * @param v input vector with v.dimension() == N\r\n ");
				//Annotations for FunctionN:getValue(double, Vector)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "v", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				mFunctionN.getOperations().add(operation);
		}
		{		//Operations of StringFunction1
			UOperation operation = null;
			//operation : isValid(boolean)
			operation = UMetaBuilder.manual().createOperation("isValid", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((StringFunction1)instance).isValid();
				}
			});
				//Annotations for StringFunction1:isValid(boolean)
				operation.createAnnotation("const");
				mStringFunction1.getOperations().add(operation);
		}
		{		//Operations of SampleFunction1
			UOperation operation = null;
			//operation : addSample(Vector2D, double, double)
			operation = UMetaBuilder.manual().createOperation("addSample", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SampleFunction1)instance).addSample((double)parameter[0], (double)parameter[1]);
				}
			});
				operation.setDocumentation("\r\n * Convenience method to add a new sample. The function creates a new Vector2D and insert it into the \r\n * samples list. If there is already an entry with the same x-value the existing entry will be changed for the new y\r\n ");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mSampleFunction1.getOperations().add(operation);
		}
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UInterface getVector(){
		if (mVector == null){
			mVector = UCoreMetaRepository.getUInterface(Vector.class);
		}
		return mVector;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mVector2D.getInterfaces().add(MathPackage.theInstance.getVector());
		mVector3D.getInterfaces().add(MathPackage.theInstance.getVector());
		mVector4D.getInterfaces().add(MathPackage.theInstance.getVector());
		mStringFunction1.getInterfaces().add(MathPackage.theInstance.getFunction1());
		mSampleFunction1.getInterfaces().add(MathPackage.theInstance.getFunction1());
		
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mSampleFunction1_borderBehavior = null;

	/**
	* @generated
	*/
	public UClass getVector2D(){
		if (mVector2D == null){
			mVector2D = UCoreMetaRepository.getUClass(Vector2D.class);
		}
		return mVector2D;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mSampleFunction1_samples = null;
	/**
	* @generated
	*/
	public UClass getVector3D(){
		if (mVector3D == null){
			mVector3D = UCoreMetaRepository.getUClass(Vector3D.class);
		}
		return mVector3D;
	}
	/**
	* @generated
	*/
	public UClass getVector4D(){
		if (mVector4D == null){
			mVector4D = UCoreMetaRepository.getUClass(Vector4D.class);
		}
		return mVector4D;
	}
	/**
	* @generated
	*/
	public UClass getMatrix2(){
		if (mMatrix2 == null){
			mMatrix2 = UCoreMetaRepository.getUClass(Matrix2.class);
		}
		return mMatrix2;
	}
	/**
	* @generated
	*/
	public UClass getMatrix3(){
		if (mMatrix3 == null){
			mMatrix3 = UCoreMetaRepository.getUClass(Matrix3.class);
		}
		return mMatrix3;
	}
	/**
	* @generated
	*/
	public UClass getMatrix4(){
		if (mMatrix4 == null){
			mMatrix4 = UCoreMetaRepository.getUClass(Matrix4.class);
		}
		return mMatrix4;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getVector2D_x(){
		if (mVector2D_x == null)
			mVector2D_x = getVector2D().getFeature("x");
		return mVector2D_x;
	}



	/**
	* @generated
	*/
	public UInterface getFunction2(){
		if (mFunction2 == null){
			mFunction2 = UCoreMetaRepository.getUInterface(Function2.class);
		}
		return mFunction2;
	}



	/**
	* @generated
	*/
	public UInterface getFunction1(){
		if (mFunction1 == null){
			mFunction1 = UCoreMetaRepository.getUInterface(Function1.class);
		}
		return mFunction1;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector2D_y(){
		if (mVector2D_y == null)
			mVector2D_y = getVector2D().getFeature("y");
		return mVector2D_y;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector3D_x(){
		if (mVector3D_x == null)
			mVector3D_x = getVector3D().getFeature("x");
		return mVector3D_x;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector3D_y(){
		if (mVector3D_y == null)
			mVector3D_y = getVector3D().getFeature("y");
		return mVector3D_y;
	}



	/**
	* @generated
	*/
	public UInterface getFunction3(){
		if (mFunction3 == null){
			mFunction3 = UCoreMetaRepository.getUInterface(Function3.class);
		}
		return mFunction3;
	}



	/**
	* @generated
	*/
	public UInterface getFunctionN(){
		if (mFunctionN == null){
			mFunctionN = UCoreMetaRepository.getUInterface(FunctionN.class);
		}
		return mFunctionN;
	}



	/**
	* @generated
	*/
	public UClass getStringFunction1(){
		if (mStringFunction1 == null){
			mStringFunction1 = UCoreMetaRepository.getUClass(StringFunction1.class);
		}
		return mStringFunction1;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector3D_z(){
		if (mVector3D_z == null)
			mVector3D_z = getVector3D().getFeature("z");
		return mVector3D_z;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector4D_x(){
		if (mVector4D_x == null)
			mVector4D_x = getVector4D().getFeature("x");
		return mVector4D_x;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector4D_y(){
		if (mVector4D_y == null)
			mVector4D_y = getVector4D().getFeature("y");
		return mVector4D_y;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector4D_z(){
		if (mVector4D_z == null)
			mVector4D_z = getVector4D().getFeature("z");
		return mVector4D_z;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVector4D_w(){
		if (mVector4D_w == null)
			mVector4D_w = getVector4D().getFeature("w");
		return mVector4D_w;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix2_m11(){
		if (mMatrix2_m11 == null)
			mMatrix2_m11 = getMatrix2().getFeature("m11");
		return mMatrix2_m11;
	}



	/**
	* @generated
	*/
	public UClass getSampleFunction1(){
		if (mSampleFunction1 == null){
			mSampleFunction1 = UCoreMetaRepository.getUClass(SampleFunction1.class);
		}
		return mSampleFunction1;
	}



	/**
	* @generated
	*/
	public UEnum getBorderBehavior(){
		if (mBorderBehavior == null){
			mBorderBehavior = UCoreMetaRepository.getUEnumeration(BorderBehavior.class);
		}
		return mBorderBehavior;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix2_m12(){
		if (mMatrix2_m12 == null)
			mMatrix2_m12 = getMatrix2().getFeature("m12");
		return mMatrix2_m12;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix2_m21(){
		if (mMatrix2_m21 == null)
			mMatrix2_m21 = getMatrix2().getFeature("m21");
		return mMatrix2_m21;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix2_m22(){
		if (mMatrix2_m22 == null)
			mMatrix2_m22 = getMatrix2().getFeature("m22");
		return mMatrix2_m22;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m11(){
		if (mMatrix3_m11 == null)
			mMatrix3_m11 = getMatrix3().getFeature("m11");
		return mMatrix3_m11;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m12(){
		if (mMatrix3_m12 == null)
			mMatrix3_m12 = getMatrix3().getFeature("m12");
		return mMatrix3_m12;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m13(){
		if (mMatrix3_m13 == null)
			mMatrix3_m13 = getMatrix3().getFeature("m13");
		return mMatrix3_m13;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m21(){
		if (mMatrix3_m21 == null)
			mMatrix3_m21 = getMatrix3().getFeature("m21");
		return mMatrix3_m21;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m22(){
		if (mMatrix3_m22 == null)
			mMatrix3_m22 = getMatrix3().getFeature("m22");
		return mMatrix3_m22;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m23(){
		if (mMatrix3_m23 == null)
			mMatrix3_m23 = getMatrix3().getFeature("m23");
		return mMatrix3_m23;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m31(){
		if (mMatrix3_m31 == null)
			mMatrix3_m31 = getMatrix3().getFeature("m31");
		return mMatrix3_m31;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m32(){
		if (mMatrix3_m32 == null)
			mMatrix3_m32 = getMatrix3().getFeature("m32");
		return mMatrix3_m32;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix3_m33(){
		if (mMatrix3_m33 == null)
			mMatrix3_m33 = getMatrix3().getFeature("m33");
		return mMatrix3_m33;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m11(){
		if (mMatrix4_m11 == null)
			mMatrix4_m11 = getMatrix4().getFeature("m11");
		return mMatrix4_m11;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m12(){
		if (mMatrix4_m12 == null)
			mMatrix4_m12 = getMatrix4().getFeature("m12");
		return mMatrix4_m12;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m13(){
		if (mMatrix4_m13 == null)
			mMatrix4_m13 = getMatrix4().getFeature("m13");
		return mMatrix4_m13;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m14(){
		if (mMatrix4_m14 == null)
			mMatrix4_m14 = getMatrix4().getFeature("m14");
		return mMatrix4_m14;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m21(){
		if (mMatrix4_m21 == null)
			mMatrix4_m21 = getMatrix4().getFeature("m21");
		return mMatrix4_m21;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m22(){
		if (mMatrix4_m22 == null)
			mMatrix4_m22 = getMatrix4().getFeature("m22");
		return mMatrix4_m22;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m23(){
		if (mMatrix4_m23 == null)
			mMatrix4_m23 = getMatrix4().getFeature("m23");
		return mMatrix4_m23;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m24(){
		if (mMatrix4_m24 == null)
			mMatrix4_m24 = getMatrix4().getFeature("m24");
		return mMatrix4_m24;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m31(){
		if (mMatrix4_m31 == null)
			mMatrix4_m31 = getMatrix4().getFeature("m31");
		return mMatrix4_m31;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m32(){
		if (mMatrix4_m32 == null)
			mMatrix4_m32 = getMatrix4().getFeature("m32");
		return mMatrix4_m32;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m33(){
		if (mMatrix4_m33 == null)
			mMatrix4_m33 = getMatrix4().getFeature("m33");
		return mMatrix4_m33;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m34(){
		if (mMatrix4_m34 == null)
			mMatrix4_m34 = getMatrix4().getFeature("m34");
		return mMatrix4_m34;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m41(){
		if (mMatrix4_m41 == null)
			mMatrix4_m41 = getMatrix4().getFeature("m41");
		return mMatrix4_m41;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m42(){
		if (mMatrix4_m42 == null)
			mMatrix4_m42 = getMatrix4().getFeature("m42");
		return mMatrix4_m42;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m43(){
		if (mMatrix4_m43 == null)
			mMatrix4_m43 = getMatrix4().getFeature("m43");
		return mMatrix4_m43;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMatrix4_m44(){
		if (mMatrix4_m44 == null)
			mMatrix4_m44 = getMatrix4().getFeature("m44");
		return mMatrix4_m44;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getStringFunction1_definition(){
		if (mStringFunction1_definition == null)
			mStringFunction1_definition = getStringFunction1().getFeature("definition");
		return mStringFunction1_definition;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSampleFunction1_borderBehavior(){
		if (mSampleFunction1_borderBehavior == null)
			mSampleFunction1_borderBehavior = getSampleFunction1().getFeature("borderBehavior");
		return mSampleFunction1_borderBehavior;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSampleFunction1_label(){
		if (mSampleFunction1_label == null)
			mSampleFunction1_label = getSampleFunction1().getFeature("label");
		return mSampleFunction1_label;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSampleFunction1_samples(){
		if (mSampleFunction1_samples == null)
			mSampleFunction1_samples = getSampleFunction1().getFeature("samples");
		return mSampleFunction1_samples;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSampleFunction1_rangeLabel(){
		if (mSampleFunction1_rangeLabel == null)
			mSampleFunction1_rangeLabel = getSampleFunction1().getFeature("rangeLabel");
		return mSampleFunction1_rangeLabel;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSampleFunction1_domainLabel(){
		if (mSampleFunction1_domainLabel == null)
			mSampleFunction1_domainLabel = getSampleFunction1().getFeature("domainLabel");
		return mSampleFunction1_domainLabel;
	}
}
