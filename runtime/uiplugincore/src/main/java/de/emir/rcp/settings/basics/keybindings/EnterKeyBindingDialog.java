package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventPostProcessor;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class EnterKeyBindingDialog extends JDialog {

    private static final long serialVersionUID = -3442961896733171592L;

    private JButton cancelBtn = new JButton("Cancel");
    private JLabel messageLbl;

    private KeyEventPostProcessor pp;

    private String validUnformatedKey = null;

    private JLabel keyLabel;

    private boolean closing;

    private PublishSubject<Optional<String>> doneSubject = PublishSubject.create();

    public EnterKeyBindingDialog(JFrame parent, AbstractKeyBinding kb) {
        super(parent, true);

        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();

        String cmdID = kb.getCommandID();

        CommandManager cm = ServiceManager.get(CommandManager.class);
        CommandDescriptor desc = cm.getCommandData(cmdID);

        messageLbl = new JLabel("Press Key(s) to set Shortcut for command: " + desc.getLabel());
        messageLbl.setFont(messageLbl.getFont().deriveFont(Font.PLAIN, 13));

        gridBagLayout.columnWeights = new double[] { 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        cancelBtn.setFont(cancelBtn.getFont().deriveFont(Font.PLAIN, 13));

        cancelBtn.addActionListener(a -> setVisible(false));

        getContentPane().setLayout(gridBagLayout);
        GridBagConstraints gbc_messageLbl = new GridBagConstraints();
        gbc_messageLbl.anchor = GridBagConstraints.WEST;
        gbc_messageLbl.insets = new Insets(10, 50, 0, 50);
        gbc_messageLbl.gridx = 0;
        gbc_messageLbl.gridy = 0;
        getContentPane().add(messageLbl, gbc_messageLbl);

        keyLabel = new JLabel(" ");
        keyLabel.setFont(keyLabel.getFont().deriveFont(Font.BOLD, 32));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(50, 50, 50, 50);
        gbc_label.gridx = 0;
        gbc_label.gridy = 1;
        getContentPane().add(keyLabel, gbc_label);
        GridBagConstraints gbc_cancelBtn = new GridBagConstraints();
        gbc_cancelBtn.fill = GridBagConstraints.HORIZONTAL;
        gbc_cancelBtn.anchor = GridBagConstraints.NORTH;
        gbc_cancelBtn.gridx = 0;
        gbc_cancelBtn.gridy = 2;
        getContentPane().add(cancelBtn, gbc_cancelBtn);
        pack();
        setLocationRelativeTo(null);

        createKeyEventProcessor();

        keyLabel.requestFocus();
        setOpacity(0.9f);
        setVisible(true);

    }

    public Disposable subscribeDone(Consumer<Optional<String>> c) {
        return doneSubject.subscribe(c);
    }

    private void createKeyEventProcessor() {

        pp = new KeyEventPostProcessor() {

            public boolean postProcessKeyEvent(KeyEvent e) {

                if (closing == true) {
                    return false;
                }

                KeyStroke ks = KeyStroke.getKeyStrokeForEvent(e);

                if (ks.getKeyEventType() == KeyEvent.KEY_PRESSED) {

                    if (ks.toString().contains("CONTROL") == false && ks.toString().contains("SHIFT") == false
                            && ks.toString().contains("ALT") == false) {

                        validUnformatedKey = PlatformUtil.getKeyBindingManager().getUnformatedStringFrom(ks);
                        keyLabel.setText(validUnformatedKey);

                    } else {

                        String s = ks.toString();
                        s = s.replaceAll("CONTROL", "");
                        s = s.replaceAll("SHIFT", "");
                        s = s.replaceAll("ALT", "");
                        s = s.replaceAll("pressed ", "");
                        s = s.toUpperCase().replaceAll(" ", "+");
                        keyLabel.setText(s);

                        validUnformatedKey = null;
                    }

                } else if (ks.getKeyEventType() == KeyEvent.KEY_RELEASED) {

                    if (validUnformatedKey != null) {

                        fadeAndClose();

                    } else {
                        keyLabel.setText(" ");
                    }

                }

                return true;
            }

        };

    }

    protected void fadeAndClose() {
        closing = true;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i < 15; i++) {

                    setOpacity(1 - 0.05f * i);

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        ULog.error(e);
                    }
                }

                setVisible(false);
                doneSubject.onNext(Optional.ofNullable(validUnformatedKey));

            }
        });

        t.start();
    }

    public String getSelectedKeys() {
        return validUnformatedKey;
    }

    @Override
    public void setVisible(boolean b) {

        if (b == true) {
            DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(pp);

        } else {

            DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(pp);

        }

        super.setVisible(b);
    }

    @Override
    public void dispose() {
        DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventPostProcessor(pp);
        super.dispose();
    }

}
