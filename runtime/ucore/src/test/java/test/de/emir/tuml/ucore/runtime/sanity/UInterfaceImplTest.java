package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UInterfaceImpl;
import de.emir.tuml.ucore.runtime.impl.UOperationImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;

/**
 * @generated
 */
public class UInterfaceImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UInterfaceImplTest test = new UInterfaceImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UInterface _new_operator = new UInterfaceImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UInterface.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UInterface);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UInterface _obj = new UInterfaceImpl();

        // Set values
        UClassifier _interfaces_value = new UInterfaceImpl();
        _obj.uAdd(RuntimePackage.Literals.UClassifier_interfaces, _interfaces_value);
        UOperation _operations_value = new UOperationImpl();
        _obj.uAdd(RuntimePackage.Literals.UClassifier_operations, _operations_value);
        String _name_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UNamedElement_name, _name_value);
        String _documentation_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModelElement_documentation, _documentation_value);
        UAnnotation _annotations_value = new UAnnotationImpl();
        _obj.uAdd(RuntimePackage.Literals.UModelElement_annotations, _annotations_value);
        UPackage _package_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModelElement_package, _package_value);

        // Get values and compare them
        Object _interfaces_get_obj = _obj.uGet(RuntimePackage.Literals.UClassifier_interfaces, 0);
        assertNotNull(_interfaces_get_obj);
        assertTrue(_interfaces_get_obj instanceof UClassifier);
        assertEquals(_interfaces_get_obj, _interfaces_value);
        Object _operations_get_obj = _obj.uGet(RuntimePackage.Literals.UClassifier_operations, 0);
        assertNotNull(_operations_get_obj);
        assertTrue(_operations_get_obj instanceof UOperation);
        assertEquals(_operations_get_obj, _operations_value);
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
