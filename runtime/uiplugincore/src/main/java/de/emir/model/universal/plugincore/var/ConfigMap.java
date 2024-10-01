package de.emir.model.universal.plugincore.var;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.var.ConfigPair;
import de.emir.model.universal.plugincore.var.ConfigPairSimple;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(parent = ConfigVariable.class)	
public interface ConfigMap extends ConfigVariable 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "map", associationType = AssociationType.PROPERTY)
	public List<ConfigPair> getMap();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "mapSimple", associationType = AssociationType.PROPERTY)
	public List<ConfigPairSimple> getMapSimple();
	/**
	 *	@generated 
	 */
	void put(final String key, final Object value);
	/**
	 *	@generated 
	 */
	void put(final ConfigPair pair);
	/**
	 *	@generated 
	 */
	void put(final ConfigPairSimple pair);
	/**
	 *	@generated 
	 */
	Object get(final String key);
	/**
	 *	@generated 
	 */
	void remove(final String key);
	
}
