package de.emir.rcp.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.MultipleCDockableFactory;
import bibliothek.gui.dock.common.action.CAction;
import bibliothek.gui.dock.common.event.CDockableLocationEvent;
import bibliothek.gui.dock.common.event.CDockableLocationListener;
import bibliothek.gui.dock.common.event.CVetoClosingEvent;
import bibliothek.gui.dock.common.event.CVetoClosingListener;
import de.emir.rcp.editors.transactions.EditorTransactionStack;
import de.emir.rcp.manager.EditorManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.IDirtyStateProvider;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.resources.IconManager;

/**
 * All editor implementations should extend this class.
 * 
 * @author fklein
 *
 */
public abstract class AbstractEditor extends DefaultMultipleCDockable implements IDirtyStateProvider {

	protected Path path;

	protected EditorTransactionStack transactionStack = new EditorTransactionStack(this);

	private String id;

	private Container parentPanel;

	private boolean isDirty;

	private String title;

	public AbstractEditor(MultipleCDockableFactory<?, ?> factory) {
		super(factory, (CAction[]) null);

		createBasicListeners();

		setRemoveOnClose(true);
		setCloseable(true);

		parentPanel = new JPanel();

		getContentPane().add(parentPanel);
		setFocusComponent(parentPanel);

	}

	public void setID(String id) {
		this.id = id;
	}

	private void createBasicListeners() {

		
		//Note (SoS) we do not want to deactivate an editor if we lost the focus, but if we activate or deactivate one (see below)
//		addFocusListener(new CFocusListener() {
//			@Override
//			public void focusLost(CDockable dockable) {
//				EditorManager.setActiveEditor(null);
//			}
//			@Override
//			public void focusGained(CDockable dockable) {
//				EditorManager.setActiveEditor(AbstractEditor.this);
//			}
//		});
		
		addCDockableLocationListener(event -> {
//				if (event.getOldShowing()) {
//					onDeactivated();
//					EditorManager.setActiveEditor(null);
//				}
			if (event.getNewShowing()) {
				PlatformUtil.getEditorManager().setActiveEditor(AbstractEditor.this);
				onActivated();
			}

		});

		addVetoClosingListener(new CVetoClosingListener() {

			@Override
			public void closing(CVetoClosingEvent event) {

				shutdown();

			}

			@Override
			public void closed(CVetoClosingEvent event) {

				EditorManager em = PlatformUtil.getEditorManager();
				
				AbstractEditor activeEditor = em.getActiveEditor();

				if (activeEditor == AbstractEditor.this) {

					// If this editor is currently focused, handle as unfocused
					em.setActiveEditor(null);

				}
				em.removeEditor(AbstractEditor.this);
				
				onClose();
			}
		});

	}
	
	protected void onClose() {
		
	}

	/**
	 * Called if the editor is activated, e.g. if the editor is shown in the editor pane
	 */
	protected void onActivated() {
		//Nothing to do here - for the moment
	}
	
	/**
	 * Called if the editor is deactivated, e.g. if the editor is no longer shown on the editor pane
	 */
	protected void onDeactivated() {
		//Nothing to do here - for the moment
	}
	
	public boolean shutdown() {

		if (isDirty == true) {

			MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

			File file = path.toFile();
			int rc = JOptionPane.showConfirmDialog(mw, "'" + file.getName() + "' has been modified. Save changes?",
					"Save Resource", JOptionPane.YES_NO_CANCEL_OPTION);

			if (rc == JOptionPane.YES_OPTION) {

				getTransactionStack().save();
			}

			if (rc == JOptionPane.CANCEL_OPTION) {
				return false;
			}

		}

		return true;

	}

	public void setPath(Path path) {
		this.path = path;
		setTitleText(path.toFile().getName());

	}

	public Path getPath() {
		return path;
	}

	public EditorTransactionStack getTransactionStack() {
		return transactionStack;
	}

	@Override
	public String toString() {
		return "Editor :" + path == null ? "null" : path.toFile().getName();
	}

