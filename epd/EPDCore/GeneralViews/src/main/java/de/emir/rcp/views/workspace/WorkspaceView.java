package de.emir.rcp.views.workspace;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.google.common.eventbus.Subscribe;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.events.RequestFileSelectionEvent;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.EventManager;
import de.emir.rcp.manager.MenuManager;
import de.emir.rcp.manager.events.ActiveEditorChangedEvent;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.rcp.views.workspace.events.WorkspaceChangedEvent;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class WorkspaceView extends AbstractView {

	public static final String PROPERTY_CONTEXT = "WorkspaceProperties";

	public static final String WORKSPACE_MENU = "Workspace.Popup";

	public static final String WORKSPACE_LOCATION_PROPERTY = "Workspace.Location";
	public static final String WORKSPACE_EXPANSION_PROPERTY = "Workspace.Expansion";

	public static final String UNIQUE_ID = "WorkspaceView";

	public static final String WORKSPACE_LOCATION_PROPERTY_DEFAULT = "workspace";
	
	public static final String POPUP_MENU_ID = Basic.POPUP_IDENTIFIER + "workspaceView";

	public static final String TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "workspaceView";

	public static final String LINK_SELECTION_WITH_EDITOR_PROPERTY = "Workspace.LinkSelectionWithEditor";

	private WorkspaceTreePanel mWorkspacePanel;


	//////////////////////// State Variables /////////////////////////////////
	private IProperty mWorkspaceProperty;
	private File mWorkspaceFolder = null;
	
	private IProperty linkWithEditorProperty = null;

	private PropertyChangeListener mWorkspacePropertyListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {

			setOrCreateWorkspaceFolder();

		}
	};
	
	private PropertyChangeListener mLinkSelectionWithEditorPropertyListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {

			focusActiveEditorElement();

		}
	};

	public WorkspaceView(String id) {
		super(id);
	}
	
	@Subscribe
	public void handleWorkspaceChangedEvent(WorkspaceChangedEvent e) {
		// TODO: SelectionManager? On delete the last selected file is not unset
		refresh();
	}
	
	@Subscribe
	public void handleRequestFileSelectionEvent(RequestFileSelectionEvent e) {
		
		// To wait for the updating process to complete. Ugly but works
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {

				while(mWorkspacePanel.isUpdating() == true) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						ULog.error(e);
					}
				}

				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						List<File> files = e.getFiles();
						mWorkspacePanel.setSelection(files);
						
					}
				});
				
			}
		});
		
		t.start();
	}
	
	@Subscribe
	public void handleActiveEditorChangedEvent(ActiveEditorChangedEvent e) {

		focusActiveEditorElement();

		
	}
	
	private void focusActiveEditorElement() {

		if((boolean)linkWithEditorProperty.getValue() == false) {
			return;
		}
		
		AbstractEditor editor = PlatformUtil.getEditorManager().getActiveEditor();

		if(editor == null) {
			return;
		}
		
		Path path = editor.getPath();

		if(path == null) {
			return;
		}
		
		mWorkspacePanel.setSelection(path.toFile());
		
	}

	public void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_refresh();
			}
		});
		
		
	}

	// need to be called from the UI - thread, if you are not sure whether you
	// are in the ui thread or not, use refresh()
	public void _refresh() {
		
		try {

			mWorkspacePanel.setRootLocation(mWorkspaceFolder.getAbsoluteFile(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onOpen() {

		setOrCreateWorkspaceFolder();
		
		mWorkspaceProperty.addPropertyChangeListener(mWorkspacePropertyListener);
		linkWithEditorProperty.addPropertyChangeListener(mLinkSelectionWithEditorPropertyListener);
	}

	private void setOrCreateWorkspaceFolder() {

		mWorkspaceFolder = new File((String) mWorkspaceProperty.getValue());

		if (mWorkspaceFolder.exists() == false) {

			mWorkspaceFolder.mkdirs();

		}

		try {
			
			mWorkspacePanel.setRootLocation(mWorkspaceFolder.getAbsoluteFile(), new IWorkspaceRefreshCallback() {
				
				@Override
				public void finished() {
					// Set expansion state
					PropertyContext propCont = PropertyStore.getContext(PROPERTY_CONTEXT);
					String expansionState = propCont.getValue(WORKSPACE_EXPANSION_PROPERTY, "");
					mWorkspacePanel.setExpansionState(expansionState);
					
				}
			});
			
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onClose() {

		mWorkspaceProperty.removePropertyChangeListener(mWorkspacePropertyListener);
		linkWithEditorProperty.removePropertyChangeListener(mLinkSelectionWithEditorPropertyListener);
	}

	@Override
	public Component createContent() {
		
		Container parent = new JPanel();
		
		parent.setLayout(new BorderLayout());
		
		JToolBar mToolBar = new JToolBar();

		parent.add(mToolBar, BorderLayout.NORTH);
		mToolBar.setMargin(new Insets(0, 0, 0, 0));
		
		mToolBar.setFloatable(false);
		
		parent.add(mWorkspacePanel = new WorkspaceTreePanel());

		PropertyContext propCont = PropertyStore.getContext(PROPERTY_CONTEXT);

		mWorkspaceProperty = propCont.getProperty(WORKSPACE_LOCATION_PROPERTY, WORKSPACE_LOCATION_PROPERTY_DEFAULT);
		linkWithEditorProperty = propCont.getProperty(LINK_SELECTION_WITH_EDITOR_PROPERTY, false);
		
		JPopupMenu popUpMenu = new JPopupMenu();
		mWorkspacePanel.setPopupMenu(popUpMenu);
		
		MenuManager mm = PlatformUtil.getMenuManager();
		mm.fillToolbar(mToolBar, TOOLBAR_ID);
		mm.fillPopup(popUpMenu, POPUP_MENU_ID);

		EventManager.UI.register(this);

		return parent;
	}
	
	@Override
	public void onShutdown() {
		
		PropertyContext propCont = PropertyStore.getContext(PROPERTY_CONTEXT);
		propCont.setValue(WORKSPACE_EXPANSION_PROPERTY, mWorkspacePanel.getExpansionState());
	}


}
