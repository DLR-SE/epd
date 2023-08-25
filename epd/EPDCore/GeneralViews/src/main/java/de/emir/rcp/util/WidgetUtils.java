package de.emir.rcp.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.AbstractCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class WidgetUtils {

	/**
	 * Creates a named panel, similar to the SWT Group, by creating a Title Border around an panel
	 * @param title
	 * @return a new JPanel, that contains a TitleBorder
	 */
	public static JPanel createGroup(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new TitledBorder(new LineBorder(Color.GRAY), title));
		return panel;
	}
	
	
	
	/**
	 * Adds an label and an editor to a given panel
	 * @param panel target panel
	 * @param label text for the label, e.g. the name of the editor field
	 * @param gcLabel GridbagConstraints for the the label - gridy will be increased by 1
	 * @param field the field that should be displayed, uses the property manager to get an editor
	 * @param gcEditor gridbagconstraints for the editor - gridy will be increased by 1
	 * 
	 * @pre requires the panel to have an GridbagLayout assigned
	 * @note if no editor could be created nothing will be added
	 * @post gcLabel, gcEditor.gridy++;
	 */
	public static void addEditor(JPanel panel, String label, GridBagConstraints gcLabel, Pointer field, GridBagConstraints gcEditor) {
		IPropertyEditor edt = PropertyManager.getInstance().getDefaultEditor(field);
		if (edt != null){
			Component comp = edt.getEditor();
			if (comp != null){
				JLabel lbl = new JLabel(label);
				panel.add(lbl, gcLabel); 
				gcLabel.gridy++;
				
				panel.add(comp, gcEditor);
				gcEditor.gridy++;
			}	
		}		
	}
	
	
	/**
	 * Adds an label and an editor to a given panel
	 * @param panel target panel
	 * @param label text for the label, e.g. the name of the editor field
	 * @param gcLabel GridbagConstraints for the the label - gridy will be increased by 1
	 * @param field the field that should be displayed, uses the property manager to get an editor
	 * @param gcEditor gridbagconstraints for the editor - gridy will be increased by 1
	 * 
	 * @pre requires the panel to have an GridbagLayout assigned
	 * @note if no editor could be created nothing will be added
	 * @post gcLabel, gcEditor.gridy++;
	 */
	public static void addEditor(JPanel panel, String label, GridBagConstraints gcLabel, IProperty field, GridBagConstraints gcEditor) {
		IPropertyEditor edt = PropertyManager.getInstance().getFirstEditor(field);
		if (edt != null){
			Component comp = edt.getEditor();
			if (comp != null){
				JLabel lbl = new JLabel(label);
				panel.add(lbl, gcLabel); 
				gcLabel.gridy++;
				
				panel.add(comp, gcEditor);
				gcEditor.gridy++;
			}	
		}		
	}
	
	
	/**
	 * Adds an label and an editor to a given panel
	 * @param panel target panel
	 * @param label text for the label, e.g. the name of the editor field
	 * @param gcLabel GridbagConstraints for the the label - gridy will be increased by 1
	 * @param edt the field that should be displayed, uses the property manager to get an editor
	 * @param gcEditor gridbagconstraints for the editor - gridy will be increased by 1
	 * 
	 * @pre requires the panel to have an GridbagLayout assigned
	 * @note if no editor could be created nothing will be added
	 * @post gcLabel, gcEditor.gridy++;
	 */
	public static void addEditor(JPanel panel, String label, GridBagConstraints gcLabel, IPropertyEditor edt, GridBagConstraints gcEditor) {
		if (edt != null){
			Component comp = edt.getEditor();
			if (comp != null){
				JLabel lbl = new JLabel(label);
				panel.add(lbl, gcLabel); 
				gcLabel.gridy++;
				
				panel.add(comp, gcEditor);
				gcEditor.gridy++;
			}	
		}		
	}


	
	/**
	 * Adds a subPanel to the parent panel and uses 2 columns for this panel, e.g. gcL.gridwidth = 2
	 * @note uses the fill mechanism from gcE
	 * 
	 * @param parent
	 * @param subPanel
	 * @param gcL
	 * @param gcE
	 * 
	 * @pre requires the panel to have an GridbagLayout assigned
	 * @note if no editor could be created nothing will be added
	 * @post gcL, gcE.gridy++;
	 */
	public static void addSubPanel(JPanel parent, Component subPanel, GridBagConstraints gcL, GridBagConstraints gcE) {
		gcL.gridwidth = 2;
		int oldFill = gcL.fill;
		gcL.fill = gcE.fill;
		parent.add(subPanel, gcL);
		gcL.fill = oldFill;
		gcL.gridwidth = 1;
		gcL.gridy++; gcE.gridy++;
	}
	
	
	
	/**
	 * Creates a panel with "Create..." Buttons to be create a new instance of an given object
	 * @param classes UClasses that can be created
	 * @param callback method that is invoked when the user presses one of the create buttons
	 * @return 
	 */
	public static JPanel createLazyLoadingPanel(Collection<UClassifier> classes, Function<UClass, Void> callback){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = gbc.gridy = 0;
		for (UClassifier cls : classes){
			if (cls instanceof UClass == false)
				continue;
			JButton createBtn = new JButton("Create " + cls.getName());
			createBtn.addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							callback.apply((UClass)cls);
						}
					});
				}
			});
			panel.add(createBtn, gbc); gbc.gridy++;
		}
		return panel;
	}
	
	
