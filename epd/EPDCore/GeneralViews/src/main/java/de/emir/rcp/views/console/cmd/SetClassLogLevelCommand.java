package de.emir.rcp.views.console.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class SetClassLogLevelCommand extends AbstractCommand {

    @Override
    public void execute() {
        LogLevelDialog dialog = new LogLevelDialog(PlatformUtil.getWindowManager().getMainWindow());
        dialog.setSize(400, 500);
        dialog.setVisible(true);
    }

    private class LogLevelDialog extends JDialog {

        public LogLevelDialog(Frame owner) {
            super(owner);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            JScrollPane scrollPane = new JScrollPane();

            JTable table = new JTable();
            table.setModel(new CustomTableModel());
            TableColumn tableColumn = table.getColumnModel().getColumn(1);
            tableColumn.setCellEditor(new CustomComboBoxEditor());
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            scrollPane.setViewportView(table);


            Level[] levels = {
                    Level.OFF,
                    Level.TRACE,
                    Level.DEBUG,
                    Level.INFO,
                    Level.WARN,
                    Level.ERROR,
                    Level.FATAL,
                    Level.ALL
            };

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(new JLabel("Set all to: "));
            JComboBox<String> comboBox = new JComboBox<>();
            panel.add(comboBox);
            for (Level level : levels) {
                comboBox.addItem(level.toString());
            }

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Level level = Level.toLevel((String) comboBox.getSelectedItem());
                    changeAllLogLevel(level);
                    repaint();
                }
            });


            add(panel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }

        private void changeAllLogLevel(Level level) {
            if (level != null) {
                Enumeration enumeration = LogManager.getCurrentLoggers();

                while (enumeration.hasMoreElements()) {
                    Object obj = enumeration.nextElement();
                    if (obj instanceof Category) {
                        Category logger = (Category) obj;
                        logger.setLevel(level);
                    }

                }
            }
        }
    }

    public class CustomTableModel extends DefaultTableModel {

        @Override
        public int getRowCount() {
            Enumeration enumeration = LogManager.getCurrentLoggers();
            int counter = 0;
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement();
                counter++;
            }

            return counter;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Logger Class";
                case 1:
                    return "Log Level";
            }
            return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return String.class;
            }

            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Enumeration enumeration = LogManager.getCurrentLoggers();
            int counter = 0;
            while (enumeration.hasMoreElements()) {
                Object obj = enumeration.nextElement();

                if (counter == rowIndex) {
                    Logger logger = (Logger) obj;
                    if (columnIndex == 0) {
                        return logger.getName();
                    } else if (columnIndex == 1) {
                        Level level = resolveLevel(logger);
                        return level.toString();
                    }
                }

                counter++;
            }


            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Enumeration enumeration = LogManager.getCurrentLoggers();
            int counter = 0;
            while (enumeration.hasMoreElements()) {
                Object obj = enumeration.nextElement();

                if (counter == rowIndex) {
                    Logger logger = (Logger) obj;
                    if (columnIndex == 1) {
                        String level = (String) aValue;
                        logger.setLevel(Level.toLevel(level, Level.INFO));
                    }
                    break;
                }

                counter++;
            }
        }

        private Level resolveLevel(Category logger) {
            if (logger == null) return null;
            Level level = logger.getLevel();
            if (level == null) {
                return resolveLevel(logger.getParent());
            }

            return level;
        }
    }

    /**
     * Custom class for adding elements in the JComboBox.
     */
    public class CustomComboBoxEditor extends DefaultCellEditor {

        // Declare a model that is used for adding the elements to the `Combo box`
        private DefaultComboBoxModel model;

        public CustomComboBoxEditor() {
            super(new JComboBox());
            this.model = (DefaultComboBoxModel) ((JComboBox) getComponent()).getModel();
            model.addElement(Level.OFF.toString());
            model.addElement(Level.TRACE.toString());
            model.addElement(Level.DEBUG.toString());
            model.addElement(Level.INFO.toString());
            model.addElement(Level.WARN.toString());
            model.addElement(Level.ERROR.toString());
            model.addElement(Level.FATAL.toString());
            model.addElement(Level.ALL.toString());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // Add the elements which you want to the model.
            // Here I am adding elements from the orderList(say) which you can pass via constructor to this class.

            //finally return the component.
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
    }
}
