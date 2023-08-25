package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumeratorImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;

/**
 * @generated
 */
public class UEnumeratorImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UEnumeratorImplTest test = new UEnumeratorImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UEnumerator _new_operator = new UEnumeratorImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UEnumerator.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UEnumerator);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UEnumerator _obj = new UEnumeratorImpl();

        // Set values
        int _value_value = 42;
        _obj.uSet(RuntimePackage.Literals.UEnumerator_value, _value_value);
        String _name_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UNamedElement_name, _name_value);
        String _documentation_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModelElement_documentation, _documentation_value);
        UAnnotation _annotations_value = new UAnnotationImpl();
        _obj.uAdd(RuntimePackage.Literals.UModelElement_annotations, _annotations_value);
        UPackage _package_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModelElement_package, _package_value);

        // Get values and compare them
        Object _value_get_obj = _obj.uGet(RuntimePackage.Literals.UEnumerator_value);
        assertNotNull(_value_get_obj);
        assertTrue(_value_get_obj instanceof Integer);
        assertEquals((int) _value_get_obj, _value_value);
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
