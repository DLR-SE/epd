package de.emir.tuml.ucore.runtime.utils;

import de.emir.tuml.ucore.runtime.IStructuralElement;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.impl.FeaturePointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.ObjectPointerImpl;
import de.emir.tuml.ucore.runtime.utils.impl.PointerChainImpl;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderExtImpl;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderImpl;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProvider;
import de.emir.tuml.ucore.runtime.utils.TypePointer;
import de.emir.tuml.ucore.runtime.utils.impl.TypePointerImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProviderExt;

/**
 * @generated
 */
public class UtilsPackage {

    /**
     * @generated
     */
    public static UtilsPackage theInstance = new UtilsPackage().init();

    /**
     * @generated
     */
    public interface Literals {
        /**
         * @generated
         * @return meta type for classifier QualifiedName
         */
        UClass QualifiedName = UtilsPackage.theInstance.getQualifiedName();
        /**
         * @generated
         * @return meta type for classifier QualifiedNameProvider
         */
        UClass QualifiedNameProvider = UtilsPackage.theInstance.getQualifiedNameProvider();
        /**
         * @generated
         * @return meta type for classifier QualifiedNameProviderExt
         */
        UClass QualifiedNameProviderExt = UtilsPackage.theInstance.getQualifiedNameProviderExt();
        /**
         * @generated
         * @return meta type for interface Pointer
         */
        UInterface Pointer = UtilsPackage.theInstance.getPointer();
        /**
         * @generated
         * @return meta type for classifier ObjectPointer
         */
        UClass ObjectPointer = UtilsPackage.theInstance.getObjectPointer();
        /**
         * @generated
         * @return meta type for classifier FeaturePointer
         */
        UClass FeaturePointer = UtilsPackage.theInstance.getFeaturePointer();
        /**
         * @generated
         * @return meta type for classifier PointerChain
         */
        UClass PointerChain = UtilsPackage.theInstance.getPointerChain();
        /**
         * @generated
         * @return meta type for classifier TypePointer
         */
        UClass TypePointer = UtilsPackage.theInstance.getTypePointer();

        /**
         * @generated
         * @return feature descriptor segments in type QualifiedName
         */
        UStructuralFeature QualifiedName_segments = UtilsPackage.theInstance.getQualifiedName_segments();
        /**
         * @generated
         * @return feature descriptor nameFeatures in type QualifiedNameProviderExt
         */
        UStructuralFeature QualifiedNameProviderExt_nameFeatures = UtilsPackage.theInstance
                .getQualifiedNameProviderExt_nameFeatures();
        /**
         * @generated
         * @return feature descriptor theInstance in type ObjectPointer
         */
        UStructuralFeature ObjectPointer_theInstance = UtilsPackage.theInstance.getObjectPointer_theInstance();
        /**
         * @generated
         * @return feature descriptor listIndex in type FeaturePointer
         */
        UStructuralFeature FeaturePointer_listIndex = UtilsPackage.theInstance.getFeaturePointer_listIndex();
        /**
         * @generated
         * @return feature descriptor theInstance in type FeaturePointer
         */
        UStructuralFeature FeaturePointer_theInstance = UtilsPackage.theInstance.getFeaturePointer_theInstance();
        /**
         * @generated
         * @return feature descriptor feature in type FeaturePointer
         */
        UStructuralFeature FeaturePointer_feature = UtilsPackage.theInstance.getFeaturePointer_feature();
        /**
         * @generated
         * @return feature descriptor parent in type PointerChain
         */
        UStructuralFeature PointerChain_parent = UtilsPackage.theInstance.getPointerChain_parent();
        /**
         * @generated
         * @return feature descriptor type in type TypePointer
         */
        UStructuralFeature TypePointer_type = UtilsPackage.theInstance.getTypePointer_type();
        /**
         * @generated
         * @return feature descriptor parent in type TypePointer
         */
        UStructuralFeature TypePointer_parent = UtilsPackage.theInstance.getTypePointer_parent();
        /**
         * @generated
         * @return feature descriptor listIndex in type TypePointer
         */
        UStructuralFeature TypePointer_listIndex = UtilsPackage.theInstance.getTypePointer_listIndex();

    }

