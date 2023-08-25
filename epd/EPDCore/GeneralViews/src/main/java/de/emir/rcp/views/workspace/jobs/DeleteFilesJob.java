package de.emir.rcp.views.workspace.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import de.emir.rcp.jobs.IJob;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class DeleteFilesJob implements IJob {

	private List<File> listOfFiles;
	private boolean canceled;

	public DeleteFilesJob(List<File> listOfFiles) {
		this.listOfFiles = listOfFiles;
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

		float stepSize = 100f / (float) steps;
		float progress = 0;
		monitor.setProgress(progress);

		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				Collection<File> list = FileUtils.listFilesAndDirs(file, TrueFileFilter.INSTANCE,
						TrueFileFilter.INSTANCE);

				File[] entries = list.toArray(new File[0]);

				for (int i = entries.length - 1; i >= 0; i--) {

					monitor.setMessage("Deleting '" + entries[i].getName() + "'");

					try {
						if (canceled == true) {
							return;
						}
						FileUtils.forceDelete(entries[i]);
					} catch (FileNotFoundException e) {

					} catch (IOException e) {
						e.printStackTrace();
					}
					progress = Math.min(progress + stepSize, 100);
					monitor.setProgress(progress);

				}

			} else {

				monitor.setMessage("Deleting '" + file.getName() + "'");

				try {
					if (canceled == true) {
						return;
					}
					FileUtils.forceDelete(file);
				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					e.printStackTrace();
				}
				progress = Math.min(progress + stepSize, 100);
				monitor.setProgress(progress);

			}
		}

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
		return "Delete Files...";
	}

	@Override
	public void cancel() {
		this.canceled = true;
		
	}

	@Override
	public boolean isBackground() {
		return false;
	}

}
