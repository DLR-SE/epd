package de.emir.rcp.properties.ui.editors;

import de.emir.model.universal.units.Measure;


public abstract class AbstractMeasureEditor<TYPE extends Measure, UNIT_TYPE> extends AbstractMeasurementEditor<TYPE, UNIT_TYPE> {
	
	protected double getMeasureValue() {
		if (getValue() == null)
			return Double.NaN;
		return getValue().getValue();
	}
}
