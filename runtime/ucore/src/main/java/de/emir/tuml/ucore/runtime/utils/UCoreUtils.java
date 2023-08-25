package de.emir.tuml.ucore.runtime.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.visitor.ReferenceVisitor;

public class UCoreUtils {

	public static boolean equals(UObject obj1, UObject obj2) {
		if (obj1 == obj2)
			return true;
		if (obj1.getUClassifier() != obj2.getUClassifier())
			return false;
		for (UStructuralFeature f : obj1.getUClassifier().getAllStructuralFeatures()){
			if (f.getAggregation() == UAssociationType.AGGREGATION)
				continue; 
			Object v1 = f.get(obj1);
			Object v2 = f.get(obj2);
			if (v1 == null && v2 == null)
				continue;
			if (f.isMany()){
				if (!compareLists(v1, v2))
					return false;
			}else{
				if (!compare(v1, v2))
					return false;
			}
		}
		return true;
	}

	private static boolean compare(Object v1, Object v2) {
		if (v1.getClass() != v2.getClass())
			return false;
		if (v1 instanceof UObject)
			return equals((UObject)v1, (UObject)v2);
		return v1.equals(v2); //should call the underlaying equals method of primitive types
	}

	private static boolean compareLists(Object v1, Object v2) {
		List l1 = (List)v1;
		List l2 = (List)v2;
		if (l1.size() != l2.size())
			return false;
		for (int i = 0; i < l1.size(); i++)
			if (!compare(l1.get(i), l2.get(i)))
				return false;
		return true;
	}

	/**
	 * collects all instances, within the current object tree (of the pointed value) that could be assigned to the given pointer
	 * @param pointer
	 * @return
	 */
	public static Collection<UObject> collectPossibleReferences(Pointer pointer) {
		UObject pointed_obj = pointer.getPointedContainer(); 
		if (pointed_obj == null) return null;
		UObject root = getRoot(pointed_obj);
		if (root == null) return null;
		return collectTypedChildren(root, pointer.getExpectedType());
	}

	/**
	 * Collect all instances of the given type, that could be found in the subtree, defined by the root element
	 * @param root root of the subtree to search in
	 * @param type expected types
	 * @return a collection with all instances, that inherit from type
	 */
	public static <T extends UObject> Collection<T> collectTypedChildren(UObject root, final UType type) {
		final HashSet<T> result = new HashSet<>();
		UVisitorUtil.visit(root, new ReferenceVisitor() {
			@Override
			public boolean beginObject(UObject obj, UClass cl) {
				if (super.beginObject(obj, cl)){
					if (obj.getUClassifier().inherits(type))
						result.add((T)obj);
					return true; //forward result from super.beginObject()
				}
				return false; //forward result from super
			}
			@Override
			public void visit(UObject parent, UStructuralFeature feature, int list_index, Object value) {	} //we are only interested in the object itself
			
		});
		return result;
	}

	/**
	 * returns the topmost UObject of the tree this, value is assigned to 
	 * @param value
	 * @return
	 */
	public static UObject getRoot(UObject value) {
		UObject p = value.getUContainer();
		if (p == null)
			return value;
		int idx = 1000; //maximum depth - to avoid endless loops
		while(p.getUContainer() != null && idx-- > 0)
			p = p.getUContainer();
		if (idx <= 0)
			ULog.error("Detected Loop in hierarchie of: " + value);
		return p;
	}

	/**
	 * returns the nearest container in the hierarchy of obj that implements clazz
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static <T extends UObject> T getFirstParent(UObject obj, Class<T> clazz) {
		UObject p = obj.getUContainer();
		if (p == null)
			return null;
		while(p != null){
			if (TypeUtils.inherits(p.getClass(), clazz))
				return (T)p;
			p = p.getUContainer();
		}
		return null;
	}
	
	
	/**
	 * returns the nearest container in the hierarchy of obj that implements clazz
	 * @param obj
	 * @param clazz
	 * @return
	 */
	public static <T extends UObject> T getFirstParent(UObject obj, UClassifier clazz) {
		UObject p = obj.getUContainer();
		if (p == null)
			return null;
		while(p != null){
			if (p.getUClassifier().inherits(clazz))
				return (T)p;
			p = p.getUContainer();
		}
		return null;
	}

	/**
	 * Finds the first instance, that inherits the given class, within the subtree defined by the instance 
	 * @param instance
	 * @param clazz
	 * @param includeInherited if set to true, this method also returns instances, who's class differs from the given clazz, but inherit from clazz
	 * @return
	 */
	public static <T extends UObject> T firstInstance(UObject instance, Class<T> clazz, boolean includeInherited) {
		return _firstInstance(instance, clazz, includeInherited, new HashSet<>());
	}
	
