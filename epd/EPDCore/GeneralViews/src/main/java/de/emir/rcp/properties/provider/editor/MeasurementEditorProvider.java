package de.emir.rcp.properties.provider.editor;

import de.emir.model.universal.units.UnitsPackage;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.ui.editors.directed.AngularVelocityEditor;
import de.emir.rcp.properties.ui.editors.directed.VelocityEditor;
import de.emir.rcp.properties.ui.editors.measure.AccelerationEditor;
import de.emir.rcp.properties.ui.editors.measure.AngleEditor;
import de.emir.rcp.properties.ui.editors.measure.AngularAccelerationEditor;
import de.emir.rcp.properties.ui.editors.measure.AngularSpeedEditor;
import de.emir.rcp.properties.ui.editors.measure.DateTimeEditor;
import de.emir.rcp.properties.ui.editors.measure.DistanceEditor;
import de.emir.rcp.properties.ui.editors.measure.LengthEditor;
import de.emir.rcp.properties.ui.editors.measure.MassEditor;
import de.emir.rcp.properties.ui.editors.measure.SpeedEditor;
import de.emir.rcp.properties.ui.editors.measure.TemperatureEditor;
import de.emir.rcp.properties.ui.editors.measure.TimeEditor;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class MeasurementEditorProvider implements IPropertyEditorProvider {

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
		
		if (expected == UnitsPackage.Literals.Angle)
			return new AngleEditor();
		if (expected == UnitsPackage.Literals.Time) {
			if (ap != null && ap.getPointer() != null && ap.getPointer().getPointedFeature() != null && ap.getPointer().getPointedFeature().getAnnotation("DateTime") != null)
				return new DateTimeEditor();
			return new TimeEditor();
		}
		if (expected == UnitsPackage.Literals.Speed)
			return new SpeedEditor();
		if (expected == UnitsPackage.Literals.AngularSpeed)
			return new AngularSpeedEditor();
		if (expected == UnitsPackage.Literals.AngularAcceleration)
			return new AngularAccelerationEditor();
		if (expected == UnitsPackage.Literals.Acceleration)
			return new AccelerationEditor();
		if (expected == UnitsPackage.Literals.Length)
			return new LengthEditor();
		if (expected == UnitsPackage.Literals.Distance)
			return new DistanceEditor();
		if (expected == UnitsPackage.Literals.Temperature)
			return new TemperatureEditor();
		if (expected == UnitsPackage.Literals.Mass)
			return new MassEditor();
		
		
		if (expected == UnitsPackage.Literals.Velocity)
			return new VelocityEditor();
		if (expected == UnitsPackage.Literals.AngularVelocity)
			return new AngularVelocityEditor();
		return null;
	}

}
