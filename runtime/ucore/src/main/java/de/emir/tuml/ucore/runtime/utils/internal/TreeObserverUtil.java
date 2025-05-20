package de.emir.tuml.ucore.runtime.utils.internal;

import de.emir.tuml.ucore.runtime.*;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Logger;

import java.util.*;
// since there is not much content defined in this module unit test for this class are defined in "model/universal/Physics/src/test/java/test/de/emir/tuml/ucore/runtime/observer/TreeObserverTest.java"

/**
 * Tree observer util that registers listeners to a SubTree of a UObject by decorating a delegate listener. <br>
 * The registration and removal of listeners caused by changes in the subtree is managed by this TreeObserverUtil, see the decorated method {@link #onValueChange(Notification)}. <br>
 * Options may be used to narrow down the subtree. The Option decides whether a classifier listener shall be registered to a classifier or a feature classifier shall be registered to a feature. E.g. {@link #OPTIONS_NO_AGGREGATIONS} where aggregations are not considered.
 * @implNote In Trees where a UObject is owned by multiple objects within the subtree own the same UObject via a Feature unexpected side effects may occur. <br>
 * Since listeners are removed on a "Remove" or "Unset" action in the model the listeners will be detached even if another UObject in the subtree own the removed UObject. This may cause situations where objects unexpectedly aren't listened to though.
 */
public class TreeObserverUtil implements IValueChangeListener<Object> {
    // Logger for this class
    private static final Logger LOG = ULog.getLogger(TreeObserverUtil.class);
    /**
     * TreeObserverOption that allows registration for all classifiers. No Feature will be listened to.
     */
    public static final TreeObserverOptions OPTIONS_ALLOW_ALL = new TreeObserverOptions(true, true, true, true);
    /**
     * TreeObserverOption that allows registration for all Features except for targets of aggregations. No Feature will be listened to.
     */
    public static final TreeObserverOptions OPTIONS_NO_AGGREGATIONS = new TreeObserverOptions(true, false, true, true);
    /**
     * Delegate listener this TreeObserverUtil owns and attaches to the tree
     */
    private final ITreeValueChangeListener mDelegate;
    /**
     * Set of all UObjects this treeObserverUtil listens to
     */
    private final Set<UObject> mListenedUObjects = new HashSet<>();
    /**
     * Options of this tree observer util
     */
    private final ITreeObserverOptions mOptions;

    /**
     * Convenience constructor without any filter -> attaches listeners to the whole subtree.
     * @implNote implicitly uses {@link #OPTIONS_ALLOW_ALL}
     * @param delegate delegate listener to attach to the entire subtree.
     */
    public TreeObserverUtil(ITreeValueChangeListener delegate) {
        this(delegate, OPTIONS_ALLOW_ALL);
    }

    /**
     * Constructor with all available parameter,
     * @param delegate delegate listener to attach to the subtree
     * @param options options that decides what shall (based ob the feature of a UObject) be listened to where (classifier or feature) the listener shall be attached.
     */
    public TreeObserverUtil(ITreeValueChangeListener delegate, ITreeObserverOptions options) {
        mDelegate = delegate;
        mOptions = options;
    }

    @Override
    public void onValueChange(final Notification<Object> notification) {
        // register/remove listener from tree
        switch (notification.getType()) {
            case ADD -> {// a previously empty field is now filled -> register listener to new object
                if (notification.getNewValue() instanceof UObject newUObject) {
                    register(newUObject);
                }
            }
            case ADD_MANY -> {// a previously empty field is now filled -> register listener to all items
                for (Object newItem : (Collection) notification.getNewValue())
                    if (newItem instanceof UObject uObjectItem)
                        register(uObjectItem);
            }
            case REMOVE -> {// a previously filled field is now empty -> remove listeners on old object
                if (notification.getOldValue() instanceof UObject oldUObject) {
                    remove(oldUObject);
                }
            }
            case REMOVE_MANY -> {
                // a previously filled field is now empty -> remove listeners on all old object
                for (Object oldCollection : (Collection) notification.getOldValue()) {
                    if (oldCollection instanceof UObject oldUObject) {
                        remove(oldUObject);
                    }
                }
            }
            case SET -> {// a new value has been set -> remove listeners of old object and add listeners to new object
                if (notification.getOldValue() instanceof UObject oldUObject) {
                    remove(oldUObject);
                }
                if (notification.getNewValue() instanceof UObject newUObject)
                    register(newUObject);
            }
            case UNSET -> { // same as remove
                if (notification.getOldValue() instanceof UObject oldUObject) {
                    remove(oldUObject);
                }
            }
        }
        // delegate to the owned listener
        mDelegate.onValueChange(notification);
    }


