package de.emir.tuml.ucore.runtime.utils.internal.ObserverOptions;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.ClassifierUtilities;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil;

import java.util.*;
// since there is not much content defined in this module unit test for this class are defined in "model/universal/Physics/src/test/java/test/de/emir/tuml/ucore/runtime/observer/TreeObserverTest.java"

/**
 * TreeObserverOption used to filter any irrelevant elements and features in the Tree. Only Elements of UTypes that
 * <b>can</b> own instances of defined target UClassifiers (e.g. UClass of Vessel) are considered.
 * <br>This observer option will only allow registrations for targeted feature. No Classifier listeners will be allowed.
 * <br>This is done using the reflections provided by the UCore data model. Since the evaluation takes a lot of computational effort results are stored and repurposed.
 * Restrictions from the association type are considered as well.
 * <pre>
 *                 [typeToInvestigate] ✓
 *                  /              \
 *          [ownedTypeA]×      [ownedTypeB] ✓
 *            /      \            /       \
 * [ownedTypeC]×  [...]×     [...]×  [targetType] ✓
 *                                           \
 *                                         [...]×
 *
 *  × = not eligible | ✓ = eligible
 * </pre>
 * @implNote Dynamic changes in the structural features of UClassifiers will not be considered.
 * @implNote This TreeObserverOptions resolves possible ownership via reflections, which is resource intensive. Therefore, results are stored. <br>
 * It is advisable to use reuse this observer on multiple TreeListeners if possible. Especially with recurring removal and application of TreeListeners with this TreeObserverOptions
 */
public class TargetTypeTreeObserverOptions implements TreeObserverUtil.ITreeObserverOptions {
    /** The type this ObserverOption is allowing registration for */
    private final Collection<UClassifier> mClassifiers;
    private final boolean mRegisterClassifierListenerToTarget;
    /** stores whether a classifier is able to own an instance of mType in its subtree*/
    private final Map<UType, Boolean> elegebilityMap = new HashMap<>(128);
    /** static classifier utils used for identification of child classifiers. It stores found inheritance trees. Therefore, it is appropriate to use a static one. */
    private static final ClassifierUtilities mClassifierUtilities = new ClassifierUtilities();
    boolean registerForAggregations;
    boolean registerForAssociations ;
    boolean registerForCompositions;
    boolean registerForProperties;

    /**
     * Creates a ClassifierTreeObserverOptions with all parameters
     * @param targetClassifiers Collection of targetClassifiers to consider for observer eligibility.
     * @param association register for associations
     * @param aggregation register for aggregations
     * @param property register for properties
     * @param composition register for compositions
     * @param registerClassifierListenerToTarget register a classifier listener to the target classifier
     */
    public TargetTypeTreeObserverOptions(final Collection<UClassifier> targetClassifiers, final boolean association, final boolean aggregation, final boolean property, final boolean composition, final boolean registerClassifierListenerToTarget) {
        registerForAggregations = aggregation;
        registerForAssociations = association;
        registerForCompositions = composition;
        registerForProperties = property;
        mClassifiers = new HashSet<>(targetClassifiers);
        mRegisterClassifierListenerToTarget = registerClassifierListenerToTarget;
        // put targetClassifiers and their children into the eligibility map
        for (UClassifier classifier : targetClassifiers) {
            elegebilityMap.put(classifier, true); // use classifier listeners

            Collection<UClassifier> inheritedClassifiers = mClassifierUtilities.getClassesInheritFrom(classifier, true);
            for (UClassifier inheritedClassifier : inheritedClassifiers) {
                elegebilityMap.put(inheritedClassifier, true); // use classifier listeners
            }
        }
    }

    /**
     * Simplified Constructor with a single input Classifier and no further information.
     * implicitly sets:
     *  aggregation = true
     *  association = true
     *  composition = true
     *  property = true
     *  registerClassifierListenerToTarget = true
     *
     * @param classifier input classifier
     */
    public TargetTypeTreeObserverOptions(UClassifier classifier) {
        this(List.of(classifier), true, true, true, true, true);
    }

    /**
     * Simplified Constructor with a single input Class and no further information.
     * implicitly sets:
     *  aggregation = true
     *  association = true
     *  composition = true
     *   property = true
     *
     * @param clazz input class. The UClassifier is identified automatically
     */
    public TargetTypeTreeObserverOptions(Class<? extends UObject> clazz) {
        this(UCoreMetaRepository.getClassifier(clazz));
    }

    /**
     * Convenience method to get the UClassifiers of classes needed in the constructor
     * @param classes input classes
     * @return Collection of UClassifiers
     */
    public static Collection<UClassifier> toClassifiers(Collection<Class<? extends UObject>> classes) {
        Collection<UClassifier> classifiers = new ArrayList<>(classes.size());
        for (Class<? extends UObject> clazz : classes) {
            classifiers.add(UCoreMetaRepository.getClassifier(clazz));
        }
        return classifiers;
    }

