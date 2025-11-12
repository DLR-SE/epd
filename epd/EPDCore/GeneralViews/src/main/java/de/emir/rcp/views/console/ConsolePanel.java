package de.emir.rcp.views.console;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;

import com.google.common.collect.EvictingQueue;

import de.emir.model.universal.plugincore.var.impl.ConfigColorImpl;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;

/**
 * This Component contains individual JLabels for each logged event. The format and style is loaded from properties and
 * does not necessarily match the format defined in the log4j.xml. Logged events are collected in an EvictingQueue to
 * prevent a buffer overflow. This also means that event might eventually be lost and not displayed on the UI if the
 * number of events per UI cycle exceeds the number of items that can be displayed without crashing the UI thread. This
 * can be balanced out with the variable _mMaximumMessages, CHUNK_SIZE and TIMER_DELAY.
 */
public class ConsolePanel extends JPanel {
	public static final String PROPERTY_CONTEXT = "de.emir.rcp.views.console.Console";
	public static final String PROP_MAXIMUM_MESSAGES = "MaximumMessages";
	public static final String PROP_DISPLAY_LOG_INDEX = "DisplayLogIndex";
	public static final String PROP_DISPLAY_LOG_TIME = "DisplayLogTime";
	public static final String PROP_DISPLAY_LOG_LEVEL = "DisplayLogLevel";
	public static final String PROP_DISPLAY_LOG_ICON = "DisplayLogIcon";
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

	private JList<LogEvent> mList;
	private DefaultListModel<LogEvent> mListModel;
	private IProperty<Integer> mMaximumMessages;
	private IProperty<Boolean> mDisplayLogIndex;
	private Boolean _mDisplayLogIndex;
	private IProperty<Boolean> mDisplayLogTime;
	private Boolean _mDisplayLogTime;
	private Boolean _mDisplayLogLevel;
	private IProperty<Boolean> mDisplayLogLevel;
	private Boolean _mDisplayLogIcon;
	private IProperty<Boolean> mDisplayLogIcon;
	private IProperty<ConfigColorImpl> mColorDebugFG;
	private ConfigColorImpl _mColorDebugFG; // using shadow variables since getting the value from the property on each
											// message is more expensive as we first thought
	private IProperty<ConfigColorImpl> mColorDebugBG;
	private ConfigColorImpl _mColorDebugBG;
	private IProperty<ConfigColorImpl> mColorInfoFG;
	private ConfigColorImpl _mColorInfoFG;
	private IProperty<ConfigColorImpl> mColorInfoBG;
	private ConfigColorImpl _mColorInfoBG;
	private IProperty<ConfigColorImpl> mColorWarnFG;
	private ConfigColorImpl _mColorWarnFG;
	private IProperty<ConfigColorImpl> mColorWarnBG;
	private ConfigColorImpl _mColorWarnBG;
	private IProperty<ConfigColorImpl> mColorErrorFG;
	private ConfigColorImpl _mColorErrorFG;
	private IProperty<ConfigColorImpl> mColorErrorBG;
	private ConfigColorImpl _mColorErrorBG;
	private IProperty<ConfigColorImpl> mColorTraceFG;
	private ConfigColorImpl _mColorTraceFG;
	private IProperty<ConfigColorImpl> mColorTraceBG;
	private ConfigColorImpl _mColorTraceBG;

	private boolean mTailing = true;
	/** Shadow variable for fast access to the maximum messages. **/
	private int _mMaximumMessages = 1000;  

	/** This Evictingqueue should prevent memory problems with too many log entries. **/
	private EvictingQueue<LogEvent> mQ = EvictingQueue.create(_mMaximumMessages);
	
	/** This number of items is the panel allowed to add/remove during each timed activation. **/
	private static final int CHUNK_SIZE = 100;
	/** The delay between timed activation on the UI thread to add/remove UI elements from the log queue. **/
	private static final int TIMER_DELAY = 50;
	
