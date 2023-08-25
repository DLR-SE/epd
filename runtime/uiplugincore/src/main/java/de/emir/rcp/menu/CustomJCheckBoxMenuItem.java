package de.emir.rcp.menu;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JToolTip;

import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CustomJCheckBoxMenuItem extends JCheckBoxMenuItem implements Observer {

    private static final long serialVersionUID = 2585026035921249705L;

    private String commandID;
    private AbstractCheckableCommand command;

    private Boolean showDev;
    private String fullPath;
    private AbstractUIPlugin provider;

    private String tooltip;

    public CustomJCheckBoxMenuItem(String label, ImageIcon icon, String tooltip, String fullPath,
            AbstractUIPlugin provider) {
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
                CustomJCheckBoxMenuItem.this.setCursor(Cursor.getDefaultCursor());

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CustomJCheckBoxMenuItem.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

        if (cmd instanceof AbstractCheckableCommand) {
            command = (AbstractCheckableCommand) cmd;

            setTooltip();

            command.addObserver(this);
            setEnabled(command != null && command.canExecute());
            if (command != null) {
                setSelected(command.isChecked());
                setVisible(command.isVisible());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        setEnabled(command.canExecute());
        setSelected(command.isChecked());
        setVisible(command.isVisible());
    }

    public String getCommandID() {
        return commandID;
    }

}
