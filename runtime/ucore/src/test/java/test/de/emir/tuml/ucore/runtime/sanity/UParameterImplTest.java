package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UMultiplicityImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.impl.UParameterImpl;
import de.emir.tuml.ucore.runtime.impl.UPrimitiveTypeImpl;

/**
 * @generated
 */
public class UParameterImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UParameterImplTest test = new UParameterImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UParameter _new_operator = new UParameterImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UParameter.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UParameter);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UParameter _obj = new UParameterImpl();

        // Set values
        // FIXME: Could not find an instantiatable type for : UParameter
        UType _type_value = new UPrimitiveTypeImpl();
        _obj.uSet(RuntimePackage.Literals.UTypedElement_type, _type_value);
        UMultiplicity _multiplicity_value = new UMultiplicityImpl();
        _obj.uSet(RuntimePackage.Literals.UTypedElement_multiplicity, _multiplicity_value);
        String _name_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UNamedElement_name, _name_value);
        String _documentation_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModelElement_documentation, _documentation_value);
        UAnnotation _annotations_value = new UAnnotationImpl();
        _obj.uAdd(RuntimePackage.Literals.UModelElement_annotations, _annotations_value);
        UPackage _package_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModelElement_package, _package_value);

        // Get values and compare them
        // FIXME: Could not find an instantiatable type for : UDirectionType
        Object _type_get_obj = _obj.uGet(RuntimePackage.Literals.UTypedElement_type);
        assertNotNull(_type_get_obj);
        assertTrue(_type_get_obj instanceof UType);
        assertEquals(_type_get_obj, _type_value);
        Object _multiplicity_get_obj = _obj.uGet(RuntimePackage.Literals.UTypedElement_multiplicity);
        assertNotNull(_multiplicity_get_obj);
        assertTrue(_multiplicity_get_obj instanceof UMultiplicity);
        assertEquals(_multiplicity_get_obj, _multiplicity_value);
        Object _name_get_obj = _obj.uGet(RuntimePackage.Literals.UNamedElement_name);
        assertNotNull(_name_get_obj);
        assertTrue(_name_get_obj instanceof String);
        assertEquals(_name_get_obj, _name_value);
        Object _documentation_get_obj = _obj.uGet(RuntimePackage.Literals.UModelElement_documentation);
        assertNotNull(_documentation_get_obj);
        assertTrue(_documentation_get_obj instanceof String);
        assertEquals(_documentation_get_obj, _documentation_value);
        Object _annotations_get_obj = _obj.uGet(RuntimePackage.Literals.UModelElement_annotations, 0);
        assertNotNull(_annotations_get_obj);
        assertTrue(_annotations_get_obj instanceof UAnnotation);
        assertEquals(_annotations_get_obj, _annotations_value);
        Object _package_get_obj = _obj.uGet(RuntimePackage.Literals.UModelElement_package);
        assertNotNull(_package_get_obj);
        assertTrue(_package_get_obj instanceof UPackage);
        assertEquals(_package_get_obj, _package_value);
    }
}
