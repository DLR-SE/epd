package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumeratorImpl;
import de.emir.tuml.ucore.runtime.impl.UInterfaceImpl;
import de.emir.tuml.ucore.runtime.impl.UOperationImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;

/**
 * @generated
 */
public class UEnumImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UEnumImplTest test = new UEnumImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UEnum _new_operator = new UEnumImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UEnum.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UEnum);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UEnum _obj = new UEnumImpl();

        // Set values
        UEnumerator _literals_value = new UEnumeratorImpl();
        _obj.uAdd(RuntimePackage.Literals.UEnum_literals, _literals_value);
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
        Object _literals_get_obj = _obj.uGet(RuntimePackage.Literals.UEnum_literals, 0);
        assertNotNull(_literals_get_obj);
        assertTrue(_literals_get_obj instanceof UEnumerator);
        assertEquals(_literals_get_obj, _literals_value);
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
