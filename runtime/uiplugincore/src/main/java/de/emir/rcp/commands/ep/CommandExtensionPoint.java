package de.emir.rcp.commands.ep;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.HashBiMap;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class CommandExtensionPoint implements IExtensionPoint {

    private Logger log = ULog.getLogger(CommandExtensionPoint.class);

    /**
     * List of commands that have been registered.
     * 
     * @see registerListener / removeListener to get notified about new commands
     */
    private HashBiMap<String, CommandDescriptor> commandMap = HashBiMap.create();
    private Map<AbstractCommand, CommandDescriptor> commandToDescriptorMap = new HashMap<>();

    /**
     * Adds a new command. Commands are used to execute logic initiated by the user. By encapsulating this logic, it is
     * possible to link both menu entries and key bindings to the same execution. However, a command can also be
     * executed programmatically.
     * 
     * @param id
     *            A unique ID, identifies this command
     * @param label
     *            A label. Used e.g. for display in the KeyBinding Settings
     * @param handler
     *            The handler class to be executed
     * @return A representation of the command within the ExtensionPoint
     */
    public ICommandDescriptor command(String id, String label, AbstractCommand command) {

        if (commandMap.containsKey(id) == true) {

            log.error("A command with id [" + id + "] already exists.");
            return null;
        }

        if (command == null) {

            log.error("Command cannot be null");
            return null;

        }

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();

        CommandDescriptor cmdDescriptor = new CommandDescriptor(id, label, command, plugin);
        commandMap.put(id, cmdDescriptor);
        commandToDescriptorMap.put(command, cmdDescriptor);
        log.debug("Command with id [" + id + "] added.");

        return cmdDescriptor;

    }

    public Map<AbstractCommand, CommandDescriptor> getCommandToDescriptorMap() {
        return commandToDescriptorMap;
    }

    /**
     * @return a map of all registered commands
     */
    public HashBiMap<String, CommandDescriptor> getCommandDescriptors() {
        return commandMap;
    }

    public CommandDescriptor getCommandDescriptor(String commandID) {
        return commandMap.get(commandID);
    }

}
