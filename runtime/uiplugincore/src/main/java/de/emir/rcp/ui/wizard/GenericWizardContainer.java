package de.emir.rcp.ui.wizard;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import de.emir.rcp.properties.LocalPropertyStore;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.JProgressBarProgressMonitor;

/**
 * @auhtor Johannes Donath
 */
public class GenericWizardContainer extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 6827258078569241411L;

    /**
     * Defines the default minimal size.
     */
    public static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension(900, 550);

    /**
     * Defines the next button text.
     */
    public static final String STRING_NEXT = "Next";

    /**
     * Defines the finish button text.
     */
    public static final String STRING_FINISH = "Finish";

    public static final String STRING_PREVIOUS = "Back";

    /**
     * Stores the wizard button panel.
     */
    protected JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

    /**
     * Stores the wizard container.
     */
    protected JPanel container = new JPanel(new GridLayout(1, 1));

    /**
     * Stores the cancel button.
     */
    protected JButton cancelButton = new JButton("Cancel");

    /**
     * Indicates whether the next button has been transformed into a finish button.
     */
    protected boolean isFinishAvailable = false;

    /**
     * Stores the next button.
     */
    protected JButton nextButton = new JButton(STRING_NEXT);

    /**
     * Stores the previous button.
     */
    protected JButton previousButton = new JButton(STRING_PREVIOUS);

    /**
     * Stores the wizard instance.
     */
    protected AbstractWizard wizard = null;

    private boolean isNextAvailable;
    private final JSeparator separator = new JSeparator();
    private final JProgressBarProgressMonitor progressBarProgressMonitor = new JProgressBarProgressMonitor();
    
	private LocalPropertyStore mPropertyStore;

    /**
     * Constructs a new GenericWizardContainer.
     * 
     * @throws HeadlessException
     */
    public GenericWizardContainer(JFrame parent, String title) throws HeadlessException {
        super(parent, title);

        this.buildUI();
        // center
        this.setLocationRelativeTo(parent);
        this.hookEvents();
    }

    /**
     * Constructs the UI.
     */
    protected void buildUI() {
        // set minimal size
        this.setMinimumSize(DEFAULT_MINIMUM_SIZE);

        // set close operation
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[] { 1.0 };
        gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);
        // wire elements together
        GridBagConstraints gbc_container = new GridBagConstraints();
        gbc_container.insets = new Insets(0, 0, 5, 0);
        gbc_container.fill = GridBagConstraints.BOTH;
        gbc_container.gridy = 0;
        gbc_container.gridx = 0;
        getContentPane().add(this.container, gbc_container);

        GridBagConstraints gbc_progressBarProgressMonitor = new GridBagConstraints();
        gbc_progressBarProgressMonitor.fill = GridBagConstraints.HORIZONTAL;
        gbc_progressBarProgressMonitor.insets = new Insets(0, 5, 5, 5);
        gbc_progressBarProgressMonitor.gridx = 0;
        gbc_progressBarProgressMonitor.gridy = 1;
        getContentPane().add(progressBarProgressMonitor, gbc_progressBarProgressMonitor);

        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.ipady = 2;
        gbc_separator.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator.insets = new Insets(0, 0, 5, 0);
        gbc_separator.gridx = 0;
        gbc_separator.gridy = 2;
        getContentPane().add(separator, gbc_separator);
        GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
        gbc_buttonPanel.anchor = GridBagConstraints.EAST;
        gbc_buttonPanel.gridy = 3;
        gbc_buttonPanel.gridx = 0;
        getContentPane().add(this.buttonPanel, gbc_buttonPanel);

        this.buttonPanel.add(this.previousButton);
        this.buttonPanel.add(this.nextButton);
        this.buttonPanel.add(this.cancelButton);

        this.progressBarProgressMonitor.setVisible(false);
    }

    public JProgressBarProgressMonitor getProgressMonitor() {
        return progressBarProgressMonitor;
    }

    public AbstractWizard getWizard() {
        return this.wizard;
    }

    public Container getWizardPageContainer() {
        return this.container;
    }

    /**
     * Hooks all events.
     */
    protected void hookEvents() {
        this.cancelButton.addActionListener(new ActionListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                wizard.onCancel();
                dispose();
            }
        });

        this.nextButton.addActionListener(new ActionListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFinishAvailable) {
                    wizard.finish();

                } else
                    wizard.nextPage();
            }
        });

        this.previousButton.addActionListener(new ActionListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                wizard.previousPage();
            }
        });

        this.cancelButton.addContainerListener(new DynamicContainerSizeListener(this));
    }

    public void onInitialize() {
        this.setVisible(true);
    }

    public void setCancelAvailable(boolean b) {
        this.cancelButton.setEnabled(b);
    }

    public void setFinishAvailable(boolean b) {
        this.isFinishAvailable = b;

        if (b) {
            this.nextButton.setText(STRING_FINISH);
            this.nextButton.setEnabled(true);
        } else {
            this.nextButton.setText(STRING_NEXT);
            this.nextButton.setEnabled(this.isNextAvailable);
        }
    }

    public void setNextAvailable(boolean b) {
        this.nextButton.setEnabled(b);
        this.isNextAvailable = b;
    }

    public void setPreviousAvailable(boolean b) {
        this.previousButton.setEnabled(b);
    }

    public void setWizard(AbstractWizard wizard) {
        this.wizard = wizard;
    }

    /**
     * A utility class which automatically adjusts the container size.
     */
    public static class DynamicContainerSizeListener implements ContainerListener {

        /**
         * Stores the parent frame.
         */
        protected final JDialog frame;

        /**
         * Constructs a new DynamicContainerSizeListener.
         * 
         * @param genericWizardContainer
         */
        public DynamicContainerSizeListener(GenericWizardContainer genericWizardContainer) {
            this.frame = genericWizardContainer;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void componentAdded(ContainerEvent e) {
            // get window dimensions
            Dimension windowSize = this.frame.getSize();
            Dimension windowPreferredSize = this.frame.getPreferredSize();

            // create new dimension
            Dimension newSize = new Dimension();
            newSize.width = ((int) Math.max(windowPreferredSize.getWidth(), windowSize.getWidth()));
            newSize.height = ((int) Math.max(windowPreferredSize.getHeight(), windowSize.getHeight()));

            // update size
            this.frame.setMinimumSize(newSize);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void componentRemoved(ContainerEvent e) {
        }
    }

	public LocalPropertyStore getPropertyStore() {
		if (mPropertyStore == null) 
			mPropertyStore = new LocalPropertyStore();
		return mPropertyStore;
	}
}
