package de.emir.rcp.manager;

import de.emir.tuml.ucore.runtime.extension.IService;

/**
 * @author Florian
 *
 *         This manager stores command line arguments the application has been started with. Instantiated and registered
 *         by AbstractUIProduct
 */
public class ArgumentsManager implements IService {

    private String[] arguments;

    public ArgumentsManager(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Get all arguments this application has been started with
     * 
     * @return
     */
    public String[] getArguments() {
        return arguments;
    }

    /**
     * Checks whether a given argument exists
     * 
     * @param arg
     * @return
     */
    public boolean exists(String arg) {

        for (String string : arguments) {
            if (arg.equals(string)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Based on the assumption that an argument consists of key and value, the value is delivered for a given key (e.g.
     * "-port 5000") getArgumentValue("-port") -> returns 5000).
     * 
     * @param key
     * @return
     */
    public String getArgumentValue(String key) {

        for (int i = 0; i < arguments.length; i++) {

            if (key.equals(arguments[i]) && i < arguments.length - 1) {
                return arguments[i + 1];
            }

        }

        return null;

    }

}
