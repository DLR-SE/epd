package de.emir.rcp.menu.ep;

import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.menu.AbstractDynamicMenuProvider;

public interface IMenuContribution {

    ISeparator separator(String id);

    IMenu menu(String id);

    IMenu menu(String id, String label);

    IMenuItem menuItem(String id, ICommandDescriptor cmd);

    IMenuItem menuItem(String id, String commandID);

    IDynamicMenu dynamicMenu(String id, AbstractDynamicMenuProvider p);

    <T> IRadioGroup<T> radioGroup(String groupID, Class<? extends T> type, String commandID);

    <T> IRadioGroup<T> radioGroup(String id, Class<? extends T> type, ICommandDescriptor cmd);

}