    /**
     * Get the classifier this Observer Option focuses
     * @return the
     */
    public Collection<UClassifier> getClassifier() {
        return new HashSet<>(mClassifiers);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean shallRegisterFeatureListener(UStructuralFeature feature) {
        // inspect association type
        boolean resultFromAssociationType = switch (feature.getAggregation()) {
            case AGGREGATION:
                yield registerForAggregations;
            case ASSOCIATION:
                yield registerForAssociations;
            case COMPOSITION:
                yield registerForCompositions;
            case PROPERTY:
                yield registerForProperties;
        };
        // can feature own an instance of the target type
        if (!resultFromAssociationType) {
            return false;
        }
        UType featureType = feature.getType();
        Boolean resultFromMap = elegebilityMap.get(featureType);
        if (resultFromMap == null) {
            investigateType(featureType);
            resultFromMap = elegebilityMap.get(featureType);
        }
        return resultFromMap;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean shallRegisterClassifierListener(UClassifier classifier) {
        return mRegisterClassifierListenerToTarget && mClassifiers.contains(classifier);
    }

    /**
     *  Investigate whether a UType is eligible for  an observer by this option.
     * If a Type <b>can</b> own any Instance of a Classifier this Option is parametrized with @see "classifier" in Constructor) directly or in its subtree it is considered eligible for an observer.
     * This is done using the reflections provided by the UCore data model. Since this evaluation takes a lot of computational effort results of evaluations are saved.
     * Restrictions from the association type are considered as well.
     * <pre>
     *                  [typeToInvestigate] ✓
     *                  /                \
     *          [ownedTypeA]×        [ownedTypeB] ✓
     *           /       \             /       \
     * [ownedTypeC]×    [...]×      [...]×  [targetType] ✓
     *
     *  × = not eligible | ✓ = eligible
     * </pre>
     *
     * @param typeToInvestigate UType in question.
     */
    private void investigateType(UType typeToInvestigate) {
        Deque<UType> TypesToProcess = new ArrayDeque<>();
        Set<UType> typesInProcess = new HashSet<>(); // used to avoid infinite loops caused by circular ownership
        TypesToProcess.push(typeToInvestigate);
        typesInProcess.add(typeToInvestigate);

        processLoop :
        while (!TypesToProcess.isEmpty()) {
            UType currentType = TypesToProcess.pop();
            if (elegebilityMap.containsKey(currentType)) {
               continue; // classifier is known, no work to be done
            }
            if (!(currentType instanceof UClassifier currentClassifier)) { // if the type is not a classifier it cannot own an instance of mClassifier
                elegebilityMap.put(currentType, false);
                continue;
            }
            // extract all possible classifiers of owned features
            List<UStructuralFeature> ownedFeatures = currentClassifier.getAllStructuralFeatures();
            List<UClassifier> ownedFeatureClassifiers = new ArrayList<>(ownedFeatures.size());
            for (UStructuralFeature feature : ownedFeatures) {
                // evaluate by UAssociationType whether the feature is of interest for the consideration
                if (!switch (feature.getAggregation()) {
                case AGGREGATION:
                    yield registerForAggregations;
                case ASSOCIATION:
                    yield registerForAssociations;
                case COMPOSITION:
                    yield registerForCompositions;
                case PROPERTY:
                    yield registerForProperties;
                }) {
                    continue;
                }
                // extract type from feature
                UType featureType = feature.getType();
                if (featureType instanceof UClassifier featureClassifier) {
                    ownedFeatureClassifiers.add(featureClassifier);
                } else {
                    // if feature type is not a classifier the feature type cannot own other instances of the target. Therefore, will never be eligible.
                    elegebilityMap.put(featureType, false);
                }
            }
            // Classifiers that could own or be target classifiers
            Collection<UClassifier> possibleClassifiers = new ArrayList<>(mClassifierUtilities.getClassesInheritFrom(currentClassifier, false)); // inherited classes of currentType
            for (UClassifier ownedClassifier : ownedFeatureClassifiers) {
                Collection<UClassifier> inheritedClassifier = mClassifierUtilities.getClassesInheritFrom(ownedClassifier, false); // inherited classes of owned features
                possibleClassifiers.addAll(inheritedClassifier);
            }
            // check if possible classifiers are known
            List<UClassifier> unknownClassifiers = new ArrayList<>();
            for (UClassifier classifierToCheck : possibleClassifiers) {
                if (!elegebilityMap.containsKey(classifierToCheck)) {
                    if (!typesInProcess.contains(classifierToCheck)) {
                        unknownClassifiers.add(classifierToCheck);
                    }
                }
            }
            // if there are unknown classifier push current classifier onto the stack and the unknown classifier on top of the stack.
            // when the classifier is processed again all possible classifiers should be known
            if (!unknownClassifiers.isEmpty()) {
                TypesToProcess.push(currentType);
                for (UClassifier unknownClassifierToProcess : unknownClassifiers) {
                    if (typesInProcess.contains(unknownClassifierToProcess)) {
                        continue; // type is already in process. No need to add again
                    }
                    TypesToProcess.push(unknownClassifierToProcess);
                    typesInProcess.add(unknownClassifierToProcess);

                }
                continue processLoop;
            }
            // if all classifiers are known then evaluate whether the classifier is mClassifier can own a feature of mClassifier
            for (UClassifier possibleClassifier : possibleClassifiers) {
                if (typesInProcess.contains(possibleClassifier)) {
                    continue; // type is in process. Eligibility is unknown.
                }
                if (elegebilityMap.get(possibleClassifier)) {
                    elegebilityMap.put(currentType, true); // eligibility has been proven. Note that this can happen even if some classifiers are still unknown
                    continue processLoop;
                }
            }
            // all classifiers are known and not eligible. Therefore, this one is not eligible
            elegebilityMap.put(currentType, false);
        }
    }
}
