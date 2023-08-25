package de.emir.rcp.editor.model.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.emir.model.universal.core.RSIdentifier;
import de.emir.rcp.editor.model.ILabelProvider;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProvider;
import de.emir.tuml.ucore.runtime.utils.impl.FeaturePointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderImpl;

/**
 * Abstract label provider, that provides default implementation for all methods in ILabelProvider
 * 
 * default behavior: 
 * by default a simple qualifiedname provider is used to determine the qualified name of an object (search for the "name" feature)
 * if the name provider returns no name for the object, the class name is used.
 * options: 
 * - use the qualified name (default = true)
 * - qualified name seperator (default = "::"), char sequence, used to seperate two names
 * - add type: adds the type (classifier's name) before the qualified name 
 * @author sschweigert
 *
 */
@SuppressWarnings("restriction")
public class GenericLabelProvider implements ILabelProvider {

	protected QualifiedNameProvider 	mNameProvider = new QualifiedNameProviderImpl();
	protected List<UStructuralFeature>	mNameFeatures = new ArrayList<UStructuralFeature>();
	
	protected boolean 					mUseQualifiedName = true;
	protected String 					mQualifiedNameSeperator = "::";
	protected boolean 					mAddTypeToName = true;
	protected boolean					mAddFeatureToName = true;
	
	 
	
	
	public QualifiedNameProvider getNameProvider() {
		return mNameProvider;
	}

	public void setNameProvider(QualifiedNameProvider mNameProvider) {
		this.mNameProvider = mNameProvider;
	}

	public List<UStructuralFeature> getNameFeatures() {
		return mNameFeatures;
	}

	public void setNameFeatures(List<UStructuralFeature> mNameFeatures) {
		this.mNameFeatures = mNameFeatures;
	}

	public boolean isUseQualifiedName() {
		return mUseQualifiedName;
	}

	public void setUseQualifiedName(boolean mUseQualifiedName) {
		this.mUseQualifiedName = mUseQualifiedName;
	}

	public String getQualifiedNameSeperator() {
		return mQualifiedNameSeperator;
	}

	public void setQualifiedNameSeperator(String mQualifiedNameSeperator) {
		this.mQualifiedNameSeperator = mQualifiedNameSeperator;
	}

	public boolean isAddTypeToName() {
		return mAddTypeToName;
	}

	public void setAddTypeToName(boolean mAddTypeToName) {
		this.mAddTypeToName = mAddTypeToName;
	}

	public boolean isAddFeatureToName() {
		return mAddFeatureToName;
	}

	public void setAddFeatureToName(boolean mAddFeatureToName) {
		this.mAddFeatureToName = mAddFeatureToName;
	}

	Pointer findNamePointer(UObject obj, UClassifier cl, UStructuralFeature _feature){
		if (obj == null) 
			return null;
		UStructuralFeature feature = null;
		ArrayList<UStructuralFeature> featureList = new ArrayList<UStructuralFeature>();
		
		for (UStructuralFeature f : mNameFeatures){
			if (cl.getFeature(f.getName()) == f){
				feature = f; 
				break;
			}
		}
		if (feature == null){
			UStructuralFeature f = cl.getFeature("name");
			if (f != null){
				mNameFeatures.add(f);
				feature = f;
			}
		}
		if (feature == null){
			UStructuralFeature f = cl.getFeature("label");
			if (f != null){
				mNameFeatures.add(f);
				feature = f;
			}
		}
		if (feature == null)
			return null;
		Pointer ptr = PointerOperations.create(obj, feature);
		if (feature.getType().getRepresentingClass() == String.class){
			return ptr;
		}
		Object child = feature.get(obj);
		if (child != null && child instanceof UObject){
			UObject uchild = (UObject)child;
			Pointer child_ptr = findNamePointer(uchild, uchild.getUClassifier(), uchild.getUContainingFeature());
			if (child_ptr != null)
				return PointerOperations.create(ptr, child_ptr);
		}		
		return ptr;
	}
		
	private String buildString(String value, UType cl, UStructuralFeature feature) {
		String name = value;
		if (mAddTypeToName || value == null || value.isEmpty())
			name = "<i>[" + cl.getName() + "] </i><b>" + name + "</b>";
		if (mAddFeatureToName && feature != null)
			name = feature.getName() + " <b>" + name + "</b>";
		
		return name;
	}
	
