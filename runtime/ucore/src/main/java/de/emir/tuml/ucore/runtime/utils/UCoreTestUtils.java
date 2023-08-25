package de.emir.tuml.ucore.runtime.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPrimitiveType;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;

/**
 * Utils for creating Unit tests
 * 
 * @author sschweigert
 *
 */
public class UCoreTestUtils {

    public static class RNGModelOptions {
        public int depth;
        public Random rng;
        public int maxListSize = 100;
        public int minListSize = 0;
        public int maxCreatedInstances = Integer.MAX_VALUE;
    }

    private static class ExtRNGOptions {
        public final RNGModelOptions base;
        public int numberOfInstances;

        public ExtRNGOptions(RNGModelOptions o) {
            base = o;
        }

        public UObject createInstance(UClass cl) {
            if (numberOfInstances > base.maxCreatedInstances)
                return null;
            UObject inst = cl.createNewInstance();
            numberOfInstances++;
            return inst;
        }

        public int createRandomListSize(UStructuralFeature feature) {
            int ma = Math.max(feature.getMultiplicity().getUpper(), base.maxListSize);
            int mi = Math.min(feature.getMultiplicity().getLower(), base.minListSize);
            return base.rng.nextInt(ma - mi) + mi;
        }
    }

    public static UObject createRandomModel(UClass rootCl, RNGModelOptions options) {
        assert (rootCl != null);
        assert (options.depth >= 0);
        assert (options.rng != null);
        ExtRNGOptions opt = new ExtRNGOptions(options);

        UObject inst = rootCl.createNewInstance();
        assert (inst != null);
        fillInstance(inst, opt.base.depth, opt);
        return inst;
    }

    private static void fillInstance(UObject inst, int deep, ExtRNGOptions opt) {
        if (deep <= 0)
            return;
        assert (inst != null);
        UClass cl = inst.getUClassifier();
        for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
            if (feature.isMany()) {
                List l = (List) feature.get(inst);
                int numEntries = opt.createRandomListSize(feature);
                for (int i = 0; i < numEntries; i++) {
                    Object v = getFeatureValue(feature, deep - 1, opt);
                    if (v != null) // may be null if to much instances have been created
                        l.add(v);
                }
            } else {
                feature.set(inst, getFeatureValue(feature, deep - 1, opt));
            }
        }
    }

    private static Object getFeatureValue(UStructuralFeature feature, int deep, ExtRNGOptions opt) {
        UType t = feature.getType();
        if (t instanceof UEnum) {
            return randomLiteral((UEnum) t, opt);
        } else if (t instanceof UPrimitiveType) {
            return randomPrimitive((UPrimitiveType) t, opt);
        } else { // interface or classifier
            if (feature.getAggregation() == UAssociationType.AGGREGATION) {
                return findExistingInstance((UClassifier) t, opt);
            }
        }
        return null;
    }

    private static Object findExistingInstance(UClassifier t, ExtRNGOptions opt) {
        // TODO
        return null;
    }

    private static Object randomPrimitive(UPrimitiveType t, ExtRNGOptions opt) {
        Class<?> rt = t.getRepresentingClass();
        if (rt == double.class || rt == Double.class) {
            return opt.base.rng.nextDouble();
        } else if (rt == int.class || rt == Integer.class) {
            return opt.base.rng.nextInt();
        } else if (rt == String.class) {
            return UUID.randomUUID().toString();
        } else if (rt == boolean.class || rt == Boolean.class) {
            return opt.base.rng.nextBoolean();
        } else if (rt == float.class || rt == Float.class) {
            return opt.base.rng.nextFloat();
        } else if (rt == byte.class || rt == Byte.class) {
            return (byte) opt.base.rng.nextInt(255);
        } else if (rt == long.class || rt == Long.class) {
            return opt.base.rng.nextLong();
        } else if (rt == short.class || rt == Short.class) {
            return (short) opt.base.rng.nextInt(Short.MAX_VALUE);
        }
        return null;
    }

    private static Object randomLiteral(UEnum en, ExtRNGOptions opt) {
        int m = en.getLiterals().size();
        UEnumerator lit = en.getLiterals().get(opt.base.rng.nextInt(m));
        return en.createNewInstance(lit);
    }

}
