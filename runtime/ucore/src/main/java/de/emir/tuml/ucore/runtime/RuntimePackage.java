package de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.impl.NotificationImpl;
import de.emir.tuml.ucore.runtime.impl.UAnnotationDetailImpl;
import de.emir.tuml.ucore.runtime.impl.UAnnotationImpl;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.impl.UClassImpl;
import de.emir.tuml.ucore.runtime.impl.UClassifierImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.impl.UEnumImpl;
import de.emir.tuml.ucore.runtime.impl.UEnumeratorImpl;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.impl.UExceptionImpl;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.impl.UInterfaceImpl;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.impl.UModelElementImpl;
import de.emir.tuml.ucore.runtime.impl.UModelImpl;
import de.emir.tuml.ucore.runtime.impl.UMultiplicityImpl;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.impl.UOperationImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.UNamedElement;
import de.emir.tuml.ucore.runtime.impl.UParameterImpl;
import de.emir.tuml.ucore.runtime.impl.UPrimitiveTypeImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UException;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.impl.UTypeImpl;
import de.emir.tuml.ucore.runtime.impl.UTypedElementImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.UMultiplicity;
import de.emir.tuml.ucore.runtime.UModel;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import de.emir.tuml.ucore.runtime.UTypedElement;
import de.emir.tuml.ucore.runtime.UType;
import java.util.List;

/**
 * @generated
 */
public class RuntimePackage {

    /**
     * @generated
     */
    public static RuntimePackage theInstance = new RuntimePackage().init();

    /**
     * @generated
     */
    public interface Literals {
        /**
         * @generated
         * @return meta type for classifier UAnnotationDetail
         */
        UClass UAnnotationDetail = RuntimePackage.theInstance.getUAnnotationDetail();
        /**
         * @generated
         * @return meta type for classifier UAnnotation
         */
        UClass UAnnotation = RuntimePackage.theInstance.getUAnnotation();
        /**
         * @generated
         * @return meta type for classifier UModelElement
         */
        UClass UModelElement = RuntimePackage.theInstance.getUModelElement();
        /**
         * @generated
         * @return meta type for classifier UNamedElement
         */
        UClass UNamedElement = RuntimePackage.theInstance.getUNamedElement();
        /**
         * @generated
         * @return meta type for classifier UModel
         */
        UClass UModel = RuntimePackage.theInstance.getUModel();
        /**
         * @generated
         * @return meta type for classifier UPackage
         */
        UClass UPackage = RuntimePackage.theInstance.getUPackage();
        /**
         * @generated
         * @return meta type for enumeration NotificationType
         */
        UEnum NotificationType = RuntimePackage.theInstance.getNotificationType();
        /**
         * @generated
         * @return meta type for classifier Notification
         */
        UClass Notification = RuntimePackage.theInstance.getNotification();
        /**
         * @generated
         * @return meta type for interface IValueChangeListener
         */
        UInterface IValueChangeListener = RuntimePackage.theInstance.getIValueChangeListener();
        /**
         * @generated
         * @return meta type for classifier UObject
         */
        UClass UObject = RuntimePackage.theInstance.getUObject();
        /**
         * @generated
         * @return meta type for classifier UType
         */
        UClass UType = RuntimePackage.theInstance.getUType();
        /**
         * @generated
         * @return meta type for classifier UPrimitiveType
         */
        UClass UPrimitiveType = RuntimePackage.theInstance.getUPrimitiveType();
        /**
         * @generated
         * @return meta type for classifier UClassifier
         */
        UClass UClassifier = RuntimePackage.theInstance.getUClassifier();
        /**
         * @generated
         * @return meta type for classifier UInterface
         */
        UClass UInterface = RuntimePackage.theInstance.getUInterface();
        /**
         * @generated
         * @return meta type for classifier UClass
         */
        UClass UClass = RuntimePackage.theInstance.getUClass();
        /**
         * @generated
         * @return meta type for classifier UEnum
         */
        UClass UEnum = RuntimePackage.theInstance.getUEnum();
        /**
         * @generated
         * @return meta type for classifier UEnumerator
         */
        UClass UEnumerator = RuntimePackage.theInstance.getUEnumerator();
        /**
         * @generated
         * @return meta type for classifier UException
         */
        UClass UException = RuntimePackage.theInstance.getUException();
        /**
         * @generated
         * @return meta type for classifier UMultiplicity
         */
        UClass UMultiplicity = RuntimePackage.theInstance.getUMultiplicity();
        /**
         * @generated
         * @return meta type for interface IStructuralElement
         */
        UInterface IStructuralElement = RuntimePackage.theInstance.getIStructuralElement();
        /**
         * @generated
         * @return meta type for classifier UTypedElement
         */
        UClass UTypedElement = RuntimePackage.theInstance.getUTypedElement();
        /**
         * @generated
         * @return meta type for classifier UStructuralFeature
         */
        UClass UStructuralFeature = RuntimePackage.theInstance.getUStructuralFeature();
        /**
         * @generated
         * @return meta type for classifier UOperation
         */
        UClass UOperation = RuntimePackage.theInstance.getUOperation();
        /**
         * @generated
         * @return meta type for classifier UParameter
         */
        UClass UParameter = RuntimePackage.theInstance.getUParameter();
        /**
         * @generated
         * @return meta type for enumeration UDirectionType
         */
        UEnum UDirectionType = RuntimePackage.theInstance.getUDirectionType();
        /**
         * @generated
         * @return meta type for enumeration UAssociationType
         */
        UEnum UAssociationType = RuntimePackage.theInstance.getUAssociationType();

        /**
         * @generated
         * @return feature descriptor key in type UAnnotationDetail
         */
        UStructuralFeature UAnnotationDetail_key = RuntimePackage.theInstance.getUAnnotationDetail_key();
        /**
         * @generated
         * @return feature descriptor value in type UAnnotationDetail
         */
        UStructuralFeature UAnnotationDetail_value = RuntimePackage.theInstance.getUAnnotationDetail_value();
        /**
         * @generated
         * @return feature descriptor name in type UAnnotation
         */
        UStructuralFeature UAnnotation_name = RuntimePackage.theInstance.getUAnnotation_name();
        /**
         * @generated
         * @return feature descriptor details in type UAnnotation
         */
        UStructuralFeature UAnnotation_details = RuntimePackage.theInstance.getUAnnotation_details();
        /**
         * @generated
         * @return feature descriptor documentation in type UModelElement
         */
        UStructuralFeature UModelElement_documentation = RuntimePackage.theInstance.getUModelElement_documentation();
        /**
         * @generated
         * @return feature descriptor annotations in type UModelElement
         */
        UStructuralFeature UModelElement_annotations = RuntimePackage.theInstance.getUModelElement_annotations();
        /**
         * @generated
         * @return feature descriptor package in type UModelElement
         */
        UStructuralFeature UModelElement_package = RuntimePackage.theInstance.getUModelElement_package();
        /**
         * @generated
         * @return feature descriptor name in type UNamedElement
         */
        UStructuralFeature UNamedElement_name = RuntimePackage.theInstance.getUNamedElement_name();
        /**
         * @generated
         * @return feature descriptor modelName in type UModel
         */
        UStructuralFeature UModel_modelName = RuntimePackage.theInstance.getUModel_modelName();
        /**
         * @generated
         * @return feature descriptor fieldOfApplication in type UModel
         */
        UStructuralFeature UModel_fieldOfApplication = RuntimePackage.theInstance.getUModel_fieldOfApplication();
        /**
         * @generated
         * @return feature descriptor scope in type UModel
         */
        UStructuralFeature UModel_scope = RuntimePackage.theInstance.getUModel_scope();
        /**
         * @generated
         * @return feature descriptor versionDate in type UModel
         */
        UStructuralFeature UModel_versionDate = RuntimePackage.theInstance.getUModel_versionDate();
        /**
         * @generated
         * @return feature descriptor rootPackage in type UModel
         */
        UStructuralFeature UModel_rootPackage = RuntimePackage.theInstance.getUModel_rootPackage();
        /**
         * @generated
         * @return feature descriptor content in type UPackage
         */
        UStructuralFeature UPackage_content = RuntimePackage.theInstance.getUPackage_content();
        /**
         * @generated
         * @return feature descriptor type in type Notification
         */
        UStructuralFeature Notification_type = RuntimePackage.theInstance.getNotification_type();
        /**
         * @generated
         * @return feature descriptor instance in type Notification
         */
        UStructuralFeature Notification_instance = RuntimePackage.theInstance.getNotification_instance();
        /**
         * @generated
         * @return feature descriptor feature in type Notification
         */
        UStructuralFeature Notification_feature = RuntimePackage.theInstance.getNotification_feature();
        /**
         * @generated
         * @return feature descriptor uClassifier in type UObject
         */
        UStructuralFeature UObject_uClassifier = RuntimePackage.theInstance.getUObject_uClassifier();
        /**
         * @generated
         * @return feature descriptor uContainer in type UObject
         */
        UStructuralFeature UObject_uContainer = RuntimePackage.theInstance.getUObject_uContainer();
        /**
         * @generated
         * @return feature descriptor uContainingFeature in type UObject
         */
        UStructuralFeature UObject_uContainingFeature = RuntimePackage.theInstance.getUObject_uContainingFeature();
        /**
         * @generated
         * @return feature descriptor interfaces in type UClassifier
         */
        UStructuralFeature UClassifier_interfaces = RuntimePackage.theInstance.getUClassifier_interfaces();
        /**
         * @generated
         * @return feature descriptor operations in type UClassifier
         */
        UStructuralFeature UClassifier_operations = RuntimePackage.theInstance.getUClassifier_operations();
        /**
         * @generated
         * @return feature descriptor abstract in type UClass
         */
        UStructuralFeature UClass_abstract = RuntimePackage.theInstance.getUClass_abstract();
        /**
         * @generated
         * @return feature descriptor superType in type UClass
         */
        UStructuralFeature UClass_superType = RuntimePackage.theInstance.getUClass_superType();
        /**
         * @generated
         * @return feature descriptor structuralFeatures in type UClass
         */
        UStructuralFeature UClass_structuralFeatures = RuntimePackage.theInstance.getUClass_structuralFeatures();
        /**
         * @generated
         * @return feature descriptor literals in type UEnum
         */
        UStructuralFeature UEnum_literals = RuntimePackage.theInstance.getUEnum_literals();
        /**
         * @generated
         * @return feature descriptor value in type UEnumerator
         */
        UStructuralFeature UEnumerator_value = RuntimePackage.theInstance.getUEnumerator_value();
        /**
         * @generated
         * @return feature descriptor standardMessage in type UException
         */
        UStructuralFeature UException_standardMessage = RuntimePackage.theInstance.getUException_standardMessage();
        /**
         * @generated
         * @return feature descriptor superType in type UException
         */
        UStructuralFeature UException_superType = RuntimePackage.theInstance.getUException_superType();
        /**
         * @generated
         * @return feature descriptor lower in type UMultiplicity
         */
        UStructuralFeature UMultiplicity_lower = RuntimePackage.theInstance.getUMultiplicity_lower();
        /**
         * @generated
         * @return feature descriptor upper in type UMultiplicity
         */
        UStructuralFeature UMultiplicity_upper = RuntimePackage.theInstance.getUMultiplicity_upper();
        /**
         * @generated
         * @return feature descriptor type in type UTypedElement
         */
        UStructuralFeature UTypedElement_type = RuntimePackage.theInstance.getUTypedElement_type();
        /**
         * @generated
         * @return feature descriptor multiplicity in type UTypedElement
         */
        UStructuralFeature UTypedElement_multiplicity = RuntimePackage.theInstance.getUTypedElement_multiplicity();
        /**
         * @generated
         * @return feature descriptor aggregation in type UStructuralFeature
         */
        UStructuralFeature UStructuralFeature_aggregation = RuntimePackage.theInstance
                .getUStructuralFeature_aggregation();
        /**
         * @generated
         * @return feature descriptor abstract in type UOperation
         */
        UStructuralFeature UOperation_abstract = RuntimePackage.theInstance.getUOperation_abstract();
        /**
         * @generated
         * @return feature descriptor const in type UOperation
         */
        UStructuralFeature UOperation_const = RuntimePackage.theInstance.getUOperation_const();
        /**
         * @generated
         * @return feature descriptor parameters in type UOperation
         */
        UStructuralFeature UOperation_parameters = RuntimePackage.theInstance.getUOperation_parameters();
        /**
         * @generated
         * @return feature descriptor direction in type UParameter
         */
        UStructuralFeature UParameter_direction = RuntimePackage.theInstance.getUParameter_direction();

    }

