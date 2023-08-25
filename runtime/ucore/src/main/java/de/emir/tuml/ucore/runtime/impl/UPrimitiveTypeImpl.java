package de.emir.tuml.ucore.runtime.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import de.emir.tuml.ucore.runtime.BinaryBlob;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.impl.UTypeImpl;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 * @generated
 */
@UMLImplementation(classifier = UPrimitiveType.class)
public class UPrimitiveTypeImpl extends UTypeImpl implements UPrimitiveType {

    /**
     * Default constructor
     * 
     * @generated
     */
    public UPrimitiveTypeImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UPrimitiveTypeImpl(final UPrimitiveType _copy) {
        super(_copy);
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UPrimitiveTypeImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name) {
        super(_documentation, _annotations, _package, _name);
    }

    public UPrimitiveTypeImpl(Class<?> clazz) {
        super(clazz, clazz.getSimpleName());
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UPrimitiveType;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public void build() {
        // TODO:
        //
        // * initializes the model element, e.g. create private member for reflection access
        //
        throw new UnsupportedOperationException("build not yet implemented");
    }

    @Override
    public boolean inherits(UType query) {
        return query == this || TypeUtils.inherits(query.getRepresentingClass(), getRepresentingClass());
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UPrimitiveTypeImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

    @Override
    public Object parseString(String value) {
        if (mClazz == String.class)
            return value;
        if (mClazz == int.class || mClazz == Integer.class)
            return Integer.parseInt(value);
        if (mClazz == double.class || mClazz == Double.class)
            return Double.parseDouble(value);
        if (mClazz == boolean.class || mClazz == Boolean.class)
            return Boolean.parseBoolean(value);
        if (mClazz == float.class || mClazz == Float.class)
            return Float.parseFloat(value);
        if (mClazz == byte.class || mClazz == Byte.class)
            return Byte.parseByte(value);
        if (mClazz == short.class || mClazz == Short.class)
            return Short.parseShort(value);
        if (mClazz == long.class || mClazz == Long.class)
            return Long.parseLong(value);
        if (mClazz == char.class || mClazz == Character.class)
            return value.charAt(0);
        if (mClazz == BinaryBlob.class) {
        	return new BinaryBlob(Base64.getDecoder().decode(value));
        }
        return null;
    }
    
    @Override
    public String toString(Object value) {
    	if (value == null) return null;
    	if (mClazz == BinaryBlob.class)
			return Base64.getEncoder().encodeToString(((BinaryBlob)value).get());
    	return value.toString();
    }

    @Override
    public Object getDefaultValue() {
        if (mClazz == String.class)
            return "";
        if (mClazz == int.class || mClazz == Integer.class)
            return Integer.valueOf(0);
        if (mClazz == double.class || mClazz == Double.class)
            return Double.valueOf(0);
        if (mClazz == boolean.class || mClazz == Boolean.class)
            return Boolean.valueOf(false);
        if (mClazz == float.class || mClazz == Float.class)
            return Float.valueOf(0);
        if (mClazz == byte.class || mClazz == Byte.class)
            return Byte.valueOf((byte) 0);
        if (mClazz == short.class || mClazz == Short.class)
            return Short.valueOf((short) 0);
        if (mClazz == long.class || mClazz == Long.class)
            return Long.valueOf(0);
        if (mClazz == char.class || mClazz == Character.class)
            return Character.valueOf(' ');
        return null;
    }
}
