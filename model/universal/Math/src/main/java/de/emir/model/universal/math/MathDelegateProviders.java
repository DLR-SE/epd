package de.emir.model.universal.math;

import de.emir.tuml.ucore.runtime.UCorePlugin;

/**
 * @deprecated this class will be removed in future releases (no other class is required) 
 */
@Deprecated
public class MathDelegateProviders implements UCorePlugin
{
	/**
	 * @generated ID(DelegateProvider_register)
	 */
	public static void register(){
		//initialize the data model
	}
	
	/**
	 * @generated ID(DelegateProvider_initializePlugin)
	 */
	@Override
	public void initializePlugin(){
		//Method is called by plugin loader
		register();		
	}
}
