package de.emir.tuml.ucore.runtime.progress;

public class NullProgressMonitor implements IProgressMonitor {

    @Override
    public void setProgress(float percent) {
    }

    @Override
    public void setMessage(String name) {
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public float getProgress() {
        return 0;
    }

}
