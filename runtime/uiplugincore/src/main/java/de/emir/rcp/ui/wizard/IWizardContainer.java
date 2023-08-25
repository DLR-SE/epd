package de.emir.rcp.ui.wizard;

import java.awt.Container;

import de.emir.rcp.ui.utils.JProgressBarProgressMonitor;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public interface IWizardContainer {

    /**
     * Returns the current Wizard instance.
     * 
     * @return
     */
    public AbstractWizard getWizard();

    /**
     * Returns the page container.
     * 
     * @return
     */
    public Container getWizardPageContainer();

    /**
     * Internal initialize callback.
     */
    public void onInitialize();

    /**
     * Changes the state of the cancel button.
     * 
     * @param b
     */
    public void setCancelAvailable(boolean b);

    /**
     * Changes the state of the finish button.
     * 
     * @param b
     */
    public void setFinishAvailable(boolean b);

    /**
     * Changes the state of the next button.
     * 
     * @param b
     */
    public void setNextAvailable(boolean b);

    /**
     * Changes the state of the previous button.
     * 
     * @param b
     */
    public void setPreviousAvailable(boolean b);

    /**
     * Sets the current Wizard instance.
     * 
     * @param wizard
     */
    public void setWizard(AbstractWizard wizard);

    public JProgressBarProgressMonitor getProgressMonitor();
}
