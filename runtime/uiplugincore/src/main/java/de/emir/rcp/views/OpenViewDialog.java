package de.emir.rcp.views;

import de.emir.rcp.manager.ViewManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.ui.widgets.JTreeWithFilter;
import de.emir.rcp.views.ep.GroupUtils;
import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.rcp.views.ep.ViewGroup;
import de.emir.ui.utils.TreeUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class OpenViewDialog extends JDialog {

    private JButton btnOk;
    private JButton btnCancel;
    private JTreeWithFilter tree;

    public OpenViewDialog(JFrame parent) {
        super(parent, true);

        // set minimal size
        this.setMinimumSize(new Dimension(300, 500));

        // center
        this.setLocationRelativeTo(null);

        // set close operation
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // wire elements together
        setTitle("Open View...");
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        JLabel lblSelectAView = new JLabel("Select a View to open:");
        springLayout.putConstraint(SpringLayout.NORTH, lblSelectAView, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblSelectAView, 10, SpringLayout.WEST, getContentPane());
        getContentPane().add(lblSelectAView);

        //DefaultTreeModel model = new DefaultTreeModel(createNodes());

        tree = new JTreeWithFilter();
        //tree.setRootVisible(false);
        tree.setCellRenderer(new ViewSelectionTreeCellRenderer());
        tree.setRootNode(createNodes());
        tree.setFilterInfoText("Filter...");
        tree.setFilterMatcher(new OpenViewFilterMatcher());
        
        JScrollPane sc = new JScrollPane(tree);

        sc.setBorder(new LineBorder(UIManager.getColor("Separator.foreground")));
        springLayout.putConstraint(SpringLayout.WEST, sc, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, sc, -10, SpringLayout.EAST, getContentPane());
        getContentPane().add(sc);

        btnCancel = new JButton("Cancel");
        springLayout.putConstraint(SpringLayout.SOUTH, sc, -15, SpringLayout.NORTH, btnCancel);
        springLayout.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, getContentPane());
        getContentPane().add(btnCancel);

        btnOk = new JButton("OK");

        springLayout.putConstraint(SpringLayout.SOUTH, btnOk, 0, SpringLayout.SOUTH, btnCancel);
        springLayout.putConstraint(SpringLayout.EAST, btnOk, -6, SpringLayout.WEST, btnCancel);
        getContentPane().add(btnOk);

        addListeners();

        btnOk.setEnabled(false);
        
        springLayout.putConstraint(SpringLayout.NORTH, sc, 6, SpringLayout.SOUTH, lblSelectAView);
    }

    private void addListeners() {
        btnOk.addActionListener(e -> finish());

        btnCancel.addActionListener(e -> dispose());

        tree.addTreeSelectionListener(e -> {
//                DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

            if (selection == null || selection.getUserObject() == null) {

                btnOk.setEnabled(false);

            } else {

                btnOk.setEnabled(true);

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

//        DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    	DefaultMutableTreeNode selection = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
    	
        if (selection == null || selection.getUserObject() == null) {

            btnOk.setEnabled(false);

        } else {
            Object obj = selection.getUserObject();
            if (obj instanceof ViewDescriptor) {
                ViewDescriptor view = (ViewDescriptor) selection.getUserObject();
                PlatformUtil.getViewManager().openView(view.getId());
                dispose();
            }
        }

    }

    private DefaultMutableTreeNode createNodes() {

        ViewManager manager = PlatformUtil.getViewManager();

        List<ViewGroup> views = manager.getInvisibleGroups();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (ViewGroup group : views) {
            if (group.getId() == null) {
                List<ViewDescriptor> descriptors = manager.getInvisibleViewDescriptors(GroupUtils.convert(group));
                for (ViewDescriptor desc : descriptors) {
                    root.add(new DefaultMutableTreeNode(desc));
                }

            } else {
                root.add(createNode(group));

            }
        }

        return TreeUtils.sort(root);

    }

    private DefaultMutableTreeNode createNode(ViewGroup group) {
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);

        List<ViewDescriptor> descriptors = PlatformUtil.getViewManager().getInvisibleViewDescriptors(group.getViews());
        for (ViewDescriptor view : descriptors) {
            groupNode.add(new DefaultMutableTreeNode(view));
        }

        for (ViewGroup sub : group.getSubGroups().values()) {
            groupNode.add(createNode(sub));
        }


        return groupNode;
    }

    /**
     *
     */
    private static final long serialVersionUID = -6566622285770162507L;

}
