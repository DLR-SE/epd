package de.emir.rcp.ui.utils.dialogs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import de.emir.rcp.manager.JobManager.JobData;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import io.reactivex.rxjava3.disposables.Disposable;

public class ProgressDialog implements IProgressMonitor {

    private JFrame parent;

    private boolean canceled = false;
    private volatile JDialog dlg;
    private JProgressBar progressBar;

    private volatile ActionListener cancelListener;
    private volatile ActionListener closeListener;
    private volatile JobData jobData;
    private volatile Disposable cancelSubscription;
    private volatile Timer timeout;
    private JButton cancelButton;

    public ProgressDialog(JFrame parent, JobData jd) {
        this.parent = parent;
        this.jobData = jd;
    }

    /**
     * @wbp.parser.entryPoint
     */
    private JDialog create() {
        boolean cancelable = jobData.job.isCancelable();
        boolean modal = jobData.job.isBlocking();
        String title = jobData.job.getTitle();

        dlg = new JDialog(parent, title, modal);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        dlg.getContentPane().setLayout(gridBagLayout);
        ImageIcon icon = IconManager.getIcon(this, "icons/emiricons/32/dynamic_feed.png", IconManager.preferedSmallIconSize());
        dlg.setIconImage(icon.getImage());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("");
        dlg.getContentPane().add(label, gbc);
        progressBar = new JProgressBar(0, 100);
        GridBagConstraints gbc_dpb = new GridBagConstraints();
        gbc_dpb.fill = GridBagConstraints.BOTH;
        gbc_dpb.insets = new Insets(5, 5, 5, 5);
        gbc_dpb.gridx = 0;
        gbc_dpb.gridy = 1;
        dlg.getContentPane().add(progressBar, gbc_dpb);
        ImageIcon icon2 = IconManager.getIcon(this, "icons/emiricons/32/cancel.png",
                IconManager.preferedSmallIconSize());

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.insets = new Insets(5, 5, 5, 5);
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        dlg.getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JButton btnRunInBackground = new JButton("Run in Background");
        GridBagConstraints gbc_btnRunInBackground = new GridBagConstraints();
        gbc_btnRunInBackground.insets = new Insets(0, 0, 0, 5);
        gbc_btnRunInBackground.gridx = 0;
        gbc_btnRunInBackground.gridy = 0;
        panel.add(btnRunInBackground, gbc_btnRunInBackground);

        cancelButton = new JButton("Cancel");
        GridBagConstraints gbc_cancelButton = new GridBagConstraints();
        gbc_cancelButton.gridx = 1;
        gbc_cancelButton.gridy = 0;
        panel.add(cancelButton, gbc_cancelButton);
        cancelButton.setIcon(icon2);

        cancelButton.setEnabled(cancelable);
        btnRunInBackground.setEnabled(!modal);

        cancelButton.addActionListener(e -> {
            cancelButton.setEnabled(false);
            canceled = true;
            if (cancelListener != null) {
                cancelListener.actionPerformed(e);
            }
        });

        btnRunInBackground.addActionListener(e -> {
            if (closeListener != null) {
                closeListener.actionPerformed(null);
            }
            close();
        });

        dlg.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dlg.setSize(309, 163);
        dlg.setLocationRelativeTo(parent);
        progressBar.setStringPainted(true);
        timeout = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Timed out, job is possibly dead
				close();
			}
        	
        });
        timeout.start();
        return dlg;
    }

    public void setCancelListener(ActionListener l) {
        this.cancelListener = l;
    }

    public void open() {
        create();
        cancelSubscription = jobData.subscribeCancelRequest(c -> cancelButton.setEnabled(c == false));
        dlg.setVisible(true);
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void close() {
    	if (timeout != null) {
    		timeout.stop();
    	}
    	dlg.setVisible(false);
        cancelSubscription.dispose();
        dlg.dispose(); // make sure we have no dead dialog lying around
    }

    public Component getDialog() {
        return dlg;
    }

    @Override
    public void setProgress(final float percent) {
        SwingUtilities.invokeLater(() -> {
        	timeout.restart();
        	progressBar.setValue((int) percent);
        });
    }

    @Override
    public void setMessage(final String msg) {
        SwingUtilities.invokeLater(() -> progressBar.setString(msg));
    }

    @Override
    public String getMessage() {
        return progressBar.getString();
    }

    @Override
    public float getProgress() {
        return progressBar.getValue();
    }

    public void setCloseListener(ActionListener l) {
        this.closeListener = l;

    }

}
