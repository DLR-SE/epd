package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.AccelerationUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.AccelerationImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AccelerationEditor extends AbstractMeasureEditor<Acceleration, AccelerationUnit>{

	private static HashMap<AccelerationUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<AccelerationUnit> getAllUnits() {
		return Arrays.asList(
				AccelerationUnit.KM_PER_SQUARE_HOUR, AccelerationUnit.KM_PER_SQUARE_MINUTE, AccelerationUnit.KM_PER_SQUARE_SECOND, 
				AccelerationUnit.METER_PER_SQUARE_HOUR, AccelerationUnit.METER_PER_SQUARE_MINUTE, AccelerationUnit.METER_PER_SQUARE_SECOND,
				AccelerationUnit.NAUTICALMILES_PER_SQUARE_HOUR, AccelerationUnit.NAUTICALMILES_PER_SQUARE_MINUTE, AccelerationUnit.NAUTICALMILES_PER_SQUARE_SECOND
				);
	}
	
	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Acceleration_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}

	@Override
	protected ImageIcon getIcon(AccelerationUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new AccelerationImpl());
	}


}
