package de.emir.rcp.views.workspace.jobs;

import java.io.File;
import java.util.List;

import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class MoveFilesJob extends PasteFilesJob {

	private DeleteFilesJob deleteJob;

	public MoveFilesJob(List<File> listOfFiles, File target) {
		super(listOfFiles, target);
	}
	
	@Override
	public void run(IProgressMonitor monitor) {
		
		super.run(monitor);
		
		if(canceled == true) {
			return;
		}
		
		deleteJob = new DeleteFilesJob(listOfFiles);
		
		deleteJob.run(monitor);

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
	public void cancel() {
		if(deleteJob != null) {
			deleteJob.cancel();
		} else {
			super.cancel();
		}

	}

	@Override
	public String getTitle() {
		return "Moving Files";
	}

}
