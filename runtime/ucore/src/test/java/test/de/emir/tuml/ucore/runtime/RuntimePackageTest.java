package test.de.emir.tuml.ucore.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 * @generated
 */
public class RuntimePackageTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        RuntimePackageTest test = new RuntimePackageTest();
        test.testClassifierLiterals();
        test.testClassifierDefinition();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierLiterals() {
        {
            UClass _UAnnotationDetail = RuntimePackage.Literals.UAnnotationDetail;
            assertNotNull(_UAnnotationDetail);
            assertEquals(_UAnnotationDetail.getName(), "UAnnotationDetail");
        }
        {
            UClass _UAnnotation = RuntimePackage.Literals.UAnnotation;
            assertNotNull(_UAnnotation);
            assertEquals(_UAnnotation.getName(), "UAnnotation");
        }
        {
            UClass _UModelElement = RuntimePackage.Literals.UModelElement;
            assertNotNull(_UModelElement);
            assertEquals(_UModelElement.getName(), "UModelElement");
        }
        {
            UClass _UNamedElement = RuntimePackage.Literals.UNamedElement;
            assertNotNull(_UNamedElement);
            assertEquals(_UNamedElement.getName(), "UNamedElement");
        }
        {
            UClass _UModel = RuntimePackage.Literals.UModel;
            assertNotNull(_UModel);
            assertEquals(_UModel.getName(), "UModel");
        }
        {
            UClass _UPackage = RuntimePackage.Literals.UPackage;
            assertNotNull(_UPackage);
            assertEquals(_UPackage.getName(), "UPackage");
        }
        {
            UEnum _NotificationType = RuntimePackage.Literals.NotificationType;
            assertNotNull(_NotificationType);
            assertEquals(_NotificationType.getName(), "NotificationType");
        }
        {
            UClass _Notification = RuntimePackage.Literals.Notification;
            assertNotNull(_Notification);
            assertEquals(_Notification.getName(), "Notification");
        }
        {
            UInterface _IValueChangeListener = RuntimePackage.Literals.IValueChangeListener;
            assertNotNull(_IValueChangeListener);
            assertEquals(_IValueChangeListener.getName(), "IValueChangeListener");
        }
        {
            UClass _UObject = RuntimePackage.Literals.UObject;
            assertNotNull(_UObject);
            assertEquals(_UObject.getName(), "UObject");
        }
        {
            UClass _UType = RuntimePackage.Literals.UType;
            assertNotNull(_UType);
            assertEquals(_UType.getName(), "UType");
        }
        {
            UClass _UPrimitiveType = RuntimePackage.Literals.UPrimitiveType;
            assertNotNull(_UPrimitiveType);
            assertEquals(_UPrimitiveType.getName(), "UPrimitiveType");
        }
        {
            UClass _UClassifier = RuntimePackage.Literals.UClassifier;
            assertNotNull(_UClassifier);
            assertEquals(_UClassifier.getName(), "UClassifier");
        }
        {
            UClass _UInterface = RuntimePackage.Literals.UInterface;
            assertNotNull(_UInterface);
            assertEquals(_UInterface.getName(), "UInterface");
        }
        {
            UClass _UClass = RuntimePackage.Literals.UClass;
            assertNotNull(_UClass);
            assertEquals(_UClass.getName(), "UClass");
        }
        {
            UClass _UEnum = RuntimePackage.Literals.UEnum;
            assertNotNull(_UEnum);
            assertEquals(_UEnum.getName(), "UEnum");
        }
        {
            UClass _UEnumerator = RuntimePackage.Literals.UEnumerator;
            assertNotNull(_UEnumerator);
            assertEquals(_UEnumerator.getName(), "UEnumerator");
        }
        {
            UClass _UException = RuntimePackage.Literals.UException;
            assertNotNull(_UException);
            assertEquals(_UException.getName(), "UException");
        }
        {
            UClass _UMultiplicity = RuntimePackage.Literals.UMultiplicity;
            assertNotNull(_UMultiplicity);
            assertEquals(_UMultiplicity.getName(), "UMultiplicity");
        }
        {
            UClass _UTypedElement = RuntimePackage.Literals.UTypedElement;
            assertNotNull(_UTypedElement);
            assertEquals(_UTypedElement.getName(), "UTypedElement");
        }
        {
            UClass _UStructuralFeature = RuntimePackage.Literals.UStructuralFeature;
            assertNotNull(_UStructuralFeature);
            assertEquals(_UStructuralFeature.getName(), "UStructuralFeature");
        }
        {
            UClass _UOperation = RuntimePackage.Literals.UOperation;
            assertNotNull(_UOperation);
            assertEquals(_UOperation.getName(), "UOperation");
        }
        {
            UClass _UParameter = RuntimePackage.Literals.UParameter;
            assertNotNull(_UParameter);
            assertEquals(_UParameter.getName(), "UParameter");
        }
        {
            UEnum _UDirectionType = RuntimePackage.Literals.UDirectionType;
            assertNotNull(_UDirectionType);
            assertEquals(_UDirectionType.getName(), "UDirectionType");
        }
        {
            UEnum _UAssociationType = RuntimePackage.Literals.UAssociationType;
            assertNotNull(_UAssociationType);
            assertEquals(_UAssociationType.getName(), "UAssociationType");
        }
    }

    /**
     * @generated
     */
    public void testClassifierDefinition() {
        testClassifierDefinition_UAnnotationDetail();
        testClassifierDefinition_UAnnotation();
        testClassifierDefinition_UModelElement();
        testClassifierDefinition_UNamedElement();
        testClassifierDefinition_UModel();
        testClassifierDefinition_UPackage();
        testClassifierDefinition_Notification();
        testClassifierDefinition_UObject();
        testClassifierDefinition_UType();
        testClassifierDefinition_UPrimitiveType();
        testClassifierDefinition_UClassifier();
        testClassifierDefinition_UInterface();
        testClassifierDefinition_UClass();
        testClassifierDefinition_UEnum();
        testClassifierDefinition_UEnumerator();
        testClassifierDefinition_UException();
        testClassifierDefinition_UMultiplicity();
        testClassifierDefinition_UTypedElement();
        testClassifierDefinition_UStructuralFeature();
        testClassifierDefinition_UOperation();
        testClassifierDefinition_UParameter();
    }

    public void testClassifierDefinition_UAnnotationDetail() {
        testClassifierDefinition_UAnnotationDetail_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UAnnotationDetail_features() {
        UClass cl = RuntimePackage.Literals.UAnnotationDetail;
        UStructuralFeature _key = cl.getFeature("key");
        assertNotNull(_key);
        assertEquals(_key.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_key.getMultiplicity());
        assertEquals(_key.getMultiplicity().getLower(), 1);
        assertEquals(_key.getMultiplicity().getUpper(), 1);

        UStructuralFeature _value = cl.getFeature("value");
        assertNotNull(_value);
        assertEquals(_value.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_value.getMultiplicity());
        assertEquals(_value.getMultiplicity().getLower(), 1);
        assertEquals(_value.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UAnnotation() {
        testClassifierDefinition_UAnnotation_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UAnnotation_features() {
        UClass cl = RuntimePackage.Literals.UAnnotation;
        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 1);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _details = cl.getFeature("details");
        assertNotNull(_details);
        assertEquals(_details.getType(), RuntimePackage.Literals.UAnnotationDetail);
        assertNotNull(_details.getMultiplicity());
        assertEquals(_details.getMultiplicity().getLower(), 0);
        assertEquals(_details.getMultiplicity().getUpper(), -1);

    }

    public void testClassifierDefinition_UModelElement() {
        testClassifierDefinition_UModelElement_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UModelElement_features() {
        UClass cl = RuntimePackage.Literals.UModelElement;
        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UNamedElement() {
        testClassifierDefinition_UNamedElement_hierarchie();
        testClassifierDefinition_UNamedElement_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UNamedElement_hierarchie() {
        UClass cl = RuntimePackage.Literals.UNamedElement;
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UNamedElement_features() {
        UClass cl = RuntimePackage.Literals.UNamedElement;
        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UModel() {
        testClassifierDefinition_UModel_hierarchie();
        testClassifierDefinition_UModel_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UModel_hierarchie() {
        UClass cl = RuntimePackage.Literals.UModel;
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UModel_features() {
        UClass cl = RuntimePackage.Literals.UModel;
        UStructuralFeature _modelName = cl.getFeature("modelName");
        assertNotNull(_modelName);
        assertEquals(_modelName.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_modelName.getMultiplicity());
        assertEquals(_modelName.getMultiplicity().getLower(), 1);
        assertEquals(_modelName.getMultiplicity().getUpper(), 1);

        UStructuralFeature _fieldOfApplication = cl.getFeature("fieldOfApplication");
        assertNotNull(_fieldOfApplication);
        assertEquals(_fieldOfApplication.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_fieldOfApplication.getMultiplicity());
        assertEquals(_fieldOfApplication.getMultiplicity().getLower(), 0);
        assertEquals(_fieldOfApplication.getMultiplicity().getUpper(), 1);

        UStructuralFeature _scope = cl.getFeature("scope");
        assertNotNull(_scope);
        assertEquals(_scope.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_scope.getMultiplicity());
        assertEquals(_scope.getMultiplicity().getLower(), 0);
        assertEquals(_scope.getMultiplicity().getUpper(), 1);

        UStructuralFeature _versionDate = cl.getFeature("versionDate");
        assertNotNull(_versionDate);
        assertEquals(_versionDate.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_versionDate.getMultiplicity());
        assertEquals(_versionDate.getMultiplicity().getLower(), 0);
        assertEquals(_versionDate.getMultiplicity().getUpper(), 1);

        UStructuralFeature _rootPackage = cl.getFeature("rootPackage");
        assertNotNull(_rootPackage);
        assertEquals(_rootPackage.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_rootPackage.getMultiplicity());
        assertEquals(_rootPackage.getMultiplicity().getLower(), 1);
        assertEquals(_rootPackage.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UPackage() {
        testClassifierDefinition_UPackage_hierarchie();
        testClassifierDefinition_UPackage_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UPackage_hierarchie() {
        UClass cl = RuntimePackage.Literals.UPackage;
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UPackage_features() {
        UClass cl = RuntimePackage.Literals.UPackage;
        UStructuralFeature _content = cl.getFeature("content");
        assertNotNull(_content);
        assertEquals(_content.getType(), RuntimePackage.Literals.UModelElement);
        assertNotNull(_content.getMultiplicity());
        assertEquals(_content.getMultiplicity().getLower(), 0);
        assertEquals(_content.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_Notification() {
        testClassifierDefinition_Notification_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_Notification_features() {
        UClass cl = RuntimePackage.Literals.Notification;
        UStructuralFeature _type = cl.getFeature("type");
        assertNotNull(_type);
        assertNotNull(_type.getMultiplicity());
        assertEquals(_type.getMultiplicity().getLower(), 1);
        assertEquals(_type.getMultiplicity().getUpper(), 1);

        UStructuralFeature _instance = cl.getFeature("instance");
        assertNotNull(_instance);
        assertEquals(_instance.getType(), RuntimePackage.Literals.UObject);
        assertNotNull(_instance.getMultiplicity());
        assertEquals(_instance.getMultiplicity().getLower(), 0);
        assertEquals(_instance.getMultiplicity().getUpper(), 1);

        UStructuralFeature _feature = cl.getFeature("feature");
        assertNotNull(_feature);
        assertEquals(_feature.getType(), RuntimePackage.Literals.UStructuralFeature);
        assertNotNull(_feature.getMultiplicity());
        assertEquals(_feature.getMultiplicity().getLower(), 0);
        assertEquals(_feature.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UObject() {
        testClassifierDefinition_UObject_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UObject_features() {
        UClass cl = RuntimePackage.Literals.UObject;
        UStructuralFeature _uClassifier = cl.getFeature("uClassifier");
        assertNotNull(_uClassifier);
        assertEquals(_uClassifier.getType(), RuntimePackage.Literals.UClass);
        assertNotNull(_uClassifier.getMultiplicity());
        assertEquals(_uClassifier.getMultiplicity().getLower(), 0);
        assertEquals(_uClassifier.getMultiplicity().getUpper(), 1);

        UStructuralFeature _uContainer = cl.getFeature("uContainer");
        assertNotNull(_uContainer);
        assertEquals(_uContainer.getType(), RuntimePackage.Literals.UObject);
        assertNotNull(_uContainer.getMultiplicity());
        assertEquals(_uContainer.getMultiplicity().getLower(), 0);
        assertEquals(_uContainer.getMultiplicity().getUpper(), 1);

        UStructuralFeature _uContainingFeature = cl.getFeature("uContainingFeature");
        assertNotNull(_uContainingFeature);
        assertEquals(_uContainingFeature.getType(), RuntimePackage.Literals.UStructuralFeature);
        assertNotNull(_uContainingFeature.getMultiplicity());
        assertEquals(_uContainingFeature.getMultiplicity().getLower(), 0);
        assertEquals(_uContainingFeature.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UType() {
        testClassifierDefinition_UType_hierarchie();
        testClassifierDefinition_UType_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UType_hierarchie() {
        UClass cl = RuntimePackage.Literals.UType;
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UType_features() {
        UClass cl = RuntimePackage.Literals.UType;
        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UPrimitiveType() {
        testClassifierDefinition_UPrimitiveType_hierarchie();
        testClassifierDefinition_UPrimitiveType_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UPrimitiveType_hierarchie() {
        UClass cl = RuntimePackage.Literals.UPrimitiveType;
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UPrimitiveType_features() {
        UClass cl = RuntimePackage.Literals.UPrimitiveType;
        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UClassifier() {
        testClassifierDefinition_UClassifier_hierarchie();
        testClassifierDefinition_UClassifier_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UClassifier_hierarchie() {
        UClass cl = RuntimePackage.Literals.UClassifier;
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UClassifier_features() {
        UClass cl = RuntimePackage.Literals.UClassifier;
        UStructuralFeature _interfaces = cl.getFeature("interfaces");
        assertNotNull(_interfaces);
        assertEquals(_interfaces.getType(), RuntimePackage.Literals.UClassifier);
        assertNotNull(_interfaces.getMultiplicity());
        assertEquals(_interfaces.getMultiplicity().getLower(), 0);
        assertEquals(_interfaces.getMultiplicity().getUpper(), -1);

        UStructuralFeature _operations = cl.getFeature("operations");
        assertNotNull(_operations);
        assertEquals(_operations.getType(), RuntimePackage.Literals.UOperation);
        assertNotNull(_operations.getMultiplicity());
        assertEquals(_operations.getMultiplicity().getLower(), 0);
        assertEquals(_operations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UInterface() {
        testClassifierDefinition_UInterface_hierarchie();
        testClassifierDefinition_UInterface_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UInterface_hierarchie() {
        UClass cl = RuntimePackage.Literals.UInterface;
        assertTrue(cl.inherits(RuntimePackage.Literals.UClassifier));
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UInterface_features() {
        UClass cl = RuntimePackage.Literals.UInterface;
        UStructuralFeature _interfaces = cl.getFeature("interfaces");
        assertNotNull(_interfaces);
        assertEquals(_interfaces.getType(), RuntimePackage.Literals.UClassifier);
        assertNotNull(_interfaces.getMultiplicity());
        assertEquals(_interfaces.getMultiplicity().getLower(), 0);
        assertEquals(_interfaces.getMultiplicity().getUpper(), -1);

        UStructuralFeature _operations = cl.getFeature("operations");
        assertNotNull(_operations);
        assertEquals(_operations.getType(), RuntimePackage.Literals.UOperation);
        assertNotNull(_operations.getMultiplicity());
        assertEquals(_operations.getMultiplicity().getLower(), 0);
        assertEquals(_operations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UClass() {
        testClassifierDefinition_UClass_hierarchie();
        testClassifierDefinition_UClass_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UClass_hierarchie() {
        UClass cl = RuntimePackage.Literals.UClass;
        assertTrue(cl.inherits(RuntimePackage.Literals.UClassifier));
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UClass_features() {
        UClass cl = RuntimePackage.Literals.UClass;
        UStructuralFeature _abstract = cl.getFeature("abstract");
        assertNotNull(_abstract);
        assertEquals(_abstract.getType(), TypeUtils.getPrimitiveType(boolean.class));
        assertNotNull(_abstract.getMultiplicity());
        assertEquals(_abstract.getMultiplicity().getLower(), 0);
        assertEquals(_abstract.getMultiplicity().getUpper(), 1);

        UStructuralFeature _superType = cl.getFeature("superType");
        assertNotNull(_superType);
        assertEquals(_superType.getType(), RuntimePackage.Literals.UClassifier);
        assertNotNull(_superType.getMultiplicity());
        assertEquals(_superType.getMultiplicity().getLower(), 0);
        assertEquals(_superType.getMultiplicity().getUpper(), 1);

        UStructuralFeature _structuralFeatures = cl.getFeature("structuralFeatures");
        assertNotNull(_structuralFeatures);
        assertEquals(_structuralFeatures.getType(), RuntimePackage.Literals.UStructuralFeature);
        assertNotNull(_structuralFeatures.getMultiplicity());
        assertEquals(_structuralFeatures.getMultiplicity().getLower(), 0);
        assertEquals(_structuralFeatures.getMultiplicity().getUpper(), -1);

        UStructuralFeature _interfaces = cl.getFeature("interfaces");
        assertNotNull(_interfaces);
        assertEquals(_interfaces.getType(), RuntimePackage.Literals.UClassifier);
        assertNotNull(_interfaces.getMultiplicity());
        assertEquals(_interfaces.getMultiplicity().getLower(), 0);
        assertEquals(_interfaces.getMultiplicity().getUpper(), -1);

        UStructuralFeature _operations = cl.getFeature("operations");
        assertNotNull(_operations);
        assertEquals(_operations.getType(), RuntimePackage.Literals.UOperation);
        assertNotNull(_operations.getMultiplicity());
        assertEquals(_operations.getMultiplicity().getLower(), 0);
        assertEquals(_operations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UEnum() {
        testClassifierDefinition_UEnum_hierarchie();
        testClassifierDefinition_UEnum_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UEnum_hierarchie() {
        UClass cl = RuntimePackage.Literals.UEnum;
        assertTrue(cl.inherits(RuntimePackage.Literals.UClassifier));
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UEnum_features() {
        UClass cl = RuntimePackage.Literals.UEnum;
        UStructuralFeature _literals = cl.getFeature("literals");
        assertNotNull(_literals);
        assertEquals(_literals.getType(), RuntimePackage.Literals.UEnumerator);
        assertNotNull(_literals.getMultiplicity());
        assertEquals(_literals.getMultiplicity().getLower(), 0);
        assertEquals(_literals.getMultiplicity().getUpper(), -1);

        UStructuralFeature _interfaces = cl.getFeature("interfaces");
        assertNotNull(_interfaces);
        assertEquals(_interfaces.getType(), RuntimePackage.Literals.UClassifier);
        assertNotNull(_interfaces.getMultiplicity());
        assertEquals(_interfaces.getMultiplicity().getLower(), 0);
        assertEquals(_interfaces.getMultiplicity().getUpper(), -1);

        UStructuralFeature _operations = cl.getFeature("operations");
        assertNotNull(_operations);
        assertEquals(_operations.getType(), RuntimePackage.Literals.UOperation);
        assertNotNull(_operations.getMultiplicity());
        assertEquals(_operations.getMultiplicity().getLower(), 0);
        assertEquals(_operations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UEnumerator() {
        testClassifierDefinition_UEnumerator_hierarchie();
        testClassifierDefinition_UEnumerator_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UEnumerator_hierarchie() {
        UClass cl = RuntimePackage.Literals.UEnumerator;
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UEnumerator_features() {
        UClass cl = RuntimePackage.Literals.UEnumerator;
        UStructuralFeature _value = cl.getFeature("value");
        assertNotNull(_value);
        assertEquals(_value.getType(), TypeUtils.getPrimitiveType(int.class));
        assertNotNull(_value.getMultiplicity());
        assertEquals(_value.getMultiplicity().getLower(), 0);
        assertEquals(_value.getMultiplicity().getUpper(), 1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UException() {
        testClassifierDefinition_UException_hierarchie();
        testClassifierDefinition_UException_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UException_hierarchie() {
        UClass cl = RuntimePackage.Literals.UException;
        assertTrue(cl.inherits(RuntimePackage.Literals.UType));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UException_features() {
        UClass cl = RuntimePackage.Literals.UException;
        UStructuralFeature _standardMessage = cl.getFeature("standardMessage");
        assertNotNull(_standardMessage);
        assertEquals(_standardMessage.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_standardMessage.getMultiplicity());
        assertEquals(_standardMessage.getMultiplicity().getLower(), 0);
        assertEquals(_standardMessage.getMultiplicity().getUpper(), 1);

        UStructuralFeature _superType = cl.getFeature("superType");
        assertNotNull(_superType);
        assertEquals(_superType.getType(), RuntimePackage.Literals.UException);
        assertNotNull(_superType.getMultiplicity());
        assertEquals(_superType.getMultiplicity().getLower(), 0);
        assertEquals(_superType.getMultiplicity().getUpper(), 1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UMultiplicity() {
        testClassifierDefinition_UMultiplicity_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UMultiplicity_features() {
        UClass cl = RuntimePackage.Literals.UMultiplicity;
        UStructuralFeature _lower = cl.getFeature("lower");
        assertNotNull(_lower);
        assertEquals(_lower.getType(), TypeUtils.getPrimitiveType(int.class));
        assertNotNull(_lower.getMultiplicity());
        assertEquals(_lower.getMultiplicity().getLower(), 0);
        assertEquals(_lower.getMultiplicity().getUpper(), 1);

        UStructuralFeature _upper = cl.getFeature("upper");
        assertNotNull(_upper);
        assertEquals(_upper.getType(), TypeUtils.getPrimitiveType(int.class));
        assertNotNull(_upper.getMultiplicity());
        assertEquals(_upper.getMultiplicity().getLower(), 0);
        assertEquals(_upper.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UTypedElement() {
        testClassifierDefinition_UTypedElement_hierarchie();
        testClassifierDefinition_UTypedElement_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UTypedElement_hierarchie() {
        UClass cl = RuntimePackage.Literals.UTypedElement;
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UTypedElement_features() {
        UClass cl = RuntimePackage.Literals.UTypedElement;
        UStructuralFeature _type = cl.getFeature("type");
        assertNotNull(_type);
        assertEquals(_type.getType(), RuntimePackage.Literals.UType);
        assertNotNull(_type.getMultiplicity());
        assertEquals(_type.getMultiplicity().getLower(), 0);
        assertEquals(_type.getMultiplicity().getUpper(), 1);

        UStructuralFeature _multiplicity = cl.getFeature("multiplicity");
        assertNotNull(_multiplicity);
        assertEquals(_multiplicity.getType(), RuntimePackage.Literals.UMultiplicity);
        assertNotNull(_multiplicity.getMultiplicity());
        assertEquals(_multiplicity.getMultiplicity().getLower(), 0);
        assertEquals(_multiplicity.getMultiplicity().getUpper(), 1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UStructuralFeature() {
        testClassifierDefinition_UStructuralFeature_hierarchie();
        testClassifierDefinition_UStructuralFeature_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UStructuralFeature_hierarchie() {
        UClass cl = RuntimePackage.Literals.UStructuralFeature;
        assertTrue(cl.inherits(RuntimePackage.Literals.UTypedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UStructuralFeature_features() {
        UClass cl = RuntimePackage.Literals.UStructuralFeature;
        UStructuralFeature _aggregation = cl.getFeature("aggregation");
        assertNotNull(_aggregation);
        assertNotNull(_aggregation.getMultiplicity());
        assertEquals(_aggregation.getMultiplicity().getLower(), 0);
        assertEquals(_aggregation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _type = cl.getFeature("type");
        assertNotNull(_type);
        assertEquals(_type.getType(), RuntimePackage.Literals.UType);
        assertNotNull(_type.getMultiplicity());
        assertEquals(_type.getMultiplicity().getLower(), 0);
        assertEquals(_type.getMultiplicity().getUpper(), 1);

        UStructuralFeature _multiplicity = cl.getFeature("multiplicity");
        assertNotNull(_multiplicity);
        assertEquals(_multiplicity.getType(), RuntimePackage.Literals.UMultiplicity);
        assertNotNull(_multiplicity.getMultiplicity());
        assertEquals(_multiplicity.getMultiplicity().getLower(), 0);
        assertEquals(_multiplicity.getMultiplicity().getUpper(), 1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UOperation() {
        testClassifierDefinition_UOperation_hierarchie();
        testClassifierDefinition_UOperation_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UOperation_hierarchie() {
        UClass cl = RuntimePackage.Literals.UOperation;
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UOperation_features() {
        UClass cl = RuntimePackage.Literals.UOperation;
        UStructuralFeature _abstract = cl.getFeature("abstract");
        assertNotNull(_abstract);
        assertEquals(_abstract.getType(), TypeUtils.getPrimitiveType(boolean.class));
        assertNotNull(_abstract.getMultiplicity());
        assertEquals(_abstract.getMultiplicity().getLower(), 0);
        assertEquals(_abstract.getMultiplicity().getUpper(), 1);

        UStructuralFeature _const = cl.getFeature("const");
        assertNotNull(_const);
        assertEquals(_const.getType(), TypeUtils.getPrimitiveType(boolean.class));
        assertNotNull(_const.getMultiplicity());
        assertEquals(_const.getMultiplicity().getLower(), 0);
        assertEquals(_const.getMultiplicity().getUpper(), 1);

        UStructuralFeature _parameters = cl.getFeature("parameters");
        assertNotNull(_parameters);
        assertEquals(_parameters.getType(), RuntimePackage.Literals.UParameter);
        assertNotNull(_parameters.getMultiplicity());
        assertEquals(_parameters.getMultiplicity().getLower(), 0);
        assertEquals(_parameters.getMultiplicity().getUpper(), -1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_UParameter() {
        testClassifierDefinition_UParameter_hierarchie();
        testClassifierDefinition_UParameter_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UParameter_hierarchie() {
        UClass cl = RuntimePackage.Literals.UParameter;
        assertTrue(cl.inherits(RuntimePackage.Literals.UTypedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UNamedElement));
        assertTrue(cl.inherits(RuntimePackage.Literals.UModelElement));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_UParameter_features() {
        UClass cl = RuntimePackage.Literals.UParameter;
        UStructuralFeature _direction = cl.getFeature("direction");
        assertNotNull(_direction);
        assertNotNull(_direction.getMultiplicity());
        assertEquals(_direction.getMultiplicity().getLower(), 0);
        assertEquals(_direction.getMultiplicity().getUpper(), 1);

        UStructuralFeature _type = cl.getFeature("type");
        assertNotNull(_type);
        assertEquals(_type.getType(), RuntimePackage.Literals.UType);
        assertNotNull(_type.getMultiplicity());
        assertEquals(_type.getMultiplicity().getLower(), 0);
        assertEquals(_type.getMultiplicity().getUpper(), 1);

        UStructuralFeature _multiplicity = cl.getFeature("multiplicity");
        assertNotNull(_multiplicity);
        assertEquals(_multiplicity.getType(), RuntimePackage.Literals.UMultiplicity);
        assertNotNull(_multiplicity.getMultiplicity());
        assertEquals(_multiplicity.getMultiplicity().getLower(), 0);
        assertEquals(_multiplicity.getMultiplicity().getUpper(), 1);

        UStructuralFeature _name = cl.getFeature("name");
        assertNotNull(_name);
        assertEquals(_name.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_name.getMultiplicity());
        assertEquals(_name.getMultiplicity().getLower(), 0);
        assertEquals(_name.getMultiplicity().getUpper(), 1);

        UStructuralFeature _documentation = cl.getFeature("documentation");
        assertNotNull(_documentation);
        assertEquals(_documentation.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_documentation.getMultiplicity());
        assertEquals(_documentation.getMultiplicity().getLower(), 0);
        assertEquals(_documentation.getMultiplicity().getUpper(), 1);

        UStructuralFeature _annotations = cl.getFeature("annotations");
        assertNotNull(_annotations);
        assertEquals(_annotations.getType(), RuntimePackage.Literals.UAnnotation);
        assertNotNull(_annotations.getMultiplicity());
        assertEquals(_annotations.getMultiplicity().getLower(), 0);
        assertEquals(_annotations.getMultiplicity().getUpper(), -1);

        UStructuralFeature _package = cl.getFeature("package");
        assertNotNull(_package);
        assertEquals(_package.getType(), RuntimePackage.Literals.UPackage);
        assertNotNull(_package.getMultiplicity());
        assertEquals(_package.getMultiplicity().getLower(), 0);
        assertEquals(_package.getMultiplicity().getUpper(), 1);

    }

}