	public String getID() {
		return id;
	}

	public Container getParentPanel() {
		return parentPanel;
	}

	public boolean isDirty() {
		return isDirty;
	}

	/**
	 * Set the editor dirty state. Set to true to activate the save function
	 * 
	 * @param isDirty
	 */
	public void setDirty(boolean isDirty) {

		boolean old = this.isDirty;

		this.isDirty = isDirty;

		if (old != isDirty) {
			super.setTitleText((isDirty ? "*" : "") + title);

		}
	}

	/**
	 * Save the current editor state
	 * 
	 * @return
	 */
	@Override
	public abstract boolean save();
	
	public void reload() {
		
	}

	/**
	 * Build your UI Elements and bind them to your model within this method.
	 * 
	 * @param parent
	 */
	public abstract void createContent(Container parent);

	@Override
	public void setTitleText(String text) {
		this.title = text;
		super.setTitleText(text);
	}

	/**
	 * Initialization process. For internal use only. Checks if the file to be
	 * represented exists and displays an error message instead of
	 * creatingContent().
	 * 
	 * @param parent
	 */
	public final void init(Container parent) {

		File file = path.toFile();

		if (file.exists() == false) {

			parent.setLayout(new BorderLayout());

			JPanel p = new JPanel();
			p.setBackground(UIManager.getColor("EditorPane.background"));

			parent.add(p, BorderLayout.CENTER);

			ImageIcon icon = IconManager.getIcon(this, "icons/emiricons/32/dangerous.png");
			GridBagLayout gbl_p = new GridBagLayout();

			gbl_p.columnWeights = new double[] { 0.0 };
			gbl_p.rowWeights = new double[] { 1.0 };
			p.setLayout(gbl_p);

			JLabel errorLabel = new JLabel("<html>File does not exist.<br/>" + file.getAbsolutePath() + "</html>");

			errorLabel.setIcon(icon);
			errorLabel.setVerticalAlignment(SwingConstants.TOP);
			errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorLabel.setForeground(UIManager.getColor("EditorPane.foreground"));
			GridBagConstraints gbc_errorLabel = new GridBagConstraints();
			gbc_errorLabel.fill = GridBagConstraints.BOTH;
			gbc_errorLabel.insets = new Insets(20, 20, 20, 20);
			gbc_errorLabel.gridx = 0;
			gbc_errorLabel.gridy = 0;
			p.add(errorLabel, gbc_errorLabel);
			return;
		}
		//
		if (file.isFile() == false) {

			parent.setLayout(new BorderLayout());

			JPanel p = new JPanel();
			p.setBackground(UIManager.getColor("EditorPane.background"));

			parent.add(p, BorderLayout.CENTER);

			ImageIcon icon = IconManager.getIcon(this, "icons/emiricons/32/dangerous.png");
			GridBagLayout gbl_p = new GridBagLayout();

			gbl_p.columnWeights = new double[] { 0.0 };
			gbl_p.rowWeights = new double[] { 1.0 };
			p.setLayout(gbl_p);

			JLabel errorLabel = new JLabel("<html>Not a valid file.<br/>" + file.getAbsolutePath() + "</html>");

			errorLabel.setIcon(icon);
			errorLabel.setVerticalAlignment(SwingConstants.TOP);
			errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorLabel.setForeground(UIManager.getColor("EditorPane.foreground"));
			GridBagConstraints gbc_errorLabel = new GridBagConstraints();
			gbc_errorLabel.fill = GridBagConstraints.BOTH;
			gbc_errorLabel.insets = new Insets(20, 20, 20, 20);
			gbc_errorLabel.gridx = 0;
			gbc_errorLabel.gridy = 0;
			p.add(errorLabel, gbc_errorLabel);
			return;
		}

		createContent(parent);

	}

	public abstract Object getModel();
	
	public String getModelIdentifier() {
		if(path != null) {
			return path.toFile().getAbsolutePath();
		}
		
		return id;
	}
}