package de.emir.rcp.manager;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Logger;

import bibliothek.gui.dock.common.MultipleCDockableFactory;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.model.universal.plugincore.var.impl.ConfigMapImpl;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.editors.BasicEditorLayout;
import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.editors.ep.EditorExtensionPoint;
import de.emir.rcp.manager.events.ActiveEditorChangedEvent;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * Responsible for creating new editors. To do so, references the
 * EditorExtensionPoint
 * 
 * @author fklein
 *
 */
public class EditorManager implements IService{

	public static final String PROPERTY_CONTEXT = "EditorsProperties";

	public static final String EDITOR_ID_FILE_EXTENSION_PROPERTY = "FileExtMap";
	public static final String OPEN_FILES_EDITOR_MAP_PROPERTY = "OpenFilesEditorMap";

	private static Logger log = ULog.getLogger(EditorManager.class);

	private EditorExtensionPoint eEP = new EditorExtensionPoint();

	private BasicEditorFactory fileEditorFactory;

	private AbstractEditor activeEditor;

	private HashMap<String, AbstractEditor> openEditors = new HashMap<String, AbstractEditor>();

	private ConfigMapImpl editorFileExtMap;

	private ConfigMapImpl openFilesEditorIdMap;
	
	private PublishSubject<Optional<AbstractEditor>> activeEditorSubject = PublishSubject.create();

	public EditorExtensionPoint getEditorExtensionPoint() {
		return eEP;
	}

	public boolean isEditorOpen(File file) {
		return getOpenEditor(file) != null;
	}

	public AbstractEditor getOpenEditor(File file) {
		return openEditors.get(file.getAbsolutePath());
	}

	public Editor getEditor(File file) {

		// First we check if this file is already opened with a specific editor
		String idOfOpenEditorForFile = openFilesEditorIdMap.get(file.getAbsolutePath()) == null ? null : ((ConfigObject) openFilesEditorIdMap.get(file.getAbsolutePath())).getValue();

		Map<String, Editor> editors = eEP.getEditors();

		if (idOfOpenEditorForFile != null) {

            return editors.get(idOfOpenEditorForFile);
		}

		// Now we check if there is an editor type manually set for the files
		// extension
		String extension = FilenameUtils.getExtension(file.getName());
		String manualSetEditorID = editorFileExtMap.get(extension) == null ? null : ((ConfigObject) editorFileExtMap.get(extension)).getValue();

		if (manualSetEditorID != null) {
			Editor editor = editors.get(manualSetEditorID);
			if (editor != null) {
				return editor;
			}
		}

		// Last we check if there is a default editor for this files extension
        return eEP.getEditorForExtension(extension);
	}

	/**
	 * 
	 * @param f
	 * @return If an editor to open this file has been found and created
	 */
	public boolean openFile(File f) {

		MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();
		
		if (mw.hasEditorArea() == false) {

			mw.showError("Error", "The product layout has no editor area.");
			return false;
		}

		BasicEditorLayout layout = fileEditorFactory.create();
		layout.setPath(f.toPath());
		AbstractEditor editor = fileEditorFactory.readManually(layout);

		if (editor == null) {

			return false;

		}

		boolean visible = editor.isVisible();

		if (visible == false) {

			mw.getWorkingArea().show(editor);
		}
		editor.toFront();

		return true;
	}

	public boolean openFile(File f, String editorID) {

		openFilesEditorIdMap.put(f.getAbsolutePath(), editorID);
		return openFile(f);
	}

	@SuppressWarnings("unchecked")
	public void fillEditors() {

		fileEditorFactory = new BasicEditorFactory();
		PlatformUtil.getWindowManager().getMainWindow().getMainControl().addMultipleDockableFactory("BasicEditorFactory",
				fileEditorFactory);

		PropertyContext context = PropertyStore.getContext(PROPERTY_CONTEXT);
		IProperty<ConfigMapImpl> prop = context.getProperty(EDITOR_ID_FILE_EXTENSION_PROPERTY, new ConfigMapImpl());
		editorFileExtMap = prop.getValue();

		IProperty<ConfigMapImpl> prop2 = context.getProperty(OPEN_FILES_EDITOR_MAP_PROPERTY, new ConfigMapImpl());
		openFilesEditorIdMap = prop2.getValue();

	}

	public AbstractEditor getActiveEditor() {
		return activeEditor;
	}

	public void setActiveEditor(AbstractEditor editor) {

		if (activeEditor == editor) {
			return;
		}

		activeEditor = editor;
		EventManager.UI.post(new ActiveEditorChangedEvent(editor));
		log.trace("Active editor changed [" + editor + "]");
		activeEditorSubject.onNext(Optional.ofNullable(activeEditor));
	}
	
