package de.emir.tuml.ucore.runtime.pointer;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;


/**
 * 
 * @author sschweigert
 *
 *@deprecated use PointerObserver instead or even better the methods from PointerOperations
 */
@Deprecated
public final class PointerChangeListener implements IValueChangeListener, IDisposable {

    static class PointerData {
        public PointerData(Pointer pointer) {
            feature = pointer.getPointedFeature();
            if (pointer instanceof FeaturePointer)
                listIndex = ((FeaturePointer) pointer).getListIndex();
        }

        UStructuralFeature feature;
        int listIndex = -1;
    }

    static class ForwardPointerChain {
        UObject root;
        ArrayList<PointerData> pointerData;

        Object getValue(int i) {
            if (i <= 0)
                return root;
            Object parent = getValue(i - 1);
            if (parent instanceof UObject) {
                UObject up = (UObject) parent;
                int lidx = pointerData.get(i - 1).listIndex;
                UStructuralFeature f = pointerData.get(i - 1).feature;
                if (f.isMany() && lidx >= 0) {
                    return ((List) f.get(up)).get(lidx);
                }
                return f.get(up);
            }
            return null;
        }
    }

    public static ForwardPointerChain getForwardPointerChain(Pointer ptr) {
        Pointer p = ptr;
        ArrayList<Pointer> pointerChain = new ArrayList<>();
        pointerChain.add(p);
        while (p != null) {
            if (p instanceof PointerChain) {
                PointerChain pc = (PointerChain) p;
                p = pc.getParent();
                pointerChain.add(0, p);
            } else
                p = null;
        }
        ForwardPointerChain fpc = new ForwardPointerChain();
        fpc.root = pointerChain.get(0).getPointedContainer();
        if (fpc.root == null || ptr instanceof ObjectPointer) {
            // an object pointer may not have a root (e.g. is the overall root itself)
            // in this case, we need to take the root object itself
            fpc.root = PointerOperations.getPointerRoot(ptr);
            pointerChain.remove(0);
        }
        fpc.pointerData = new ArrayList<>();
        for (int i = 0; i < pointerChain.size(); i++)
            fpc.pointerData.add(new PointerData(pointerChain.get(i)));
        return fpc;
    }

    @SuppressWarnings("rawtypes")
    class PCLListener implements IValueChangeListener {

        private int mIndex;

        public PCLListener(int i) {
            mIndex = i;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onValueChange(Notification notification) {
            if (mDelegate != null)
                mDelegate.onValueChange(notification);
            if (mIndex < mListener.size() - 1) { // in this case we know that there are following listener, so we have
                                                 // to remove them from the old instance and register them into the new
                                                 // instance.
                // TODO: how to handle add_many and remove_many
                Object ov = notification.getOldValue();
                if (null != ov && ov instanceof UObject) {
                    _remove(ov, mIndex + 1);
                }
                Object nv = notification.getNewValue();
                if (null != nv && nv instanceof UObject) {
                    _register(nv, mIndex + 1);
                }
            }
        }

    }

    @SuppressWarnings("rawtypes")
    private IValueChangeListener 	mDelegate;
    ForwardPointerChain 			mFPC;
    private ArrayList<PCLListener> 	mListener = new ArrayList<>();
    
    
    private boolean 				mDisposed = false;

    public PointerChangeListener(@SuppressWarnings("rawtypes") IValueChangeListener delegate) {
        mDelegate = delegate;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onValueChange(Notification notification) {
        if (mDelegate != null)
            mDelegate.onValueChange(notification);
    }

	@Override
	public void dispose() {
		remove(mFPC);
		mDisposed = true;
	}

	@Override
	public boolean isDisposed() {
		return mDisposed;
	}
    
    
    void register(ForwardPointerChain fpc) {
        mFPC = fpc;
        for (int i = 0; i < fpc.pointerData.size(); i++)
            mListener.add(new PCLListener(i));
        _register(mFPC.root, 0);
    }

    public void remove(ForwardPointerChain mFPC2) {
        _remove(mFPC2.root, 0);
    }

    private void _register(Object v, int start) {
        if (mFPC.pointerData.size() == 0) {
            // in such a case the root pointer was an ObjectPointer, and we do not have to register featuures, just
            // observe the (whole) pointed object
            mFPC.root.registerListener(this);
        } else {
            for (int i = start; i < mFPC.pointerData.size(); i++) {
                if (v instanceof UObject) {
                    UObject uobj = (UObject) v;
                    UStructuralFeature f = mFPC.pointerData.get(i).feature;
                    PCLListener pcll = mListener.get(i);
                    uobj.registerListener(f, pcll);
                    // System.out.println("Register: " + uobj + " ; " + f);
                    if (f.isMany()) {
                        int lidx = mFPC.pointerData.get(i).listIndex;
                        List l = (List) f.get(uobj);
                        if (l == null || l.size() < lidx || lidx < 0)
                            break;
                        else
                            v = l.get(lidx);
                    } else
                        v = f.get(uobj);
                }
            }
        }
        // System.out.println();
    }

    private void _remove(Object v, int start) {
        if (mFPC.pointerData.size() == 0) {
            // we did regsiter ourself as a classifier listener to the root object (see _regsiter)
            mFPC.root.removeListener(this);
        } else {
            for (int i = start; i < mFPC.pointerData.size(); i++) {
                if (v instanceof UObject) {
                    UObject uobj = (UObject) v;
                    UStructuralFeature f = mFPC.pointerData.get(i).feature;
                    PCLListener pcll = mListener.get(i);
                    uobj.removeListener(f, pcll);
                    // System.out.println("Remove: " + uobj + " ; " + f);
                    int lidx = mFPC.pointerData.get(i).listIndex;
                    if (f.isMany() && lidx >= 0) {                        
                        v = ((List) f.get(uobj)).get(lidx);
                    } else
                        v = f.get(uobj);
                }
            }
        }
        // System.out.println();
    }

   


}
