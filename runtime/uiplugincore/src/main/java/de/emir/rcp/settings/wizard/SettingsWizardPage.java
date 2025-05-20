package de.emir.rcp.settings.wizard;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import de.emir.ui.utils.TreeUtils;
import org.apache.logging.log4j.Logger;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.settings.ep.SettingsPageData;
import de.emir.rcp.ui.widgets.AbstractDetailsContentPanel;
import de.emir.rcp.ui.widgets.IDetailsAreaProvider;
import de.emir.rcp.ui.widgets.IFilterMatcher;
import de.emir.rcp.ui.widgets.JTreeWithFilterAndDetailsArea;
import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class SettingsWizardPage extends AbstractWizardPage {

	private Logger log = ULog.getLogger(SettingsWizardPage.class);

	private static final long serialVersionUID = -1071694549535168966L;


	private AbstractSettingsPage currentPage;

	public SettingsWizardPage(AbstractWizard wizard) {
		super(wizard);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		JTreeWithFilterAndDetailsArea area = new JTreeWithFilterAndDetailsArea();
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;

		add(area, gbc_panel);

		DefaultMutableTreeNode root = PlatformUtil.getSettingsPageManager().getTreeNodes();
		root = TreeUtils.sort(root);
		area.setRootNode(root);
		area.setCellRenderer(new SettingsPageTreeCellRenderer());
		area.setFilterMatcher(new IFilterMatcher() {
			
			@Override
			public boolean matches(Object o, String filter) {
				
				SettingsPageData spd = (SettingsPageData) o;

				if (spd.label == null) {
					return false;
				}

				return spd.label.toLowerCase().contains(filter.toLowerCase());
				
				
			}
		});
		area.setTreeWidth(120);
		area.setDetailsAreaProvider(new IDetailsAreaProvider() {
			
			@Override
			public AbstractDetailsContentPanel<?> getDetailsPanel(Object selection) {
				
				return new AbstractDetailsContentPanel<SettingsPageData>((SettingsPageData)selection) {
					
					@Override
					public void onOpen() {}
					
					@Override
					public void onClose() {
						finishCurrentPage(true);
						
					}
					
					@Override
					public String getTitle() {
						return getObject().label;
					}
					
					@Override
					public Icon getIcon() {
						return getObject().icon;
					}
					
					@Override
					public Component createContents() {
						
						SettingsPageData spd = getObject();
						
						try {

							currentPage = spd.pageClass.newInstance();
							return currentPage.fillPage();

						} catch (InstantiationException | IllegalAccessException e) {
							log.error("Error creating settings page [" + spd.pageClass + "]");
							e.printStackTrace();
						}
						
						
						return null;
					}
				};
				
			}
		});

	}


	private void finishCurrentPage(boolean askToApply) {
		if (currentPage != null) {
			if (currentPage.isDirty() == true) {

				if (askToApply == true) {
					int result = JOptionPane.showConfirmDialog(SettingsWizardPage.this, "Apply Changes?", "Apply Changes", JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						currentPage.finish();
					}
				} else {
					currentPage.finish();
				}
			}
		}
	}

	
	@Override
	public void onLeave() {
		super.onLeave();
		if (currentPage != null)
			currentPage.close();
	}
	
	@Override
	public boolean isFinishAvailable() {
		return true;
	}

	@Override
	public boolean onFinish() {

		finishCurrentPage(false);
		return true;
	}

}
