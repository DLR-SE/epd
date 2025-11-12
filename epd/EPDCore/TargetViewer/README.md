# TargetViewer Plugin

This is the TargetViewer plugin. It provides a visualization of Target or TrackedTarget objects
which are located in the model. A TrackedTarget or Target generally refers to an object which
is detected by a detection or tracking system such as RADAR. This plugin provides means to display
these targets as a separate map layer trough the TargetLayer and display target information through the
TargetView. The display representation adheres to SN.1/Circ.243.

&nbsp;&nbsp;&nbsp;&nbsp;Maintainers: Arne Bokern

&nbsp;&nbsp;&nbsp;&nbsp;TRL: 1
&nbsp;&nbsp;&nbsp;&nbsp; [AC](https://wiki.dlr.de/display/SoftwareEngineering/Guidelines): 0

&nbsp;&nbsp;&nbsp;&nbsp;Compatible OS: Windows, Linux

## Getting Started

### Prerequisites

In order to install the plugin, the parent eMir-OpenSource project needs to be installed. This can be done by executing
following command:

    mvn clean install

### Installation

The TargetViewer plugin is either installed when executing mvn clean on the parent eMir-OpenSource or can be installed
manually
by executing the following command in this directory:

    mvn clean install

## Adding the Plugin

In order to add the plugin to the product, the DevelopmentTools plugin needs to be installed.
Afterwards, the PluginManager can be started by clicking DevTools > Configure Plugins in the EPD.
There, the pom.xml of the TargetViewer can be added to the list of plugins. After adding, click Apply & Exit and restart
the EPD. Afterwards, the TargetViewer should be enabled.

