package de.emir.tuml.ucore.runtime.progress;

public interface IProgressMonitor {
    public void setProgress(float percent);

    public void setMessage(String msg);

    public String getMessage();

    public float getProgress();

}
