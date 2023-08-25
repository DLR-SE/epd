package de.emir.rcp.commands.ep;

import de.emir.rcp.commands.AbstractCommand;

public interface ICommandDescriptor {

    /**
     * 
     * @return the unique identifier of this command
     */
    public String getId();

    /**
     * returns an instance of the abstract command associated with this ICommand
     * 
     * @return
     */
    public AbstractCommand getCommand();

    /**
     * Get a label describing this command
     * 
     * @return
     */
    String getLabel();

}
