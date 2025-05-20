package de.emir.rcp.pluginmanager;


import de.emir.rcp.UICorePlugin;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.keybindings.ep.KeyBindingExtensionPoint;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.IMenuContribution;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.pluginmanager.cmds.AddDependencyCommand;
import de.emir.rcp.pluginmanager.cmds.AddPluginCommand;
import de.emir.rcp.pluginmanager.cmds.AddRepositoryCommand;
import de.emir.rcp.pluginmanager.cmds.ChangeProductDefinitionCommand;
import de.emir.rcp.pluginmanager.cmds.EditElementCommand;
import de.emir.rcp.pluginmanager.cmds.ExportProductCommand;
import de.emir.rcp.pluginmanager.cmds.RemoveElementCommand;
import de.emir.rcp.pluginmanager.cmds.ResolveDependencyInformationsCommand;
import de.emir.rcp.pluginmanager.cmds.SaveAndExitCommand;
import de.emir.rcp.pluginmanager.cmds.UpdateDependenciesCommand;
import de.emir.rcp.pluginmanager.doxygen.DoxygenExtensionPoint;
import de.emir.rcp.pluginmanager.doxygen.extensions.*;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.jobs.ExportProductJob;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.statusbar.PluginManagerStatusBarElement;
import de.emir.rcp.pluginmanager.views.PluginsView;
import de.emir.rcp.statusbar.ep.StatusBarExtensionPoint;
import de.emir.rcp.views.ep.IViewDescriptor;
import de.emir.rcp.views.ep.ViewExtensionPoint;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class PluginManagerPlugin extends AbstractUIPlugin {

	private static boolean HELP_PRINTED = false;

	private static final String BUILD_ONLINE = "ONLINE";
	private static final String BUILD_LEAN = "LEAN";
	private static final String BUILD_OFFLINE = "OFFLINE";

	private static Logger logger = LogManager.getLogger(PluginManagerPlugin.class);

	public static void main(String... args){

		logger.info("Start application");

        PlatformUtil.initBasicManagers();

		ExtensionPointManager.registerExtensionPoint(PMBasics.EXTENSION_POINT_DOXYGEN, new DoxygenExtensionPoint());
		registerDoxygenExtensions();

		Options options = getOptions();

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;

		try {
			logger.debug("Parsing args");
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
            logger.error(e.getMessage(), e);
            printHelp("h");
			return;
		}

		if (cmd.hasOption("h")){
			printHelp("h");
		}

		ProductFile productFile = null;

		if (cmd.hasOption("p")){
		    String product = cmd.getOptionValue("p");
		    File file = new File(product);
		    if (file.exists() == false){
		        logger.error("Product file does not exist!");
		        return;
            }else {
                try {
                    productFile = new ProductFile(file);
                } catch (SAXException | IOException | ParserConfigurationException e) {
                    logger.error(e.getMessage(), e);
                    return;
                }
            }
        }

		if (cmd.hasOption("n")){
		    productFile.setName(cmd.getOptionValue("n"));
        }

        ExportData exportData = new ExportData();

		if (cmd.hasOption("e")){
		    exportData.setEntryPointPath(cmd.getOptionValue("e"));
        }

        if (cmd.hasOption("bp")){
		    exportData.setEntryPointPomPath(cmd.getOptionValue("bp"));
        }

        if (cmd.hasOption("lf")){
		    //TODO set layout file path
//		    exportData.setLayoutFilePath("");
        }

        if (cmd.hasOption("pf")){
		    //TODO set layout file path
//		    exportData.setPropertiesFilePath("");
        }

        if (cmd.hasOption("z")){
			exportData.setCreateZip(true);
		}else {
			exportData.setCreateZip(false);
		}

        if (cmd.hasOption("af")){
		    exportData.setCopyAll(true);
        }else {
		    exportData.setCopyAll(false);
        }

        if (cmd.hasOption("ad")){
            exportData.setAddDocumentation(true);
        }else {
		    exportData.setAddDocumentation(false);
        }

        if (cmd.hasOption("b")){
		    String build = cmd.getOptionValue("b");
		    if (build == null){
		        logger.error("Build is not specified");
                return;
		    }


		    if (BUILD_ONLINE.trim().toLowerCase().equals(build.trim().toLowerCase())){
                boolean b = Boolean.parseBoolean(build);
                exportData.setOnlineRelease(b);
                exportData.setLeanRelease(false);
                exportData.setResolveLocally(false);
            }else if (BUILD_LEAN.trim().toLowerCase().equals(build.trim().toLowerCase())){
                boolean b = Boolean.parseBoolean(build);
                exportData.setLeanRelease(b);
                exportData.setResolveLocally(false);
                exportData.setOnlineRelease(false);
            }else if (BUILD_OFFLINE.trim().toLowerCase().equals(build.trim().toLowerCase())){
                boolean b = Boolean.parseBoolean(build);
                exportData.setResolveLocally(b);
                exportData.setLeanRelease(false);
                exportData.setOnlineRelease(false);
            }else {
		        logger.error("Build needs to be one of the following: "+BUILD_ONLINE+", "+BUILD_LEAN+", "+BUILD_OFFLINE);
            }
        }

        if (cmd.hasOption("d")){
		    String d = cmd.getOptionValue("d");
            exportData.setDoxygenPath(d);
        }

        if (cmd.hasOption("pl")){
		    String plantuml = cmd.getOptionValue("pl");
		    exportData.setPlantUMLPath(plantuml);
        }

        if (cmd.hasOption("o")){
		    String output = cmd.getOptionValue("o");
		    exportData.setOutputPath(output);
        }


        ExportProductJob job = new ExportProductJob(productFile, exportData);

        PlatformUtil.getJobManager().schedule(job, job1 -> System.exit(0));


	}

	@Override
	public void initializePlugin() {

		ServiceManager.register(new PmManager());

		ExtensionPointManager.registerExtensionPoint(PMBasics.EXTENSION_POINT_DOXYGEN, new DoxygenExtensionPoint());
	}

	@Override
	public void addExtensions() {

		ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);

		IViewDescriptor pView = viewEP.view(PMBasics.PLUGINS_VIEW_ID, PluginsView.class).label("Plugins")
				.icon("icons/emiricons/32/sbe_extensions.png");

		
		StatusBarExtensionPoint sbEP = ExtensionPointManager.getExtensionPoint(StatusBarExtensionPoint.class);
		sbEP.element(PluginManagerStatusBarElement.class);
		
		CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);

		ICommandDescriptor changeCmd = cmdEP.command(PMBasics.CHANGE_PRODUCT_DEF_CMD_ID, "Open Change Product Definition Dialog",
				new ChangeProductDefinitionCommand());

		ICommandDescriptor removeCMD = cmdEP.command(PMBasics.REMOVE_ELEMENT_CMD_ID, "Remove Selected Element(s)",
				new RemoveElementCommand());
		
		ICommandDescriptor addbuggedCMD = cmdEP.command(PMBasics.BUGGED_ADD_PLUGINS_CMD_ID, "Add Plugin(s)",
				new AddPluginCommand());
		
		ICommandDescriptor saveAndExitCMD = cmdEP.command(PMBasics.SAVE_AND_EXIT_CMD_ID, "Save & Exit Application",
				new SaveAndExitCommand());
		
		ICommandDescriptor editCMD = cmdEP.command(PMBasics.EDIT_CMD_ID, "Edit Selected Element",
				new EditElementCommand());
		
		ICommandDescriptor addRepoCMD = cmdEP.command(PMBasics.ADD_REPO_CMD_ID, "Add Repository",
				new AddRepositoryCommand());
		
		ICommandDescriptor addDependencyCMD = cmdEP.command(PMBasics.ADD_DEP_CMD_ID, "Add Dependency",
				new AddDependencyCommand());
		
		ICommandDescriptor gatherInfosCMD = cmdEP.command(PMBasics.RESOLVE_DEPENDENCIES_CMD_ID, "Update Dependencies",
				new ResolveDependencyInformationsCommand());
		
		ICommandDescriptor exportProductCMD = cmdEP.command(PMBasics.EXPORT_PRODUCT_CMD_ID, "Export Product",
				new ExportProductCommand());
		
		ICommandDescriptor updateVersionsCMD = cmdEP.command(PMBasics.UPDATE_VERSIONS_CMD_ID,
				"Update Dependency Versions", new UpdateDependenciesCommand());

		MenuExtensionPoint mEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
		
		IMenuContribution ptb = mEP.menuContribution(PMBasics.PLUGINS_TOOLBAR_ID);

		ptb.menuItem("addBugged", addbuggedCMD).icon("icons/emiricons/32/library_add.png", ResourceManager.get(UICorePlugin.class)).iconSize(32);
		ptb.menuItem("addDep", addDependencyCMD).icon("icons/emiricons/32/playlist_add.png").iconSize(32);
		ptb.menuItem("addRepo", addRepoCMD).icon("icons/emiricons/32/post_add.png").iconSize(32);
		ptb.separator("afterAddSep");
		ptb.menuItem("undo", Basic.CMD_UNDO).icon("icons/emiricons/32/undo.png", ResourceManager.get(UICorePlugin.class)).iconSize(32);
		ptb.menuItem("redo", Basic.CMD_REDO).icon("icons/emiricons/32/redo.png", ResourceManager.get(UICorePlugin.class)).iconSize(32);
		ptb.separator("afterRedoSep");
		
		
		ptb.menuItem("editSelection", editCMD).icon("icons/emiricons/32/edit.png").iconSize(32);
		ptb.menuItem("removePlugins", removeCMD).icon("icons/emiricons/32/delete.png").iconSize(32);
		ptb.menuItem("updateDependencies", updateVersionsCMD).icon("icons/emiricons/32/next_plan.png").iconSize(32);
		ptb.separator("afterRemoveSep");
		
		ptb.menuItem("gatherInfos", gatherInfosCMD).icon("icons/emiricons/32/cloud_sync.png").iconSize(32);
		
		IMenuContribution main = mEP.menuContribution(Basic.MENU_MAIN_MENU + ".file");
		main.menuItem("chooseProductFile", changeCmd).label("Open...").before("settings");
		main.menuItem("save", Basic.CMD_SAVE).label("Save").icon("icons/emiricons/32/sd_card.png", ResourceManager.get(UICorePlugin.class)).before("settings");
		main.separator("afterSaveSep").before("settings");

		IMenuContribution sbtb = mEP.menuContribution(PMBasics.STATUS_BAR_TOOLBAR_ID);
		sbtb.menuItem("saveProduct", saveAndExitCMD).icon("icons/emiricons/32/sd_card.png", ResourceManager.get(UICorePlugin.class)).label("Apply & Exit");
		
		IMenuContribution tbr = mEP.menuContribution(PMBasics.PLUGINS_TOOLBAR_RIGHT_ID);
		tbr.menuItem("exportMenu", exportProductCMD).icon("icons/emiricons/32/archive.png").label("Export...").iconSize(32);
		
		KeyBindingExtensionPoint kbEP = ExtensionPointManager.getExtensionPoint(KeyBindingExtensionPoint.class);
		
		kbEP.global("Ctrl+S", Basic.CMD_SAVE);
		kbEP.global("Ctrl+Z", Basic.CMD_UNDO);
		kbEP.global("Ctrl+Y", Basic.CMD_REDO);
		kbEP.inView(pView, "DELETE", PMBasics.REMOVE_ELEMENT_CMD_ID);
		kbEP.global("Ctrl+Shift+P", PMBasics.CHANGE_PRODUCT_DEF_CMD_ID);

		registerDoxygenExtensions();
	}

	@Override
	public void postWindowOpen() {
		ServiceManager.get(PmManager.class).loadProductFile();
	}

	private static void registerDoxygenExtensions(){
		DoxygenExtensionPoint doxygen = ExtensionPointManager.getExtensionPoint(DoxygenExtensionPoint.class);
		doxygen.registerDoxygenExtension(new ExcludeFoldersExtension());
		doxygen.registerDoxygenExtension(new ImagePathExtension());
		doxygen.registerDoxygenExtension(new InputFoldersExtension());
		doxygen.registerDoxygenExtension(new OutputDirectoryExtension());
		doxygen.registerDoxygenExtension(new PlantUMLDirExtension());
		doxygen.registerDoxygenExtension(new PlantUMLExtension());
		doxygen.registerDoxygenExtension(new ProjectDescriptionExtension());
		doxygen.registerDoxygenExtension(new ProjectLogoExtension());
		doxygen.registerDoxygenExtension(new ProjectNameExtension());
		doxygen.registerDoxygenExtension(new MainPageExtension());
	}

	private static void printHelp(String usage) {
		if (HELP_PRINTED == false) {
			if (usage == null) {
				usage = "utility-name";
			}
			HelpFormatter formatter = new HelpFormatter();
			Options options = getOptions();
			formatter.printHelp(usage, options);
			HELP_PRINTED = true;
		}
	}

	private static Options getOptions() {
		Options options = new Options();

		Option help = new Option("h", "help", false, "Help Command");
		help.setRequired(false);
		options.addOption(help);

		Option name = new Option("n", "name", true, "Required to create the output folder");
		name.setRequired(false);
		options.addOption(name);

		Option product = new Option("p", "product", true, "Product File to export");
		product.setRequired(true);
		options.addOption(product);

		Option entryPoint = new Option("e", "entrypoint", true, "The built JAR of the product. Must contain all direct dependencies");
		entryPoint.setRequired(true);
		options.addOption(entryPoint);

		Option pom = new Option("bp", "basepom", true, "The Maven POM according to which the product JAR was built");
		pom.setRequired(true);
		options.addOption(pom);

		Option layoutFile = new Option("lf", "layoutfile", true, "Settings files are created in the home directory of the application (usually next to the Product.xml). They contain the layout and properties of the application. By copying, the settings made during development can be included in a release.");
		layoutFile.setRequired(false);
		options.addOption(layoutFile);

		Option propertiesFile = new Option("pf", "propertiesfile", true, "Add Properties files.");
		propertiesFile.setRequired(false);
		options.addOption(propertiesFile);

		Option additionalFiles = new Option("af", "additionalfiles", false, "Copies all files and folders located next to the Product.xml. This allows plugin-specific files to be included in a release.");
		additionalFiles.setRequired(false);
		options.addOption(additionalFiles);

		Option addDocumentation = new Option("ad", "adddocumentation", false, "Documentation is created for each project specified in the Product.xml (Workspace-Directories)");
		addDocumentation.setRequired(false);
		options.addOption(addDocumentation);

		Option build = new Option("b", "build", true, "Choose Build config (ONLINE, LEAN, OFFLINE)");
		build.setRequired(true);
		options.addOption(build);

		Option doxygen = new Option("d", "doxygen", true, "Path to the Doxygen Executable");
		doxygen.setRequired(false);
		options.addOption(doxygen);

		Option plantuml = new Option("pl", "plantuml", true, "Path to the Plantuml Jar (without white spaces)");
		plantuml.setRequired(false);
		options.addOption(plantuml);

		Option zip = new Option("z", "zip", false, "Determines whether a zip file should be created or not");
		zip.setRequired(false);
		options.addOption(zip);

		Option output = new Option("o", "output", true, "The directory into which the application is deployed (without white spaces)");
		output.setRequired(true);
		options.addOption(output);


		return options;
	}
}