//	public static JPanel createSubPartEditor(String title, UClassifier classifier, Pointer ptr){
//		JPanel configPanel = WidgetUtils.createGroup(title);
//		configPanel.setLayout(new BorderLayout());
//		IFormEditorPart formProvider = FormEditorPartManager.provideEditorPart(classifier, true);
//		if (formProvider != null){
//			Component composite = formProvider.createComposite(ptr);
//			configPanel.add(composite, BorderLayout.CENTER);
//		}		
//		return configPanel;
//	}
	
	

	
	
	
	/**
	 * Interface to provide auto completion suggesstions for a given String
	 * @author sschweigert
	 *
	 */
	public static interface ICompletionProvider {
		List<String> provide(final String input);
	}
	private static class CompletionTask implements Runnable {
		private String completion;
		private int position;
		private JTextField textfield;

		CompletionTask(String completion, int position, JTextField tf) {
			this.completion = completion;
			this.position = position;
			this.textfield = tf;
		}

		@Override
		public void run() {
			textfield.setText(textfield.getText() + completion);
			textfield.setCaretPosition(position + completion.length());
			textfield.moveCaretPosition(position);
		}
	}
	public static JTextArea createAutoCompletionTextArea(String txt, final ICompletionProvider provider){
		 RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
      textArea.setCodeFoldingEnabled(true);
		CompletionProvider prov = new DefaultCompletionProvider() {
			
			@Override
			public List<Completion> getCompletionsAt(JTextComponent tc, Point p) {
				List<Completion> result = super.getCompletionsAt(tc, p);
				List<String> newStr = provider.provide(getAlreadyEnteredText(tc));
				for (String str : newStr) {
					result.add(new BasicCompletion(this, str));
				}
				return result;
			}
			
			@Override
			public List<Completion> getCompletionByInputText(String inputText) {
				List<Completion> result = super.getCompletionByInputText(inputText);
				List<String> newStr = provider.provide(inputText);
				for (String str : newStr) {
					result.add(new BasicCompletion(this, str));
				}
				return result;
			}
		};
		
		AutoCompletion ac = new AutoCompletion(prov);
		ac.install(textArea);
		return textArea;
	}
	public static JTextField createAutoCompletionTextField(String txt, final ICompletionProvider provider){
		JTextField tf = new JTextField(txt, 20);
		CompletionProvider prov = new DefaultCompletionProvider(new String[] {"Hello", "World"}) {
					
			
			@Override
			public List<Completion> getCompletionByInputText(String inputText) {
				List<Completion> result = super.getCompletionByInputText(inputText);
				if (result == null) result = new ArrayList<>();
				List<String> newStr = provider.provide(inputText);
				for (String str : newStr) {
					result.add(new BasicCompletion(this, str));
				}
				Collections.sort(result);
				return result;
			}
			
			
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			@SuppressWarnings("unchecked")
			protected List<Completion> getCompletionsImpl(JTextComponent comp) {

				List<Completion> retVal = new ArrayList<Completion>();
				String text = getAlreadyEnteredText(comp);

				if (text!=null) {
					completions = getCompletionByInputText(tf.getText());
					int index = Collections.binarySearch(completions, text, comparator);
					if (index<0) { // No exact match
						index = -index - 1;
					}
					else {
						// If there are several overloads for the function being
						// completed, Collections.binarySearch() will return the index
						// of one of those overloads, but we must return all of them,
						// so search backward until we find the first one.
						int pos = index - 1;
						while (pos>0 &&
								comparator.compare(completions.get(pos), text)==0) {
							retVal.add(completions.get(pos));
							pos--;
						}
					}

					while (index<completions.size()) {
						Completion c = completions.get(index);
						if (Util.startsWithIgnoreCase(c.getInputText(), text)) {
							retVal.add(c);
							index++;
						}
						else {
							break;
						}
					}

				}

				return retVal;

			}
		};
		
		AutoCompletion ac = new AutoCompletion(prov);
		ac.install(tf);
		return tf;
		
//		JTextField tf = new JTextField(20);
//		tf.getDocument().addDocumentListener(new DocumentListener() {
//			@Override
//			public void changedUpdate(DocumentEvent arg0) {}
//			@Override
//			public void insertUpdate(DocumentEvent ev) {
//				String completion;
//				int pos = ev.getOffset();
//				String content = null;
//				try {
//					content = tf.getText(0, pos + 1);
//				} catch (BadLocationException ex) {
//					ex.printStackTrace();
//				}
//
//				int w;
//				for (w = pos; w >= 0; w--) {
//				}
//				if (pos - w < 2) {
//					return;
//				}
//
//				String prefix = content.substring(w + 1);
//				List<String> treffer = provider.provide(prefix);
//				int n = Collections.binarySearch(treffer, prefix);
//				if (n < 0 && -n <= treffer.size()) {
//					String match = treffer.get(-n - 1);
//					if (match.startsWith(prefix)) {
//						completion = match.substring(pos - w);
//						SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1, tf));
//					}
//				}
//			}
//			@Override
//			public void removeUpdate(DocumentEvent arg0) {}
//
//		});
//		return tf;
	}
	
	public static JTextField createAutoCompletionTextFiledWithDropDown(String text, ICompletionProvider provider){
		return createAutoCompletionTextFiledWithDropDown(text, provider, null);
	}
	public static JTextField createAutoCompletionTextFiledWithDropDown(String text, ICompletionProvider provider, Window frame){
		return createAutoCompletionTextField(text, provider);
//		JTextField tf = new JTextField(text);
//		AutoSuggestor as = new AutoSuggestor(tf, frame, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
//			 @Override
//	           boolean wordTyped(String typedWord) {
//				 setDictionary(provider.provide(typedWord)); 
//				 return super.wordTyped(typedWord);
//			 }
//		};
//		
//		return tf;
	}


	/** 
	 * Creates an simple JPanel with borderlayout, that has an JLabel with "label" as text on WEST component and the editor on CENTER 
	 * @param label (not null)
	 * @param editor not null
	 * @return
	 */
	public static JPanel createRowPanel(String label, Component editor) {
		if (label == null || editor == null)
			return null;
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel(label), BorderLayout.WEST);
		panel.add(editor, BorderLayout.CENTER);
		return panel;
	}
	
	
	
	
	public static JTextField createPointerEditor(UClassifier rootClassifier, IProperty<String> strProp) {
		JTextField tf = new JTextField(strProp.getValue(), 20);		
		PointerStringAutocompletion provider = new PointerStringAutocompletion();
		provider.setRootClassifier(rootClassifier);
		AutoCompletion ac = new AutoCompletion(provider);
		ac.setAutoActivationDelay(100);
		ac.setAutoCompleteSingleChoices(true);
		ac.setAutoActivationEnabled(true);
		ac.setShowDescWindow(true);
		ac.setParameterAssistanceEnabled(true);
		
		ac.install(tf);	
		tf.addFocusListener(new FocusAdapter() {
			String txt;
			@Override
			public void focusGained(FocusEvent e) {
				txt = tf.getText();
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (txt != tf.getText() && strProp.isEditable())
					strProp.setValue(tf.getText());
			}
		});
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (strProp.isEditable())
					strProp.setValue(tf.getText());
			}
		});
		tf.setEnabled(strProp.isEditable());
				
		return tf;
		
	}
}
