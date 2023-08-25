package test.de.emir.tuml.ucore.runtime.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * @generated
 */
public class UtilsPackageTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        UtilsPackageTest test = new UtilsPackageTest();
        test.testClassifierLiterals();
        test.testClassifierDefinition();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierLiterals() {
        {
            UClass _QualifiedName = UtilsPackage.Literals.QualifiedName;
            assertNotNull(_QualifiedName);
            assertEquals(_QualifiedName.getName(), "QualifiedName");
        }
        {
            UClass _QualifiedNameProvider = UtilsPackage.Literals.QualifiedNameProvider;
            assertNotNull(_QualifiedNameProvider);
            assertEquals(_QualifiedNameProvider.getName(), "QualifiedNameProvider");
        }
        {
            UClass _QualifiedNameProviderExt = UtilsPackage.Literals.QualifiedNameProviderExt;
            assertNotNull(_QualifiedNameProviderExt);
            assertEquals(_QualifiedNameProviderExt.getName(), "QualifiedNameProviderExt");
        }
        {
            UInterface _Pointer = UtilsPackage.Literals.Pointer;
            assertNotNull(_Pointer);
            assertEquals(_Pointer.getName(), "Pointer");
        }
        {
            UClass _ObjectPointer = UtilsPackage.Literals.ObjectPointer;
            assertNotNull(_ObjectPointer);
            assertEquals(_ObjectPointer.getName(), "ObjectPointer");
        }
        {
            UClass _FeaturePointer = UtilsPackage.Literals.FeaturePointer;
            assertNotNull(_FeaturePointer);
            assertEquals(_FeaturePointer.getName(), "FeaturePointer");
        }
        {
            UClass _PointerChain = UtilsPackage.Literals.PointerChain;
            assertNotNull(_PointerChain);
            assertEquals(_PointerChain.getName(), "PointerChain");
        }
    }

    /**
     * @generated
     */
    public void testClassifierDefinition() {
        testClassifierDefinition_QualifiedName();
        testClassifierDefinition_QualifiedNameProvider();
        testClassifierDefinition_QualifiedNameProviderExt();
        testClassifierDefinition_ObjectPointer();
        testClassifierDefinition_FeaturePointer();
        testClassifierDefinition_PointerChain();
    }

    public void testClassifierDefinition_QualifiedName() {
        testClassifierDefinition_QualifiedName_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_QualifiedName_features() {
        UClass cl = UtilsPackage.Literals.QualifiedName;
        UStructuralFeature _segments = cl.getFeature("segments");
        assertNotNull(_segments);
        assertEquals(_segments.getType(), TypeUtils.getPrimitiveType(String.class));
        assertNotNull(_segments.getMultiplicity());
        assertEquals(_segments.getMultiplicity().getLower(), 0);
        assertEquals(_segments.getMultiplicity().getUpper(), -1);

    }

    public void testClassifierDefinition_QualifiedNameProvider() {
        testClassifierDefinition_QualifiedNameProvider_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_QualifiedNameProvider_features() {
        UClass cl = UtilsPackage.Literals.QualifiedNameProvider;
    }

    public void testClassifierDefinition_QualifiedNameProviderExt() {
        testClassifierDefinition_QualifiedNameProviderExt_hierarchie();
        testClassifierDefinition_QualifiedNameProviderExt_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_QualifiedNameProviderExt_hierarchie() {
        UClass cl = UtilsPackage.Literals.QualifiedNameProviderExt;
        assertTrue(cl.inherits(UtilsPackage.Literals.QualifiedNameProvider));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_QualifiedNameProviderExt_features() {
        UClass cl = UtilsPackage.Literals.QualifiedNameProviderExt;
        UStructuralFeature _nameFeatures = cl.getFeature("nameFeatures");
        assertNotNull(_nameFeatures);
        assertEquals(_nameFeatures.getType(), RuntimePackage.Literals.UStructuralFeature);
        assertNotNull(_nameFeatures.getMultiplicity());
        assertEquals(_nameFeatures.getMultiplicity().getLower(), 0);
        assertEquals(_nameFeatures.getMultiplicity().getUpper(), -1);

    }

    public void testClassifierDefinition_ObjectPointer() {
        testClassifierDefinition_ObjectPointer_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_ObjectPointer_features() {
        UClass cl = UtilsPackage.Literals.ObjectPointer;
        UStructuralFeature _theInstance = cl.getFeature("theInstance");
        assertNotNull(_theInstance);
        assertEquals(_theInstance.getType(), RuntimePackage.Literals.UObject);
        assertNotNull(_theInstance.getMultiplicity());
        assertEquals(_theInstance.getMultiplicity().getLower(), 0);
        assertEquals(_theInstance.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_FeaturePointer() {
        testClassifierDefinition_FeaturePointer_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_FeaturePointer_features() {
        UClass cl = UtilsPackage.Literals.FeaturePointer;
        UStructuralFeature _listIndex = cl.getFeature("listIndex");
        assertNotNull(_listIndex);
        assertEquals(_listIndex.getType(), TypeUtils.getPrimitiveType(int.class));
        assertNotNull(_listIndex.getMultiplicity());
        assertEquals(_listIndex.getMultiplicity().getLower(), 0);
        assertEquals(_listIndex.getMultiplicity().getUpper(), 1);

        UStructuralFeature _theInstance = cl.getFeature("theInstance");
        assertNotNull(_theInstance);
        assertEquals(_theInstance.getType(), RuntimePackage.Literals.UObject);
        assertNotNull(_theInstance.getMultiplicity());
        assertEquals(_theInstance.getMultiplicity().getLower(), 0);
        assertEquals(_theInstance.getMultiplicity().getUpper(), 1);

        UStructuralFeature _feature = cl.getFeature("feature");
        assertNotNull(_feature);
        assertEquals(_feature.getType(), RuntimePackage.Literals.IStructuralElement);
        assertNotNull(_feature.getMultiplicity());
        assertEquals(_feature.getMultiplicity().getLower(), 0);
        assertEquals(_feature.getMultiplicity().getUpper(), 1);

    }

    public void testClassifierDefinition_PointerChain() {
        testClassifierDefinition_PointerChain_hierarchie();
        testClassifierDefinition_PointerChain_features();
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_PointerChain_hierarchie() {
        UClass cl = UtilsPackage.Literals.PointerChain;
        assertTrue(cl.inherits(UtilsPackage.Literals.FeaturePointer));
    }

    /**
     * @generated
     */
    @Test
    public void testClassifierDefinition_PointerChain_features() {
        UClass cl = UtilsPackage.Literals.PointerChain;
        UStructuralFeature _parent = cl.getFeature("parent");
        assertNotNull(_parent);
        assertEquals(_parent.getType(), UtilsPackage.Literals.Pointer);
        assertNotNull(_parent.getMultiplicity());
        assertEquals(_parent.getMultiplicity().getLower(), 0);
        assertEquals(_parent.getMultiplicity().getUpper(), 1);

        UStructuralFeature _listIndex = cl.getFeature("listIndex");
        assertNotNull(_listIndex);
        assertEquals(_listIndex.getType(), TypeUtils.getPrimitiveType(int.class));
        assertNotNull(_listIndex.getMultiplicity());
        assertEquals(_listIndex.getMultiplicity().getLower(), 0);
        assertEquals(_listIndex.getMultiplicity().getUpper(), 1);

        UStructuralFeature _theInstance = cl.getFeature("theInstance");
        assertNotNull(_theInstance);
        assertEquals(_theInstance.getType(), RuntimePackage.Literals.UObject);
        assertNotNull(_theInstance.getMultiplicity());
        assertEquals(_theInstance.getMultiplicity().getLower(), 0);
        assertEquals(_theInstance.getMultiplicity().getUpper(), 1);

        UStructuralFeature _feature = cl.getFeature("feature");
        assertNotNull(_feature);
        assertEquals(_feature.getType(), RuntimePackage.Literals.IStructuralElement);
        assertNotNull(_feature.getMultiplicity());
        assertEquals(_feature.getMultiplicity().getLower(), 0);
        assertEquals(_feature.getMultiplicity().getUpper(), 1);

    }

}
