package de.emir.rcp.properties.ui.editors.measure;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.UnitSymbolUtil;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.rcp.properties.ui.editors.AbstractMeasureEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class TimeEditor extends AbstractMeasureEditor<Time, TimeUnit>
{

	private HashMap<TimeUnit, BufferedImage> 	mUnitIconMap = new HashMap<>();
	
	@Override
	public void dispose() {

	}

	@Override
	protected Pointer getUnitPointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Time_unit);
	}

	@Override
	protected Pointer getValuePointer() {
		return PointerOperations.create(getValue(), UnitsPackage.Literals.Measure_value);
	}

	@Override
	protected List<TimeUnit> getAllUnits() {
		return Arrays.asList(TimeUnit.DAY, TimeUnit.HOUR, TimeUnit.MICROSECOND, TimeUnit.MILLISECOND, TimeUnit.MINUTE, TimeUnit.NANOSECOND, TimeUnit.SECOND);
	}
	
	@Override
	protected ImageIcon getIcon(TimeUnit unit) {
		if (mUnitIconMap.containsKey(unit) == false)
			mUnitIconMap.put(unit, createImage(UnitSymbolUtil.getSymbol(unit)));
		return new ImageIcon(mUnitIconMap.get(unit));
	}

	@Override
	protected void createValue() {
		getUCoreProperty().getPointer().setValue(new TimeImpl());	
	}

}
