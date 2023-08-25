package de.emir.rcp.manager.util;

import de.emir.rcp.manager.ArgumentsManager;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.EditorManager;
import de.emir.rcp.manager.JobManager;
import de.emir.rcp.manager.KeyBindingManager;
import de.emir.rcp.manager.MenuManager;
import de.emir.rcp.manager.ModelManager;
import de.emir.rcp.manager.ProductInfoManager;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.SettingsPageManager;
import de.emir.rcp.manager.ViewManager;
import de.emir.rcp.manager.WindowManager;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.statusbar.StatusBarManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Grants access to the basic managers of the uicore plugin.
 * 
 * @author Florian
 *
 */
public class PlatformUtil {

    private static boolean initialized = false;

    public static void initBasicManagers() {

        if (initialized == true) {
            throw new UnsupportedOperationException("Basic managers have already been initialized");
        }
        initialized = true;

        ServiceManager.register(new CommandManager());
        ServiceManager.register(new MenuManager());
        ServiceManager.register(new ViewManager());
        ServiceManager.register(new EditorManager());
        ServiceManager.register(new JobManager());
        ServiceManager.register(new KeyBindingManager());
        ServiceManager.register(new ModelManager());
        ServiceManager.register(new SelectionManager());
        ServiceManager.register(new SettingsPageManager());
        ServiceManager.register(new WindowManager());
        ServiceManager.register(new StatusBarManager());
        ServiceManager.register(new ProductInfoManager());
    }

    public static EditorManager getEditorManager() {
        return ServiceManager.get(EditorManager.class);
    }

    public static ViewManager getViewManager() {
        return ServiceManager.get(ViewManager.class);
    }

    public static CommandManager getCommandManager() {
        return ServiceManager.get(CommandManager.class);
    }

    public static JobManager getJobManager() {
        return ServiceManager.get(JobManager.class);
    }

    public static KeyBindingManager getKeyBindingManager() {
        return ServiceManager.get(KeyBindingManager.class);
    }

    public static MenuManager getMenuManager() {
        return ServiceManager.get(MenuManager.class);
    }

    public static ModelManager getModelManager() {
        return ServiceManager.get(ModelManager.class);
    }

    public static SelectionManager getSelectionManager() {
        return ServiceManager.get(SelectionManager.class);
    }

    public static SettingsPageManager getSettingsPageManager() {
        return ServiceManager.get(SettingsPageManager.class);
    }

    public static WindowManager getWindowManager() {
        return ServiceManager.get(WindowManager.class);
    }

    public static StatusBarManager getStatusBarManager() {
        return ServiceManager.get(StatusBarManager.class);
    }
    
    public static ProductInfoManager getProductInfoManager() {
        return ServiceManager.get(ProductInfoManager.class);
    }

    /**
     * 
     * @return The arguments manager which stores command line arguments this application has been started with
     */
    public static ArgumentsManager getArgumentsManager() {
        return ServiceManager.get(ArgumentsManager.class);
    }
    
    /**
     * Utility method to run a model transaction. 
     * This method is equivalent to PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(modelTransaction) 
     * but checks if every manager, provider and stack is available or not
     * @param modelTransaction transaction to be executed
     * @return true, if the transaction has been forwarded to the model transaction stack, false otherwise
     */
    public static boolean runModelTransaction(AbstractModelTransaction modelTransaction) {
    	ModelManager mm = getModelManager();
    	if (mm != null) {
    		AbstractModelProvider mp = mm.getModelProvider();
    		if (mp != null) {
    			ModelTransactionStack ts = mp.getTransactionStack();
    			if (ts != null) {
    				ts.run(modelTransaction);
    				return true;
    			}else {
    				ULog.debug("Failed to run ModelTransaction - missing TransactionStack");
    			}
    		}else {
    			ULog.debug("Failed to run ModelTransaction - missing ModelProvider");
    		}
    	}
    	ULog.debug("Failed to run ModelTransaction - missing ModelManager");
    	return false;
    }
}