	private static <T extends UObject> T _firstInstance(UObject instance, Class<T> clazz, boolean includeInherited, Set<UObject> visited) {
		if (instance == null)
			return null;
		if (instance.getClass() == clazz)
			return (T)instance;
		if (visited.contains(instance))
			return null;
		visited.add(instance);

		if (includeInherited && TypeUtils.inherits(instance.getClass(), clazz))
			return (T)instance;

		//TODO: remove recursive call
		Iterable<UObject> iter = instance.getContentIterator();
		for (UObject c : iter){
			T v = _firstInstance(c, clazz, includeInherited, visited);
			if (v != null)
				return v;
		}
		return null;
	}

	public static <T extends UObject, U extends T> Collection<T> collectTypedChildren(UObject obj, Class<U> clazz) {
		UClassifier cl = UCoreMetaRepository.findClassifier(clazz);
		if (cl == null || obj == null) return null;
		return collectTypedChildren(obj, cl);
	}

	public static <T extends UObject, U extends T> List<T> collectTypedChildrenList(UObject obj, Class<U> class1) {
		Collection<T> coll = collectTypedChildren(obj, class1);
		if (coll != null && coll.isEmpty() == false){
			ArrayList<T> out = new ArrayList<T>();
			out.addAll(coll);
			return out;
		}
		return null;
	}

	/**
	 * Copies an instance of a UObject
	 * <br>
	 * takes care about composite features. Each composit feature is also copied, all other features, are simply assigned. 
	 * @param orig instance to copy
	 * @return copy of orig
	 */
	public static <T extends UObject> T copy(T orig) {
		UClass cl = orig.getUClassifier();
		UObject newInstance = cl.createNewInstance();
		for (UStructuralFeature f : cl.getAllStructuralFeatures()){
			if (f.isMany()){
				List l = (List)f.get(orig);
				List nl = (List)f.get(newInstance); 
				nl.clear(); //remove standard values
				for (Object v : l){
					copy_SetAttribute(newInstance, f, v, nl, false, false);
				}
			}else{
				Object value = f.get(orig);
				copy_SetAttribute(newInstance, f, value, null, false, false);
			}
		}
		return (T)newInstance;
	}
	
	/**
	 * creates a deep copy of the object that includes a copy of all Composite values
	 * @param copyProperties if set to true, properties will also be copied
	 * @param copyAssociations if set to true, associations (e.g. UStructuralFeature.getAggreggationType() == Association) will also be copied
	 * @param orig
	 * @return a copy of the original value that would return true on orig.equals(copy) but false on orig == copy
	 * 
	 * @warn setting copyProperties or copyAssociations may result in large copy operations, since the method is called recursive
	 */
	public static <T extends UObject> T deepCopy(T orig, boolean copyProperties, boolean copyAssociations) {
		UClass cl = orig.getUClassifier();
		UObject newInstance = cl.createNewInstance();
		for (UStructuralFeature f : cl.getAllStructuralFeatures()){
			if (f.isMany()){
				List l = (List)f.get(orig);
				List nl = (List)f.get(newInstance); 
				nl.clear(); //remove standard values
				for (Object v : l){
					copy_SetAttribute(newInstance, f, v, nl, copyProperties, copyAssociations);
				}
			}else{
				Object value = f.get(orig);
				copy_SetAttribute(newInstance, f, value, null, copyProperties, copyAssociations);
			}
		}
		return (T)newInstance;
	}

	private static void copy_SetAttribute(UObject newInstance, UStructuralFeature feature, Object value, List targetList, boolean copyProperties, boolean copyAssociations) {
		if (feature.isReadOnly()) return ;
		boolean needCopy = feature.getAggregation() == UAssociationType.COMPOSITION; //composites are always copied
		if (!needCopy && copyProperties && feature.getAggregation() == UAssociationType.PROPERTY) 
			needCopy = true;
		if (!needCopy && copyAssociations && feature.getAggregation() == UAssociationType.ASSOCIATION)
			needCopy = true;
		if (value == null || value instanceof UObject == false) //in those cases we do not need or can not copy
			needCopy = false;
		
		if (needCopy){
			UObject value_cpy = deepCopy((UObject)value, copyProperties, copyAssociations);
			if (targetList != null) //it's a list feature
				targetList.add(value_cpy);
			else
				feature.set(newInstance, value_cpy);
		}else{ //it's not a containment, therefore we can simply assign the value
			if (targetList != null)
				targetList.add(value);
			else
				feature.set(newInstance, value);
		}
	}

	

}
