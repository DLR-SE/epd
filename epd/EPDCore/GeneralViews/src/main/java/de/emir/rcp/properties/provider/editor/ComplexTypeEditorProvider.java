package de.emir.rcp.properties.provider.editor;

import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.io.IFile;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Quaternion;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.IPropertyEditorProvider;
import de.emir.rcp.properties.ui.editors.CoordinateEditor;
import de.emir.rcp.properties.ui.editors.CoordinateSequenceEditor;
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
        switch (value) {
            case null -> {
                return null;
            }
            case Vector vector -> {
                return new VectorEditor();
            }
            case Coordinate coordinate -> {
                return new CoordinateEditor();
            }
            case Euler euler -> {
                return new EulerEditor();
            }
            case IFile file -> {
                return new FileEditor();
            }
            case Quaternion quaternion -> {
                return new QuaternionEditor(true);
            }
            case Pose pose -> {
                return new PoseEditor();
            }
            case CoordinateSequence coordinateSequence -> {
                return new CoordinateSequenceEditor();
            }
            case ModelPath modelPath -> {
                return new ModelPathEditor();
            }
            default -> {
                return null;
            }
        }
    }
}
