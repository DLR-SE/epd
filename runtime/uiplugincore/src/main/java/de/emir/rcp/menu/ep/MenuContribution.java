package de.emir.rcp.menu.ep;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.menu.AbstractDynamicMenuProvider;
import de.emir.rcp.menu.ep.util.MenuEntryCounter;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class MenuContribution implements IMenuContribution {

    private Logger log = ULog.getLogger(MenuContribution.class.getName());

    private Map<String, MenuEntry> entries = new HashMap<>();

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
     * @see de.emir.rcp.menu.ep.IMenuContribution#separator(java.lang.String)
     */
    @Override
    public ISeparator separator(String id) {

        if (id.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        if (entries.containsKey(id) == true) {

            log.error("A menu entry with id [" + id + "] already exists.");
            return null;
        }

        log.debug("Separator with id [" + id + "] added.");

        Separator result = new Separator();

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();

        result.setPlugin(plugin);

        put(id, result);

        log.debug("Separator with id [" + id + "] added.");

        return result;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuContribution#menu(java.lang.String, java.lang.String)
     */
    @Override
    public IMenu menu(String id, String label) {

        if (id.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        if (entries.containsKey(id) == true) {

            ((Menu) entries.get(id)).setLabel(label);
            return (IMenu) entries.get(id);
        }

        Menu result = new Menu(label);

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();

        result.setPlugin(plugin);

        put(id, result);

        log.debug("Menu with id [" + id + "] added.");

        return result;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuContribution#menuItem(java.lang.String, de.emir.rcp.commands.ep.Command)
     */
    @Override
    public IMenuItem menuItem(String id, ICommandDescriptor cmd) {
        return menuItem(id, cmd.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuContribution#menuItem(java.lang.String, java.lang.String)
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

        put(id, result);

        log.debug("MenuItem with id [" + id + "] added.");

        return result;

    }

    public Map<String, MenuEntry> getEntries() {
        return entries;
    }

    @Override
    public IMenu menu(String id) {
        return menu(id, null);
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

}
