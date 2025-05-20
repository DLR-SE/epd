package de.emir.rcp.commands.basics;

import java.io.IOException;
import java.net.URI;

import de.emir.rcp.commands.AbstractCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExternalBrowserCommand extends AbstractCommand {
	String target;
	String browserExecutable;
	static final Logger LOG = LogManager.getLogger(ExternalBrowserCommand.class);
	
	/**
	 * Create a command that can open the given URL in the system default browser.
	 *  
	 * @param target This is the URL to open. Please be aware of the security implications when using this.
	 */
	public ExternalBrowserCommand(URI target) {
		this.target = target.toString();
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			browserExecutable = "rundll32 url.dll,FileProtocolHandler";
		} else if (os.indexOf("mac") >= 0) {
			browserExecutable = "open";
		} else if (os.indexOf("nix") >=0 || os.indexOf("nux") >=0) {
			browserExecutable = "xdg-open";
		} 
	}
	
	/**
	 * This opens a predefined URL in the system default browser window.
	 */
	@Override
	public void execute() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(browserExecutable + " " + target);
		} catch (IOException e) {
			LOG.error("Could not open external browser.", e);
		}
	}

}
