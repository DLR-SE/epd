package de.emir.tuml.ucore.runtime.adapter;

/**
 * An adapter maps the model in the backend to the needs of a plugin. The plugin does not need to know the specific data
 * structure of the model and can therefore be easily reused.
 * 
 * @author Florian
 *
 */
public interface IAdapter {

    public String getId();

    public String getName();

}