    //////////////////////////////////////////////////////////////////////
    // Classifiers //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    private UClass mUAnnotationDetail = null;
    /**
     * @generated
     */
    private UClass mUAnnotation = null;
    /**
     * @generated
     */
    private UClass mUModelElement = null;
    /**
     * @generated
     */
    private UClass mUNamedElement = null;
    /**
     * @generated
     */
    private UClass mUModel = null;
    /**
     * @generated
     */
    private UClass mUPackage = null;
    /**
     * @generated
     */
    private UEnum mNotificationType = null;
    /**
     * @generated
     */
    private UClass mNotification = null;
    /**
     * @generated
     */
    private UInterface mIValueChangeListener = null;
    /**
     * @generated
     */
    private UClass mUObject = null;
    /**
     * @generated
     */
    private UClass mUType = null;
    /**
     * @generated
     */
    private UClass mUPrimitiveType = null;
    /**
     * @generated
     */
    private UClass mUClassifier = null;
    /**
     * @generated
     */
    private UClass mUInterface = null;
    /**
     * @generated
     */
    private UClass mUClass = null;
    /**
     * @generated
     */
    private UClass mUEnum = null;
    /**
     * @generated
     */
    private UClass mUEnumerator = null;
    /**
     * @generated
     */
    private UClass mUException = null;
    /**
     * @generated
     */
    private UClass mUMultiplicity = null;
    /**
     * @generated
     */
    private UInterface mIStructuralElement = null;
    /**
     * @generated
     */
    private UClass mUTypedElement = null;
    /**
     * @generated
     */
    private UClass mUStructuralFeature = null;
    /**
     * @generated
     */
    private UClass mUOperation = null;
    /**
     * @generated
     */
    private UClass mUParameter = null;
    /**
     * @generated
     */
    private UEnum mUDirectionType = null;
    /**
     * @generated
     */
    private UEnum mUAssociationType = null;

    //////////////////////////////////////////////////////////////////////
    // StructuralFeatures //
    //////////////////////////////////////////////////////////////////////

    // Features for classifier UAnnotationDetail
    /**
     * @generated
     */
    private UStructuralFeature mUAnnotationDetail_key = null;
    /**
     * @generated
     */
    private UStructuralFeature mUAnnotationDetail_value = null;

    // Features for classifier UAnnotation
    /**
     * @generated
     */
    private UStructuralFeature mUAnnotation_name = null;
    /**
     * @generated
     */
    private UStructuralFeature mUAnnotation_details = null;

    // Features for classifier UModelElement
    /**
     * @generated
     */
    private UStructuralFeature mUModelElement_documentation = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModelElement_annotations = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModelElement_package = null;

    // Features for classifier UNamedElement
    /**
     * @generated
     */
    private UStructuralFeature mUNamedElement_name = null;

    // Features for classifier UModel
    /**
     * @generated
     */
    private UStructuralFeature mUModel_modelName = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModel_fieldOfApplication = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModel_scope = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModel_versionDate = null;
    /**
     * @generated
     */
    private UStructuralFeature mUModel_rootPackage = null;

    // Features for classifier UPackage
    /**
     * @generated
     */
    private UStructuralFeature mUPackage_content = null;

    // Features for classifier Notification
    /**
     * @generated
     */
    private UStructuralFeature mNotification_type = null;
    /**
     * @generated
     */
    private UStructuralFeature mNotification_instance = null;
    /**
     * @generated
     */
    private UStructuralFeature mNotification_feature = null;

    // Features for classifier UObject
    /**
     * @generated
     */
    private UStructuralFeature mUObject_uClassifier = null;
    /**
     * @generated
     */
    private UStructuralFeature mUObject_uContainer = null;
    /**
     * @generated
     */
    private UStructuralFeature mUObject_uContainingFeature = null;

    // Features for classifier UClassifier
    /**
     * @generated
     */
    private UStructuralFeature mUClassifier_interfaces = null;
    /**
     * @generated
     */
    private UStructuralFeature mUClassifier_operations = null;

    // Features for classifier UClass
    /**
     * @generated
     */
    private UStructuralFeature mUClass_abstract = null;
    /**
     * @generated
     */
    private UStructuralFeature mUClass_superType = null;
    /**
     * @generated
     */
    private UStructuralFeature mUClass_structuralFeatures = null;

    // Features for classifier UEnum
    /**
     * @generated
     */
    private UStructuralFeature mUEnum_literals = null;

    // Features for classifier UEnumerator
    /**
     * @generated
     */
    private UStructuralFeature mUEnumerator_value = null;

    // Features for classifier UException
    /**
     * @generated
     */
    private UStructuralFeature mUException_standardMessage = null;
    /**
     * @generated
     */
    private UStructuralFeature mUException_superType = null;

    // Features for classifier UMultiplicity
    /**
     * @generated
     */
    private UStructuralFeature mUMultiplicity_lower = null;
    /**
     * @generated
     */
    private UStructuralFeature mUMultiplicity_upper = null;

    // Features for classifier UTypedElement
    /**
     * @generated
     */
    private UStructuralFeature mUTypedElement_type = null;
    /**
     * @generated
     */
    private UStructuralFeature mUTypedElement_multiplicity = null;

    // Features for classifier UStructuralFeature
    /**
     * @generated
     */
    private UStructuralFeature mUStructuralFeature_aggregation = null;

    // Features for classifier UOperation
    /**
     * @generated
     */
    private UStructuralFeature mUOperation_abstract = null;
    /**
     * @generated
     */
    private UStructuralFeature mUOperation_const = null;
    /**
     * @generated
     */
    private UStructuralFeature mUOperation_parameters = null;

    // Features for classifier UParameter
    /**
     * @generated
     */
    private UStructuralFeature mUParameter_direction = null;

