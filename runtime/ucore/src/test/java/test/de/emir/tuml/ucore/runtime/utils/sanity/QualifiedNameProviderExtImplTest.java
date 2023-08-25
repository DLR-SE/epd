package test.de.emir.tuml.ucore.runtime.utils.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProviderExt;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderExtImpl;

/**
 * @generated
 */
public class QualifiedNameProviderExtImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        QualifiedNameProviderExtImplTest test = new QualifiedNameProviderExtImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedNameProviderExt _new_operator = new QualifiedNameProviderExtImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = UtilsPackage.Literals.QualifiedNameProviderExt.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof QualifiedNameProviderExt);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        QualifiedNameProviderExt _obj = new QualifiedNameProviderExtImpl();

        // Set values
        UStructuralFeature _nameFeatures_value = new UStructuralFeatureImpl();
        _obj.uAdd(UtilsPackage.Literals.QualifiedNameProviderExt_nameFeatures, _nameFeatures_value);

        // Get values and compare them
        Object _nameFeatures_get_obj = _obj.uGet(UtilsPackage.Literals.QualifiedNameProviderExt_nameFeatures, 0);
        assertNotNull(_nameFeatures_get_obj);
        assertTrue(_nameFeatures_get_obj instanceof UStructuralFeature);
        assertEquals(_nameFeatures_get_obj, _nameFeatures_value);
    }
}
