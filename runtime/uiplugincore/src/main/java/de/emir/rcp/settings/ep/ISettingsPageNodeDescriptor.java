package de.emir.rcp.settings.ep;

import de.emir.rcp.settings.AbstractSettingsPage;

public interface ISettingsPageNodeDescriptor {

    ISettingsPageDescriptor page(String id, Class<? extends AbstractSettingsPage> pageClass);

    /**
     * This method only returns existing pages. No new page will be created
     * 
     * @param id
     * @return
     */
    ISettingsPageDescriptor page(String id);

}
