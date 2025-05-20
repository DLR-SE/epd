package de.emir.rcp.manager;

import java.awt.Window;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.apache.logging.log4j.Logger;

import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class WindowManager implements IService {

    private static Logger LOG = ULog.getLogger(WindowManager.class);

    private MainWindow mainWindow;

    private JFrame activeFrame;

    private PublishSubject<Optional<JFrame>> activeFrameSubject = PublishSubject.create();

    static {
    	initializeUIManager();
    }
    
    
    
    /**
     * Adjust global ui settings
     */
    private static void initializeUIManager() {
    	UIDefaults uiDefs = UIManager.getDefaults();
	}
    
    
    
    
    /**
     * Returns the application main window
     * 
     * @return
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }



	/**
     * Sets the application main window. For interal use only
     * 
     * @param mw
     */
    public void setMainWindow(JFrame mw) {

        if (mw instanceof MainWindow == false) {
            LOG.error("Main Window has to be of type [" + MainWindow.class + "]");
        }
        
        mainWindow = (MainWindow) mw;
        setActiveFrame(mainWindow);
    }

    public JFrame getActiveFrame() {
    	
    	Window[] windows = Window.getWindows();
 
    	for (Window window : windows) {
			if(window instanceof JFrame && window.isActive() == true) {
				return (JFrame) window;
			}
		}

        return activeFrame;
    }

    public void setActiveFrame(JFrame activeFrame) {
        this.activeFrame = activeFrame;
        activeFrameSubject.onNext(Optional.ofNullable(activeFrame));
    }

    public Disposable subscribeActiveFrame(Consumer<Optional<JFrame>> c) {
        return activeFrameSubject.subscribe(c);
    }

}
