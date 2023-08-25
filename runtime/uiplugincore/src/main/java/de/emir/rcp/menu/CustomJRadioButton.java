package de.emir.rcp.menu;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolTip;

import org.slf4j.Logger;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.AbstractRadioGroupCommand;
import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class CustomJRadioButton<T> extends JRadioButton implements Observer {

    private static Logger log = ULog.getLogger(CustomJRadioButton.class);

    private static final long serialVersionUID = -458966484880613739L;
    private AbstractRadioGroupCommand<T> command;
    private String commandID;

    private T value;

    private Boolean showDev;
    private String fullPath;
    private AbstractUIPlugin provider;

    private String tooltip;

    public CustomJRadioButton(String label, String iconPath, T value, String tooltip, String fullPath,
            AbstractUIPlugin provider) {
        super(label);

        this.fullPath = fullPath;
        this.provider = provider;
        this.value = value;
        this.tooltip = tooltip;

        if (iconPath != null) {
            ImageIcon icon = IconManager.getIcon(this, iconPath, IconManager.preferedSmallIconSize());

            if (icon != null) {
                setIcon(icon);
            }

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
                CustomJRadioButton.this.setCursor(Cursor.getDefaultCursor());

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CustomJRadioButton.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            }

        });
    }

    protected void executeCommand() {

        if (command != null && command.canExecute() == true) {
            command.execute(value);
        }

    }

    @SuppressWarnings("unchecked")
    public void setCommandID(String commandID) {

        if (this.command != null) {

            command.deleteObserver(this);

        }

        setEnabled(false);

        this.commandID = commandID;

        CommandManager cm = ServiceManager.get(CommandManager.class);
        AbstractCommand tmpCommand = cm.getCommand(commandID);

        if (tmpCommand == null) {

            log.error("Command with id [" + commandID + "] not found.");
            return;

        }

        if (tmpCommand instanceof AbstractRadioGroupCommand == false) {

            log.error("Command with id [" + commandID + "] has to be an instance of [" + AbstractRadioGroupCommand.class
                    + "].");
            return;
        }

        command = (AbstractRadioGroupCommand<T>) tmpCommand;
        command.addObserver(this);
        setTooltip();

        setEnabled(command.canExecute());
        setSelected(command.isSelected(value));

    }

    @Override
    public void update(Observable o, Object arg) {

        setEnabled(command.canExecute());
        setSelected(command.isSelected(value));
        setVisible(command.isVisible());
    }

    public String getCommandID() {
        return commandID;
    }

}
