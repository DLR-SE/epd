package de.emir.rcp.product;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.common.base.Function;

import de.emir.rcp.UICorePlugin;
import de.emir.rcp.manager.ArgumentsManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.BasicEditorModelProvider;
import de.emir.rcp.product.ui.LookAndFeelManager;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.runtime.plugin.manager.PluginRuntimeManager;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.runtime.plugin.windows.MainWindow.ICloseListener;
import de.emir.tuml.ucore.runtime.UCorePlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.utils.FileOperations;
import de.emir.tuml.ucore.runtime.utils.Zip;
import de.emir.tuml.ucore.runtime.utils.ZipFileException;

public abstract class AbstractUIProduct extends AbstractProduct implements ICloseListener {

	public AbstractUIProduct(String[] args) {
		super(args);
	}

	@Override
	public void init() {
		// parse the arguments to extract the applications name and initialize the ResourceManager. This
		// needs to be done before we call the init method with the progress monitor, for the case that
		// the progress monitor (for example a splash screen) needs access to resources
		String appName = getApplicationName();

		// Disabling garbage free logging configuration. This creates warnings by log4j because each logger is then
		// initialized with the ReusableMessageFactory and requested again with the ParameterizedMessageFactory.
		Properties props = System.getProperties();
		props.setProperty("log4j2.enableThreadlocals", "false");

		// set only if not null, otherwise we may get some strange errors with
		// the ResourceManager
		if (appName != null && appName.isEmpty() == false)
			ResourceManager.applicationName = appName;

		//Registers an arguments manager providing access to the command line arguments
		//of this application during runtime
		ServiceManager.register(new ArgumentsManager(getArgs()));
		
		// initialize the logging. this can be done as soon as the resource manager has
		// been initialized, to get the
		// log_configuration file from the home directory
		// if there is no log file in the home directory, we create one first and later
		// load it. the following method only creates a file if it is not already there
		ResourceManager rmgr = ResourceManager.get(AbstractUIPlugin.class);
		
		File productFile = new File("Product.xml");
		if(productFile.exists() == true) {
			rmgr.setHomePath(new File(""));
			ULog.debug("Home Path set to " + rmgr.getHomePath().toFile().getAbsolutePath());
		}
		
		try {
			rmgr.unpackFileToHome("log_config/log4j.xml");
			rmgr.unpackFileToHome("layout.xml");
			rmgr.unpackFileToHome("properties.data");
			rmgr.unpackFileToHome("UserConfiguration.xml");
			rmgr.unpackFileToHome("Splash.png");
			rmgr.unpackFileToHome("logo.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// the default log4j.xml contains an environment variable
		// "${RCPProductWithUI.home}" which should point to our current "home" dir, thus
		// we set it
		System.setProperty("RCPProductWithUI.home", rmgr.getHomePath().toFile().getAbsolutePath());

		// now we can load the file
		File logFile = rmgr.resolveFile("log_config/log4j.xml");
		if (logFile != null && logFile.exists())
			ULog.getInstance().initialize(logFile);
		else { // we have no log, so use the console first
			ULog.error("Could not find a valid log configuration (log_config/log4j.xml");
		}

		LookAndFeelManager.init(getLAF());

		Splash splash = new Splash(getSplashImagePath());

		PlatformUtil.initBasicManagers();

		final MainWindow mainFrame = new MainWindow(getTitle(), hasEditorArea(), hasStatusBar());
		mainFrame.setCloseListener(this);

		// perform first start initialization if necessary
		try {
			// can do this earliest, when having a Frame to show modal dialogs
			checkFirstStart(ResourceManager.get(getClass()), mainFrame, splash);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}

		splash.setProgress(0);
		splash.setMessage("Initializing...");

		try {
			// Init plugins with the Splashscreen set as a ProgressMonitor
			super.init(splash);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}

		splash.setProgress(2);
		splash.setMessage("Creating UI...");

		Map<String, UCorePlugin> plugins = mPluginManager.getPlugins();

		splash.setMessage("Initialize Plugins...");
		for (Entry<String, UCorePlugin> entry : plugins.entrySet()) {
			UCorePlugin plugin = entry.getValue();
			if (plugin instanceof AbstractUIPlugin) {
				splash.setMessage("... initialize UI Plugin: " + entry.getKey());
				((AbstractUIPlugin) plugin).onWindowCreate(mainFrame);
			}
		}

		PlatformUtil.getModelManager().setModelProvider(getModelProvider());
		initProduct();

		PluginRuntimeManager prm = new PluginRuntimeManager();
		ServiceManager.register(prm);

		for (Entry<String, UCorePlugin> entry : plugins.entrySet()) {
			UCorePlugin plugin = entry.getValue();
			if (plugin instanceof AbstractUIPlugin) {
				try {
					prm.setCurrentlyLoadingPlugin((AbstractUIPlugin) plugin);
					splash.setMessage("... register extension points of: " + entry.getKey());
					((AbstractUIPlugin) plugin).registerExtensionPoints();
					prm.setCurrentlyLoadingPlugin(null);
				} catch (Exception | Error e) {
					// application should not break appart if one plugin has an exception
					ULog.error("Failed to register ExtensionPoints of: " + entry.getKey() + " with error: "
							+ e.getMessage());
					e.printStackTrace();
				}
			}
		}

		splash.setProgress(10);
		splash.setMessage("Loading Extensions...");

		int pluginCount = plugins.values().size();
		
		double increment = 80 / (pluginCount != 0 ? pluginCount : 1);

		int i = 0;

		for (Entry<String, UCorePlugin> entry : plugins.entrySet()) {

			UCorePlugin plugin = entry.getValue();
			String coord = entry.getKey();
			if (plugin instanceof AbstractUIPlugin) {
				try {
					prm.setCurrentlyLoadingPlugin((AbstractUIPlugin) plugin);
					((AbstractUIPlugin) plugin).addExtensions();
					prm.setCurrentlyLoadingPlugin(null);
				} catch (Exception | Error e) {
					// don't break if one plugin could not be initialized
					ULog.error("Failed to add Extensions of: " + entry.getKey() + " with error: " + e.getMessage());
					e.printStackTrace();
				}
			}
			i++;
			splash.setProgress((int) (10 + i * increment));
			splash.setMessage("Loading Extensions: " + coord);
		}
		
		for (UCorePlugin plugin : plugins.values()) {
		
			if (plugin instanceof AbstractUIPlugin) {
				try {
					prm.setCurrentlyLoadingPlugin((AbstractUIPlugin) plugin);
					((AbstractUIPlugin) plugin).postAddExtensions();
					prm.setCurrentlyLoadingPlugin(null);
				} catch (Exception | Error e) {
					ULog.error("Failed to postAddExtensions of: " + plugin + " with error: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		splash.setProgress(100);
		splash.setMessage("Done...");

		for (UCorePlugin plugin : plugins.values()) {

			if (plugin instanceof AbstractUIPlugin) {

				prm.setCurrentlyLoadingPlugin((AbstractUIPlugin) plugin);
				((AbstractUIPlugin) plugin).preWindowOpen();
				prm.setCurrentlyLoadingPlugin(null);
			}

		}

		// Hide splash screen, load main frame
		splash.dispose();

		// Open the main window
		mainFrame.openMainFrame();

		mainFrame.setIconImage(getApplicationImage());
		for (UCorePlugin plugin : plugins.values()) {

			if (plugin instanceof AbstractUIPlugin) {

				prm.setCurrentlyLoadingPlugin((AbstractUIPlugin) plugin);
				((AbstractUIPlugin) plugin).postWindowOpen();
				prm.setCurrentlyLoadingPlugin(null);
				prm.addLoadedPlugin((AbstractUIPlugin) plugin);
			}

		}

	}

	@Override
	public void aboutToClose() {
		Map<String, UCorePlugin> plugins = mPluginManager.getPlugins();
		for (Entry<String, UCorePlugin> e : plugins.entrySet()) {
			UCorePlugin plugin = e.getValue();
			if (plugin instanceof AbstractUIPlugin) {
				try {
					((AbstractUIPlugin) plugin).preApplicationClose();
				} catch (Exception | Error eexp) {
					eexp.printStackTrace();
					ULog.error("Failed to notify plugin: " + e.getKey() + " to be closed soon: " + eexp.getMessage());
				}
			}
		}
	}

	@Override
	public void closed() {
		Map<String, UCorePlugin> plugins = mPluginManager.getPlugins();
		for (Entry<String, UCorePlugin> e : plugins.entrySet()) {
			UCorePlugin plugin = e.getValue();
			if (plugin instanceof AbstractUIPlugin) {
				try {
					((AbstractUIPlugin) plugin).postApplicationClose();
				} catch (Exception eexp) {
					eexp.printStackTrace();
					ULog.error("Failed to notify plugin: " + e.getKey() + " to be closed: " + eexp.getMessage());
				}
			}
		}
	}

	private void checkFirstStart(ResourceManager rmgr, MainWindow mainFrame, Splash splash)
			throws IOException, SAXException, ParserConfigurationException {
		splash.setProgress(0);
		splash.setMessage("Checking first Startup");

		// if this app is started the first time, we show the Plugin dialog and
		// update / download the available plugins
		// we can detect if it is the first start, by looking for the
		// "Product.xml" file in our home directory.
		Path productFilePath = rmgr.getHomePath().resolve("Product.xml");
		if (productFilePath != null && productFilePath.toFile().exists()) {
			// has already been started, thus nothing to do here anymore
			return;
		}
		ULog.info("Preparing for first start");
		JOptionPane.showMessageDialog(mainFrame,
				"Application need to be prepared for first start, this may take some while after initialisation, depending on your network connection \n Please configure your required plugins in the next panel and press apply to continue",
				"First Initialisation", JOptionPane.INFORMATION_MESSAGE, null);
		// use preconfigured plugin file but give the user the chance to
		// manipulate it
		// we already unpack them here, to give the product developer the option to
		// place new files with release specific context into
		// its own applications resources
		rmgr.unpackFileToHome("Product.xml"); // describes the plugins to be loaded
		
		showPluginManager();
	}
	
	
	/**
	 * Search for a jar file in the directories home path (e.g. ResourceManager.getHomePath) that starts with "PluginManager"
	 * if the file does not exist, the method search for a zip resource with the same name and extract the found resource to the home folder
	 * @return
	 */
	public static File getPluginManagerJarFile() {
		ResourceManager rmgr = ResourceManager.get(AbstractUIProduct.class);
		File baseFile = rmgr.getHomePath().resolve("PluginManager").toFile();
		if (baseFile == null || baseFile.exists() == false) { //we need to extract the contained resource
			try {
				File zipPath = rmgr.unpackFileToHome("PluginManager/PluginManagerProduct.zip").toFile();
				Zip.decompress(zipPath, rmgr.getHomePath().toString(), true);
				zipPath.delete(); //no longer needed
			} catch (IOException | ZipFileException e) {
				ULog.error(e);
			}
		}
		return new File(baseFile.getAbsolutePath() + File.separator + "PluginManagerProduct.jar");
	}
	
	
	/**
	 * The method expects a resource named "PluginManager/PluginManagerProduct.zip" in the uiplugincore resource
	 * directory.
	 * In this Zip file an offline release of the PluginManagerProduct is expected, which in turn is stored in the
	 * "PluginManager" directory in the Zip file.
	 * 
	 *  The PluginManagerProduct.zip file is extracted to the home directory of the application. 
	 *  Then the PluginManagerProduct is started with the Product.xml file for the current project as input parameter
	 *  
	 *  The method awaits the started PluginManagerProduct to return
	 */
	private void showPluginManager() {
		
		Runtime rt = Runtime.getRuntime();
		try {
			String javaExecutablePath = ProcessHandle.current()
				    .info()
				    .command()
				    .orElseThrow();
			
			Path pluginFilePath = ResourceManager.get(getClass()).getHomePath().resolve("Product.xml");
			File pf = pluginFilePath.toFile();
			
			File pmpJarFile = getPluginManagerJarFile();

			Process pr = rt.exec( 
					javaExecutablePath + " -jar " + pmpJarFile.getAbsolutePath() + " -lock -edit " + pf.getAbsolutePath(),
					null,
					pmpJarFile.getParentFile()
			);

			final Thread ioThread = new Thread() {
				@Override
				public void run() {
					try {
						final BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
						String line = null;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
						}
						reader.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
			};
			ioThread.start();

			final Thread ioThread2 = new Thread() {
				@Override
				public void run() {
					try {
						final BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
						String line = null;
						while ((line = reader.readLine()) != null) {
							System.err.println(line);
						}
						reader.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
			};
			ioThread2.start();
			
			// Wait for PluginManager to be closed
			while (pr.isAlive()) {

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
		
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method is called right before the main window is opened. Implement logic
	 * that is product-specific in this method.
	 */
	public abstract void initProduct();

	/**
	 * Returns the model provider of this product. Override this method to define a
	 * custom behavior. The default behavior defined by the
	 * {@link BasicEditorModelProvider} always returns the model of the currently
	 * active editor (or null)
	 * 
	 * @return
	 */
	public AbstractModelProvider getModelProvider() {
		return new BasicEditorModelProvider();
	}

	/**
	 * Returns the default look and feel. Can be overridden to use another
	 * look and feel.
	 * 
	 * @return
	 */
	public LookAndFeel getLAF() {
		return LookAndFeelManager.getDefault();
	}
	
	/**
	 * Returns the path to the splash image file. Can be overridden to use another
	 * image.
	 * 
	 * @return
	 */
	public String getSplashImagePath() {
		return "Splash.png";
	}

	/**
	 * Returns the applications title. Can be overridden to set a custom title.
	 * 
	 * @return
	 */
	public String getTitle() {
		return "eMIR Product";
	}

	/**
	 * Returns the applications image. Can be overridden to set a custom image.
	 * 
	 * @return
	 */
	public Image getApplicationImage() {
		return IconManager.getIcon(this, "logo.png", IconManager.preferedSmallIconSize()).getImage();
	}

	/**
	 * Override this method to define if the product has an editor area. This area
	 * is necessary in order to open editors, but it is always present. To see
	 * changes you probably have to delete the layout.xml within your workspace
	 * folder
	 * 
	 * @return
	 */
	public boolean hasEditorArea() {
		return false;
	}

	/**
	 * Override this method to define if the product has a status bar area. This
	 * area is necessary in order to show status bar entries defined via the status
	 * bar extension point.
	 * 
	 * @return
	 */
	public boolean hasStatusBar() {
		return false;
	}

}
