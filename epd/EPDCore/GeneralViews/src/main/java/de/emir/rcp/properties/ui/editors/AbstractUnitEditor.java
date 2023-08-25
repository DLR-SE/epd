package de.emir.rcp.properties.ui.editors;

import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.AccelerationUnitImpl;
import de.emir.model.universal.units.impl.AngularAccelerationUnitImpl;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.rcp.util.LatexRender;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public abstract class AbstractUnitEditor<UNIT_TYPE> extends AbstractPropertyEditor<UNIT_TYPE> implements PropertyChangeListener {


    //stores ALL icons, but that shouldn't be a problem, only that the list is getting a little bigger
    private static HashMap<Object, BufferedImage> mUnitIconMap = new HashMap<>(); // [UNIT_TYPE, BufferedImage]
    private static HashMap<Class<?>, List<Object>> mAllUnits = new HashMap<>(); //[UNIT_TYPE.class, allUnits]

    static {
        mUnitIconMap.put(null, IconManager.getScaledImage(ResourceManager.get(AbstractUnitEditor.class).getResource("icons/emiricons/32/data_object.png"), IconManager.preferedSmallIconSize()));
    }

    JComboBox<UNIT_TYPE> mUnitField = null;
    private boolean mInternalUpdate = false;


    final HashMap<ImageIcon, UNIT_TYPE> mImageUnitMap = new HashMap<>();
    final HashMap<UNIT_TYPE, ImageIcon> mUnitImageMap = new HashMap<>();

    class UnitTypeRenderer extends BasicComboBoxRenderer {
        private static final long serialVersionUID = -2222080060946984225L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            //try to get the icon from the global map, if it does not exist, we create it
            BufferedImage bimg = mUnitIconMap.get(value);
            if (bimg == null) {
                bimg = createUnitImage((UNIT_TYPE) value);
                if (bimg != null) mUnitIconMap.put(value, bimg);
            }
            if (bimg != null) {
                setIcon(new ImageIcon(bimg));
                setText("");
            }
            return this;
        }


    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createComponent() {
        if (mUnitField == null) {

            Vector<UNIT_TYPE> units = new Vector<UNIT_TYPE>(getAllUnits());
            units.add(0, null); //for the case it's not defined yet

            mUnitField = new JComboBox<UNIT_TYPE>(units);
            mUnitField.setEnabled(getProperty().isEditable());
            mUnitField.setRenderer(new UnitTypeRenderer());

            UNIT_TYPE current = getValue();
            mUnitField.setSelectedItem(current);

            mUnitField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setValue(createNewUnitInstance((UNIT_TYPE) mUnitField.getSelectedItem()));
                }
            });

        }
        return mUnitField;
    }


    protected abstract BufferedImage createUnitImage(UNIT_TYPE value);

    protected abstract List<UNIT_TYPE> getAllUnits();

    protected abstract UNIT_TYPE createNewUnitInstance(UNIT_TYPE value);


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
    public void propertyChange(PropertyChangeEvent evt) {
        if (mInternalUpdate) return;
        //TODO: change unit
    }


    public static class AngleUnitEditor extends AbstractUnitEditor<AngleUnit> {
        @Override
        protected List<AngleUnit> getAllUnits() {
            return Arrays.asList(
                    AngleUnit.values()
            );
        }

        @Override
        protected BufferedImage createUnitImage(AngleUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected AngleUnit createNewUnitInstance(AngleUnit unit_TYPE) {
            return unit_TYPE;//it's an enum
        }

    }

    public static class TimeUnitEditor extends AbstractUnitEditor<TimeUnit> {
        @Override
        protected BufferedImage createUnitImage(TimeUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<TimeUnit> getAllUnits() {
            return Arrays.asList(
                    TimeUnit.values()
            );
        }

        @Override
        protected TimeUnit createNewUnitInstance(TimeUnit unit_TYPE) {
            return unit_TYPE; //it's an enum
        }
    }

    public static class AngularSpeedUnitEditor extends AbstractUnitEditor<AngularSpeedUnit> {
        @Override
        protected BufferedImage createUnitImage(AngularSpeedUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<AngularSpeedUnit> getAllUnits() {
            return Arrays.asList(
                    AngularSpeedUnit.DEGREES_PER_MINUTE,
                    AngularSpeedUnit.DEGREES_PER_SECOND,
                    AngularSpeedUnit.DEGREES_PER_HOUR,
                    AngularSpeedUnit.RADIANS_PER_SECOND,
                    AngularSpeedUnit.RADIANS_PER_MINUTE,
                    AngularSpeedUnit.RADIANS_PER_HOUR,
                    AngularSpeedUnit.ROUNDS_PER_SECOND,
                    AngularSpeedUnit.ROUNDS_PER_MINUTE,
                    AngularSpeedUnit.ROUNDS_PER_HOUR
            );
        }

        @Override
        protected AngularSpeedUnit createNewUnitInstance(AngularSpeedUnit unit_TYPE) {
            return new AngularSpeedUnitImpl(unit_TYPE);
        }
    }

    public static class SpeedUnitEditor extends AbstractUnitEditor<SpeedUnit> {
        @Override
        protected BufferedImage createUnitImage(SpeedUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<SpeedUnit> getAllUnits() {
            return Arrays.asList(
                    SpeedUnit.KMH,
                    SpeedUnit.KM_PER_MINUTE,
                    SpeedUnit.KM_PER_SECOND,
                    SpeedUnit.KNOTS,
                    SpeedUnit.METER_PER_SECOND,
                    SpeedUnit.METER_PER_MINUTE,
                    SpeedUnit.METER_PER_HOUR
            );
        }

        @Override
        protected SpeedUnit createNewUnitInstance(SpeedUnit unit_TYPE) {
            return new SpeedUnitImpl(unit_TYPE);
        }
    }

    public static class AngularAccelerationUnitEditor extends AbstractUnitEditor<AngularAccelerationUnit> {
        @Override
        protected BufferedImage createUnitImage(AngularAccelerationUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<AngularAccelerationUnit> getAllUnits() {
            return Arrays.asList(
                    AngularAccelerationUnit.DEGREE_PER_SQUARE_MINUTE,
                    AngularAccelerationUnit.DEGREE_PER_SQUARE_SECOND,
                    AngularAccelerationUnit.RADIANS_PER_SQUARE_MINUTE,
                    AngularAccelerationUnit.RADIANS_PER_SQUARE_SECOND,
                    AngularAccelerationUnit.ROUNDS_PER_SQUARE_MINUTE,
                    AngularAccelerationUnit.ROUNDS_PER_SQUARE_SECOND);
        }

        @Override
        protected AngularAccelerationUnit createNewUnitInstance(AngularAccelerationUnit value) {
            return new AngularAccelerationUnitImpl(value);
        }
    }

    public static class AccelerationUnitEditor extends AbstractUnitEditor<AccelerationUnit> {
        @Override
        protected BufferedImage createUnitImage(AccelerationUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<AccelerationUnit> getAllUnits() {
            return Arrays.asList(
                    AccelerationUnit.KM_PER_SQUARE_HOUR,
                    AccelerationUnit.KM_PER_SQUARE_MINUTE,
                    AccelerationUnit.KM_PER_SQUARE_SECOND,
                    AccelerationUnit.METER_PER_SQUARE_HOUR,
                    AccelerationUnit.METER_PER_SQUARE_MINUTE,
                    AccelerationUnit.METER_PER_SQUARE_SECOND,
                    AccelerationUnit.NAUTICALMILES_PER_SQUARE_HOUR,
                    AccelerationUnit.NAUTICALMILES_PER_SQUARE_MINUTE,
                    AccelerationUnit.NAUTICALMILES_PER_SQUARE_SECOND
            );
        }

        @Override
        protected AccelerationUnit createNewUnitInstance(AccelerationUnit value) {
            return new AccelerationUnitImpl(value);
        }
    }

    public static class DistanceUnitEditor extends AbstractUnitEditor<DistanceUnit> {
        @Override
        protected BufferedImage createUnitImage(DistanceUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<DistanceUnit> getAllUnits() {
            return Arrays.asList(
                    DistanceUnit.values()
            );
        }

        @Override
        protected DistanceUnit createNewUnitInstance(DistanceUnit value) {
            return value; //it's an enum
        }
    }

    public static class TemperatureUnitEditor extends AbstractUnitEditor<TemperatureUnit> {
        @Override
        protected BufferedImage createUnitImage(TemperatureUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<TemperatureUnit> getAllUnits() {
            return Arrays.asList(
                    TemperatureUnit.values()
            );
        }

        @Override
        protected TemperatureUnit createNewUnitInstance(TemperatureUnit value) {
            return value; //it's an enum
        }
    }

    public static class MassUnitEditor extends AbstractUnitEditor<MassUnit> {
        @Override
        protected BufferedImage createUnitImage(MassUnit value) {
            return createImage(UnitSymbolUtil.getSymbol(value));
        }

        @Override
        protected List<MassUnit> getAllUnits() {
            return Arrays.asList(
                    MassUnit.values()
            );
        }

        @Override
        protected MassUnit createNewUnitInstance(MassUnit value) {
            return value; //it's an enum
        }
    }
}
