package de.emir.tuml.ucore.runtime.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.emir.tuml.ucore.runtime.UAnnotationDetail;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;
import de.emir.tuml.ucore.runtime.utils.internal.ClassifierUtilities;
import de.emir.tuml.ucore.runtime.utils.internal.builder.PackageBuilder;
import de.emir.tuml.ucore.runtime.utils.internal.builder.UClassBuilder;

public class UCoreMetaRepository {

    private static final HashSet<UPackage> mRootPackages = new HashSet<UPackage>();

    private static final HashMap<String, UPackage> mPackageMap = new HashMap<String, UPackage>();
    private static final HashMap<String, UClassifier> mQualifiedNamesMap = new HashMap<String, UClassifier>();
    private static final HashMap<String, UClassifier> mSimpleNamesMap = new HashMap<String, UClassifier>();

    private static final HashMap<String, UClass> mAnnotationsWithDetailsMap = new HashMap<String, UClass>();

    private static ClassifierUtilities mClassifierUtilities = new ClassifierUtilities();

    public static UClassifier registerClassifier(String name, UClassifier cl) {
        if (mQualifiedNamesMap.containsKey(name) == false) {
            mQualifiedNamesMap.put(name, cl);
            QualifiedName qn = QualifiedNameImpl.createWithRegEx(name, "\\.");
            mSimpleNamesMap.put(qn.lastSegment(), cl);
            mClassifierUtilities.markDirty();
            return cl;
        } else {
            return mQualifiedNamesMap.get(name);
        }
    }

    public static UClass getUClass(Class<?> class1) {
        UClassifier cl = getClassifier(class1);
        if (cl instanceof UClass)
            return (UClass) cl;
        return null;
    }

    public static UEnum getUEnumeration(Class<?> clazz) {
        UClassifier cl = getClassifier(clazz);
        if (cl != null && cl instanceof UEnum)
            return (UEnum) cl;
        return null;
    }

    public static UInterface getUInterface(Class<?> clazz) {
        UClassifier cl = getClassifier(clazz);
        if (cl != null && cl instanceof UInterface)
            return (UInterface) cl;
        return null;
    }

    public static UClassifier getClassifier(Class<?> clazz) {
        if (clazz == null)
            return null;
        UClassifier cl = findClassifier(clazz);
        if (cl != null)
            return cl;

        cl = UClassBuilder.createNewClassifier(clazz);
        cl = registerClassifier(clazz.getName(), cl);
        return cl;
    }

    public static UPackage getPackage(String string) {
        return getPackage(QualifiedNameImpl.createWithRegEx(string, "\\."));
    }

    public static UPackage getPackage(QualifiedName qn) {
        String str = qn.toString();
        if (mPackageMap.containsKey(str))
            return mPackageMap.get(str);
        UPackage p = PackageBuilder.createPackage(qn);
        mPackageMap.put(str, p);
        return p;
    }

    public static UClassifier findClassifier(Class<?> clazz) {
        if (clazz == null)
            return null;
        String str = clazz.getName();
        if (mQualifiedNamesMap.containsKey(str))
            return mQualifiedNamesMap.get(str);
        return null;
    }

    public static UClass findClass(Class<?> clazz) {
        UClassifier cl = findClassifier(clazz);
        if (cl != null && cl instanceof UClass)
            return (UClass) cl;
        return null;
    }

    public static UClassifier findClassifierBySimpleName(String str) {
        if (mSimpleNamesMap.containsKey(str))
            return mSimpleNamesMap.get(str);
        if (mQualifiedNamesMap.containsKey(str)) // just to be sure, that it is not a qualified name
            return mQualifiedNamesMap.get(str);
        return null;
    }

    public static UClass findClassBySimpleName(String str) {
        UClassifier cl = findClassifierBySimpleName(str);
        if (cl != null && cl instanceof UClass)
            return (UClass) cl;
        return null;
    }

    public static UClass findClassByAnnotation(String name, String key, String value) {

        String mapKey = createAnnotationWithDetailMapKey(name, key, value);

        UClass result = mAnnotationsWithDetailsMap.get(mapKey);

        if (result != null) {
            return result;
        }

        List<UClassifier> classifier = getAllClassifier();

        for (UClassifier uClassifier : classifier) {
            if (uClassifier instanceof UClass == false) {
                continue;
            }

            UAnnotationDetail annotation = uClassifier.getAnnotationDetail(name, key);

            if (annotation == null) {
                continue;
            }

            if (annotation.getValue() != null && annotation.getValue().equals(value)) {

                result = (UClass) uClassifier;

                mAnnotationsWithDetailsMap.put(mapKey, result);

                return result;
            }

        }

        return null;

    }

    private static String createAnnotationWithDetailMapKey(String name, String key, String value) {
        return "@" + name + "(" + key + "=" + value + ")";

    }

    /**
     * returns all classifiers, that inherit from parent
     * 
     * @param parent
     *            the parent classifier
     * @param instantiableOnly
     *            if set to true, only those classifiers will be returned, that can be instantiated
     * @return
     */
    public static Collection<UClassifier> getClassesInheritFrom(UClassifier parent, boolean instantiableOnly) {
        return mClassifierUtilities.getClassesInheritFrom(parent, instantiableOnly);
    }

    /**
     * Returns an collection of all registered classifiers
     * 
     * @return a transient list of all classifiers
     */
    public static List<UClassifier> getAllClassifier() {
        HashSet<UClassifier> set = new HashSet<UClassifier>();

        ArrayList<UClassifier> out = new ArrayList<UClassifier>();
        for (UClassifier cl : mQualifiedNamesMap.values())
            if (cl != null)
                set.add(cl);
        out.addAll(set);
        return out;
    }

    public static UPackage findPackage(String string) {
        if (mPackageMap.containsKey(string))
            return mPackageMap.get(string);
        for (UPackage p : mPackageMap.values())
            if (p.getName().equals(string))
                return p;
        return null;
    }

}
