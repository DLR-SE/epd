package de.emir.rcp.pluginmanager.views.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.views.PluginRenderUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.runtime.epf.ProductFile;

public class ExportProductDialog extends JDialog {

    private static final long serialVersionUID = -2315770391175489012L;
    private JButton btnBrowsePlantuml;
    private JButton btnBrowseDoxygen;
    private JButton btnOk;

    private JRadioButton radioOnline;
    private JRadioButton radioLean;
    private JRadioButton radioOffline;

    private JTextField nameTF;
    private JTextField entryPointTF;
    private JTextField entryPointPomTF;
    private JTextField outputTF;
    private JTextField doxygenTF;
    private JTextField plantumlTF;

    private JCheckBox copyLayoutCB;
    private JCheckBox copyPropCB;
    private JCheckBox copyAllCB;
    private JCheckBox removeReposCB;
    private JCheckBox addDocumentationCB;

    private JLabel entryPointClassLabel;
    private JLabel nameErrorLabel;
    private JLabel pomErrorLabel;
    private JLabel outputErrorLabel;
    private JLabel removeReposInfo;
    private JLabel doxygenErrorLabel;
    private JLabel plantUMLErrorLabel;

    private JPanel addDocumentationPanel;

    private static final String DEFAULT_TEXT_NAME = "Required to create the output folder";
    private static final String DEFAULT_TEXT_ENTRY_POINT = "The built JAR of the product. Must contain all direct dependencies";
    private static final String DEFAULT_TEXT_POM = "The Maven POM according to which the product JAR was built";
    private static final String DEFAULT_TEXT_OUTPUT = "The directory into which the application is deployed (without white spaces)";
    private static final String DEFAULT_TEXT_DOXYGEN = "Path to the Doxygen Executable";
    private static final String DEFAULT_TEXT_PLANTUML = "Path to the Plantuml Jar (without white spaces)";

    private ExportData data;

    Pattern folderNamePattern = Pattern.compile("[\\\\<>|*/?\\s]");

    private ProductFile productFile;

    private boolean ok = false;

    /**
     * @param parent
     * @wbp.parser.constructor
     */
    public ExportProductDialog(Window parent, ProductFile pf) {
        super(parent);

        this.productFile = pf;

        setExportData();

        setModal(true);

        setTitle("Export Product...");
        setIconImage(PluginRenderUtil.EXPORT_ICON_IMAGE);
        setMinimumSize(new Dimension(730, 850));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0 };
        getContentPane().setLayout(gridBagLayout);

        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.gridwidth = 2;
        gbc_panel_2.insets = new Insets(10, 10, 5, 10);
        gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 0;
        getContentPane().add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();

        gbl_panel_2.columnWeights = new double[] { 1.0, 1.0, 0.0 };
        gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
        panel_2.setLayout(gbl_panel_2);

