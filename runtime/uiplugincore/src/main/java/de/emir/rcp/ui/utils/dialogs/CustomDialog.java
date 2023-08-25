package de.emir.rcp.ui.utils.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class CustomDialog extends JDialog {

    public static int RESULT_OK = 1;
    public static int RESULT_CANCEL = 0;

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CustomDialog dialog = new CustomDialog(new GenericProperty<>("OK", "", true, true), "Foobar",
                    "SomeFoobar that does something", new JButton("Fara"), null);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int mResult = RESULT_CANCEL;

    /**
     * Create the dialog.
     */
    public CustomDialog(IProperty<Boolean> okEnabled, String title, String description, JComponent component,
            Image image) {
        super(selectParent());
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        if (title != null && title.isEmpty())
            setTitle(title);
        if (image != null)
            setIconImage(image);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            contentPanel.add(component, BorderLayout.CENTER);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                if (okEnabled != null) {
                    okEnabled.addPropertyChangeListener(pcl -> {
                        okButton.setEnabled((Boolean) pcl.getNewValue());
                    });
                }
                okButton.addActionListener(al -> {
                    mResult = RESULT_OK;
                    setVisible(false);
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(al -> {
                    mResult = RESULT_CANCEL;
                    setVisible(false);
                });
            }
        }
    }

    private static Frame selectParent() {
        JFrame f = PlatformUtil.getWindowManager().getActiveFrame();
        if (f == null)
            f = PlatformUtil.getWindowManager().getMainWindow();
        return f;
    }

    public int getResult() {
        return mResult;
    }

}
