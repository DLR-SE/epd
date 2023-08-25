package de.emir.rcp.parts;

import de.emir.rcp.ids.Basic;

public class VesselEditorBasic {
    public static final String EQUIPMENT_EXTENSION_POINT_ID = "de.emir.rcp.parts.vesseleditor.provider.equip.EquipmentProviderExtensionPoint";

    //toolbar
    public static final String TOOLBAR_ID = Basic.TOOLBAR_IDENTIFIER + "GeometryEditorPanel_Toolbar";

    //view ids
    public static final String CHANGE_VIEW_BASE_ID = "de.emir.rcp.parts.ve.menu.cmds.ChangeViewCommand";
    public static final String CHANGE_VIEW_TOP_ID = CHANGE_VIEW_BASE_ID + ".top";
    public static final String CHANGE_VIEW_SIDE_ID = CHANGE_VIEW_BASE_ID + ".side";
    public static final String CHANGE_VIEW_FRONT_ID = CHANGE_VIEW_BASE_ID + ".front";

    //equipment Groups
    public static final String EQUIPMENT_DYNAMIC_GROUP = "Dynamic System";

    //selection context
    public static final String CTX_VIEW_SELECTION_ID = "VIEWSelectionManagerID";
    public static final String CTX_OBJECT_SURFACE_SELECTION_ID = "ObjectSurfaceSelectionManagerID";
    public static final String CTX_DRAG_SELECTION_ID = "DragSelectionID";
    public static final String CTX_EQUIPMENT_SELECTION_ID = "EquipmentSelectionID";

    //property context
    public static final String PROP_VESSEL_EDITOR = "VesselEditorProperties";

    public static final String TOOGLE_SHAPE_EDIT = "ToogleShapeEdit";
    public static final String CENTER_ON_SHAPE = "CenterOnShape";
    public static final String ZOOM_TO_BEST_FIT = "ZOOM_TO_BEST_FIT";
    public static final String SHOW_GRID_LINES = "ShowGridLines";
    public static final String SHOW_AXIS_LINES = "ShowAxisLines";

    public static final String PROP_TOOGLE_SHAPE_EDIT = "PropToogleShapeEdit";
    public static final String PROP_CENTER_ON_SHAPE = "PropCenterOnShape";
    public static final String PROP_ZOOM_TO_BEST_FIT = "PropZoomToBestFit";
    public static final String PROP_SHOW_GRID_LINES = "PropShowGridLines";
    public static final String PROP_SHOW_AXIS_LINES = "PropShowAxisLines";

}
