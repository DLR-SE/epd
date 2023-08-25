package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UAnnotationDetailImpl;

/**
 * @generated
 */
public class UAnnotationDetailImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UAnnotationDetailImplTest test = new UAnnotationDetailImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UAnnotationDetail _new_operator = new UAnnotationDetailImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UAnnotationDetail.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UAnnotationDetail);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UAnnotationDetail _obj = new UAnnotationDetailImpl();

        // Set values
        String _key_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UAnnotationDetail_key, _key_value);
        String _value_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UAnnotationDetail_value, _value_value);

        // Get values and compare them
        Object _key_get_obj = _obj.uGet(RuntimePackage.Literals.UAnnotationDetail_key);
        assertNotNull(_key_get_obj);
        assertTrue(_key_get_obj instanceof String);
        assertEquals(_key_get_obj, _key_value);
        Object _value_get_obj = _obj.uGet(RuntimePackage.Literals.UAnnotationDetail_value);
        assertNotNull(_value_get_obj);
        assertTrue(_value_get_obj instanceof String);
        assertEquals(_value_get_obj, _value_value);
    }
}
