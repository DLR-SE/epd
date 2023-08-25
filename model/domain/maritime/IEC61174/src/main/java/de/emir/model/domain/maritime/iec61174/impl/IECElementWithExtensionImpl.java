package de.emir.model.domain.maritime.iec61174.impl;

import java.util.List;

import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IECElementWithExtension.class)
abstract public class IECElementWithExtensionImpl extends UObjectImpl implements IECElementWithExtension  
{
	
	
	/**
	 *	@generated 
	 */
	private List<Extension> mExtensions = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public IECElementWithExtensionImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public IECElementWithExtensionImpl(final IECElementWithExtension _copy) {
		mExtensions = _copy.getExtensions();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public IECElementWithExtensionImpl(List<Extension> _extensions) {
		mExtensions = _extensions; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.IECElementWithExtension;
	}

	/**
	 *	@generated 
	 */
	public List<Extension> getExtensions() {
		if (mExtensions == null) {
			mExtensions = new UContainmentList<Extension>(this, Iec61174Package.theInstance.getIECElementWithExtension_extensions()); 
		}
		return mExtensions;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "IECElementWithExtensionImpl{" +
		"}";
	}
}
