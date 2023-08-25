package de.emir.model.universal.io;

import java.io.File;
import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Wrapper around the java.io.File object to allow specification of files
 * instead using string to point to a file
 * @generated 
 */
@UMLClass	
public interface IFile extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "absolutePath", associationType = AssociationType.PROPERTY)
	public void setAbsolutePath(String _absolutePath);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "absolutePath", associationType = AssociationType.PROPERTY)
	public String getAbsolutePath();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	boolean isDirectory();
	
	/**
	 *	@generated 
	 */
	boolean isFile();
	
	/**
	 *	@generated 
	 */
	boolean exists();
	
	/**
	 *	@generated 
	 */
	String getName();
	
	/**
	 Trys to resolve the file if it does not exist yet therefore the
	 * method takes into account the position of the containing resource
	 * @returns 1) this, if exists() evaluates to true 2) a new File if it could be found relative to the containing eResource or 3) null 
	 * @generated 
	 */
	IFile resolveFile();
	/**
	 Lists all files in the directory, this object points to
	 * @param depth search depth, set to -1 for unlimited search
	 * @return list of files if this instance points to a directory (maybe empty) or null, if this instance points towards a file
	 * @generated 
	 */
	List<IFile> listFiles(final int depth);
	
	public File getNativeFile();
	
}
