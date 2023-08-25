package de.emir.rcp.ui.utils.dialogs;

public interface ITextValidator {

    /**
     * 
     * @param text
     *            the text to validate
     * @return null if text is valid, an error message otherwise
     */
    public String isValid(String text);
}
