package de.emir.rcp.properties.ui.editors;

import de.emir.rcp.cmd.SetValueCommand;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.ui.editors.cmd.PropertyEditorChangeTransaction;
import de.emir.rcp.util.LatexRender;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public abstract class AbstractMeasurementEditor<TYPE extends UObject, UNIT_TYPE> extends AbstractPropertyEditor<TYPE> {

    private static final ImageIcon null_icon = IconManager.getIcon(AbstractMeasurementEditor.class, "icons/emiricons/32/data_object.png", IconManager.preferedSmallIconSize());
    JPanel mEditor = null;

    JComboBox<ImageIcon> mUnitField = null;
    private boolean mInternalUpdate = false;
    private Color mBackgroundColor;


    final HashMap<UNIT_TYPE, ImageIcon> unitImageMap = new HashMap<>();
    final HashMap<ImageIcon, UNIT_TYPE> imageUnitMap = new HashMap<>();

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createComponent() {
        if (mEditor == null) {
            mEditor = new JPanel();
            mEditor.setLayout(new BorderLayout());

            if (getProperty().isEditable() && getValue() == null)
                createValue();
            IPropertyEditor edt = PropertyManager.getInstance().getDefaultEditor(getValuePointer());
            if (edt == null) {
                JLabel edtComp = new JLabel("Could not find Editor for: " + getValuePointer());
                mEditor.add(edtComp, BorderLayout.CENTER);
            } else {
                Component comp = edt.getEditor();
                if (comp != null) {
                    edt.addPropertyChangeListener(pcl -> {
                        ((AbstractProperty) getProperty()).firePropertyChange(pcl.getOldValue(), pcl.getNewValue());
                    });
                    mEditor.add(comp, BorderLayout.CENTER);
                } else {
                    throw new NullPointerException("Failed to create value editor");
                }

            }

            List<UNIT_TYPE> units = getAllUnits();
            mUnitField = new JComboBox<>();


            mUnitField.addItem(null_icon);
            imageUnitMap.put(null_icon, null);
            unitImageMap.put(null, null_icon);

            for (UNIT_TYPE unit : units) {
                ImageIcon icon = getIcon(unit);
                mUnitField.addItem(icon);
                imageUnitMap.put(icon, unit);
                unitImageMap.put(unit, icon);
            }

            UNIT_TYPE current_unit = (UNIT_TYPE) getUnitPointer().getValue();
            if (current_unit != null) {
                ImageIcon icon = unitImageMap.get(current_unit);
                if (icon == null) {
                    icon = getIcon(current_unit);
                    mUnitField.addItem(icon);
                    imageUnitMap.put(icon, current_unit);
                    unitImageMap.put(current_unit, icon);
                }
                mUnitField.setSelectedItem(icon);
            } else {
                mUnitField.setSelectedItem(null_icon);
            }

            mUnitField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!mInternalUpdate)
                        _setUnit(imageUnitMap.get(mUnitField.getSelectedItem()));
                }
            });

            mUnitField.setEnabled(getProperty().isEditable());
            mEditor.add(mUnitField, BorderLayout.EAST);

            customize(mEditor);
        }
        return mEditor;
    }

    /**
     * Option to customize the editor a little.
     * Note that the AbstractMeasure uses the CENTER position (of BorderLayout) for the value and the EAST position for the unit
     *
     * @param editor
     */
    protected void customize(JPanel editor) {

    }

    protected void _setUnit(UNIT_TYPE unit) {
        Pointer ptr = getUnitPointer();
        if (ptr == null) return;
        if (ptr.isValid() == false) {
            createValue();
            ptr = getUnitPointer();
            if (ptr == null || ptr.isValid() == false)
                return;
        }
        AbstractEditor editor = PlatformUtil.getEditorManager().getActiveEditor();
        if (editor != null) {
            //if we could identify an editor, we will do the change inside the editors context
            PropertyEditorChangeTransaction pecc = new PropertyEditorChangeTransaction(ptr, unit, editor);
            editor.getTransactionStack().run(pecc);
        } else {
            //otherwise, just create a simple (non - editor) command
            CommandManager cm = ServiceManager.get(CommandManager.class);
            cm.executeCommand(new SetValueCommand(ptr, unit));
        }
        //FIXME: we call it here manually, since we do change values within the property and thus do not call the method setValue()
        if (getProperty() instanceof AbstractProperty) {
            ((AbstractProperty) getProperty()).firePropertyChange(null, getValue());
        }

    }

    private void _setValue(Double value) {
        //sets the value but checks if the instance is already there, if not the instance is created, using createValue()
        Pointer valuePtr = getValuePointer();
        if (valuePtr == null) return;
        if (valuePtr.isValid() == false) {
            createValue();
            valuePtr = getValuePointer();
            if (valuePtr == null || valuePtr.isValid() == false)
                return;
        }
        AbstractEditor editor = PlatformUtil.getEditorManager().getActiveEditor();
        if (editor != null) {
            //if we could identify an editor, we will do the change inside the editors context
            PropertyEditorChangeTransaction pecc = new PropertyEditorChangeTransaction(valuePtr, value, editor);
            editor.getTransactionStack().run(pecc);
        } else {
            //otherwise, just create a simple (non - editor) command
            CommandManager cm = ServiceManager.get(CommandManager.class);
            cm.executeCommand(new SetValueCommand(valuePtr, value));
        }
        //FIXME: we call it here manually, since we do change values within the property and thus do not call the method setValue()
        if (getProperty() instanceof AbstractProperty) {
            ((AbstractProperty) getProperty()).firePropertyChange(null, getValue());
        }

    }


    /**
     * creates a new value instance
     * this method is called, if the setter can not be performed, tue to an invalid valuePointer
     */
    protected abstract void createValue();

    protected abstract Pointer getUnitPointer();

    protected abstract Pointer getValuePointer();


    protected abstract ImageIcon getIcon(UNIT_TYPE unit);

    protected abstract List<UNIT_TYPE> getAllUnits();

    protected BufferedImage createImage(String tex) {
        try {
            return LatexRender.createImage(tex);
        } catch (IOException e) {
            ULog.error(e);
            return null;
        }
    }

    protected ImageIcon createIcon(String tex) {
        try {
            return LatexRender.createIcon(tex);
        } catch (IOException e) {
            ULog.error(e);
            return null;
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean supportValueUpdates() {
        return true;
    }

    @Override
    public void updateEditorValue() {
        mInternalUpdate = true;
        try {
            UNIT_TYPE current_unit = (UNIT_TYPE) getUnitPointer().getValue();
            if (current_unit != null) {
                ImageIcon icon = unitImageMap.get(current_unit);
                if (icon == null) {
                    icon = getIcon(current_unit);
                    mUnitField.addItem(icon);
                    imageUnitMap.put(icon, current_unit);
                    unitImageMap.put(current_unit, icon);
                }
                mUnitField.setSelectedItem(icon);
            } else {
                mUnitField.setSelectedItem(null_icon);
            }
        } finally {
            mInternalUpdate = false;
        }
    }
}
