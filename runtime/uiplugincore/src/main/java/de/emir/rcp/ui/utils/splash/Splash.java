package de.emir.rcp.ui.utils.splash;

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

import de.emir.tuml.ucore.runtime.resources.IconManager;

public class Splash extends JWindow {
    private int min = 0, max = 100;
    private boolean show = true;
    final JProgressBar progressBar = new JProgressBar(min, max);

    /**
     * @wbp.parser.constructor
     */
    public Splash(final String imgPath, final int max) {
        this(null, imgPath, max);
    }

    public Splash(Component parent, final String imgPath, final int max) {

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
        this.setSize(icon.getIconWidth(), icon.getIconHeight());
        contentPane.add(new JLabel(icon, JLabel.CENTER), BorderLayout.CENTER);
        contentPane.add(progressBar, BorderLayout.SOUTH);
        progressBar.setStringPainted(true);
        this.setLocationRelativeTo(parent);
        this.toFront();
        this.setVisible(true);

    }

    public void setProgress(final String msg, final int value) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(value);
            progressBar.setString(msg);
        });
    }
}
