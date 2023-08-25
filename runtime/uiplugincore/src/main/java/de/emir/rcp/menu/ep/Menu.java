package de.emir.rcp.menu.ep;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.slf4j.Logger;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.menu.AbstractDynamicMenuProvider;
import de.emir.rcp.menu.ep.util.MenuEntryCounter;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class Menu extends MenuEntry implements IMenu {
	private static final Logger log = ULog.getLogger(Menu.class);

	private Map<String, MenuEntry> entries = new HashMap<>();

	private String label;

	private String tooltip;

	private String mIconPath;

	private ResourceManager mResourceManager;

	private int mIconSize = -1;

	public Menu(String label) {
		this.label = label;
	}

	// If no before/after information is set, the adding order should be used
	private void put(String id, MenuEntry entry) {

		int counter = MenuEntryCounter.counter;
		MenuEntryCounter.counter++;
		entry.setAddedIndex(counter);
		entries.put(id, entry);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.rcp.menu.ep.IMenu#separator(java.lang.String)
	 */
	@Override
	public ISeparator separator(String id) {

		if (entries.containsKey(id) == true) {

			log.error("A menu entry with id [" + id + "] already exists.");
			return null;
		}

		Separator result = new Separator();

		AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
		
		result.setPlugin(plugin);
		
		put(id, result);
		
		log.debug("Separator with id [" + id + "] added.");
		
		return result;

	}

	/* (non-Javadoc)
	 * @see de.emir.rcp.menu.ep.IMenu#menu(java.lang.String)
	 */
	@Override
	public IMenu menu(String id) {
		return menu(id, null);
	}
	

	/* (non-Javadoc)
	 * @see de.emir.rcp.menu.ep.IMenu#menu(java.lang.String, java.lang.String)
	 */
	@Override
	public IMenu menu(String id, String label) {
		return menu(id, label, null);
	}


	/* (non-Javadoc)
	 * @see de.emir.rcp.menu.ep.IMenu#menu(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IMenu menu(String id, String label, String tooltip) {

		if (id.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		if (entries.containsKey(id) == true) {

			((Menu) entries.get(id)).setLabel(label);
			return (IMenu) entries.get(id);
		}

		Menu result = new Menu(label);
		result.setTooltip(tooltip);
		
		AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
		
		result.setPlugin(plugin);
		
		put(id, result);
		

		log.debug("Menu with id [" + id + "] added.");
		
		return result;
	}

	@Override
	public IDynamicMenu dynamicMenu(String id, AbstractDynamicMenuProvider p) {

		if (id.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		if (entries.containsKey(id) == true) {
			return (IDynamicMenu) entries.get(id);
		}

		DynamicMenu result = new DynamicMenu(id, p);
		
		AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
		
		result.setPlugin(plugin);

		put(id, result);
		
		log.debug("Dynamic Menu with id [" + id + "] added.");
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.rcp.menu.ep.IMenu#menuItem(java.lang.String,
	 * de.emir.rcp.commands.ep.Command)
	 */
	@Override
	public IMenuItem menuItem(String id, ICommandDescriptor cmd) {
		return menuItem(id, cmd.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.rcp.menu.ep.IMenu#menuItem(java.lang.String, java.lang.String)
	 */
	@Override
	public IMenuItem menuItem(String id, String commandID) {

		if (id.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		if (entries.containsKey(id) == true) {

			log.error("A menu entry with id [" + id + "] already exists.");
			return null;
		}

		MenuItem result = new MenuItem(id, commandID);
		
		AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
		
		result.setPlugin(plugin);

		log.debug("MenuItem with id [" + id + "] added.");

		put(id, result);
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.rcp.menu.ep.IMenu#after(java.lang.String)
	 */
	@Override
	public IMenu after(String siblingID) {

		if (siblingID.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		after = siblingID;
		before = null;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.emir.rcp.menu.ep.IMenu#before(java.lang.String)
	 */
	@Override
	public IMenu before(String siblingID) {

		if (siblingID.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		before = siblingID;
		after = null;
		return this;
	}

	public Map<String, MenuEntry> getEntries() {
		return entries;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
	
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	public String getTooltip() {
		return tooltip;
	}

	@Override
	public <T> IRadioGroup<T> radioGroup(String id, Class<? extends T> type, ICommandDescriptor cmd) {

		return radioGroup(id, type, ((CommandDescriptor) cmd).getId());

	}

	@Override
	public <T> IRadioGroup<T> radioGroup(String id, Class<? extends T> type, String commandID) {

		if (id.contains(".")) {
			throw new IllegalArgumentException("Menu paths must not contain a dot");
		}

		if (entries.containsKey(id) == true) {

			log.error("A button group with id [" + id + "] already exists.");
			return null;
		}


		RadioGroup<T> bg = new RadioGroup<T>(id, commandID);
		
		AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
		
		bg.setPlugin(plugin);

		put(id, bg);
		

		log.debug("ButtonGroup with id [" + id + "] added.");
		
		return bg;

	}

	@Override
	public IMenu icon(String path) {
		return icon(path, ResourceManager.get(getPlugin()));
	}

	@Override
	public IMenu icon(String path, ResourceManager rmgr) {
		mResourceManager = rmgr;
		this.mIconPath = path;
		return this;
	}

	@Override
	public IMenu iconSize(int size) {
		mIconSize = size;
		return this;
	}

	public ImageIcon getIcon() {
		if (mIconPath != null && !mIconPath.isEmpty()) {
			URL iconURL = mResourceManager != null ? mResourceManager.getResource(mIconPath)
					: ResourceManager.get(getClass()).getResource(mIconPath);
			if (iconURL != null) {
				int iconSize = mIconSize < 0 ? IconManager.preferedSmallIconSize() : mIconSize;
				return IconManager.getIcon(iconURL, iconSize);
			}
		}
		return null;
	}

}
