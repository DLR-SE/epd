package de.emir.rcp.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.rcp.model.adapter.UModelAdapter;
import de.emir.rcp.model.adapter.UModelAdapter.AdapterOptions;
import de.emir.rcp.model.adapter.UModelAdapter.IObjectCallback;
import de.emir.rcp.properties.impl.ParameterProperty;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

public class OperationManager implements IExtensionPoint
{

	public static class CallableOperation {
		public final UOperation				operation;
		public final UObject				instance;
		
		public CallableOperation(UObject inst, UOperation op) {
			operation = op; instance = inst;
		}
		boolean isHidden() {
			return operation.getAnnotation("Hidden") != null;
		}
	}
	
	public static final String		PARAMETER_PROPERTY_BASE = "internal.";
	
	public String getOperationName(UOperation operation) {
        return operation != null ? operation.getName() : "Unknown";
	}

	public String getReturnTypeName(UOperation operation) {
		UParameter returnParameter = operation.getReturnParameter();
		UType returnType = returnParameter != null ? returnParameter.getType() : null;
        return returnType != null ? returnType.getName() : "void";
	}
	

	public List<IProperty> getInputParameterAsProperty(UObject instance, UOperation operation) {
		List<UParameter> parameters = getParameter(operation, UDirectionType.IN, UDirectionType.INOUT);
		//try to find the previous parameters
		ArrayList<IProperty> out = new ArrayList<>();
		for (UParameter param : parameters)
			out.add(buildProperty(instance, operation, param));
		return out;
	}

	private IProperty buildProperty(UObject instance, UOperation operation, UParameter param) {
		String propId = getPropertyId(operation, param);
		IProperty out = null;
		if (instance != null) {
			out = instance.getProperty(propId);
		}
		if (out == null) {
			out = new ParameterProperty(param);
			if (instance != null) {
				IProperty opPropGroup = getPropertyGroup(instance, operation);
				if (opPropGroup instanceof GenericProperty) {
					((GenericProperty)opPropGroup).addChild(out);
				}
			}
			if (TypeUtils.isPrimitive(param.getType().getRepresentingClass())) {
				//in this case, we need to create a default value
				UPrimitiveType pt = (UPrimitiveType) param.getType();
				out.setValue(pt.getDefaultValue());
			}
		}
		return out;
	}

	private IProperty getPropertyGroup(UObject instance, UOperation operation) {
		IProperty prop = instance.getProperty(PARAMETER_PROPERTY_BASE + operation.getName());
		if (prop == null) {
			prop = instance.addProperty(PARAMETER_PROPERTY_BASE + operation.getName(), "Parameter of Operation");
		}
		return prop;
	}

	private String getPropertyId(UOperation operation, UParameter param) {
		return PARAMETER_PROPERTY_BASE + operation.getName() + "." + param.getName();
	}

	private List<UParameter> getParameter(UOperation operation, UDirectionType...directionTypes) {
		if (operation == null) return null;
		ArrayList<UParameter> out = new ArrayList<>();
		for (UParameter p : operation.getParameters())
			if (matchOne(p, directionTypes))
				out.add(p);
		return out;
	}

	private boolean matchOne(UParameter p, UDirectionType[] directionTypes) {
		if (p == null || directionTypes == null || directionTypes.length == 0) return false;
		UDirectionType dt = p.getDirection();
		for (UDirectionType q : directionTypes) if (dt == q) return true;
		return false;
	}

	
	
	
	/**
	 * Returns all operations that fullfill one of the following two conditions: 
	 * 1) have an @UserCallable annotation
	 * 2) are defined in an ICapability
	 * 3) do NOT have an @Hidden annotation
	 * @param uobj
	 * @return
	 */
	public List<CallableOperation> getOperations(UObject uobj) {
		if (uobj == null) return null;
		UClass cl = uobj.getUClassifier();
		boolean isCap = cl.inherits(PhysicsPackage.Literals.ICapability);
		ArrayList<CallableOperation> out = new ArrayList<>();
		List<UOperation> allOps = isCap ? cl.getOperations() : cl.getAllOperations();
		for (UOperation op : allOps) {
			if (isCap) {
				out.add(new CallableOperation(uobj, op));
			}else {
				UAnnotation anno = op.getAnnotation("UserCallable");
				if (anno != null) {
					out.add(new CallableOperation(uobj, op));
				}
			}
		}
		UModelAdapter uma = new UModelAdapter();
		AdapterOptions options = new AdapterOptions(ICapability.class);
		options.maximumDepth = 1;
		uma.visitModel(uobj, new IObjectCallback() {			
			@Override
			public boolean accept(Object value, UObject parent, UStructuralFeature feature, int listIndex, int depth) {
				List<CallableOperation> list = getOperations((UObject)value); //cast should be fine, since it inherits ICapability
				if (list != null && list.isEmpty() == false)
					out.addAll(list);
				return false;
			}
		}, options);
		return out.stream()
				.filter( predicate -> !predicate.isHidden() )
				.collect(Collectors.toList()) 
				;
	}

	

	
}
