package de.emir.rcp.ui.wizard;

/**
 * @auhtor Johannes Donath
 */
public interface IWizardPage {

    /**
     * Returns the next page.
     * 
     * @return
     */
    public IWizardPage getNextPage();

    /**
     * Returns the current Wizard instance.
     * 
     * @return
     */
    public AbstractWizard getWizard();

    /**
     * Checks whether the wizard can be cancelled.
     * 
     * @return
     */
    public boolean isCancelAvailable();

    /**
     * Checks whether the wizard can be finished.
     * 
     * @return
     */
    public boolean isFinishAvailable();

    /**
     * Checks whether the wizard can be forwarded to the next page.
     * 
     * @return
     */
    public boolean isNextAvailable();

    /**
     * Checks whether the wizard can go back to the previous page.
     * 
     * @return
     */
    public boolean isPreviousAvailable();

    /**
     * Callback for activation.
     */
    public void onEnter();

    /**
     * Callback for de-activation.
     */
    public void onLeave();

    /**
     * Called on finish pressed
     */
    public boolean onFinish();

}
