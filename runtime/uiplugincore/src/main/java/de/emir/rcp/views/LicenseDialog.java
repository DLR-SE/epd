package de.emir.rcp.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseDialog extends JDialog {
	static final Logger LOG = LoggerFactory.getLogger(LicenseDialog.class);

	/**
	 * Create the license dialog.
	 */
	public LicenseDialog() {
		setBounds(100, 100, 550, 500);
		setResizable(true);
		setTitle("License");
		setAlwaysOnTop(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(8, 8, 8, 8));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		FileReader reader;
		try {
			reader = new FileReader("LICENSE");
			textArea.read(reader, "LICENSE");
		} catch (IOException e) {
			LOG.error("Could not read LICENSE file.");
		}
		
	}

}
