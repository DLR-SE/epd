package test.de.emir.tuml.ucore.runtime.utils.sanity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;

/**
 * @generated
 */
public class ObjectPointerImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        ObjectPointerImplTest test = new ObjectPointerImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        ObjectPointer _new_operator = new ObjectPointerImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = UtilsPackage.Literals.ObjectPointer.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof ObjectPointer);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        ObjectPointer _obj = new ObjectPointerImpl();

        // Set values
        // FIXME: Could not find an instantiatable type for : ObjectPointer

        // Get values and compare them
        // FIXME: Could not find an instantiatable type for : UObject
    }
}
