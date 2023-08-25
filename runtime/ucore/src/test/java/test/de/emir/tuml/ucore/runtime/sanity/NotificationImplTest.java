package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.NotificationImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;

/**
 * @generated
 */
public class NotificationImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        NotificationImplTest test = new NotificationImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        Notification _new_operator = new NotificationImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.Notification.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof Notification);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        Notification _obj = new NotificationImpl();

        // Set values
        // FIXME: Could not find an instantiatable type for : Notification
        // FIXME: Could not find an instantiatable type for : Notification
        UStructuralFeature _feature_value = new UStructuralFeatureImpl();
        _obj.uSet(RuntimePackage.Literals.Notification_feature, _feature_value);

        // Get values and compare them
        // FIXME: Could not find an instantiatable type for : NotificationType
        // FIXME: Could not find an instantiatable type for : UObject
        Object _feature_get_obj = _obj.uGet(RuntimePackage.Literals.Notification_feature);
        assertNotNull(_feature_get_obj);
        assertTrue(_feature_get_obj instanceof UStructuralFeature);
        assertEquals(_feature_get_obj, _feature_value);
    }
}
