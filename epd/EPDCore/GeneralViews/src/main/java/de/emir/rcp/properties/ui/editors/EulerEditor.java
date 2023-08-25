package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;

public class EulerEditor extends AbstractPropertyEditor<Euler>{

	
	private JPanel 		mEditor = null;
	private boolean		mHorizontal = true;
	private JLabel mLblX;
	private JLabel mLblY;
	private JLabel mLblZ;
	private IPropertyEditor mPeX;
	private IPropertyEditor mPeY;
	private IPropertyEditor mPeZ;
	
	public void setHorizontalOrientation(){ mHorizontal = true;}
	public void setVerticalOrientation() { mHorizontal = false;}
	
	@Override
	public void dispose() {

	}

	@Override
	protected Component createComponent() {
		if (mEditor == null){
			mEditor = new JPanel();
			
			mLblX = new JLabel("X");
			mLblY = new JLabel("Y");
			mLblZ = new JLabel("Z");
			
			mPeX = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Euler_x));
			mPeY = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Euler_y));
			mPeZ = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Euler_z));
			
			if (mHorizontal){
				layoutHorizontal();
			}else
				layoutVertical();
		}
		return mEditor;
	}

	private void layoutVertical() {
		mEditor.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		mEditor.add(mLblX, gbc); gbc.gridy++;
		mEditor.add(mLblY, gbc); gbc.gridy++;
		mEditor.add(mLblZ, gbc); gbc.gridy++;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridy++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridy++;
		mEditor.add(mPeZ.getEditor(), gbc); gbc.gridy++;
	}

	private void layoutHorizontal() {
		mEditor.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		mEditor.add(mLblX, gbc); gbc.gridx++;
		mEditor.add(mLblY, gbc); gbc.gridx++;
		mEditor.add(mLblZ, gbc); gbc.gridx++;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridx++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridx++;
		mEditor.add(mPeZ.getEditor(), gbc); gbc.gridx++;
	
	}

	private void createHorizontalEditor(JPanel parent) {
		
		
		
		
	}

}
