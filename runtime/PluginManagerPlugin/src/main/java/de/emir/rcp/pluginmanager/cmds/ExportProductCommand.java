package de.emir.rcp.pluginmanager.cmds;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.pluginmanager.jobs.ExportProductJob;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.PmModelProvider;
import de.emir.rcp.pluginmanager.views.dialogs.ExportProductDialog;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class ExportProductCommand extends AbstractCommand {

    public ExportProductCommand() {
        PmManager pm = ServiceManager.get(PmManager.class);
        if (pm == null || pm.getModelProvider() == null){
        	setCanExecute(false);
        }else {
	        pm.getModelProvider().subscribeProductFile(opt -> setCanExecute(opt.isPresent()));
	
	        setCanExecute(pm.getModelProvider().getProductFile() != null);
        }
    }

    @Override
    public void execute() {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        PmManager pm = ServiceManager.get(PmManager.class);

        PmModelProvider mp = pm.getModelProvider();

        ProductFile pf = mp.getProductFile();

        ModelTransactionStack ts = mp.getTransactionStack();

        if (ts.isDirty() == true) {

            String modelName = mp.getModelIdentifier();
            int rc = JOptionPane.showConfirmDialog(mw, "'" + modelName + "' has been modified. Save changes?", "Save",
                    JOptionPane.YES_NO_OPTION);

            if (rc == JOptionPane.YES_OPTION) {
                ts.save();
            } else {
                return;
            }

        }

        ExportProductDialog dialog = new ExportProductDialog(mw, pf);

        dialog.setVisible(true);

        if (dialog.isOk() == false) {
            return;
        }

        ExportData data = dialog.getExportData();

        ExportProductJob job = new ExportProductJob(pf, data);

        PlatformUtil.getJobManager().schedule(job, e -> handleJobFinished(job, data, pf));

    }

    private void handleJobFinished(ExportProductJob job, ExportData data, ProductFile pf) {

        String errorMsg = job.getExportError();
        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        if (errorMsg == null) {

            String outputPath = data.getOutputPath() + File.separator + pf.getName();

            JOptionPane.showMessageDialog(mw, "<html>Application has been deployed to<br><font  color=\"#99b4d1\">"
                    + outputPath + "</font></html>", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(mw, errorMsg, "Error exporting product...", JOptionPane.ERROR_MESSAGE);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                execute();

            }
        });
    }

}