	String changeableLabelProperty(UObject uobj, UClassifier cl, UStructuralFeature feature){
		Pointer ptr = findNamePointer(uobj, cl, feature);
		if (ptr == null)
			return null;
		Object ptrValue = ptr.getValue();
		if (ptrValue instanceof String) {
			return buildString((String)ptrValue, cl, feature);
		}else if(ptrValue instanceof RSIdentifier) {
			return buildString((String)((RSIdentifier)ptrValue).getCode(), cl, feature);
		} else {
			return buildString("",  cl, feature);
		}
	}
	
	private HashMap<UType, Pointer> 		mTypeToNamePointer = new HashMap<>();
	
	
	
	@Override
	public String getLabel(Pointer pointer) {
		if (pointer == null ) 
			return null;
		if (pointer.isValid() == false) {
			String ptr = PointerOperations.toPointerString(pointer);
			int idx = ptr.lastIndexOf('.');
			if (idx >= 0)
				return ptr.substring(idx);
			return ptr;
		}
		UType type = pointer.getType();
		if (type == null) type = pointer.getExpectedType();
		if (type == null) return PointerOperations.toPointerString(pointer);
		
		UStructuralFeature feature = pointer.getPointedFeature();		
		if (type instanceof UPrimitiveType || type instanceof UEnum) {
			if (feature != null)
				return "<html>" + feature.getName() + " <i>[" + type.getName() + "]</i></html>";
			return PointerOperations.toPointerString(pointer);
		}

		UClassifier cl = (UClassifier)type;
		UObject uobj = pointer.getUObject();
		Pointer namePointer = findNamePointer(uobj, cl, feature);
		if (namePointer != null) {
			String name = namePointer.get();
			return "<html>"+buildString(name, cl, feature)+"</html>";
		}
		String fn = feature != null ? feature.getName() : "";
		String cn = cl != null ? cl.getName() : "";
		return "<html>" + fn + " <i>[" + cn + "]</i></html>";
	}
	
	@Override
	public String getLabel(Object obj) {
		if (obj == null )
			return null;
		if (obj instanceof UObject == false)
			return obj.getClass() + ":" + obj.toString();
		UObject uobj = (UObject)obj;
		UClass cl = uobj.getUClassifier();
		UStructuralFeature feature = uobj.getUContainingFeature();
		String result = cl.getName();
		String changeable = changeableLabelProperty(uobj, cl, feature); 
		if (changeable != null)
			return changeable;
		QualifiedName qn = mNameProvider.get((UObject)obj);
		String name = null;
		if (qn != null){
			if (mUseQualifiedName)
				name = qn.toString(mQualifiedNameSeperator);
			else
				name = qn.lastSegment();
		}
		if (name != null){
			name = "<b>" + name + "</b>";
			if (mAddTypeToName)
				result = "[" + result + "] " + name;
			else
				result = name;
		}
		if (result != null && mAddFeatureToName && feature != null)
		result = feature.getName() + " " + result + "";
		result = "<html>" + result + "</html>";
		return result;
	}

	@Override
	public String getTooltip(Object obj) {
		if (obj == null )
			return null;
		if (obj instanceof Pointer == false)
			return null; 
		
		Pointer ptr = (Pointer)obj;
		String toolTip = "<html>\n";
		toolTip += "<b>Position</b>: " + PointerOperations.toPointerString(ptr) + "<br>\n";
		UStructuralFeature f = ptr.getPointedFeature();
		if (f != null) {
			toolTip += "<h2>Feature</h2>\n";
			toolTip += "<b>"+f.getName() + "</b>:" + f.getDocumentation();
			toolTip += "<br>\n";
		}
		UType t = ptr.getType();
		if (t != null) {
			toolTip += "<h2>Type</h2>\n";
			toolTip += "<b>"+t.getName() + "</b>: " + t.getDocumentation() + "<br>\n";
		}
		toolTip += "<h2>Value</h2>\n" + ptr.getValue();
		toolTip += "</html>";
		return toolTip;
	}

	@Override
	public URL getIcon(Object pointer) {
		// TODO
		return null;
	}


}
