package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.PolarCRS;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;

public class CoordinateEditor extends AbstractPropertyEditor<Coordinate>{

	
	private JPanel 		mEditor = null;
	private boolean		mHorizontal = true;
	private JLabel mLblX;
	private JLabel mLblY;
	private JLabel mLblZ;
	private JLabel mLblCRS;
	private IPropertyEditor mPeX;
	private IPropertyEditor mPeY;
	private IPropertyEditor mPeZ;
	private IPropertyEditor mPeCRS;
	
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
			mLblCRS = new JLabel("CRS");
			
			mPeX = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), SpatialPackage.Literals.Coordinate_x));
			mPeY = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), SpatialPackage.Literals.Coordinate_y));
			mPeZ = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), SpatialPackage.Literals.Coordinate_z));
			mPeCRS = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), SpatialPackage.Literals.Coordinate_crs));
			
			if (mHorizontal){
				layoutHorizontal();
			}else
				layoutVertical();
			
			//register some listener to change the labels 
			mPeCRS.addPropertyChangeListener(pcl->{
				if (pcl.getNewValue() != null && pcl.getNewValue() instanceof CoordinateReferenceSystem)
					updateLabelsAndFields((CoordinateReferenceSystem)pcl.getNewValue());
			});
			_updateLabelsAndFields(getValue().getCrs());
		}
		return mEditor;
	}

	private void updateLabelsAndFields(final CoordinateReferenceSystem crs) {
		if (SwingUtilities.isEventDispatchThread())
			_updateLabelsAndFields(crs);
		else
			SwingUtilities.invokeLater(new Runnable() {				
				@Override
				public void run() {
					_updateLabelsAndFields(crs);		
				}
			});
	}
	
	private void _updateLabelsAndFields(final CoordinateReferenceSystem crs) {
		//allways executed in ui thread
		if (crs == null) return ;
		if (crs.dimension() == 2){
			mPeZ.getEditor().setEnabled(false);
		}else
			mPeZ.getEditor().setEnabled(true);
		
		if (crs instanceof WGS84CRS){
			mLblX.setText("Lat");
			mLblY.setText("Lon");
			mLblZ.setText("Alt");
		}else if (crs instanceof PolarCRS){
			mLblX.setText("Dist");
			mLblY.setText("Alpha");
			mLblZ.setText("Beta");
		}else{
			mLblX.setText("X");
			mLblY.setText("Y");
			mLblZ.setText("Z");
		}
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
		mEditor.add(mLblCRS, gbc); gbc.gridy++;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridy++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridy++;
		mEditor.add(mPeZ.getEditor(), gbc); gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		mEditor.add(mPeCRS.getEditor(), gbc); gbc.gridy++;
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
		mEditor.add(mLblCRS, gbc); gbc.gridx++;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		mEditor.add(mPeX.getEditor(), gbc); gbc.gridx++;
		mEditor.add(mPeY.getEditor(), gbc); gbc.gridx++;
		mEditor.add(mPeZ.getEditor(), gbc); gbc.gridx++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		mEditor.add(mPeCRS.getEditor(), gbc); gbc.gridx++;
		
	}

	private void createHorizontalEditor(JPanel parent) {
		
		
		
		
	}

}
