package test.de.emir.tuml.ucore.runtime.utils.sanity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProvider;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderImpl;

/**
 * @generated
 */
public class QualifiedNameProviderImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        QualifiedNameProviderImplTest test = new QualifiedNameProviderImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedNameProvider _new_operator = new QualifiedNameProviderImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = UtilsPackage.Literals.QualifiedNameProvider.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof QualifiedNameProvider);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedNameProvider _obj = new QualifiedNameProviderImpl();

        // Set values

        // Get values and compare them
    }
}