    /**
     * @generated
     */
    public static RuntimePackage init() {
        if (theInstance != null)
            return theInstance;

        ULog.debug("initialize package RuntimePackage ...", 1);
        theInstance = new RuntimePackage();

        theInstance.createClassifier();
        theInstance.createFeatures();
        theInstance.createOperations();
        theInstance.buildHierarchies();
        UPackage p = UCoreMetaRepository.getPackage("de.emir.tuml.ucore.runtime");
        p.getContent().add(theInstance.mUAnnotationDetail);
        p.getContent().add(theInstance.mUAnnotation);
        p.getContent().add(theInstance.mUModelElement);
        p.getContent().add(theInstance.mUNamedElement);
        p.getContent().add(theInstance.mUModel);
        p.getContent().add(theInstance.mUPackage);
        p.getContent().add(theInstance.mNotificationType);
        p.getContent().add(theInstance.mNotification);
        p.getContent().add(theInstance.mIValueChangeListener);
        p.getContent().add(theInstance.mUObject);
        p.getContent().add(theInstance.mUType);
        p.getContent().add(theInstance.mUPrimitiveType);
        p.getContent().add(theInstance.mUClassifier);
        p.getContent().add(theInstance.mUInterface);
        p.getContent().add(theInstance.mUClass);
        p.getContent().add(theInstance.mUEnum);
        p.getContent().add(theInstance.mUEnumerator);
        p.getContent().add(theInstance.mUException);
        p.getContent().add(theInstance.mUMultiplicity);
        p.getContent().add(theInstance.mIStructuralElement);
        p.getContent().add(theInstance.mUTypedElement);
        p.getContent().add(theInstance.mUStructuralFeature);
        p.getContent().add(theInstance.mUOperation);
        p.getContent().add(theInstance.mUParameter);
        p.getContent().add(theInstance.mUDirectionType);
        p.getContent().add(theInstance.mUAssociationType);
        p.freeze();

        ULog.debug(-1, "... package RuntimePackage initialized");

        return theInstance;
    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createClassifier() {
        mUAnnotationDetail = UMetaBuilder.manual().createClass("UAnnotationDetail", false, UAnnotationDetail.class,
                UAnnotationDetailImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUAnnotationDetail, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UAnnotationDetailImpl();
            }
        });