    //////////////////////////////////////////////////////////////////////
    // Classifiers //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    private UClass mQualifiedName = null;
    /**
     * @generated
     */
    private UClass mQualifiedNameProvider = null;
    /**
     * @generated
     */
    private UClass mQualifiedNameProviderExt = null;
    /**
     * @generated
     */
    private UInterface mPointer = null;
    /**
     * @generated
     */
    private UClass mObjectPointer = null;
    /**
     * @generated
     */
    private UClass mFeaturePointer = null;
    /**
     * @generated
     */
    private UClass mPointerChain = null;
    /**
     * @generated
     */
    private UClass mTypePointer = null;

    //////////////////////////////////////////////////////////////////////
    // StructuralFeatures //
    //////////////////////////////////////////////////////////////////////

    // Features for classifier QualifiedName
    /**
     * @generated
     */
    private UStructuralFeature mQualifiedName_segments = null;

    // Features for classifier QualifiedNameProviderExt
    /**
     * @generated
     */
    private UStructuralFeature mQualifiedNameProviderExt_nameFeatures = null;

    // Features for classifier ObjectPointer
    /**
     * @generated
     */
    private UStructuralFeature mObjectPointer_theInstance = null;

    // Features for classifier FeaturePointer
    /**
     * @generated
     */
    private UStructuralFeature mFeaturePointer_listIndex = null;
    /**
     * @generated
     */
    private UStructuralFeature mFeaturePointer_theInstance = null;
    /**
     * @generated
     */
    private UStructuralFeature mFeaturePointer_feature = null;

    // Features for classifier PointerChain
    /**
     * @generated
     */
    private UStructuralFeature mPointerChain_parent = null;

    /**
     * @generated
     */
    public static UtilsPackage init() {
        if (theInstance != null)
            return theInstance;

        ULog.debug("initialize package UtilsPackage ...");
        theInstance = new UtilsPackage();

        theInstance.createClassifier();
        theInstance.createFeatures();
        theInstance.createOperations();
        theInstance.buildHierarchies();
        UPackage p = UCoreMetaRepository.getPackage("de.emir.tuml.ucore.runtime.utils");
        p.getContent().add(theInstance.mQualifiedName);
        p.getContent().add(theInstance.mQualifiedNameProvider);
        p.getContent().add(theInstance.mQualifiedNameProviderExt);
        p.getContent().add(theInstance.mPointer);
        p.getContent().add(theInstance.mObjectPointer);
        p.getContent().add(theInstance.mFeaturePointer);
        p.getContent().add(theInstance.mPointerChain);
        p.getContent().add(theInstance.mTypePointer);
        p.freeze();

        ULog.debug("... package UtilsPackage initialized");

        return theInstance;
    }

