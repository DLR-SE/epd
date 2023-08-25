package de.emir.rcp.menu.ep;

public interface IRadioGroup<T> {

    IRadioGroup<T> before(String siblingID);

    IRadioGroup<T> after(String siblingID);

    IRadioGroup<T> option(String label, T value);

    IRadioGroup<T> option(String label, T value, String icon);

    IRadioGroup<T> option(String label, T value, String icon, String tooltip);

    IRadioGroup<T> option(String label, String tooltip, T value);

}
