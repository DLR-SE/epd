package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import de.emir.rcp.pluginmanager.ids.PMBasics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SelectProductFileDialog extends JDialog {

    private static final long serialVersionUID = -2315770391175489012L;
    private JTextField textField;
    private boolean blocking;

    private String result;
    private String currentDirPath;
    private JButton btnOk;

    /**
     * @param parent
     * @param blocking
     *            Wether this dialog blocks the whole application. If true, the user has to choose a valid file or exit
     *            the app
     */
    public SelectProductFileDialog(Window parent, boolean blocking) {
        super(parent);

        this.blocking = blocking;

        setTitle("Product Definition File...");

        setMinimumSize(new Dimension(500, 160));
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panelSelection = new JPanel();
        getContentPane().add(panelSelection, BorderLayout.CENTER);
                                                                GridBagLayout gbl_panelSelection = new GridBagLayout();
                                                                gbl_panelSelection.columnWidths = new int[] {391, 48};
                                                                gbl_panelSelection.rowHeights = new int[] {32, 32};
                                                                gbl_panelSelection.columnWeights = new double[]{2.0, 0.0};
                                                                gbl_panelSelection.rowWeights = new double[]{1.0, 1.0};
                                                                panelSelection.setLayout(gbl_panelSelection);
                                                                        
                                                                                JLabel lblSelectAValid = new JLabel(
                                                                                        "<html>Select a valid Product Definition File to continue. <br> <i>A Product Definition defines which plugins are to be loaded by an application.</i></html>");
                                                                                GridBagConstraints gbc_lblSelectAValid = new GridBagConstraints();
                                                                                gbc_lblSelectAValid.anchor = GridBagConstraints.SOUTH;
                                                                                gbc_lblSelectAValid.gridwidth = 2;
                                                                                gbc_lblSelectAValid.fill = GridBagConstraints.HORIZONTAL;
                                                                                gbc_lblSelectAValid.insets = new Insets(4, 4, 4, 4);
                                                                                gbc_lblSelectAValid.gridx = 0;
                                                                                gbc_lblSelectAValid.gridy = 0;
                                                                                panelSelection.add(lblSelectAValid, gbc_lblSelectAValid);
                                                                                        
                                                                                                textField = new JTextField();
                                                                                                GridBagConstraints gbc_textField = new GridBagConstraints();
                                                                                                gbc_textField.fill = GridBagConstraints.BOTH;
                                                                                                gbc_textField.insets = new Insets(4, 4, 4, 4);
                                                                                                gbc_textField.gridx = 0;
                                                                                                gbc_textField.gridy = 1;
                                                                                                panelSelection.add(textField, gbc_textField);
                                                                                                textField.setColumns(10);
                                                                                                
                                                                                                        textField.setEnabled(false);
                                                                                
                                                                                        JButton btnBrowse = new JButton("Browse...");
                                                                                        GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
                                                                                        gbc_btnBrowse.insets = new Insets(4, 4, 4, 4);
                                                                                        gbc_btnBrowse.anchor = GridBagConstraints.EAST;
                                                                                        gbc_btnBrowse.gridx = 1;
                                                                                        gbc_btnBrowse.gridy = 1;
                                                                                        panelSelection.add(btnBrowse, gbc_btnBrowse);
                                                                                        btnBrowse.addActionListener(e -> handleBrowse());

        JPanel panelButtons = new JPanel();
        getContentPane().add(panelButtons, BorderLayout.SOUTH);
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnExit = new JButton(blocking ? "Exit Application" : "Cancel");
        panelButtons.add(btnExit);

        btnOk = new JButton("   OK   ");
        btnOk.setEnabled(false);
        panelButtons.add(btnOk);

        setLocationRelativeTo(parent);

        btnOk.addActionListener(e -> handleOk());
        btnExit.addActionListener(e -> handleExit());

        if (blocking == true) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }

    }

    public void setCurrentFile(String productFilePath) {

        if (productFilePath == null) {
            return;
        }

        File f = new File(productFilePath);
        this.currentDirPath = f.getParent();

        textField.setText(productFilePath);
        btnOk.setEnabled(true);

    }

    private void handleBrowse() {

        JFileChooser dialog = new JFileChooser(currentDirPath);

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "Product Definition File (" + PMBasics.PRODUCT_FILE_NAME + ")";
            }

            @Override
            public boolean accept(File f) {

                if (f.isDirectory() == true) {
                    return true;
                }

                return f.getAbsolutePath().endsWith(PMBasics.PRODUCT_FILE_NAME);
            }
        });

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        textField.setText(selection.getAbsolutePath());

        btnOk.setEnabled(true);

    }

    private void handleOk() {

        result = textField.getText();
        setVisible(false);

    }

    public String getResult() {
        return result;
    }

    private void handleExit() {

        if (blocking == true) {

            System.exit(ABORT);

        } else {

            setVisible(false);

        }

    }

}
