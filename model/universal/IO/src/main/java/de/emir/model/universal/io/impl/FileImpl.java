package de.emir.model.universal.io.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.io.IFile;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.universal.io.IoPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**

 * Wrapper around the java.io.File object to allow specification of files
 * instead using string to point to a file
 * @generated 
 */
@UMLImplementation(classifier = IFile.class)
public class FileImpl extends UObjectImpl implements IFile  
{
	
	
	/**
	 *	@generated 
	 */
	private String mAbsolutePath = "";
	private File mNativeFile;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public FileImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public FileImpl(final IFile _copy) {
		mAbsolutePath = _copy.getAbsolutePath();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public FileImpl(String _absolutePath) {
		mAbsolutePath = _absolutePath;
	}
	
	public FileImpl(File file) {
		this(file.getAbsolutePath());
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return IoPackage.Literals.File;
	}

	/**
	 *	@generated 
	 */
	public void setAbsolutePath(String _absolutePath) {
		if (needNotification(IoPackage.Literals.File_absolutePath)){
			String _oldValue = mAbsolutePath;
			mAbsolutePath = _absolutePath;
			notify(_oldValue, mAbsolutePath, IoPackage.Literals.File_absolutePath, NotificationType.SET);
		}else{
			mAbsolutePath = _absolutePath;
		}
	}

	/**
	 *	@generated 
	 */
	public String getAbsolutePath() {
		return mAbsolutePath;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isDirectory()
	{
		 return getNativeFile().isDirectory();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean isFile()
	{
		return getNativeFile().isFile();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean exists()
	{
		return getNativeFile().exists();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public String getName()
	{
		return getNativeFile().getName();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public List<IFile> listFiles(final int depth)
	{
		 if (isDirectory())
			 return null;
		 File[] l = getNativeFile().listFiles();
		 ArrayList<IFile> out = new ArrayList<IFile>();
		 for (File fi : l){
			 IFile fout = new FileImpl(fi);
			 if (fi.isDirectory()){
				 if (depth != 0)
					 out.addAll(fout.listFiles(depth-1));
			 }else
				 out.add(fout);
		 }
		 return out;
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public IFile resolveFile()
	{
		//TODO: 
		//  Trys to resolve the file if it does not exists yet therefore the 
		//  * method takes into account the position of the containing resource
		//  * @returns 1) this, if exists() evaluates to true 2) a new File if it could be found relative to the containing eResource or 3) null 
		//  
		throw new UnsupportedOperationException("resolveFile not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "FileImpl{" +
		" absolutePath = " + getAbsolutePath() + 
		"}";
	}

	@Override
	public File getNativeFile() {
		return mNativeFile = new File(getAbsolutePath());
	}
}
