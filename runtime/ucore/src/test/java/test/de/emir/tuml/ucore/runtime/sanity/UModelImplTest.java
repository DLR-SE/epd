package test.de.emir.tuml.ucore.runtime.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UModel;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.impl.UModelImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;

/**
 * @generated
 */
public class UModelImplTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UModelImplTest test = new UModelImplTest();
        test.createInstance();
        test.testSetterGetter();
    }

    /**
     * @generated
     */
    @Test
    public void createInstance() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UModel _new_operator = new UModelImpl();

        // using the classifier and thus the reflection capability
        UObject _package_uobj = RuntimePackage.Literals.UModel.createNewInstance();
        assertNotNull(_package_uobj);
        assertTrue(_package_uobj instanceof UModel);
    }

    /**
     * @generated
     */
    @Test
    public void testSetterGetter() {
        // using the new operator, just check if the instance can be created without throwing an exception
        UModel _obj = new UModelImpl();

        // Set values
        String _modelName_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModel_modelName, _modelName_value);
        String _fieldOfApplication_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModel_fieldOfApplication, _fieldOfApplication_value);
        String _scope_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModel_scope, _scope_value);
        String _versionDate_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModel_versionDate, _versionDate_value);
        UPackage _rootPackage_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModel_rootPackage, _rootPackage_value);
        String _documentation_value = "Was ist 42?";
        _obj.uSet(RuntimePackage.Literals.UModelElement_documentation, _documentation_value);
        UAnnotation _annotations_value = new UAnnotationImpl();
        _obj.uAdd(RuntimePackage.Literals.UModelElement_annotations, _annotations_value);
        UPackage _package_value = new UPackageImpl();
        _obj.uSet(RuntimePackage.Literals.UModelElement_package, _package_value);

        // Get values and compare them
        Object _modelName_get_obj = _obj.uGet(RuntimePackage.Literals.UModel_modelName);
        assertNotNull(_modelName_get_obj);
        assertTrue(_modelName_get_obj instanceof String);
        assertEquals(_modelName_get_obj, _modelName_value);
        Object _fieldOfApplication_get_obj = _obj.uGet(RuntimePackage.Literals.UModel_fieldOfApplication);
        assertNotNull(_fieldOfApplication_get_obj);
        assertTrue(_fieldOfApplication_get_obj instanceof String);
        assertEquals(_fieldOfApplication_get_obj, _fieldOfApplication_value);
        Object _scope_get_obj = _obj.uGet(RuntimePackage.Literals.UModel_scope);
        assertNotNull(_scope_get_obj);
        assertTrue(_scope_get_obj instanceof String);
        assertEquals(_scope_get_obj, _scope_value);
        Object _versionDate_get_obj = _obj.uGet(RuntimePackage.Literals.UModel_versionDate);
        assertNotNull(_versionDate_get_obj);
        assertTrue(_versionDate_get_obj instanceof String);
        assertEquals(_versionDate_get_obj, _versionDate_value);
        Object _rootPackage_get_obj = _obj.uGet(RuntimePackage.Literals.UModel_rootPackage);
        assertNotNull(_rootPackage_get_obj);
        assertTrue(_rootPackage_get_obj instanceof UPackage);
        assertEquals(_rootPackage_get_obj, _rootPackage_value);
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
