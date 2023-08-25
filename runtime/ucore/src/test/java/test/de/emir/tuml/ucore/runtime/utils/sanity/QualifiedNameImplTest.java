package test.de.emir.tuml.ucore.runtime.utils.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;

/**
 * @generated
 */
public class QualifiedNameImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        QualifiedNameImplTest test = new QualifiedNameImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedName _new_operator = new QualifiedNameImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = UtilsPackage.Literals.QualifiedName.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof QualifiedName);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedName _obj = new QualifiedNameImpl();

        // Set values
        String _segments_value = "Was ist 42?";
        _obj.uAdd(UtilsPackage.Literals.QualifiedName_segments, _segments_value);

        // Get values and compare them
        Object _segments_get_obj = _obj.uGet(UtilsPackage.Literals.QualifiedName_segments, 0);
        assertNotNull(_segments_get_obj);
        assertTrue(_segments_get_obj instanceof String);
        assertEquals(_segments_get_obj, _segments_value);
    }
}
