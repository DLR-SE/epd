# eMIR Plugin System (EPF)
## Introduction
This page introduces the eMIR Plugin System (EPF), that is used to
- dynamically load plugins (Dependencies)
- load classes from different IDE working directories (Workspaces)
- resolve dependencies of loaded plugins and libraries
- update plugins based on maven repositories

## Artifacts
Within the EPF we differ between two types of artifacts, that could be either:
- plugins
- libraries

where a plugin contains one specific class that implements the interface de.emir.tuml.ucore.runtime.UCorePlugin. This class will be called if the plugin shall be started.
A library on the other hand does not contain (or does not publish) such an implementation.

## Artifact Declaration
Both plugins and libraries can be either loaded as:
- Dependency (Release Mode): Within this mode, the plugin or library is available as an *.jar file within a local maven repository.
- Workspace (Development Mode): Within this mode, the EPF uses *.class files provided by an IDE (for example Eclipse) instead of a file.
  This mode (at least in combination with Eclipse) allows the debugging and hot replacement of source code.

## Extensions by EPF Project
The EPF Project does the following extensions
- PluginManager: 
The de.emir.tuml.runtime.epf.PluginManager registers itself as accessible instance in the ExtensionManager.
Using UCoreExtensionManager.getExtensions(PluginManager.class) returns the instance of the PluginManager that has been used to load all plugins during startup.

- ClasspathEntry:
the de.emir.tuml.runtime.epf.ClasspathEntry created for a plugin or its dependencies will be registered as an extension within the ExtensionManager.
Using the ClasspathEntry you get access to the classloader, as well as its dependencies at runtime.  
```java
Collection<ClasspathEntry> entries = UCoreExtensionManager.getExtensions(ClasspathEntry.class);
```

- ClassPathProvider:
The de.emir.tuml.runtime.epf.ClasspathEntry implements the de.emir.tuml.ucore.runtime.resources.ClassPathProvider interface to allow access to the plugins classpath, e.g. to search for classes or resources inside the plugin
```java
Collection<ClassPathProvider> provider = UCoreExtensionManager.getExtensions(ClassPathProvider.class);
```

