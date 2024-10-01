package de.emir.rcp.other.navtool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.service.routeservices.IRouteExport;
import de.emir.service.routeservices.impl.RTZRouteExportImpl;
import de.emir.service.routeservices.impl.RouteExportImpl;
import de.emir.service.routeservices.impl.SimpleRouteFileExportImpl;
import de.emir.tuml.ucore.runtime.UCoreExtensionManager;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class MultiManeuverbordBearingToolPanel extends JPanel {
	
	public static Action createAction() {
		return new AbstractAction("Multi Maneuverboard Bearing Tool") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog diag = new JDialog();
				diag.getContentPane().setLayout(new BorderLayout());
				diag.getContentPane().add(new MultiManeuverbordBearingToolPanel(), BorderLayout.CENTER);
				diag.setSize(700, 500);
				diag.setVisible(true);				
			}
		};
	}
	
	ArrayList<FixData>			mFixes = new ArrayList<>();
	private GenericProperty<Coordinate>		mStartPosition = new GenericProperty<>("StartPosition", "", true, new CoordinateImpl(0,0, CRSUtils.WGS84_2D));
	
	
	public class BearingPanel extends JPanel {
		
		/**
		 * Create the panel.
		 */
		public BearingPanel(FixData fd, PropertyManager pmgr) {

			setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Fix"));
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			JLabel lblTime = new JLabel("Time");
			GridBagConstraints gbc_lblTime = new GridBagConstraints();
			gbc_lblTime.anchor = GridBagConstraints.EAST;
			gbc_lblTime.insets = new Insets(0, 0, 5, 5);
			gbc_lblTime.gridx = 0;
			gbc_lblTime.gridy = 0;
			add(lblTime, gbc_lblTime);
			
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.gridwidth = 3;
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 0;
			add(pmgr.getFirstEditor(fd.time).getEditor(), gbc_textField_4);
			
			if (fd.targetCourse != null) {
				JLabel lblTargetCourse = new JLabel("Target Course");
				GridBagConstraints gbc_lblTargetCourse = new GridBagConstraints();
				gbc_lblTargetCourse.insets = new Insets(0, 0, 5, 5);
				gbc_lblTargetCourse.anchor = GridBagConstraints.EAST;
				gbc_lblTargetCourse.gridx = 4;
				gbc_lblTargetCourse.gridy = 0;
				add(lblTargetCourse, gbc_lblTargetCourse);
				
				
				GridBagConstraints gbc_textField_5 = new GridBagConstraints();
				gbc_textField_5.insets = new Insets(0, 0, 5, 0);
				gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_5.gridx = 5;
				gbc_textField_5.gridy = 0;
				add(pmgr.getFirstEditor(fd.targetCourse).getEditor(), gbc_textField_5);
			}
			
			JLabel lblOwnCourse = new JLabel("Own Course");
			GridBagConstraints gbc_lblOwnCourse = new GridBagConstraints();
			gbc_lblOwnCourse.anchor = GridBagConstraints.EAST;
			gbc_lblOwnCourse.insets = new Insets(0, 0, 5, 5);
			gbc_lblOwnCourse.gridx = 0;
			gbc_lblOwnCourse.gridy = 1;
			add(lblOwnCourse, gbc_lblOwnCourse);
			
			
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			add(pmgr.getFirstEditor(fd.ownCourse).getEditor(), gbc_textField_1);
			
			JLabel lblTargetBearing = new JLabel("Target Bearing");
			GridBagConstraints gbc_lblTargetBearing = new GridBagConstraints();
			gbc_lblTargetBearing.insets = new Insets(0, 0, 5, 5);
			gbc_lblTargetBearing.anchor = GridBagConstraints.EAST;
			gbc_lblTargetBearing.gridx = 2;
			gbc_lblTargetBearing.gridy = 1;
			add(lblTargetBearing, gbc_lblTargetBearing);
			
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 1;
			add(pmgr.getFirstEditor(fd.targetBearing).getEditor(), gbc_textField);
			
			if (fd.targetSpeed != null) {
				JLabel lblTargetSpeed = new JLabel("Target Speed");
				GridBagConstraints gbc_lblTargetSpeed = new GridBagConstraints();
				gbc_lblTargetSpeed.anchor = GridBagConstraints.EAST;
				gbc_lblTargetSpeed.insets = new Insets(0, 0, 5, 5);
				gbc_lblTargetSpeed.gridx = 4;
				gbc_lblTargetSpeed.gridy = 1;
				add(lblTargetSpeed, gbc_lblTargetSpeed);
				
				GridBagConstraints gbc_textField_6 = new GridBagConstraints();
				gbc_textField_6.insets = new Insets(0, 0, 5, 0);
				gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_6.gridx = 5;
				gbc_textField_6.gridy = 1;
				add(pmgr.getFirstEditor(fd.targetSpeed).getEditor(), gbc_textField_6);
			}
			
			JLabel lblOwnSpeed = new JLabel("Own Speed");
			GridBagConstraints gbc_lblOwnSpeed = new GridBagConstraints();
			gbc_lblOwnSpeed.anchor = GridBagConstraints.EAST;
			gbc_lblOwnSpeed.insets = new Insets(0, 0, 0, 5);
			gbc_lblOwnSpeed.gridx = 0;
			gbc_lblOwnSpeed.gridy = 2;
			add(lblOwnSpeed, gbc_lblOwnSpeed);
			
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 0, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 2;
			add(pmgr.getFirstEditor(fd.ownSpeed).getEditor(), gbc_textField_2);
			
			JLabel lblTargetDistance = new JLabel("Target Distance");
			GridBagConstraints gbc_lblTargetDistance = new GridBagConstraints();
			gbc_lblTargetDistance.anchor = GridBagConstraints.EAST;
			gbc_lblTargetDistance.insets = new Insets(0, 0, 0, 5);
			gbc_lblTargetDistance.gridx = 2;
			gbc_lblTargetDistance.gridy = 2;
			add(lblTargetDistance, gbc_lblTargetDistance);
			
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 3;
			gbc_textField_3.gridy = 2;
			add(pmgr.getFirstEditor(fd.targetDistance).getEditor(), gbc_textField_3);

			if (fd.tcpa != null) {
				JLabel lblCpa = new JLabel("CPA");
				GridBagConstraints gbc_lblCpa = new GridBagConstraints();
				gbc_lblCpa.anchor = GridBagConstraints.EAST;
				gbc_lblCpa.insets = new Insets(0, 0, 0, 5);
				gbc_lblCpa.gridx = 4;
				gbc_lblCpa.gridy = 2;
				add(lblCpa, gbc_lblCpa);
				
				JTextField textField_7 = new JTextField(fd.tcpa.get().getAs(TimeUnit.MINUTE) + "[min]; " + fd.dcpa.get().getAs(DistanceUnit.NAUTICAL_MILES) + "[nM]");
				textField_7.setEditable(false);
				GridBagConstraints gbc_textField_7 = new GridBagConstraints();
				gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_7.gridx = 5;
				gbc_textField_7.gridy = 2;
				add(textField_7, gbc_textField_7);
			}
		}

	}
	
	/**
	 * Create the panel.
	 */
	public MultiManeuverbordBearingToolPanel() {
		mFixes.add(new FixData());
		mFixes.add(new FixData()); //we need at least two
		setup();
	}
	
	
	
	public void setup() {
		removeAll();
		setLayout(new BorderLayout(0, 0));
		
		PropertyManager pmgr = PropertyManager.getInstance();
		JPanel listPanel = new JPanel();
		GridBagLayout gbl_listPanel = new GridBagLayout();
		gbl_listPanel.rowWeights = new double[]{};
		gbl_listPanel.columnWeights = new double[]{};
		listPanel.setLayout(gbl_listPanel);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 15);
		
		gbc.gridx = 0;
		gbc.gridy = 0;

		for (int i = 0; i < mFixes.size(); i++) {
			FixData fd = mFixes.get(i);
			JPanel fixPanel = new JPanel();
			fixPanel.setLayout(new BorderLayout());
			JButton remove = new JButton("Remove");
			remove.addActionListener(acl -> {
				mFixes.remove(fd);
				setup();
				updateUI();
			});
			if (i >= 2)
				fixPanel.add(remove, BorderLayout.EAST);
			fixPanel.add(new BearingPanel(fd, pmgr), BorderLayout.CENTER);
			listPanel.add(fixPanel, gbc);
			gbc.gridy++;
		}
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mFixes.add(new FixData());
				setup();
				updateUI();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnCopyLast = new JButton("Copy Last");
		btnCopyLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mFixes.add(new FixData(mFixes.get(mFixes.size()-1)));
				setup();
				updateUI();
			}
		});
		GridBagConstraints gbc_btnCopyLast = new GridBagConstraints();
		gbc_btnCopyLast.insets = new Insets(0, 0, 5, 0);
		gbc_btnCopyLast.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCopyLast.gridx = 0;
		gbc_btnCopyLast.gridy = 1;
		panel.add(btnCopyLast, gbc_btnCopyLast);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate(mFixes);
				setup();
				updateUI();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(mFixes);
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 4;
		panel.add(btnSave, gbc_btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<FixData> tmp = load();
				if (tmp != null && tmp.size() >= 2) {
					mFixes.clear();
					mFixes.addAll(tmp);
					setup();
					updateUI();
				}
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 5;
		panel.add(btnLoad, gbc_btnLoad);
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate.gridx = 0;
		gbc_btnCalculate.gridy = 7;
		panel.add(btnCalculate, gbc_btnCalculate);
		
		JButton btnExportRoutes = new JButton("Export Routes");
		btnExportRoutes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportRoute(mFixes);
			}
		});
		GridBagConstraints gbc_btnExportRoutes = new GridBagConstraints();
		gbc_btnExportRoutes.insets = new Insets(0, 0, 5, 0);
		gbc_btnExportRoutes.gridx = 0;
		gbc_btnExportRoutes.gridy = 8;
		panel.add(btnExportRoutes, gbc_btnExportRoutes);
		
		JLabel lblStartCoordinate = new JLabel("Start Coordinate");
		GridBagConstraints gbc_lblStartCoordinate = new GridBagConstraints();
		gbc_lblStartCoordinate.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartCoordinate.gridx = 0;
		gbc_lblStartCoordinate.gridy = 9;
		panel.add(lblStartCoordinate, gbc_lblStartCoordinate);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 10;
		panel.add(pmgr.getFirstEditor(mStartPosition).getEditor(), gbc_textField);
	}



	protected void exportRoute(ArrayList<FixData> fixes) {
		//do the calculation, just to be sure
		calculate(fixes);
		Route ownRoute = new RouteImpl();
		ownRoute.setWaypoints(new WayPointsImpl());
		Route tarRoute = new RouteImpl();
		tarRoute.setWaypoints(new WayPointsImpl());
		
		WayPoints ownWps = ownRoute.getWaypoints();
		WayPoints tarWps = tarRoute.getWaypoints();
		for (int i = 0; i < fixes.size(); i++) {
			Waypoint owp = new WaypointImpl();
			Waypoint twp = new WaypointImpl();
			
			String name = "WP_" + i + "_" + fixes.get(i).time.get().getAs(TimeUnit.MINUTE);
			owp.setName(name); twp.setName(name);
			owp.setId(i);twp.setId(i);
			owp.setPosition(new CoordinateImpl(fixes.get(i).ownCoordinate));
			twp.setPosition(new CoordinateImpl(fixes.get(i).targetCoordiante));
			
			ownWps.getWaypoints().add(owp);
			tarWps.getWaypoints().add(twp);
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Routes");
		if (fileChooser.showSaveDialog(PlatformUtil.getWindowManager().getActiveFrame()) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String name = file.getName();
			String ext = "";
			if (name.lastIndexOf('.') > 0) {
				ext = name.substring(name.lastIndexOf('.')+1);
				name = name.substring(0, name.lastIndexOf('.'));
			}
			File pFile = file.getParentFile();
			File ownFile = new File(pFile.getAbsolutePath() + "/" + name + "_own." + ext);
			File tarFile = new File(pFile.getAbsolutePath() + "/" + name + "_target." + ext);
			
			
			IRouteExport exporter = null;
			if (file.getName().endsWith(".xml") || file.getName().endsWith(".rtz")) {
				exporter = new RTZRouteExportImpl();
			}else
				exporter = new SimpleRouteFileExportImpl();
			
			try {
				((RouteExportImpl)exporter).exportToFile(ownRoute, ownFile);
				((RouteExportImpl)exporter).exportToFile(tarRoute, tarFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
				
	}



	protected List<FixData> load() {
		XmlMapper mapper = new XmlMapper();
		Thread.currentThread().setContextClassLoader(UCoreExtensionManager.getExtension("MultiContextClassLoader", ClassLoader.class));

		TypeFactory factory = mapper.getTypeFactory();
		MapType mapType = factory.constructMapType(HashMap.class, String.class, PropertyContext.class);
        List<FixData> obj = null;
        try {
            obj = mapper.readValue(ResourceManager.get(MultiManeuverbordBearingToolPanel.class).resolveFile("ManeuverBoard.xml"), new TypeReference<List<FixData>>() {
            });
        } catch (IOException e) {
			e.printStackTrace();
            return null;
        }
		return obj;
	}



	protected void save(ArrayList<FixData> fixes) {
		XmlMapper mapper = new XmlMapper();
		try {
			File f = new File(ResourceManager.get(MultiManeuverbordBearingToolPanel.class).getHomePath().toFile().getAbsolutePath() + "/ManeuverBoard.xml");
			mapper.writeValue(new FileOutputStream(f), fixes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	protected void calculate(ArrayList<FixData> fixes) {
		fixes.get(0).ownCoordinate = new CoordinateImpl(mStartPosition.get().copy());
		for (int i = 1; i < fixes.size(); i++) {
			FixData fd1 = fixes.get(i-1);
			FixData fd2 = fixes.get(i);
			fd2.ownCoordinate = null; 
			fd2.targetCoordiante = null;
			
//			calculateAbsoluteBearing(fd1.ownCourse.get(), fd1.ownSpeed.get(), fd1.time.get(), fd2.time.get(), fd1.targetDistance.get(), fd2.targetDistance.get(), fd1.targetBearing.get(), fd2.targetBearing.get(), fd2);
			calculate(fd1, fd2);
		}
	}
	
	private void calculate(FixData fd0, FixData fd1) {
		Time dt = fd1.time.get().sub(fd0.time.get());
		//own coordinate is always set, the first one, is the one of the starting point (UI)
		Geodesic calc = Geodesic.WGS84;
		Coordinate ownLoc0 = fd0.ownCoordinate;
		Coordinate tarLoc0 = null;
		if (fd0.targetCoordiante == null) {
			GeodesicData targetLoc0_gd = calc.Direct(ownLoc0.getLatitude(), ownLoc0.getLongitude(), fd0.targetBearing.get().normalize180().getAs(AngleUnit.DEGREE), fd0.targetDistance.get().getAs(DistanceUnit.METER));
			tarLoc0 = fd0.targetCoordiante = new CoordinateImpl(targetLoc0_gd.lat2, targetLoc0_gd.lon2, CRSUtils.WGS84_2D);
		}else
			tarLoc0 = fd0.targetCoordiante;
		
		
		//interpolate the own position, based on speed and course
		Distance ownDist0 = fd0.ownSpeed.get().integrate(dt);
		GeodesicData ownLoc1_gd = calc.Direct(ownLoc0.getLatitude(), ownLoc0.getLongitude(), fd0.ownCourse.get().normalize180().get(AngleUnit.DEGREE), ownDist0.getAs(DistanceUnit.METER));
		Coordinate ownLoc1 = fd1.ownCoordinate = new CoordinateImpl(ownLoc1_gd.lat2, ownLoc1_gd.lon2, CRSUtils.WGS84_2D);
		
		//do the same as above to calculate the targets position but now from the interpolated position of the own ship
		GeodesicData targetLoc1_gd = calc.Direct(ownLoc1.getLatitude(), ownLoc1.getLongitude(), fd1.targetBearing.get().normalize180().getAs(AngleUnit.DEGREE), fd1.targetDistance.get().getAs(DistanceUnit.METER));
		Coordinate tarLoc1 = fd1.targetCoordiante = new CoordinateImpl(targetLoc1_gd.lat2, targetLoc1_gd.lon2, CRSUtils.WGS84_2D);
		
		//calculate the distance, the target ship has travelled 
		GeodesicData res = calc.Inverse(tarLoc0.getLatitude(), tarLoc0.getLongitude(), tarLoc1.getLatitude(), tarLoc1.getLongitude());
		fd0.targetSpeed = new GenericProperty<>("target Speed", "", false, new SpeedImpl(new SpeedImpl(res.s12 / dt.getAs(TimeUnit.SECOND), SpeedUnit.METER_PER_SECOND).getAs(SpeedUnit.KNOTS), SpeedUnit.KNOTS));
		fd0.targetCourse = new GenericProperty<>("target course", "", false, new AngleImpl(res.azi1, AngleUnit.DEGREE).normalize());
		
		fd0.targetSpeed.get().roundLocal(2);
		fd0.targetCourse.get().roundLocal(2);
		fd0.tcpa = new GenericProperty<>("tcpa", "", false, PhysicalObjectUtils.getTimeToClosedPointOfApproach(ownLoc0, fd0.ownCourse.get(), fd0.ownSpeed.get(), tarLoc0, fd0.targetCourse.get(), fd0.targetSpeed.get()));
		fd0.dcpa = new GenericProperty<>("dcpa", "", false, PhysicalObjectUtils.getDistanceAtClosedPointOfApproach(ownLoc0, fd0.ownCourse.get(), fd0.ownSpeed.get(), tarLoc0, fd0.targetCourse.get(), fd0.targetSpeed.get()));
		
	}
	private void calculateAbsoluteBearing(Angle own_cog, Speed own_sog, Time t0, Time t1, Distance d0, Distance d1, Angle b0, Angle b1, FixData result) {
		double dt = t1.sub(t0).getAs(TimeUnit.SECOND);
		//assume own ship at the center of the origin and calc the position (cartesian) for t1
		final Vector2D north = new Vector2DImpl(0, 1);
		final Vector2D own_dir = north.rotateCW(own_cog.getAs(AngleUnit.RADIAN)).normalize();
		final Vector2D own_loc_t1 = own_dir.mult(own_sog.integrate(t1.sub(t0)).getAs(DistanceUnit.METER));
		
		//calculate target (tar) ships location at t0
		Vector2D tar_rel_dir = north.rotateCW(b0.getAs(AngleUnit.RADIAN)).normalize();
		final Vector2D tar_loc_t0 = tar_rel_dir.mult(d0.getAs(DistanceUnit.METER));
		//calculate target (tar) ships location at t1, based on own location at t1
		tar_rel_dir = north.rotateCW(b1.getAs(AngleUnit.RADIAN)).normalize();
		final Vector2D tar_loc_t1 = own_loc_t1.add(tar_rel_dir.mult(d1.getAs(DistanceUnit.METER)));
		
		final Vector2D tar_travel = tar_loc_t1.sub(tar_loc_t0);
		final Distance tar_travel_dist = new DistanceImpl(tar_travel.getLength(), DistanceUnit.METER);
		result.targetSpeed = new GenericProperty<>("target Speed", "", false, new SpeedImpl(tar_travel_dist.getAs(DistanceUnit.METER) / dt, SpeedUnit.METER_PER_SECOND));
		result.targetCourse = new GenericProperty<>("target course", "", false, new AngleImpl(Math.toDegrees(new Engineering2DImpl().directionToBearing(tar_travel.getX(), tar_travel.getY(), Double.NaN).get(0)), AngleUnit.DEGREE).normalize());
		
		result.targetSpeed.get().roundLocal(2);
		result.targetCourse.get().roundLocal(2);
		result.tcpa = new GenericProperty<>("tcpa", "", false, PhysicalObjectUtils.getTimeToClosedPointOfApproach(new CoordinateImpl(0, 0, null), own_cog, own_sog, new CoordinateImpl(tar_loc_t0, null), result.targetCourse.get(), result.targetSpeed.get()));
		result.dcpa = new GenericProperty<>("dcpa", "", false, PhysicalObjectUtils.getDistanceAtClosedPointOfApproach(new CoordinateImpl(0, 0, null), own_cog, own_sog, new CoordinateImpl(tar_loc_t0, null), result.targetCourse.get(), result.targetSpeed.get()));
		
		
//		System.out.println(speed + "[m/s] Knots: " + speed.getAs(SpeedUnit.KNOTS) + "[kn]");
//		System.out.print("Curse: " + Math.toDegrees(new Engineering2DImpl().directionToBearing(tar_travel.getX(), tar_travel.getY(), Double.NaN).get(0)));
		
	}
	

}