        mUAnnotation = UMetaBuilder.manual().createClass("UAnnotation", false, UAnnotation.class,
                UAnnotationImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUAnnotation, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UAnnotationImpl();
            }
        });

        mUModelElement = UMetaBuilder.manual().createClass("UModelElement", true, UModelElement.class,
                UModelElementImpl.class);

        mUNamedElement = UMetaBuilder.manual().createClass("UNamedElement", true, UNamedElement.class,
                UNamedElementImpl.class);

        mUModel = UMetaBuilder.manual().createClass("UModel", false, UModel.class, UModelImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUModel, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UModelImpl();
            }
        });

        mUPackage = UMetaBuilder.manual().createClass("UPackage", false, UPackage.class, UPackageImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUPackage, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UPackageImpl();
            }
        });

        mNotificationType = UMetaBuilder.manual().createEnumeration("NotificationType", NotificationType.class);

        mNotification = UMetaBuilder.manual().createClass("Notification", false, Notification.class,
                NotificationImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mNotification, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new NotificationImpl();
            }
        });
        // Annotations for Notification
        mNotification.createAnnotation("struct");

        mIValueChangeListener = UMetaBuilder.manual().createInterface("IValueChangeListener",
                IValueChangeListener.class);

        mUObject = UMetaBuilder.manual().createClass("UObject", true, UObject.class, UObjectImpl.class);

        mUType = UMetaBuilder.manual().createClass("UType", true, UType.class, UTypeImpl.class);

        mUPrimitiveType = UMetaBuilder.manual().createClass("UPrimitiveType", false, UPrimitiveType.class,
                UPrimitiveTypeImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUPrimitiveType, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UPrimitiveTypeImpl();
            }
        });

        mUClassifier = UMetaBuilder.manual().createClass("UClassifier", true, UClassifier.class, UClassifierImpl.class);

        mUInterface = UMetaBuilder.manual().createClass("UInterface", false, UInterface.class, UInterfaceImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUInterface, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UInterfaceImpl();
            }
        });

        mUClass = UMetaBuilder.manual().createClass("UClass", false, UClass.class, UClassImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUClass, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UClassImpl();
            }
        });

        mUEnum = UMetaBuilder.manual().createClass("UEnum", false, UEnum.class, UEnumImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUEnum, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UEnumImpl();
            }
        });

        mUEnumerator = UMetaBuilder.manual().createClass("UEnumerator", false, UEnumerator.class,
                UEnumeratorImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUEnumerator, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UEnumeratorImpl();
            }
        });

        mUException = UMetaBuilder.manual().createClass("UException", false, UException.class, UExceptionImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUException, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UExceptionImpl();
            }
        });

        mUMultiplicity = UMetaBuilder.manual().createClass("UMultiplicity", false, UMultiplicity.class,
                UMultiplicityImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUMultiplicity, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UMultiplicityImpl();
            }
        });

        mIStructuralElement = UMetaBuilder.manual().createInterface("IStructuralElement", IStructuralElement.class);

        mUTypedElement = UMetaBuilder.manual().createClass("UTypedElement", true, UTypedElement.class,
                UTypedElementImpl.class);

        mUStructuralFeature = UMetaBuilder.manual().createClass("UStructuralFeature", false, UStructuralFeature.class,
                UStructuralFeatureImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUStructuralFeature, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UStructuralFeatureImpl();
            }
        });

        mUOperation = UMetaBuilder.manual().createClass("UOperation", false, UOperation.class, UOperationImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUOperation, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UOperationImpl();
            }
        });

        mUParameter = UMetaBuilder.manual().createClass("UParameter", false, UParameter.class, UParameterImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mUParameter, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new UParameterImpl();
            }
        });

        mUDirectionType = UMetaBuilder.manual().createEnumeration("UDirectionType", UDirectionType.class);

        mUAssociationType = UMetaBuilder.manual().createEnumeration("UAssociationType", UAssociationType.class);

    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createFeatures() {
        {// create features
         // Features of UAnnotationDetail
            mUAnnotationDetail_key = UMetaBuilder.manual().createFeature("key",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUAnnotationDetail_key, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UAnnotationDetail) instance).getKey();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UAnnotationDetail) instance).setKey((String) value);
                }
            });
            mUAnnotationDetail_value = UMetaBuilder.manual().createFeature("value",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUAnnotationDetail_value, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UAnnotationDetail) instance).getValue();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UAnnotationDetail) instance).setValue((String) value);
                }
            });

            // Features of UAnnotation
            mUAnnotation_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class),
                    UAssociationType.PROPERTY, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUAnnotation_name, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UAnnotation) instance).getName();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UAnnotation) instance).setName((String) value);
                }
            });
            mUAnnotation_details = UMetaBuilder.manual().createFeature("details",
                    RuntimePackage.theInstance.getUAnnotationDetail(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUAnnotation_details, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UAnnotation) instance).getDetails();
                }
            }, null);

            // Features of UModelElement
            mUModelElement_documentation = UMetaBuilder.manual().createFeature("documentation",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModelElement_documentation, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModelElement) instance).getDocumentation();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModelElement) instance).setDocumentation((String) value);
                }
            });
            mUModelElement_annotations = UMetaBuilder.manual().createFeature("annotations",
                    RuntimePackage.theInstance.getUAnnotation(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUModelElement_annotations, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModelElement) instance).getAnnotations();
                }
            }, null);
            mUModelElement_package = UMetaBuilder.manual().createFeature("package",
                    RuntimePackage.theInstance.getUPackage(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModelElement_package, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModelElement) instance).getPackage();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModelElement) instance).setPackage((UPackage) value);
                }
            });

            // Features of UNamedElement
            mUNamedElement_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUNamedElement_name, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UNamedElement) instance).getName();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UNamedElement) instance).setName((String) value);
                }
            });

            // Features of UModel
            mUModel_modelName = UMetaBuilder.manual().createFeature("modelName",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModel_modelName, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModel) instance).getModelName();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModel) instance).setModelName((String) value);
                }
            });
            mUModel_fieldOfApplication = UMetaBuilder.manual().createFeature("fieldOfApplication",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModel_fieldOfApplication, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModel) instance).getFieldOfApplication();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModel) instance).setFieldOfApplication((String) value);
                }
            });
            mUModel_scope = UMetaBuilder.manual().createFeature("scope", TypeUtils.getPrimitiveType(String.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModel_scope, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModel) instance).getScope();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModel) instance).setScope((String) value);
                }
            });
            mUModel_versionDate = UMetaBuilder.manual().createFeature("versionDate",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModel_versionDate, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModel) instance).getVersionDate();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModel) instance).setVersionDate((String) value);
                }
            });
            mUModel_rootPackage = UMetaBuilder.manual().createFeature("rootPackage",
                    RuntimePackage.theInstance.getUPackage(), UAssociationType.COMPOSITION, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUModel_rootPackage, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UModel) instance).getRootPackage();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UModel) instance).setRootPackage((UPackage) value);
                }
            });

            // Features of UPackage
            mUPackage_content = UMetaBuilder.manual().createFeature("content",
                    RuntimePackage.theInstance.getUModelElement(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUPackage_content, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UPackage) instance).getContent();
                }
            }, null);

            // Features of Notification
            mNotification_type = UMetaBuilder.manual().createFeature("type",
                    RuntimePackage.theInstance.getNotificationType(), UAssociationType.PROPERTY, 1, 1);
            UMetaBuilder.manual().setFeatureAccessor(mNotification_type, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((Notification) instance).getType();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((Notification) instance).setType((NotificationType) value);
                }
            });
            mNotification_instance = UMetaBuilder.manual().createFeature("instance",
                    RuntimePackage.theInstance.getUObject(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mNotification_instance, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((Notification) instance).getInstance();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((Notification) instance).setInstance((UObject) value);
                }
            });
            mNotification_feature = UMetaBuilder.manual().createFeature("feature",
                    RuntimePackage.theInstance.getUStructuralFeature(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mNotification_feature, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((Notification) instance).getFeature();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((Notification) instance).setFeature((UStructuralFeature) value);
                }
            });

            // Features of UObject
            mUObject_uClassifier = UMetaBuilder.manual().createFeature("uClassifier",
                    RuntimePackage.theInstance.getUClass(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUObject_uClassifier, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UObject) instance).getUClassifier();
                }
            }, null);
            mUObject_uClassifier.setDocumentation(" the type of this object ");
            mUObject_uClassifier.setReadOnly(true);
            mUObject_uContainer = UMetaBuilder.manual().createFeature("uContainer",
                    RuntimePackage.theInstance.getUObject(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUObject_uContainer, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UObject) instance).getUContainer();
                }
            }, null);
            mUObject_uContainer.setDocumentation(
                    " if this instance is part of an composite association (composition)\r\n * the container points to the owner of this instance\r\n ");
            mUObject_uContainer.setReadOnly(true);
            mUObject_uContainingFeature = UMetaBuilder.manual().createFeature("uContainingFeature",
                    RuntimePackage.theInstance.getUStructuralFeature(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUObject_uContainingFeature, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UObject) instance).getUContainingFeature();
                }
            }, null);
            mUObject_uContainingFeature.setReadOnly(true);

            // Features of UClassifier
            mUClassifier_interfaces = UMetaBuilder.manual().createFeature("interfaces",
                    RuntimePackage.theInstance.getUClassifier(), UAssociationType.AGGREGATION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUClassifier_interfaces, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UClassifier) instance).getInterfaces();
                }
            }, null);
            mUClassifier_operations = UMetaBuilder.manual().createFeature("operations",
                    RuntimePackage.theInstance.getUOperation(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUClassifier_operations, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UClassifier) instance).getOperations();
                }
            }, null);

            // Features of UClass
            mUClass_abstract = UMetaBuilder.manual().createFeature("abstract",
                    TypeUtils.getPrimitiveType(boolean.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUClass_abstract, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UClass) instance).getAbstract();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UClass) instance).setAbstract((boolean) value);
                }
            });
            mUClass_superType = UMetaBuilder.manual().createFeature("superType",
                    RuntimePackage.theInstance.getUClassifier(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUClass_superType, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UClass) instance).getSuperType();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UClass) instance).setSuperType((UClassifier) value);
                }
            });
            mUClass_structuralFeatures = UMetaBuilder.manual().createFeature("structuralFeatures",
                    RuntimePackage.theInstance.getUStructuralFeature(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUClass_structuralFeatures, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UClass) instance).getStructuralFeatures();
                }
            }, null);

            // Features of UEnum
            mUEnum_literals = UMetaBuilder.manual().createFeature("literals",
                    RuntimePackage.theInstance.getUEnumerator(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUEnum_literals, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UEnum) instance).getLiterals();
                }
            }, null);

            // Features of UEnumerator
            mUEnumerator_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(int.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUEnumerator_value, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UEnumerator) instance).getValue();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UEnumerator) instance).setValue((int) value);
                }
            });

            // Features of UException
            mUException_standardMessage = UMetaBuilder.manual().createFeature("standardMessage",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUException_standardMessage, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UException) instance).getStandardMessage();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UException) instance).setStandardMessage((String) value);
                }
            });
            mUException_superType = UMetaBuilder.manual().createFeature("superType",
                    RuntimePackage.theInstance.getUException(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUException_superType, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UException) instance).getSuperType();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UException) instance).setSuperType((UException) value);
                }
            });

            // Features of UMultiplicity
            mUMultiplicity_lower = UMetaBuilder.manual().createFeature("lower", TypeUtils.getPrimitiveType(int.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUMultiplicity_lower, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UMultiplicity) instance).getLower();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UMultiplicity) instance).setLower((int) value);
                }
            });
            mUMultiplicity_upper = UMetaBuilder.manual().createFeature("upper", TypeUtils.getPrimitiveType(int.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUMultiplicity_upper, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UMultiplicity) instance).getUpper();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UMultiplicity) instance).setUpper((int) value);
                }
            });

            // Features of UTypedElement
            mUTypedElement_type = UMetaBuilder.manual().createFeature("type", RuntimePackage.theInstance.getUType(),
                    UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUTypedElement_type, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UTypedElement) instance).getType();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UTypedElement) instance).setType((UType) value);
                }
            });
            mUTypedElement_multiplicity = UMetaBuilder.manual().createFeature("multiplicity",
                    RuntimePackage.theInstance.getUMultiplicity(), UAssociationType.COMPOSITION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUTypedElement_multiplicity, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UTypedElement) instance).getMultiplicity();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UTypedElement) instance).setMultiplicity((UMultiplicity) value);
                }
            });

            // Features of UStructuralFeature
            mUStructuralFeature_aggregation = UMetaBuilder.manual().createFeature("aggregation",
                    RuntimePackage.theInstance.getUAssociationType(), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUStructuralFeature_aggregation, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UStructuralFeature) instance).getAggregation();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UStructuralFeature) instance).setAggregation((UAssociationType) value);
                }
            });

            // Features of UOperation
            mUOperation_abstract = UMetaBuilder.manual().createFeature("abstract",
                    TypeUtils.getPrimitiveType(boolean.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUOperation_abstract, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UOperation) instance).getAbstract();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UOperation) instance).setAbstract((boolean) value);
                }
            });
            mUOperation_const = UMetaBuilder.manual().createFeature("const", TypeUtils.getPrimitiveType(boolean.class),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUOperation_const, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UOperation) instance).getConst();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UOperation) instance).setConst((boolean) value);
                }
            });
            mUOperation_parameters = UMetaBuilder.manual().createFeature("parameters",
                    RuntimePackage.theInstance.getUParameter(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mUOperation_parameters, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UOperation) instance).getParameters();
                }
            }, null);

            // Features of UParameter
            mUParameter_direction = UMetaBuilder.manual().createFeature("direction",
                    RuntimePackage.theInstance.getUDirectionType(), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mUParameter_direction, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((UParameter) instance).getDirection();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((UParameter) instance).setDirection((UDirectionType) value);
                }
            });

        }
        { // assign features
            mUAnnotationDetail.getStructuralFeatures().add(mUAnnotationDetail_key);
            mUAnnotationDetail.getStructuralFeatures().add(mUAnnotationDetail_value);
            mUAnnotation.getStructuralFeatures().add(mUAnnotation_name);
            mUAnnotation.getStructuralFeatures().add(mUAnnotation_details);
            mUModelElement.getStructuralFeatures().add(mUModelElement_documentation);
            mUModelElement.getStructuralFeatures().add(mUModelElement_annotations);
            mUModelElement.getStructuralFeatures().add(mUModelElement_package);
            mUNamedElement.getStructuralFeatures().add(mUNamedElement_name);
            mUModel.getStructuralFeatures().add(mUModel_modelName);
            mUModel.getStructuralFeatures().add(mUModel_fieldOfApplication);
            mUModel.getStructuralFeatures().add(mUModel_scope);
            mUModel.getStructuralFeatures().add(mUModel_versionDate);
            mUModel.getStructuralFeatures().add(mUModel_rootPackage);
            mUPackage.getStructuralFeatures().add(mUPackage_content);
            mNotification.getStructuralFeatures().add(mNotification_type);
            mNotification.getStructuralFeatures().add(mNotification_instance);
            mNotification.getStructuralFeatures().add(mNotification_feature);
            mUObject.getStructuralFeatures().add(mUObject_uClassifier);
            mUObject.getStructuralFeatures().add(mUObject_uContainer);
            mUObject.getStructuralFeatures().add(mUObject_uContainingFeature);
            mUClassifier.getStructuralFeatures().add(mUClassifier_interfaces);
            mUClassifier.getStructuralFeatures().add(mUClassifier_operations);
            mUClass.getStructuralFeatures().add(mUClass_abstract);
            mUClass.getStructuralFeatures().add(mUClass_superType);
            mUClass.getStructuralFeatures().add(mUClass_structuralFeatures);
            mUEnum.getStructuralFeatures().add(mUEnum_literals);
            mUEnumerator.getStructuralFeatures().add(mUEnumerator_value);
            mUException.getStructuralFeatures().add(mUException_standardMessage);
            mUException.getStructuralFeatures().add(mUException_superType);
            mUMultiplicity.getStructuralFeatures().add(mUMultiplicity_lower);
            mUMultiplicity.getStructuralFeatures().add(mUMultiplicity_upper);
            mUTypedElement.getStructuralFeatures().add(mUTypedElement_type);
            mUTypedElement.getStructuralFeatures().add(mUTypedElement_multiplicity);
            mUStructuralFeature.getStructuralFeatures().add(mUStructuralFeature_aggregation);
            mUOperation.getStructuralFeatures().add(mUOperation_abstract);
            mUOperation.getStructuralFeatures().add(mUOperation_const);
            mUOperation.getStructuralFeatures().add(mUOperation_parameters);
            mUParameter.getStructuralFeatures().add(mUParameter_direction);
        }

        UMetaBuilder.manual().addLiteral(mNotificationType, "SET", 0, NotificationType.SET);
        UMetaBuilder.manual().addLiteral(mNotificationType, "UNSET", 1, NotificationType.UNSET);
        UMetaBuilder.manual().addLiteral(mNotificationType, "ADD", 2, NotificationType.ADD);
        UMetaBuilder.manual().addLiteral(mNotificationType, "ADD_MANY", 3, NotificationType.ADD_MANY);
        UMetaBuilder.manual().addLiteral(mNotificationType, "REMOVE", 4, NotificationType.REMOVE);
        UMetaBuilder.manual().addLiteral(mNotificationType, "REMOVE_MANY", 5, NotificationType.REMOVE_MANY);
        UMetaBuilder.manual().addLiteral(mUDirectionType, "IN", 0, UDirectionType.IN);
        UMetaBuilder.manual().addLiteral(mUDirectionType, "OUT", 1, UDirectionType.OUT);
        UMetaBuilder.manual().addLiteral(mUDirectionType, "INOUT", 2, UDirectionType.INOUT);
        UMetaBuilder.manual().addLiteral(mUDirectionType, "RETURN", 3, UDirectionType.RETURN);
        UMetaBuilder.manual().addLiteral(mUAssociationType, "ASSOCIATION", 0, UAssociationType.ASSOCIATION);
        UMetaBuilder.manual().addLiteral(mUAssociationType, "AGGREGATION", 1, UAssociationType.AGGREGATION);
        UMetaBuilder.manual().addLiteral(mUAssociationType, "COMPOSITION", 2, UAssociationType.COMPOSITION);
        UMetaBuilder.manual().addLiteral(mUAssociationType, "PROPERTY", 3, UAssociationType.PROPERTY);
    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createOperations() {
        { // Operations of UAnnotation
            UOperation operation = null;
            // operation : addDetail(void, String, String)
            operation = UMetaBuilder.manual().createOperation("addDetail", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UAnnotation) instance).addDetail((String) parameter[0], (String) parameter[1]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUAnnotation.getOperations().add(operation);
            // operation : getDetail(UAnnotationDetail, String)
            operation = UMetaBuilder.manual().createOperation("getDetail", false,
                    RuntimePackage.theInstance.getUAnnotationDetail(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UAnnotation) instance).getDetail((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUAnnotation.getOperations().add(operation);
        }
        { // Operations of UModelElement
            UOperation operation = null;
            // operation : freeze(void)
            operation = UMetaBuilder.manual().createOperation("freeze", false, TypeUtils.getPrimitiveType(void.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UModelElement) instance).freeze();
                            return null;
                        }
                    });
            operation.setDocumentation(
                    " mark the meta data to be freezed, in this case, it can not be changed anymore ");
            mUModelElement.getOperations().add(operation);
            // operation : isFrozen(boolean)
            operation = UMetaBuilder.manual().createOperation("isFrozen", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UModelElement) instance).isFrozen();
                        }
                    });
            operation.setDocumentation(" check if the meta data has been freezed ");
            // Annotations for UModelElement:isFrozen(boolean)
            operation.createAnnotation("const");
            mUModelElement.getOperations().add(operation);
            // operation : createAnnotation(UAnnotation, String)
            operation = UMetaBuilder.manual().createOperation("createAnnotation", false,
                    RuntimePackage.theInstance.getUAnnotation(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UModelElement) instance).createAnnotation((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUModelElement.getOperations().add(operation);
            // operation : getAnnotation(UAnnotation, String)
            operation = UMetaBuilder.manual().createOperation("getAnnotation", false,
                    RuntimePackage.theInstance.getUAnnotation(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UModelElement) instance).getAnnotation((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUModelElement.getOperations().add(operation);
            // operation : getAnnotationDetail(UAnnotationDetail, String, String)
            operation = UMetaBuilder.manual().createOperation("getAnnotationDetail", false,
                    RuntimePackage.theInstance.getUAnnotationDetail(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UModelElement) instance).getAnnotationDetail((String) parameter[0],
                                    (String) parameter[1]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "key", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUModelElement.getOperations().add(operation);
            // operation : build(void)
            operation = UMetaBuilder.manual().createOperation("build", true, TypeUtils.getPrimitiveType(void.class), 0,
                    1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UModelElement) instance).build();
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * initializes the model element, e.g. create private member for reflection access\r\n ");
            mUModelElement.getOperations().add(operation);
        }
        { // Operations of UNamedElement
            UOperation operation = null;
            // operation : getQualifiedName(QualifiedName)
            operation = UMetaBuilder.manual().createOperation("getQualifiedName", false,
                    UtilsPackage.theInstance.getQualifiedName(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UNamedElement) instance).getQualifiedName();
                        }
                    });
            // Annotations for UNamedElement:getQualifiedName(QualifiedName)
            operation.createAnnotation("const");
            mUNamedElement.getOperations().add(operation);
        }
        { // Operations of UPackage
            UOperation operation = null;
            // operation : createPackage(UPackage, String)
            operation = UMetaBuilder.manual().createOperation("createPackage", false,
                    RuntimePackage.theInstance.getUPackage(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).createPackage((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n * creates a new sub package of this package\r\n * @note if the package allready exists (getSubpackage(name, true)) the existing package is returned\r\n ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : createInterface(UInterface, String)
            operation = UMetaBuilder.manual().createOperation("createInterface", false,
                    RuntimePackage.theInstance.getUInterface(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).createInterface((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    "\r\n * creates a new interface within this package\r\n * @precondition isFrozen() == false\r\n ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : createClass(UClass, String)
            operation = UMetaBuilder.manual().createOperation("createClass", false,
                    RuntimePackage.theInstance.getUClass(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).createClass((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    "\r\n * creates a new class within this package\r\n * @precondition isFrozen() == false\r\n ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : createEnumeration(UEnum, String)
            operation = UMetaBuilder.manual().createOperation("createEnumeration", false,
                    RuntimePackage.theInstance.getUEnum(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).createEnumeration((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n * creates a new enumeration in this package\r\n * @precondition isFrozen() == false\r\n ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : getSubpackage(UPackage, String, boolean)
            operation = UMetaBuilder.manual().createOperation("getSubpackage", false,
                    RuntimePackage.theInstance.getUPackage(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).getSubpackage((String) parameter[0], (boolean) parameter[1]);
                        }
                    });
            operation.setDocumentation(
                    "\r\n * returns the subpackage with the given namen, if it exists\r\n * @param name the name of the subpackage\r\n * @param ownedOnly if set to true, only this package is checked for the subpackage, otherwise\r\n * all owned subpackages (getContent().filter(typeof(UPackage))) will be checked too. \r\n ");
            // Annotations for UPackage:getSubpackage(UPackage, String, boolean)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "ownedOnly", TypeUtils.getPrimitiveType(boolean.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : getClassifier(UClassifier, String, boolean)
            operation = UMetaBuilder.manual().createOperation("getClassifier", false,
                    RuntimePackage.theInstance.getUClassifier(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).getClassifier((String) parameter[0], (boolean) parameter[1]);
                        }
                    });
            // Annotations for UPackage:getClassifier(UClassifier, String, boolean)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "ownedOnly", TypeUtils.getPrimitiveType(boolean.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
            // operation : getClassifier(UClassifier, QualifiedName, boolean)
            operation = UMetaBuilder.manual().createOperation("getClassifier", false,
                    RuntimePackage.theInstance.getUClassifier(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UPackage) instance).getClassifier((QualifiedName) parameter[0],
                                    (boolean) parameter[1]);
                        }
                    });
            // Annotations for UPackage:getClassifier(UClassifier, QualifiedName, boolean)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "qn", UtilsPackage.theInstance.getQualifiedName(), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "ownedOnly", TypeUtils.getPrimitiveType(boolean.class), 0, 1,
                    UDirectionType.IN);
            mUPackage.getOperations().add(operation);
        }
        { // Operations of IValueChangeListener
            UOperation operation = null;
            // operation : onValueChange(void, Notification)
            operation = UMetaBuilder.manual().createOperation("onValueChange", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((IValueChangeListener) instance).onValueChange((Notification) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * This method is called, when a feature has changed\r\n * @warn normally the new value has not been applied when this method is called. \r\n * To get access to the new value, use notification.getNewValue()\r\n ");
            UMetaBuilder.manual().addParameter(operation, "notification", RuntimePackage.theInstance.getNotification(),
                    0, 1, UDirectionType.IN);
            mIValueChangeListener.getOperations().add(operation);
        }
        { // Operations of UObject
            UOperation operation = null;
            // operation : registerListener(void, IValueChangeListener)
            operation = UMetaBuilder.manual().createOperation("registerListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).registerListener((IValueChangeListener) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * register a listener that is notified, whenever the value of an structural feature changes\r\n * @note an instance of listener can only be registered once, if the listener instance is already\r\n * registered, this method has no effect\r\n * @note this listener will be notified for each change, if you would like to have a selective\r\n * notification, use registerListener(feature, listener)\r\n ");
            UMetaBuilder.manual().addParameter(operation, "listener",
                    RuntimePackage.theInstance.getIValueChangeListener(), 0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : registerListener(void, UStructuralFeature, IValueChangeListener)
            operation = UMetaBuilder.manual().createOperation("registerListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).registerListener((UStructuralFeature) parameter[0],
                                    (IValueChangeListener) parameter[1]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * registers a listener that is notified, only if the value of the given feature changes\r\n * @note an instance of IValueChangeListener can only be registered once per UStructuralFeature. \r\n * If this listener instance is already registered, this method has no effect. \r\n ");
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "listener",
                    RuntimePackage.theInstance.getIValueChangeListener(), 0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : removeClassifierListener(void, IValueChangeListener)
            operation = UMetaBuilder.manual().createOperation("removeClassifierListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).removeClassifierListener((IValueChangeListener) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * removes the listener\r\n * @note if the same instance of the listener has also been registered for an specific structural feature\r\n * this registration is not touched. \r\n ");
            UMetaBuilder.manual().addParameter(operation, "listener",
                    RuntimePackage.theInstance.getIValueChangeListener(), 0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : removeListener(void, UStructuralFeature, IValueChangeListener)
            operation = UMetaBuilder.manual().createOperation("removeListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).removeListener((UStructuralFeature) parameter[0],
                                    (IValueChangeListener) parameter[1]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * removes the listener from notification for the given feature. \r\n * @note if the same instance of the listener has also been registered for any other feature, or for the whole class\r\n * this registrations are not touched\r\n ");
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "listener",
                    RuntimePackage.theInstance.getIValueChangeListener(), 0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : removeAllListener(void, UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("removeAllListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).removeAllListener((UStructuralFeature) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * removes all listener for the given feature\r\n * @note if a listener for the whole class has been registered, this registration is not touched. \r\n ");
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : removeAllClassifierListener(void)
            operation = UMetaBuilder.manual().createOperation("removeAllClassifierListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).removeAllClassifierListener();
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * removes all listener that has not been registered for a specific structural feature. \r\n ");
            mUObject.getOperations().add(operation);
            // operation : removeListener(void, IValueChangeListener)
            operation = UMetaBuilder.manual().createOperation("removeListener", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).removeListener((IValueChangeListener) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation("\r\n * completly remove the listener from this UObject instance\r\n * \r\n ");
            UMetaBuilder.manual().addParameter(operation, "listener",
                    RuntimePackage.theInstance.getIValueChangeListener(), 0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : needNotification(boolean, UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("needNotification", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UObject) instance).needNotification((UStructuralFeature) parameter[0]);
                        }
                    });
            // Annotations for UObject:needNotification(boolean, UStructuralFeature)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : unset(Object, UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("unset", false, TypeUtils.getPrimitiveType(Object.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UObject) instance).unset((UStructuralFeature) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : uGet(Object, UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("uGet", false, TypeUtils.getPrimitiveType(Object.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UObject) instance).uGet((UStructuralFeature) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : uGet(Object, UStructuralFeature, int)
            operation = UMetaBuilder.manual().createOperation("uGet", false, TypeUtils.getPrimitiveType(Object.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UObject) instance).uGet((UStructuralFeature) parameter[0], (int) parameter[1]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "index", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : uSet(void, UStructuralFeature, Object)
            operation = UMetaBuilder.manual().createOperation("uSet", false, TypeUtils.getPrimitiveType(void.class), 0,
                    1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).uSet((UStructuralFeature) parameter[0], (Object) parameter[1]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : uAdd(void, UStructuralFeature, Object)
            operation = UMetaBuilder.manual().createOperation("uAdd", false, TypeUtils.getPrimitiveType(void.class), 0,
                    1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).uAdd((UStructuralFeature) parameter[0], (Object) parameter[1]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : uAdd(void, UStructuralFeature, int, Object)
            operation = UMetaBuilder.manual().createOperation("uAdd", false, TypeUtils.getPrimitiveType(void.class), 0,
                    1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UObject) instance).uAdd((UStructuralFeature) parameter[0], (int) parameter[1],
                                    (Object) parameter[2]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "feature", RuntimePackage.theInstance.getUStructuralFeature(),
                    0, 1, UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "index", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            mUObject.getOperations().add(operation);
            // operation : invoke(Object, UOperation, Object)
            operation = UMetaBuilder.manual().createOperation("invoke", false, TypeUtils.getPrimitiveType(Object.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UObject) instance).invoke((UOperation) parameter[0], (Object) parameter[1]);
                        }
                    });
            operation.setDocumentation(
                    "\r\n * Invokes an operation associated with this object (and defined in its UClassifier)\r\n * in case the Operation has more than one parameter, the value shall contain the paramter values as array, in the same \r\n * order as the parameters. In case the Operation has no parameter the value may be null or any value (its ignored). \r\n * @param operation the operation to be called on this instance\r\n * @param the parameter value(s) or null\r\n * @return the return value of the function or null if the operation is \"void\" operation\r\n ");
            UMetaBuilder.manual().addParameter(operation, "operation", RuntimePackage.theInstance.getUOperation(), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            mUObject.getOperations().add(operation);
        }
        { // Operations of UType
            UOperation operation = null;
            // operation : inherits(boolean, UType)
            operation = UMetaBuilder.manual().createOperation("inherits", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UType) instance).inherits((UType) parameter[0]);
                        }
                    });
            operation.setDocumentation(" checks if the query type inherits from this type ");
            // Annotations for UType:inherits(boolean, UType)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "query", RuntimePackage.theInstance.getUType(), 0, 1,
                    UDirectionType.IN);
            mUType.getOperations().add(operation);
        }
        { // Operations of UClassifier
            UOperation operation = null;
            // operation : addParent(void, UClassifier)
            operation = UMetaBuilder.manual().createOperation("addParent", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((UClassifier) instance).addParent((UClassifier) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n   * adds a new superType / parent type to this classifier\r\n   * @precondition isFrozen() == false\r\n   ");
            UMetaBuilder.manual().addParameter(operation, "cl", RuntimePackage.theInstance.getUClassifier(), 0, 1,
                    UDirectionType.IN);
            mUClassifier.getOperations().add(operation);
            // operation : createOperation(UOperation, String)
            operation = UMetaBuilder.manual().createOperation("createOperation", false,
                    RuntimePackage.theInstance.getUOperation(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).createOperation((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n   * adds a new operation to this type\r\n   * @precondition isFrozen() == false\r\n   ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUClassifier.getOperations().add(operation);
            // operation : getAllStructuralFeatures(UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("getAllStructuralFeatures", true,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, -1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getAllStructuralFeatures();
                        }
                    });
            operation.setDocumentation(
                    " returns all structural features of this classifier, \r\n   * this includes the owned features as well as all features of \r\n   * its parents\r\n   ");
            mUClassifier.getOperations().add(operation);
            // operation : getAllAttributes(UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("getAllAttributes", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, -1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getAllAttributes();
                        }
                    });
            operation.setDocumentation(
                    "\r\n   * returns all structural features, that have a primitive type (int, long, double, ...) \r\n   * as type; e.g. feature.isAttribute() == true\r\n   ");
            // Annotations for UClassifier:getAllAttributes(UStructuralFeature)
            operation.createAnnotation("const");
            mUClassifier.getOperations().add(operation);
            // operation : getAllReferences(UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("getAllReferences", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, -1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getAllReferences();
                        }
                    });
            operation.setDocumentation(
                    " \r\n   * returns all structural features that have a complex type; e.g. feature.isReference() == true\r\n   ");
            // Annotations for UClassifier:getAllReferences(UStructuralFeature)
            operation.createAnnotation("const");
            mUClassifier.getOperations().add(operation);
            // operation : getAllStructuralFeatures(UStructuralFeature, UAssociationType)
            operation = UMetaBuilder.manual().createOperation("getAllStructuralFeatures", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, -1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getAllStructuralFeatures((UAssociationType) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    "\r\n * returns structural features. (references)\r\n * \r\n * @param type minimum filter, only features, with a more rigid association type will be added\r\n * into the iterator, following the order: \r\n * - COMPOSITION : only composite features\r\n * - ASSOCIATION: association and composition\r\n * - AGGREGATION: aggregation, association and compositon\r\n * - PROPERTY: all complex features\r\n * @return\r\n ");
            // Annotations for UClassifier:getAllStructuralFeatures(UStructuralFeature, UAssociationType)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "type", RuntimePackage.theInstance.getUAssociationType(), 0,
                    1, UDirectionType.IN);
            mUClassifier.getOperations().add(operation);
            // operation : getFeature(UStructuralFeature, String)
            operation = UMetaBuilder.manual().createOperation("getFeature", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getFeature((String) parameter[0]);
                        }
                    });
            // Annotations for UClassifier:getFeature(UStructuralFeature, String)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUClassifier.getOperations().add(operation);
            // operation : getOperation(UOperation, String)
            operation = UMetaBuilder.manual().createOperation("getOperation", false,
                    RuntimePackage.theInstance.getUOperation(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getOperation((String) parameter[0]);
                        }
                    });
            // Annotations for UClassifier:getOperation(UOperation, String)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUClassifier.getOperations().add(operation);
            // operation : getAllParents(UClassifier)
            operation = UMetaBuilder.manual().createOperation("getAllParents", false,
                    RuntimePackage.theInstance.getUClassifier(), 0, -1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClassifier) instance).getAllParents();
                        }
                    });
            mUClassifier.getOperations().add(operation);
        }
        { // Operations of UClass
            UOperation operation = null;
            // operation : createFeature(UStructuralFeature, String)
            operation = UMetaBuilder.manual().createOperation("createFeature", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClass) instance).createFeature((String) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n   * creates a new StructuralFeature for this class\r\n   * @precondition isFrozen() == false\r\n   ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUClass.getOperations().add(operation);
            // operation : createNewInstance(UObject)
            operation = UMetaBuilder.manual().createOperation("createNewInstance", false,
                    RuntimePackage.theInstance.getUObject(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UClass) instance).createNewInstance();
                        }
                    });
            operation.setDocumentation("\r\n   * Creates a new instance of the corresponding type\r\n   ");
            // Annotations for UClass:createNewInstance(UObject)
            operation.createAnnotation("const");
            mUClass.getOperations().add(operation);
        }
        { // Operations of UEnum
            UOperation operation = null;
            // operation : createLiteral(UEnumerator, String, int)
            operation = UMetaBuilder.manual().createOperation("createLiteral", false,
                    RuntimePackage.theInstance.getUEnumerator(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).createLiteral((String) parameter[0], (int) parameter[1]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n   * creates a new Literal\r\n   * @precondition isFrozen() == false\r\n   ");
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mUEnum.getOperations().add(operation);
            // operation : createNewInstance(Object, String)
            operation = UMetaBuilder.manual().createOperation("createNewInstance", false,
                    TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).createNewInstance((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUEnum.getOperations().add(operation);
            // operation : createNewInstance(Object, int)
            operation = UMetaBuilder.manual().createOperation("createNewInstance", false,
                    TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).createNewInstance((int) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mUEnum.getOperations().add(operation);
            // operation : createNewInstance(Object, UEnumerator)
            operation = UMetaBuilder.manual().createOperation("createNewInstance", false,
                    TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).createNewInstance((UEnumerator) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "enumerator", RuntimePackage.theInstance.getUEnumerator(), 0,
                    1, UDirectionType.IN);
            mUEnum.getOperations().add(operation);
            // operation : getEnumerator(UEnumerator, String)
            operation = UMetaBuilder.manual().createOperation("getEnumerator", false,
                    RuntimePackage.theInstance.getUEnumerator(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).getEnumerator((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "name", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mUEnum.getOperations().add(operation);
            // operation : getEnumerator(UEnumerator, int)
            operation = UMetaBuilder.manual().createOperation("getEnumerator", false,
                    RuntimePackage.theInstance.getUEnumerator(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UEnum) instance).getEnumerator((int) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mUEnum.getOperations().add(operation);
        }
        { // Operations of IStructuralElement
            UOperation operation = null;
            // operation : getName(String)
            operation = UMetaBuilder.manual().createOperation("getName", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((IStructuralElement) instance).getName();
                        }
                    });
            // Annotations for IStructuralElement:getName(String)
            operation.createAnnotation("const");
            mIStructuralElement.getOperations().add(operation);
            // operation : getDocumentation(String)
            operation = UMetaBuilder.manual().createOperation("getDocumentation", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((IStructuralElement) instance).getDocumentation();
                        }
                    });
            // Annotations for IStructuralElement:getDocumentation(String)
            operation.createAnnotation("const");
            mIStructuralElement.getOperations().add(operation);
            // operation : getType(UType)
            operation = UMetaBuilder.manual().createOperation("getType", false, RuntimePackage.theInstance.getUType(),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((IStructuralElement) instance).getType();
                        }
                    });
            // Annotations for IStructuralElement:getType(UType)
            operation.createAnnotation("const");
            mIStructuralElement.getOperations().add(operation);
            // operation : getMultiplicity(UMultiplicity)
            operation = UMetaBuilder.manual().createOperation("getMultiplicity", false,
                    RuntimePackage.theInstance.getUMultiplicity(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((IStructuralElement) instance).getMultiplicity();
                        }
                    });
            // Annotations for IStructuralElement:getMultiplicity(UMultiplicity)
            operation.createAnnotation("const");
            mIStructuralElement.getOperations().add(operation);
            // operation : isMany(boolean)
            operation = UMetaBuilder.manual().createOperation("isMany", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((IStructuralElement) instance).isMany();
                        }
                    });
            operation.setDocumentation(
                    " returns true, if the more than one element can be stored within this feature ( upperBound != 1) ");
            // Annotations for IStructuralElement:isMany(boolean)
            operation.createAnnotation("const");
            mIStructuralElement.getOperations().add(operation);
        }
        { // Operations of UTypedElement
            UOperation operation = null;
            // operation : isAttribute(boolean)
            operation = UMetaBuilder.manual().createOperation("isAttribute", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UTypedElement) instance).isAttribute();
                        }
                    });
            operation.setDocumentation(" returns true, if the type (getType()) references to an UPrimitiveType ");
            // Annotations for UTypedElement:isAttribute(boolean)
            operation.createAnnotation("const");
            mUTypedElement.getOperations().add(operation);
            // operation : isReference(boolean)
            operation = UMetaBuilder.manual().createOperation("isReference", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UTypedElement) instance).isReference();
                        }
                    });
            operation.setDocumentation(" returns true if the type is a complex type, e.g. isAttribute() == false ");
            // Annotations for UTypedElement:isReference(boolean)
            operation.createAnnotation("const");
            mUTypedElement.getOperations().add(operation);
            // operation : isMany(boolean)
            operation = UMetaBuilder.manual().createOperation("isMany", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UTypedElement) instance).isMany();
                        }
                    });
            operation.setDocumentation(
                    " returns true, if the more than one element can be stored within this feature ( upperBound != 1) ");
            // Annotations for UTypedElement:isMany(boolean)
            operation.createAnnotation("const");
            mUTypedElement.getOperations().add(operation);
        }
        { // Operations of UStructuralFeature
            UOperation operation = null;
            // operation : getOwner(UClass)
            operation = UMetaBuilder.manual().createOperation("getOwner", false, RuntimePackage.theInstance.getUClass(),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UStructuralFeature) instance).getOwner();
                        }
                    });
            // Annotations for UStructuralFeature:getOwner(UClass)
            operation.createAnnotation("const");
            mUStructuralFeature.getOperations().add(operation);
            // operation : isReadOnly(boolean)
            operation = UMetaBuilder.manual().createOperation("isReadOnly", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((UStructuralFeature) instance).isReadOnly();
                        }
                    });
            operation.setDocumentation(
                    "\r\n * returns true if the feature can not be written, though the reflection system\r\n * @note a feature may be read only even if a setter is implemented within the implementation class, but not definied within the interface\r\n ");
            // Annotations for UStructuralFeature:isReadOnly(boolean)
            operation.createAnnotation("const");
            mUStructuralFeature.getOperations().add(operation);
        }
    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void buildHierarchies() {
        mUNamedElement.setSuperType(RuntimePackage.theInstance.getUModelElement());
        mUModel.setSuperType(RuntimePackage.theInstance.getUModelElement());
        mUPackage.setSuperType(RuntimePackage.theInstance.getUNamedElement());
        mUType.setSuperType(RuntimePackage.theInstance.getUNamedElement());
        mUPrimitiveType.setSuperType(RuntimePackage.theInstance.getUType());
        mUClassifier.setSuperType(RuntimePackage.theInstance.getUType());
        mUInterface.setSuperType(RuntimePackage.theInstance.getUClassifier());
        mUClass.setSuperType(RuntimePackage.theInstance.getUClassifier());
        mUEnum.setSuperType(RuntimePackage.theInstance.getUClassifier());
        mUEnumerator.setSuperType(RuntimePackage.theInstance.getUNamedElement());
        mUException.setSuperType(RuntimePackage.theInstance.getUType());
        mUTypedElement.setSuperType(RuntimePackage.theInstance.getUNamedElement());
        mUStructuralFeature.getInterfaces().add(RuntimePackage.theInstance.getIStructuralElement());
        mUStructuralFeature.setSuperType(RuntimePackage.theInstance.getUTypedElement());
        mUOperation.getInterfaces().add(RuntimePackage.theInstance.getIStructuralElement());
        mUOperation.setSuperType(RuntimePackage.theInstance.getUNamedElement());
        mUParameter.setSuperType(RuntimePackage.theInstance.getUTypedElement());

    }

    //////////////////////////////////////////////////////////////////////
    // Classifier GETTER //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    public UClass getUAnnotationDetail() {
        if (mUAnnotationDetail == null) {
            mUAnnotationDetail = UCoreMetaRepository.getUClass(UAnnotationDetail.class);
        }
        return mUAnnotationDetail;
    }

    /**
     * @generated
     */
    public UClass getUAnnotation() {
        if (mUAnnotation == null) {
            mUAnnotation = UCoreMetaRepository.getUClass(UAnnotation.class);
        }
        return mUAnnotation;
    }

    /**
     * @generated
     */
    public UClass getUModelElement() {
        if (mUModelElement == null) {
            mUModelElement = UCoreMetaRepository.getUClass(UModelElement.class);
        }
        return mUModelElement;
    }

    /**
     * @generated
     */
    public UClass getUNamedElement() {
        if (mUNamedElement == null) {
            mUNamedElement = UCoreMetaRepository.getUClass(UNamedElement.class);
        }
        return mUNamedElement;
    }

    /**
     * @generated
     */
    public UClass getUModel() {
        if (mUModel == null) {
            mUModel = UCoreMetaRepository.getUClass(UModel.class);
        }
        return mUModel;
    }

    /**
     * @generated
     */
    public UClass getUPackage() {
        if (mUPackage == null) {
            mUPackage = UCoreMetaRepository.getUClass(UPackage.class);
        }
        return mUPackage;
    }

    /**
     * @generated
     */
    public UEnum getNotificationType() {
        if (mNotificationType == null) {
            mNotificationType = UCoreMetaRepository.getUEnumeration(NotificationType.class);
        }
        return mNotificationType;
    }

    /**
     * @generated
     */
    public UClass getNotification() {
        if (mNotification == null) {
            mNotification = UCoreMetaRepository.getUClass(Notification.class);
        }
        return mNotification;
    }

    /**
     * @generated
     */
    public UInterface getIValueChangeListener() {
        if (mIValueChangeListener == null) {
            mIValueChangeListener = UCoreMetaRepository.getUInterface(IValueChangeListener.class);
        }
        return mIValueChangeListener;
    }

    /**
     * @generated
     */
    public UClass getUObject() {
        if (mUObject == null) {
            mUObject = UCoreMetaRepository.getUClass(UObject.class);
        }
        return mUObject;
    }

    /**
     * @generated
     */
    public UClass getUType() {
        if (mUType == null) {
            mUType = UCoreMetaRepository.getUClass(UType.class);
        }
        return mUType;
    }

    /**
     * @generated
     */
    public UClass getUPrimitiveType() {
        if (mUPrimitiveType == null) {
            mUPrimitiveType = UCoreMetaRepository.getUClass(UPrimitiveType.class);
        }
        return mUPrimitiveType;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        if (mUClassifier == null) {
            mUClassifier = UCoreMetaRepository.getUClass(UClassifier.class);
        }
        return mUClassifier;
    }

    /**
     * @generated
     */
    public UClass getUInterface() {
        if (mUInterface == null) {
            mUInterface = UCoreMetaRepository.getUClass(UInterface.class);
        }
        return mUInterface;
    }

    /**
     * @generated
     */
    public UClass getUClass() {
        if (mUClass == null) {
            mUClass = UCoreMetaRepository.getUClass(UClass.class);
        }
        return mUClass;
    }

    /**
     * @generated
     */
    public UClass getUEnum() {
        if (mUEnum == null) {
            mUEnum = UCoreMetaRepository.getUClass(UEnum.class);
        }
        return mUEnum;
    }

    /**
     * @generated
     */
    public UClass getUEnumerator() {
        if (mUEnumerator == null) {
            mUEnumerator = UCoreMetaRepository.getUClass(UEnumerator.class);
        }
        return mUEnumerator;
    }

    /**
     * @generated
     */
    public UClass getUException() {
        if (mUException == null) {
            mUException = UCoreMetaRepository.getUClass(UException.class);
        }
        return mUException;
    }

    /**
     * @generated
     */
    public UClass getUMultiplicity() {
        if (mUMultiplicity == null) {
            mUMultiplicity = UCoreMetaRepository.getUClass(UMultiplicity.class);
        }
        return mUMultiplicity;
    }

    /**
     * @generated
     */
    public UInterface getIStructuralElement() {
        if (mIStructuralElement == null) {
            mIStructuralElement = UCoreMetaRepository.getUInterface(IStructuralElement.class);
        }
        return mIStructuralElement;
    }

    /**
     * @generated
     */
    public UClass getUTypedElement() {
        if (mUTypedElement == null) {
            mUTypedElement = UCoreMetaRepository.getUClass(UTypedElement.class);
        }
        return mUTypedElement;
    }

    /**
     * @generated
     */
    public UClass getUStructuralFeature() {
        if (mUStructuralFeature == null) {
            mUStructuralFeature = UCoreMetaRepository.getUClass(UStructuralFeature.class);
        }
        return mUStructuralFeature;
    }

    /**
     * @generated
     */
    public UClass getUOperation() {
        if (mUOperation == null) {
            mUOperation = UCoreMetaRepository.getUClass(UOperation.class);
        }
        return mUOperation;
    }

    /**
     * @generated
     */
    public UClass getUParameter() {
        if (mUParameter == null) {
            mUParameter = UCoreMetaRepository.getUClass(UParameter.class);
        }
        return mUParameter;
    }

    /**
     * @generated
     */
    public UEnum getUDirectionType() {
        if (mUDirectionType == null) {
            mUDirectionType = UCoreMetaRepository.getUEnumeration(UDirectionType.class);
        }
        return mUDirectionType;
    }

    /**
     * @generated
     */
    public UEnum getUAssociationType() {
        if (mUAssociationType == null) {
            mUAssociationType = UCoreMetaRepository.getUEnumeration(UAssociationType.class);
        }
        return mUAssociationType;
    }

    //////////////////////////////////////////////////////////////////////
    // StructuralFeatures GETTER //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    public UStructuralFeature getUAnnotationDetail_key() {
        if (mUAnnotationDetail_key == null)
            mUAnnotationDetail_key = getUAnnotationDetail().getFeature("key");
        return mUAnnotationDetail_key;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUAnnotationDetail_value() {
        if (mUAnnotationDetail_value == null)
            mUAnnotationDetail_value = getUAnnotationDetail().getFeature("value");
        return mUAnnotationDetail_value;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUAnnotation_name() {
        if (mUAnnotation_name == null)
            mUAnnotation_name = getUAnnotation().getFeature("name");
        return mUAnnotation_name;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUAnnotation_details() {
        if (mUAnnotation_details == null)
            mUAnnotation_details = getUAnnotation().getFeature("details");
        return mUAnnotation_details;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModelElement_documentation() {
        if (mUModelElement_documentation == null)
            mUModelElement_documentation = getUModelElement().getFeature("documentation");
        return mUModelElement_documentation;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModelElement_annotations() {
        if (mUModelElement_annotations == null)
            mUModelElement_annotations = getUModelElement().getFeature("annotations");
        return mUModelElement_annotations;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModelElement_package() {
        if (mUModelElement_package == null)
            mUModelElement_package = getUModelElement().getFeature("package");
        return mUModelElement_package;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUNamedElement_name() {
        if (mUNamedElement_name == null)
            mUNamedElement_name = getUNamedElement().getFeature("name");
        return mUNamedElement_name;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModel_modelName() {
        if (mUModel_modelName == null)
            mUModel_modelName = getUModel().getFeature("modelName");
        return mUModel_modelName;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModel_fieldOfApplication() {
        if (mUModel_fieldOfApplication == null)
            mUModel_fieldOfApplication = getUModel().getFeature("fieldOfApplication");
        return mUModel_fieldOfApplication;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModel_scope() {
        if (mUModel_scope == null)
            mUModel_scope = getUModel().getFeature("scope");
        return mUModel_scope;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModel_versionDate() {
        if (mUModel_versionDate == null)
            mUModel_versionDate = getUModel().getFeature("versionDate");
        return mUModel_versionDate;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUModel_rootPackage() {
        if (mUModel_rootPackage == null)
            mUModel_rootPackage = getUModel().getFeature("rootPackage");
        return mUModel_rootPackage;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUPackage_content() {
        if (mUPackage_content == null)
            mUPackage_content = getUPackage().getFeature("content");
        return mUPackage_content;
    }

    /**
     * @generated
     */
    public UStructuralFeature getNotification_type() {
        if (mNotification_type == null)
            mNotification_type = getNotification().getFeature("type");
        return mNotification_type;
    }

    /**
     * @generated
     */
    public UStructuralFeature getNotification_instance() {
        if (mNotification_instance == null)
            mNotification_instance = getNotification().getFeature("instance");
        return mNotification_instance;
    }

    /**
     * @generated
     */
    public UStructuralFeature getNotification_feature() {
        if (mNotification_feature == null)
            mNotification_feature = getNotification().getFeature("feature");
        return mNotification_feature;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUObject_uClassifier() {
        if (mUObject_uClassifier == null)
            mUObject_uClassifier = getUObject().getFeature("uClassifier");
        return mUObject_uClassifier;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUObject_uContainer() {
        if (mUObject_uContainer == null)
            mUObject_uContainer = getUObject().getFeature("uContainer");
        return mUObject_uContainer;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUObject_uContainingFeature() {
        if (mUObject_uContainingFeature == null)
            mUObject_uContainingFeature = getUObject().getFeature("uContainingFeature");
        return mUObject_uContainingFeature;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUClassifier_interfaces() {
        if (mUClassifier_interfaces == null)
            mUClassifier_interfaces = getUClassifier().getFeature("interfaces");
        return mUClassifier_interfaces;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUClassifier_operations() {
        if (mUClassifier_operations == null)
            mUClassifier_operations = getUClassifier().getFeature("operations");
        return mUClassifier_operations;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUClass_abstract() {
        if (mUClass_abstract == null)
            mUClass_abstract = getUClass().getFeature("abstract");
        return mUClass_abstract;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUClass_superType() {
        if (mUClass_superType == null)
            mUClass_superType = getUClass().getFeature("superType");
        return mUClass_superType;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUClass_structuralFeatures() {
        if (mUClass_structuralFeatures == null)
            mUClass_structuralFeatures = getUClass().getFeature("structuralFeatures");
        return mUClass_structuralFeatures;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUEnum_literals() {
        if (mUEnum_literals == null)
            mUEnum_literals = getUEnum().getFeature("literals");
        return mUEnum_literals;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUEnumerator_value() {
        if (mUEnumerator_value == null)
            mUEnumerator_value = getUEnumerator().getFeature("value");
        return mUEnumerator_value;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUException_standardMessage() {
        if (mUException_standardMessage == null)
            mUException_standardMessage = getUException().getFeature("standardMessage");
        return mUException_standardMessage;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUException_superType() {
        if (mUException_superType == null)
            mUException_superType = getUException().getFeature("superType");
        return mUException_superType;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUMultiplicity_lower() {
        if (mUMultiplicity_lower == null)
            mUMultiplicity_lower = getUMultiplicity().getFeature("lower");
        return mUMultiplicity_lower;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUMultiplicity_upper() {
        if (mUMultiplicity_upper == null)
            mUMultiplicity_upper = getUMultiplicity().getFeature("upper");
        return mUMultiplicity_upper;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUTypedElement_type() {
        if (mUTypedElement_type == null)
            mUTypedElement_type = getUTypedElement().getFeature("type");
        return mUTypedElement_type;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUTypedElement_multiplicity() {
        if (mUTypedElement_multiplicity == null)
            mUTypedElement_multiplicity = getUTypedElement().getFeature("multiplicity");
        return mUTypedElement_multiplicity;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUStructuralFeature_aggregation() {
        if (mUStructuralFeature_aggregation == null)
            mUStructuralFeature_aggregation = getUStructuralFeature().getFeature("aggregation");
        return mUStructuralFeature_aggregation;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUOperation_abstract() {
        if (mUOperation_abstract == null)
            mUOperation_abstract = getUOperation().getFeature("abstract");
        return mUOperation_abstract;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUOperation_const() {
        if (mUOperation_const == null)
            mUOperation_const = getUOperation().getFeature("const");
        return mUOperation_const;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUOperation_parameters() {
        if (mUOperation_parameters == null)
            mUOperation_parameters = getUOperation().getFeature("parameters");
        return mUOperation_parameters;
    }

    /**
     * @generated
     */
    public UStructuralFeature getUParameter_direction() {
        if (mUParameter_direction == null)
            mUParameter_direction = getUParameter().getFeature("direction");
        return mUParameter_direction;
    }
}
