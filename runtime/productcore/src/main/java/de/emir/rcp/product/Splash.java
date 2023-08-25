package de.emir.rcp.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class Splash extends JWindow implements IProgressMonitor {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean show = true;
    final JProgressBar progressBar = new JProgressBar(0, 100);

    public Splash(final String imgPath) {
        this(null, imgPath);
    }

    public Splash(Component parent, final String imgPath) {

        this.setBackground(Color.WHITE);

        // Mausevent setzt bei Klick die Hilfsvariable show auf false, beendet
        // damit den Splashscreen und &ouml;ffnet das Hauptfenster, sofern es
        // geladen ist.
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                show = false;
                setVisible(false);
                dispose();
            }
        });

        // Splash-GUI
        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        ImageIcon icon = IconManager.getIcon(this, imgPath);
        this.setSize(icon.getIconWidth(), icon.getIconHeight() + 20);
        contentPane.add(new JLabel(icon, JLabel.CENTER), BorderLayout.CENTER);
        contentPane.add(progressBar, BorderLayout.SOUTH);
        progressBar.setStringPainted(true);
        this.setLocationRelativeTo(parent);
        this.toFront();
        this.setVisible(true);

    }

    @Override
    public void setProgress(final float percent) {
        SwingUtilities.invokeLater(() -> progressBar.setValue((int) percent));

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
}
