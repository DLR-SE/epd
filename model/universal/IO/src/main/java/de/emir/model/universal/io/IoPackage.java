package de.emir.model.universal.io;

import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.io.impl.FileImpl;
import de.emir.model.universal.io.impl.InputStreamImpl;
import de.emir.model.universal.io.impl.OutputStreamImpl;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.model.universal.io.IFile;
import de.emir.model.universal.io.IInputStream;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.universal.io.IOutputStream;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import java.util.List;

/**
 *	@generated 
 */
public class IoPackage  
{
	/**
	 * @generated
	 */
	public static IoPackage theInstance = new IoPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier File
		*/
		UClass File = IoPackage.theInstance.getFile();
		/**
		* @generated
		* @return meta type for classifier OutputStream
		*/
		UClass OutputStream = IoPackage.theInstance.getOutputStream();
		/**
		* @generated
		* @return meta type for classifier InputStream
		*/
		UClass InputStream = IoPackage.theInstance.getInputStream();
		/**
		 * @generated
		 * @return feature descriptor absolutePath in type File
		 */
		 UStructuralFeature File_absolutePath = IoPackage.theInstance.getFile_absolutePath();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mFile = null;
	/**
	* @generated
	*/
	private UClass mOutputStream = null;
	/**
	* @generated
	*/
	private UClass mInputStream = null;
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	//Features for classifier File
	/**
	 * @generated
	 */
	private UStructuralFeature mFile_absolutePath = null;
	
	/**
	 * @generated
	 */
	public static IoPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package IoPackage ...");
		theInstance = new IoPackage();
		//initialize referenced models
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.io");
		p.getContent().add(theInstance.mFile);
		p.getContent().add(theInstance.mOutputStream);
		p.getContent().add(theInstance.mInputStream);
		p.freeze();
		
		
		
