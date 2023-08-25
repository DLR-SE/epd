package de.emir.rcp.other.navtool;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.physics.PhysicalObjectUtils;
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
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

public class ManeuverbordBearingToolPanel extends JPanel {
	
	public static Action createAction() {
		return new AbstractAction("Maneuverboard Bearing Tool") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog diag = new JDialog();
				diag.getContentPane().setLayout(new BorderLayout());
				diag.getContentPane().add(new ManeuverbordBearingToolPanel(), BorderLayout.CENTER);
				diag.setSize(700, 500);
				diag.setVisible(true);				
			}
		};
	}
	
	private Component mTxtOwnCourse;
	private Component mTxtOwnSpeed;
	private Component mTxtTime1;
	private Component mTxtRange1;
	private Component mTxtBearing1;
	private Component mTxtTime2;
	private Component mTxtRange2;
	private Component mTxtBearing2;
	
	private JTextField mTxtCPA;
	private JTextField mTxtTCPA;
	private JTextField mTxtTargetCourse;
	private JTextField mTxtTargetSpeed;

	private GenericProperty<Angle>		mOwnCourse 	= new GenericProperty<>("mOwnCourse", "", true, new AngleImpl(340, AngleUnit.DEGREE));
	private GenericProperty<Speed>		mOwnSpeed 	= new GenericProperty<>("mOwnSpeed", "", true, new SpeedImpl(15, SpeedUnit.KNOTS));
	
	private GenericProperty<Time>		mTime1 		= new GenericProperty<>("mTime1", "", true, new TimeImpl(0, TimeUnit.MINUTE));
	private GenericProperty<Time>		mTime2 		= new GenericProperty<>("mTime2", "", true, new TimeImpl(10, TimeUnit.MINUTE));
	
	private GenericProperty<Distance>	mRange1 	= new GenericProperty<>("mRange1", "", true, new DistanceImpl(9, DistanceUnit.NAUTICAL_MILES));
	private GenericProperty<Distance>	mRange2 	= new GenericProperty<>("mRange2", "", true, new DistanceImpl(9, DistanceUnit.NAUTICAL_MILES));
	private GenericProperty<Angle>		mBearing1 	= new GenericProperty<>("mBearing1", "", true, new AngleImpl(20, AngleUnit.DEGREE));
	private GenericProperty<Angle>		mBearing2 	= new GenericProperty<>("mBearing2", "", true, new AngleImpl(20, AngleUnit.DEGREE));
	private AngleImpl mTargetCourse;
	private SpeedImpl mTargetSpeed;
	private Time mTCPA;
	private Distance mDCPA;
	
	/**
	 * Create the panel.
	 */
	public ManeuverbordBearingToolPanel() {
		
		PropertyManager pmgr = PropertyManager.getInstance();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblOwnCourse = new JLabel("Own Course");
		lblOwnCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblOwnCourse = new GridBagConstraints();
		gbc_lblOwnCourse.anchor = GridBagConstraints.EAST;
		gbc_lblOwnCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnCourse.gridx = 0;
		gbc_lblOwnCourse.gridy = 0;
		add(lblOwnCourse, gbc_lblOwnCourse);
		
		mTxtOwnCourse = pmgr.getFirstEditor(mOwnCourse).getEditor();
		GridBagConstraints gbc_mTxtOwnCourse = new GridBagConstraints();
		gbc_mTxtOwnCourse.gridwidth = 2;
		gbc_mTxtOwnCourse.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtOwnCourse.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtOwnCourse.gridx = 1;
		gbc_mTxtOwnCourse.gridy = 0;
		add(mTxtOwnCourse, gbc_mTxtOwnCourse);
		
		JLabel lblOwnSpeed = new JLabel("Own Speed");
		lblOwnSpeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblOwnSpeed = new GridBagConstraints();
		gbc_lblOwnSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblOwnSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnSpeed.gridx = 0;
		gbc_lblOwnSpeed.gridy = 1;
		add(lblOwnSpeed, gbc_lblOwnSpeed);
		
		mTxtOwnSpeed = pmgr.getFirstEditor(mOwnSpeed).getEditor();
		GridBagConstraints gbc_mTxtOwnSpeed = new GridBagConstraints();
		gbc_mTxtOwnSpeed.gridwidth = 2;
		gbc_mTxtOwnSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtOwnSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtOwnSpeed.gridx = 1;
		gbc_mTxtOwnSpeed.gridy = 1;
		add(mTxtOwnSpeed, gbc_mTxtOwnSpeed);
		
		JLabel lblFix = new JLabel("Fix 1");
		lblFix.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblFix = new GridBagConstraints();
		gbc_lblFix.insets = new Insets(0, 0, 5, 5);
		gbc_lblFix.gridx = 1;
		gbc_lblFix.gridy = 3;
		add(lblFix, gbc_lblFix);
		
		JLabel lblFix_1 = new JLabel("Fix2");
		lblFix_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblFix_1 = new GridBagConstraints();
		gbc_lblFix_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblFix_1.gridx = 2;
		gbc_lblFix_1.gridy = 3;
		add(lblFix_1, gbc_lblFix_1);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 4;
		add(lblTime, gbc_lblTime);
		
		mTxtTime1 = pmgr.getFirstEditor(mTime1).getEditor();
		GridBagConstraints gbc_mTxtTime1 = new GridBagConstraints();
		gbc_mTxtTime1.insets = new Insets(0, 0, 5, 5);
		gbc_mTxtTime1.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtTime1.gridx = 1;
		gbc_mTxtTime1.gridy = 4;
		add(mTxtTime1, gbc_mTxtTime1);
		
		mTxtTime2 = pmgr.getFirstEditor(mTime2).getEditor();
		GridBagConstraints gbc_mTxtTime2 = new GridBagConstraints();
		gbc_mTxtTime2.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtTime2.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtTime2.gridx = 2;
		gbc_mTxtTime2.gridy = 4;
		add(mTxtTime2, gbc_mTxtTime2);
		
		JLabel lblRange = new JLabel("Range");
		lblRange.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblRange = new GridBagConstraints();
		gbc_lblRange.anchor = GridBagConstraints.EAST;
		gbc_lblRange.insets = new Insets(0, 0, 5, 5);
		gbc_lblRange.gridx = 0;
		gbc_lblRange.gridy = 5;
		add(lblRange, gbc_lblRange);
		
		mTxtRange1 = pmgr.getFirstEditor(mRange1).getEditor();
		GridBagConstraints gbc_mTxtRange1 = new GridBagConstraints();
		gbc_mTxtRange1.insets = new Insets(0, 0, 5, 5);
		gbc_mTxtRange1.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtRange1.gridx = 1;
		gbc_mTxtRange1.gridy = 5;
		add(mTxtRange1, gbc_mTxtRange1);
		
		mTxtRange2 = pmgr.getFirstEditor(mRange2).getEditor();
		GridBagConstraints gbc_mTxtRange2 = new GridBagConstraints();
		gbc_mTxtRange2.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtRange2.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtRange2.gridx = 2;
		gbc_mTxtRange2.gridy = 5;
		add(mTxtRange2, gbc_mTxtRange2);
		
		JLabel lblAbsoluteBearing = new JLabel("Absolute Bearing");
		lblAbsoluteBearing.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblAbsoluteBearing = new GridBagConstraints();
		gbc_lblAbsoluteBearing.anchor = GridBagConstraints.EAST;
		gbc_lblAbsoluteBearing.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbsoluteBearing.gridx = 0;
		gbc_lblAbsoluteBearing.gridy = 6;
		add(lblAbsoluteBearing, gbc_lblAbsoluteBearing);
		
		mTxtBearing1 = pmgr.getFirstEditor(mBearing1).getEditor();
		GridBagConstraints gbc_mTxtBearing1 = new GridBagConstraints();
		gbc_mTxtBearing1.insets = new Insets(0, 0, 5, 5);
		gbc_mTxtBearing1.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtBearing1.gridx = 1;
		gbc_mTxtBearing1.gridy = 6;
		add(mTxtBearing1, gbc_mTxtBearing1);
		
		mTxtBearing2 = pmgr.getFirstEditor(mBearing2).getEditor();
		GridBagConstraints gbc_mTxtBearing2 = new GridBagConstraints();
		gbc_mTxtBearing2.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtBearing2.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtBearing2.gridx = 2;
		gbc_mTxtBearing2.gridy = 6;
		add(mTxtBearing2, gbc_mTxtBearing2);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCalculate.gridwidth = 2;
		gbc_btnCalculate.gridx = 1;
		gbc_btnCalculate.gridy = 7;
		add(btnCalculate, gbc_btnCalculate);
		
		JLabel lblCpa = new JLabel("CPA");
		lblCpa.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCpa = new GridBagConstraints();
		gbc_lblCpa.anchor = GridBagConstraints.EAST;
		gbc_lblCpa.insets = new Insets(0, 0, 5, 5);
		gbc_lblCpa.gridx = 0;
		gbc_lblCpa.gridy = 8;
		add(lblCpa, gbc_lblCpa);
		
		mTxtCPA = new JTextField();
		mTxtCPA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mTxtCPA.setEditable(false);
		GridBagConstraints gbc_mTxtCPA = new GridBagConstraints();
		gbc_mTxtCPA.gridwidth = 2;
		gbc_mTxtCPA.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtCPA.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtCPA.gridx = 1;
		gbc_mTxtCPA.gridy = 8;
		add(mTxtCPA, gbc_mTxtCPA);
		
		JLabel lblTcpa = new JLabel("TCPA");
		lblTcpa.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTcpa = new GridBagConstraints();
		gbc_lblTcpa.anchor = GridBagConstraints.EAST;
		gbc_lblTcpa.insets = new Insets(0, 0, 5, 5);
		gbc_lblTcpa.gridx = 0;
		gbc_lblTcpa.gridy = 9;
		add(lblTcpa, gbc_lblTcpa);
		
		mTxtTCPA = new JTextField();
		mTxtTCPA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mTxtTCPA.setEditable(false);
		GridBagConstraints gbc_mTxtTCPA = new GridBagConstraints();
		gbc_mTxtTCPA.gridwidth = 2;
		gbc_mTxtTCPA.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtTCPA.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtTCPA.gridx = 1;
		gbc_mTxtTCPA.gridy = 9;
		add(mTxtTCPA, gbc_mTxtTCPA);
		
		JLabel lblTargetCourse = new JLabel("Target Course");
		lblTargetCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTargetCourse = new GridBagConstraints();
		gbc_lblTargetCourse.anchor = GridBagConstraints.EAST;
		gbc_lblTargetCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetCourse.gridx = 0;
		gbc_lblTargetCourse.gridy = 10;
		add(lblTargetCourse, gbc_lblTargetCourse);
		
		mTxtTargetCourse = new JTextField();
		mTxtTargetCourse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mTxtTargetCourse.setEditable(false);
		GridBagConstraints gbc_mTxtTargetCourse = new GridBagConstraints();
		gbc_mTxtTargetCourse.gridwidth = 2;
		gbc_mTxtTargetCourse.insets = new Insets(0, 0, 5, 0);
		gbc_mTxtTargetCourse.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtTargetCourse.gridx = 1;
		gbc_mTxtTargetCourse.gridy = 10;
		add(mTxtTargetCourse, gbc_mTxtTargetCourse);
		
		JLabel lblTargetSpeed = new JLabel("Target Speed");
		lblTargetSpeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTargetSpeed = new GridBagConstraints();
		gbc_lblTargetSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblTargetSpeed.insets = new Insets(0, 0, 0, 5);
		gbc_lblTargetSpeed.gridx = 0;
		gbc_lblTargetSpeed.gridy = 11;
		add(lblTargetSpeed, gbc_lblTargetSpeed);
		
		mTxtTargetSpeed = new JTextField();
		mTxtTargetSpeed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mTxtTargetSpeed.setEditable(false);
		GridBagConstraints gbc_mTxtTargetSpeed = new GridBagConstraints();
		gbc_mTxtTargetSpeed.gridwidth = 2;
		gbc_mTxtTargetSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_mTxtTargetSpeed.gridx = 1;
		gbc_mTxtTargetSpeed.gridy = 11;
		add(mTxtTargetSpeed, gbc_mTxtTargetSpeed);

	}

	protected void calculate() {
		calculateAbsoluteBearing(mOwnCourse.get(), mOwnSpeed.get(), mTime1.get(), mTime2.get(), mRange1.get(), mRange2.get(), mBearing1.get(), mBearing2.get());
		if (SwingUtilities.isEventDispatchThread())
			updateFields();
		else
			SwingUtilities.invokeLater(new Runnable() {				
				@Override
				public void run() {
					updateFields();
				}
			});
	}
	
	protected void updateFields() {
		if (mTargetCourse != null)
			mTxtTargetCourse.setText(MeasureImpl.round(mTargetCourse.getAs(AngleUnit.DEGREE), 2) + "[°]");
		if (mTargetSpeed != null)
			mTxtTargetSpeed.setText(MeasureImpl.round(mTargetSpeed.getAs(SpeedUnit.KNOTS), 2) + "[kn]");
		if (mTCPA != null){
			mTxtTCPA.setText(MeasureImpl.round(mTCPA.getAs(TimeUnit.MINUTE), 2) + "[min]");
		}
		if (mDCPA != null) {
			mTxtCPA.setText(MeasureImpl.round(mDCPA.getAs(DistanceUnit.NAUTICAL_MILES), 2) + "[NM]");
		}
			
	}

	private void calculateAbsoluteBearing(Angle own_cog, Speed own_sog, Time t0, Time t1, Distance d0, Distance d1, Angle b0, Angle b1) {
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
		mTargetSpeed = new SpeedImpl(tar_travel_dist.getAs(DistanceUnit.METER) / dt, SpeedUnit.METER_PER_SECOND);
		mTargetCourse = new AngleImpl(Math.toDegrees(new Engineering2DImpl().directionToBearing(tar_travel.getX(), tar_travel.getY(), Double.NaN).get(0)), AngleUnit.DEGREE);
		
		mTargetSpeed.roundLocal(2);
		mTargetCourse.roundLocal(2);
		mTCPA = PhysicalObjectUtils.getTimeToClosedPointOfApproach(new CoordinateImpl(0, 0, null), own_cog, own_sog, new CoordinateImpl(tar_loc_t0, null), mTargetCourse, mTargetSpeed);
		mDCPA = PhysicalObjectUtils.getDistanceAtClosedPointOfApproach(new CoordinateImpl(0, 0, null), own_cog, own_sog, new CoordinateImpl(tar_loc_t0, null), mTargetCourse, mTargetSpeed);
		
//		System.out.println(speed + "[m/s] Knots: " + speed.getAs(SpeedUnit.KNOTS) + "[kn]");
//		System.out.print("Curse: " + Math.toDegrees(new Engineering2DImpl().directionToBearing(tar_travel.getX(), tar_travel.getY(), Double.NaN).get(0)));
		
	}


}
