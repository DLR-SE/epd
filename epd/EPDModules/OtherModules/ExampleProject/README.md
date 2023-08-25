# Example Project

This project serves as an initial entry point into development for the EPD. It explains central 
classes and concepts that are usually present in every EPD plugin. In the following sections, 
the folder and class structure will first be defined in more detail. This serves for the overview
and for the uniform structuring of EPD Plugins. Thereupon follow explanations for features of the
Example project. Finally further references are given to other tutorials and resources.

**Feel free to contribute to this project to help and show other developers how to develop for
the EPD.**

## Functionalities

This project explains the core functionalities for the EPD including map extensions.

**View**: The ExampleView just shows the basic class structure and creates a view with a label to display
text.

**SettingsPage**: The ExampleSettingsPage registers basic widgets for displaying information and changing
options. For each option the last state is saved so that changes can be undone at any time. Here "isDirty"
is used to check if a change was made by the user and "finish" is called if there is a change, and it should
be saved.

**Map**: First, the ExampleLayer draws a circle on the map together with a message. This information is only
visible if the layer is activated by the user. If a user moves the mouse over the circle, the position
is sent to the SelectionManager, so that the coordinate can be used elsewhere. An example for this is
the ExampleTool. When activated, this tool allows the coordinate to be moved.The ExampleCommand is used to
center the map on the coordinate so that the coordinate can be found at any time.

## Folder Structure

This project (and most other projects) follow this folder structure:
- basic
    - Contains <ProjectName>Basic.java file which contains all IDs for registered ExtensionPoints,
    PropertyContext, Views, ... This is the main source for other developers which want to extend
    a plugin.
- cmd
  - Contains commands derived from AbstractCommands. These are used to execute logic initiated by 
  the user and bound to key bindings or menu entries. 
- map
  - Groups map specific classes like layers and tools. This folder is obviously only needed if 
  the plugin extends map functionalities. 
- settings
  - Contains classes for saving and loading settings. Setting Pages, which are shown in the settings
  menu, are derived from AbstractSettingsPage. Classes used for the settings itself may also lie in 
  this folder.
- view
  - Contains view classes extended from AbstractView and everything related to it. Classes used 
  for the view itself may also lie in this folder.

The main plugin class (e.g. ExamplePlugin.java) lies in the root folder and serves as an entry point
for the plugin. This folder structure is adapted by all/most plugins of the epd and eases reading and
extending plugins. 

## Resources

- [Getting Started Guide](../../../doc/GettingStarted.md)
- [How To Document](../../../doc/HowToDocument.md)
- [How To Build](../../../README.md)
- [Open Digital Incubator Initiative](https://digitalincubator.maritimeconnectivity.net/)