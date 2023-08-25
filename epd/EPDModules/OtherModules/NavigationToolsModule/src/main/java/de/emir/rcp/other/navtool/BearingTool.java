package de.emir.rcp.other.navtool;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.impl.Polar2DImpl;
import de.emir.model.universal.crs.impl.Polar3DImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.util.WidgetUtils;
import de.emir.rcp.views.properties.JPropertySheet;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class BearingTool extends JPanel {


	public static Action createAction() {
		return new AbstractAction("Bearing Tool") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog diag = new JDialog();
				diag.getContentPane().setLayout(new BorderLayout());
				diag.getContentPane().add(new BearingTool(), BorderLayout.CENTER);
				diag.setSize(700, 500);
				diag.setVisible(true);				
			}
		};
	}
	
	
	GenericProperty<Angle>			mBearingProperty = new GenericProperty<>("Bearing", "Bearing angle against north", true, new AngleImpl(0, AngleUnit.DEGREE));
	GenericProperty<Distance>		mDistanceProperty = new GenericProperty<>("Distance", "Distance in direction of bearing", true, new DistanceImpl(0, DistanceUnit.METER));
	GenericProperty<Coordinate>		mResultCoordinate = new GenericProperty<Coordinate>("Coordinate", "Result Coordinate", true, new CoordinateImpl(0, 0, CRSUtils.WGS84_2D));
	
	Coordinate						mSRC = null;
	private final java.util.List<CoordinateReferenceSystem> 			crss; //used as storage for the different CRS
	/**
	 * Create the panel.
	 */
	public BearingTool() {
		//prepare some coordinate reference system, so we can choose from them
		crss = new ArrayList<>();
		crss.add(new WGS842DImpl());
		crss.add(new WGS843DImpl());
		crss.add(new Engineering2DImpl());
		crss.add(new Engineering3DImpl());
		crss.add(new Polar2DImpl());
		crss.add(new Polar3DImpl());
		
		mSRC = new CoordinateImpl(0,0, crss.get(0));
		mResultCoordinate.get().setCrs(mSRC.getCrs());
		_rebuild();
	}
	
	private void _rebuild() {
		removeAll();
		setLayout(new BorderLayout());
		
		
		JPanel srcPanel = WidgetUtils.createGroup("Source");
		JPanel dstPanel = WidgetUtils.createGroup("Bearing");
		
		JPropertySheet srcPS = new JPropertySheet();
//		JPropertySheet dstPS = new JPropertySheet();
		
		
		Map<String, IProperty> srcCRSProps = PropertyManager.getInstance().collectProperties(mSRC);
		srcCRSProps.get("crs").addPropertyChangeListener(new PropertyChangeListener() {			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				srcPS.clear();
				srcPS.addProperties(srcCRSProps.values());
				srcPS.addProperties(PropertyManager.getInstance().collectProperties(mSRC.getCrs()).values());
				srcPS.updateUI();
			}
		});
		((AssociationProperty)srcCRSProps.get("crs")).setValueHint(crss); // TODO what does this do?
		srcPS.addProperties(srcCRSProps.values());
		srcPS.addProperties(PropertyManager.getInstance().collectProperties(mSRC.getCrs()).values());
		
		srcPanel.setLayout(new BorderLayout());
		srcPanel.add(srcPS, BorderLayout.CENTER);
		
		add(srcPanel, BorderLayout.WEST);
		
		
		/////////////////////////////////////////////////////////////
		//bearing, distance and result
		/////////////////////////////////////////////////////////////
		
		dstPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		IPropertyEditor bearingEditor = PropertyManager.getInstance().getFirstEditor(mBearingProperty);
		dstPanel.add(new JLabel("Bearing"), gbc); gbc.gridy++;
		dstPanel.add(bearingEditor.getEditor(), gbc); gbc.gridy++;
		
		IPropertyEditor distanceEditor = PropertyManager.getInstance().getFirstEditor(mDistanceProperty);
		dstPanel.add(new JLabel("Distance"), gbc); gbc.gridy++;
		dstPanel.add(distanceEditor.getEditor(), gbc); gbc.gridy++;
		
		IPropertyEditor resultEditor = PropertyManager.getInstance().getFirstEditor(mResultCoordinate);
		dstPanel.add(new JLabel("Result"), gbc); gbc.gridy++;
		dstPanel.add(resultEditor.getEditor(), gbc); gbc.gridy++;
		
		gbc.weighty = 99;
		dstPanel.add(new JLabel(""), gbc);
		add(dstPanel, BorderLayout.EAST);
		
		JButton btn = new JButton("Transform");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double deg = mBearingProperty.get().normalize180().getAs(AngleUnit.DEGREE);
				double dist = mDistanceProperty.get().getAs(DistanceUnit.METER);
				double lat = mSRC.getLatitude();
				double lon = mSRC.getLongitude();
				
				GeodesicData dest = Geodesic.WGS84.Direct(lat, lon, deg, dist);
				Coordinate res = mResultCoordinate.get();
				res.setLatLon(dest.lat2, dest.lon2);
				new Thread() { public void run() { rebuild(); }}.start();
			}
		});
		add(btn, BorderLayout.SOUTH);
	}

	public void rebuild() {
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				_rebuild();
				updateUI();
			}
		});
	}
	

	

}
