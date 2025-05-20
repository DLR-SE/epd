package de.emir.rcp.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.HashBiMap;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * The command manager handles the execution of application commands. Therefore,
 * it references the command ExtensionPoint.
 * 
 * @author fklein
 *
 */
public class CommandManager implements IService {

	private static final Logger log = ULog.getLogger(CommandManager.class);

	private CommandExtensionPoint cmdEP = new CommandExtensionPoint();

	public HashBiMap<String, CommandDescriptor> getCommands() {
		return cmdEP.getCommandDescriptors();
	}
	
	public CommandDescriptor getCommandData(String commandID) {
		return cmdEP.getCommandDescriptors().get(commandID);
	}

	public CommandExtensionPoint getCommandExtensionPoint() {
		return cmdEP;
	}
	
	public CommandDescriptor getCommandDescriptor(AbstractCommand cmd) {
		return cmdEP.getCommandToDescriptorMap().get(cmd);
	}
	
	

	public AbstractCommand getCommand(String commandID) {
		
		CommandDescriptor descriptor = cmdEP.getCommandDescriptor(commandID);
		
		if(descriptor == null) {
			return null;
		}
		
		return descriptor.getCommand();
	}

	public boolean executeCommand(String commandID) {
		AbstractCommand cmd = getCommand(commandID);
		if (cmd == null) {
			log.warn("Execute Command [" + commandID + "]: Command not found.");
			return false;
		}

		return _executeCommand(cmd);
	}

	public boolean executeCommand(AbstractCommand cmd) {
		if (cmd == null)
			return false;
		return _executeCommand(cmd);
	}

	private boolean _executeCommand(AbstractCommand cmd) {
		
		try {
			if (cmd.canExecute() == true) {
				cmd.execute();
				return true;
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			log.error("Failed to execute Command: " + cmd);
		}
		return false;
	}
	
	/**
	 * Get all commands grouped by their providing plugin
	 * @return
	 */
	public Map<AbstractUIPlugin, List<CommandDescriptor>> getCommandsGroupedByPlugin() {
		
		HashBiMap<String, CommandDescriptor> commands = getCommands();
		
		Map<AbstractUIPlugin, List<CommandDescriptor>> result = new HashMap<>();
		
		for (CommandDescriptor desc : commands.values()) {
			
			List<CommandDescriptor> descList = result.get(desc.getProvider());
			
			if(descList == null) {
				descList = new ArrayList<>();
				result.put(desc.getProvider(), descList);
			}
			
			descList.add(desc);
			
		}
		
		return result;
	}

}
