package de.emir.rcp.other.navtool;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.impl.Polar2DImpl;
import de.emir.model.universal.crs.impl.Polar3DImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.util.WidgetUtils;
import de.emir.rcp.views.properties.JPropertySheet;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CoordinateTransform extends JPanel {


	public static Action createAction() {
		return new AbstractAction("Coordinate Transform") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog diag = new JDialog();
				diag.getContentPane().setLayout(new BorderLayout());
				diag.getContentPane().add(new CoordinateTransform(), BorderLayout.CENTER);
				diag.setSize(700, 500);
				diag.setVisible(true);				
			}
		};
		
	}

	/**
	 * Create the panel.
	 */
	public CoordinateTransform() {
		setLayout(new BorderLayout());

		List<CoordinateReferenceSystem> crss = new ArrayList<>();
		crss.add(new WGS842DImpl());
		crss.add(new WGS843DImpl());
		crss.add(new Engineering2DImpl());
		crss.add(new Engineering3DImpl());
		crss.add(new Polar2DImpl());
		crss.add(new Polar3DImpl());
		
		Coordinate src = new CoordinateImpl(0,0, crss.get(0));
		Coordinate dst = new CoordinateImpl(0,0, crss.get(2));
		
		JPanel srcPanel = WidgetUtils.createGroup("Source");
		JPanel dstPanel = WidgetUtils.createGroup("Target");
		
		JPropertySheet srcPS = new JPropertySheet();
		JPropertySheet dstPS = new JPropertySheet();
		
		
		Map<String, IProperty> srcCRSProps = PropertyManager.getInstance().collectProperties(src);
		srcCRSProps.get("crs").addPropertyChangeListener(new PropertyChangeListener() {			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				srcPS.clear();
				srcPS.addProperties(srcCRSProps.values());
				srcPS.addProperties(PropertyManager.getInstance().collectProperties(src.getCrs()).values());
				srcPS.updateUI();
			}
		});
		((AssociationProperty)srcCRSProps.get("crs")).setValueHint(crss); // TODO what does this do?
		srcPS.addProperties(srcCRSProps.values());
		srcPS.addProperties(PropertyManager.getInstance().collectProperties(src.getCrs()).values());
		
		srcPanel.setLayout(new BorderLayout());
		srcPanel.add(srcPS, BorderLayout.CENTER);
		
		add(srcPanel, BorderLayout.WEST);
		
		
		Map<String, IProperty> dstCRSProps = PropertyManager.getInstance().collectProperties(dst);
		dstCRSProps.get("crs").addPropertyChangeListener(new PropertyChangeListener() {			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dstPS.clear();
				dstPS.addProperties(dstCRSProps.values());
				dstPS.addProperties(PropertyManager.getInstance().collectProperties(dst.getCrs()).values());
				dstPS.updateUI();
			}
		});
		((AssociationProperty)dstCRSProps.get("crs")).setValueHint(crss); // TODO what does this do?
		dstPS.addProperties(dstCRSProps.values());
		dstPS.addProperties(PropertyManager.getInstance().collectProperties(dst.getCrs()).values());
		
		dstPanel.setLayout(new BorderLayout());
		dstPanel.add(dstPS, BorderLayout.CENTER);
		
		add(dstPanel, BorderLayout.EAST);
		
		JButton btn = new JButton("Transform");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Coordinate t = src.get(dst.getCrs());
				dst.setXYZ(t.getX(), t.getY(), t.getZ());
				dstPS.clear();
				dstPS.addProperties(dstCRSProps.values());
				dstPS.addProperties(PropertyManager.getInstance().collectProperties(dst.getCrs()).values());
				dstPS.updateUI();
			}
		});
		add(btn, BorderLayout.SOUTH);
	}



	

}
