package de.emir.rcp.views.console;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggingEvent;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ConsoleView extends AbstractView {
	
	
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
		final PublishSubject<LoggingEvent> publisher = PublishSubject.create(); // used
																				// to
		LogManager.getRootLogger().addAppender(new ConsoleAppender() {
			@Override
			public synchronized void doAppend(LoggingEvent event) {
				if (event == null || event.getLevel() == null)
					return;
				if (event.getLevel().isGreaterOrEqual(Level.toLevel(mLogLevel.getValue())))
					publisher.onNext(event);
			}
		});

		publisher.buffer(100, TimeUnit.MILLISECONDS).subscribe(e -> {
			if (e.isEmpty() == false) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						for (LoggingEvent evt : e)
							mPanel.addMessage(evt);
					}
				});
			}
		});

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
