package de.emir.tuml.ucore.runtime.utils.internal.ObserverOptions;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Tree listener option where only a white list of UStructuralFeatures and their subtrees are considered for listener attachment
 */
public class TreeObserverWhiteListOptions implements TreeObserverUtil.ITreeObserverOptions {

    public boolean registerForAssociations = true;
    public boolean registerForAggregations = true;
    public boolean registerForProperties = true;
    public boolean registerForCompositions = true;
    private final HashSet<UStructuralFeature> whiteList = new HashSet<>();

    public TreeObserverWhiteListOptions() {
        this(true, true, true, true, new ArrayList<>());
    }

    public TreeObserverWhiteListOptions(boolean association, boolean aggregation, boolean property, boolean composition, final Collection<UStructuralFeature> whiteList) {
        this.whiteList.addAll(whiteList);
        registerForAggregations = aggregation;
        registerForAssociations = association;
        registerForCompositions = composition;
        registerForProperties = property;
    }

    public TreeObserverUtil.ITreeObserverOptions addWhiteListedFeature(UStructuralFeature feature) {
        if (feature != null)
            whiteList.add(feature);
        return this;
    }

    public boolean shallRegisterFeatureListener(UStructuralFeature feature) {
        if (!whiteList.contains(feature))
            return false;
        return switch (feature.getAggregation()) {
            case AGGREGATION -> registerForAggregations;
            case ASSOCIATION -> registerForAssociations;
            case COMPOSITION -> registerForCompositions;
            case PROPERTY -> registerForProperties;
        };
    }

    @Override
    public boolean shallRegisterClassifierListener(UClassifier classifier) {
        return false; // never listen to classifier
    }
}
