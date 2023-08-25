package de.emir.tuml.ucore.runtime.pointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;
import de.emir.tuml.ucore.runtime.utils.impl.FeaturePointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.PointerChainImpl;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;
import de.emir.tuml.ucore.runtime.utils.impl.TypePointerImpl;

public class PointerOperations {
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//											Creation of Pointers															//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static Pointer create(UObject instance){
		return new ObjectPointerImpl(instance);
	}
	public static Pointer create(UObject instance, UStructuralFeature feature, int listIndex) {
		return new FeaturePointerImpl(instance, feature, listIndex);
	}
	
	public static Pointer createChain(Pointer parent, UStructuralFeature... features){
		if (parent == null) return null;
		if (features.length <= 0) return null;
		PointerChain pc = new PointerChainImpl(parent, features[0]);
		for (int i = 1; i < features.length; i++)
			pc = new PointerChainImpl(pc, features[i]);
		return pc;
	}
	public static Pointer createChain(Pointer parent, IStructuralElement element, int listIndex) {
		return new PointerChainImpl(parent, element, listIndex);
	}
	public static Pointer createChain(Pointer parent, UStructuralFeature feature, int listIndex) {
		return new PointerChainImpl(parent, feature, listIndex);
	}
	
	public static Pointer create(UObject parent, UStructuralFeature... features) {
		FeaturePointerImpl fp = new FeaturePointerImpl(parent, features[0]);
		if (features.length == 1)
			return fp;
		PointerChain pc = null;
		Pointer parentPtr = fp;
		for (int i = 1; i < features.length; i++){
			pc = new PointerChainImpl(parentPtr, features[i]);
			parentPtr = pc;
		}
		return pc;
	}

	public static Pointer create(Pointer parent, Pointer child) {
		if (child instanceof PointerChain){
			PointerChain pc =new PointerChainImpl((PointerChain) child);
			pc.setParent(parent);
			return pc;
		}else if (child instanceof FeaturePointer){
			PointerChain pc = new PointerChainImpl();
			pc.setParent(parent);
			pc.setFeature(((FeaturePointer)child).getFeature());
			pc.setListIndex(((FeaturePointer)child).getListIndex());
			return pc;
		}
		return null;
	}
	
	/**
	 * Chreates a pointer chain from the root of the uobject tree
	 * @param uobj
	 * @return
	 */
	public static Pointer createPointerFromRoot(UObject uobj) {
		if (uobj == null)
			return null;
		UObject container = uobj.getUContainer();
		if (container == null)
			return new ObjectPointerImpl(uobj);
		Pointer parent = createPointerFromRoot(container);
		Pointer ptr = createPointerToObject(uobj);
		ptr = create(parent, ptr);		
		return ptr;
	}
	
	/**
	 * converts any pointer to a pointer to the root of the current tree. 
	 * How it works: 
	 * if the pointed value is a UObject, this method returns the value of createPointerFromRoot(UObject)
	 * otherwise it will return the pointer of the pointedContainer (always an UObject) and adds the feature to the object
	 * @param ptr
	 * @return
	 */
	public static Pointer convertToPointerFromRoot(Pointer ptr) {
		if (ptr == null || ptr.isValid() == false)
			return null;
		UObject value = ptr.getUObject();
		if (value != null && value.getUContainer() != null)
			return createPointerFromRoot(value);
		value = ptr.getPointedContainer();
		Pointer newPtr = createPointerFromRoot(value);
		int lidx = -1;
		if (ptr instanceof FeaturePointer)
			lidx = ((FeaturePointer)ptr).getListIndex();
		return createChain(newPtr, ptr.getPointedFeature(), lidx);
	}
	
	
	/**
	 * Creates a pointer that points towards the given object and is not an ObjectPointer, e.g. its relativ to its current parent
	 * @param uobj the object that should be pointed at
	 * @return a valid FeaturePointer if the uobj is not null and the uobj is contained inside a composite association. Null otherwise
	 */
	public static Pointer createPointerToObject(UObject uobj){
		if (uobj == null)
			return null;
		UObject container = uobj.getUContainer();
		UStructuralFeature feature = uobj.getUContainingFeature();
		if (container == null || feature == null)
			return null;
		FeaturePointer fp = new FeaturePointerImpl(container, feature);
		if (feature.isMany()){
			int idx = ((List)feature.get(container)).indexOf(uobj);
			fp.setListIndex(idx);
		}
		return fp;
	}

	public static List<Pointer> toPointerList(PointerChain pc) {
		if (pc.getParent() instanceof ObjectPointer){
			ArrayList<Pointer> out = new ArrayList<>();
			out.add(pc);
			out.add(0, pc.getParent());
			return out;
		}else if (pc.getParent() instanceof PointerChain){
			List<Pointer> parents = toPointerList((PointerChain)pc.getParent());
			parents.add(pc);
			return parents;
		}
		return null;
	}
	
	/**
	 * Checks if the string follows a syntax to describe a pointer. For this purpose the string has to follow this
	 * BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @param mPointerString
	 * @return
	 */
	public static boolean checkPointerString(String pointerString) {
		return PointerStrings.syntaxCheckPointerString(pointerString);
	}
	
	
	/**
	 * For more information see de.emir.tuml.ucore.runtime.pointer.PointerStrings
	 * @param root
	 * @param pointerString
	 * @return
	 */
	public static Pointer createPointerFromString(Pointer parent, String pointerString) {
		return PointerStrings.create(parent, pointerString);
	}
	/**
	 * For more information see de.emir.tuml.ucore.runtime.pointer.PointerStrings
	 * @param root
	 * @param pointerString
	 * @return
	 */
	public static Pointer createPointerFromString(UObject root, String pointerString) {
		return PointerStrings.create(root, pointerString);
	}
	
	/**
	 * Creates a new Pointer relative to an UClassifier. 
	 * @warn the Pointer will be valid but can not be read or written!!
	 * 		Nevertheless this pointer can be transformed to an valid pointer with read and write access by chainging its root
	 * 
	 * @note This type of pointer is meant for UI-Purposes only
	 *  
	 * @param classifier
	 * @param pointerString
	 * @return a new PointerChain, with a TypePointer as root pointer, that possesses an empty (null) parent itself. 
	 */
	public static Pointer createPointerFromString(UClass classifier, String pointerString) {
		return PointerStrings.create(new TypePointerImpl(null, classifier, -1), pointerString);
	}

	/**
	 * Creates a pointer that points into a specific index inside a list, based on a pointer to the whole list
	 * @param ptr pointer that has to point to an list
	 * @param idx new list index for the pointer
	 * @return a new pointer, that points to the idx's position in the list or null, if the ptr is invalid or does not point to an list (for example a ObjectPointer could not point to an list)
	 * @note the method does not take care if the index is valid, e.g. only working on the meta model. 
	 */
	public static FeaturePointer createListPointer(Pointer ptr, int idx) {
		if (ptr == null || ptr.isValid() == false)
			return null;
		if (ptr instanceof FeaturePointer == false)
			return null;
		FeaturePointer fp = (FeaturePointer)ptr;
		if (fp.getFeature().isMany())
			return null;
		FeaturePointer newPtr = UCoreUtils.copy(fp);
		newPtr.setListIndex(idx);
		return newPtr;
	}
	
	
	
	
	
	
	

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//										Manipulation of Pointer Values														//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public synchronized static void set(UObject instance, IStructuralElement feature, int listIndex, Object value) {
		assign(instance, feature, listIndex, value);
	}

	public synchronized static boolean assign(UObject instance, IStructuralElement feature, int listIndex, Object _value) {
		if (instance == null)
			return false;
		if (feature.isMany()){
			return assignToMany(instance, feature, listIndex, _value);
		}else{
			if (feature instanceof UStructuralFeature) {
				//@note: we can not use invoke here, as the feature needs somehow to find out if its a getter or a setter, using the
				//generic method. If we known that its a feature, we can simply use the set method
				((UStructuralFeature)feature).set(instance, _value);
			}else {
				feature.invoke(instance, _value);
			}
			return true;
		}
	}
	public synchronized static boolean assign(UObject instance, IStructuralElement feature, final Object value){
		return assign(instance, feature, -1, value);
	}
	
	
	/**
	 * Assign the new value only if it differs from the current value
	 * @note this method fist has to read the pointed value and therefore is not as fast as the 
	 * simple assign method
	 * 
	 * @param instance 
	 * @param feature
	 * @param listIndex
	 * @param _value
	 * @return true, if the variable has been set. False if either the variable could not be set (@see assign) or if the 
	 * value is the same as the current pointed value
	 */
	public synchronized static boolean assignIfDiffers(UObject instance, IStructuralElement feature, int listIndex, Object _value) {
		if (instance == null)
			return false;
		if (feature.isMany()){ 
			return assignToManyIfDiffers(instance, feature, listIndex, _value);
		}
		Object current = feature.invoke(instance, null); //getter
		if (current == _value)
			return false;
		feature.invoke(instance, _value);
		return true;
	}
	public static boolean assignIfDiffers(UObject instance, IStructuralElement feature, final Object value){
		return assignIfDiffers(instance, feature, -1, value);
	}
	public synchronized static boolean assignToManyIfDiffers(UObject instance, IStructuralElement feature, int listIndex, Object value) {
		Object list_obj = feature.invoke(instance, null); //getter //instance.uGet(feature);
		if (list_obj == null || list_obj instanceof List == false)
			return false;
		if (listIndex < 0){
			((List)list_obj).add(value);
			return true; //if we shall add, we always change the list 
		}else {
			Object current = ((List)list_obj).get(listIndex);
			if (current != value){
				((List)list_obj).set(listIndex, value);
				return true;
			}
			return false; //did not differ
		}
	}
	
	

	public synchronized static boolean assignToMany(UObject instance, IStructuralElement feature, int listIndex, Object value) {
		Object list_obj = feature.invoke(instance, null); //gettter //instance.uGet(feature);
		if (list_obj == null || list_obj instanceof List == false)
			return false;
		if (listIndex < 0){
			if (value instanceof List){
				//replace the old list with the new list
				((List)list_obj).clear();
				((List)list_obj).addAll((List)value);
			}else
				((List)list_obj).add(value);
		}
		else {
			List l = (List)list_obj;
			if (l.size() <= listIndex)
				l.add(value);
			else
				l.set(listIndex, value);
		}
		return true;
	}

	public static Object getValue(UObject instance, UStructuralFeature feature, int listIndex) {
		if (instance != null && feature != null)
		{
			Object value = feature.get(instance);
			if (listIndex >= 0 && listIndex < ((List)value).size())
				return ((List)value).get(listIndex); //this may throws an exception, but that's intended. 
			return value;
		}
		return null;
	}
	public static Object getValue(UObject instance, IStructuralElement feature, int listIndex) {
		if (instance != null && feature != null)
		{
			Object value = feature.invoke(instance, null); //since we use null as parameter the feature is choosing the getter
			if (listIndex >= 0 && listIndex < ((List)value).size())
				return ((List)value).get(listIndex); //this may throws an exception, but that's intended. 
			return value;
		}
		return null;
	}
	
	
	
	
	
	/**
	 * removes the value from its parent
	 * for this purpose this method search a valid pointer to the value (based on parent and feature) and invokes the unset(Pointer) method.
	 * @param parent
	 * @param feature
	 * @param value
	 * @return
	 */
	public static boolean unset(UObject parent, UStructuralFeature feature, UObject value) {
		if (parent == null || feature == null || value == null)
			return false;
		if (feature.isMany() == false){
			//simple case, just check if the pointer points to the value
			Pointer ptr = create(parent, feature);
			if (ptr.getValue() == value)
				return unset(ptr);
		}else{
			//search inside the list for the given list index
			List list = (List)getValue(parent, feature, -1);
			if (list != null){
				for (int i = 0; i < list.size(); i++)
					if (list.get(i) == value)
						return unset(create(parent, feature, i));
			}
		}
		return false;
	}

	/**
	 * removes the given value from its parent. 
	 * - if the parent, feature pair points to a list, the index (stored inside the pointer) is removed. If the pointer points to the whole list (getListIndex() < 0) the list is cleared
	 * - if the parent, feature pair points to a single value, the value is set to null
	 * @note this method does not work with ObjectPointer instance's
	 * @param parent
	 * @param feature
	 * @param value
	 * @return true, if the object has been removed
	 */
	public static boolean unset(Pointer ptr){
		if (ptr == null)
			return false;
		if (ptr.getPointedFeature().isMany()){
			List list = (List)getValue(ptr.getPointedContainer(), ptr.getPointedFeature(), -1);
			int listIndex = -1;
			if (ptr instanceof FeaturePointer){ //goes also for pointerchain
				listIndex = ((FeaturePointer)ptr).getListIndex();
			}
			if (listIndex < 0) //remove the whole list
				list.clear();
			else
				list.remove(listIndex);
			return true;
		}else{
			//simple case, just set to null
			//TODO: need to check for primitive types?
			ptr.setValue(null);
			return true;
		}
	}
	
	
	/**
	 * Checks if a new value can be set at the pointed position. 
	 * A value can be set if: 
	 * - the pointer points to an list
	 * - the pointed element (not a list) is null
	 * @param ptr Pointer to be checked
	 * @return true, if a new value can be set, without deleting anything 
	 */
	public static boolean canSet(Pointer ptr) {
		if (ptr == null || ptr.isValid() == false)
			return false;
		UStructuralFeature f = ptr.getPointedFeature();
		if (f.isMany())
			return true;
		return ptr.getValue() == null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//									Observation of Pointers and their Values												//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		
	/**
	 * @brief Observes the full chain of a pointer, e.g. if anything within the chain from root to the pointed object changes, the listener gets notified
	 * 
	 * - get a notification if the value of a pointer changes
	 * - get a notification if one of the elements between root and the pointed value changes (and thus potentially the value itself)
	 * - takes care that after changing an instance within the pointed chain, the listener is remvoed from all removed instances, and added to all new instances
	 * @param ptr pointer to be observed
	 * @param listener listener that shall be notified
	 * @return a PointerChangeListener that will be registered within the pointer chain and does the listener management
	 * 
	 * @warn remember the returned PointerChangeListener, to stop the observation @see removePointerObserver
	 * @warn remember to remove the observer if no longer needed, to save memory and computation time
	 */
	public static IDisposable observePointer(Pointer ptr, IValueChangeListener listener){
		return PointerObserver.registerListener(ptr, listener);
	}
	
	
	/**
	 * @brief Observes the full chain of a pointer, e.g. if anything within the chain from root to the pointed object changes, the listener gets notified
	 * 
	 * - get a notification if the value of a pointer changes
	 * - get a notification if one of the elements between root and the pointed value changes (and thus potentially the value itself)
	 * - takes care that after changing an instance within the pointed chain, the listener is remvoed from all removed instances, and added to all new instances
	 * @param ptr pointer to be observed
	 * @param listener listener that shall be notified
	 * @return a PointerChangeListener that will be registered within the pointer chain and does the listener management
	 * 
	 * @warn remember the returned PointerChangeListener, to stop the observation @see removePointerObserver
	 * @warn remember to remove the observer if no longer needed, to save memory and computation time
	 */
	public static IDisposable observePointer(Pointer ptr, ITreeValueChangeListener listener){
		return PointerObserver.registerListener(ptr, listener);
	}
	
	
	
	/**
	 * @brief Observes the full chain of a pointer, e.g. if anything within the chain from root to the pointed object changes, the listener gets notified
	 * 
	 * - get a notification if the value of a pointer changes
	 * - get a notification if one of the elements between root and the pointed value changes (and thus potentially the value itself)
	 * - takes care that after changing an instance within the pointed chain, the listener is remvoed from all removed instances, and added to all new instances
	 * @param ptr pointer to be observed
	 * @param listener listener that shall be notified
	 * @return a PointerChangeListener that will be registered within the pointer chain and does the listener management
	 * 
	 * @warn remember to remove the observer if no longer needed, to save memory and computation time
	 * @deprecated use the observePointer(Pointer, IValueChangeListener) or observePointer(Pointer, ITreeValueChangeListener) methods instead
	 */
	@Deprecated
	public static void observePointer(Pointer ptr, PointerChangeListener listener){
		//extract the pointer chain
		listener.register(PointerChangeListener.getForwardPointerChain(ptr)); //nullpointer is wanted
	}
	
	/**
	 * @brief stop observing a pointer 
	 * the PointerChangeListener l will not be notified if an value changes 
	 * @param ptr
	 * @param l
	 * @deprecated use the returned IDisposable to remove the listener
	 */
	@Deprecated
	public static void removePointerObserver(Pointer ptr, PointerChangeListener l){
		l.remove(l.mFPC);
	}

	
	
	
	
	
	
	
	

	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//													Other																	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Creates a string that could be read by <code>createPointerFromString(UObject, String)</code>
	 * 
	 * @param ptr pointer that should be written as string
	 * @return pointer string, that can be read by createPointerFromString(UObject, String) or null if ptr is an ObjectPointer
	 */
	public static String toPointerString(Pointer ptr) {
		return PointerStrings.toPointerString(ptr);
	}
	


	/**
	 * Returns the root element of this pointer
	 * that is: 
	 * - theInstance if it is an ObjectPointer or an FeaturePointer
	 * - the topmost instance if its an PointerChain
	 * @param ptr
	 * @return
	 */
	public static UObject getPointerRoot(Pointer ptr) {
		if (ptr instanceof PointerChain) {
			return getPointerRoot(((PointerChain)ptr).getParent());
		}
		if (ptr instanceof FeaturePointer) {
			return ((FeaturePointer)ptr).getTheInstance();
		}
		if (ptr instanceof ObjectPointer)
			return ((ObjectPointer)ptr).getTheInstance();
		return null;
	}

	
	/** 
	 * Search for a common parent between the pointer and the given instance (owner) and bend the pointer to start from the common base. 
	 * The base has to fullfill the following rules
	 * 1. not the owner
	 * 2. not the pointed value
	 * 4. parent of both, ptr and owner
	 * 
	 * @param ptr pointer from root
	 * @param owner may not be a struct, e.g. need a valid path to root
	 * @return
	 */
	public static Pointer findCommonBase(Pointer ptr, UObject owner) {
		Pointer ownerRootPtr = createPointerFromRoot(owner);
		if (ptr == null || ownerRootPtr == null)
			return null;
		UObject ptrRoot = getPointerRoot(ownerRootPtr);
		UObject ownerRoot = getPointerRoot(ownerRootPtr);
		if (ptrRoot != ownerRoot)
			return null;
		String ptrStr = toPointerString(ptr);
		String ownerStr = toPointerString(ownerRootPtr);
		if (ptrStr == null || ptrStr.isEmpty() || ownerStr == null || ownerStr.isEmpty())
			return null;
		QualifiedName ptrQn = new QualifiedNameImpl(ptrStr.split("\\."));
		QualifiedName ownerQn = new QualifiedNameImpl(ownerStr.split("\\."));
		int l = Math.min(ptrQn.numSegments(), ownerQn.numSegments());
		
		QualifiedNameImpl root = new QualifiedNameImpl();
		for (int i = 0; i < l-1; i++) { //we want a pointer that is not an object pointer
			String ptrSeg = ptrQn.firstSegment();
			String ownerSeg = ownerQn.segment(i);
			if (ptrSeg.equals(ownerSeg)) {
				root.localAppendBack(ptrSeg);
				ptrQn.localRemoveSegmentsFromStart(1);
			}else
				break;
		}
		if (root.numSegments() >= 1 && ptrQn.isEmpty() == false) {
			UObject newRoot = PointerOperations.createPointerFromString(ptrRoot, root.toString(".")).getUObject();
			assert(newRoot != null);
			return PointerOperations.createPointerFromString(newRoot, ptrQn.toString("."));
		}
		
		return null;
	}

	
	

	


}
