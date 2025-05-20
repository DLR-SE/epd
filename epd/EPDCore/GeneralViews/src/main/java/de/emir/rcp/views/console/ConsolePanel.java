package de.emir.rcp.views.console;

import de.emir.model.universal.plugincore.var.impl.ConfigColorImpl;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;

public class ConsolePanel extends JPanel {

	public class LogCellRenderer implements ListCellRenderer<LogEvent> {


		public LogCellRenderer() {
	    }

	    @Override
		public Component getListCellRendererComponent(JList list, LogEvent value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = new JLabel();
			label.setOpaque(true);
	        // Assumes the stuff in the list has a pretty toString
			label.setText(index + ": " + value.getMessage().getFormattedMessage());
	        org.apache.logging.log4j.Level l = value.getLevel();
	        if (l.compareTo(Level.DEBUG) == 0) {
//	        	setBackground(Color.gray);
	        	label.setBackground(_mColorDebugBG.getAWTColor()); //using the shaddow variables directly to not always query the property
				label.setForeground(_mColorDebugFG.getAWTColor());
	        } else if (l.compareTo(Level.INFO) == 0) {
				label.setBackground(_mColorInfoBG.getAWTColor());
				label.setForeground(_mColorInfoFG.getAWTColor());
	        } else if (l.compareTo(Level.WARN) == 0) {
				label.setBackground(_mColorWarnBG.getAWTColor());
				label.setForeground(_mColorWarnFG.getAWTColor());
	        } else if (l.compareTo(Level.ERROR) == 0) {
				label.setBackground(_mColorErrorBG.getAWTColor());
				label.setForeground(_mColorErrorFG.getAWTColor());
	        } else if (l.compareTo(Level.TRACE) == 0) {
				label.setBackground(_mColorTraceBG.getAWTColor());
				label.setForeground(_mColorTraceFG.getAWTColor());
	        }
	        return label;
	    }
	}

	public static final String PROPERTY_CONTEXT = "de.emir.rcp.views.console.Console";
	public static final String PROP_MAXIMUM_MESSAGES = "MaximumMessages";
	public static final String PROP_COLOR_DEBUG_FG = "ConsoleColorDebugFG";
	public static final String PROP_COLOR_DEBUG_BG = "ConsoleColorDebugBG";
	public static final String PROP_COLOR_INFO_FG = "ConsoleColorInfoFG";
	public static final String PROP_COLOR_INFO_BG = "ConsoleColorInfoBG";
	public static final String PROP_COLOR_WARN_FG = "ConsoleColorWarnFG";
	public static final String PROP_COLOR_WARN_BG = "ConsoleColorWarnBG";
	public static final String PROP_COLOR_ERROR_FG = "ConsoleColorErrorFG";
	public static final String PROP_COLOR_ERROR_BG = "ConsoleColorErrorBG";
	public static final String PROP_COLOR_TRACE_FG = "ConsoleColorTraceFG";
	public static final String PROP_COLOR_TRACE_BG = "ConsoleColorTraceBG";
	
	private JList<LogEvent> 					mList;
	private DefaultListModel<LogEvent> 			mListModel;
	private IProperty<Integer>						mMaximumMessages;
	private IProperty<ConfigColorImpl>						mColorDebugFG;
	private ConfigColorImpl									_mColorDebugFG; //using shadow variables since getting the value from the property on each message is more expensive as we first thought
	private IProperty<ConfigColorImpl>						mColorDebugBG;
	private ConfigColorImpl									_mColorDebugBG;
	private IProperty<ConfigColorImpl>						mColorInfoFG;
	private ConfigColorImpl									_mColorInfoFG;
	private IProperty<ConfigColorImpl>						mColorInfoBG;
	private ConfigColorImpl									_mColorInfoBG;
	private IProperty<ConfigColorImpl>						mColorWarnFG;
	private ConfigColorImpl									_mColorWarnFG;
	private IProperty<ConfigColorImpl>						mColorWarnBG;
	private ConfigColorImpl									_mColorWarnBG;
	private IProperty<ConfigColorImpl>						mColorErrorFG;
	private ConfigColorImpl									_mColorErrorFG;
	private IProperty<ConfigColorImpl>						mColorErrorBG;
	private ConfigColorImpl									_mColorErrorBG;
	private IProperty<ConfigColorImpl>						mColorTraceFG;
	private ConfigColorImpl									_mColorTraceFG;
	private IProperty<ConfigColorImpl>						mColorTraceBG;
	private ConfigColorImpl									_mColorTraceBG;
	
	
	private boolean mTailing = true;
	private int _mMaximumMessages = 1000; //shadow variable for fast access
	
	
	
