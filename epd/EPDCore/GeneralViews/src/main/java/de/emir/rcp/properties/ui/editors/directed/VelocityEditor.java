package de.emir.rcp.properties.ui.editors.directed;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.ui.editors.AbstractMeasurementEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class VelocityEditor extends AbstractMeasurementEditor<Velocity, SpeedUnit>
{

	private static HashMap<SpeedUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<SpeedUnit> getAllUnits() {
		return Arrays.asList(SpeedUnit.KMH, SpeedUnit.KM_PER_MINUTE, SpeedUnit.KM_PER_SECOND, SpeedUnit.KNOTS, SpeedUnit.METER_PER_SECOND, SpeedUnit.METER_PER_MINUTE, SpeedUnit.METER_PER_HOUR);
	}

	@Override
	protected void customize(JPanel editor) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("CRS"), BorderLayout.WEST);
		p.add(PropertyManager.getInstance().getEditorComponent(getValue(), UnitsPackage.Literals.Velocity_crs), BorderLayout.CENTER);
		editor.add(p, BorderLayout.SOUTH);
	}

	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Velocity_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Velocity_value);
	}


	@Override
	protected ImageIcon getIcon(SpeedUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new VelocityImpl());
	}
}