		ULog.debug("... package IoPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mFile = UMetaBuilder.manual().createClass("File", false, IFile.class, FileImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mFile, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new FileImpl();
				}
			});
			mFile.setDocumentation("\r\n * Wrapper around the java.io.File object to allow specification of files\r\n * instead using string to point to an file\r\n ");
		
		mOutputStream = UMetaBuilder.manual().createClass("OutputStream", false, IOutputStream.class, OutputStreamImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mOutputStream, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new OutputStreamImpl();
				}
			});
			//Annotations for OutputStream
			mOutputStream.createAnnotation("struct");
		
		mInputStream = UMetaBuilder.manual().createClass("InputStream", false, IInputStream.class, InputStreamImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mInputStream, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new InputStreamImpl();
				}
			});
			//Annotations for InputStream
			mInputStream.createAnnotation("struct");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of File
			mFile_absolutePath = UMetaBuilder.manual().createFeature("absolutePath", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mFile_absolutePath, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IFile)instance).getAbsolutePath(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((IFile)instance).setAbsolutePath((String)value); } }
				);
			
		}
		{ //assign features
			mFile.getStructuralFeatures().add(mFile_absolutePath);
		}
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of File
			UOperation operation = null;
			//operation : isDirectory(boolean)
			operation = UMetaBuilder.manual().createOperation("isDirectory", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).isDirectory();
				}
			});
				//Annotations for File:isDirectory(boolean)
				operation.createAnnotation("const");
				mFile.getOperations().add(operation);
			//operation : isFile(boolean)
			operation = UMetaBuilder.manual().createOperation("isFile", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).isFile();
				}
			});
				//Annotations for File:isFile(boolean)
				operation.createAnnotation("const");
				mFile.getOperations().add(operation);
			//operation : exists(boolean)
			operation = UMetaBuilder.manual().createOperation("exists", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).exists();
				}
			});
				//Annotations for File:exists(boolean)
				operation.createAnnotation("const");
				mFile.getOperations().add(operation);
			//operation : getName(String)
			operation = UMetaBuilder.manual().createOperation("getName", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).getName();
				}
			});
				//Annotations for File:getName(String)
				operation.createAnnotation("const");
				mFile.getOperations().add(operation);
			//operation : resolveFile(File)
			operation = UMetaBuilder.manual().createOperation("resolveFile", false, IoPackage.theInstance.getFile(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).resolveFile();
				}
			});
				operation.setDocumentation(" Trys to resolve the file if it does not exists yet therefore the \r\n * method takes into account the position of the containing resource\r\n * @returns 1) this, if exists() evaluates to true 2) a new File if it could be found relative to the containing eResource or 3) null \r\n ");
				//Annotations for File:resolveFile(File)
				operation.createAnnotation("const");
				mFile.getOperations().add(operation);
			//operation : listFiles(File, int)
			operation = UMetaBuilder.manual().createOperation("listFiles", false, IoPackage.theInstance.getFile(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IFile)instance).listFiles((int)parameter[0]);
				}
			});
				operation.setDocumentation(" Lists all files in the directory, this object points to\r\n * @param depth search depth, set to -1 for unlimited search\r\n * @return list of files if this instance points to an directory (may be empty) or null, if this instance points towards a file\r\n ");
				UMetaBuilder.manual().addParameter(operation, "depth", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mFile.getOperations().add(operation);
		}
		{		//Operations of OutputStream
			UOperation operation = null;
			//operation : isValid(boolean)
			operation = UMetaBuilder.manual().createOperation("isValid", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).isValid();
				}
			});
				//Annotations for OutputStream:isValid(boolean)
				operation.createAnnotation("const");
				mOutputStream.getOperations().add(operation);
			//operation : isEmpty(boolean)
			operation = UMetaBuilder.manual().createOperation("isEmpty", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).isEmpty();
				}
			});
				//Annotations for OutputStream:isEmpty(boolean)
				operation.createAnnotation("const");
				mOutputStream.getOperations().add(operation);
			//operation : size(int)
			operation = UMetaBuilder.manual().createOperation("size", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).size();
				}
			});
				//Annotations for OutputStream:size(int)
				operation.createAnnotation("const");
				mOutputStream.getOperations().add(operation);
			//operation : write(int, int)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((int)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
			//operation : write(int, long)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((long)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(long.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
			//operation : write(int, boolean)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((boolean)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
			//operation : write(int, float)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((float)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(float.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
			//operation : write(int, double)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((double)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
			//operation : write(int, String)
			operation = UMetaBuilder.manual().createOperation("write", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IOutputStream)instance).write((String)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "v", TypeUtils.getPrimitiveType(String.class), 0, 1, UDirectionType.IN);
				mOutputStream.getOperations().add(operation);
		}
		{		//Operations of InputStream
			UOperation operation = null;
			//operation : isValid(boolean)
			operation = UMetaBuilder.manual().createOperation("isValid", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).isValid();
				}
			});
				//Annotations for InputStream:isValid(boolean)
				operation.createAnnotation("const");
				mInputStream.getOperations().add(operation);
			//operation : isEmpty(boolean)
			operation = UMetaBuilder.manual().createOperation("isEmpty", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).isEmpty();
				}
			});
				//Annotations for InputStream:isEmpty(boolean)
				operation.createAnnotation("const");
				mInputStream.getOperations().add(operation);
			//operation : size(int)
			operation = UMetaBuilder.manual().createOperation("size", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).size();
				}
			});
				//Annotations for InputStream:size(int)
				operation.createAnnotation("const");
				mInputStream.getOperations().add(operation);
			//operation : readBoolean(boolean)
			operation = UMetaBuilder.manual().createOperation("readBoolean", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readBoolean();
				}
			});
				mInputStream.getOperations().add(operation);
			//operation : readInt(int)
			operation = UMetaBuilder.manual().createOperation("readInt", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readInt();
				}
			});
				mInputStream.getOperations().add(operation);
			//operation : readLong(long)
			operation = UMetaBuilder.manual().createOperation("readLong", false, TypeUtils.getPrimitiveType(long.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readLong();
				}
			});
				mInputStream.getOperations().add(operation);
			//operation : readFloat(float)
			operation = UMetaBuilder.manual().createOperation("readFloat", false, TypeUtils.getPrimitiveType(float.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readFloat();
				}
			});
				mInputStream.getOperations().add(operation);
			//operation : readDouble(double)
			operation = UMetaBuilder.manual().createOperation("readDouble", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readDouble();
				}
			});
				mInputStream.getOperations().add(operation);
			//operation : readString(String)
			operation = UMetaBuilder.manual().createOperation("readString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((IInputStream)instance).readString();
				}
			});
				mInputStream.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		
	}



	/**
	* @generated
	*/
	public UClass getOutputStream(){
		if (mOutputStream == null){
			mOutputStream = UCoreMetaRepository.getUClass(IOutputStream.class);
		}
		return mOutputStream;
	}



	/**
	* @generated
	*/
	public UClass getInputStream(){
		if (mInputStream == null){
			mInputStream = UCoreMetaRepository.getUClass(IInputStream.class);
		}
		return mInputStream;
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getFile(){
		if (mFile == null){
			mFile = UCoreMetaRepository.getUClass(IFile.class);
		}
		return mFile;
	}
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getFile_absolutePath(){
		if (mFile_absolutePath == null)
			mFile_absolutePath = getFile().getFeature("absolutePath");
		return mFile_absolutePath;
	}
}
