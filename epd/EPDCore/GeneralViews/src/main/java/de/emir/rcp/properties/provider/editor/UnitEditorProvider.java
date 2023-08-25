package de.emir.rcp.properties.provider.editor;

import de.emir.model.universal.units.UnitsPackage;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.ui.editors.AbstractUnitEditor;
import de.emir.rcp.properties.ui.editors.QuaternionEditor;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class UnitEditorProvider implements IPropertyEditorProvider {

	@Override
	public IPropertyEditor provide(IProperty property) {
		UType expected = null;
		AssociationProperty ap = null;
		if (property instanceof AssociationProperty){
			ap = (AssociationProperty)property;
			expected = ap.getPointer().getExpectedType();
		}else {
			expected = UCoreMetaRepository.findClass(property.getType());
		}
		
		if (expected == UnitsPackage.Literals.AngleUnit)
			return new AbstractUnitEditor.AngleUnitEditor();
		if (expected == UnitsPackage.Literals.TimeUnit) {
			return new AbstractUnitEditor.TimeUnitEditor();
		}
		if (expected == UnitsPackage.Literals.SpeedUnit)
			return new AbstractUnitEditor.SpeedUnitEditor();
		if (expected == UnitsPackage.Literals.AngularSpeedUnit)
			return new AbstractUnitEditor.AngularSpeedUnitEditor();
		if (expected == UnitsPackage.Literals.AngularAccelerationUnit)
			return new AbstractUnitEditor.AngularAccelerationUnitEditor();
		if (expected == UnitsPackage.Literals.AccelerationUnit)
			return new AbstractUnitEditor.AccelerationUnitEditor();
		if (expected == UnitsPackage.Literals.DistanceUnit)
			return new AbstractUnitEditor.DistanceUnitEditor();
		if (expected == UnitsPackage.Literals.TemperatureUnit)
			return new AbstractUnitEditor.TemperatureUnitEditor();
		if (expected == UnitsPackage.Literals.MassUnit)
			return new AbstractUnitEditor.MassUnitEditor();
		
		return null;
	}

}
