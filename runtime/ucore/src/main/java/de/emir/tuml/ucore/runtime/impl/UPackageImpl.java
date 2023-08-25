package de.emir.tuml.ucore.runtime.impl;

import java.util.List;

import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import de.emir.tuml.ucore.runtime.utils.DynamicUObject;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.lists.LazyReferenceContainmentList;
import de.emir.tuml.ucore.runtime.utils.internal.lists.LazyReferenceContainmentList.FeatureResolver;

/**
 * @generated
 */
@UMLImplementation(classifier = UPackage.class)
public class UPackageImpl extends UNamedElementImpl implements UPackage {
    /**
     * @generated
     */
    private List<UModelElement> mContent = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public UPackageImpl() {
        super();
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public UPackageImpl(final UPackage _copy) {
        super(_copy);
        mContent = _copy.getContent();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public UPackageImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name,
            List<UModelElement> _content) {
        super(_documentation, _annotations, _package, _name);
        mContent = _content;
    }

    public UPackageImpl(String name) {
        super(name);
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return RuntimePackage.Literals.UPackage;
    }

    //////////////////////////////////////////////////////////////////
    // Setter / Getter //
    //////////////////////////////////////////////////////////////////

    /**
     * @generated not
     */
    @Override
    public List<UModelElement> getContent() {
        if (mContent == null) {
            mContent = new UContainmentList<UModelElement>(this, RuntimePackage.theInstance.getUPackage_content());
            final UPackage p = this;
            this.registerListener(RuntimePackage.theInstance.getUPackage_content(),
                    new IValueChangeListener<UModelElement>() {
                        @Override
                        public void onValueChange(Notification<UModelElement> _notification) {
                            if (_notification.getNewValue() != null)
                                _notification.getNewValue().setPackage(p);
                        }
                    });
        }
        return mContent;
    }

    public List<UModelElement> getContent1() {
        if (mContent == null) {
            mContent = new LazyReferenceContainmentList<UModelElement>(this, new FeatureResolver() {
                @Override
                public UStructuralFeature getFeature() {
                    UClass cl = UCoreMetaRepository.findClass(UPackage.class);
                    if (cl != null)
                        return cl.getFeature("content");
                    return null;
                }
            });
        }
        return mContent;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UPackage createPackage(final String _name) {
        // first check if we allready got this package
        UPackage sub = getSubpackage(_name, true);
        if (sub != null)
            return sub;
        // if (isFrozen())
        // throw new UnsupportedOperationException("Package " + getName() + " is frozen");

        sub = new UPackageImpl(_name);
        sub.setPackage(this);
        getContent().add(sub);
        return sub;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public UInterface createInterface(final String name) {
        // TODO:
        //
        // * creates a new interface within this package
        // * @precondition isFrozen() == false
        //
        throw new UnsupportedOperationException("createInterface not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UClass createClass(final String _name) {
        return createClass(DynamicUObject.class, _name);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UPackage getSubpackage(final String _name, final boolean _ownedOnly) {
        for (UModelElement me : getContent()) {
            if (me instanceof UPackage && ((UPackage) me).getName().equals(_name))
                return (UPackage) me;
        }
        if (!_ownedOnly) {
            for (UModelElement me : getContent()) {
                if (me instanceof UPackage) {
                    UPackage sub = ((UPackage) me).getSubpackage(_name, _ownedOnly);
                    if (sub != null)
                        return sub;
                }
            }
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////
    // Operations //
    //////////////////////////////////////////////////////////////////
    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public UClassifier getClassifier(final String _name, final boolean _ownedOnly) {
        for (UModelElement me : getContent()) {
            if (me instanceof UClassifier && ((UClassifier) me).getName().equals(_name))
                return (UClassifier) me;
        }
        if (!_ownedOnly) {
            for (UModelElement me : getContent()) {
                if (me instanceof UPackage) {
                    UClassifier cl = ((UPackage) me).getClassifier(_name, _ownedOnly);
                    if (cl != null)
                        return cl;
                }
            }
        }
        return null;
    }

    /**
     * @inheritDoc
     * @generated
     */
    public UEnum createEnumeration(final String name) {
        // TODO:
        //
        // * creates a new enumeration in this package
        // * @precondition isFrozen() == false
        //
        throw new UnsupportedOperationException("createEnumeration not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated
     */
    public UClassifier getClassifier(final QualifiedName qn, final boolean ownedOnly) {
        // TODO:
        throw new UnsupportedOperationException("getClassifier not yet implemented");
    }

    /**
     * @inheritDoc
     * @generated
     */
    public void build() {
        // TODO:
        //
        // * initializes the model element, e.g. create private member for reflection access
        //
        throw new UnsupportedOperationException("build not yet implemented");
    }

    /**
     * @generated
     */
    @Override
    public String toString() {
        return "UPackageImpl{" + " documentation = " + getDocumentation() + " name = " + getName() + "}";
    }

    public UInterfaceImpl createInterface(Class<?> clazz) {
        return createInterface(clazz, clazz.getSimpleName());
    }

    public UEnumImpl createEnumeration(Class<?> clazz) {
        return createEnumeration(clazz, clazz.getSimpleName());
    }

    public UClassImpl createClass(Class<?> clazz) {
        return createClass(clazz, clazz.getSimpleName());
    }

    private UEnumImpl createEnumeration(Class<?> clazz, String simpleName) {
        if (isFrozen())
            throw new UnsupportedOperationException("Package " + getName() + " is frozen");

        UClassifier _cl = getClassifier(simpleName, true);
        if (_cl != null && _cl instanceof UEnumImpl)
            return (UEnumImpl) _cl;

        UEnumImpl cl = UEnumImpl.createEnumerationWithReflection(simpleName, clazz);
        cl.setPackage(this);
        getContent().add(cl);
        return cl;
    }

    private UInterfaceImpl createInterface(Class<?> clazz, String simpleName) {
        if (isFrozen())
            throw new UnsupportedOperationException("Package " + getName() + " is frozen");
        String name = simpleName;
        if (clazz != null && clazz.isAnnotationPresent(UMLInterface.class)) {
            UMLInterface anno = clazz.getAnnotation(UMLInterface.class);
            if (anno != null && anno.name() != null && anno.name().isEmpty() == false)
                name = anno.name();
        }
        UClassifier _cl = getClassifier(name, true);
        if (_cl != null && _cl instanceof UInterfaceImpl)
            return (UInterfaceImpl) _cl;

        UInterfaceImpl cl = UInterfaceImpl.createInterfaceWithReflection(this, clazz, name);
        getContent().add(cl);
        return cl;
    }

    private UClassImpl createClass(Class<?> clazz, String simpleName) {
        if (isFrozen())
            throw new UnsupportedOperationException("Package " + getName() + " is frozen");
        String name = simpleName;
        boolean isAbstract = false;
        if (clazz != null && clazz.isAnnotationPresent(UMLClass.class)) {
            UMLClass anno = clazz.getAnnotation(UMLClass.class);
            if (anno != null && anno.name() != null && anno.name().isEmpty() == false)
                name = anno.name();
            if (anno != null)
                isAbstract = anno.isAbstract();
        }
        UClassifier _cl = getClassifier(name, true);
        if (_cl != null && _cl instanceof UClassImpl)
            return (UClassImpl) _cl;

        UClassImpl cl = UClassImpl.createClassWithReflection(this, clazz, name);
        cl.setAbstract(isAbstract);
        getContent().add(cl);
        return cl;
    }

    @Override
    public void freeze() {
        for (UModelElement me : getContent()) {
            if (me != null)
                me.freeze();
        }
        super.freeze();
    }

}
