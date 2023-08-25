package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.AngularAcceleration;
import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.AngularAccelerationImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AngularAccelerationEditor extends AbstractMeasureEditor<AngularAcceleration, AngularAccelerationUnit>{

	
	private HashMap<AngularAccelerationUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<AngularAccelerationUnit> getAllUnits() {
		return Arrays.asList(
				AngularAccelerationUnit.DEGREE_PER_SQUARE_MINUTE, AngularAccelerationUnit.DEGREE_PER_SQUARE_SECOND,
				AngularAccelerationUnit.RADIANS_PER_SQUARE_MINUTE, AngularAccelerationUnit.RADIANS_PER_SQUARE_SECOND, 
				AngularAccelerationUnit.ROUNDS_PER_SQUARE_MINUTE, AngularAccelerationUnit.ROUNDS_PER_SQUARE_SECOND);
	}
	
	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.AngularAcceleration_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}
	
	@Override
	protected ImageIcon getIcon(AngularAccelerationUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new AngularAccelerationImpl());
	}
}