        DocumentListener textFieldListener = new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInputs();

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInputs();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInputs();

            }
        };

        DocumentListener textFieldEntryPointListener = new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
                resolveEntryPoint();

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
                resolveEntryPoint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
                resolveEntryPoint();

            }
        };

        JLabel lblApplication = new JLabel("Application");
        lblApplication.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblApplication = new GridBagConstraints();
        gbc_lblApplication.anchor = GridBagConstraints.WEST;
        gbc_lblApplication.insets = new Insets(0, 0, 5, 5);
        gbc_lblApplication.gridx = 0;
        gbc_lblApplication.gridy = 0;
        panel_2.add(lblApplication, gbc_lblApplication);

        JLabel lblApplicationName = new JLabel("Name:");
        GridBagConstraints gbc_lblApplicationName = new GridBagConstraints();
        gbc_lblApplicationName.anchor = GridBagConstraints.EAST;
        gbc_lblApplicationName.insets = new Insets(0, 0, 5, 5);
        gbc_lblApplicationName.gridx = 0;
        gbc_lblApplicationName.gridy = 1;
        panel_2.add(lblApplicationName, gbc_lblApplicationName);

        nameTF = new JTextField();
        nameTF.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_layoutTextFieldName = new GridBagConstraints();
        gbc_layoutTextFieldName.insets = new Insets(0, 0, 5, 5);
        gbc_layoutTextFieldName.fill = GridBagConstraints.BOTH;
        gbc_layoutTextFieldName.gridx = 1;
        gbc_layoutTextFieldName.gridy = 1;
        panel_2.add(nameTF, gbc_layoutTextFieldName);
        nameTF.setColumns(10);
        nameTF.setText(pf.getName());
        nameErrorLabel = new JLabel(" ");

        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 1;
        gbc_lblNewLabel_1_1.gridy = 2;
        panel_2.add(nameErrorLabel, gbc_lblNewLabel_1_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setMinimumSize(new Dimension(5, 5));
        GridBagConstraints gbc_separator_2 = new GridBagConstraints();
        gbc_separator_2.gridwidth = 3;
        gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator_2.insets = new Insets(0, 0, 5, 0);
        gbc_separator_2.gridx = 0;
        gbc_separator_2.gridy = 3;
        panel_2.add(separator_2, gbc_separator_2);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 2;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        getContentPane().add(panel_1, gbc_panel_1);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(5, 5, 10, 10);
        gbc_panel.anchor = GridBagConstraints.EAST;
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWeights = new double[] { 0.0, 0.0 };
        gbl_panel.rowWeights = new double[] { 0.0 };
        panel.setLayout(gbl_panel);

        JButton btnExit = new JButton("Cancel");
        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.insets = new Insets(0, 0, 0, 5);
        gbc_btnExit.gridx = 0;
        gbc_btnExit.gridy = 0;
        panel.add(btnExit, gbc_btnExit);

        btnOk = new JButton("   OK   ");
        btnOk.setEnabled(false);
        GridBagConstraints gbc_btnOk = new GridBagConstraints();
        gbc_btnOk.gridx = 1;
        gbc_btnOk.gridy = 0;
        panel.add(btnOk, gbc_btnOk);

        setLocationRelativeTo(null);

        btnOk.addActionListener(e -> handleOk());
        btnExit.addActionListener(e -> handleExit());

        ButtonGroup group = new ButtonGroup();

        JLabel lblApplicationEntryPoint = new JLabel("Entry Point");
        lblApplicationEntryPoint.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblApplicationEntryPoint = new GridBagConstraints();
        gbc_lblApplicationEntryPoint.anchor = GridBagConstraints.WEST;
        gbc_lblApplicationEntryPoint.gridwidth = 2;
        gbc_lblApplicationEntryPoint.insets = new Insets(0, 0, 5, 5);
        gbc_lblApplicationEntryPoint.gridx = 0;
        gbc_lblApplicationEntryPoint.gridy = 4;
        panel_2.add(lblApplicationEntryPoint, gbc_lblApplicationEntryPoint);

        JLabel lblNewLabel = new JLabel("Entry Point:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 5;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);

        entryPointTF = new JTextField();
        entryPointTF.setText(data.getEntryPointPath());

        GridBagConstraints gbc_textFieldEntryPoint = new GridBagConstraints();
        gbc_textFieldEntryPoint.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldEntryPoint.fill = GridBagConstraints.BOTH;
        gbc_textFieldEntryPoint.gridx = 1;
        gbc_textFieldEntryPoint.gridy = 5;
        panel_2.add(entryPointTF, gbc_textFieldEntryPoint);
        entryPointTF.setColumns(10);

        JButton btnBrowse_1 = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowse_1 = new GridBagConstraints();
        gbc_btnBrowse_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnBrowse_1.gridx = 2;
        gbc_btnBrowse_1.gridy = 5;
        panel_2.add(btnBrowse_1, gbc_btnBrowse_1);
        btnBrowse_1.addActionListener(e -> handleBrowseEntryPoint());

        entryPointClassLabel = new JLabel(" ");
        GridBagConstraints gbc_entryPoint = new GridBagConstraints();
        gbc_entryPoint.anchor = GridBagConstraints.WEST;
        gbc_entryPoint.insets = new Insets(0, 0, 5, 5);
        gbc_entryPoint.gridx = 1;
        gbc_entryPoint.gridy = 6;
        panel_2.add(entryPointClassLabel, gbc_entryPoint);

        JLabel lblEntryPointPom = new JLabel("Base POM:");
        GridBagConstraints gbc_lblEntryPointPom = new GridBagConstraints();
        gbc_lblEntryPointPom.anchor = GridBagConstraints.EAST;
        gbc_lblEntryPointPom.insets = new Insets(0, 0, 5, 5);
        gbc_lblEntryPointPom.gridx = 0;
        gbc_lblEntryPointPom.gridy = 7;
        panel_2.add(lblEntryPointPom, gbc_lblEntryPointPom);

        entryPointPomTF = new JTextField(data.getEntryPointPomPath());

        GridBagConstraints gbc_entryPointPom = new GridBagConstraints();
        gbc_entryPointPom.insets = new Insets(0, 0, 5, 5);
        gbc_entryPointPom.fill = GridBagConstraints.BOTH;
        gbc_entryPointPom.gridx = 1;
        gbc_entryPointPom.gridy = 7;
        panel_2.add(entryPointPomTF, gbc_entryPointPom);
        entryPointPomTF.setColumns(10);

        JButton btnBrowsePom = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowsePom = new GridBagConstraints();
        gbc_btnBrowsePom.insets = new Insets(0, 0, 5, 0);
        gbc_btnBrowsePom.fill = GridBagConstraints.VERTICAL;
        gbc_btnBrowsePom.gridx = 2;
        gbc_btnBrowsePom.gridy = 7;
        panel_2.add(btnBrowsePom, gbc_btnBrowsePom);
        btnBrowsePom.addActionListener(e -> handleBrowseEntryPointPOM());

        pomErrorLabel = new JLabel("");
        GridBagConstraints gbc_pomErrorLabel = new GridBagConstraints();
        gbc_pomErrorLabel.anchor = GridBagConstraints.WEST;
        gbc_pomErrorLabel.insets = new Insets(0, 0, 5, 5);
        gbc_pomErrorLabel.gridx = 1;
        gbc_pomErrorLabel.gridy = 8;
        panel_2.add(pomErrorLabel, gbc_pomErrorLabel);

        JSeparator separator_3 = new JSeparator();
        separator_3.setMinimumSize(new Dimension(5, 5));
        GridBagConstraints gbc_separator_3 = new GridBagConstraints();
        gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator_3.gridwidth = 3;
        gbc_separator_3.insets = new Insets(0, 0, 5, 0);
        gbc_separator_3.gridx = 0;
        gbc_separator_3.gridy = 9;
        panel_2.add(separator_3, gbc_separator_3);

        JLabel lblApplicationSettings = new JLabel("Settings");
        lblApplicationSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblApplicationSettings = new GridBagConstraints();
        gbc_lblApplicationSettings.anchor = GridBagConstraints.WEST;
        gbc_lblApplicationSettings.gridwidth = 2;
        gbc_lblApplicationSettings.insets = new Insets(0, 0, 5, 5);
        gbc_lblApplicationSettings.gridx = 0;
        gbc_lblApplicationSettings.gridy = 10;
        panel_2.add(lblApplicationSettings, gbc_lblApplicationSettings);

        copyLayoutCB = new JCheckBox("Layout File");
        GridBagConstraints gbc_copyLayoutCB = new GridBagConstraints();
        gbc_copyLayoutCB.anchor = GridBagConstraints.WEST;
        gbc_copyLayoutCB.insets = new Insets(0, 0, 5, 5);
        gbc_copyLayoutCB.gridx = 0;
        gbc_copyLayoutCB.gridy = 11;
        panel_2.add(copyLayoutCB, gbc_copyLayoutCB);
        copyLayoutCB.setSelected(data.isCopyLayout());

        copyLayoutCB.addActionListener(e -> data.setCopyLayout(copyLayoutCB.isSelected()));

        JLabel lblsettingsFilesAre = new JLabel(
                "<html>Settings files are created in the home directory of the application (usually next to the Product.xml). They contain the layout and properties of the application. By copying, the settings made during development can be included in a release.</html>");
        GridBagConstraints gbc_lblsettingsFilesAre = new GridBagConstraints();
        gbc_lblsettingsFilesAre.anchor = GridBagConstraints.NORTH;
        gbc_lblsettingsFilesAre.gridwidth = 2;
        gbc_lblsettingsFilesAre.gridheight = 2;
        gbc_lblsettingsFilesAre.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblsettingsFilesAre.insets = new Insets(4, 0, 5, 0);
        gbc_lblsettingsFilesAre.gridx = 1;
        gbc_lblsettingsFilesAre.gridy = 11;
        panel_2.add(lblsettingsFilesAre, gbc_lblsettingsFilesAre);
        lblsettingsFilesAre.setFont(new Font("Tahoma", Font.ITALIC, 11));

        copyPropCB = new JCheckBox("Properties File");
        GridBagConstraints gbc_copyPropCB = new GridBagConstraints();
        gbc_copyPropCB.anchor = GridBagConstraints.WEST;
        gbc_copyPropCB.insets = new Insets(0, 0, 5, 5);
        gbc_copyPropCB.gridx = 0;
        gbc_copyPropCB.gridy = 12;
        panel_2.add(copyPropCB, gbc_copyPropCB);
        copyPropCB.setSelected(data.isCopyProperties());
        copyPropCB.addActionListener(e -> data.setCopyProperties(copyPropCB.isSelected()));

        copyAllCB = new JCheckBox("Additional Files");
        GridBagConstraints gbc_copyAllCB = new GridBagConstraints();
        gbc_copyAllCB.anchor = GridBagConstraints.WEST;
        gbc_copyAllCB.insets = new Insets(0, 0, 5, 5);
        gbc_copyAllCB.gridx = 0;
        gbc_copyAllCB.gridy = 13;
        panel_2.add(copyAllCB, gbc_copyAllCB);
        copyAllCB.setSelected(data.isCopyAdditionalFiles());
        copyAllCB.addActionListener(e -> data.setCopyAll(copyAllCB.isSelected()));

        JLabel lblcopiesAllFiles = new JLabel(
                "<html>Copies all files and folders located next to the Product.xml. This allows plugin-specific files to be included in a release.</html>");
        GridBagConstraints gbc_lblcopiesAllFiles = new GridBagConstraints();
        gbc_lblcopiesAllFiles.gridwidth = 2;
        gbc_lblcopiesAllFiles.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblcopiesAllFiles.insets = new Insets(0, 0, 5, 0);
        gbc_lblcopiesAllFiles.gridx = 1;
        gbc_lblcopiesAllFiles.gridy = 13;
        panel_2.add(lblcopiesAllFiles, gbc_lblcopiesAllFiles);
        lblcopiesAllFiles.setFont(new Font("Tahoma", Font.ITALIC, 11));

        JCheckBox chckbxCreateZipFile = new JCheckBox("Create Zip File");
        GridBagConstraints gbc_chckbxCreateZipFile = new GridBagConstraints();
        gbc_chckbxCreateZipFile.anchor = GridBagConstraints.WEST;
        gbc_chckbxCreateZipFile.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxCreateZipFile.gridx = 0;
        gbc_chckbxCreateZipFile.gridy = 14;
        panel_2.add(chckbxCreateZipFile, gbc_chckbxCreateZipFile);
        chckbxCreateZipFile.setSelected(data.isCreateZip());
        chckbxCreateZipFile.addActionListener(e -> data.setCreateZip(chckbxCreateZipFile.isSelected()));

        JLabel lblcreatesAZip = new JLabel("<html>Creates a Zip file of the complete release.</html>");
        lblcreatesAZip.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lblcreatesAZip = new GridBagConstraints();
        gbc_lblcreatesAZip.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblcreatesAZip.insets = new Insets(0, 0, 5, 5);
        gbc_lblcreatesAZip.gridx = 1;
        gbc_lblcreatesAZip.gridy = 14;
        panel_2.add(lblcreatesAZip, gbc_lblcreatesAZip);

        addDocumentationCB = new JCheckBox("Add Documentation");
        GridBagConstraints gbc_addDocumentationCB = new GridBagConstraints();
        gbc_addDocumentationCB.anchor = GridBagConstraints.NORTHWEST;
        gbc_addDocumentationCB.insets = new Insets(0, 0, 5, 5);
        gbc_addDocumentationCB.gridx = 0;
        gbc_addDocumentationCB.gridy = 15;
        panel_2.add(addDocumentationCB, gbc_addDocumentationCB);
        addDocumentationCB.setSelected(data.isAddDocumentation());
        addDocumentationCB.addActionListener(e -> {
            data.setAddDocumentation(addDocumentationCB.isSelected());
            checkAddDocumentationState();
        });

        JLabel lbldocumentationIsCreated = new JLabel(
                "<html>Documentation is created for each project specified in the Product.xml (Workspace-Directories)</html>");
        lbldocumentationIsCreated.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lbldocumentationIsCreated = new GridBagConstraints();
        gbc_lbldocumentationIsCreated.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbldocumentationIsCreated.gridwidth = 2;
        gbc_lbldocumentationIsCreated.insets = new Insets(0, 0, 5, 0);
        gbc_lbldocumentationIsCreated.gridx = 1;
        gbc_lbldocumentationIsCreated.gridy = 15;
        panel_2.add(lbldocumentationIsCreated, gbc_lbldocumentationIsCreated);

        addDocumentationPanel = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.gridheight = 2;
        gbc_panel_3.gridwidth = 3;
        gbc_panel_3.insets = new Insets(0, 30, 5, 0);
        gbc_panel_3.fill = GridBagConstraints.BOTH;
        gbc_panel_3.gridx = 0;
        gbc_panel_3.gridy = 16;
        panel_2.add(addDocumentationPanel, gbc_panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        addDocumentationPanel.setLayout(gbl_panel_3);

        JLabel lblDoxygenExecutable = new JLabel("Doxygen Executable: ");
        GridBagConstraints gbc_lblDoxygenExecutable = new GridBagConstraints();
        gbc_lblDoxygenExecutable.anchor = GridBagConstraints.EAST;
        gbc_lblDoxygenExecutable.gridwidth = 7;
        gbc_lblDoxygenExecutable.insets = new Insets(5, 0, 5, 5);
        gbc_lblDoxygenExecutable.gridx = 0;
        gbc_lblDoxygenExecutable.gridy = 0;
        addDocumentationPanel.add(lblDoxygenExecutable, gbc_lblDoxygenExecutable);

        doxygenTF = new JTextField();
        doxygenTF.setColumns(10);
        GridBagConstraints gbc_doxygenTF = new GridBagConstraints();
        gbc_doxygenTF.gridwidth = 11;
        gbc_doxygenTF.insets = new Insets(5, 0, 5, 5);
        gbc_doxygenTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_doxygenTF.gridx = 7;
        gbc_doxygenTF.gridy = 0;
        addDocumentationPanel.add(doxygenTF, gbc_doxygenTF);
        doxygenTF.setText(data.getDoxygenPath());

        btnBrowseDoxygen = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowseDoxygen = new GridBagConstraints();
        gbc_btnBrowseDoxygen.insets = new Insets(0, 0, 5, 0);
        gbc_btnBrowseDoxygen.gridx = 18;
        gbc_btnBrowseDoxygen.gridy = 0;
        addDocumentationPanel.add(btnBrowseDoxygen, gbc_btnBrowseDoxygen);
        btnBrowseDoxygen.addActionListener(e -> handleBrowseDoxygen());

        doxygenErrorLabel = new JLabel(DEFAULT_TEXT_DOXYGEN);
        GridBagConstraints gbc_doxygenErrorLabel = new GridBagConstraints();
        gbc_doxygenErrorLabel.anchor = GridBagConstraints.WEST;
        gbc_doxygenErrorLabel.gridwidth = 11;
        gbc_doxygenErrorLabel.insets = new Insets(0, 0, 5, 5);
        gbc_doxygenErrorLabel.gridx = 7;
        gbc_doxygenErrorLabel.gridy = 1;
        addDocumentationPanel.add(doxygenErrorLabel, gbc_doxygenErrorLabel);

        JLabel lblPlantumlJar = new JLabel("Plantuml Jar: ");
        GridBagConstraints gbc_lblPlantumlJar = new GridBagConstraints();
        gbc_lblPlantumlJar.anchor = GridBagConstraints.EAST;
        gbc_lblPlantumlJar.gridwidth = 7;
        gbc_lblPlantumlJar.insets = new Insets(0, 0, 5, 5);
        gbc_lblPlantumlJar.gridx = 0;
        gbc_lblPlantumlJar.gridy = 2;
        addDocumentationPanel.add(lblPlantumlJar, gbc_lblPlantumlJar);

        plantumlTF = new JTextField();
        plantumlTF.setColumns(10);
        GridBagConstraints gbc_plantumlTF = new GridBagConstraints();
        gbc_plantumlTF.gridwidth = 11;
        gbc_plantumlTF.insets = new Insets(0, 0, 5, 5);
        gbc_plantumlTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_plantumlTF.gridx = 7;
        gbc_plantumlTF.gridy = 2;
        addDocumentationPanel.add(plantumlTF, gbc_plantumlTF);
        plantumlTF.setText(data.getPlantUMLPath());

        btnBrowsePlantuml = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowsePlantuml = new GridBagConstraints();
        gbc_btnBrowsePlantuml.insets = new Insets(0, 0, 5, 0);
        gbc_btnBrowsePlantuml.gridx = 18;
        gbc_btnBrowsePlantuml.gridy = 2;
        addDocumentationPanel.add(btnBrowsePlantuml, gbc_btnBrowsePlantuml);
        btnBrowsePlantuml.addActionListener(e -> handleBrowsePlantUML());

        plantUMLErrorLabel = new JLabel(DEFAULT_TEXT_PLANTUML);
        GridBagConstraints gbc_plantUMLErrorLabel = new GridBagConstraints();
        gbc_plantUMLErrorLabel.anchor = GridBagConstraints.WEST;
        gbc_plantUMLErrorLabel.gridwidth = 11;
        gbc_plantUMLErrorLabel.insets = new Insets(0, 0, 0, 5);
        gbc_plantUMLErrorLabel.gridx = 7;
        gbc_plantUMLErrorLabel.gridy = 3;
        addDocumentationPanel.add(plantUMLErrorLabel, gbc_plantUMLErrorLabel);

        JSeparator separator_4 = new JSeparator();
        separator_4.setMinimumSize(new Dimension(5, 5));
        GridBagConstraints gbc_separator_4 = new GridBagConstraints();
        gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator_4.gridwidth = 3;
        gbc_separator_4.insets = new Insets(0, 0, 5, 0);
        gbc_separator_4.gridx = 0;
        gbc_separator_4.gridy = 18;
        panel_2.add(separator_4, gbc_separator_4);

        JLabel lblRelease = new JLabel("Build");
        lblRelease.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_lblRelease = new GridBagConstraints();
        gbc_lblRelease.anchor = GridBagConstraints.WEST;
        gbc_lblRelease.gridwidth = 2;
        gbc_lblRelease.insets = new Insets(0, 0, 5, 5);
        gbc_lblRelease.gridx = 0;
        gbc_lblRelease.gridy = 19;
        panel_2.add(lblRelease, gbc_lblRelease);

        radioOnline = new JRadioButton("Online Release");
        GridBagConstraints gbc_radioOnline = new GridBagConstraints();
        gbc_radioOnline.anchor = GridBagConstraints.NORTHWEST;
        gbc_radioOnline.insets = new Insets(0, 0, 5, 5);
        gbc_radioOnline.gridx = 0;
        gbc_radioOnline.gridy = 20;
        panel_2.add(radioOnline, gbc_radioOnline);
        radioOnline.setSelected(data.isOnlineRelease());
        group.add(radioOnline);
        radioOnline.addActionListener(e -> {
            data.setOnlineRelease(radioOnline.isSelected());
            checkOfflineOnlyInputsState();
        });

        JLabel lblInAnOnline = new JLabel(
                "<html> In an online release dependencies are retrieved from the online repository (last stable version). </html>");
        lblInAnOnline.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lblInAnOnline = new GridBagConstraints();
        gbc_lblInAnOnline.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblInAnOnline.gridwidth = 2;
        gbc_lblInAnOnline.anchor = GridBagConstraints.NORTH;
        gbc_lblInAnOnline.insets = new Insets(4, 0, 5, 0);
        gbc_lblInAnOnline.gridx = 1;
        gbc_lblInAnOnline.gridy = 20;
        panel_2.add(lblInAnOnline, gbc_lblInAnOnline);

        radioLean = new JRadioButton("Lean Release");
        GridBagConstraints gbc_radioLean = new GridBagConstraints();
        gbc_radioLean.anchor = GridBagConstraints.NORTHWEST;
        gbc_radioLean.insets = new Insets(0, 0, 5, 5);
        gbc_radioLean.gridx = 0;
        gbc_radioLean.gridy = 21;
        panel_2.add(radioLean, gbc_radioLean);
        radioLean.setSelected(data.isLeanRelease());
        group.add(radioLean);
        radioLean.addActionListener(e -> {
            data.setLeanRelease(radioLean.isSelected());
            checkOfflineOnlyInputsState();
        });
        JLabel lblDsadsadsa = new JLabel(
                "<html>In a lean release, all workspace and dependency entries are retrieved from the specified local Maven repository (default is /.m2).</html>");
        GridBagConstraints gbc_lblDsadsadsa = new GridBagConstraints();
        gbc_lblDsadsadsa.insets = new Insets(4, 0, 5, 0);
        gbc_lblDsadsadsa.anchor = GridBagConstraints.NORTH;
        gbc_lblDsadsadsa.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblDsadsadsa.gridwidth = 2;
        gbc_lblDsadsadsa.gridx = 1;
        gbc_lblDsadsadsa.gridy = 21;
        panel_2.add(lblDsadsadsa, gbc_lblDsadsadsa);
        lblDsadsadsa.setFont(new Font("Tahoma", Font.ITALIC, 11));

        radioOffline = new JRadioButton("Offline Release");
        GridBagConstraints gbc_radioOffline = new GridBagConstraints();
        gbc_radioOffline.anchor = GridBagConstraints.WEST;
        gbc_radioOffline.insets = new Insets(0, 0, 5, 5);
        gbc_radioOffline.gridx = 0;
        gbc_radioOffline.gridy = 22;
        panel_2.add(radioOffline, gbc_radioOffline);
        group.add(radioOffline);
        radioOffline.setSelected(data.isLeanRelease() == false);
        radioOffline.addActionListener(e -> {
            data.setLeanRelease(radioOffline.isSelected() == false);
            checkOfflineOnlyInputsState();
        });

        JLabel lblInAnOffline = new JLabel(
                "<html> In an offline release, all dependencies are copied to the target directory.</html>");
        lblInAnOffline.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_lblInAnOffline = new GridBagConstraints();
        gbc_lblInAnOffline.gridwidth = 2;
        gbc_lblInAnOffline.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblInAnOffline.insets = new Insets(0, 0, 5, 0);
        gbc_lblInAnOffline.gridx = 1;
        gbc_lblInAnOffline.gridy = 22;
        panel_2.add(lblInAnOffline, gbc_lblInAnOffline);

        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.gridwidth = 3;
        gbc_panel_5.insets = new Insets(0, 30, 5, 0);
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 23;
        panel_2.add(panel_5, gbc_panel_5);
        GridBagLayout gbl_panel_5 = new GridBagLayout();
        gbl_panel_5.columnWeights = new double[] { 0.0, 1.0 };
        gbl_panel_5.rowWeights = new double[] { 0.0 };
        panel_5.setLayout(gbl_panel_5);

        removeReposCB = new JCheckBox("Remove Repositories");
        GridBagConstraints gbc_removeReposCB = new GridBagConstraints();
        gbc_removeReposCB.insets = new Insets(0, 0, 5, 5);
        gbc_removeReposCB.anchor = GridBagConstraints.NORTHWEST;
        gbc_removeReposCB.gridx = 0;
        gbc_removeReposCB.gridy = 0;
        panel_5.add(removeReposCB, gbc_removeReposCB);
        removeReposCB.setSelected(data.isRemoveAllRepositories());
        removeReposCB.addActionListener(e -> data.setRemoveAllRepositories(removeReposCB.isSelected()));

        removeReposInfo = new JLabel(
                "<html>Removes the specified remote repositories and their authentication informations from the release.</html>");
        removeReposInfo.setFont(new Font("Tahoma", Font.ITALIC, 11));
        GridBagConstraints gbc_removeReposInfo = new GridBagConstraints();
        gbc_removeReposInfo.insets = new Insets(4, 0, 5, 0);
        gbc_removeReposInfo.anchor = GridBagConstraints.NORTH;
        gbc_removeReposInfo.fill = GridBagConstraints.HORIZONTAL;
        gbc_removeReposInfo.gridx = 1;
        gbc_removeReposInfo.gridy = 0;
        panel_5.add(removeReposInfo, gbc_removeReposInfo);

        JSeparator separator = new JSeparator();
        separator.setMinimumSize(new Dimension(5, 5));
        GridBagConstraints gbc_separator = new GridBagConstraints();
        gbc_separator.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator.gridwidth = 3;
        gbc_separator.insets = new Insets(0, 0, 5, 0);
        gbc_separator.gridx = 0;
        gbc_separator.gridy = 24;
        panel_2.add(separator, gbc_separator);

        JLabel lblTargetFolder = new JLabel("Output Folder:");
        GridBagConstraints gbc_lblTargetFolder = new GridBagConstraints();
        gbc_lblTargetFolder.anchor = GridBagConstraints.EAST;
        gbc_lblTargetFolder.insets = new Insets(0, 0, 5, 5);
        gbc_lblTargetFolder.gridx = 0;
        gbc_lblTargetFolder.gridy = 25;
        panel_2.add(lblTargetFolder, gbc_lblTargetFolder);

        outputTF = new JTextField();
        GridBagConstraints gbc_textField1 = new GridBagConstraints();
        gbc_textField1.insets = new Insets(0, 0, 5, 5);
        gbc_textField1.fill = GridBagConstraints.BOTH;
        gbc_textField1.gridx = 1;
        gbc_textField1.gridy = 25;
        panel_2.add(outputTF, gbc_textField1);
        outputTF.setColumns(10);
        outputTF.setText(data.getOutputPath());
        outputTF.getDocument().addDocumentListener(textFieldListener);

        JButton btnBrowse = new JButton("Browse...");
        GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
        gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
        gbc_btnBrowse.fill = GridBagConstraints.VERTICAL;
        gbc_btnBrowse.gridx = 2;
        gbc_btnBrowse.gridy = 25;
        panel_2.add(btnBrowse, gbc_btnBrowse);

        btnBrowse.addActionListener(e -> handleBrowse());

        outputErrorLabel = new JLabel("");
        GridBagConstraints gbc_outputErrorLabel = new GridBagConstraints();
        gbc_outputErrorLabel.anchor = GridBagConstraints.WEST;
        gbc_outputErrorLabel.insets = new Insets(0, 0, 5, 5);
        gbc_outputErrorLabel.gridx = 1;
        gbc_outputErrorLabel.gridy = 26;
        panel_2.add(outputErrorLabel, gbc_outputErrorLabel);

        JSeparator separator_1 = new JSeparator();
        separator_1.setMinimumSize(new Dimension(5, 5));
        GridBagConstraints gbc_separator_1 = new GridBagConstraints();
        gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_separator_1.gridwidth = 3;
        gbc_separator_1.gridx = 0;
        gbc_separator_1.gridy = 27;
        panel_2.add(separator_1, gbc_separator_1);

        checkOfflineOnlyInputsState();
        checkAddDocumentationState();

        nameTF.getDocument().addDocumentListener(textFieldListener);
        entryPointPomTF.getDocument().addDocumentListener(textFieldListener);
        entryPointTF.getDocument().addDocumentListener(textFieldEntryPointListener);
        doxygenTF.getDocument().addDocumentListener(textFieldListener);
        plantumlTF.getDocument().addDocumentListener(textFieldListener);

        validateInputs();
        resolveEntryPoint();

        clearPomLabel();
        clearOutputLabel();
        clearNameLabel();
        clearDoxygenLabel();
        clearPlantUMLLabel();
    }

    private void checkAddDocumentationState() {
        boolean selected = addDocumentationCB.isSelected();
        doxygenTF.setEnabled(selected);
        plantumlTF.setEnabled(selected);
        btnBrowseDoxygen.setEnabled(selected);
        btnBrowsePlantuml.setEnabled(selected);
    }

    private void checkOfflineOnlyInputsState() {
        removeReposCB.setEnabled(radioOffline.isSelected());
        removeReposInfo.setEnabled(radioOffline.isSelected());
    }

    private void setExportData() {

        PropertyContext ctx = PropertyStore.getContext(PMBasics.PM_PROP_CTX);
        HashMap<String, ExportData> map = ctx.getValue(PMBasics.PM_PROP_LAST_EXPORT_SETTINGS_MAP,
                new HashMap<String, ExportData>());

        data = map.get(productFile.getFile().getAbsolutePath());
        if (data == null) {
            data = new ExportData();
        }

    }

    private void handleBrowse() {

        JFileChooser dialog = new JFileChooser(data.getOutputPath());

        dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        outputTF.setText(selection.getAbsolutePath());
        validateInputs();

    }

    private void handleBrowseEntryPoint() {

        JFileChooser dialog = new JFileChooser(data.getEntryPointPath());

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "JAR File (*.jar)";
            }

            @Override
            public boolean accept(File f) {
                if (f == null) {
                    return false;
                }

                return f.isDirectory() || f.getName().endsWith(".jar");
            }
        });

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        entryPointTF.setText(selection.getAbsolutePath());
        validateInputs();

    }

    private void handleBrowseEntryPointPOM() {

        JFileChooser dialog = new JFileChooser(
                data.getEntryPointPomPath() == null ? data.getEntryPointPath() : data.getEntryPointPomPath());

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "POM File (pom.xml)";
            }

            @Override
            public boolean accept(File f) {
                if (f == null) {
                    return false;
                }

                return f.isDirectory() || f.getName().equalsIgnoreCase("pom.xml");
            }
        });

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        entryPointPomTF.setText(selection.getAbsolutePath());
        validateInputs();

    }

    private void handleBrowseDoxygen() {
        JFileChooser dialog = new JFileChooser(data.getEntryPointPath());

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "Executable File";
            }

            @Override
            public boolean accept(File f) {
                if (f == null) {
                    return false;
                }

                return f.canExecute() || f.getName().endsWith(".exe");
            }
        });

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        doxygenTF.setText(selection.getAbsolutePath());
        validateInputs();
    }

    private void handleBrowsePlantUML() {
        JFileChooser dialog = new JFileChooser(data.getEntryPointPath());

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "JAR File (*.jar)";
            }

            @Override
            public boolean accept(File f) {
                if (f == null) {
                    return false;
                }

                return f.isDirectory() || f.getName().endsWith(".jar");
            }
        });

        int dialogResult = dialog.showOpenDialog(this);

        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selection = dialog.getSelectedFile();

        plantumlTF.setText(selection.getAbsolutePath());
        validateInputs();
    }

    private void resolveEntryPoint() {
        String entryPoint = entryPointTF.getText();

        if (entryPoint.isEmpty() == true) {
            clearEntryPointClassLabel();
            return;
        }

        File entryPointFile = new File(entryPoint);

        if (entryPoint.endsWith(".jar") == false || entryPointFile.exists() == false) {
            clearEntryPointClassLabel();
            return;
        }

        try {
            JarFile jar = new JarFile(entryPointFile);
            Attributes atts = jar.getManifest().getMainAttributes();
            String mainClass = atts.getValue("Main-Class");

            jar.close();

            if (mainClass == null) {
                entryPointClassLabel.setIcon(PluginRenderUtil.ERROR_ICON);
                entryPointClassLabel.setText("Missing Main-Class Attribute in Manifest.MF");
                return;
            }

            entryPointClassLabel.setText(mainClass);
            entryPointClassLabel.setIcon(PluginRenderUtil.CLASS_ICON);

        } catch (IOException e1) {

            e1.printStackTrace();
            clearEntryPointClassLabel();
            return;

        }

    }

    private void clearNameLabel() {
        nameErrorLabel.setText(DEFAULT_TEXT_NAME);
        nameErrorLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    private void clearPomLabel() {
        pomErrorLabel.setText(DEFAULT_TEXT_POM);
        pomErrorLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    private void clearDoxygenLabel() {
        doxygenErrorLabel.setText(DEFAULT_TEXT_DOXYGEN);
        doxygenErrorLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    private void clearPlantUMLLabel() {
        plantUMLErrorLabel.setText(DEFAULT_TEXT_PLANTUML);
        plantUMLErrorLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    private void clearEntryPointClassLabel() {
        entryPointClassLabel.setText(DEFAULT_TEXT_ENTRY_POINT);
        entryPointClassLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    private void clearOutputLabel() {
        outputErrorLabel.setText(DEFAULT_TEXT_OUTPUT);
        outputErrorLabel.setIcon(PluginRenderUtil.INFO_ICON);
    }

    protected void validateInputs() {
        String target = outputTF.getText();
        boolean targetValid = true;
        if (target == null || target.isEmpty()) {
            targetValid = false;

            outputErrorLabel.setText("Can't be empty");
            outputErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
        } else {

            File targetFile = new File(target);

            if (targetFile.exists() == false || targetFile.isDirectory() == false) {
                targetValid = false;

                outputErrorLabel.setText("Folder doesn't exist.");
                outputErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
            }

        }

        if (targetValid == true) {
            clearOutputLabel();
            data.setOutputPath(target);
        }

        String entryPoint = entryPointTF.getText();
        boolean entryPointValid = true;

        if (entryPoint == null || entryPoint.isEmpty()) {
            entryPointValid = false;
        } else {
            File entryPointFile = new File(entryPoint);

            if (entryPointFile.exists() == false) {
                entryPointValid = false;
            }
        }

        if (entryPointValid == true) {
            data.setEntryPointPath(entryPoint);
        }

        String name = nameTF.getText();

        boolean nameValid = true;

        if (name == null || name.isEmpty()) {
            nameValid = false;
            nameErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
            nameErrorLabel.setText("Can't be empty");
        } else {

            Matcher m = folderNamePattern.matcher(name);
            if (m.find() == true) {
                nameValid = false;
                nameErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
                nameErrorLabel.setText("Must match the OS rules of a folder name");
            } else {
                clearNameLabel();
            }
        }

        String epPom = entryPointPomTF.getText();
        boolean pomValid = true;
        if (epPom == null || epPom.isEmpty() || epPom.endsWith("pom.xml") == false) {
            pomValid = false;
            pomErrorLabel.setText("Has to be a valid pom.xml file");
            pomErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
        } else {

            File epPomFile = new File(epPom);

            if (epPomFile.exists() == false) {
                pomValid = false;
                pomErrorLabel.setText("File doesn't exist");
                pomErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
            } else {
                clearPomLabel();
            }

        }

        if (pomValid == true) {
            data.setEntryPointPomPath(epPom);
        }

        boolean doxygenValid = false;
        boolean plantumlValid = false;

        if (data.isAddDocumentation()) {
            String doxygen = doxygenTF.getText();
            doxygenValid = true;
            addDocumentationPanel.setEnabled(true);
            if (doxygen == null || doxygen.isEmpty()) {
                doxygenValid = false;
                doxygenErrorLabel.setText("Has to be a valid executable file");
                doxygenErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
            } else {
                File doxygenFile = new File(doxygen);

                if (doxygenFile.exists() == false) {
                    doxygenValid = false;
                    doxygenErrorLabel.setText("File doesn't exist");
                    doxygenErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
                } else {
                    clearDoxygenLabel();
                }
            }

            if (doxygenValid == true) {
                data.setDoxygenPath(doxygen);
            }

            String plantuml = plantumlTF.getText();
            plantumlValid = true;
            if (plantuml == null || plantuml.isEmpty() || plantuml.endsWith(".jar") == false
                    || plantuml.contains(" ")) {
                plantumlValid = false;
                plantUMLErrorLabel.setText("Has to be a valid jar file (without white spaces)");
                plantUMLErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
            } else {

                File plantUMLFile = new File(plantuml);

                if (plantUMLFile.exists() == false) {
                    plantumlValid = false;
                    plantUMLErrorLabel.setText("File doesn't exist");
                    plantUMLErrorLabel.setIcon(PluginRenderUtil.ERROR_ICON);
                } else {
                    clearPlantUMLLabel();
                }

            }

            if (plantumlValid == true) {
                data.setPlantUMLPath(plantuml);
            }

        } else {
            addDocumentationPanel.setEnabled(false);
        }
        boolean valid = false;

        if (data.isAddDocumentation()) {
            valid = nameValid && targetValid && entryPointValid && pomValid && doxygenValid && plantumlValid;
        } else {
            valid = nameValid && targetValid && entryPointValid && pomValid;
        }

        btnOk.setEnabled(valid);
    }

    private void handleOk() {
        int mavenInstallWarning = -1;

        if (data.isOnlineRelease()) {
            mavenInstallWarning = JOptionPane.showConfirmDialog(this,
                    "<html>The build attempts to resolve the Maven projects found in workspaces via the online repositories.<br> Make sure that an internet connection is available!",
                    "Deploy Application...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        } else {
            mavenInstallWarning = JOptionPane.showConfirmDialog(this,
                    "<html>The build attempts to resolve the Maven projects found in workspaces via the local repository.<br> Make sure they are installed into <font  color=\"#99b4d1\">"
                            + productFile.getLocalRepository() + "</font></html>",
                    "Deploy Application...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        }

        if (mavenInstallWarning != JOptionPane.OK_OPTION) {
            return;
        }

        PropertyContext ctx = PropertyStore.getContext(PMBasics.PM_PROP_CTX);
        HashMap<String, ExportData> map = ctx.getValue(PMBasics.PM_PROP_LAST_EXPORT_SETTINGS_MAP,
                new HashMap<String, ExportData>());

        map.put(productFile.getFile().getAbsolutePath(), data);
        productFile.setName(nameTF.getText());
        boolean canContinue = checkFolderExists(outputTF.getText(), productFile);

        if (canContinue == false) {
            return;
        }

        try {
            // Has to be saved before opening this dialog, so it can be saved afterwards and
            // only name is changed
            productFile.write();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ok = true;
        setVisible(false);

    }

    private void handleExit() {

        ok = false;

        setVisible(false);

    }

    public boolean isOk() {
        return ok;
    }

    public ExportData getExportData() {
        return data;
    }

    /**
     * @param targetPath
     * @param productFile
     * @return Continue?
     */
    private boolean checkFolderExists(String targetPath, ProductFile productFile) {

        File targetFolder = new File(targetPath);

        File folder = targetFolder.toPath().resolve(productFile.getName()).toFile();

        if (folder.exists() == false) {
            return true;
        }

        int rc = JOptionPane.showConfirmDialog(this, "Folder '" + productFile.getName() + "' exists. Continue?",
                "Folder exists...", JOptionPane.OK_CANCEL_OPTION);

        return rc == JOptionPane.OK_OPTION;

    }

}
