package de.emir.rcp.views.console;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.types.Color;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ConsolePanel extends JPanel {

	public class LogCellRenderer extends JLabel implements ListCellRenderer<LoggingEvent> {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 928904714024941298L;

		public LogCellRenderer() {
	        setOpaque(true);
	    }

	    @Override
		public Component getListCellRendererComponent(JList list, LoggingEvent value, int index, boolean isSelected, boolean cellHasFocus) {
	        // Assumes the stuff in the list has a pretty toString
	        setText(index + ": " + value.getMessage().toString());
	        Level l = value.getLevel();
	        if (l == Level.DEBUG) {
//	        	setBackground(Color.gray);
	        	setBackground(_mColorDebugBG.getAWTColor()); //using the shaddow variables directly to not always query the property
	        	setForeground(_mColorDebugFG.getAWTColor());
	        } else if (l == Level.INFO) {
	        	setBackground(_mColorInfoBG.getAWTColor());
	        	setForeground(_mColorInfoFG.getAWTColor());
	        } else if (l == Level.WARN) {
	        	setBackground(_mColorWarnBG.getAWTColor());
	        	setForeground(_mColorWarnFG.getAWTColor());
	        } else if (l == Level.ERROR) {
	        	setBackground(_mColorErrorBG.getAWTColor());
        		setForeground(_mColorErrorFG.getAWTColor());
	        } else if (l == Level.TRACE) {
	        	setBackground(_mColorTraceBG.getAWTColor());
        		setForeground(_mColorTraceFG.getAWTColor());
	        }
	        return this;
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
	
	private JList<LoggingEvent> 					mList;
	private DefaultListModel<LoggingEvent> 			mListModel;
	private IProperty<Integer>						mMaximumMessages;
	private IProperty<Color>						mColorDebugFG;
	private Color									_mColorDebugFG; //using shadow variables since getting the value from the property on each message is more expensive as we first thought
	private IProperty<Color>						mColorDebugBG;
	private Color									_mColorDebugBG;
	private IProperty<Color>						mColorInfoFG;
	private Color									_mColorInfoFG;
	private IProperty<Color>						mColorInfoBG;
	private Color									_mColorInfoBG;
	private IProperty<Color>						mColorWarnFG;
	private Color									_mColorWarnFG;
	private IProperty<Color>						mColorWarnBG;
	private Color									_mColorWarnBG;
	private IProperty<Color>						mColorErrorFG;
	private Color									_mColorErrorFG;
	private IProperty<Color>						mColorErrorBG;
	private Color									_mColorErrorBG;
	private IProperty<Color>						mColorTraceFG;
	private Color									_mColorTraceFG;
	private IProperty<Color>						mColorTraceBG;
	private Color									_mColorTraceBG;
	
	
	private boolean mTailing = true;
	private int _mMaximumMessages = 1000; //shadow variable for fast access
	
	
	
	/**
	 * Create the panel.
	 */
	public ConsolePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		mList = new JList<LoggingEvent>();
		mList.setModel(mListModel = new DefaultListModel<>());
		scrollPane.setViewportView(mList);
		mList.setCellRenderer(new LogCellRenderer());
		
		PropertyContext context = PropertyStore.getContext(PROPERTY_CONTEXT);
		mMaximumMessages = context.getProperty(PROP_MAXIMUM_MESSAGES, 99000);
		mMaximumMessages.addPropertyChangeListener(pcl -> _mMaximumMessages = mMaximumMessages.getValue());
		_mMaximumMessages = mMaximumMessages.getValue();
		
		mColorDebugFG = context.getProperty(PROP_COLOR_DEBUG_FG, new Color(java.awt.Color.LIGHT_GRAY));
		_mColorDebugFG = mColorDebugFG.getValue(); //initialize shadow variable
		mColorDebugFG.addPropertyChangeListener(pcl -> _mColorDebugFG = (Color) pcl.getNewValue() ); //change shaddow variable if property change
		mColorDebugBG = context.getProperty(PROP_COLOR_DEBUG_BG, new Color(UIManager.getColor("List.background")));
		_mColorDebugBG = mColorDebugBG.getValue();
		mColorDebugBG.addPropertyChangeListener(pcl -> _mColorDebugBG = (Color) pcl.getNewValue() );
		
		mColorInfoFG = context.getProperty(PROP_COLOR_INFO_FG, new Color(java.awt.Color.LIGHT_GRAY));
		_mColorInfoFG = mColorInfoFG.getValue();
		mColorInfoFG.addPropertyChangeListener(pcl -> _mColorInfoFG = (Color) pcl.getNewValue() );		
		mColorInfoBG = context.getProperty(PROP_COLOR_INFO_BG, new Color(java.awt.Color.DARK_GRAY));
		_mColorInfoBG = mColorInfoBG.getValue();
		mColorInfoBG.addPropertyChangeListener(pcl -> _mColorInfoBG = (Color) pcl.getNewValue() );
		
		mColorWarnFG = context.getProperty(PROP_COLOR_WARN_FG, new Color(java.awt.Color.BLACK));
		_mColorWarnFG = mColorWarnFG.getValue();
		mColorWarnFG.addPropertyChangeListener(pcl -> _mColorWarnFG = (Color) pcl.getNewValue() );
		mColorWarnBG = context.getProperty(PROP_COLOR_WARN_BG, new Color(java.awt.Color.ORANGE));
		_mColorWarnBG = mColorWarnBG.getValue();
		mColorWarnBG.addPropertyChangeListener(pcl -> _mColorWarnBG = (Color) pcl.getNewValue() );
		
		mColorErrorFG = context.getProperty(PROP_COLOR_ERROR_FG, new Color(java.awt.Color.BLACK));
		_mColorErrorFG = mColorErrorFG.getValue();
		mColorErrorFG.addPropertyChangeListener(pcl -> _mColorErrorFG = (Color) pcl.getNewValue() );
		mColorErrorBG = context.getProperty(PROP_COLOR_ERROR_BG, new Color(java.awt.Color.RED));
		_mColorErrorBG = mColorErrorBG.getValue();
		mColorErrorBG.addPropertyChangeListener(pcl -> _mColorErrorBG = (Color) pcl.getNewValue() );
		
		mColorTraceFG = context.getProperty(PROP_COLOR_TRACE_FG, new Color(java.awt.Color.GRAY));
		_mColorTraceFG = mColorTraceFG.getValue();
		mColorTraceFG.addPropertyChangeListener(pcl -> _mColorTraceFG = (Color) pcl.getNewValue() );
		mColorTraceBG = context.getProperty(PROP_COLOR_TRACE_BG, new Color(java.awt.Color.BLACK));
		_mColorTraceBG = mColorTraceBG.getValue();
		mColorTraceBG.addPropertyChangeListener(pcl -> _mColorTraceBG = (Color) pcl.getNewValue() );
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
	public void addMessage(LoggingEvent evt) {
		mListModel.addElement(evt);
		if (_mMaximumMessages > 0 && mListModel.getSize() > _mMaximumMessages)
			while(mListModel.size() > _mMaximumMessages)
				mListModel.remove(0);
		if (mTailing)
			mList.ensureIndexIsVisible(mListModel.size()-1);
	}

}
