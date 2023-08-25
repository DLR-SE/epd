package de.emir.rcp.settings.ep;

import java.util.ArrayList;
import java.util.List;

import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.settings.basics.EmptySettingsPage;
import de.emir.runtime.plugin.AbstractUIPlugin;

public abstract class SettingsPageNodeDescriptor implements ISettingsPageNodeDescriptor {

    private List<SettingsPageDescriptor> pages = new ArrayList<>();

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.settings.ep.ISettingsPageNode#page(java.lang.String, java.lang.Class)
     */
    @Override
    public ISettingsPageDescriptor page(String id, Class<? extends AbstractSettingsPage> pageClass) {

        ISettingsPageDescriptor page = getPage(id);

        if (page != null) {
            page.pageClass(pageClass);
            return page;
        }

        SettingsPageDescriptor sp = new SettingsPageDescriptor(id, pageClass);

        AbstractUIPlugin plugin = ExtensionPointUtil.getCurrentlyLoadingPlugin();
        sp.setPlugin(plugin);

        pages.add(sp);
        return sp;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.settings.ep.ISettingsPageNode#page(java.lang.String)
     */
    @Override
    public ISettingsPageDescriptor page(String id) {

        ISettingsPageDescriptor page = getPage(id);

        if (page == null) {
            return page(id, EmptySettingsPage.class);
        }

        return page;
    }

    public List<SettingsPageDescriptor> getPages() {
        return pages;
    }

    private ISettingsPageDescriptor getPage(String id) {

        for (SettingsPageDescriptor settingsPage : pages) {
            if (settingsPage.getId().equals(id)) {
                return settingsPage;
            }
        }

        return null;
    }

}
