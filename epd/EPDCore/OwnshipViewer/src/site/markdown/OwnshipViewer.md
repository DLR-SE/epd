# Ownship Information Views



## Introduction

This plugin displays information about the own ship and provides features for modifying its values.
It contains a map which displays the own ship and contains the VesselEditor for changing the
ship's geometry, as well as some safety configuration.

## Setup

Just add this plugin to a Product - Product.xml and start your product.

## Use-Case

The primary use case for this plugin is to display and configure the own ship. This is done
by providing a de.emir.epd.ownship.OwnshipLayer which is added to the MapView. Configurations
to the own ship is done by using the de.emir.epd.ownship.settings.OwnshipViewerSettingsPage which
contains the VesselEditor (of VesselEditorPlugin) to modify the ships geometry and its values.

## How-To

Once you loaded the OwnShipPlugin into your application, you can access the ownship view by selecting
Layout -> Open View... -> Ownship Info. This will open a new view which looks like this:

![Own Ship Info](images/OwnshipInfo.PNG)

This view shows the current status of the ownship, displaying the mmsi, position, sog (speed over ground)
and cog (course over ground). This will update according to the current sensor information.

The ownship plugin also contains a settings page, which can be used to modify the ownship information.

![Own Ship Settings](images/OwnshipSettings.PNG)


As seen above, the first part of the settings page contains information about the sources. AIS Target
by MMSI is used to simulate an ownship. Just insert a mmsi of an AIS target and the application
will use the target as ownship. Ownship NMEA Sentences on the other side makes use of a nmea
reader, which reads sentences and updates the vessel.

Last part of the settings page is the vessel editor. On opened it looks like this:

![Own Ship Settings Editir](images/OwnshipSettingsEditor.PNG)


This editor makes use of the VesselEditorPlugin and its geometry editor. The top toolbar shows
the geometry views (top, side, front). Based on the selection, the editor shows a geometry
for that view. On the left side are general information about the ownship.
For more information about the editor, just read the VesselEditorPlugin documentation page.
