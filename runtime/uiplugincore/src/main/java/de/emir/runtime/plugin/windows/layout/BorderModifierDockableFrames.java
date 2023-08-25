package de.emir.runtime.plugin.windows.layout;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.StackDockStation;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.intern.CommonDockable;
import bibliothek.gui.dock.displayer.DisplayerDockBorder;
import bibliothek.gui.dock.station.stack.StackDockComponentBorder;
import bibliothek.gui.dock.themes.border.BorderModifier;
import bibliothek.gui.dock.util.UIBridge;
import bibliothek.gui.dock.util.UIValue;

public class BorderModifierDockableFrames {

    public static void main(final CControl control) {

        DockController controller = control.getController();

        controller.getThemeManager().setBorderModifier("dock.border.displayer.basic.base", new BorderModifier() {
            public Border modify(Border border) {
                return BorderFactory.createEmptyBorder();
            }
        });
        /*
         * We may also set a modifier directly using a key. The modifier is then applied to all places which use the
         * key.
         */
        controller.getThemeManager().setBorderModifier("dock.border.displayer.basic.content", new BorderModifier() {
            public Border modify(Border border) {
//                return BorderFactory.createEtchedBorder(UIManager.getColor("InternalFrame.borderHighlight"), UIManager.getColor("InternalFrame.borderShadow"));
            	return BorderFactory.createLineBorder(UIManager.getColor("controlDkShadow"));
            }
        });

        // controller.getColors().put(Priority.CLIENT, "stack.tab.background", Color.red);

        // stack.border
        //
        // controller.getTheme().get

        controller.getThemeManager().setBorderModifierBridge(DisplayerDockBorder.KIND, new NoBorderModifierBridge());
        controller.getThemeManager().setBorderModifierBridge(StackDockComponentBorder.KIND,
                new NoBorderModifierBridge());

        // if(controller.getTheme() instanceof CSmoothTheme) {
        //
        // CSmoothTheme theme = (CSmoothTheme) controller.getTheme();
        //
        // theme.putColorBridgeFactory( TabColor.KIND_TAB_COLOR, new ColorBridgeFactory(){
        // public ColorBridge create( ColorManager manager ) {
        // BasicTabTransmitter transmitter = new BasicTabTransmitter( manager );
        //
        // transmitter.setControl(control);
        // return transmitter;
        // }
        // });
        //
        // }

    }

    public static class NoBorderModifierBridge implements UIBridge<BorderModifier, UIValue<BorderModifier>> {

        @Override
        public void add(String id, UIValue<BorderModifier> uiValue) {
            // TODO

        }

        @Override
        public void remove(String id, UIValue<BorderModifier> uiValue) {
            // TODO

        }

        @Override
        public void set(String id, BorderModifier value, UIValue<BorderModifier> uiValue) {
            /*
             * This is a safe cast, because of the arguments we used when registering this brige.
             */
            DisplayerDockBorder displayerDockBorder = (DisplayerDockBorder) uiValue;

            /*
             * "uiValue" represents the request for a property, "value" is the default value, "id" is the name of
             * the property. "uiValue" tells us for which Dockable the property is requested...
             */
            Dockable dockable = displayerDockBorder.getDisplayer().getDockable();

            if (dockable.getComponent().isFocusOwner()) {

                uiValue.set(new FocusedBorderModifier());
                return;
            }

            if (dockable instanceof StackDockStation) {
                value = new NoBorderModifier();
            } else if (dockable instanceof CommonDockable) {
                CDockable cdockable = ((CommonDockable) dockable).getDockable();

                if (cdockable.asStation() instanceof CWorkingArea) {
                    /*
                     * ... and if the property is requested for a CWorkingArea, we set the "NoBorderModifier"
                     */
                    value = new NoBorderModifier();
                }
            }
            uiValue.set(value);
        }
    }

    /* The "NoBorderModifier" replaces any border with "null" */
    private static class NoBorderModifier implements BorderModifier {
        @Override
        public Border modify(Border border) {
            return null;
        }
    }

    private static class FocusedBorderModifier implements BorderModifier {
        @Override
        public Border modify(Border border) {
//            return BorderFactory.createEtchedBorder(UIManager.getColor("Button.select"), UIManager.getColor("InternalFrame.borderShadow"));
        	return BorderFactory.createLineBorder(Color.RED, 2);
        }
    }
}
