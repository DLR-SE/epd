package de.emir.rcp.cmds;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.product.AbstractUIProduct;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * This Command allows the eMIR Product to launch a PluginManager and load the Product.xml in order to change the used
 * plugins or to export the product as package.
 */
public class ShowPluginDialogCommand extends AbstractCommand{
	public static final String ID = "cmd.ShowPluginDialogCmd";
	public static final String LABEL = "Configure Plugins";

	@Override
	public void execute() {
		try {
			// Get the Java executable.
			String javaExecutablePath = ProcessHandle.current().info().command().orElseThrow();
			
			// Find the Product.xml.
			Path productFilePath = ResourceManager.get(getClass()).getHomePath().resolve("Product.xml");
			File pf = productFilePath.toFile();
			
			// Get the PluginManager.
			File f = AbstractUIProduct.getPluginManagerJarFile();
			
			// Create and start the process with parameters.
			ProcessBuilder pb = new ProcessBuilder(javaExecutablePath, "-jar", f.getAbsolutePath(), "-lock", "-edit",
					pf.getAbsolutePath());
			pb.directory(f.getParentFile());
			Process pr = pb.start();	

			// Forwards the PluginManagers stdout to the log.
			final Thread ioThread = new Thread() {
				@Override
				public void run() {
					try {
						final BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
						String line = null;
						while ((line = reader.readLine()) != null) {
							ULog.info(line);
						}
						reader.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
			};
			ioThread.start();

			// Forwards the PluginManagers stderr to the log as error.
			final Thread ioThread2 = new Thread() {
				@Override
				public void run() {
					try {
						final BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
						String line = null;
						while ((line = reader.readLine()) != null) {
							ULog.error(line);
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
