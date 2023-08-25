package de.emir.rcp.menu;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CustomJButton extends JButton implements Observer {

    private static final long serialVersionUID = -458966484880613739L;
    private AbstractCommand command;

    private boolean showDev;
    private String fullPath;
    private AbstractUIPlugin provider;

    private String tooltip;

    public CustomJButton(String label, ImageIcon icon, String tooltip, String fullPath, AbstractUIPlugin provider) {
        super(label);

        this.fullPath = fullPath;
        this.provider = provider;
        this.tooltip = tooltip;

        if (icon != null) {
            setIcon(icon);
        }
        init();
        PropertyContext propContext = PropertyStore.getContext(Basic.DEV_PROP_CTX);
        IProperty<Boolean> property = propContext.getProperty(Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS, false);
        showDev = property.getValue();

        setTooltip();

    }

    private void setTooltip() {

        if (showDev == true && tooltip == null) {
            setToolTipText("");
        }

        if (tooltip != null) {

            setToolTipText(tooltip);
            return;

        }

        if (command == null) {
            return;
        }

        CommandManager mgr = ServiceManager.get(CommandManager.class);
        CommandDescriptor cmdDescriptor = mgr.getCommandDescriptor(command);

        if (cmdDescriptor == null) {
            return;
        }

        setToolTipText(cmdDescriptor.getLabel());

    }

    @Override
    public JToolTip createToolTip() {

        if (showDev == true) {
            return new CustomDevTooltip(fullPath, provider, command);
        } else {
            return super.createToolTip();
        }

    }

    private void init() {

        setEnabled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand();
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                CustomJButton.this.setCursor(Cursor.getDefaultCursor());

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CustomJButton.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            }

        });

    }

    protected void executeCommand() {

        if (command != null && command.canExecute() == true) {
            command.execute();
        }

    }

    public void setCommand(AbstractCommand cmd) {
        if (command != null)
            command.deleteObserver(this);

        command = cmd;

        setTooltip();

        if (cmd == null) {
            setEnabled(false);
            return;
        }

        setEnabled(command != null && command.canExecute());
        command.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        setEnabled(command.canExecute());
        setVisible(command.isVisible());
    }

}
