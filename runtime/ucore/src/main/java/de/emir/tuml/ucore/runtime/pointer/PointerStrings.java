package de.emir.tuml.ucore.runtime.pointer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.TypePointer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.TypePointerImpl;
import org.apache.logging.log4j.Logger;


/**
 * Utility class to provide pointers from String
 * 
 * \see \ref Pointer
 * @author sschweigert
 * 
 * @note this class is tested in the Physics Project (see test.de.emir.tuml.ucore.runtime.pointer.PointerStringsTest)
 *
 */
public class PointerStrings {
	
	private static final Logger logger = ULog.getLogger(PointerStrings.class);
	
	private static final Pattern 					mAccessorPattern = Pattern.compile("(?:->)?(\\w*)(?:<(\\w*)>)?(\\(\\))?(?::(\\d*))?");
	
	private static final int 						FEATURE_GROUP = 1;
	private static final int 						TYPE_GROUP = 2;
	private static final int 						METHOD_INDICATOR_GROUP = 3; //indicates that a method shall be called, e.g. "->" before the operations name
	private static final int 						INDEX_GROUP = 4;
		
	
	
	/**
	 * Checks if the string follows a syntax to describe a pointer. For this purpose the string has to follow this
	 * BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @param mPointerString
	 * @return
	 */
	public static boolean syntaxCheckPointerString(String str) {
		String[] segments = str.replace(',', '.').trim().split("\\.");
		for (String seg : segments)
			if (!syntaxCheckPointerStringSegment(seg))
				return false;
		return true;
	}


	private static boolean syntaxCheckPointerStringSegment(String seg) {
		return mAccessorPattern.matcher(seg).matches();
	}


	public static Pointer create(UObject root, String pointerString) {
		if (root == null)
			return null;
		return create(new ObjectPointerImpl(root), pointerString);
	}
	public static Pointer create(Pointer parent, String pointerString) {
		if (pointerString == null || pointerString.isEmpty())
			return parent;
		String[] segments = pointerString.replace(',', '.').trim().split("\\.");
		Pointer ptr = parent.copy();
		
		for (int i = 0; i < segments.length; i++) {
			UType parentType = ptr.getType();
			if (parentType == null) parentType = ptr.getExpectedType(); //fallback, if we have a currently invalid pointer (for example if a TypePointer is within the chain)
			if (parentType instanceof UClassifier == false)
				return null; //if not a classifier we can not find any operations or features inside
			UClassifier cl = (UClassifier)parentType;
			Matcher matcher = mAccessorPattern.matcher(segments[i]);
			if (!matcher.find()) {
				if (logger.isDebugEnabled()) {logger.debug("Could not find segment {} in type: {}", segments[i], cl.getName());}
				return null; //if we can not return the full pointer, we return null
			}
			String featureOrOperationName = matcher.group(FEATURE_GROUP);
			String expectedTypeStr = matcher.group(TYPE_GROUP);
			boolean isFeature = matcher.group(METHOD_INDICATOR_GROUP) == null;
			IStructuralElement element = isFeature ? cl.getFeature(featureOrOperationName) : cl.getOperation(featureOrOperationName);
			String listIdxStr = matcher.group(INDEX_GROUP);
			int listIndex = -1;
			if (listIdxStr != null) 
				try {
					listIndex = Integer.parseInt(listIdxStr);
				}catch(Exception e) {
					if (logger.isDebugEnabled()) logger.debug("Could not cast {} to list index", listIdxStr);
					return null;
				}
			
			UType expectedType = expectedTypeStr == null ? null : UCoreMetaRepository.findClassifierBySimpleName(expectedTypeStr);
			//now that we have all information we could get from the string, we need to check them and may 
			if (element == null) {
				if (featureOrOperationName.toLowerCase().startsWith("ucomponent") && expectedType != null) {
					ptr = new TypePointerImpl(ptr, expectedType, listIndex);
					continue; 
				}
				if (!isFeature && featureOrOperationName.startsWith("get")) {
					//in case of an operation we also allow getter, so we try to remove the "get" and convert the name to lower camel case
					char c = featureOrOperationName.charAt(3);
					featureOrOperationName = Character.toLowerCase(c) + featureOrOperationName.substring(4);
					element = cl.getFeature(featureOrOperationName);
				}
				if (element == null) {
					if (logger.isDebugEnabled()) { logger.debug("Classifier {} does not own a {} with name {}", cl.getName(), isFeature?"feature":"operation", featureOrOperationName);	}
					return null;
				}
			}
			
			if (listIndex > 0 && element.isMany() == false) {
				logger.info("Feature {} of {} is not a list, skipping list index of {}", element.getName(), cl.getName(), listIndex);
				listIndex = -1;
			}
			ptr = PointerOperations.createChain(ptr, element, listIndex);
			
			//check if the types match - at least if an expected type has been provided
			if (expectedType != null) {
				UType ptrType = ptr.getType();
				if (!ptrType.inherits(expectedType)) {
					logger.warn("The type of pointer {} == {} does not match the expected type of {}", ptr, ptrType.getName(), expectedTypeStr);
					return null;
				}
			}
		}
		return ptr;
	}


	/**
	 * Creates a string that could be read by <code>createPointerFromString(UObject, String)</code>
	 * 
	 * @param ptr pointer that should be written as string
	 * @return pointer string, that can be read by createPointerFromString(UObject, String) or null if ptr is an ObjectPointer
	 */
	public static String toPointerString(Pointer ptr) {
		if (ptr instanceof ObjectPointer)
			return null; 
		String str = "";
		if (ptr instanceof PointerChain){
			String tmp = toPointerString(((PointerChain)ptr).getParent());
			if (tmp != null)
				str += tmp + ".";			
		}
		if (ptr instanceof FeaturePointer){
			str += ((FeaturePointer)ptr).getFeature().getName();
			int lidx = ((FeaturePointer)ptr).getListIndex();
			if (lidx >= 0)
				str += ":" + lidx;
		}
		if (ptr instanceof TypePointer) {
			String tmp = toPointerString(((TypePointer)ptr).getParent());
			if (tmp != null)
				str += tmp + ".";
			str += "UComponent<" + ((TypePointer)ptr).getExpectedType().getName() + ">";
			int lidx = ((TypePointer)ptr).getListIndex();
			if (lidx >= 0)
				str += ":" + lidx;
		}
		return str;
	}



}
