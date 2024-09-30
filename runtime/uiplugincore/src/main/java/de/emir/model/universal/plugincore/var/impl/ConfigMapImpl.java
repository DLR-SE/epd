package de.emir.model.universal.plugincore.var.impl;

import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigMap;
import de.emir.model.universal.plugincore.var.ConfigPair;
import de.emir.model.universal.plugincore.var.ConfigPairSimple;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.Iterator;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigMap.class)
public class ConfigMapImpl extends ConfigVariableImpl implements ConfigMap  
{
	
	
	/**
	 *	@generated 
	 */
	private List<ConfigPair> mMap = null;
	/**
	 *	@generated 
	 */
	private List<ConfigPairSimple> mMapSimple = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigMapImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigMapImpl(final ConfigMap _copy) {
		super(_copy);
		mMap = _copy.getMap();
		mMapSimple = _copy.getMapSimple();
	}
    
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigMapImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, List<ConfigPair> _map, List<ConfigPairSimple> _mapSimple) {
		super(_documentation,_annotations,_package,_name);
		mMap = _map; 
		mMapSimple = _mapSimple; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigMap;
	}

	/**
	 *	@generated 
	 */
	public List<ConfigPairSimple> getMapSimple() {
		if (mMapSimple == null) {
			mMapSimple = ListUtils.<ConfigPairSimple>asList(this, VarPackage.theInstance.getConfigMap_mapSimple()); 
		}
		return mMapSimple;
	}
	
	/**
	 *	@generated_not
	 */
    @Override
	public List<ConfigPair> getMap() {
		if (mMap == null) {
			mMap = ListUtils.<ConfigPair>asList(this, VarPackage.theInstance.getConfigMap_map()); 
		}
		return mMap;
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public void put(final String key, final Object value)
	{
        if (value instanceof UObject) {
            ConfigPairImpl pair = new ConfigPairImpl(key, (ConfigVariable) value); 
            put(pair);
        } else {
            ConfigPairSimpleImpl pairSimple = new ConfigPairSimpleImpl(key, value); 
            put(pairSimple);
        }
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public void put(final ConfigPairSimple pair)
	{
        // make sure the key is not present before adding the new entry
        remove(pair.getKey());
		getMapSimple().add(pair);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public void put(final ConfigPair pair)
	{
		// make sure the key is not present before adding the new entry
        remove(pair.getKey());
		getMap().add(pair);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public ConfigVariable get(final String key)
	{
		for (ConfigPairSimple item : getMapSimple()) {
            if (item.getKey().equals(key)) return item.getValue();
        }
        for (ConfigPair item : getMap()) {
            if (item.getKey().equals(key)) return item.getValue();
        }
        return null;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void build()
	{
		//TODO: 
		// 
		//  * initializes the model element, e.g. create private member for reflection access
		//  
		throw new UnsupportedOperationException("build not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ConfigMapImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		"}";
	}

    /**
	 * @inheritDoc
	 * @generated_not
	 */
    public void remove(String key) {
        Iterator<ConfigPairSimple> it = getMapSimple().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equals(key)) {
                it.remove();
            }
        }
        Iterator<ConfigPair> it2 = getMap().iterator();
        while (it2.hasNext()) {
            if (it2.next().getKey().equals(key)) {
                it2.remove();
            }
        }
    }
}
