package de.emir.rcp.ui.utils;

import java.awt.Graphics;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class JProgressBarProgressMonitor extends JProgressBar implements IProgressMonitor {

    private static final long serialVersionUID = -3431627509561628633L;
    private boolean internalVisible;

    public JProgressBarProgressMonitor() {

    }

    @Override
    public void setProgress(float percent) {
        SwingUtilities.invokeLater(() -> setValue((int) percent));
    }

    @Override
    public void setMessage(String msg) {

        SwingUtilities.invokeLater(() -> setString(msg));

    }

    @Override
    public String getMessage() {
        return getString();
    }

    @Override
    public float getProgress() {
        return getValue();
    }

    @Override
    public void setVisible(boolean aFlag) {
        internalVisible = aFlag;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (internalVisible == false && getParent() != null) {

            g.setColor(getParent().getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

        }

    }

}
