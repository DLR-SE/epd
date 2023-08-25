package de.emir.rcp.product.ui;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeelManager {
    public static void init(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    public static LookAndFeel getDefault() {
        try {
            String className = UIManager.getSystemLookAndFeelClassName();

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (className.equals(info.getName())) {
                    return UIManager.createLookAndFeel(className);
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    	return UIManager.getLookAndFeel();
    }
}
