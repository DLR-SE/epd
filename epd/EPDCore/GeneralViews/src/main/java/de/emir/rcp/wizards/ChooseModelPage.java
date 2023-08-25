package de.emir.rcp.wizards;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class ChooseModelPage extends AbstractWizardPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9057701872333510601L;
	private boolean mFinish = false;
	
	private JComboBox classCombo;
	
	public ChooseModelPage(AbstractWizard w) {
		super(w);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		JLabel packLbl = new JLabel("Package");
		gc.gridx = 0; gc.gridy = 0;
		add(packLbl, gc);
		
		JLabel classLbl = new JLabel("Classifier");
		gc.gridy = 1;
		add(classLbl, gc);
		
		JComboBox<String> packageCombo = new JComboBox<>();
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridy = 0;
		add(packageCombo, gc);
		
		classCombo = new JComboBox<UClass>();
		gc.gridy = 1;
		add(classCombo, gc);
		
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
				w.updateButtons();
			}
		});
		
		classCombo.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				mFinish = true;
				w.updateButtons();
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
