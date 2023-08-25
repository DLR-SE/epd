package de.emir.tuml.ucore.runtime.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class FeatureBaseModelAdapter {
	
	public interface IAcceptanceCallback {
		public boolean accept(UObject instance);
	}
	
	public interface IValueCallback {
		public boolean addValue(UObject instance);
		public boolean removeValue(UObject instance);
	}
	
	class AdapterItem implements IDisposable {
		UObject 						mObj;
		Set<Object> 					mVisitedA;//used to avoid cycles (the same set is forwarded to all children)
		AdapterItem 					mParent = null;
		List<IDisposable> 				mDisposables = new ArrayList<>();
		Map<Object, AdapterItem> 		mChildren;

		IDisposable						mValueDisposable; //to be notified, if the item is removed
		
		@Override
		public String toString() {
			String str = "FBMA_Adapter{ " + mUniqueID;
			str += ",\n\tObject: " + mObj;
			str += "\n\tChildren: ";
			if (mChildren != null) for (Object c : mChildren.keySet()) str += "[" + c + "] ";
			str += "\n};";
			return str;			
		}

		public AdapterItem(UObject obj, AdapterItem parent, Set<Object> visited) {
			mObj = obj;
			mParent = parent;
			mVisitedA = visited;
			mVisitedA.add(obj);
			
			mValueDisposable = handleValue(mObj);
	
			buildChildren();
		}

		private void buildChildren() {
			UClass cl = mObj.getUClassifier();
			for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
				if (feature.isAttribute() || isIteratableFeature(feature) == false)
					continue;
				iterateChildren(mObj, feature);
				if (isObservableFeature(feature))
					//register a listener for all observed features, to get notified if a new object is added or an existing is removed. 
					mDisposables.add(mObj.registerListener(feature, new IValueChangeListener() {
						@Override
						public void onValueChange(Notification n) {
							if (n.getOldValue() != null)
								removeChild(n.getOldValue());
							if (n.getNewValue() != null) {
								addChild(n.getNewValue());
							}
						}
					}));
			}
		}

		private void iterateChildren(UObject uobj, UStructuralFeature feature) {
			Object o = feature.get(uobj);
			if (o == null)
				return; // nothing to do now, but maybe later -- see isObservableFeature() ...
			if (feature.isMany()) {
				List l = (List) o;
				for (Object e : l) {
					addChild(e);
				}
			} else
				addChild(o);
		}

		private void addChild(Object newValue) {
			if (newValue == null || mVisitedA.contains(newValue))
				return;
			if (newValue instanceof UObject) {
				AdapterItem item = new AdapterItem((UObject) newValue, this, mVisitedA);
				if (mChildren == null)
					mChildren = new HashMap<>();
				mChildren.put(newValue, item);
			}
		}

		private void removeChild(Object oldValue) {
			AdapterItem item = mChildren.remove(oldValue);
			if (item != null) {
				item.dispose();
				mVisitedA.remove(oldValue);
			}
		}

		@Override
		public void dispose() {			
			if (mValueDisposable != null && !mValueDisposable.isDisposed())
				mValueDisposable.dispose(); //notify external values that the model element is no longer valid
			mValueDisposable = null;
			
			mVisisted.remove(mObj); // may be visited another way?
			for (IDisposable d : mDisposables)
				d.dispose();
			mDisposables.clear();
			if (mChildren != null) {
				while (mChildren.isEmpty() == false) {
					Object k = mChildren.keySet().iterator().next();
					removeChild(k);
				}
			}
		}

		@Override
		public boolean isDisposed() {
			return mDisposables.isEmpty() && mValueDisposable == null;
		}
	}
	
	
	public static class ClassAcceptanceFilter implements IAcceptanceCallback {
		Class<?> mClazz;
		public ClassAcceptanceFilter(Class<?> clazz) {
			mClazz = clazz;
		}
		@Override
		public boolean accept(UObject instance) {
			if (instance == null) return false;
			Class<?> cl = instance.getClass();
			return TypeUtils.inherits(cl, mClazz);
		}
	}
	
	private String						mUniqueID;
	
	private Set<UStructuralFeature> 	mObservedFeatureSet = new HashSet<>();
	private Set<UStructuralFeature> 	mIteratableFeatureSet = new HashSet<>();
	private IValueCallback				mValueCallback;
	private IAcceptanceCallback			mAcceptanceCallback; //used to pre-filter (optional)
	
	private Map<Object, AdapterItem>	mRootItems = new HashMap<>(); //we store an AdapterItem for each input. This is only nessesary if the adapter is used as sub-model. 
	private Map<Object, Set<Object>> 	mVisisted = new HashMap<>();

	
	public FeatureBaseModelAdapter(String id, IValueCallback callback) {
		mUniqueID = id;
		mValueCallback = callback;
		
	}
	
	public void setAcceptanceCallback(IAcceptanceCallback cb) { mAcceptanceCallback = cb; }

	public boolean addObservableFeature(String str) {
		try {
			
			String[] clf = str.split("\\.");
			return addObservableFeature(clf[0], clf[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected IDisposable handleValue(UObject instance) {
		if (mAcceptanceCallback != null) {
			if (!mAcceptanceCallback.accept(instance))
				return null;
		}
		if (mValueCallback.addValue(instance)) {
			return new IDisposable() {				
				boolean mDisposed = false;
				@Override
				public boolean isDisposed() {
					return mDisposed;
				}
				
				@Override
				public void dispose() {
					if (!mDisposed) {
						mValueCallback.removeValue(instance);
						mDisposed = true;
					}
				}
			};			
		}
		return null;
	}

	public boolean addObservableFeature(String classifier, String feature) {
		UClassifier cl = UCoreMetaRepository.findClassifierBySimpleName(classifier);
		if (cl != null) {
			UStructuralFeature f = cl.getFeature(feature);
			if (f != null) {
				return addObservableFeature(f);
			}
		}
		return false;
	}

	public boolean addObservableFeature(UStructuralFeature feature) {
		if (feature == null)
			return false;
		return mObservedFeatureSet.add(feature);
	}

	public boolean addIteratableFeature(String str) {
		try {
			String[] clf = str.split("\\.");
			return addIteratableFeature(clf[0], clf[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addIteratableFeature(String classifier, String feature) {
		UClassifier cl = UCoreMetaRepository.findClassifierBySimpleName(classifier);
		if (cl != null) {
			UStructuralFeature f = cl.getFeature(feature);
			if (f != null) {
				addIteratableFeature(f);
				return true;
			}
		}
		return false;
	}

	public boolean addIteratableFeature(UStructuralFeature feature) {
		if (feature == null)
			return false;
		return mIteratableFeatureSet.add(feature);
	}

	public void removeObservableFeature(String str) {
		try {
			String[] clf = str.split("\\.");
			removeObservableFeature(clf[0], clf[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void removeObservableFeature(String classifier, String feature) {
		UClassifier cl = UCoreMetaRepository.findClassifierBySimpleName(classifier);
		if (cl != null) {
			UStructuralFeature f = cl.getFeature(feature);
			if (f != null) {
				removeObservableFeature(f);
				return ;
			}
		}
	}

	public boolean removeObservableFeature(UStructuralFeature feature) {
		if (feature == null)
			return true;
		return mObservedFeatureSet.remove(feature);
	}

	public boolean removeIteratbleFeature(UStructuralFeature feature) {
		if (feature == null)
			return true;
		return mIteratableFeatureSet.remove(feature);
	}

	
	public boolean isIteratableFeature(UStructuralFeature feature) {
		return isObservableFeature(feature) || mIteratableFeatureSet.contains(feature);
	}

	public boolean isObservableFeature(UStructuralFeature feature) {
		return mObservedFeatureSet.contains(feature);
	}

	
	
	
	public void changeModel(UObject input) {
		//cleanup
		AdapterItem rootItem = mRootItems.remove(input);
		//if we did already create an AdapterItem tree for this object, we remove it as something in the upper
		//hierarchie has been changed and invalidated the created items. 
		if (rootItem != null) {
			rootItem.dispose();
			rootItem = null;
			mVisisted.remove(input);
			assert(mVisisted.isEmpty());
		}
		
		//rebuild with new model
		Set<Object> visited = new HashSet<>(); //visited will ensure no internal cycles, while the closedList is valid for the whole view / scene
		rootItem = new AdapterItem((UObject)input, null, visited);
		mRootItems.put(input, rootItem);
		mVisisted.put(input, visited);
	}
	public void clear() {
		if (mRootItems != null) {
			for (Object in : mRootItems.keySet()) {
				AdapterItem ai = mRootItems.get(in);
				ai.dispose();
				mVisisted.remove(in); 
			}
			mRootItems.clear();
		}

		if (mVisisted.isEmpty() == false) {
			ULog.error("Something went wrong with cleaning up the model");
			mVisisted.clear();
		}
	}


}