	/**
	 * Create the panel.
	 */
	public ConsolePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		mList = new JList<>();
		mList.setModel(mListModel = new DefaultListModel<>());
		scrollPane.setViewportView(mList);
		mList.setCellRenderer(new LogCellRenderer());
		
		PropertyContext context = PropertyStore.getContext(PROPERTY_CONTEXT);
		mMaximumMessages = context.getProperty(PROP_MAXIMUM_MESSAGES, 1000);
		mMaximumMessages.addPropertyChangeListener(pcl -> _mMaximumMessages = mMaximumMessages.getValue());
		_mMaximumMessages = mMaximumMessages.getValue();
		
		mColorDebugFG = context.getProperty(PROP_COLOR_DEBUG_FG, new ConfigColorImpl(java.awt.Color.LIGHT_GRAY));
		_mColorDebugFG = mColorDebugFG.getValue(); //initialize shadow variable
		mColorDebugFG.addPropertyChangeListener(pcl -> _mColorDebugFG = (ConfigColorImpl) pcl.getNewValue() ); //change shaddow variable if property change
		mColorDebugBG = context.getProperty(PROP_COLOR_DEBUG_BG, new ConfigColorImpl(UIManager.getColor("List.background")));
		_mColorDebugBG = mColorDebugBG.getValue();
		mColorDebugBG.addPropertyChangeListener(pcl -> _mColorDebugBG = (ConfigColorImpl) pcl.getNewValue() );
		
		mColorInfoFG = context.getProperty(PROP_COLOR_INFO_FG, new ConfigColorImpl(java.awt.Color.LIGHT_GRAY));
		_mColorInfoFG = mColorInfoFG.getValue();
		mColorInfoFG.addPropertyChangeListener(pcl -> _mColorInfoFG = (ConfigColorImpl) pcl.getNewValue() );		
		mColorInfoBG = context.getProperty(PROP_COLOR_INFO_BG, new ConfigColorImpl(java.awt.Color.DARK_GRAY));
		_mColorInfoBG = mColorInfoBG.getValue();
		mColorInfoBG.addPropertyChangeListener(pcl -> _mColorInfoBG = (ConfigColorImpl) pcl.getNewValue() );
		
		mColorWarnFG = context.getProperty(PROP_COLOR_WARN_FG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorWarnFG = mColorWarnFG.getValue();
		mColorWarnFG.addPropertyChangeListener(pcl -> _mColorWarnFG = (ConfigColorImpl) pcl.getNewValue() );
		mColorWarnBG = context.getProperty(PROP_COLOR_WARN_BG, new ConfigColorImpl(java.awt.Color.ORANGE));
		_mColorWarnBG = mColorWarnBG.getValue();
		mColorWarnBG.addPropertyChangeListener(pcl -> _mColorWarnBG = (ConfigColorImpl) pcl.getNewValue() );
		
		mColorErrorFG = context.getProperty(PROP_COLOR_ERROR_FG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorErrorFG = mColorErrorFG.getValue();
		mColorErrorFG.addPropertyChangeListener(pcl -> _mColorErrorFG = (ConfigColorImpl) pcl.getNewValue() );
		mColorErrorBG = context.getProperty(PROP_COLOR_ERROR_BG, new ConfigColorImpl(java.awt.Color.RED));
		_mColorErrorBG = mColorErrorBG.getValue();
		mColorErrorBG.addPropertyChangeListener(pcl -> _mColorErrorBG = (ConfigColorImpl) pcl.getNewValue() );
		
		mColorTraceFG = context.getProperty(PROP_COLOR_TRACE_FG, new ConfigColorImpl(java.awt.Color.GRAY));
		_mColorTraceFG = mColorTraceFG.getValue();
		mColorTraceFG.addPropertyChangeListener(pcl -> _mColorTraceFG = (ConfigColorImpl) pcl.getNewValue() );
		mColorTraceBG = context.getProperty(PROP_COLOR_TRACE_BG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorTraceBG = mColorTraceBG.getValue();
		mColorTraceBG.addPropertyChangeListener(pcl -> _mColorTraceBG = (ConfigColorImpl) pcl.getNewValue() );
	}

	
	public void enableTailing(boolean b){
		mTailing = b;
	}
	public boolean isTailingEnabled() { return mTailing;}
	
	public void clear(){
		if (mListModel != null)
			mListModel.clear();
	}
	
	/**
	 * Adds a logging event to the JList
	 * @note this method has to be called from the UI Thread
	 * @param evt
	 */
	public void addMessage(LogEvent evt) {
		mListModel.addElement(evt);
		if (_mMaximumMessages > 0 && mListModel.getSize() > _mMaximumMessages)
			while(mListModel.size() > _mMaximumMessages)
				mListModel.remove(0);
		if (mTailing)
			mList.ensureIndexIsVisible(mListModel.size()-1);
	}

}
