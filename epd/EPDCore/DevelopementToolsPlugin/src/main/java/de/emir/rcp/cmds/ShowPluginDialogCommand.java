package de.emir.rcp.cmds;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import de.emir.rcp.UICorePlugin;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.product.AbstractUIProduct;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class ShowPluginDialogCommand extends AbstractCommand{


	public static final String ID = "cmd.ShowPluginDialogCmd";
	public static final String LABEL = "Configure Plugins";

	@Override
	public void execute() {

		Runtime rt = Runtime.getRuntime();
		try {
			String javaExecutablePath = ProcessHandle.current()
				    .info()
				    .command()
				    .orElseThrow();
			
			Path pluginFilePath = ResourceManager.get(getClass()).getHomePath().resolve("Product.xml");
			File pf = pluginFilePath.toFile();
			
//			File baseFile = ResourceManager.get(getClass()).getHomePath().resolve("PluginManager").toFile();
			File f = AbstractUIProduct.getPluginManagerJarFile(); //new File(baseFile.getAbsolutePath() + File.separator + "PluginManagerProduct-0.9.0-SNAPSHOT.jar");

			
			Process pr = rt.exec(javaExecutablePath + " -jar " + f.getAbsolutePath() + " -lock -edit " + pf.getAbsolutePath(), null, f.getParentFile());

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

		} catch (IOException e) {
			ULog.error(e);
		}
	}

}
