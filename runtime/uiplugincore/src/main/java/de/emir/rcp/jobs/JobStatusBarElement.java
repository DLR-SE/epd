package de.emir.rcp.jobs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.JobManager;
import de.emir.rcp.manager.JobManager.JobData;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.statusbar.AbstractStatusBarElement;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class JobStatusBarElement extends AbstractStatusBarElement implements IProgressMonitor {

    private JobData currentJobData;

    private JLabel jobLabel;

    private JProgressBar progressBar;

    private JButton btnShowView;

    private boolean visible;

    protected String jobLabelMessage = "";

    protected float jobProgress = 0;

    public JobStatusBarElement() {

        JobManager jm = PlatformUtil.getJobManager();

        jm.subscribeRunningJobAdded(c -> handleJobChanged());

        jm.subscribeRunningJobRemoved(c -> handleJobChanged());

    }

    @Override
    public void setProgress(float percent) {

        jobProgress = percent;

        if (progressBar != null) {
            progressBar.setValue((int) jobProgress);
        }

    }

    @Override
    public void setMessage(String msg) {

        jobLabelMessage = msg;

        if (jobLabel != null) {
            jobLabel.setText(jobLabelMessage);
        }

    }

    @Override
    public float getProgress() {
        return jobProgress;
    }

    @Override
    public String getMessage() {
        return jobLabelMessage;
    }

    private void handleJobChanged() {

        JobManager jm = PlatformUtil.getJobManager();

        List<JobData> jobs = jm.getRunningJobs();

        JobData jd = null;

        synchronized (jobs) {

            if (jobs.size() > 0) {
                jd = jobs.get(jobs.size() - 1);
            }

        }

        setCurrentJob(jd);

    }

    private void setCurrentJob(JobData jd) {

        if (currentJobData != null) {

            if (jd == currentJobData) {
                return;
            }

            currentJobData.monitor.removeDelegate(this);

        }

        setMessage("");
        setProgress(0);

        if (jd != null) {
            currentJobData = jd;
            currentJobData.monitor.addDelegate(this);
            setVisible(true);
        } else {

            setVisible(false);

        }

    }

    private void setVisible(boolean isVisible) {
        visible = isVisible;

        if (btnShowView == null) {
            return;
        }

        jobLabel.setVisible(visible);
        progressBar.setVisible(visible);
        btnShowView.setVisible(visible);

    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContents() {

        JPanel p = new JPanel();
        GridBagLayout gbl_p = new GridBagLayout();
        gbl_p.columnWidths = new int[] { 150, 40, 20 };
        gbl_p.rowHeights = new int[] { 20 };
        gbl_p.columnWeights = new double[] { 1.0, 0.0, 0.0 };
        gbl_p.rowWeights = new double[] { 0.0 };
        p.setLayout(gbl_p);

        jobLabel = new JLabel(jobLabelMessage);
        GridBagConstraints gbc_lblJoblabel = new GridBagConstraints();
        gbc_lblJoblabel.anchor = GridBagConstraints.WEST;
        gbc_lblJoblabel.insets = new Insets(0, 1, 0, 5);
        gbc_lblJoblabel.gridx = 0;
        gbc_lblJoblabel.gridy = 0;

        jobLabel.setVisible(visible);

        jobLabel.setPreferredSize(new Dimension(150, 20));

        p.add(jobLabel, gbc_lblJoblabel);

        progressBar = new JProgressBar();
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.insets = new Insets(0, 5, 0, 5);
        gbc_progressBar.anchor = GridBagConstraints.WEST;
        gbc_progressBar.gridx = 1;
        gbc_progressBar.gridy = 0;

        progressBar.setVisible(visible);
        progressBar.setValue((int) jobProgress);
        progressBar.setPreferredSize(new Dimension(40, 20));

        p.add(progressBar, gbc_progressBar);

        btnShowView = new JButton();
        btnShowView.setPreferredSize(new Dimension(30, 20));
        btnShowView.setIcon(IconManager
                .getIcon(this, "icons/emiricons/32/background_tasks.png", IconManager.preferedSmallIconSize()));
        btnShowView.setFocusPainted(false);
        btnShowView.setVisible(visible);

        GridBagConstraints gbc_btnShowinprogressview = new GridBagConstraints();
        gbc_btnShowinprogressview.gridx = 2;
        gbc_btnShowinprogressview.gridy = 0;
        p.add(btnShowView, gbc_btnShowinprogressview);

        MouseAdapter mouseClickListener = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() != 2) {
                    return;
                }

                showDialogForCurrentJob();

            }

        };

        btnShowView
                .addActionListener(e -> PlatformUtil.getCommandManager().executeCommand(Basic.CMD_SHOW_PROGRESS_VIEW));

        progressBar.addMouseListener(mouseClickListener);
        jobLabel.addMouseListener(mouseClickListener);

        progressBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jobLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return p;
    }

    protected void showDialogForCurrentJob() {

        if (currentJobData != null) {

            JobManager jm = PlatformUtil.getJobManager();
            jm.showDialogForRunningJob(currentJobData);
        }

    }

}