    /**
     * @generated
     */
    private UStructuralFeature mTypePointer_parent = null;
    /**
     * @generated
     */
    private UStructuralFeature mTypePointer_type = null;
    /**
     * @generated
     */
    private UStructuralFeature mTypePointer_listIndex = null;

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createClassifier() {
        mQualifiedName = UMetaBuilder.manual().createClass("QualifiedName", false, QualifiedName.class,
                QualifiedNameImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mQualifiedName, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new QualifiedNameImpl();
            }
        });

        mQualifiedNameProvider = UMetaBuilder.manual().createClass("QualifiedNameProvider", false,
                QualifiedNameProvider.class, QualifiedNameProviderImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mQualifiedNameProvider, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new QualifiedNameProviderImpl();
            }
        });
        mQualifiedNameProvider.setDocumentation(
                "\r\n  * creates a qualified name for an UObject\r\n  * therefore the QualifiedNameProvider checks the objects's class\r\n  * if it contains a name feature\r\n  ");

        mQualifiedNameProviderExt = UMetaBuilder.manual().createClass("QualifiedNameProviderExt", false,
                QualifiedNameProviderExt.class, QualifiedNameProviderExtImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mQualifiedNameProviderExt, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new QualifiedNameProviderExtImpl();
            }
        });
        mQualifiedNameProviderExt.setDocumentation(
                " extends the QualifiedNameProvider for the capability to use different \r\n  * features to get the name\r\n  ");

        mPointer = UMetaBuilder.manual().createInterface("Pointer", Pointer.class);
        mPointer.setDocumentation(
                "\r\n * A abstract pointer to an element into an object tree. \r\n * In contrast to pointer in C++ or other languages, this pointer is not pointing to a specific \r\n * object, but an position inside an object hierarchy, thus the underlying object may change, even if the \r\n * pointer remains the same. \r\n ");

        mObjectPointer = UMetaBuilder.manual().createClass("ObjectPointer", false, ObjectPointer.class,
                ObjectPointerImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mObjectPointer, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new ObjectPointerImpl();
            }
        });

        mFeaturePointer = UMetaBuilder.manual().createClass("FeaturePointer", false, FeaturePointer.class,
                FeaturePointerImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mFeaturePointer, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new FeaturePointerImpl();
            }
        });

        mPointerChain = UMetaBuilder.manual().createClass("PointerChain", false, PointerChain.class,
                PointerChainImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mPointerChain, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new PointerChainImpl();
            }
        });

        mTypePointer = UMetaBuilder.manual().createClass("TypePointer", false, TypePointer.class,
                TypePointerImpl.class);
        UMetaBuilder.manual().setInstanceCreator(mTypePointer, new IInstanceCreator() {
            @Override
            public UObject createNewInstance() {
                return new TypePointerImpl();
            }
        });
        mTypePointer.setDocumentation(
                "\r\n * The TypedPointer is dynamic pointer type, that search for the listIndex .th instance of a given type\r\n * inside an UObject (provided by the parent pointer). \r\n * Thereby the result may change between two calls to getValue(), if in the meantime a new instance has been added. \r\n * \r\n * To find the concrete value, the TypePointer iterates (within each call) through all IStructuralElements (first the features, \r\n * later the operations) and checks if the result type inherits the specified type. If that is the case and the value is not null\r\n * the index value is decremented until the index is <= 0. if the index is <= 0 the result is returned. \r\n * \r\n * With regard to list features (or operations) the TypePointer iterates through all instances of the list (always decrement the index if the type match)\r\n * before going to the next feature. \r\n ");

    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createFeatures() {
        {// create features
         // Features of QualifiedName
            mQualifiedName_segments = UMetaBuilder.manual().createFeature("segments",
                    TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mQualifiedName_segments, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((QualifiedName) instance).getSegments();
                }
            }, null);

            // Features of QualifiedNameProviderExt
            mQualifiedNameProviderExt_nameFeatures = UMetaBuilder.manual().createFeature("nameFeatures",
                    RuntimePackage.theInstance.getUStructuralFeature(), UAssociationType.COMPOSITION, 0, -1);
            UMetaBuilder.manual().setFeatureAccessor(mQualifiedNameProviderExt_nameFeatures, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((QualifiedNameProviderExt) instance).getNameFeatures();
                }
            }, null);
            mQualifiedNameProviderExt_nameFeatures.setDocumentation(
                    " the QualifiedNameProviderExt checks if the UObject contains one of the \r\n  * nameFeatures, and uses this to create the name\r\n  * @note if more than one feature is available, the first matching feature fires\r\n  ");

            // Features of ObjectPointer
            mObjectPointer_theInstance = UMetaBuilder.manual().createFeature("theInstance",
                    RuntimePackage.theInstance.getUObject(), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mObjectPointer_theInstance, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((ObjectPointer) instance).getTheInstance();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((ObjectPointer) instance).setTheInstance((UObject) value);
                }
            });

            // Features of FeaturePointer
            mFeaturePointer_theInstance = UMetaBuilder.manual().createFeature("theInstance",
                    RuntimePackage.theInstance.getUObject(), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mFeaturePointer_theInstance, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((FeaturePointer) instance).getTheInstance();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((FeaturePointer) instance).setTheInstance((UObject) value);
                }
            });
            mFeaturePointer_listIndex = UMetaBuilder.manual().createFeature("listIndex",
                    TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mFeaturePointer_listIndex, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((FeaturePointer) instance).getListIndex();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((FeaturePointer) instance).setListIndex((int) value);
                }
            });
            mFeaturePointer_feature = UMetaBuilder.manual().createFeature("feature",
                    RuntimePackage.theInstance.getIStructuralElement(), UAssociationType.AGGREGATION, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mFeaturePointer_feature, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((FeaturePointer) instance).getFeature();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((FeaturePointer) instance).setFeature((IStructuralElement) value);
                }
            });

            // Features of PointerChain
            mPointerChain_parent = UMetaBuilder.manual().createFeature("parent", UtilsPackage.theInstance.getPointer(),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mPointerChain_parent, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((PointerChain) instance).getParent();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((PointerChain) instance).setParent((Pointer) value);
                }
            });

            // Features of TypePointer
            mTypePointer_parent = UMetaBuilder.manual().createFeature("parent", UtilsPackage.theInstance.getPointer(),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mTypePointer_parent, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((TypePointer) instance).getParent();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((TypePointer) instance).setParent((Pointer) value);
                }
            });
            mTypePointer_type = UMetaBuilder.manual().createFeature("type", RuntimePackage.theInstance.getUType(),
                    UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mTypePointer_type, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((TypePointer) instance).getType();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((TypePointer) instance).setType((UType) value);
                }
            });
            mTypePointer_listIndex = UMetaBuilder.manual().createFeature("listIndex",
                    TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
            UMetaBuilder.manual().setFeatureAccessor(mTypePointer_listIndex, new IFeatureGetter() {
                @Override
                public Object get(UObject instance) {
                    return ((TypePointer) instance).getListIndex();
                }
            }, new IFeatureSetter() {
                @Override
                public void set(UObject instance, Object value) {
                    ((TypePointer) instance).setListIndex((int) value);
                }
            });

        }
        { // assign features
            mQualifiedName.getStructuralFeatures().add(mQualifiedName_segments);
            mQualifiedNameProviderExt.getStructuralFeatures().add(mQualifiedNameProviderExt_nameFeatures);
            mObjectPointer.getStructuralFeatures().add(mObjectPointer_theInstance);
            mFeaturePointer.getStructuralFeatures().add(mFeaturePointer_theInstance);
            mFeaturePointer.getStructuralFeatures().add(mFeaturePointer_listIndex);
            mFeaturePointer.getStructuralFeatures().add(mFeaturePointer_feature);
            mPointerChain.getStructuralFeatures().add(mPointerChain_parent);
            mTypePointer.getStructuralFeatures().add(mTypePointer_parent);
            mTypePointer.getStructuralFeatures().add(mTypePointer_type);
            mTypePointer.getStructuralFeatures().add(mTypePointer_listIndex);
        }

    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void createOperations() {
        { // Operations of QualifiedName
            UOperation operation = null;
            // operation : toString(String, String)
            operation = UMetaBuilder.manual().createOperation("toString", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).toString((String) parameter[0]);
                        }
                    });
            // Annotations for QualifiedName:toString(String, String)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "delimiter", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : numSegments(int)
            operation = UMetaBuilder.manual().createOperation("numSegments", false,
                    TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).numSegments();
                        }
                    });
            // Annotations for QualifiedName:numSegments(int)
            operation.createAnnotation("const");
            mQualifiedName.getOperations().add(operation);
            // operation : removeSegmentsFromEnd(QualifiedName, int)
            operation = UMetaBuilder.manual().createOperation("removeSegmentsFromEnd", false,
                    UtilsPackage.theInstance.getQualifiedName(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).removeSegmentsFromEnd((int) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "num", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : localRemoveSegmentsFromEnd(void, int)
            operation = UMetaBuilder.manual().createOperation("localRemoveSegmentsFromEnd", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((QualifiedName) instance).localRemoveSegmentsFromEnd((int) parameter[0]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "num", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : removeSegmentsFromStart(QualifiedName, int)
            operation = UMetaBuilder.manual().createOperation("removeSegmentsFromStart", false,
                    UtilsPackage.theInstance.getQualifiedName(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).removeSegmentsFromStart((int) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "num", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : localRemoveSegmentsFromStart(void, int)
            operation = UMetaBuilder.manual().createOperation("localRemoveSegmentsFromStart", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((QualifiedName) instance).localRemoveSegmentsFromStart((int) parameter[0]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "num", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : appendFront(QualifiedName, String)
            operation = UMetaBuilder.manual().createOperation("appendFront", false,
                    UtilsPackage.theInstance.getQualifiedName(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).appendFront((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "segment", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : localAppendFront(void, String)
            operation = UMetaBuilder.manual().createOperation("localAppendFront", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((QualifiedName) instance).localAppendFront((String) parameter[0]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "segment", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : appendBack(QualifiedName, String)
            operation = UMetaBuilder.manual().createOperation("appendBack", false,
                    UtilsPackage.theInstance.getQualifiedName(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).appendBack((String) parameter[0]);
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "segment", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : localAppendBack(void, String)
            operation = UMetaBuilder.manual().createOperation("localAppendBack", false,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((QualifiedName) instance).localAppendBack((String) parameter[0]);
                            return null;
                        }
                    });
            UMetaBuilder.manual().addParameter(operation, "segment", TypeUtils.getPrimitiveType(String.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : lastSegment(String)
            operation = UMetaBuilder.manual().createOperation("lastSegment", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).lastSegment();
                        }
                    });
            // Annotations for QualifiedName:lastSegment(String)
            operation.createAnnotation("const");
            mQualifiedName.getOperations().add(operation);
            // operation : firstSegment(String)
            operation = UMetaBuilder.manual().createOperation("firstSegment", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).firstSegment();
                        }
                    });
            // Annotations for QualifiedName:firstSegment(String)
            operation.createAnnotation("const");
            mQualifiedName.getOperations().add(operation);
            // operation : segment(String, int)
            operation = UMetaBuilder.manual().createOperation("segment", false,
                    TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).segment((int) parameter[0]);
                        }
                    });
            // Annotations for QualifiedName:segment(String, int)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedName.getOperations().add(operation);
            // operation : empty(boolean)
            operation = UMetaBuilder.manual().createOperation("empty", false, TypeUtils.getPrimitiveType(boolean.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).empty();
                        }
                    });
            // Annotations for QualifiedName:empty(boolean)
            operation.createAnnotation("const");
            mQualifiedName.getOperations().add(operation);
            // operation : isEmpty(boolean)
            operation = UMetaBuilder.manual().createOperation("isEmpty", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedName) instance).isEmpty();
                        }
                    });
            // Annotations for QualifiedName:isEmpty(boolean)
            operation.createAnnotation("const");
            mQualifiedName.getOperations().add(operation);
        }
        { // Operations of QualifiedNameProvider
            UOperation operation = null;
            // operation : get(QualifiedName, UObject)
            operation = UMetaBuilder.manual().createOperation("get", false, UtilsPackage.theInstance.getQualifiedName(),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedNameProvider) instance).get((UObject) parameter[0]);
                        }
                    });
            operation.setDocumentation(
                    " creates a qualified name for the given object, by appending\r\n  * the value of the \"name\" feature of the object itself and all of its parents\r\n  ");
            // Annotations for QualifiedNameProvider:get(QualifiedName, UObject)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "obj", RuntimePackage.theInstance.getUObject(), 0, 1,
                    UDirectionType.IN);
            mQualifiedNameProvider.getOperations().add(operation);
            // operation : get(QualifiedName, UObject, int)
            operation = UMetaBuilder.manual().createOperation("get", false, UtilsPackage.theInstance.getQualifiedName(),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((QualifiedNameProvider) instance).get((UObject) parameter[0], (int) parameter[1]);
                        }
                    });
            operation.setDocumentation(
                    " \r\n  * creates a qualified name for the given object, that has a limited length\r\n  ");
            // Annotations for QualifiedNameProvider:get(QualifiedName, UObject, int)
            operation.createAnnotation("const");
            UMetaBuilder.manual().addParameter(operation, "obj", RuntimePackage.theInstance.getUObject(), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "depth", TypeUtils.getPrimitiveType(int.class), 0, 1,
                    UDirectionType.IN);
            mQualifiedNameProvider.getOperations().add(operation);
        }
        { // Operations of Pointer
            UOperation operation = null;
            // operation : getValue(Object)
            operation = UMetaBuilder.manual().createOperation("getValue", false,
                    TypeUtils.getPrimitiveType(Object.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getValue();
                        }
                    });
            operation.setDocumentation(" @returns the object, this pointer currently points on or null if not valid ");
            // Annotations for Pointer:getValue(Object)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : setValue(void, Object)
            operation = UMetaBuilder.manual().createOperation("setValue", false, TypeUtils.getPrimitiveType(void.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((Pointer) instance).setValue((Object) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    " sets the value of the pointed instance\r\n * the setValue behavior depends on the kind of pointer\r\n * - if the pointer is a FeaturePointer: \r\n * -- if the feature is a list and the listIndex is invalid (<0) the value (if not null) is added to the list\r\n * -- if the feature is a list and the listIndex is valid, the value is set to the corresponding index in the list\r\n * -- if the feature is not a list and the value is not null, the feature value will be set (corresponds to eSet)\r\n * -- if the feature is not a list and the value is null, eUnSet is called. This not necessarily results in setting the value to null, but to its default value\r\n * - if the pointer is a ObjectPointer, the instance is changed\r\n ");
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            mPointer.getOperations().add(operation);
            // operation : assign(void, Object, boolean)
            operation = UMetaBuilder.manual().createOperation("assign", false, TypeUtils.getPrimitiveType(void.class),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((Pointer) instance).assign((Object) parameter[0], (boolean) parameter[1]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    " \r\n * Assign differs from setValue, in that case that the directly pointed instance is not changed, but \r\n * all feature values from incoming value will be copied to the pointed instance. \r\n * Features that are not part of the pointed instance, but can be found in the value, will be ignored \r\n * Therefore this method may be used, if the incoming value is a superclass of the pointed instance\r\n * (e.g. pointer(Type: PhysicalObject)->assign(Type: Vessel))\r\n * @param copyContainments if set to true, the value of a containment feature is copied otherwise it is simply set. \r\n ");
            UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(Object.class), 0, 1,
                    UDirectionType.IN);
            UMetaBuilder.manual().addParameter(operation, "copyContainments", TypeUtils.getPrimitiveType(boolean.class),
                    0, 1, UDirectionType.IN);
            mPointer.getOperations().add(operation);
            // operation : isValid(boolean)
            operation = UMetaBuilder.manual().createOperation("isValid", false,
                    TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).isValid();
                        }
                    });
            operation.setDocumentation(" @returns true if the pointer is currently pointing to an existing object ");
            // Annotations for Pointer:isValid(boolean)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : getType(UType)
            operation = UMetaBuilder.manual().createOperation("getType", false, RuntimePackage.theInstance.getUType(),
                    0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getType();
                        }
                    });
            operation.setDocumentation(
                    " returns the type of the object, this pointer currently points on or null if not valid ");
            // Annotations for Pointer:getType(UType)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : getExpectedType(UType)
            operation = UMetaBuilder.manual().createOperation("getExpectedType", false,
                    RuntimePackage.theInstance.getUType(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getExpectedType();
                        }
                    });
            operation.setDocumentation(
                    " \r\n * returns the expected type of the pointed object. This type may differ from <code>getType</code> in \r\n * terms that it may is more abstract.\r\n ");
            // Annotations for Pointer:getExpectedType(UType)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : getUObject(UObject)
            operation = UMetaBuilder.manual().createOperation("getUObject", false,
                    RuntimePackage.theInstance.getUObject(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getUObject();
                        }
                    });
            operation.setDocumentation(
                    " convinience method, that checks the <code>getValue</code> method, if it returns an UObject ");
            // Annotations for Pointer:getUObject(UObject)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : getPointedContainer(UObject)
            operation = UMetaBuilder.manual().createOperation("getPointedContainer", false,
                    RuntimePackage.theInstance.getUObject(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getPointedContainer();
                        }
                    });
            operation.setDocumentation(" returns the container of the pointed object ");
            // Annotations for Pointer:getPointedContainer(UObject)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : getPointedFeature(UStructuralFeature)
            operation = UMetaBuilder.manual().createOperation("getPointedFeature", false,
                    RuntimePackage.theInstance.getUStructuralFeature(), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).getPointedFeature();
                        }
                    });
            operation.setDocumentation(" returns the feature that is used to access the pointed value ");
            // Annotations for Pointer:getPointedFeature(UStructuralFeature)
            operation.createAnnotation("const");
            mPointer.getOperations().add(operation);
            // operation : changeAnchor(void, UObject)
            operation = UMetaBuilder.manual().createOperation("changeAnchor", true,
                    TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            ((Pointer) instance).changeAnchor((UObject) parameter[0]);
                            return null;
                        }
                    });
            operation.setDocumentation(
                    "\r\n * Changes the anchor of the pointer, e.g. the one and only fixed position within this pointer. <br> \r\n *- In case of a ObjectPointer, the instance is changed\r\n * - In case of an FeaturePointer the instance is changed, but feature and list index remain the same\r\n * - In case of an PointerChain the very first parent pointer is changed, that is not an PointerChain. \r\n * \r\n * Use this method to realocate a pointer to another tree with the same structure as the old tree  \r\n ");
            UMetaBuilder.manual().addParameter(operation, "root", RuntimePackage.theInstance.getUObject(), 0, 1,
                    UDirectionType.IN);
            mPointer.getOperations().add(operation);
            // operation : copy(Pointer)
            operation = UMetaBuilder.manual().createOperation("copy", true, UtilsPackage.theInstance.getPointer(), 0, 1,
                    new IOperationInvoker() {
                        @Override
                        public Object invoke(UObject instance, Object... parameter) {
                            return ((Pointer) instance).copy();
                        }
                    });
            operation.setDocumentation(" creates a copy of this pointer that points to the same instance ");
            mPointer.getOperations().add(operation);
        }
    }

    /**
     * create all required classifiers
     * 
     * @generated
     **/
    private void buildHierarchies() {
        mQualifiedNameProviderExt.setSuperType(UtilsPackage.theInstance.getQualifiedNameProvider());
        mObjectPointer.getInterfaces().add(UtilsPackage.theInstance.getPointer());
        mFeaturePointer.getInterfaces().add(UtilsPackage.theInstance.getPointer());
        mPointerChain.setSuperType(UtilsPackage.theInstance.getFeaturePointer());
        mTypePointer.getInterfaces().add(UtilsPackage.theInstance.getPointer());

    }

    //////////////////////////////////////////////////////////////////////
    // Classifier GETTER //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    public UClass getQualifiedName() {
        if (mQualifiedName == null) {
            mQualifiedName = UCoreMetaRepository.getUClass(QualifiedName.class);
        }
        return mQualifiedName;
    }

    /**
     * @generated
     */
    public UClass getQualifiedNameProvider() {
        if (mQualifiedNameProvider == null) {
            mQualifiedNameProvider = UCoreMetaRepository.getUClass(QualifiedNameProvider.class);
        }
        return mQualifiedNameProvider;
    }

    /**
     * @generated
     */
    public UClass getQualifiedNameProviderExt() {
        if (mQualifiedNameProviderExt == null) {
            mQualifiedNameProviderExt = UCoreMetaRepository.getUClass(QualifiedNameProviderExt.class);
        }
        return mQualifiedNameProviderExt;
    }

    /**
     * @generated
     */
    public UInterface getPointer() {
        if (mPointer == null) {
            mPointer = UCoreMetaRepository.getUInterface(Pointer.class);
        }
        return mPointer;
    }

    /**
     * @generated
     */
    public UClass getObjectPointer() {
        if (mObjectPointer == null) {
            mObjectPointer = UCoreMetaRepository.getUClass(ObjectPointer.class);
        }
        return mObjectPointer;
    }

    /**
     * @generated
     */
    public UClass getFeaturePointer() {
        if (mFeaturePointer == null) {
            mFeaturePointer = UCoreMetaRepository.getUClass(FeaturePointer.class);
        }
        return mFeaturePointer;
    }

    /**
     * @generated
     */
    public UClass getPointerChain() {
        if (mPointerChain == null) {
            mPointerChain = UCoreMetaRepository.getUClass(PointerChain.class);
        }
        return mPointerChain;
    }

    //////////////////////////////////////////////////////////////////////
    // StructuralFeatures GETTER //
    //////////////////////////////////////////////////////////////////////

    /**
     * @generated
     */
    public UStructuralFeature getQualifiedName_segments() {
        if (mQualifiedName_segments == null)
            mQualifiedName_segments = getQualifiedName().getFeature("segments");
        return mQualifiedName_segments;
    }

    /**
     * @generated
     */
    public UStructuralFeature getQualifiedNameProviderExt_nameFeatures() {
        if (mQualifiedNameProviderExt_nameFeatures == null)
            mQualifiedNameProviderExt_nameFeatures = getQualifiedNameProviderExt().getFeature("nameFeatures");
        return mQualifiedNameProviderExt_nameFeatures;
    }

    /**
     * @generated
     */
    public UClass getTypePointer() {
        if (mTypePointer == null) {
            mTypePointer = UCoreMetaRepository.getUClass(TypePointer.class);
        }
        return mTypePointer;
    }

    /**
     * @generated
     */
    public UStructuralFeature getObjectPointer_theInstance() {
        if (mObjectPointer_theInstance == null)
            mObjectPointer_theInstance = getObjectPointer().getFeature("theInstance");
        return mObjectPointer_theInstance;
    }

    /**
     * @generated
     */
    public UStructuralFeature getFeaturePointer_listIndex() {
        if (mFeaturePointer_listIndex == null)
            mFeaturePointer_listIndex = getFeaturePointer().getFeature("listIndex");
        return mFeaturePointer_listIndex;
    }

    /**
     * @generated
     */
    public UStructuralFeature getFeaturePointer_theInstance() {
        if (mFeaturePointer_theInstance == null)
            mFeaturePointer_theInstance = getFeaturePointer().getFeature("theInstance");
        return mFeaturePointer_theInstance;
    }

    /**
     * @generated
     */
    public UStructuralFeature getFeaturePointer_feature() {
        if (mFeaturePointer_feature == null)
            mFeaturePointer_feature = getFeaturePointer().getFeature("feature");
        return mFeaturePointer_feature;
    }

    /**
     * @generated
     */
    public UStructuralFeature getPointerChain_parent() {
        if (mPointerChain_parent == null)
            mPointerChain_parent = getPointerChain().getFeature("parent");
        return mPointerChain_parent;
    }

    /**
     * @generated
     */
    public UStructuralFeature getTypePointer_type() {
        if (mTypePointer_type == null)
            mTypePointer_type = getTypePointer().getFeature("type");
        return mTypePointer_type;
    }

    /**
     * @generated
     */
    public UStructuralFeature getTypePointer_listIndex() {
        if (mTypePointer_listIndex == null)
            mTypePointer_listIndex = getTypePointer().getFeature("listIndex");
        return mTypePointer_listIndex;
    }

    /**
     * @generated
     */
    public UStructuralFeature getTypePointer_parent() {
        if (mTypePointer_parent == null)
            mTypePointer_parent = getTypePointer().getFeature("parent");
        return mTypePointer_parent;
    }
}
