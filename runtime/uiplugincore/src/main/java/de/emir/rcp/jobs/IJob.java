package de.emir.rcp.jobs;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public interface IJob {
    public void run(IProgressMonitor monitor);

    /**
     * Defines whether this job is blocking. A blocking job prevents other jobs from running in parallel. Run via
     * JobManager a modal dialog is shown that can't be hidden.
     * 
     * @return
     */
    public boolean isBlocking();

    /**
     * Defines whether this job can be canceled by the user. A cancelable job should check if cancel() has been called
     * as often as possible within its run method
     * 
     * @return
     */
    public boolean isCancelable();

    /**
     * Defines whether this job should be run in background. If true, no dialog will be opened at the beginning of the
     * jobs' execution. Collides with isBlocking(). A blocking job always opens a non-closeable progress dialog.
     *
     * @return
     */
    public boolean isBackground();

    /**
     * Called when the user requests a cancel. Standard implementations should set a member var "canceled" to true which
     * is checked repeatedly within the run() method.
     */
    public void cancel();

    /**
     * The title of the job. Define what this job does.
     * 
     * @return
     */
    public String getTitle();
}
