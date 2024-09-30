## Build Instructions

The EPD is entirely written in Java 21 and by using Maven. By default, the code is only compatible with Java 21 and 
also requires a JRE21 environment for execution. For other Java versions take a look at the chapter 
[Other Java Versions](#other-java-versions), which may include version specific instructions for building and 
running. The user interface of the EPD is built on top of swing and a fork of 
[jxMapViewer](https://github.com/sbehrensen/jxmapviewer2).

### Build a development version

The following section provides step-by-step instructions to build a development version of the EPD:
1. Build all the EPD maven modules by running `mvn clean install` in the root directory of this project.

2. Decide, which files you want to include as a dependency of the build (see
[Plug-In System](HowToUsePluginManager.md)). This can also be done after starting the EPD with the first
start wizard.

3. Start the [EPD Community Version](../product/src/main/java/de/emaritime/epd/product/EPDCommunity.java). If the
home folder (`~/EPDCommunity`) does not exist, a first start wizard will welcome you. Otherwise, the EPD will just
open.

The standard EPD configuration will present you with the default plugins of this repository (map, routes, ais, ...). 

### Create a standalone executable version

The following section provides step-by-step instructions to build an executable version of the EPD:
1. Build the Plug-In Manager by running `mvn clean install` in the Plug-In Manager project.

2. Build all the EPD maven modules by running `mvn clean install` in the root directory of the project.

3. Decide, which files you want to include as a dependency of the build (see
   [Plug-In System](HowToUsePluginManager.md)).

4. Create a new module (or modify some existing modules) in `Products` and include all the dependencies from
   step 4 in the pom.xml of the project.

5. Create Product.xml file, that also includes a list of the Plug-Ins. See
   [EPD Product XML](../product/src/main/resources/Product.xml) for an example.

6. Build the new submodule with `mvn clean install`.

7. Start the Plug-In Manager from `~/PluginManagerProduct/PluginManager/PluginManagerProduct.jar`

8. Select your `Product.xml` from step 6.

9. Configure your local Repository to your .m2 folder (typically `~/.m2/repository/`) and click on the orange "refresh"
   button.

10. Click on "Export..." and set the following properties:
    * Entry Point: Select the jar from your build in step 7 (can be found in the target folder of the project).
    * Base POM: Select the pom.xml from step 5.
    * Output Folder: The directory to create the executable/binaries.

11. Done. You can now start the jar in the output folder.

### Other Java Versions

This section contains java version specific instructions for other Java Versions as Java 21. 
Currently no other Java versions have been tested with the EPD version 4.1.0.


