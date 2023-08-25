package de.emir.rcp.properties.ui.editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

import de.emir.model.universal.io.IFile;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**
 * Creates a property editor for files, that can be configured for 
 * - open file
 * - save file
 * - open / save directory 
 * modes, as well as having valid extensions and initial values
 * 
 * Those configurations can be set either using API calls
 * - setMode(FileEditor.FILE_OPEN | .FILE_SAVE | .DIRECTORY_OPEN | .DIRECTORY_SAVE)
 * - setExtensions("*.jpg, *.png")
 * - setInitial(new File("C:/foo"))
 * 
 * or through annotations on data model features, see \ref DataModelAnnotations 
 * @author sschweigert
 *
 */
public class FileEditor extends AbstractPropertyEditor<IFile> {

	public static final String FILE_OPEN 		= "FILE_OPEN";
	public static final String FILE_SAVE 		= "FILE_SAVE";
	public static final String DIRECTORY_OPEN 	= "DIRECTORY_OPEN";
	public static final String DIRECTORY_SAVE 	= "DIRECTORY_SAVE";
	
	private String		mMode = null;
	private String		mExtensions = null;
	private boolean		mOverwrite = true;
	private File		mInitial = null;

	JPanel				mPanel = null;	
	JTextField			mField = null;
	JButton				mButton = null;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mPanel == null){
			mPanel = new JPanel();
			mPanel.setLayout(new BorderLayout());
			
			IFile file = getValue();
			String absFile = file != null ? file.getAbsolutePath() : "";
			mField = new JTextField(absFile, 30);
			
			
			
			mField.getDocument().addDocumentListener(new DocumentListener() {
				private Timer					mTimer = null;
				@Override
				public void removeUpdate(DocumentEvent e) { update(e); }				
				@Override
				public void insertUpdate(DocumentEvent e) { update(e); }
				@Override
				public void changedUpdate(DocumentEvent e) { update(e); }
				
				public void update(DocumentEvent e) {
					if (mTimer != null) {
						mTimer.cancel();
						mTimer.purge();
						mTimer = null;
					}
					mTimer = new Timer();
					
					mTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							try {
								setValue(e.getDocument().getText(0, e.getDocument().getLength()));
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
							mTimer = null;
						}
					}, 500);
				}
			});
			mField.setEnabled(getProperty().isEditable());
			
			mField.addFocusListener(new FocusAdapter() {
				String txt;
				@Override
				public void focusGained(FocusEvent e) {
					txt = mField.getText();
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					if (txt.equals(mField.getText()) == false && getProperty().isEditable()) //fire only if there is a change
						setValue(mField.getText());
				}
			});
			mField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (getProperty().isEditable())
						setValue(mField.getText());
				}
			});
			mField.setEnabled(getProperty().isEditable());
			
			
			//Autocompletion based on the file system
			installAutocompletion(mField);
			mPanel.add(mField, BorderLayout.CENTER);
			
			mButton = new JButton("...");
			mButton.addActionListener( al -> { showFileDialog(mField.getText()); });
			mPanel.add(mButton, BorderLayout.EAST);
			
			//try to get the values for Mode, extensions and initial from feature annotations
			if (getProperty() instanceof UCoreProperty) {
				Pointer ptr = ((UCoreProperty)getProperty()).getPointer();
				if (ptr != null) {
					UStructuralFeature feature = ptr.getPointedFeature();
					if (feature != null) {
						UAnnotation fileAnno = feature.getAnnotation("File");
						if (fileAnno != null) {
							if (mMode == null && fileAnno.getDetail("mode") != null) {
								mMode = fileAnno.getDetail("mode").getValue();
							}
							if (mExtensions == null && fileAnno.getDetail("extensions") != null)
								mExtensions = fileAnno.getDetail("extensions").getValue();
							if (mInitial == null && fileAnno.getDetail("initial") != null)
								mInitial = new File(fileAnno.getDetail("initial").getValue());
						}
					}
				}
			}
			//even if we do not have default values for all, we got a default value for the inital, that is "."
			if (mInitial == null) mInitial = new File(".");
		}
		return mPanel;
	}

	private void showFileDialog(String currentFile) {
		String init = currentFile;
		if (init == null || init.isEmpty())
			if (mInitial != null) 
				init = mInitial.getAbsolutePath();
		JFileChooser jfc = new JFileChooser(init);
		FileFilter filter = null;
		if (mExtensions != null) {
			String[] splitExt = mExtensions.split(",");
			for (int i = 0; i < splitExt.length; i++) {
				splitExt[i] = splitExt[i].replace("*.", "").trim();
			}
			filter = new FileFilter() {				
				@Override
				public String getDescription() {
					return "";
				}				
				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) return true;
					int idx = f.getName().lastIndexOf('.');
					String n = f.getName().substring(idx+1);
					for (int i = 0; i < splitExt.length; i++)
						if (splitExt[i].equals(n))
							return true;
					return false;
				}
			};
		}
		
		
		int res = JFileChooser.ERROR_OPTION;
		if (mMode == null || mMode.toLowerCase().equals(FILE_OPEN.toLowerCase())) {
			if (filter != null) jfc.setFileFilter(filter);			
			res = jfc.showOpenDialog(PlatformUtil.getWindowManager().getMainWindow());
		}else if (mMode.toLowerCase().equals(FILE_SAVE.toLowerCase())) {
			if (filter != null) jfc.setFileFilter(filter);
			res = jfc.showSaveDialog(PlatformUtil.getWindowManager().getMainWindow());
		}else if (mMode.toLowerCase().equals(DIRECTORY_OPEN.toLowerCase()) || mMode.equals(DIRECTORY_SAVE.toLowerCase())) {
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.setAcceptAllFileFilterUsed(false);
			res = jfc.showOpenDialog(PlatformUtil.getWindowManager().getMainWindow());
		}else
			throw new UnsupportedOperationException("Unsupported mode");
		if (res == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			if (f != null) {
				setValue(f.getAbsolutePath());
				mField.setText(f.getAbsolutePath());
			}
		}
	}
	
	
	protected void setValue(String text) {
		getValue().setAbsolutePath(text);
	}
	

	private void installAutocompletion(JTextField tf) {
		DefaultCompletionProvider provider = new DefaultCompletionProvider() {
			@Override
			protected List<Completion> getCompletionsImpl(JTextComponent comp) {
				ArrayList<Completion> out = new ArrayList<Completion>();
				
				String str = seg.toString();
				String alreadyAvailable = "";
				int lidx = str.lastIndexOf(File.separator);
				if (lidx >= 0) {
					alreadyAvailable = str.substring(lidx+1);
					str = str.substring(0, lidx+1);
				}
				File file = new File(str);
				String[] other = file.list();
				String alreadyEntered = getAlreadyEnteredText(comp);
				
				if (other != null && other.length > 0) {
					for (String sub : other) {
						File f = new File(str + File.separator + sub);
						if (f.isDirectory())
							out.add(new BasicCompletion(this, sub + File.separator));
						else
							out.add(new BasicCompletion(this, sub));
					}
				}
				
				Collections.sort(out);
				completions = out;
				return super.getCompletionsImpl(comp);
			}
		};
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(100);
		ac.setAutoCompleteSingleChoices(true);
		ac.setAutoActivationEnabled(true);
		ac.setShowDescWindow(false);
		ac.setParameterAssistanceEnabled(false);
		ac.install(tf);
	}

	
	/**
	 * Set the editor mode to one of the following
	 * 	- FILE_OPEN (default value)
	 * 	- FILE_SAVE
	 * 	- DIRECTORY_OPEN
	 * 	- DIRECTORY_SAVE
	 * @ref DataModelAnnotations
	 * @param mode
	 */
	public void setMode(final String mode) {
		mMode = mode;
	}
	/**
	 * Set the file extensions for save or loading dialog. If more than one extension is supported, they comma separated
	 * for example: setExtensions("*.jpg, *.png")
	 * @param ext
	 */
	public void setExtensions(final String ext) {
		mExtensions = ext;
	}
	
	public void setInitial(final String str) { setInitial(new File(str));}
	public void setInitial(final File file) { mInitial = file; }
	
	@Override
	public void dispose() {

	}

}
