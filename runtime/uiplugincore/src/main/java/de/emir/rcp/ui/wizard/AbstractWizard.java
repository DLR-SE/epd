package de.emir.rcp.ui.wizard;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

/**
 * @auhtor Johannes Donath
 */
public abstract class AbstractWizard {

    /**
     * Stores the current wizard page.
     */
    protected AbstractWizardPage currentPage = null;

    /**
     * Stores the stack of wizard pages.
     */
    protected List<AbstractWizardPage> pageStack = new ArrayList<AbstractWizardPage>();

    private int currentPageIndex = -1;

    /**
     * Stores the wizard container.
     */
    protected GenericWizardContainer container = null;

    private Object mUserData;

    /**
     * Constructs a new Wizard.
     * 
     * @param container
     */
    public AbstractWizard(GenericWizardContainer container) {
        this.container = container;
        this.container.setWizard(this);
    }

    /**
     * Constructs a new Wizard.
     * 
     * @param title
     */
    public AbstractWizard(JFrame parent, String title) {
        this.container = new GenericWizardContainer(parent, title);
        this.container.setWizard(this);
    }

    /**
     * Returns the wizard container.
     * 
     * @return
     */
    public GenericWizardContainer getContainer() {
        return this.container;
    }

    /**
     * Initializes the wizard.
     * 
     * @param page
     */
    public void initialize(AbstractWizardPage page) {

        // set new page
        this.setPage(page);

        // call event callback
        this.container.onInitialize();
    }

    /**
     * Switches to the next page.
     */
    public void nextPage() {
        // cancel if no next page is available
        if (!this.currentPage.isNextAvailable())
            return;

        // get next page
        AbstractWizardPage newPage = this.currentPage.getNextPage();

        if (newPage == null) {
            return;
        }

        // set new page
        this.setPage(newPage);
    }

    /**
     * Internal callback for wizard cancellation.
     */
    public void onCancel() {
        this.currentPage.onLeave();
    }

    public void finish() {

        boolean pageFinish = this.currentPage.onFinish();

        if (pageFinish == false) {
            return;
        }

        onFinish();
    }

    /**
     * Internal callback for wizard finishing.
     */
    public abstract void onFinish();

    /**
     * Switches back to the previous page.
     */
    public void previousPage() {
        // cancel if no previous page is available
        if (!this.currentPage.isPreviousAvailable())
            return;

        // request button update if nothing is available in our stack
        if (onFirstPage()) {
            this.updateButtons();
            return;
        }

        // get previous page
        AbstractWizardPage page = pageStack.get(currentPageIndex - 1);

        // set new page
        this.setPage(page);
    }

    /**
     * Sets a new page.
     * 
     * @param page
     */
    public void setPage(AbstractWizardPage page) {
        if (this.currentPage != null) {
            // notify page
            this.currentPage.onLeave();

            // remove element
            this.container.getWizardPageContainer().remove(((Component) this.currentPage));
        }

        currentPageIndex = pageStack.indexOf(page);

        // set new page
        this.currentPage = page;

        // notify new page
        this.currentPage.onEnter();

        // append to container
        this.container.getWizardPageContainer().add(((Component) this.currentPage));
        this.container.getWizardPageContainer().validate();
        this.container.getWizardPageContainer().repaint();

        // update buttons
        this.updateButtons();
    }

    /**
     * Updates the state of all buttons.
     */
    public void updateButtons() {

        boolean cancelAvailable = false;
        boolean nextAvailable = false;
        boolean finishAvailable = false;
        boolean previousAvailable = false;

        if (this.currentPage != null) {
            cancelAvailable = this.currentPage.isCancelAvailable();
            nextAvailable = this.currentPage.isNextAvailable();
            finishAvailable = this.currentPage.isFinishAvailable();
            previousAvailable = onFirstPage() == false && this.currentPage.isPreviousAvailable();
        }

        this.container.setCancelAvailable(cancelAvailable);
        this.container.setNextAvailable(nextAvailable);
        this.container.setFinishAvailable(finishAvailable);
        this.container.setPreviousAvailable(previousAvailable);
    }

    private boolean onFirstPage() {
        return currentPageIndex < 1;
    }

    public abstract List<AbstractWizardPage> getInitialPages();

    public void initialize() {
        List<AbstractWizardPage> pages = getInitialPages();

        addPages(pages);

        initialize(pages.get(0));
    }

    public void setUserData(Object userdata) {
        mUserData = userdata;
    }

    public Object getUserData() {
        return mUserData;
    }

    public void addPages(AbstractWizardPage... pages) {

        List<AbstractWizardPage> list = Arrays.asList(pages);
        addPages(list);

    }

    public void removePages(AbstractWizardPage... pages) {

        List<AbstractWizardPage> list = Arrays.asList(pages);
        removePages(list);

    }

    public void addPages(List<AbstractWizardPage> pages) {

        if (pages == null) {
            return;
        }

        pageStack.addAll(pages);
        for (int i = 1; i < pageStack.size(); i++) {
            ((AbstractWizardPage) pageStack.get(i - 1)).setNextPage(pageStack.get(i));
        }
    }

    public void removePages(List<AbstractWizardPage> pages) {

        if (pages == null) {
            return;
        }

        pageStack.removeAll(pages);
        for (int i = 1; i < pageStack.size(); i++) {
            if (pageStack.get(i - 1) instanceof AbstractWizardPage)
                ((AbstractWizardPage) pageStack.get(i - 1)).setNextPage(pageStack.get(i));
        }
    }

    public void close() {
        this.container.dispose();
    }

}