	public Disposable subscribeActiveEditor(Consumer<Optional<AbstractEditor>> c) {
		return activeEditorSubject.subscribe(c);
	}

	public void setTypeForFile(String editorID, String ext) {
		editorFileExtMap.put(ext, editorID);
	}

	public class BasicEditorFactory implements MultipleCDockableFactory<AbstractEditor, BasicEditorLayout> {

		private Logger log = ULog.getLogger(BasicEditorFactory.class);

		@Override
		public BasicEditorLayout write(AbstractEditor dockable) {
			return new BasicEditorLayout(dockable.getPath());
		}

		/**
		 * This method is only used when restoring the application state
		 */
		@Override
		public AbstractEditor read(BasicEditorLayout layout) {

			File file = layout.getPath().toFile();
			String filename = file.getName();

			Editor editor = PlatformUtil.getEditorManager().getEditor(file);

			if (editor == null) {
				log.trace("No editor found for file [" + filename + "]");
				return null;
			}

			Class<? extends AbstractEditor> editorClass = editor.getEditorClass();

			if (editorClass == null) {
				log.trace("No editor found for file [" + filename + "]");
				return null;
			}
			Constructor<? extends AbstractEditor> constructor;
			AbstractEditor result = null;

			try {

				constructor = editorClass.getConstructor(MultipleCDockableFactory.class);
				result = constructor.newInstance(this);
				result.setPath(layout.getPath());
				result.setID(editor.getId());

				ImageIcon icon = editor.getIcon();

				if (icon != null) {
					result.setTitleIcon(icon);
				}

				openEditors.put(file.getAbsolutePath(), result);
				openFilesEditorIdMap.put(file.getAbsolutePath(), editor.getId());

				result.init(result.getParentPanel());

				PlatformUtil.getKeyBindingManager().registerEditorKeyBindings(result);

			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {

				e.printStackTrace();
			}

			return result;
		}

		/**
		 * This method is used when an editor should be opened manually
		 * 
		 * @param layout
		 * @return
		 */
		public AbstractEditor readManually(BasicEditorLayout layout) {

			File file = layout.getPath().toFile();
			String filename = file.getName();

			AbstractEditor editorInstance = openEditors.get(file.getAbsolutePath());

			if (editorInstance != null) {
				return editorInstance;
			}

			Editor editor = PlatformUtil.getEditorManager().getEditor(file);

			if (editor == null) {
				log.trace("No editor found for file [" + filename + "]");
				return null;
			}

			Class<? extends AbstractEditor> editorClass = editor.getEditorClass();

			if (editorClass == null) {
				log.trace("No editor class found for editor [" + editor.getId() + "]");
				return null;
			}
			Constructor<? extends AbstractEditor> constructor;
			AbstractEditor result = null;

			try {

				constructor = editorClass.getConstructor(MultipleCDockableFactory.class);
				result = constructor.newInstance(this);
				result.setPath(layout.getPath());
				result.setID(editor.getId());

				ImageIcon icon = editor.getIcon();
				if (icon != null) {
					result.setTitleIcon(icon);
				}

				openEditors.put(file.getAbsolutePath(), result);
				openFilesEditorIdMap.put(file.getAbsolutePath(), editor.getId());

				result.init(result.getParentPanel());

				PlatformUtil.getKeyBindingManager().registerEditorKeyBindings(result);

			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {

				e.printStackTrace();
			}

			return result;
		}

		@Override
		public boolean match(AbstractEditor dockable, BasicEditorLayout layout) {
			return dockable.getPath().equals(layout.getPath());
		}

		@Override
		public BasicEditorLayout create() {
			return new BasicEditorLayout(null);
		}

	}

	public void removeEditor(AbstractEditor editorInstance) {

		File file = editorInstance.getPath().toFile();

		openEditors.remove(file.getAbsolutePath());
		openFilesEditorIdMap.remove(file.getAbsolutePath());

	}

	public boolean shutdown() {

		for (AbstractEditor editor : openEditors.values()) {
			if (editor.shutdown() == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isSomeEditorDirty() {

		for (AbstractEditor editor : openEditors.values()) {
			if (editor.isDirty() == true) {
				return true;
			}
		}

		return false;
	}
	
	public Editor getEditorData(String editorID) {	
		return eEP.getEditors().get(editorID);		
	}

	public Collection<AbstractEditor> getOpenEditors() {
		return Collections.unmodifiableCollection(openEditors.values());
	}

}
