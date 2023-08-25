package de.emir.rcp.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.SwingUtilities;

import de.emir.rcp.jobs.DelegateProgressMonitor;
import de.emir.rcp.jobs.IJob;
import de.emir.rcp.jobs.IJobFinishedHandler;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.ui.utils.dialogs.ProgressDialog;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * Manages the execution of jobs. Should be used for prolonged tasks to prevent UI locks.
 * 
 * @author fklein
 *
 */
public class JobManager implements IService {

    private BlockingQueue<JobData> queue = new LinkedBlockingQueue<>();

    private List<JobData> runningJobs = Collections.synchronizedList(new ArrayList<JobData>());

    private PublishSubject<JobData> addRunningJobSubject = PublishSubject.create();
    private PublishSubject<JobData> removeRunningJobSubject = PublishSubject.create();

    public JobManager() {
        initJobThread();
    }

    public Disposable subscribeRunningJobAdded(Consumer<JobData> c) {
        return addRunningJobSubject.subscribe(c);
    }

    public Disposable subscribeRunningJobRemoved(Consumer<JobData> c) {
        return removeRunningJobSubject.subscribe(c);
    }

    private void initJobThread() {

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    JobData entry = queue.take();

                    IJob job = entry.job;
                    if (job.isBlocking()) {

                        runJob(entry);

                    } else {

                        Thread t2 = new Thread(() -> runJob(entry));
                        t2.start();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t.setDaemon(true);
        t.start();

    }

    public List<JobData> getRunningJobs() {
        return runningJobs;
    }

    /**
     * Adds a job to the queue and executes it.
     * 
     * @param job
     */
    public void schedule(IJob job) {
        schedule(job, null);
    }

    /**
     * Adds a job to the queue and executes it.
     * 
     * @param job
     * @param cb
     *            A callback, fired when job is completed
     */
    public void schedule(IJob job, IJobFinishedHandler cb) {

        JobData jd = new JobData();
        jd.job = job;
        if (cb != null) {
            jd.addHandler(cb);
        }
        jd.monitor = new DelegateProgressMonitor();

        try {
            queue.put(jd);
        } catch (InterruptedException e) {
            ULog.error(e);
        }
    }

    private void runJob(JobData jd) {

        IJob job = jd.job;

        addRunningJob(jd);

        if (job.isBlocking() == true || job.isBackground() == false) {

            showDialogForRunningJob(jd);

        }

        try {
            job.run(jd.monitor);

            IJobFinishedHandler[] fhs = jd.getFinishHandler();

            // Execute desc because job dialog should be closed first and is always added last
            for (int i = fhs.length - 1; i >= 0; i--) {
                fhs[i].finished(job);
            }

        } catch (Exception | LinkageError er) {
            er.printStackTrace();
        }

        removeRunningJob(jd);

    }

    public void showDialogForRunningJob(JobData rjd) {

        ProgressDialog dialog = new ProgressDialog(PlatformUtil.getWindowManager().getMainWindow(), rjd);

        dialog.setCancelListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rjd.requestCancel();

            }
        });

        IJobFinishedHandler finishHandler = j -> {

            try {
                SwingUtilities.invokeAndWait(dialog::close);
            } catch (InvocationTargetException | InterruptedException e1) {
                ULog.error(e1);
            }

        };

        // Remove the finish handler closing the dialog if it is manually closed
        dialog.setCloseListener(e -> rjd.removeHandler(finishHandler));

        rjd.monitor.addDelegate(dialog);

        rjd.addHandler(finishHandler);

        SwingUtilities.invokeLater(dialog::open);

    }

    private void addRunningJob(JobData rjd) {
        synchronized (runningJobs) {
            runningJobs.add(rjd);
        }
        addRunningJobSubject.onNext(rjd);
    }

    private void removeRunningJob(JobData rjd) {
        synchronized (runningJobs) {
            runningJobs.remove(rjd);
        }
        removeRunningJobSubject.onNext(rjd);
    }

    public class JobData {
        public IJob job;
        public DelegateProgressMonitor monitor;

        private boolean isCancelRequested = false;
        private BehaviorSubject<Boolean> cancelRequestSubject = BehaviorSubject.createDefault(false);
        private List<IJobFinishedHandler> finishHandler = Collections.synchronizedList(new ArrayList<>());

        public void addHandler(IJobFinishedHandler h) {
            synchronized (finishHandler) {
                finishHandler.add(h);
            }
        }

        public void removeHandler(IJobFinishedHandler h) {
            synchronized (finishHandler) {
                finishHandler.remove(h);
            }
        }

        public Disposable subscribeCancelRequest(Consumer<Boolean> c) {
            return cancelRequestSubject.subscribe(c);
        }

        public IJobFinishedHandler[] getFinishHandler() {
            IJobFinishedHandler[] result = null;

            synchronized (finishHandler) {
                result = finishHandler.toArray(new IJobFinishedHandler[0]);
            }

            return result;
        }

        public void requestCancel() {

            if (isCancelRequested == false) {
                isCancelRequested = true;
                cancelRequestSubject.onNext(true);
                job.cancel();
            }

        }

    }
}
