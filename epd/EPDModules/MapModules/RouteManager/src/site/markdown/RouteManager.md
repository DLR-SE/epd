# RouteManager



## Introduction

The RouteEditor is used to display and modify registered routes. It adds a new layer which draws routes and
provides tools to edit these. RouteEditor also provides a view which shows all available route for the own ship
and ais target. For each route a properties dialog can be opened to edit meta information (name, start, end)
and its way points.

## Setup

Just add this plugin to a Product - Product.xml and start your product. Add a new Route by creating or importing
it. By settings a route visible it will be shown on the map view.

## Use-Case

The use cases for the RouteManager are displaying and editing route information.

## Extending-Features

### RouteView
Selected routes are broadcasted using the SelectionManager, using the key RouteManagerBasic.CTX_ROUTE_SELECTION. If
users select a route in the view, the SelectionManager is called and buttons in the toolbar are enabled. New buttons
can be added by using the menu id RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR.

### Tools
Route tools which lets users create and edit routes can be found in the package de.emir.epd.routemanager.map. This
contains the EditRouteTool and the NewRouteTool

## How-To

![Route Manager](images/RouteManager.PNG)


As seen above, the route manager consists of a map layer and a route view. The layer is used to draw
routes for an ownship and AIS targets. Furthermore, it provides features to create and edit routes.

![Toolbar Menus](images/ToolBarMenus.PNG)

By using the first button, a new route can be drawn on the map. Each click on a map adds a new way point.
Clicking on another tool end the route creation. Displayed routes can be modified by using the second
button. This activates the edit mode and way points can be moved. Beside editing waypoint location, it's
possible to add or delete waypoint. Adding waypoints can be done by double-clicking on a line. This adds
a waypoint at the mouse position. Deleting waypoint is done by pressing a waypoint longer than one second.

The second part of the RouteEditor is the Routes-View. It can be opened by selecting Layout -> Open Views...
-> Routes. This will open the following view:

![Route Manager View](images/RouteManagerView.PNG)

This view will display a list of routes for assigned to a specific group. Groups can be seen as owners
of the route. There are groups for AIS targets, ownship and unasigned. This tabled also shows a description
and the option to display a route on the map.

![Toolbox](images/Toolbox.PNG)

The toolbar on the right side can be used to import/export, delete and modify selected routes. Import and
Export opens a file browser, in which a format and path can be chosen. The invert button, reverts a selected
route by changing the direction (start = end, end = start).
Beside editing a route on a map, it can also be modified by double-clicking on a route or by clicking the
Properties button.

![Route Editor](images/RouteEditor.PNG)

The route properties dialog consists of various properties and a table containing details about each way point.


