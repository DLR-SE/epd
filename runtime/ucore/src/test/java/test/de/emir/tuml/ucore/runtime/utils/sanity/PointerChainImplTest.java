package test.de.emir.tuml.ucore.runtime.utils.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.PointerChainImpl;

/**
 * @generated
 */
public class PointerChainImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        PointerChainImplTest test = new PointerChainImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        PointerChain _new_operator = new PointerChainImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = UtilsPackage.Literals.PointerChain.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof PointerChain);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        PointerChain _obj = new PointerChainImpl();

        // Set values
        Pointer _parent_value = new ObjectPointerImpl();
        _obj.uSet(UtilsPackage.Literals.PointerChain_parent, _parent_value);
        int _listIndex_value = 42;
        _obj.uSet(UtilsPackage.Literals.FeaturePointer_listIndex, _listIndex_value);
        // FIXME: Could not find an instantiatable type for : PointerChain
        UStructuralFeature _feature_value = new UStructuralFeatureImpl();
        _obj.uSet(UtilsPackage.Literals.FeaturePointer_feature, _feature_value);

        // Get values and compare them
        Object _parent_get_obj = _obj.uGet(UtilsPackage.Literals.PointerChain_parent);
        assertNotNull(_parent_get_obj);
        assertTrue(_parent_get_obj instanceof Pointer);
        assertEquals(_parent_get_obj, _parent_value);
        Object _listIndex_get_obj = _obj.uGet(UtilsPackage.Literals.FeaturePointer_listIndex);
        assertNotNull(_listIndex_get_obj);
        assertTrue(_listIndex_get_obj instanceof Integer);
        assertEquals((int) _listIndex_get_obj, _listIndex_value);
        // FIXME: Could not find an instantiatable type for : UObject
        Object _feature_get_obj = _obj.uGet(UtilsPackage.Literals.FeaturePointer_feature);
        assertNotNull(_feature_get_obj);
        assertTrue(_feature_get_obj instanceof UStructuralFeature);
        assertEquals(_feature_get_obj, _feature_value);
    }
}
