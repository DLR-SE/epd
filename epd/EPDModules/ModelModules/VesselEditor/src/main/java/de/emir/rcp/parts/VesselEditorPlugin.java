package de.emir.rcp.parts;

import de.emir.rcp.UICorePlugin;
import de.emir.rcp.commands.ep.CommandExtensionPoint;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.menu.ep.IMenuContribution;
import de.emir.rcp.menu.ep.MenuExtensionPoint;
import de.emir.rcp.parts.vesseleditor.cmds.CenterShapeCommand;
import de.emir.rcp.parts.vesseleditor.cmds.ChangeViewCommand;
import de.emir.rcp.parts.vesseleditor.cmds.toggle.ShowAxisLinesCommand;
import de.emir.rcp.parts.vesseleditor.cmds.toggle.ShowGridLinesCommand;
import de.emir.rcp.parts.vesseleditor.cmds.toggle.ToggleShapeEditCommand;
import de.emir.rcp.parts.vesseleditor.provider.equip.EquipmentProviderExtensionPoint;
import de.emir.rcp.parts.vesseleditor.provider.equip.impl.AISEQProvider;
import de.emir.rcp.parts.vesseleditor.provider.equip.impl.EngineEQProvider;
import de.emir.rcp.parts.vesseleditor.provider.equip.impl.RudderEQProvider;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class VesselEditorPlugin extends AbstractUIPlugin {

    @Override
    public void registerExtensionPoints() {
        ExtensionPointManager.registerExtensionPoint(VesselEditorBasic.EQUIPMENT_EXTENSION_POINT_ID, new EquipmentProviderExtensionPoint());
    }

    @Override
    public void addExtensions() {
        CommandExtensionPoint cmdEP = ExtensionPointManager.getExtensionPoint(CommandExtensionPoint.class);
        ICommandDescriptor topCMD = cmdEP.command(VesselEditorBasic.CHANGE_VIEW_TOP_ID, "TOP", new ChangeViewCommand(View.TOP));
        ICommandDescriptor sideCMD = cmdEP.command(VesselEditorBasic.CHANGE_VIEW_SIDE_ID, "SIDE", new ChangeViewCommand(View.SIDE));
        ICommandDescriptor frontCMD = cmdEP.command(VesselEditorBasic.CHANGE_VIEW_FRONT_ID, "FRONT", new ChangeViewCommand(View.FRONT));
        ICommandDescriptor toggleShapeEDIT = cmdEP.command(VesselEditorBasic.TOOGLE_SHAPE_EDIT, "Edit Shape", new ToggleShapeEditCommand());
        ICommandDescriptor centerShapeEDIT = cmdEP.command(VesselEditorBasic.CENTER_ON_SHAPE, "Center Shape", new CenterShapeCommand());
        ICommandDescriptor showGridLinesCMD = cmdEP.command(VesselEditorBasic.SHOW_GRID_LINES, "Show Grid", new ShowGridLinesCommand());
        ICommandDescriptor showAxisLinesCMD = cmdEP.command(VesselEditorBasic.SHOW_AXIS_LINES, "Show Axis", new ShowAxisLinesCommand());

        ResourceManager localResourceManager = ResourceManager.get(VesselEditorPlugin.class);
        ResourceManager globalResourceManager = ResourceManager.get(UICorePlugin.class);

        MenuExtensionPoint menuEP = ExtensionPointManager.getExtensionPoint(MenuExtensionPoint.class);
        IMenuContribution toolbarEP = menuEP.menuContribution(VesselEditorBasic.TOOLBAR_ID);
        toolbarEP.separator("viewToolsSeparator");
        toolbarEP.menuItem("top", topCMD)
                .label("TOP")
                .after("viewToolsSeparator");
        toolbarEP.menuItem("side", sideCMD)
                .label("SIDE")
                .after("viewToolsSeparator");
        toolbarEP.menuItem("front", frontCMD)
                .label("FRONT")
                .after("viewToolsSeparator");

        toolbarEP.separator("vesselEditorToolsSeparator");
        toolbarEP.menuItem("toggle shape edit", toggleShapeEDIT)
                .label("Edit Shape")
                .after("vesselEditorToolsSeparator")
                .icon("/icons/emiricons/32/edit.png", globalResourceManager);

        toolbarEP.menuItem("center shape", centerShapeEDIT)
                .label("Center Shape")
                .after("vesselEditorToolsSeparator")
                .icon("/icons/emiricons/32/filter_center_focus.png", globalResourceManager);

        toolbarEP.menuItem("show grid", showGridLinesCMD)
                .label("Show Grid")
                .after("vesselEditorToolsSeparator")
                .icon("/icons/emiricons/32/grid.png", globalResourceManager);

        toolbarEP.menuItem("show axis", showAxisLinesCMD)
                .label("Show Axis")
                .after("vesselEditorToolsSeparator")
                .icon("/icons/emiricons/32/augmented_reality.png", globalResourceManager);

        EquipmentProviderExtensionPoint epep = ExtensionPointManager.getExtensionPoint(EquipmentProviderExtensionPoint.class);
        epep.registerEquipmentProvider(VesselEditorBasic.EQUIPMENT_DYNAMIC_GROUP, new EngineEQProvider());
        epep.registerEquipmentProvider(VesselEditorBasic.EQUIPMENT_DYNAMIC_GROUP, new RudderEQProvider());
        epep.registerEquipmentProvider(VesselEditorBasic.EQUIPMENT_DYNAMIC_GROUP, new AISEQProvider());
    }

}
