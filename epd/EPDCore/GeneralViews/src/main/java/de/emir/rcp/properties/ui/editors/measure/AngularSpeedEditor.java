package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.AngularSpeedImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AngularSpeedEditor extends AbstractMeasureEditor<AngularSpeed, AngularSpeedUnit>{

	
	private HashMap<AngularSpeedUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected List<AngularSpeedUnit> getAllUnits() {
		return Arrays.asList(AngularSpeedUnit.DEGREES_PER_MINUTE, AngularSpeedUnit.DEGREES_PER_SECOND, AngularSpeedUnit.DEGREES_PER_HOUR, AngularSpeedUnit.RADIANS_PER_SECOND, AngularSpeedUnit.RADIANS_PER_MINUTE, AngularSpeedUnit.RADIANS_PER_HOUR, AngularSpeedUnit.ROUNDS_PER_SECOND, AngularSpeedUnit.ROUNDS_PER_MINUTE, AngularSpeedUnit.ROUNDS_PER_HOUR);
	}

	
	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.AngularSpeed_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		Pointer ptr = PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
		boolean b = ptr.isValid();
		return ptr;
	}
	
	@Override
	protected ImageIcon getIcon(AngularSpeedUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new AngularSpeedImpl());
	}
}
