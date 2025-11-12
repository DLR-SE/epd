package de.emir.rcp.views.workspace.cmd;

import java.io.File;
import java.io.IOException;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public class ShowInSystemExplorerCommand extends AbstractCommand {
	private boolean isWindows = false;
	
	public ShowInSystemExplorerCommand() {
		String os = System.getProperty("os.name");
		isWindows = os.toLowerCase().contains("win");
		setCanExecute(findSystemExplorer() != null);
	}
	
	@Override
	public void execute() {		
		Object selection = PlatformUtil.getSelectionManager().getFirstSelectedObject();

		if (selection instanceof File f) {
			if (isWindows) {
				try {
					ProcessBuilder pb = new ProcessBuilder("explorer.exe","/select,"+ ((File) selection).getAbsolutePath());
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (!f.isDirectory()) {
						f = f.getParentFile();
					}
					ProcessBuilder pb = new ProcessBuilder("xdg-open", f.getAbsolutePath());
					pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * Go through all directories in the systems path variable and find either explorer.exe or xdg-open, depending on 
	 * the os.
	 * 
	 * @return the executable file for the system explorer, can be explorer.exe or xdg-open
	 */
	private File findSystemExplorer()
    {
        String path = System.getenv("PATH");
        String[] pathDirs = path.split(File.pathSeparator);
  
        for (String dir : pathDirs)
        {
            File file = new File(dir, isWindows ? "explorer.exe" : "xdg-open");
            if (file.isFile())
            {
                return file;
            }
        }
        return null;
    }
}
