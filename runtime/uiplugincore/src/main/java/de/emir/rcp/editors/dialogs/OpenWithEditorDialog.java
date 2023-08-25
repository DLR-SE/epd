package de.emir.rcp.editors.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.manager.util.PlatformUtil;

/**
 * A classic "Open With..." dialog
 * 
 * @author fklein
 *
 */
public class OpenWithEditorDialog extends JDialog {

    private JButton btnOk;
    private JButton btnCancel;
    private JTree tree;

    private String selectedEditorID;

    public OpenWithEditorDialog(JFrame parent, String message, String fileName) {
        super(parent, true);

        // set minimal size
        this.setMinimumSize(new Dimension(300, 380));

        // center
        this.setLocationRelativeTo(null);

        // set close operation
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // wire elements together
        setTitle("Open with...");

        String headText = "Choose an editor for opening " + fileName + ":";

        if (message != null) {

            headText = message + "<br />" + headText;

        }
        headText = "<html>" + headText + "</html>";
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
        gridBagLayout.columnWeights = new double[] { 1.0 };

        // gridBagLayout.columnWeights = new double[] { 1.0 };
        // gridBagLayout.rowWeights = new double[] { 0.2, 0.8, 0.0 };
        getContentPane().setLayout(gridBagLayout);

        DefaultTreeModel model = new DefaultTreeModel(createNodes());

        JLabel lblSelectAView = new JLabel(headText);
        lblSelectAView.setVerticalAlignment(SwingConstants.TOP);
        lblSelectAView.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSelectAView = new GridBagConstraints();
        gbc_lblSelectAView.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblSelectAView.anchor = GridBagConstraints.NORTH;
        gbc_lblSelectAView.insets = new Insets(10, 10, 5, 10);

        gbc_lblSelectAView.gridx = 0;
        gbc_lblSelectAView.gridy = 0;

        getContentPane().add(lblSelectAView, gbc_lblSelectAView);

        tree = new JTree(model);
        tree.setCellRenderer(new OpenWithEditorTreeCellRenderer());

        JScrollPane sc = new JScrollPane(tree);

        sc.setBorder(new LineBorder(UIManager.getColor("windowBorder")));
        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.insets = new Insets(5, 10, 10, 10);
        gbc_sc.fill = GridBagConstraints.BOTH;

        gbc_sc.gridx = 0;
        gbc_sc.gridy = 1;
        getContentPane().add(sc, gbc_sc);

        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        getContentPane().add(panel_1, gbc_panel_1);

        chckbxAlwaysUseThis = new JCheckBox("Always use this editor");
        panel_1.add(chckbxAlwaysUseThis);

        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.anchor = GridBagConstraints.NORTHEAST;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 3;
        getContentPane().add(panel, gbc_panel);

        btnOk = new JButton("OK");
        panel.add(btnOk);

        btnOk.setEnabled(false);
        chckbxAlwaysUseThis.setEnabled(false);

        btnCancel = new JButton("Cancel");
        panel.add(btnCancel);

        addListeners();

    }

    private void addListeners() {
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                finish();

            }
        });

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (selection == null || selection.getUserObject() == null) {

                    btnOk.setEnabled(false);
                    chckbxAlwaysUseThis.setEnabled(false);

                } else {

                    btnOk.setEnabled(true);
                    chckbxAlwaysUseThis.setEnabled(true);
                }

            }
        });

        tree.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    finish();

                }
            }

        });

    }

    private void finish() {

        selectedEditorID = null;

        DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (selection == null || selection.getUserObject() == null) {

            btnOk.setEnabled(false);

        } else {

            Editor editor = (Editor) selection.getUserObject();
            selectedEditorID = editor.getId();
            dispose();
        }

    }

    public String getSelectedEditorID() {
        return selectedEditorID;
    }

    private DefaultMutableTreeNode createNodes() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        Map<String, Editor> editors = PlatformUtil.getEditorManager().getEditorExtensionPoint().getEditors();

        for (Entry<String, Editor> entry : editors.entrySet()) {
            root.add(new DefaultMutableTreeNode(entry.getValue()));
        }

        return root;

    }

    public boolean isAlwaysUseThisSet() {
        return chckbxAlwaysUseThis.isSelected();
    }

    /**
     * 
     */
    private static final long serialVersionUID = -6566622285770162507L;
    private JPanel panel;
    private JPanel panel_1;
    private JCheckBox chckbxAlwaysUseThis;
}
