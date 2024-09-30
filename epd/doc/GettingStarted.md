# Getting Started

We highly recommend to develop any extension with new features as a Plug-In. For getting started, you can find a 
minimal example project at **EPDModules/ExampleProject**.

## Create a new EPD plugin

To enable the EPD to be scaled to a wide variety of use cases, it was developed on a plug-in basis. 
New functionalities can thus be easily loaded as plugins. The EPD is based on a specially developed 
framework called `eMIR Plugin Framework` (epf). It uses Maven as backend, which handles the dependency 
management, as well as the loading of plugins. This means that all Java projects can be loaded that are
based on Maven. If the project has a corresponding EPF plugin class, a plugin entry point can also be 
defined.

The following are basic steps for developing a new plugin. First, a new Maven project is created. In 
order for the new project to be loaded from the EPD and interact with the EPD, the dependency on the 
UIPluginCore must first be set up and a reference to the "Main" class must be specified. The reference
to the Main class is done through a Maven property named `eMIR-Plugin-Class`. This describes the path to 
the Main class (package name + class name). This results in the following basic entries in the Maven pom:

```xml
<properties>
    <!--Main Plugin Class-->
    <eMIR-Plugin-Class>de.emir.epd.example.ExamplePlugin</eMIR-Plugin-Class>
</properties>

<dependencies>
    <dependency>
        <!--Contains rcp core, every plugin has this dependency-->
        <groupId>de.dlr-se.emir.runtime</groupId>
        <artifactId>uiplugincore</artifactId>
        <version>4.1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

The main class of a plugin extends the AbstractUIPlugin class. Lifecycle methods of the EPD are 
defined in this class. The following code shows a minimal implementation of a Main class and the 
order of method execution.

```java
package de.emir.epd.example;

import de.emir.runtime.plugin.AbstractUIPlugin;

import javax.swing.*;

public class ExamplePlugin extends AbstractUIPlugin {

    @Override
    public void initializePlugin() {}

    @Override
    public void registerExtensionPoints() {}

    @Override
    public void addExtensions() {}

    @Override
    public void postAddExtensions() {}

    @Override
    public void preWindowOpen() {}

    @Override
    public void onWindowCreate(JFrame window) {}

    @Override
    public void postWindowOpen() {}

    @Override
    public void preApplicationClose() {}

    @Override
    public void postApplicationClose() {}
}
```

The lifecycle of a plugin is based on the order of the code section. First, the plugin is initialized. 
This is followed by the registration of extension points, they enable the extension of the EPD. With
`addExtensions` functionalities are added to the EPD (e.g. views by the ViewExtensionPoint).
`postAddExtensions` is called as soon as `addExtensions` ends. It is followed by methods that are called 
when a UI window is created. As soon as the program is closed, `pre/-postApplicationClose` is called. They 
offer e.g. as a possibility to save settings.

## Adding EPD Extensions

ExtensionPoints have been defined to extend the EPD. These are central classes with which allow, for example, 
menu items, views or settings pages to be defined. In addition, own ExtensionPoints can be implemented to give 
other developers the possibility to extend a plugin (e.g. a MapView). They can be registered and obtained via the
`ExtensionPointManager` class. First we concentrate on the most important ExtensionPoints, which are used by 
almost every plugin.

| Name                       | Description                                                                                                |
|----------------------------|------------------------------------------------------------------------------------------------------------|
| ViewExtensionPoint         | Registration of views, i.e. windows within the EPD for displaying information.                             |
| SettingsPageExtensionPoint | Registration of settings pages under the menu item "File -> Settings".                                     |
| CommandExtensionPoint      | Definition of commands or actions for the execution of an activity. For example, the activation of a tool. |
| MenuExtensionPoint         | Enables the creation of new menus and menu items in the main menu bar of the EPD.                          |
| MapViewExtensionPoint      | Extension of the map by additional layers. This is only available if the MapView is given as a dependency. |

All extension points are registered by the ExtensionPointManager. By the class of the ExtensionPoint it can be 
retrieved.

```java
ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
```

How an extension can be set up within the ExtensionPoint depends on its implementation. However, usually the class 
of the own extension is needed and an ID string. The latter allows to extend the system without needing a hard 
dependency on another plugin. An example of registering a view is shown below.

```java
// retrieve ViewExtensionPoint which manages views
ViewExtensionPoint viewEP = ExtensionPointManager.getExtensionPoint(ViewExtensionPoint.class);
// register group (is used to structure views in groups in the interface)
IViewGroup viewGroup = viewEP.group(ExampleBasic.EXAMPLE_GROUP_ID)
        .label("Example");
viewGroup.view(ExampleBasic.EXAMPLE_VIEW_ID, ExampleView.class)
        .label("Example View")
        .icon("/icons/question_mark.png", rsm);
```

More examples can be found in ExampleProject, where all basic examples (e.g. View, SettingsPage, MapLayer, MapTool) 
are listed.

## Manager

Within the EPD, many functionalities are provided in the form of managers. They make it possible, for example, to 
obtain views during runtime or to communicate with other plugins. The essential managers are provided by the
`PlatformUtil` class.

For most developers the classes `ViewManager`, `ModelManager` and `SelectionManager` are in regular use. The 
ViewManager stores and manages all views, so that e.g. all visible windows can be obtained. The ModelManager 
manages changes to the EPDModel (e.g. stores all registered ships). Every change to the EPDModel is done by a 
transaction (`AbstractModelTransaction`) which is stored in a stack. As soon as a user wants to undo a change, 
the transaction can be removed from the stack (cf. CTRL+Z). The `SelectionManager` has been implemented with the 
publish-subscribe pattern and stores elements that have been selected by a user. For instance, it can be used 
to receive objects that have been selected on a map. For example, information about ships can be displayed 
when a user selects one.

## EPD Configuration

In order to use the EPD and its plugins, plugins must be defined via a `Product.xml`. At this point only the
necessary steps to load your plugin are shown, for more information take a look at the 
[Plugin Manager Readme](HowToUsePluginManager.md).

```xml
<project>
    ...
	<workspaces>
	    <workspace>/home/user/epd/EPDModules/ExampleProject/pom.xml</workspace>
	</workspaces>

	<dependencies>
		<dependency>
			<groupId>de.dlr-se.emir.epd</groupId>
			<artifactId>MapView</artifactId>
			<version>4.1.0-SNAPSHOT</version>
		</dependency>
	</dependencies>
    ...
</project>
```

There are two possible ways to load a project: using dependencies or workspaces. `workspaces` are intended for 
developers who are currently implementing a plugin. By providing the path of the Maven `pom.xml` of your project
the EPD will load locally compiled files. Otherwise, the project can be loaded using maven coordinates. Just
define the project like it is done in maven and it will be loaded from the local repository (`~/.m2/repository/`).