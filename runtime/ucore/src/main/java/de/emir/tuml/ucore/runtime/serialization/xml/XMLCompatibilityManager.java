package de.emir.tuml.ucore.runtime.serialization.xml;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Node;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer.IXMLReadListener;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer.XMLReaderImpl;
import de.emir.tuml.ucore.runtime.utils.XMLReader;

/**
 * The XMLCompatibilityManager is used to support the on demand handling of model changes, during loading of XML files. 
 * Therefore you can register an instance of de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer.IXMLReadListener that will be notified if a 
 * new object instance has been created.  
 * @author sschweigert
 *
 */
public class XMLCompatibilityManager implements IExtensionPoint, IXMLReadListener {

	public interface IXMLPostProcessor extends IXMLReadListener {
		/** will be asked for every read object, if a postprocessing is required. If the method returns true, the onObjectRead method is called */
		public boolean requiresPostProcessing(UObject obj, final Node xmlNode, XMLReader reader);
	}
	
	private ArrayList<IXMLPostProcessor> 		mDelegates = new ArrayList<>();
	
	public XMLCompatibilityManager() {
	}
	/**
	 * Register a new Compatibility handler that shall be notified, if a new instance that is of the same type as the specified clazz has been created 
	 * @param clazz
	 * @param handler
	 */
	public void registerCompatiblityHandler(IXMLPostProcessor handler) {
		if (handler != null && mDelegates.contains(handler) == false)
			mDelegates.add(handler);
	}

	public boolean isEmpty() {
		return mDelegates.isEmpty();
	}


	@Override
	public void onObjectRead(UObject object, Node xmlNode, XMLReader reader, XMLReaderImpl serializer) {
		if (mDelegates.isEmpty()) return ; 
		for (IXMLPostProcessor pp : mDelegates) {
			try {
				if (pp.requiresPostProcessing(object, xmlNode, reader)) {
					pp.onObjectRead(object, xmlNode, reader, serializer);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public UClassifier onCouldNotFindClassifier(String type, UObject instance, UStructuralFeature feature, Node node, XMLReader mReader, XMLReaderImpl xmlReaderImpl) {
		if (mDelegates.isEmpty()) return null; 
		for (IXMLPostProcessor pp : mDelegates) {
			try {
				UClassifier cl = pp.onCouldNotFindClassifier(type, instance, feature, node, mReader, xmlReaderImpl);
				if (cl != null)
					return cl;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
