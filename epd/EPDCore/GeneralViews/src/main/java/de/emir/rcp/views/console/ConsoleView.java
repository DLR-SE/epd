package de.emir.rcp.views.console;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ConsoleView extends AbstractView {

	@Plugin(name="TextAreaAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
	public class TextAreaAppender extends AbstractAppender{
		protected TextAreaAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
			super(name, filter, layout, ignoreExceptions, properties);
			start();
		}

		@Override
		public void append(LogEvent event) {
//			Thread thread = Thread.ofVirtual().start(() -> {
			LogEvent logEvent = event.toImmutable();
			if (logEvent == null || logEvent.getLevel() == null) {
				return;
			}
			if (logEvent.getLevel().isInRange(Level.FATAL, Level.toLevel(mLogLevel.getValue()))) {
				SwingUtilities.invokeLater(() -> {
					if (mPanel != null) {
						mPanel.addMessage(logEvent);
					}

				});
			}
//			});
		}
	}

	private PublishSubject<LogEvent> publisher;

	public static final String UNIQUE_ID = "ConsoleView";

	public static final String PROPERTY_CONTEXT_ID = "ConsoleViewProperties";

	public static final String TAILING_PROPERTY = "Tailing";
	public static final String LOG_LEVEL_PROPERTY = "LogLevel";

	public static final String TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "consoleView";

	private ConsolePanel mPanel;

	private IProperty<String> mLogLevel = null;

	private PropertyContext pContext = PropertyStore.getContext(PROPERTY_CONTEXT_ID);

	public ConsoleView(String id) {
		super(id);

		mLogLevel = pContext.getProperty(LOG_LEVEL_PROPERTY, "INFO");
	}

	@Override
	public void onOpen() {
		publisher = PublishSubject.create();
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		TextAreaAppender appender = new TextAreaAppender("ConsoleViewAppender", null,
				config.getAppenders().values().stream().findFirst().get().getLayout(), false, null);
		config.addAppender(appender);
		ctx.getRootLogger().addAppender(appender);
		ctx.updateLoggers();
		
		mPanel.enableTailing(pContext.getProperty(TAILING_PROPERTY, true).getValue());
		pContext.getProperty(TAILING_PROPERTY).addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				mPanel.enableTailing((boolean) evt.getNewValue());
			}
		});

	}
	
	public void clearConsole() {
		
		if(mPanel != null) {
			mPanel.clear();
		}
		
	}

	@Override
	public void onClose() {

	}
	
	public String getLog() {
		if (mPanel == null) return null;
		StringBuilder sb = new StringBuilder();
		Iterator<LogEvent> it = mPanel.getModel().elements().asIterator(); 
		while (it.hasNext()) {
			sb.append(it.next().getMessage().getFormattedMessage()).append(System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createContent() {

		JPanel parent = new JPanel();
		
		BorderLayout layout = new BorderLayout();
		
		parent.setLayout(layout);
		
		JToolBar mToolBar = new JToolBar();
		PlatformUtil.getMenuManager().fillToolbar(mToolBar, TOOLBAR_ID);

		parent.add(mToolBar, BorderLayout.NORTH);
		mToolBar.setMargin(new Insets(0, 0, 0, 0));
		
		mToolBar.setFloatable(false);
		
		parent.add(mPanel = new ConsolePanel(), BorderLayout.CENTER);
		
		return parent;
		
	}

}
