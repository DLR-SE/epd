package de.emir.rcp.other.navtool;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class ClassifierTypeTool extends JPanel {


	public static Action createAction() {
		return new AbstractAction("ClassifierTypes") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<UClassifier> list = UCoreMetaRepository.getAllClassifier();
				ArrayList<UEnum> enums = new ArrayList<>();
				ArrayList<UClassifier> structs = new ArrayList<>();
				ArrayList<UClassifier> classifiers = new ArrayList<>();
				
				for (UClassifier cl : list) {
					if (cl instanceof UEnum) {
						enums.add((UEnum) cl);
					}else if (cl instanceof UClass) {
						if (((UClass)cl).isStruct()) {
							structs.add(cl);
						}else
							classifiers.add(cl);
					}
				}
				
				ULog.info("------------------------Enumerations (Start)------------------", 1);
				for (UEnum u : enums) { ULog.info(u.getName()); }
				ULog.info(-1, "------------------------Enumerations (End)------------------");
				
				ULog.info("------------------------Structs (Start)------------------", 1);
				for (UClassifier u : structs) { ULog.info(u.getName()); }
				ULog.info(-1, "------------------------Structs (End)------------------");
				
				ULog.info("------------------------Classifier (Start)------------------", 1);
				for (UClassifier u : classifiers) { ULog.info(u.getName()); }
				ULog.info(-1, "------------------------Classifier (End)------------------");
			}
		};
	}

}
