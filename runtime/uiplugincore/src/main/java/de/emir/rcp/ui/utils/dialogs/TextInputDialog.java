package de.emir.rcp.ui.utils.dialogs;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.emir.tuml.ucore.runtime.resources.IconManager;

public class TextInputDialog extends JDialog {

    private static final long serialVersionUID = -4786517853098644514L;
    private JTextField textField;

    private Image titleIcon = IconManager.getScaledImage(this, "icons/emiricons/32/edit.png",
            IconManager.preferedSmallIconSize());
    private Icon errorIcon = IconManager.getIcon(this, "icons/emiricons/32/dangerous.png",
            IconManager.preferedSmallIconSize());

    private String text = null;
    private ITextValidator validator;
    private JButton btnOk;
    private JLabel errorLabel;

    public TextInputDialog(JFrame frame, String title, String info, String fieldTitle, ITextValidator validator) {
        super(frame);

        this.validator = validator;

        setModal(true);
        setTitle(title);
        setIconImage(titleIcon);
        setSize(450, 143);
        setLocationRelativeTo(frame);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        JLabel lblDetailtext = new JLabel(info);
        lblDetailtext.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lblDetailtext = new GridBagConstraints();
        gbc_lblDetailtext.anchor = GridBagConstraints.WEST;
        gbc_lblDetailtext.gridwidth = 2;
        gbc_lblDetailtext.insets = new Insets(5, 5, 15, 0);
        gbc_lblDetailtext.gridx = 0;
        gbc_lblDetailtext.gridy = 0;
        getContentPane().add(lblDetailtext, gbc_lblDetailtext);

        JLabel lblTitle = new JLabel(fieldTitle);
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.insets = new Insets(0, 5, 5, 5);
        gbc_lblTitle.anchor = GridBagConstraints.EAST;
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 1;
        getContentPane().add(lblTitle, gbc_lblTitle);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 3;
        getContentPane().add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        panel_1.add(errorLabel, gbc_label);

        errorLabel.setIcon(errorIcon);
        errorLabel.setVisible(false);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(5, 5, 5, 5);
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        btnOk = new JButton("Ok");
        GridBagConstraints gbc_btnOk = new GridBagConstraints();
        gbc_btnOk.insets = new Insets(0, 0, 0, 5);
        gbc_btnOk.gridx = 0;
        gbc_btnOk.gridy = 0;
        panel.add(btnOk, gbc_btnOk);

        JButton btnCancel = new JButton("Cancel");
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.gridx = 1;
        gbc_btnCancel.gridy = 0;
        panel.add(btnCancel, gbc_btnCancel);

        btnCancel.addActionListener(evt -> {

            text = null;
            setVisible(false);

        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                text = null;
            }
        });

        btnOk.addActionListener(evt -> {

            text = textField.getText();
            setVisible(false);

        });

        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();

            }
        });

    }

    private void validateInput() {
        String text = textField.getText();

        String error = validator.isValid(text);

        btnOk.setEnabled(error == null);
        errorLabel.setText(error);
        errorLabel.setVisible(error != null);

    }

    public String getText() {
        return text;
    }

}
