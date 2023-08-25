package de.emir.tuml.ucore.runtime.utils.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameProviderImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.QualifiedNameProviderExt;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * extends the QualifiedNameProvider for the capability to use different features to get the name
 * 
 * @generated
 */
@UMLImplementation(classifier = QualifiedNameProviderExt.class)
public class QualifiedNameProviderExtImpl extends QualifiedNameProviderImpl implements QualifiedNameProviderExt {
    /**
     * the QualifiedNameProviderExt checks if the UObject contains one of the nameFeatures, and uses this to create the
     * name
     * 
     * @note if more than one feature is available, the first matching feature fires
     * @generated
     */
    private List<UStructuralFeature> mNameFeatures = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public QualifiedNameProviderExtImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public QualifiedNameProviderExtImpl(final QualifiedNameProviderExt _copy) {
        super(_copy);
        mNameFeatures = _copy.getNameFeatures();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public QualifiedNameProviderExtImpl(List<UStructuralFeature> _nameFeatures) {
        super();
        mNameFeatures = _nameFeatures;
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.QualifiedNameProviderExt;
    }

    /**
     * the QualifiedNameProviderExt checks if the UObject contains one of the nameFeatures, and uses this to create the
     * name
     * 
     * @note if more than one feature is available, the first matching feature fires
     * @generated
     */
    public List<UStructuralFeature> getNameFeatures() {
        if (mNameFeatures == null) {
            mNameFeatures = new UContainmentList<UStructuralFeature>(this,
                    UtilsPackage.theInstance.getQualifiedNameProviderExt_nameFeatures());
        }
        return mNameFeatures;
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "QualifiedNameProviderExtImpl{" + "}";
    }

    Pointer findNamePointer(UObject obj, UClassifier cl) {
        ArrayList<UStructuralFeature> featureList = new ArrayList<UStructuralFeature>();
        UStructuralFeature feature = null;// cl.getFeature("name");

        for (UStructuralFeature f : mNameFeatures) {
            if (cl.getFeature(f.getName()) == f) {
                feature = f;
                break;
            }
        }
        if (feature == null)
            return null;
        Pointer ptr = PointerOperations.create(obj, feature);
        if (feature.getType().getRepresentingClass() == String.class) {
            return ptr;
        }
        Object child = feature.get(obj);
        if (child != null && child instanceof UObject) {
            UObject uchild = (UObject) child;
            Pointer child_ptr = findNamePointer(uchild, uchild.getUClassifier());
            if (child_ptr != null)
                return PointerOperations.create(ptr, child_ptr);
        }
        return ptr;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName get(final UObject _obj, final int _depth) {
        if (_obj == null || _depth == 0)
            return null;
        UClass cl = _obj.getUClassifier();

        Pointer ptr = findNamePointer(_obj, cl);
        String name = null;

        if (ptr != null) {
            Object v = ptr.getValue();
            if (v != null)
                name = v.toString();
        }

        QualifiedName parent = null;
        if (_obj.getUContainer() != null) {
            parent = get(_obj.getUContainer(), _depth - 1);
        }
        if (parent != null)
            if (name != null)
                return parent.appendBack(name);
            else
                return parent;
        else if (name == null)
            return null;
        return new QualifiedNameImpl(name);
    }
}
