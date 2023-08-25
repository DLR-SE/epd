package de.emir.rcp.manager;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;

import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.IPropertyProvider;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.rcp.properties.provider.property.UStructuralFeaturePropertyProvider;
import de.emir.rcp.properties.ui.editors.AbstractPropertyEditor;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.pointer.PointerStrings;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class PropertyManager implements IExtensionPoint 
{

	private static final Logger		logger = ULog.getLogger(PropertyManager.class);
	
	private static PropertyManager 	theInstance = new PropertyManager();	
	
	public static PropertyManager getInstance() {
		return theInstance ;
	}
	
	
	private static class PEPStore {
		public final int						order;
		public final IPropertyEditorProvider	provider;
		
		public PEPStore(int o, IPropertyEditorProvider p) {
			order = o; provider = p;
		}
	}
	
	private ArrayList<IPropertyProvider>		mPropertyProvider = new ArrayList<>();
	private ArrayList<PEPStore>					mEditorProvider = new ArrayList<>();
	private Timer								mTimer = null;	
	private Set<AbstractPropertyEditor>			mEditorsToUpdate = new HashSet<>();
	
	public void register(IPropertyProvider provider){
		if (provider != null && !mPropertyProvider.contains(provider)){
			if (logger.isDebugEnabled()) logger.debug("Register Property Provider: " + provider);
			mPropertyProvider.add(provider);
		}
	}

	/**
	 * Registers a new property editor provider, with a default order. The default order is 10 - giving space for higher and lower prioritized providers.
	 * @see register(int, IPropertyEditorProvider) 
	 * @param provider provider to be registered
	 */
	public void register(IPropertyEditorProvider provider){
		register(10, provider);
	}
	
	/**
	 * registers a new property editor provider with a given order. 
	 * The order is used to sort the provider, thus a provider with higher order will be asked later 
	 * to create an property editor as a provider with low order. (Orders may all have the same value.)
	 * 
	 * @note use an high order if you provide some kind of backup-editor provider
	 * 
	 * @param order 	Specifies the order (weighting) in which the provider is to be requested for an editor.
	 * @param provider	The provider to provide a new IPropertyEditor instance
	 */
	public void register(int order, IPropertyEditorProvider provider){
		if (provider != null && !mEditorProvider.contains(provider)){
			if (logger.isDebugEnabled()) logger.debug("Register Property Editor Provider: " + provider);
			PEPStore pep = new PEPStore(order, provider);
			mEditorProvider.add(pep);
			Collections.sort(mEditorProvider, new Comparator<PEPStore>() {
				public int compare(PEPStore o1, PEPStore o2) {
					return Integer.compare(o1.order, o2.order);
				}
			});
		}
	}
	
	public void remove(IPropertyProvider provider){
		if (provider != null){
			if (logger.isDebugEnabled()) logger.debug("Remove Property Provider: " + provider);
			mPropertyProvider.remove(provider);
		}
	}
	
	public void remove(IPropertyEditorProvider provider){
		if (provider != null){
			if (logger.isDebugEnabled()) logger.debug("Remove Property Editor Provider: " + provider);
			mEditorProvider.remove(provider);
		}
	}
	
	public synchronized Map<String, IProperty> collectProperties(Object value) {
		Map<String, IProperty> out = new HashMap<String, IProperty>();
		for (IPropertyProvider pp : mPropertyProvider){
			try{
				pp.contributeProperties(value, out);
			}catch(Exception e){
				ULog.error("Failed to collect properties with PropertyProvider: " + pp + " Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return out;
	}
	
	public synchronized IPropertyEditor getFirstEditor(IProperty property) {
		for (PEPStore provider : mEditorProvider){
			IPropertyEditor editor = provider.provider.provide(property);
			if (editor != null){
				editor.setProperty(property);
				return editor;
			}
		}
		return null;
	}
	
	public IPropertyEditor getDefaultEditor(Pointer ptr) {
		IProperty property = createProperty(ptr);
		if (property != null)
			return getFirstEditor(property);
		return null;		
	}
	
	/**
	 * Creates a property for the given pointer
	 * @param ptr
	 * @return null, if the pointer is not valid
	 */
	public IProperty createProperty(Pointer ptr) {
		//just forward to the UStructuralFeaturePropertyProvider
		return UStructuralFeaturePropertyProvider.createProperty(ptr);
	}
	
	/**
	 * Creates a property for the given pointer
	 * @param ptr
	 * @return null, if the pointer is not valid
	 */
	public IProperty createProperty(UObject uobj, UStructuralFeature... features) {
		//just forward to the UStructuralFeaturePropertyProvider
		return UStructuralFeaturePropertyProvider.createProperty(PointerOperations.create(uobj, features));
	}
	
	/**
	 * Sorts the properties as follows: 
	 * - all attribute properties (e.g. String, int, float, ...)
	 * - all association properties (e.g. Coordinates, Pose, Units, ...)
	 * - all list properties
	 * 
	 * @param input
	 * @param output
	 */
	public void sort(Map<String, IProperty> input, List<IProperty> output) {
		output.addAll(input.values());
		Collections.sort(output, new Comparator<IProperty>() {
			@Override
			public int compare(IProperty o1, IProperty o2) {
				//sort by type 1) Attribute Properties, 2) AssociationProperties 3) ListProperties
				return Integer.compare(getPropertyType(o1), getPropertyType(o2));
			}

			private int getPropertyType(IProperty p) {
				if (p instanceof AttributeProperty)
					return 1;
				if (p instanceof AssociationProperty)
					return 2;
				if (p instanceof UCoreListProperty)
					return 3;
				return 5;
			}
		});
	}
	
	/**
	 * Utility function to get a property within an UObject
	 * @param uobj
	 * @param qualifiedPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static <T> IProperty<T> getOrCreateProperty(UObject uobj, String qualifiedPropertyName, T defaultValue) {
		IProperty<T> p = (IProperty<T>) uobj.getProperty(qualifiedPropertyName);
		if (p == null) {
			p = (IProperty<T>) uobj.addProperty(qualifiedPropertyName, qualifiedPropertyName, true);
			p.setValue(defaultValue);
		}
		return p;
	}
	
	/**
	 * Return directly the editor component, e.g. getDefaultEditor().getEditor()
	 * @param uobject the object that owns the property to be displayed
	 * @param pointerString pointer string (see PointerStrings) to the feature to be displayed
	 * @return
	 */
	public Component getEditorComponent(UObject uobject, String pointerString) {
		assert(uobject != null && pointerString != null && pointerString.isEmpty() == false);
		Pointer ptr = PointerStrings.create(uobject, pointerString);
		if (ptr == null) { if (logger.isDebugEnabled()) { logger.debug("Could not create a pointer for {} and {}", uobject, pointerString);} return null; }
		return getEditorComponent(ptr);
	}
	
	/**
	 * Return directly the editor component, e.g. getDefaultEditor().getEditor()
	 * @param uobject the object that owns the property to be displayed
	 * @param feature feature used to create the property
	 * @return
	 */
	public Component getEditorComponent(UObject uobject, UStructuralFeature feature) {
		assert(uobject != null && feature != null );
		Pointer ptr = PointerOperations.create(uobject, feature);
		if (ptr == null) { if (logger.isDebugEnabled()) { logger.debug("Could not create a pointer for {} and {}", uobject, feature);} return null; }
		return getEditorComponent(ptr);
	}
	
	/**
	 * Convenience method for getDefaultEditor(ptr).getEditor()
	 * @param ptr pointer to the object to be displayed / edited
	 * @return the editor component
	 */
	public Component getEditorComponent(Pointer ptr) {
		assert(ptr != null && ptr.isValid());
		IPropertyEditor edt = getDefaultEditor(ptr);
		if (edt == null) { if (logger.isDebugEnabled()) logger.debug("Could not find editor for {}", ptr); return null; }
		return edt.getEditor();
	}
	/**
	 * Convenience method for getDefaultEditor(ptr).getEditor()
	 * @param property property to be displayed / edited
	 * @return the editor component
	 */
	public Component getEditorComponent(IProperty property) {
		assert(property != null);
		IPropertyEditor edt = getFirstEditor(property);
		if (edt == null) { if (logger.isDebugEnabled()) logger.debug("Could not find editor for {}", property); return null; }
		return edt.getEditor();
	}
	
	
	/**
	 * An {@link AbstractPropertyEditor} registers itself for updates of its content. 
	 * @note This registration is done to do a batch update every X milliseconds, to not spam the UI - Thread with hundrets or thousends of update notification, 
	 * if the simulation mode is used. 
	 * 
	 * @param abstractPropertyEditor
	 */
	public void registerForUpdate(AbstractPropertyEditor abstractPropertyEditor) {
		if (mTimer == null) {
			mTimer = new Timer("PropertyManager", true);
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() { //do everything in the UI-thread						
						@Override
						public void run() {
							synchronized (mEditorsToUpdate) {
								Set<AbstractPropertyEditor> set = mEditorsToUpdate;
								mEditorsToUpdate = new HashSet<>();	
								for (AbstractPropertyEditor edt : set)
									edt.updateEditorValue();
							}						
						}
					});
					mTimer.cancel();
					mTimer.purge();
					mTimer = null;
				}
			}, 100); //maximum of 10 updates per second
		}
		
		synchronized (mEditorsToUpdate) {
			mEditorsToUpdate.add(abstractPropertyEditor);
			
		}
	}

	
}
