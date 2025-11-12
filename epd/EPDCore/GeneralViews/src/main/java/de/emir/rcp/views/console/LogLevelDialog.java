package de.emir.rcp.views.console;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import de.emir.tuml.ucore.runtime.resources.IconManager;

/**
 * A Log level selection dialog based on a table of classes which can be filtered.
 * 
 * @author Behrensen, Stefan <stefan.behrensen@dlr.de>
 */
public class LogLevelDialog extends JDialog {
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager
			.getLogger(LogLevelDialog.class);
    private TableRowSorter<ClassLogLevelTableModel> mSorter;
    private JTextField filterText;
	private JTable mTable;

    public LogLevelDialog(Frame owner) {
        super(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        Level[] levels = {Level.OFF, Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.FATAL, Level.ALL};
        JComboBox<String> cellLogLevels = new JComboBox<>();
        
        JScrollPane scrollPane = new JScrollPane();
        mTable = new JTable();
        mTable.setModel(new ClassLogLevelTableModel());
        TableColumn logLevelColumn = mTable.getColumnModel().getColumn(1);
        logLevelColumn.setCellEditor(new DefaultCellEditor(cellLogLevels));
        logLevelColumn.setPreferredWidth(cellLogLevels.getWidth());
        mSorter = new TableRowSorter(mTable.getModel());
        mTable.setRowSorter(mSorter);
        scrollPane.setViewportView(mTable);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(4, 4, 4, 4));
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{35, 68, 26, 0, 59, 76, 0};
        gbl_panel.rowHeights = new int[]{27, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        JButton btnClear = new JButton();
        btnClear.setIcon(new ImageIcon(IconManager.getImage(LogLevelDialog.class, "icons/emiricons/32/cancel.png", IconManager.preferedSmallIconSize())));
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                filterText.setText("");
                applyFilter("");
            }
        });
        filterText = new JTextField("");
        filterText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                applyFilter(filterText.getText());
            }
        });
        JLabel label = new JLabel("Filter: ");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(0, 0, 0, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        panel.add(label, gbc_label);
        GridBagConstraints gbc_filterText = new GridBagConstraints();
        gbc_filterText.fill = GridBagConstraints.HORIZONTAL;
        gbc_filterText.weightx = 1.0;
        gbc_filterText.anchor = GridBagConstraints.NORTH;
        gbc_filterText.insets = new Insets(0, 0, 0, 5);
        gbc_filterText.gridx = 1;
        gbc_filterText.gridy = 0;
        panel.add(filterText, gbc_filterText);
        GridBagConstraints gbc_btnClear = new GridBagConstraints();
        gbc_btnClear.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnClear.insets = new Insets(0, 0, 0, 5);
        gbc_btnClear.gridx = 2;
        gbc_btnClear.gridy = 0;
        panel.add(btnClear, gbc_btnClear);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
        gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
        gbc_horizontalGlue.gridx = 3;
        gbc_horizontalGlue.gridy = 0;
        panel.add(horizontalGlue, gbc_horizontalGlue);
        JLabel label_1 = new JLabel(" Set all to: ");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.WEST;
        gbc_label_1.insets = new Insets(0, 0, 0, 5);
        gbc_label_1.gridx = 4;
        gbc_label_1.gridy = 0;
        panel.add(label_1, gbc_label_1);
        JComboBox<String> rootLogLevels = new JComboBox<>();
        GridBagConstraints gbc_rootLogLevels = new GridBagConstraints();
        gbc_rootLogLevels.fill = GridBagConstraints.BOTH;
        gbc_rootLogLevels.anchor = GridBagConstraints.EAST;
        gbc_rootLogLevels.gridx = 5;
        gbc_rootLogLevels.gridy = 0;
        panel.add(rootLogLevels, gbc_rootLogLevels);
        for (Level level : levels) {
            rootLogLevels.addItem(level.toString());
            cellLogLevels.addItem(level.toString());
        }
        rootLogLevels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Level level = Level.toLevel((String) rootLogLevels.getSelectedItem());
                changeAllLogLevel(level, mTable.getModel());
                repaint();
            }
        });
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        resizeColumns();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeColumns();
            }
        });
        
    }

    /**
     * Make sure that the columns are resized in a way that we have more space for the longer class names(80/20).
     */
    private void resizeColumns() {
        int tableWidth = mTable.getColumnModel().getTotalColumnWidth();
        mTable.getColumnModel().getColumn(0).setPreferredWidth((int) Math.round(tableWidth * 0.8));
        mTable.getColumnModel().getColumn(1).setPreferredWidth((int) Math.round(tableWidth * 0.2));
    }
    
    /**
     * Change the log level for all classes which are currently visible in the table.
     *  
     * @param level the new level to set
     * @param model the TableModel to update
     */
    private void changeAllLogLevel(Level level, TableModel model) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.getRootLogger().setLevel(level);
        ctx.updateLoggers();
        applyFilter(filterText.getText()); // make sure filter is applied
        for (int i = 0; i < mSorter.getViewRowCount(); i++) {
            int j = mSorter.convertRowIndexToModel(i);
            model.setValueAt(level.toString(), j, 1);
        }
    }

    /**
     * Filter the table for a given expression.
     * 
     * @param filter the filter to apply to the table
     */
    private void applyFilter(String filter) {
    	if (filter == null || filter.isEmpty()) {
    		// Remove RowFilter altogether if String is null or empty
    		mSorter.setRowFilter(null);
    		return;
    	}
    	RowFilter<ClassLogLevelTableModel, Integer> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filter, 0);
        } catch (java.util.regex.PatternSyntaxException e) {
        	LOG.error("Filter cannot parse expression {}", filter);
            return;
        }
        mSorter.setRowFilter(rf);
    }
    
}
