## Map View

The map view is one of the central components of the EPD. It is capable of displaying AIS data, routes, sea charts
and many more. The exact functionality depends on the plugin configurations, thus this document shows functions that 
are also part of normal ECDIS.

![Route Example](images/routemap.png)

By default, OSM (Open Street Map) is shown on the map. As in every interactive map display the map can be the map can 
be moved or zoomed. In the lower right corner the scale for is shown. In the lower left corner the copyright of the map 
material is visualized. Above the view there is a toolbar on where plugins register their tools (e.g. create routes, 
activate navigation tools.).

![Route Example](images/map_layers.png)

A map typically consists of multiple layers. Each plugin may add additional layers to provide further information
for users. An example is the AIS layer which renders information about vessels received by AIS. Another example
is the usage of OpenSeaMap which adds maritime Information to already existing OpenStreetMaps. Layers can be 
set visible or invisible using the `Map Layers` view. It displays all possible layers and adds additional settings. 

### Changing the map tile source

By default, OSM is configured as the tile source. At the time of writing this can be changed in the settings to 
`Google Maps`, `WMS`, `Open Street Map` or `Google Earth`. To change this open `File -> Settings -> Map View` and
select the desired tile source. It is accepted and change once `Finish` is clicked. In this settings-page, 
hard-drive-caching can also be enabled. This will cache tiles on the disc to reduce network traffic and improve
loading times. It may also be used for situations in which no internet connection is available. Note that `OpenSeaMap`
is not a tile source, it is just an overlay for other sources. Thus, `OpenSeaMap` can also be rendered on `Google Maps`.
