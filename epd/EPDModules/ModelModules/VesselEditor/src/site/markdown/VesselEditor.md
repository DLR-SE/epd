# VesselEditor

## Introduction
The VesselEditor provides a graphical editor for a vessel and its geometry. Additionally, it provides an editor
for geometries itself, heading and speed.

## Setup
Load this plugin into your product and open a file with the emod editor. Once opened
select the vessel or a geometry in the emod editor tree. This will open the vessel
editor. A geometry for is vessel is provided by an ObjectSurfaceInformation Object,
if none of it is found in the vessel object, a view will open where a SurfaceInformation
can be chosen.

![Chose Surface Information](images/CreateSurfaceInfo.PNG)

## Use-Case
The primary use case is to provide a graphical editor for vessels. Thereby it's possible to
change a vessels attributes and its underlying geometry. Additionally, it provides functions
to change its equipment (engine, sensors) and safety characteristics (for example under keel
clearance).
Secondary use case is providing additional editors for geometries, heading and speed.

## Extending-Features

This guide is split into multiple parts, Adding new editors, using drag functionality, transferable objects
extending vessel editor.

### Adding Editors

Starting with adding editors, the vessel editor already has a few editors. They are found in the
de.emir.rcp.parts.vesseleditor.view.parts package. These are extended from a JPanel and provide the basic
view, except for the editor view itself. So if you want to add a new editor or change the existing ones
take a look at de.emir.rcp.parts.vesseleditor.view.vessel.VesselEditorPanel,
de.emir.rcp.parts.vesseleditor.view.geometry.GeometryPanel and
de.emir.rcp.parts.vesseleditor.view.geometry.AbstractGeometryPanel. The AbstractGeometryPanel provides basic
editor functions like zoom and pan, point conversions and editor overlays (grids, axis). It also contains
the AffineTransforms for updating the view. But this class actually doesn't draw the geometry. This is done
in the GeometryPanel and VesselEditorPanel. The GeometryPanel draws a given geometry and its overlays (grids, axis).
Additionally, it provides a DragHandler which lets user drag certain elements. The VesselEditorPanel on the other side
is extended from the GeometryPanel and adds additional layers for vessel specific characteristics like waterlines,
personal space and under keel clearance. This panel also lets users add equipments and change its location.

### Drag Functionality

The drag function for this editor is some kind of special. The editors are in 2D, but most elements can be dragged
in 3D using different views. As already mentioned the de.emir.rcp.parts.vesseleditor.view.geometry.GeometryPanel contains
a DragHandler. It uses a DrawWrapper and DragProperties to specify how Shapes can be dragged. For Example Equipments
can be dragged in 3D, so the given parameters are Rectangle, Coordinate (position of the object) and DragProperties
with transformX (boolean),transformY (boolean), transformZ (boolean) as true. On the other side things like
a line for under keel clearance should only be drawn and dragged when the side view or front view is active. This is
done by giving the parameters Line, Coordinate (position of the object) and DragProperties with transformX (boolean),
transformZ (boolean) as false and transformY (boolean) as true. The DragWrapper then decides how the coordinates
should be changed according to DragProperties.

### Transferable Objects

As already mentioned in the user guide, equipments and reference points are draggable and can be placed on the
geometry. Adding more transferable object can be done by extending
de.emir.rcp.parts.vesseleditor.provider.ITransferableProvider and
de.emir.rcp.parts.vesseleditor.view.panels.transferable.AbstractTransferablePanel. The first class provides an
interface for placing objects on other objects. When a Transferable is placed by the user, place() is called. Its called
by de.emir.rcp.parts.vesseleditor.view.panels.transferable.AbstractTransferablePanel, which handles being draggable and
being placed. Being draggable needs to be implemented in a AbstractTransferablePanel implementation where the mouse
listener and transfer handler need to be set. An example can be found in
de.emir.rcp.parts.vesseleditor.view.panels.transferable.EquipmentPanel.

### Extending VesselEditor

The vessel editor is split into de.emir.rcp.parts.vesseleditor.view.parts.VesselEditorPart,
de.emir.rcp.parts.vesseleditor.view.vessel.VesselEditorPanel and various Panels found at
de.emir.rcp.parts.vesseleditor.view.panels. The VesselEditorPart is the overall view. It consists of a toolbar,
a sidebar and a main editor panel which is the VesselEditorPanel. The toolbar can be extended using
VesselEditorBasic.TOOLBAR_ID. The side panel is defined in the VesselEditorPart under createSidePanel(). The main
editor panel is added by overwriting the method createEditorPanel() from
de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart.

Note: Equipments are clickable, which opens an editor for the clicked equipment. This is done by the
EquipmentHandler in de.emir.rcp.parts.vesseleditor.view.vessel.VesselEditorPanel. It uses the drag mechanism
of the DragHandler to listen to click events.

## How-To
Once you opened the vessel editor it should look just like this:

![Vessel Editor](images/VesselEditorComplete.PNG)

The editor has three main parts. The top side is a toolbar, which contains view settings, the sidebar contains
object specific settings and the center panel contains the geometry editor. Starting with the toolbar, it lets
you choose views for a geometry (Top, Side, Front).

![Views](images/Views.PNG)

Selecting the side view, the editor will load a side geometry for the vessel, and it should look like this:

![Vessel side view](images/SideView.PNG)

Note that loadings views is based on the ObjectSurface implementation. For example using a
MultiViewObjectSurfaceInformation will contain all views (Top, Side, Front). Using a simple
ObjectSurfaceInformation will contain just the Top view.
There are also some predefined geometries to choose from. Using the WKTPanel, you can choose from a number
of geometries to start with, which set the base look of the vessel.

![WKT Settings](images/WKTPanel.PNG)

By now there are geometries for Orca, Oil Tanker, Apollo and Simple (just a basic rectangle). Once loaded, the editor
will save it in the object, and you can edit the points. Moving the points will change its position in a 3D
orientation. This means that changing a side view point will affect the other views. Adding points can be archived
by double-clicking on a line, which adds a point just between those lines. Removing points is done by right-clicking
on a point. This will open a dialog which asks if the point should really be removed.

The next part if this tutorial are the settings panels. by now there are 4 settings panels, general, size, hull and
safety.

![General Settings](images/GeneralPanel.PNG)

Starting with the general panel, it contains the basic attributes of a vessel. This lets you change the name,
mmsi, imo and type of the ship. Furthermore, it provides pose settings.
The next panel provides settings for the size of a vessel. This lets you change width, height and length of a ship,
which changes the underlying geometry.

![Size Settings](images/SizePanel.PNG)

Next up are the hull settings, which provide additional settings for the vessel hull.

![Hull Settings](images/HullPanel.PNG)

These settings aren't shown in the editor panel, but these are used to calculate other values. For example
setting the draft is important to calculate the under keel clearance which is set in the safety characteristics.
The safety characteristic panel is used to set basic safety settings like under keel clearance and personal
space.

![Safety Settings](images/SafetyPanel.PNG)

Under keel clearance is used to set a comfortable value for space between the ground and the keel. This value is
marked by a red line which is calculated using draft and under keel clearance. The personal space is marked by
a green rectangle and is used as a space where other ships shouldn't be in.

The last chapter are equipment settings. For now there are two equipments, engine and rudder.

![Equipment Settings](images/EquipmentPanel.PNG)

These can be placed on the vessel by dragging them and setting their position. Equipments can be positioned
in 3D by using two views like Top and Side. Once placed it can look like this:

![Equipment View](images/EquipmentView.PNG)

Clicking on equipment results in opening an editor for it. This lets you change values for the equipment like
engine power.

