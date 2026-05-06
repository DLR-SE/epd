# RouteManagerPlugin
This plugin handles provides different tools to create, delete, and edit routes. It also provides different graphics for the map to display routes, corridors, etc.

## RoutesListView
This view shows all available routes in the current environment in a tree table. It provides functions to **copy** and **paste** routes as XML serialized object strings (using the clipboard), as well as functions to **duplicate**, **delete**, **invert** and **edit** routes. Additionally, one can **import** and **export** routes from/to files. Furthermore, it is possible to **"Zoom to"** a selected route on the map and change the visibility of specific routes on the map.

Usage:

* **Button "Copy Rote XML":** Select a route in tree view of RouteManger and click the new button. Similar to "Copy" --> "Copy XML" in eMOD editor, the selected route object is serialized as a XML string and copied to the clipboard.
* **Button "Import from Clipboard":** If a valid Route object is in the users clipboard, a wizard opens. The user can select the import destination for the route, e.g. "unasigned" or a vessel. Additionally, the content of the clipboard is displayed. If no valid Route can be deserialized from the clipboard content, an error messages is logged and the wizard is not opened.