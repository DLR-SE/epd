package de.emir.rcp.ui.wizard;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @auhtor Johannes Donath
 */
public abstract class AbstractWizardPage extends JPanel implements IWizardPage {

    private static final long serialVersionUID = -795835947381725087L;

    /**
     * Stores the parent wizard instance.
     */
    private final AbstractWizard wizard;

    private AbstractWizardPage mNextPage; // for linear wizards

    /**
     * Constructs a new AbstractWizardPage.
     * 
     * @param wizard
     * @wbp.parser.constructor
     */
    public AbstractWizardPage(AbstractWizard wizard) {
        super();
        this.wizard = wizard;
    }

    /**
     * Constructs a new AbstractWizardPage.
     * 
     * @param wizard
     * @param layout
     * @param isDoubleBuffered
     */
    public AbstractWizardPage(AbstractWizard wizard, LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        this.wizard = wizard;
    }

    /**
     * Constructs a new AbstractWizardPage.
     * 
     * @param wizard
     * @param layout
     */
    public AbstractWizardPage(AbstractWizard wizard, LayoutManager layout) {
        super(layout);
        this.wizard = wizard;
    }

    /**
     * Constructs a new AbstractWizardPage.
     * 
     * @param wizard
     * @param isDoubleBuffered
     */
    public AbstractWizardPage(AbstractWizard wizard, boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.wizard = wizard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractWizardPage getNextPage() {
        return mNextPage;
    }

    public void setNextPage(AbstractWizardPage iWizardPage) {
        mNextPage = iWizardPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractWizard getWizard() {
        return this.wizard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelAvailable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinishAvailable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNextAvailable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPreviousAvailable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLeave() {
        // nothing to do
    }

    @Override
    public boolean onFinish() {
        return true;
    }
}
