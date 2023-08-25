package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UAnnotationDetailImpl;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;

/**
 * @generated
 */
public class UAnnotationImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UAnnotationImplTest test = new UAnnotationImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UAnnotation _new_operator = new UAnnotationImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UAnnotation.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UAnnotation);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UAnnotation _obj = new UAnnotationImpl();

        // Set values
        String _name_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UAnnotation_name, _name_value);
        UAnnotationDetail _details_value = new UAnnotationDetailImpl();
        _obj.uAdd(RuntimePackage.Literals.UAnnotation_details, _details_value);

        // Get values and compare them
        Object _name_get_obj = _obj.uGet(RuntimePackage.Literals.UAnnotation_name);
        assertNotNull(_name_get_obj);
        assertTrue(_name_get_obj instanceof String);
        assertEquals(_name_get_obj, _name_value);
        Object _details_get_obj = _obj.uGet(RuntimePackage.Literals.UAnnotation_details, 0);
        assertNotNull(_details_get_obj);
        assertTrue(_details_get_obj instanceof UAnnotationDetail);
        assertEquals(_details_get_obj, _details_value);
    }
}
