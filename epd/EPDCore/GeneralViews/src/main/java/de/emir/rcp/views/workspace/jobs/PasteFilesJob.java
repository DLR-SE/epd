package de.emir.rcp.views.workspace.jobs;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.jobs.IJob;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class PasteFilesJob implements IJob {

	protected List<File> listOfFiles;
	private File targetFolder;
	private boolean overrideAll;
	protected boolean canceled;

	public PasteFilesJob(List<File> listOfFiles, File target) {
		this.listOfFiles = listOfFiles;
		this.targetFolder = target;
	}
	
	@Override
	public void run(IProgressMonitor monitor) {
		

		int steps = 0;
		
		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				Collection<File> list = FileUtils.listFilesAndDirs(file, TrueFileFilter.INSTANCE,
						TrueFileFilter.INSTANCE);
				steps += list.size();
				
			} else {
				steps++;
			}
		}
		
		float stepSize = 100f/(float)steps;
		float progress = 0;
		monitor.setProgress(progress);

		final Path target = targetFolder.toPath();

		for (File file : listOfFiles) {

			if (file.isDirectory()) {
				Path source = file.toPath();
				Path targetDir = target.resolve(file.getName());

				Collection<File> list = FileUtils.listFilesAndDirs(file, TrueFileFilter.INSTANCE,
						TrueFileFilter.INSTANCE);

				for (File file2 : list) {

					Path file2Target = targetDir.resolve(source.relativize(file2.toPath()));
					
					monitor.setMessage("Inserting '" + file2Target.getFileName() + "'");
					try {
						if (canceled == true) {
							return;
						}
						Files.copy(file2.toPath(), file2Target);
				
					} catch (FileAlreadyExistsException e) {

						// Alert that file already exists
						boolean override = checkFileHandling(file2Target);

						if (canceled == true) {
							return;
						}

						if (override == true) {
							try {

								// First clear directory
								if (file2.isDirectory() == true) {
									FileUtils.forceDelete(file2Target.toFile());
								}

								Files.copy(file2.toPath(), file2Target, StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e1) {
								ULog.error(e1);
							}
						}
						

					} catch (IOException e) {
						ULog.error(e);
					}
					progress = Math.min(progress + stepSize, 100);
					monitor.setProgress(progress);
				}

			} else {
				Path source = file.toPath();
				Path targetFile = target.resolve(file.getName());
				
				monitor.setMessage(targetFile.getFileName() + "");
				try {
					if (canceled == true) {
						return;
					}
					
					AbstractEditor editor = PlatformUtil.getEditorManager().getOpenEditor(file);
					
					if(editor != null) {
						
						boolean canContinue = editor.shutdown();
						
						if(canContinue == false) {
							return;
						}
						
						editor.setVisible(false);
						
					}
					
					Files.copy(source, targetFile);

				} catch (FileAlreadyExistsException e) {

					// Alert that file already exists
					boolean override = checkFileHandling(targetFile);

					if (canceled == true) {
						return;
					}

					if (override == true) {
						try {
							Files.copy(source, targetFile, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e1) {
							ULog.error(e1);
						}
					}

				} catch (IOException e) {
					ULog.error(e);
				}
				

				progress = Math.min(progress + stepSize, 100);
				monitor.setProgress(progress);
			}

		}
		
	}
	private boolean checkFileHandling(Path target) {

		if (overrideAll == true) {
			return true;
		}

		String[] buttons = { "Yes", "Yes to all", "No", "Cancel" };

		JFrame mw = PlatformUtil.getWindowManager().getActiveFrame();

		String type = target.toFile().isDirectory() ? "Directory" : "File";

		int rc = JOptionPane.showOptionDialog(mw,
				type + " '" + target.toFile().getName() + "' already exists. Override?", type + " exists...",
				JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
		
		if (rc == 1) {
			overrideAll = true;
			return true;
		}

		if (rc == 0) {
			return true;
		}

		if (rc == 3) {
			canceled = true;
		}

		return false;

	}

	@Override
	public boolean isBlocking() {
		return true;
	}

	@Override
	public boolean isCancelable() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Inserting Files...";
	}

	@Override
	public void cancel() {
		canceled = true;
	}

	@Override
	public boolean isBackground() {
		return false;
	}

}
