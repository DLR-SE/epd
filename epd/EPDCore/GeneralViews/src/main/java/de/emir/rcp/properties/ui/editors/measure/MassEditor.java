package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.Mass;
import de.emir.model.universal.units.MassUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.MassImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class MassEditor extends AbstractMeasureEditor<Mass, MassUnit>
{

	private static HashMap<MassUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<MassUnit> getAllUnits() {
		return Arrays.asList(
				MassUnit.GRAM,
				MassUnit.KILOGRAM,
				MassUnit.TONNE
		);
	}


	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Mass_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}


	@Override
	protected ImageIcon getIcon(MassUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new MassImpl());
	}
}
