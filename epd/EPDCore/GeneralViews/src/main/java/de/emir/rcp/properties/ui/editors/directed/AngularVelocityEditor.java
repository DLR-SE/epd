package de.emir.rcp.properties.ui.editors.directed;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.rcp.properties.ui.editors.AbstractMeasurementEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AngularVelocityEditor extends AbstractMeasurementEditor<AngularVelocity, AngularSpeedUnit>
{

	private static HashMap<AngularSpeedUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<AngularSpeedUnit> getAllUnits() {
		return Arrays.asList(AngularSpeedUnit.DEGREES_PER_MINUTE, AngularSpeedUnit.DEGREES_PER_SECOND, AngularSpeedUnit.DEGREES_PER_HOUR, AngularSpeedUnit.RADIANS_PER_SECOND, AngularSpeedUnit.RADIANS_PER_MINUTE, AngularSpeedUnit.RADIANS_PER_HOUR, AngularSpeedUnit.ROUNDS_PER_SECOND, AngularSpeedUnit.ROUNDS_PER_MINUTE, AngularSpeedUnit.ROUNDS_PER_HOUR);
	}

	@Override
	protected void customize(JPanel editor) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("CRS"), BorderLayout.WEST);
		p.add(PropertyManager.getInstance().getEditorComponent(getValue(), UnitsPackage.Literals.AngularVelocity_crs), BorderLayout.CENTER);
		editor.add(p, BorderLayout.SOUTH);
	}

	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.AngularVelocity_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.AngularVelocity_value);
	}


	@Override
	protected ImageIcon getIcon(AngularSpeedUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new AngularVelocityImpl(new EulerImpl(0, 0, 0, AngleUnit.DEGREE), AngularSpeedUnit.DEGREES_PER_SECOND, null));
	}
}
