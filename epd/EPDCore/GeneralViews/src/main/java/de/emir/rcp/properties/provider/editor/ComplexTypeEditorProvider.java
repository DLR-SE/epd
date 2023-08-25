package de.emir.rcp.properties.provider.editor;

import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.io.IFile;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Quaternion;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.ui.editors.CoordinateEditor;
import de.emir.rcp.properties.ui.editors.EulerEditor;
import de.emir.rcp.properties.ui.editors.FileEditor;
import de.emir.rcp.properties.ui.editors.ModelPathEditor;
import de.emir.rcp.properties.ui.editors.PoseEditor;
import de.emir.rcp.properties.ui.editors.QuaternionEditor;
import de.emir.rcp.properties.ui.editors.VectorEditor;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ComplexTypeEditorProvider implements IPropertyEditorProvider
{

	@Override
	public IPropertyEditor provide(IProperty property) {
		Object value = property.getValue();
		if (value == null) return null;
		if (value instanceof ModelPath)
			return new ModelPathEditor();
		if (value instanceof Vector)
			return new VectorEditor();
		else if (value instanceof Coordinate)
			return new CoordinateEditor();
		else if (value instanceof Euler)
			return new EulerEditor();
		else if (value instanceof IFile)
			return new FileEditor();
		else if (value instanceof Quaternion)
			return new QuaternionEditor(true);
		else if (value instanceof Pose)
			return new PoseEditor();
		
		return null;
	}
}
