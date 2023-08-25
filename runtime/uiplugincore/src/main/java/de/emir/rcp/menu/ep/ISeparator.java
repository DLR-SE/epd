package de.emir.rcp.menu.ep;

public interface ISeparator {

    ISeparator after(String siblingID);

    ISeparator before(String siblingID);

}