    /**
     * Register Listener to uObject and its children in its subtree restricted by the options provided
     * @param uObj to register listener to
     */
    public void register(final UObject uObj) {
        // how it works:
        //    1) Use Stack (Deque) to store objects to process
        //    2) extract feature of object
        //    3) evaluate whether to listen to the feature or its owned subtree (via classifier)
        //    4) attach corresponding listener if applicable
        //    5) if applicable push owned feature of subtree onto the stack
        synchronized (this) {
            Deque<UObject> unprocessedSubtrees = new ArrayDeque<>();
            unprocessedSubtrees.add(uObj);
            Set<UObject> processedUObjects = new HashSet<>();
            while (!unprocessedSubtrees.isEmpty()) {
                // get new subtree
                UObject subtreeRoot = unprocessedSubtrees.pop();
                if (mListenedUObjects.contains(subtreeRoot) || processedUObjects.contains(subtreeRoot)) {
                    continue; // skip if already processed or has already listeners attached
                }
                processedUObjects.add(subtreeRoot);
                UClass subTreeUClass = subtreeRoot.getUClassifier();
                if (subTreeUClass == null) {
                    LOG.warn("Class of UObject is null");
                    continue; // this should not happen
                }
                boolean classifierListenerAdded = false; // avoid multiple addition of classifier listeners
                // register classifier listener
                if (mOptions.shallRegisterClassifierListener(subTreeUClass)) {
                    subtreeRoot.registerListener(this);
                    mListenedUObjects.add(subtreeRoot);
                    classifierListenerAdded = true;
                }
                // extract features
                for (UStructuralFeature feature : subTreeUClass.getAllStructuralFeatures()) {
                    boolean shallRegisterFeatureListener = mOptions.shallRegisterFeatureListener(feature);
                    if (!classifierListenerAdded && shallRegisterFeatureListener) { // do not add feature listener if classifier listener has been added since this would be redundant
                        // only register if options allow it and if the feature is a reference (complex type) and not an Attribute
                        subtreeRoot.registerListener(feature, this); // register feature listener
                        mListenedUObjects.add(subtreeRoot);
                    }
                    if (classifierListenerAdded || shallRegisterFeatureListener) { // only push to subtree if feature is listened to (by classifier or directly)
                        Object valueOfFeature = feature.get(subtreeRoot); // get value of feature of current subtree via reflections
                        // push subtree to processing
                        if (!feature.isMany()) {
                            // add subTree to processing
                            if (valueOfFeature instanceof UObject newSubTree) {
                                unprocessedSubtrees.push(newSubTree);
                            }
                        } else { // isMany
                            // add all subTrees to processing
                            Collection valueAsMany = (Collection) valueOfFeature; // no type check needed since it isMany
                            for (Object object : valueAsMany) {
                                if (object instanceof UObject uObjectElement) {
                                    unprocessedSubtrees.push(uObjectElement);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Remove listener from uObject and its subtree.
     * @param uObj
     */
    public void remove(UObject uObj) {
        Deque<UObject> unprocessedSubtree = new ArrayDeque<>();
        unprocessedSubtree.add(uObj);
        Set<UObject> processedObjects = new HashSet<>(); // avoid circular processing

        synchronized (this) {
            while (!unprocessedSubtree.isEmpty()) {
                // get new subtree
                UObject currentRoot = unprocessedSubtree.pop();
                if (!mListenedUObjects.contains(currentRoot) || processedObjects.contains(currentRoot)) {
                    continue;
                }
                // detaches listener
                currentRoot.removeListener(this);
                mListenedUObjects.remove(currentRoot); // all feature listener and classifier listeners will be removed
                // add eligible subtree to stack
                UClass subTreeUClass = currentRoot.getUClassifier();
                if (subTreeUClass == null) {
                    continue;
                }
                for (UStructuralFeature feature : subTreeUClass.getAllStructuralFeatures()) {
                    // only register if the feature is a reference (complex type) and not an Attribute
                    if (!feature.isReference()) {
                        continue;
                    }
                    Object valueOfFeature = feature.get(currentRoot); // get value of feature of current subtree via reflections
                    if (!feature.isMany()) {
                        // add subTree to processing
                        if (valueOfFeature instanceof UObject newSubTree) {
                            unprocessedSubtree.push(newSubTree);
                        }
                    } else { // isMany
                        // add all subTrees to processing
                        List<Object> valueAsMany = (List<Object>) valueOfFeature; // no type check needed since it isMany
                        for (Object o : valueAsMany) {
                            if (o instanceof UObject uObjectElement) {
                                unprocessedSubtree.push(uObjectElement);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Inter face for Tree Observer Options used to restrict listener attachment to trees.
     */
    public interface ITreeObserverOptions {
        /**
         * Determine whether an Observer shall be registered a featureListener to a feature.
         * @param feature feature in question
         * @return true if an Observer shall register
         */
        boolean shallRegisterFeatureListener(UStructuralFeature feature);
        /**
         * Determine whether an Observer shall register a classifierListener to an UObject.
         * @param classifier in question
         * @return true if an Observer shall register
         */
        boolean shallRegisterClassifierListener(UClassifier classifier);
    }


    public ITreeValueChangeListener getDelegate() {
        return mDelegate;
    }

    /**
     * Basic tree observer option that can filter by association type and ignore certain features. It will only allow registration using classifier listeners
     */
    public static class TreeObserverOptions implements ITreeObserverOptions{

        private Collection<UStructuralFeature> ignoredFeature;
        private final boolean registerForAggregations;
        private final boolean registerForAssociations;
        private final boolean registerForCompositions;
        private final boolean registerForProperties;

        public TreeObserverOptions() {
            this(true, true, true, true);
        }

        public TreeObserverOptions(boolean association, boolean aggregation, boolean property, boolean composition) {
            this(association, aggregation, property, composition, new ArrayList<>());
        }

        public TreeObserverOptions(boolean association, boolean aggregation, boolean property, boolean composition, Collection<UStructuralFeature> ignoredFeature) {
            this.ignoredFeature = new HashSet<>(ignoredFeature);
            registerForAggregations = aggregation;
            registerForAssociations = association;
            registerForCompositions = composition;
            registerForProperties = property;
        }

        @Override
        public boolean shallRegisterFeatureListener(UStructuralFeature feature) {
            if (ignoredFeature.contains(feature)) {
                return false;
            }
            return switch (feature.getAggregation()) {
                case AGGREGATION -> registerForAggregations;
                case ASSOCIATION -> registerForAssociations;
                case COMPOSITION -> registerForCompositions;
                case PROPERTY -> registerForProperties;
            };
        }

        @Override
        public boolean shallRegisterClassifierListener(UClassifier classifier) {
            return false;
        }
    }

}
