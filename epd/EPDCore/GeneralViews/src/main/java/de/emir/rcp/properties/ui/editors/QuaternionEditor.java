package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;

public class QuaternionEditor extends AbstractPropertyEditor<Quaternion>{

	
	private JPanel 			mEditor = null;
	private boolean			mHorizontal = true;
	private JLabel 			mLblX;
	private JLabel			mLblY;
	private JLabel			mLblZ;
	private JLabel 			mLblW;
	private IPropertyEditor mPeX;
	private IPropertyEditor mPeY;
	private IPropertyEditor mPeZ;
	private IPropertyEditor mPeW;
	
	public void setHorizontalOrientation(){ mHorizontal = true;}
	public void setVerticalOrientation() { mHorizontal = false;}

	public QuaternionEditor() {
		this(true);
	}
	public QuaternionEditor(boolean horizontal) {
		mHorizontal = horizontal;
	}
	
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
			mLblZ = new JLabel("W");
			
			mPeX = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Quaternion_x));
			mPeY = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Quaternion_y));
			mPeZ = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Quaternion_z));
			mPeW = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), UnitsPackage.Literals.Quaternion_w));
			
			
			
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
		if (mLblZ != null){
			mEditor.add(mLblZ, gbc); gbc.gridy++;
		}
		if (mLblW != null){
			mEditor.add(mLblZ, gbc); gbc.gridy++;
		}
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridy++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridy++;
		if (mPeZ != null){
			mEditor.add(mPeZ.getEditor(), gbc); gbc.gridy++;
		}
		if (mPeW != null){
			mEditor.add(mPeW.getEditor(), gbc); gbc.gridy++;
		}
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
		if (mLblZ != null){
			mEditor.add(mLblZ, gbc); gbc.gridx++;
		}
		if (mLblW != null){
			mEditor.add(mLblZ, gbc); gbc.gridx++;
		}
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridx++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridx++;
		if (mPeZ != null){
			mEditor.add(mPeZ.getEditor(), gbc); gbc.gridx++;
		}
		if (mPeW != null){
			mEditor.add(mPeW.getEditor(), gbc); gbc.gridx++;
		}
	}

}
