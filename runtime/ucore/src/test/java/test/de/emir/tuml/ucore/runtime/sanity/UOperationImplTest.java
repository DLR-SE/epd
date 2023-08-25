package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UOperationImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.impl.UParameterImpl;

/**
 * @generated
 */
public class UOperationImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UOperationImplTest test = new UOperationImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UOperation _new_operator = new UOperationImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UOperation.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UOperation);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UOperation _obj = new UOperationImpl();

        // Set values
        boolean _abstract_value = true;
        _obj.uSet(RuntimePackage.Literals.UOperation_abstract, _abstract_value);
        boolean _const_value = true;
        _obj.uSet(RuntimePackage.Literals.UOperation_const, _const_value);
        UParameter _parameters_value = new UParameterImpl();
        _obj.uAdd(RuntimePackage.Literals.UOperation_parameters, _parameters_value);
        String _name_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UNamedElement_name, _name_value);
        String _documentation_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModelElement_documentation, _documentation_value);
        UAnnotation _annotations_value = new UAnnotationImpl();
        _obj.uAdd(RuntimePackage.Literals.UModelElement_annotations, _annotations_value);
        UPackage _package_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModelElement_package, _package_value);

        // Get values and compare them
        Object _abstract_get_obj = _obj.uGet(RuntimePackage.Literals.UOperation_abstract);
        assertNotNull(_abstract_get_obj);
        assertTrue(_abstract_get_obj instanceof Boolean);
        assertEquals((boolean) _abstract_get_obj, _abstract_value);
        Object _const_get_obj = _obj.uGet(RuntimePackage.Literals.UOperation_const);
        assertNotNull(_const_get_obj);
        assertTrue(_const_get_obj instanceof Boolean);
        assertEquals((boolean) _const_get_obj, _const_value);
        Object _parameters_get_obj = _obj.uGet(RuntimePackage.Literals.UOperation_parameters, 0);
        assertNotNull(_parameters_get_obj);
        assertTrue(_parameters_get_obj instanceof UParameter);
        assertEquals(_parameters_get_obj, _parameters_value);
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
