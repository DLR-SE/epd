package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class LengthEditor extends AbstractMeasureEditor<Length, DistanceUnit>
{

	private static HashMap<DistanceUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<DistanceUnit> getAllUnits() {
		return Arrays.asList(
				DistanceUnit.METER,
				DistanceUnit.KILOMETER,
				DistanceUnit.NAUTICAL_MILES,
				DistanceUnit.CENTIMETER,
				DistanceUnit.MILLIMETER,
				DistanceUnit.CABLE,
				DistanceUnit.FOOT,
				DistanceUnit.MILES,
				DistanceUnit.YARD
		);
	}


	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Length_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}


	@Override
	protected ImageIcon getIcon(DistanceUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		setValue(new LengthImpl(0, DistanceUnit.METER));
	}
}
