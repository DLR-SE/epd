package de.emir.rcp.views.console;

import java.util.Collection;

import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

public class ClassLogLevelTableModel extends DefaultTableModel {

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