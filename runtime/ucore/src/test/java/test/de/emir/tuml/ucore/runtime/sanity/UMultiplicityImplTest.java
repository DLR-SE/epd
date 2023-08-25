package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UMultiplicityImpl;

/**
 * @generated
 */
public class UMultiplicityImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UMultiplicityImplTest test = new UMultiplicityImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UMultiplicity _new_operator = new UMultiplicityImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UMultiplicity.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UMultiplicity);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UMultiplicity _obj = new UMultiplicityImpl();

        // Set values
        int _lower_value = 42;
        _obj.uSet(RuntimePackage.Literals.UMultiplicity_lower, _lower_value);
        int _upper_value = 42;
        _obj.uSet(RuntimePackage.Literals.UMultiplicity_upper, _upper_value);

        // Get values and compare them
        Object _lower_get_obj = _obj.uGet(RuntimePackage.Literals.UMultiplicity_lower);
        assertNotNull(_lower_get_obj);
        assertTrue(_lower_get_obj instanceof Integer);
        assertEquals((int) _lower_get_obj, _lower_value);
        Object _upper_get_obj = _obj.uGet(RuntimePackage.Literals.UMultiplicity_upper);
        assertNotNull(_upper_get_obj);
        assertTrue(_upper_get_obj instanceof Integer);
        assertEquals((int) _upper_get_obj, _upper_value);
    }
}
