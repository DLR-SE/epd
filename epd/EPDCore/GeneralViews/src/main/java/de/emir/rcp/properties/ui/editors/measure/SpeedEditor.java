package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class SpeedEditor extends AbstractMeasureEditor<Speed, SpeedUnit>{

	
	private HashMap<SpeedUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	
	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Speed_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}
	
	@Override
	protected List<SpeedUnit> getAllUnits() {
		return Arrays.asList(SpeedUnit.KMH, SpeedUnit.KM_PER_MINUTE, SpeedUnit.KM_PER_SECOND, SpeedUnit.KNOTS, SpeedUnit.METER_PER_SECOND, SpeedUnit.METER_PER_MINUTE, SpeedUnit.METER_PER_HOUR);
	}

	@Override
	protected ImageIcon getIcon(SpeedUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}


	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new SpeedImpl());
	}

}
