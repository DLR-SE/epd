package de.emir.rcp.views.console.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SetClassLogLevelCommand extends AbstractCommand {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(SetClassLogLevelCommand.class);

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

            JComboBox<String> rootLogLevels = new JComboBox<>();
            JComboBox<String> cellLogLevels = new JComboBox<>();

            for (Level level : levels) {
                rootLogLevels.addItem(level.toString());
                cellLogLevels.addItem(level.toString());
            }

            JScrollPane scrollPane = new JScrollPane();

            JTable table = new JTable();
            table.setModel(new CustomTableModel());
            TableColumn logLevelColumn = table.getColumnModel().getColumn(1);
            logLevelColumn.setCellEditor(new DefaultCellEditor(cellLogLevels));
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            scrollPane.setViewportView(table);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(new JLabel("Set all to: "));
            panel.add(rootLogLevels);

            rootLogLevels.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Level level = Level.toLevel((String) rootLogLevels.getSelectedItem());
                    changeAllLogLevel(level, table.getModel());
                    repaint();
                }
            });
            add(panel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }

        private void changeAllLogLevel(Level level, TableModel model) {
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            ctx.getRootLogger().setLevel(level);
            ctx.updateLoggers();
            for(int i=0; i<model.getRowCount(); i++) {
                model.setValueAt(level.toString(), i, 1);
            }
        }
    }

    public class CustomTableModel extends DefaultTableModel {

        @Override
        public int getRowCount() {
            LoggerContext logContext = (LoggerContext) LogManager
                    .getContext(false);
            return logContext.getLoggers().size();
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
            LoggerContext logContext = (LoggerContext) LogManager
                    .getContext(false);
            Collection<Logger> config = logContext
                    .getLoggers();
            if(rowIndex <= config.size()) {
                Logger conf = (Logger) config.toArray()[rowIndex];
                if(columnIndex == 0) {
                    return conf.getName();
                } else if (columnIndex == 1) {
                    return conf.getLevel().toString();
                }
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            LoggerContext logContext = (LoggerContext) LogManager
                    .getContext(false);
            Collection<Logger> config = logContext.getLoggers();
            if(rowIndex <= config.size()) {
                if (columnIndex == 1) {
                    String loggerName = getValueAt(rowIndex, 0).toString();
                    Logger conf = config.stream().filter(logger -> logger.getName().equals(loggerName)).findFirst().orElse(null);
                    if(conf != null) {
                        Configurator.setLevel(conf, Level.toLevel(aValue.toString()));
                        logContext.updateLoggers();
                    }
                }
            }
        }
    }
}
