package de.emir.tuml.ucore.runtime.utils.internal.builder;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.access.impl.ReflectiveInstanceCreator;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;
import de.emir.tuml.ucore.runtime.impl.UClassImpl;
import de.emir.tuml.ucore.runtime.impl.UClassifierImpl;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;

public class UClassBuilder {

    /**
     * Creates a new Classifier, within its package. if the package does not exits yet, it will be created
     * 
     * @param clazz
     * @return
     */
    public static UClassifier createNewClassifier(Class<?> clazz) {
        QualifiedName qn = QualifiedNameImpl.createWithRegEx(clazz.getName(), "\\.");
        UPackage parent_package = UCoreMetaRepository.getPackage(qn.removeSegmentsFromEnd(1));
        if (parent_package == null)
            return null;
        return createClassifier(parent_package, clazz);
    }

    private static UClassifier createClassifier(UPackage _parent, Class<?> clazz) {

        if (_parent instanceof UPackageImpl == false)
            return null;
        UPackageImpl parent = (UPackageImpl) _parent;

        if (clazz.isAnnotationPresent(UMLInterface.class)) {
            return parent.createInterface(clazz);
        } else if (clazz.isAnnotationPresent(UMLClass.class))
            return parent.createClass(clazz);
        else if (clazz.isEnum())
            return parent.createEnumeration(clazz);
        else if (clazz.isAnnotationPresent(UMLImplementation.class)) {
            UMLImplementation anno = clazz.getAnnotation(UMLImplementation.class);
            Class<?> _cl = anno.classifier();
            UClassifier cl = UCoreMetaRepository.getClassifier(_cl);
            if (cl instanceof UClassImpl)
                ((UClassImpl) cl).setInstanceCreator(new ReflectiveInstanceCreator(clazz));
            return cl;
        }
        return null;
    }

