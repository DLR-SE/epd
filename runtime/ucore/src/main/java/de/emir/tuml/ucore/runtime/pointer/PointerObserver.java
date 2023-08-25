package de.emir.tuml.ucore.runtime.pointer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.tuml.ucore.runtime.utils.TypePointer;




public class PointerObserver implements IDisposable{
	
	public static IDisposable registerListener(Pointer ptr, IValueChangeListener vcl) {
		return new PointerObserver(ptr, new ValueDelegateListener(vcl));
	}
	public static IDisposable registerListener(Pointer ptr, ITreeValueChangeListener tcl) {
		return new PointerObserver(ptr, new TreeDelegateListener(tcl));
	}
	
	
	interface IPointerObserverListener extends IDisposable {
		void forwardNotification(Notification notification);

		void register(UObject parent);

		void setNext(IPointerObserverListener iPointerObserverListener);
	}
	
	
	private static class AbstractPointerObserverListener implements IPointerObserverListener {
		protected UStructuralFeature 				mFeature;
		protected int								mListIndex;
		protected IDisposable 						mThisDisposable;
		protected IPointerObserverListener			mNext;
		
		protected AbstractPointerObserverListener(UStructuralFeature f, int i) {
			changeFeatureAndIndex(f, i);
		}
		
		public void setNext(IPointerObserverListener next) {
			mNext = next;
		}
		

		public void changeFeatureAndIndex(UStructuralFeature feature, int listIndex) {
			mFeature = feature;
			mListIndex = listIndex;
		}

		@Override
		public void forwardNotification(Notification notification) {
			//System.out.println("Forward");
		}

		@Override
		public void register(UObject parent) {
			// TODO
			
		}
		@Override
		public void dispose() {
			if (mThisDisposable != null) mThisDisposable.dispose();
			mThisDisposable = null;
			if (mNext != null)
				mNext.dispose();
		}
		@Override
		public boolean isDisposed() {
			return mThisDisposable == null || (mNext != null && mNext.isDisposed());
		}
	}
	
	private static class ValueDelegateListener extends AbstractPointerObserverListener {
		private final IValueChangeListener	mDelegate;
		
		public ValueDelegateListener(IValueChangeListener delegate) {
			super(null, -1);
			mDelegate = delegate;
		}
		@Override
		public void forwardNotification(Notification notification) {
			mDelegate.onValueChange(notification);
		}
		@Override
		public void register(UObject parent) {
			mThisDisposable = parent.registerListener(mFeature, vcl -> {
				if (mListIndex >= 0) { //need to check if the correct instance changed
					
				}else
					forwardNotification(vcl);	
			});
		}

	}
	private static class TreeDelegateListener extends AbstractPointerObserverListener {
		private final ITreeValueChangeListener mDelegate;
		public TreeDelegateListener(ITreeValueChangeListener delegate) {
			super(null, -1);
			mDelegate = delegate; 
		}
		@Override
		public void forwardNotification(Notification notification) {
			mDelegate.onValueChange(notification);
		}
		
		@Override
		public void register(UObject parent) {
			mThisDisposable = parent.registerTreeListener(tvl -> {
				if (mListIndex >= 0) { //need to check if the correct instance changed
					
				}else
					forwardNotification(tvl);
			});
		}
		
	}
	private class IntermediateListener extends AbstractPointerObserverListener implements IValueChangeListener, IPointerObserverListener{
			
		
		public IntermediateListener(final UStructuralFeature feature, final int listIndex) {
			super(feature, listIndex);
			mFeature = feature;
			mListIndex = listIndex;
		}
				
		@Override
		public void onValueChange(Notification notification) {
			Object oldValue = notification.getOldValue();
			if (oldValue instanceof UObject) {
				if (mNext != null)
					mNext.dispose();
				//System.out.println();
			}
			Object newValue = notification.getNewValue();
			if (newValue != null && newValue instanceof UObject) {
				if (mNext != null)
					mNext.register((UObject)newValue);
				//System.out.println();
			}
			forwardNotification(notification);
		}
		
		@Override
		public void register(UObject parent) {
			if (parent != null) {
				mThisDisposable = parent.registerListener(mFeature, this); //we save the disposable as it is faster to remove - otherwise the UObject has to search the disposable
				Object child = PointerOperations.getValue(parent, mFeature, mListIndex);
				if (child instanceof UObject) {
					mNext.register((UObject) child);
				}
			}//otherwise there is nothing to register, however if the value changes, we will get notified and do the registration then.
		}
		
		
		@Override
		public void forwardNotification(Notification notification) {
			mDelegate.forwardNotification(notification);
		}
	}
	
	
	final UObject										mRoot; //TODO: not needed after first registration but nice for debugging
	final AbstractPointerObserverListener				mDelegate;
	final ArrayList<IPointerObserverListener>			mListener = new ArrayList<>();
	
	
	protected PointerObserver(Pointer ptr, AbstractPointerObserverListener delegate) {
		mDelegate = delegate;
	
		mRoot = getPointerList(ptr, mListener);
		
		//if the delegate is a tree listener, we need to get notified about the last pointed element (which shall be an UObject)
		//to re-register the tree listener. 
		//on the other hand, if the listener is a value listener, we do not need to add another abstraction layer but register the delegate
		//directly. That is why we replace the last (intermediate) listener with the direct delegate, e.g. remove the last
		if (mDelegate instanceof ValueDelegateListener && mListener.isEmpty() == false) {
			IntermediateListener last = (IntermediateListener)mListener.remove(mListener.size()-1);
			mDelegate.changeFeatureAndIndex(last.mFeature, last.mListIndex);
		}
		mListener.add(mDelegate);
		
		
		for (int i = 1; i < mListener.size(); i++)
			mListener.get(i-1).setNext(mListener.get(i));
		
		mListener.get(0).register(mRoot);
	}
	
	UObject getPointerList(Pointer ptr, List<IPointerObserverListener> listener){
		while(ptr != null) {
			if (ptr instanceof TypePointer) {
				throw new UnsupportedOperationException("Observation of TypedPointers is not supported");
			}
			if (ptr instanceof ObjectPointer) {
				return ((ObjectPointer) ptr).getTheInstance();
			}else if (ptr instanceof FeaturePointer) {
				FeaturePointer fp = (FeaturePointer)ptr;
				listener.add(0, new IntermediateListener(fp.getPointedFeature(), fp.getListIndex()));
				if (ptr instanceof PointerChain)
					ptr = ((PointerChain) ptr).getParent();
				else
					return fp.getTheInstance();
			}
		}
		return null;
	}
	
	@Override
	public void dispose() {
		if (!mListener.isEmpty()) {
			mListener.get(0).dispose(); //disposes all children as well
			mListener.clear();
		}
	}

	@Override
	public boolean isDisposed() {
		return mListener.isEmpty();
	}

      


}


