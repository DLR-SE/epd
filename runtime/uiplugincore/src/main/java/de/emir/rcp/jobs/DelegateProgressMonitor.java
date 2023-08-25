package de.emir.rcp.jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class DelegateProgressMonitor implements IProgressMonitor {

    private String msg;
    private float progress;

    private List<IProgressMonitor> delegates = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void setProgress(float percent) {

        this.progress = percent;

        synchronized (delegates) {

            for (IProgressMonitor d : delegates) {
                d.setProgress(percent);
            }

        }

    }

    @Override
    public void setMessage(String msg) {

        this.msg = msg;

        synchronized (delegates) {

            for (IProgressMonitor d : delegates) {
                d.setMessage(msg);
            }

        }

    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public float getProgress() {
        return progress;
    }

    public void addDelegate(IProgressMonitor m) {
        synchronized (delegates) {
            delegates.add(m);
        }
    }

    public void removeDelegate(IProgressMonitor m) {
        synchronized (delegates) {
            delegates.remove(m);
        }
    }

    public IProgressMonitor[] getDelegates() {

        IProgressMonitor[] result = null;

        synchronized (delegates) {
            result = delegates.toArray(new IProgressMonitor[0]);
        }

        return result;
    }

}
