package de.emir.rcp.wizards.types.ucore;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.emir.rcp.wizards.NewFileWizard;
import de.emir.rcp.wizards.NewFileWizardModel;
import de.emir.rcp.wizards.types.AbstractNewFileWizardPage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class ChooseModelPage extends AbstractNewFileWizardPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9057701872333510601L;
	private boolean mFinish = false;
	
	private JComboBox classCombo;
	
	public ChooseModelPage(NewFileWizard wizard, NewFileWizardModel model) {
		super(wizard, model);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10, 5, 0, 5);
		
		JLabel packLbl = new JLabel("Package");
		gc.gridx = 0; gc.gridy = 0;
		add(packLbl, gc);
		
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.insets = new Insets(5, 5, 0, 5);
		JLabel classLbl = new JLabel("Classifier");
		gc2.gridy = 1;
		add(classLbl, gc2);
		
		JComboBox<String> packageCombo = new JComboBox<>();
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.insets = new Insets(10, 0, 0, 5);
		gc3.gridx = 1;
		gc3.fill = GridBagConstraints.HORIZONTAL;
		gc3.gridy = 0;
		add(packageCombo, gc3);
		
		classCombo = new JComboBox<UClass>();
		GridBagConstraints gc4 = new GridBagConstraints();
		gc4.insets = new Insets(5, 0, 0, 5);
		gc4.fill = GridBagConstraints.HORIZONTAL;
		gc4.gridy = 1;
		add(classCombo, gc4);
		
		Collection<UClass> classifier = getRootClasses();
		HashSet<UPackage> packages = new HashSet<>();
		for (UClass cl : classifier){
			UPackage p = cl.getPackage();
			if (p != null)
				packages.add(p);
		}
		ArrayList<UPackage> sorted = new ArrayList<>();
		sorted.addAll(packages);
		Collections.sort(sorted, new Comparator<UPackage>() {
			@Override
			public int compare(UPackage o1, UPackage o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (UPackage p : sorted)
			packageCombo.addItem(p.getName());
		
		packageCombo.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				classCombo.removeAllItems();
				mFinish = false;
				UPackage p = UCoreMetaRepository.findPackage(packageCombo.getSelectedItem().toString());
				ArrayList<UClass> cl = new ArrayList<>();
				for (UModelElement el : p.getContent()){
					if (el instanceof UClass && ((UClass)el).getAbstract() == false)
						cl.add((UClass)el);
				}
				Collections.sort(cl, new Comparator<UClass>() {
					@Override
					public int compare(UClass o1, UClass o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
				for (UClass c : cl)
					classCombo.addItem(c.getName());
				wizard.updateButtons();
			}
		});
		
		classCombo.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				mFinish = true;
				
				model.setAdditionalInformations(getSelectedClassifier());
				
				wizard.updateButtons();
			}
		});
	}
	
	@Override
	public boolean isFinishAvailable() {
		return mFinish;
	}
	@Override
	public boolean isCancelAvailable() {
		return true;
	}
	
	public UClass getSelectedClassifier() {
		
		if(classCombo.getSelectedItem() == null) {
			return null;
		}
		
		String str = classCombo.getSelectedItem().toString();
		return mFinish ? UCoreMetaRepository.findClassBySimpleName(str) : null;
	}
	
	protected Collection<UClass> getRootClasses() {
		List<UClassifier> all = UCoreMetaRepository.getAllClassifier();
		ArrayList<UClass> out = new ArrayList<UClass>();
		for (UClassifier cl : all){
			if (cl instanceof UClass && ((UClass)cl).getAbstract() == false)
				out.add((UClass)cl);
		}
		return out;
	}
	
}
