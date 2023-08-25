package de.emir.rcp.views.basics.progress;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.emir.rcp.manager.JobManager;
import de.emir.rcp.manager.JobManager.JobData;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import io.reactivex.rxjava3.disposables.Disposable;

public class ProgressView extends AbstractView {

    private Map<JobData, JobProgressWidget> widgets = new HashMap<>();
    private Container parent;
    private Disposable addSubscription;
    private Disposable removeSubscription;
    private JScrollPane scrollPane;
    private JPanel area;

    public ProgressView(String id) {
        super(id);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContent() {

        this.parent = new JPanel();

        GridBagLayout gbl_parent = new GridBagLayout();

        gbl_parent.columnWeights = new double[] { 1.0 };
        gbl_parent.rowWeights = new double[] { 1.0 };
        parent.setLayout(gbl_parent);

        scrollPane = new JScrollPane();

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 0;
        parent.add(scrollPane, gbc_sc);

        area = new JPanel();

        area.setLayout(new BoxLayout(area, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(area);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        JobManager jm = PlatformUtil.getJobManager();

        List<JobData> jobs = jm.getRunningJobs();

        synchronized (jobs) {

            for (JobData jd : jobs) {
                addJob(jd);
            }

        }

        return this.parent;

    }

    private void addJob(JobData jd) {

        JobProgressWidget w = new JobProgressWidget(jd);
        jd.monitor.addDelegate(w);

        widgets.put(jd, w);
        w.setAlignmentX(Component.LEFT_ALIGNMENT);
        w.setMaximumSize(new Dimension(Integer.MAX_VALUE, w.getPreferredSize().height));
        area.add(w);
        area.repaint();
        area.revalidate();

    }

    private void removeJob(JobData jd) {

        JobProgressWidget w = widgets.get(jd);

        if (w == null) {
            return;
        }

        jd.monitor.removeDelegate(w);
        w.dispose();
        area.remove(w);
        area.repaint();
        area.revalidate();

    }

    @Override
    public void onOpen() {
        JobManager jm = PlatformUtil.getJobManager();

        addSubscription = jm.subscribeRunningJobAdded(jd -> addJob(jd));
        removeSubscription = jm.subscribeRunningJobRemoved(jd -> removeJob(jd));

    }

    @Override
    public void onClose() {

        if (addSubscription != null) {
            addSubscription.dispose();
        }

        if (addSubscription != null) {
            removeSubscription.dispose();
        }

    }

}
