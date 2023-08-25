package de.emir.tuml.ucore.runtime.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.lists.LazyReferenceContainmentList;

/**
 * @generated
 */
@UMLImplementation(classifier = UModelElement.class)
abstract public class UModelElementImpl extends UObjectImpl implements UModelElement {
    /**
     * @generated
     */
    private String mDocumentation = "";
    /**
     * @generated
     */
    private List<UAnnotation> mAnnotations = null;
    /**
     * @generated
     */
    private UPackage mPackage = null;
    private boolean mFrozen = false;

    /**
     * Default constructor
     * 
     * @generated not
     */
    public UModelElementImpl() {
        super();
        // set the default values and assign them to this instance
        // setPackage(mPackage);
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UModelElementImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package) {
        mDocumentation = _documentation;
        mAnnotations = _annotations;
        mPackage = _package;
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UModelElementImpl(final UModelElement _copy) {
        mDocumentation = _copy.getDocumentation();
        mAnnotations = _copy.getAnnotations();
        mPackage = _copy.getPackage();
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UModelElement;
    }

    /**
     * @generated
     */
    public void setDocumentation(String _documentation) {
        if (needNotification(RuntimePackage.Literals.UModelElement_documentation)) {
            String _oldValue = mDocumentation;
            mDocumentation = _documentation;
            notify(_oldValue, mDocumentation, RuntimePackage.Literals.UModelElement_documentation,
                    NotificationType.SET);
        } else {
            mDocumentation = _documentation;
        }
    }

    /**
     * @generated
     */
    public String getDocumentation() {
        return mDocumentation;
    }

    /**
     * @generated not
     */
    public List<UAnnotation> getAnnotations() {
        if (mAnnotations == null) {
            mAnnotations = new ArrayList<>();
            // new LazyReferenceContainmentList<>(this, new LazyReferenceContainmentList.FeatureResolver() {
            // @Override
            // public UStructuralFeature getFeature() {
            // UClass cl = UCoreMetaRepository.findClass(UModelElement.class);
            // if (cl != null && cl instanceof UClassImpl){
            // if (((UClassImpl)cl).getStructuralFeatures() != null){
            // return cl.getFeature("annotations");
            // }
            // }
            // return null;
            // }
            // });
            // mAnnotations = new UContainmentList<UAnnotation>(this,
            // RuntimePackage.theInstance.getUModelElement_annotations());
        }
        return mAnnotations;
    }

    /**
     * @generated
     */
    public void setPackage(UPackage _package) {
        Notification<UPackage> notification = basicSet(mPackage, _package,
                RuntimePackage.Literals.UModelElement_package);
        mPackage = _package;
        if (notification != null) {
            dispatchNotification(notification);
        }
    }

    /**
     * @generated not
     */
    @Override
    public UPackage getPackage() {
        if (mPackage == null) {
            UObject c = getUContainer();
            if (c != null && c instanceof UPackage) {
                setPackage((UPackage) c);
            }
            // if (mPackage == null)
            // throw new NullPointerException();
        }
        return mPackage;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void freeze() {
        mFrozen = true;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UAnnotation createAnnotation(final String _name) {
        UAnnotation anno = getAnnotation(_name);
        if (anno != null)
            return anno;
        anno = new UAnnotationImpl(_name, null);
        getAnnotations().add(anno);
        return anno;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isFrozen() {
        return mFrozen;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UAnnotation getAnnotation(final String _name) {
        for (UAnnotation anno : getAnnotations())
            if (anno.getName().equals(_name))
                return anno;
        return null;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UAnnotationDetail getAnnotationDetail(final String _name, final String _key) {
        UAnnotation anno = getAnnotation(_name);
        if (anno != null)
            return anno.getDetail(_key);
        return null;
    }

    @Override
    public UAnnotationDetail createAnnotationDetail(String source, String key, String value) {
        UAnnotation anno = getAnnotation(source);
        if (anno == null)
            anno = createAnnotation(source);
        anno.addDetail(key, value);
        return anno.getDetail(key);
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UModelElementImpl{" + " documentation = " + getDocumentation() + "}";
    }
}