    public static void discoverHierarchie(UClassifierImpl cl) {
        Class<?> clazz = cl.getRepresentingClass();
        for (Class<?> inter : clazz.getInterfaces()) {
            UClassifier parent = UCoreMetaRepository.getClassifier(inter);
            cl.addParent(parent);
        }
        if (cl instanceof UClassImpl && clazz.isAnnotationPresent(UMLClass.class)) {
            UMLClass anno = clazz.getAnnotation(UMLClass.class);
            if (anno.parent() != null) {
                UClassifier st = UCoreMetaRepository.getClassifier(anno.parent());
                ((UClassImpl) cl).setSuperType(st);
            }
        }

    }
    //
    // private static UClassifier createUInterface(Class<?> clazz) {
    // UInterface cl = new UInterfaceImpl(clazz);
    // cl.setName(getClassifierName(clazz));
    // return cl;
    // }
    //
    // private static UClass createUClass(Class<?> clazz) {
    // UClassImpl cl = new UClassImpl(clazz);
    // return cl;
    // }
    //
    //
    // public static String getClassifierName(Class<?> clazz){
    // String name = clazz.getSimpleName();
    // if (clazz.isAnnotationPresent(UMLClass.class)){
    // UMLClass anno = clazz.getAnnotation(UMLClass.class);
    // if (anno.name() != null && anno.name().isEmpty() == false)
    // name = anno.name();
    // }else if (clazz.isAnnotationPresent(UMLInterface.class)){
    // UMLInterface anno = clazz.getAnnotation(UMLInterface.class);
    // if (anno.name() != null && anno.name().isEmpty() == false)
    // name = anno.name();
    // }
    // return name;
    // }
    //
    // public static boolean isAbstract(Class<?> clazz) {
    // if (clazz.isAnnotationPresent(UMLClass.class)){
    // UMLClass anno = clazz.getAnnotation(UMLClass.class);
    // return anno.isAbstract();
    // }
    // return false;
    // }
    ////
    ////
    ////
    //// public static PropertyDescriptor getPropertyDescriptior(Class<?> clazz, String name) {
    //// BeanInfo beanInfo;
    //// try {
    //// beanInfo = Introspector.getBeanInfo(clazz);
    //// PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
    //// for (PropertyDescriptor d : descs){
    //// if (d.getName().equals(name))
    //// return d;
    //// }
    //// for (PropertyDescriptor d : descs){
    //// if (d.getName().toLowerCase().equals(name.toLowerCase()))
    //// return d;
    //// }
    //// } catch (IntrospectionException e) {
    //// e.printStackTrace();
    //// }
    //// return null;
    //// }
    ////
    ////
    ////
    //// public static void getProperties(Class<?> clazz){
    //// BeanInfo beanInfo;
    //// try {
    //// beanInfo = Introspector.getBeanInfo(clazz);
    //// PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
    ////
    //// } catch (IntrospectionException e) {
    //// e.printStackTrace();
    //// }
    ////
    //// }
    ////
    ////
    ////
    //// static UClassifier createClassifier(Class<?> clazz, HashMap<String, UClassifier> mclassifiermap) {
    //// UClassifier cl = null;
    //// if (clazz.isAnnotationPresent(UMLInterface.class)){
    //// cl = createUInterface(clazz, mclassifiermap);
    //// }else if (clazz.isAnnotationPresent(UMLClass.class))
    //// cl = createUClass(clazz, mclassifiermap);
    //// else if (clazz.isAnnotationPresent(UMLImplementation.class)){
    //// UMLImplementation anno = clazz.getAnnotation(UMLImplementation.class);
    //// Class<?> _cl = anno.classifier();
    //// if (cl == null && _cl != clazz)
    //// cl = createClassifier(_cl, mclassifiermap);
    //// }
    //// if (cl != null && cl.getPackage() == null && cl instanceof UClassifierImpl){
    //// UPackage p = UCoreMetaRepository.getPackage(clazz.getPackage().getName());
    //// p.getContent().add(cl);
    ////// ((UClassifierImpl)cl).setPackage(p);
    //// }
    //// return cl;
    //// }
    ////
    //// private static UClassifier createUInterface(Class<?> clazz, HashMap<String, UClassifier> mclassifiermap) {
    //// UMLInterface anno = clazz.getAnnotation(UMLInterface.class);
    //// String name = anno.name();
    //// if (name == null || name.isEmpty())
    //// name = clazz.getSimpleName();
    //// UInterface yi = new UInterfaceImpl();
    //// yi.setName(name);
    //// mclassifiermap.put(getQualifiedName(clazz).toString("."), yi);
    ////
    //// createInterfaceHierarchie(clazz, yi);
    //// return yi;
    //// }
    ////
    ////
    ////
    ////
    ////
    //// private static UClassifier createUClass(Class<?> clazz, HashMap<String, UClassifier> mclassifiermap) {
    //// UMLClass anno = clazz.getAnnotation(UMLClass.class);
    //// String name = anno.name();
    //// if (name == null || name.isEmpty())
    //// name = clazz.getSimpleName();
    //// UClass cl = new UClassImpl();
    //// cl.setName(name);
    //// mclassifiermap.put(getQualifiedName(clazz).toString("."), cl);
    ////
    //// createInterfaceHierarchie(clazz, cl);
    //// if (anno.parent() != null){
    //// UClassifier parentClassifier = UCoreMetaRepository.getClassifier(anno.parent());
    //// if (parentClassifier != null && parentClassifier instanceof UClass){
    //// cl.setSuperType((UClass)parentClassifier);
    //// }
    //// }
    ////
    //// collectFeatures(clazz, cl);
    //// return cl;
    //// }
    ////
    ////
    //// private static void createInterfaceHierarchie(Class<?> clazz, UClassifier yi) {
    //// for (Class<?> in : clazz.getInterfaces()){
    //// UClassifier ycl = UCoreMetaRepository.getClassifier(in);
    //// if (ycl != null && ycl instanceof UInterface){
    //// yi.getInterfaces().add((UInterface) ycl);
    //// }
    //// }
    //// }
    //// private static QualifiedName getQualifiedName(Class<?> clazz) {
    //// return QualifiedNameImpl.createWithRegEx(clazz.getName(), "\\.");
    //// }
    ////
    ////
    //// private static void collectFeatures(Class<?> clazz, UClass cl) {
    //// try {
    //// BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
    //// PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
    //// for (PropertyDescriptor desc : descs){
    //// UStructuralFeature feature = null;
    //// if (desc.getReadMethod() == null || desc.getWriteMethod() == null)
    //// continue; //if we can not read the value, we don't need it
    //// feature = createFeature(desc);
    //// System.out.println(feature);
    //// if (feature != null)
    //// cl.getStructuralFeatures().add(feature);
    //// }
    //// } catch (IntrospectionException e) {
    //// e.printStackTrace();
    //// }
    //// }
    ////
    //// private static UStructuralFeature createFeature(PropertyDescriptor desc) {
    //// UMLProperty anno = null;
    //// if (desc.getReadMethod().isAnnotationPresent(UMLProperty.class))
    //// anno = desc.getReadMethod().getAnnotation(UMLProperty.class);
    //// if (anno == null && desc.getWriteMethod().isAnnotationPresent(UMLProperty.class))
    //// anno = desc.getWriteMethod().getAnnotation(UMLProperty.class);
    //// if (anno == null)
    //// return null;
    //// String name = anno.name();
    //// if (name == null || name.isEmpty())
    //// name = desc.getName();
    ////
    //// UStructuralFeature feature = getFeatureType(desc, anno);
    //// if (feature == null)
    //// return null;
    //// feature.setName(name);
    ////
    ////
    //// return feature;
    //// }
    ////
    //// private static UStructuralFeature getFeatureType(PropertyDescriptor desc, UMLProperty anno) {
    //// Method getter = desc.getReadMethod();
    //// int upper = anno.upperBound();
    //// int lower = anno.lowerBound();
    ////
    //// Class<?> typeClass = getter.getReturnType();
    //// Type type = typeClass;
    //// if (isMany(typeClass)){
    //// Type grt = getter.getGenericReturnType();
    //// if (grt instanceof ParameterizedType){
    //// ParameterizedType pt = (ParameterizedType)grt;
    //// type = pt.getActualTypeArguments()[0]; //assumes that we use lists only, no maps
    //// if (upper == 1)
    //// upper = -1; //code for infinity
    //// }
    //// }
    ////
    ////
    //// UStructuralFeature feature = new UStructuralFeatureImpl();
    ////
    //// if (ReflectionUtil.isPrimitive(type)){
    //// feature.setType(ReflectionUtil.getPrimitiveType(type));
    //// }else{
    //// feature.setType(UCoreMetaRepository.getClassifier((Class<?>) type));
    //// //TODO: read from annotations
    //// switch(anno.associationType()){
    //// case SHARED :
    //// feature.setAggregation(UAssociationType.AGGREGATION);
    //// break;
    //// case COMPOSITE :
    //// feature.setAggregation(UAssociationType.COMPOSITION);
    //// break;
    //// case NONE :
    //// feature.setAggregation(UAssociationType.ASSOCIATION);
    //// break;
    //// case PROPERTY :
    //// feature.setAggregation(UAssociationType.PROPERTY);
    //// break;
    //// }
    //// }
    //// feature.setMultiplicity(new UMultiplicityImpl(lower, upper));
    //// return feature;
    //// }
    ////
    //// private static boolean isMany(Class<?> type) {
    //// if (type instanceof Class && ReflectionUtil.inherits(type, List.class)){
    //// return true;
    //// }
    //// return false;
    //// }
    ////
    // public static UAssociationType getAggregation(AssociationType type) {
    // switch(type){
    // case SHARED :
    // return UAssociationType.AGGREGATION;
    // case COMPOSITE :
    // return UAssociationType.COMPOSITION;
    // case NONE :
    // return UAssociationType.ASSOCIATION;
    // case PROPERTY :
    // return UAssociationType.PROPERTY;
    // }
    // return null;
    // }
    //
    // public static UMLProperty getPropertyAnnotation(PropertyDescriptor pd) {
    // if (pd.getReadMethod() != null && pd.getReadMethod().isAnnotationPresent(UMLProperty.class))
    // return pd.getReadMethod().getAnnotation(UMLProperty.class);
    // if (pd.getWriteMethod() != null && pd.getWriteMethod().isAnnotationPresent(UMLProperty.class))
    // return pd.getWriteMethod().getAnnotation(UMLProperty.class);
    // return null;
    // }
    ////
    //// public static UMultiplicity getMultiplicity(PropertyDescriptor pd) {
    //// int lower = 0;
    //// int upper = 1;
    //// UMLProperty anno = getPropertyAnnotation(pd);
    //// if (anno != null) {
    //// lower = anno.lowerBound();
    //// upper = anno.upperBound();
    //// }
    //// return new UMultiplicityImpl(lower, upper);
    //// }
    ////
    // public static UType getPropertyType(Type typeClass) {
    // Type type = typeClass;
    // if (typeClass instanceof ParameterizedType){
    // Type grt = type;//pd.getReadMethod().getGenericReturnType();
    // if (grt instanceof ParameterizedType){
    // ParameterizedType pt = (ParameterizedType)grt;
    // type = pt.getActualTypeArguments()[0]; //assumes that we use lists only, no maps
    // }
    // }
    // if (TypeUtils.isPrimitive(type))
    // return TypeUtils.getPrimitiveType(type);
    // return UCoreMetaRepository.getClassifier((Class<?>) type);
    // }
    //
    // public static List<UStructuralFeature> discoverStructuralFeatures(UClass clazz) {
    // ArrayList<UStructuralFeature> out = new ArrayList<UStructuralFeature>();
    // try {
    // BeanInfo beanInfo = Introspector.getBeanInfo(clazz.getRepresentedClass());
    // PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
    // for (PropertyDescriptor desc : descs){
    // UMLProperty anno = getPropertyAnnotation(desc);
    // if (anno == null)
    // continue;
    // UStructuralFeature feature = new UStructuralFeatureImpl(desc);
    // feature.setName(anno.name());
    // out.add(feature);
    // }
    // } catch (IntrospectionException e) {
    // e.printStackTrace();
    // }
    // return out;
    // }
    //
    // public static UStructuralFeature discoverStructuralFeatures(UType type, String featureName) {
    // Class<?> type_j_class = type.getRepresentedClass();
    // if (type_j_class == null)
    // return null;
    // BeanInfo beanInfo = getBeanInfo(type_j_class);
    // PropertyDescriptor desc = getPropertyDescriptor(beanInfo, featureName);
    // if (desc == null)
    // return null;
    // return buildStructuralFeature(desc, type);
    // }
    //
    // public static UStructuralFeature buildStructuralFeature(final PropertyDescriptor desc, UType parentType) {
    // if (desc.getReadMethod() == null)
    // return null; //we can not use it if we can not read the value
    // UMLProperty anno = getPropertyAnnotation(desc);
    // String name = (anno != null && anno.name() != null && anno.name().isEmpty()==false) ? anno.name() :
    // desc.getName();
    //
    // Invokable getter = Invokable.from(desc.getReadMethod());
    // Invokable setter = desc.getWriteMethod() != null ? Invokable.from(desc.getWriteMethod()) : null;
    //
    // Type rawType = desc.getReadMethod().getGenericReturnType();
    // UType type = getPropertyType(rawType);
    //
    // int lower = anno != null ? anno.lowerBound() : 0;
    // int upper = anno != null ? anno.upperBound() : 1;
    // if (upper == 1 && TypeUtils.isList(getter.getReturnType().getRawType()))
    // upper = -1;
    // UAssociationType assoType = getAggregation(anno != null ? anno.associationType() : AssociationType.PROPERTY);
    //
    // UStructuralFeature feature = new UStructuralFeatureImpl(name, type, lower, upper, assoType, getter, setter,
    // desc);
    // return feature;
    // }
    //
    //
    //
    //
    //
    //
    //
    // private static PropertyDescriptor getPropertyDescriptor(BeanInfo beanInfo, String featureName) {
    // for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()){
    // if (desc.getName().equals(featureName))
    // return desc;
    // }
    // //in some cases the naming schema used by java reflection / PropertyDescriptor does not match our schema,
    // therefore we have to
    // // use additional search
    // String fn_lc = featureName.toLowerCase();
    // for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()){
    // if (fn_lc.equals(desc.getName().toLowerCase())){
    // //in this case check if there is an annotation available. if the the name of the annotation match, its ok
    // UMLProperty anno = getPropertyAnnotation(desc);
    // if (anno.name() != null && anno.name().equals(featureName))
    // return desc;
    // }
    // }
    // return null;
    // }
    //
    //
    // private static HashMap<Class<?>, BeanInfo> mBeanInfos = new HashMap<Class<?>, BeanInfo>();
    //
    // private static BeanInfo getBeanInfo(Class<?> type_j_class) {
    // if (mBeanInfos.containsKey(type_j_class))
    // return mBeanInfos.get(type_j_class);
    // try {
    // BeanInfo bi = Introspector.getBeanInfo(type_j_class);
    // mBeanInfos.put(type_j_class, bi);
    // return bi;
    // } catch (IntrospectionException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
    //
    // public static List<UClassifier> getInterfaces(UClassifier cl) {
    // ArrayList<UClassifier> out = new ArrayList<UClassifier>();
    // Class<?> j_cl = cl.getRepresentedClass();
    // for (Class<?> in : j_cl.getInterfaces()){
    // UClassifier parent = UCoreMetaRepository.getClassifier(in);
    // if (parent != null)
    // out.add(parent);
    // }
    // return out;
    // }
    //
    // public static UClassifier getSuperType(UClass cl) {
    // Class<?> j_cl = cl.getRepresentedClass();
    // if (j_cl.getSuperclass() != null)
    // return UCoreMetaRepository.getClassifier(j_cl.getSuperclass());
    // return null;
    // }
    //
    //
    //
    //

}
