package de.emir.rcp.editor.text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.UndoableEdit;

import com.google.common.io.Files;

import bibliothek.gui.dock.common.MultipleCDockableFactory;
import de.emir.rcp.editor.text.cmd.TextEditTransaction;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import net.boplicity.xmleditor.XmlEditorKit;

public class TextEditor extends AbstractEditor {

	private JEditorPane editorPane;

	public TextEditor(MultipleCDockableFactory<?, ?> factory) {
		super(factory);
	}

	@Override
	public void createContent(Container parent) {

		parent.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setBackground(new Color(255, 255, 255));

		parent.add(p, BorderLayout.CENTER);

		File file = path.toFile();

		if (file.exists() == false) {
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
			errorLabel.setForeground(Color.BLACK);
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
			errorLabel.setForeground(Color.BLACK);
			GridBagConstraints gbc_errorLabel = new GridBagConstraints();
			gbc_errorLabel.fill = GridBagConstraints.BOTH;
			gbc_errorLabel.insets = new Insets(20, 20, 20, 20);
			gbc_errorLabel.gridx = 0;
			gbc_errorLabel.gridy = 0;
			p.add(errorLabel, gbc_errorLabel);
			return;
		}

		GridBagLayout gbl_p = new GridBagLayout();

		gbl_p.columnWeights = new double[] { 1.0 };
		gbl_p.rowWeights = new double[] { 1.0 };
		p.setLayout(gbl_p);

		editorPane = new JEditorPane();

		JScrollPane sc = new JScrollPane(editorPane);

		GridBagConstraints gbc_sc = new GridBagConstraints();
		gbc_sc.fill = GridBagConstraints.BOTH;
		p.add(sc, gbc_sc);
		StyledEditorKit kit = new StyledEditorKit();
		editorPane.setBackground(new Color(255, 255, 255));
		String extension = Files.getFileExtension(path.toFile().getName());
		if(extension.equals("xml")) {
			editorPane.setEditorKit(new XmlEditorKit());
		}
//		editorPane.setEditorKit(kit);
	
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			editorPane.read(br, null);
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		Document document = editorPane.getDocument();
	
		editorPane.setForeground(new Color(0, 0, 0));
		document.addUndoableEditListener(new UndoableEditListener() {

			String lastEditName=null;
	        TextEditTransaction current;

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {

				UndoableEdit edit = e.getEdit();

				if (edit instanceof AbstractDocument.DefaultDocumentEvent) {
					
					try {
						AbstractDocument.DefaultDocumentEvent event = (AbstractDocument.DefaultDocumentEvent) edit;
						int start = event.getOffset();
						int len = event.getLength();
						
						int textLength = event.getDocument().getLength();
						
						boolean isNeedStart = false;
						
						if(textLength >= start + len) {
							String text = event.getDocument().getText(start, len);
							
							if (text.contains("\n")) {
								isNeedStart = true;
							}
							
						}

						if (current == null) {
							isNeedStart = true;
						} else if (lastEditName == null || !lastEditName.equals(edit.getPresentationName())) {
							isNeedStart = true;
						} else if(current.hasBeenUndoRedo()) {
							isNeedStart = true;
						} else if(isDirty() == false) {
							isNeedStart = true;
						}

						if (isNeedStart) {
							createTransaction();
						}

						current.addEdit(edit);
						lastEditName = edit.getPresentationName();

						if(isNeedStart) {
							transactionStack.run(current);
						}
						
						
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}

			}
			
			private void createTransaction() {
				current = new TextEditTransaction();
				
			}
		});

	}

	@Override
	public boolean save() {

		if (path == null) {
			return false;
		}
		File f = path.toFile();
		if (f != null) {
			try {
				FileWriter writer = new FileWriter(f);
				editorPane.write(writer);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;

	}

	@Override
	public Object getModel() {
		return editorPane.getDocument();
	}

}