	private static final Icon ICON_INFO = IconManager.getIcon(ConsolePanel.class, "icons/emiricons/32/info.png", IconManager.preferedSmallIconSize());
	private static final Icon ICON_ERROR = IconManager.getIcon(ConsolePanel.class, "icons/emiricons/32/dangerous.png", IconManager.preferedSmallIconSize());
	private static final Icon ICON_WARNING = IconManager.getIcon(ConsolePanel.class, "icons/emiricons/32/warning.png", IconManager.preferedSmallIconSize());
	private static final Icon ICON_DEBUG = IconManager.getIcon(ConsolePanel.class, "icons/emiricons/32/critical_bug.png", IconManager.preferedSmallIconSize());
	private static final Icon ICON_TRACE = IconManager.getIcon(ConsolePanel.class, "icons/emiricons/32/code.png", IconManager.preferedSmallIconSize());
	private javax.swing.Timer mTimer;
	
	/**
	 * This Renderer defines the style for each log entry in the console view.
	 */
	public class LogCellRenderer implements ListCellRenderer<LogEvent> {
		public LogCellRenderer() {
		}

		@Override
		public Component getListCellRendererComponent(JList list, LogEvent value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = new JLabel();
			label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, UIManager.getFont("Label.font").getSize()));
			label.setOpaque(true);
			// Constructs the actual log entries from index, time, level and message. This is different from the format 
			// in the log.txt file and the stdout.
			StringBuilder sb = new StringBuilder();
			if (_mDisplayLogIndex) {
				sb.append(String.format("%1$" + (int) (Math.log10(_mMaximumMessages) + 1) + "s", index)).append(" ");
			}
			if (_mDisplayLogTime) {
				sb.append(String.format("%1$24s", Instant.ofEpochMilli(value.getTimeMillis()).toString())).append(" ");
			}
			if (_mDisplayLogLevel) {
				sb.append(String.format("%1$5s", value.getLevel().name())).append(" ");
			}
			if (_mDisplayLogLevel || _mDisplayLogTime || _mDisplayLogIndex) {
				sb.append("- ");
			}
			sb.append(value.getMessage().getFormattedMessage());
			label.setText(sb.toString());
			Icon icon = null;
			// Set the log color according to the loaded properties.
			org.apache.logging.log4j.Level l = value.getLevel();
			if (l.compareTo(Level.DEBUG) == 0) {
				label.setBackground(_mColorDebugBG.getAWTColor()); // using the shaddow variables directly to not always
																	// query the property
				label.setForeground(_mColorDebugFG.getAWTColor());
				icon = ICON_DEBUG;
			} else if (l.compareTo(Level.INFO) == 0) {
				label.setBackground(_mColorInfoBG.getAWTColor());
				label.setForeground(_mColorInfoFG.getAWTColor());
				icon = ICON_INFO;
			} else if (l.compareTo(Level.WARN) == 0) {
				label.setBackground(_mColorWarnBG.getAWTColor());
				label.setForeground(_mColorWarnFG.getAWTColor());
				icon =  ICON_WARNING;
			} else if (l.compareTo(Level.ERROR) == 0) {
				label.setBackground(_mColorErrorBG.getAWTColor());
				label.setForeground(_mColorErrorFG.getAWTColor());
				icon = ICON_ERROR;
			} else if (l.compareTo(Level.TRACE) == 0) {
				label.setBackground(_mColorTraceBG.getAWTColor());
				label.setForeground(_mColorTraceFG.getAWTColor());
				icon = ICON_TRACE;
			}
			if (_mDisplayLogIcon) {
				label.setIcon(icon);
			} else {
				label.setIcon(null);
			}
			return label;
		}
	}
	
	/**
	 * This is a custom selection model that does nothing.
	 */
	public class NoopSelectionModel implements ListSelectionModel {
		@Override
		public void setSelectionInterval(int index0, int index1) {
		}

		@Override
		public void addSelectionInterval(int index0, int index1) {
		}

		@Override
		public void removeSelectionInterval(int index0, int index1) {
		}

		@Override
		public int getMinSelectionIndex() {
			return 0;
		}

		@Override
		public int getMaxSelectionIndex() {
			return 0;
		}

		@Override
		public boolean isSelectedIndex(int index) {
			return false;
		}

		@Override
		public int getAnchorSelectionIndex() {
			return 0;
		}

		@Override
		public void setAnchorSelectionIndex(int index) {
		}

		@Override
		public int getLeadSelectionIndex() {
			return 0;
		}

		@Override
		public void setLeadSelectionIndex(int index) {
		}

		@Override
		public void clearSelection() {
		}

		@Override
		public boolean isSelectionEmpty() {
			return false;
		}

		@Override
		public void insertIndexInterval(int index, int length, boolean before) {
		}

		@Override
		public void removeIndexInterval(int index0, int index1) {
		}

		@Override
		public void setValueIsAdjusting(boolean valueIsAdjusting) {
		}

		@Override
		public boolean getValueIsAdjusting() {
			return false;
		}

		@Override
		public void setSelectionMode(int selectionMode) {
		}

		@Override
		public int getSelectionMode() {
			return 0;
		}

		@Override
		public void addListSelectionListener(ListSelectionListener x) {
		}

		@Override
		public void removeListSelectionListener(ListSelectionListener x) {
		}
	}

	/**
	 * Create the panel.
	 */
	public ConsolePanel() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		mList = new JList<>();
		mList.setVerifyInputWhenFocusTarget(false);
		mList.setRequestFocusEnabled(false);
		mList.setDoubleBuffered(true);
		mList.setModel(mListModel = new DefaultListModel<>());
		mList.setSelectionModel(new NoopSelectionModel()); // Use empty selection model as selection does not work anyways and it interferes with the UI update.
		scrollPane.setViewportView(mList);
		mList.setCellRenderer(new LogCellRenderer());

		// Load properties
		PropertyContext context = PropertyStore.getContext(PROPERTY_CONTEXT);
		mMaximumMessages = context.getProperty(PROP_MAXIMUM_MESSAGES, 1000);
		mMaximumMessages.addPropertyChangeListener(pcl -> _mMaximumMessages = mMaximumMessages.getValue());
		_mMaximumMessages = mMaximumMessages.getValue();

		mDisplayLogIndex = context.getProperty(PROP_DISPLAY_LOG_INDEX, true);
		mDisplayLogIndex.addPropertyChangeListener(pcl -> _mDisplayLogIndex = mDisplayLogIndex.getValue());
		_mDisplayLogIndex = mDisplayLogIndex.getValue();

		mDisplayLogTime = context.getProperty(PROP_DISPLAY_LOG_TIME, false);
		mDisplayLogTime.addPropertyChangeListener(pcl -> _mDisplayLogTime = mDisplayLogTime.getValue());
		_mDisplayLogTime = mDisplayLogTime.getValue();

		mDisplayLogLevel = context.getProperty(PROP_DISPLAY_LOG_LEVEL, false);
		mDisplayLogLevel.addPropertyChangeListener(pcl -> _mDisplayLogLevel = mDisplayLogLevel.getValue());
		_mDisplayLogLevel = mDisplayLogLevel.getValue();
		
		mDisplayLogIcon = context.getProperty(PROP_DISPLAY_LOG_ICON, false);
		mDisplayLogIcon.addPropertyChangeListener(pcl -> _mDisplayLogIcon = mDisplayLogIcon.getValue());
		_mDisplayLogIcon = mDisplayLogIcon.getValue();

		mColorDebugFG = context.getProperty(PROP_COLOR_DEBUG_FG, new ConfigColorImpl(java.awt.Color.LIGHT_GRAY));
		_mColorDebugFG = mColorDebugFG.getValue(); // initialize shadow variable
		mColorDebugFG.addPropertyChangeListener(pcl -> _mColorDebugFG = (ConfigColorImpl) pcl.getNewValue()); // update shadow variable on property change
		mColorDebugBG = context.getProperty(PROP_COLOR_DEBUG_BG, new ConfigColorImpl(java.awt.Color.DARK_GRAY));
		_mColorDebugBG = mColorDebugBG.getValue();
		mColorDebugBG.addPropertyChangeListener(pcl -> _mColorDebugBG = (ConfigColorImpl) pcl.getNewValue());

		mColorInfoFG = context.getProperty(PROP_COLOR_INFO_FG,
				new ConfigColorImpl(UIManager.getColor("List.foreground")));
		_mColorInfoFG = mColorInfoFG.getValue();
		mColorInfoFG.addPropertyChangeListener(pcl -> _mColorInfoFG = (ConfigColorImpl) pcl.getNewValue());
		mColorInfoBG = context.getProperty(PROP_COLOR_INFO_BG,
				new ConfigColorImpl(UIManager.getColor("List.background")));
		_mColorInfoBG = mColorInfoBG.getValue();
		mColorInfoBG.addPropertyChangeListener(pcl -> _mColorInfoBG = (ConfigColorImpl) pcl.getNewValue());

		mColorWarnFG = context.getProperty(PROP_COLOR_WARN_FG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorWarnFG = mColorWarnFG.getValue();
		mColorWarnFG.addPropertyChangeListener(pcl -> _mColorWarnFG = (ConfigColorImpl) pcl.getNewValue());
		mColorWarnBG = context.getProperty(PROP_COLOR_WARN_BG, new ConfigColorImpl(java.awt.Color.ORANGE));
		_mColorWarnBG = mColorWarnBG.getValue();
		mColorWarnBG.addPropertyChangeListener(pcl -> _mColorWarnBG = (ConfigColorImpl) pcl.getNewValue());

		mColorErrorFG = context.getProperty(PROP_COLOR_ERROR_FG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorErrorFG = mColorErrorFG.getValue();
		mColorErrorFG.addPropertyChangeListener(pcl -> _mColorErrorFG = (ConfigColorImpl) pcl.getNewValue());
		mColorErrorBG = context.getProperty(PROP_COLOR_ERROR_BG, new ConfigColorImpl(java.awt.Color.RED));
		_mColorErrorBG = mColorErrorBG.getValue();
		mColorErrorBG.addPropertyChangeListener(pcl -> _mColorErrorBG = (ConfigColorImpl) pcl.getNewValue());

		mColorTraceFG = context.getProperty(PROP_COLOR_TRACE_FG, new ConfigColorImpl(java.awt.Color.GRAY));
		_mColorTraceFG = mColorTraceFG.getValue();
		mColorTraceFG.addPropertyChangeListener(pcl -> _mColorTraceFG = (ConfigColorImpl) pcl.getNewValue());
		mColorTraceBG = context.getProperty(PROP_COLOR_TRACE_BG, new ConfigColorImpl(java.awt.Color.BLACK));
		_mColorTraceBG = mColorTraceBG.getValue();
		mColorTraceBG.addPropertyChangeListener(pcl -> _mColorTraceBG = (ConfigColorImpl) pcl.getNewValue());

		mTimer = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mQ.peek() != null) {
					int i = 0;
					while (mQ.peek() != null && i < CHUNK_SIZE) {
						mListModel.addElement(mQ.poll());
						if (_mMaximumMessages > 0 && mListModel.getSize() > _mMaximumMessages) {
							int j = 0;
							while (mListModel.size() > _mMaximumMessages && j < CHUNK_SIZE + 1) {
								mListModel.remove(0);
								j++;
							}
						}
						i++;
					}
					if (mTailing) {
						mList.ensureIndexIsVisible(mListModel.size() - 1);
					}
				} else {
					mTimer.setDelay(1000);
				}
			}});
		mTimer.setInitialDelay(1000);
		mTimer.start();
	}

	public void enableTailing(boolean b) {
		mTailing = b;
	}

	public boolean isTailingEnabled() {
		return mTailing;
	}

	public void clear() {
		if (mListModel != null)
			mListModel.clear();
	}

	/**
	 * Adds a logging event to the JList
	 * 
	 * @note this method has to be called from the UI Thread
	 * @param evt
	 */
	public void addMessage(LogEvent evt) {
		mTimer.setDelay(TIMER_DELAY);
		mQ.add(evt);
		
	}

	public DefaultListModel<LogEvent> getModel() {
		return mListModel;
	}

}
