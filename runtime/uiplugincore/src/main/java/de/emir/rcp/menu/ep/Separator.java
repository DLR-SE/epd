package de.emir.rcp.menu.ep;

public class Separator extends MenuEntry implements ISeparator {

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.ISeparator#after(java.lang.String)
     */
    @Override
    public ISeparator after(String siblingID) {

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
     * @see de.emir.rcp.menu.ep.ISeparator#before(java.lang.String)
     */
    @Override
    public ISeparator before(String siblingID) {

        if (siblingID.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        before = siblingID;
        after = null;
        return this;
    }

}
