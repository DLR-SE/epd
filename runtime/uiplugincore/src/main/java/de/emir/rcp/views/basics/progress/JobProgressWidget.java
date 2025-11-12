package de.emir.rcp.views.basics.progress;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import de.emir.rcp.manager.JobManager.JobData;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import io.reactivex.rxjava3.disposables.Disposable;

public class JobProgressWidget extends JPanel implements IProgressMonitor {

    private static final long serialVersionUID = -853040344684238236L;

    private JobData jobData;
    private JProgressBar progressBar;
    private JLabel lblJobmessage;
    private JButton btnCancelbutton;
    private JLabel lblJobtitle;
    private float progress;
    private JPanel panel;

    private Disposable cancelSubscription;

    public JobProgressWidget(JobData jd) {

        this.jobData = jd;

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;

        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWeights = new double[] { 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0 };
        setLayout(gbl_panel);

        JPanel content = new JPanel();

        GridBagLayout gbl_content = new GridBagLayout();

        gbl_content.columnWeights = new double[] { 1.0, 0.0 };
        gbl_content.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        content.setLayout(gbl_content);

        GridBagConstraints gbc_content = new GridBagConstraints();
        gbc_content.fill = GridBagConstraints.HORIZONTAL;
        gbc_content.insets = new Insets(0, 1, 0, 1);
        gbc_content.gridx = 0;
        gbc_content.gridy = 0;

        add(content, gbc_content);

        lblJobtitle = new JLabel();
        lblJobtitle.setFont(lblJobtitle.getFont().deriveFont(Font.BOLD, 11));
        GridBagConstraints gbc_lblJobtitle = new GridBagConstraints();
        gbc_lblJobtitle.anchor = GridBagConstraints.WEST;
        gbc_lblJobtitle.insets = new Insets(5, 5, 5, 0);
        gbc_lblJobtitle.gridx = 0;
        gbc_lblJobtitle.gridy = 0;

        lblJobtitle.setText(jd.job.getTitle());

        content.add(lblJobtitle, gbc_lblJobtitle);

        progressBar = new JProgressBar();
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.insets = new Insets(0, 5, 0, 5);
        gbc_progressBar.fill = GridBagConstraints.BOTH;
        gbc_progressBar.gridx = 0;
        gbc_progressBar.gridy = 1;
        content.add(progressBar, gbc_progressBar);

        lblJobmessage = new JLabel(" ");
        GridBagConstraints gbc_lblJobmessage = new GridBagConstraints();
        gbc_lblJobmessage.insets = new Insets(5, 5, 5, 5);
        gbc_lblJobmessage.anchor = GridBagConstraints.WEST;
        gbc_lblJobmessage.gridx = 0;
        gbc_lblJobmessage.gridy = 2;
        content.add(lblJobmessage, gbc_lblJobmessage);

        btnCancelbutton = new JButton();

        GridBagConstraints gbc_btnCancelbutton = new GridBagConstraints();
        gbc_btnCancelbutton.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancelbutton.gridx = 1;
        gbc_btnCancelbutton.gridy = 1;

        btnCancelbutton.setIcon(
                IconManager.getIcon(this, "icons/emiricons/32/cancel.png", IconManager.preferedSmallIconSize()));

        content.add(btnCancelbutton, gbc_btnCancelbutton);

        panel = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(1, 0, 0, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 0;
        gbc_panel2.gridy = 1;
        add(panel, gbc_panel2);
        GridBagLayout gbl_panel2 = new GridBagLayout();
        gbl_panel2.columnWidths = new int[] { 0 };
        gbl_panel2.rowHeights = new int[] { 0 };
        gbl_panel2.columnWeights = new double[] { Double.MIN_VALUE };
        gbl_panel2.rowWeights = new double[] { Double.MIN_VALUE };
        panel.setLayout(gbl_panel2);

        btnCancelbutton.addActionListener(evt -> jobData.requestCancel());

        cancelSubscription = jobData.subscribeCancelRequest(req -> btnCancelbutton.setEnabled(req == false));

    }

    public void dispose() {
        if (cancelSubscription != null) {
            cancelSubscription.dispose();
        }
    }

    @Override
    public void setProgress(float percent) {

        this.progress = percent;
        progressBar.setValue((int) percent);

    }

    @Override
    public void setMessage(String msg) {
        lblJobmessage.setText(msg);

    }

    @Override
    public String getMessage() {
        return lblJobmessage.getText();
    }

    @Override
    public float getProgress() {
        return this.progress;
    }

}
