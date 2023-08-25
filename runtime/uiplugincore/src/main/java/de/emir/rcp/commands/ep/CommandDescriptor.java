package de.emir.rcp.commands.ep;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.runtime.plugin.AbstractUIPlugin;

public class CommandDescriptor implements ICommandDescriptor {

    private String id;
    private String label;

    private AbstractCommand command;
    private AbstractUIPlugin provider;

    public CommandDescriptor(String id, String label, AbstractCommand command, AbstractUIPlugin provider) {
        this.id = id;
        this.label = label;
        this.command = command;
        this.provider = provider;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public AbstractCommand getCommand() {
        return command;
    }

    public AbstractUIPlugin getProvider() {
        return provider;
    }

}
