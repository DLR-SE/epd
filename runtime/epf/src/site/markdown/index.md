# eMIR Plugin System (EPF)

The eMIR Plugin System (EPF) is a maven based plugin system. It loads plugins (with developer defined entry points) and
libraries (projects without dependencies to the plugin system) using the maven tool. This allows developers to use the
existing maven ecosystem and integrate their project with minimal effort. Some additional benefits are as follows:

- dynamically load plugins (Dependencies)
- resolve dependencies of loaded plugins and libraries
- load classes from different IDE working directories (Workspaces)
- update plugins based on maven repositories

## Product Definition and Configuration

The main entrypoint for EPF is it's product definition. This is defined via a xml file (`Product.xml`) and contains
base information (name, description, version), a repository location (`localRepos` for dependencies), workspaces
(local projects that should be loaded), dependencies and online repositories. Syntactically the file format follows
the maven file definition. However, not all features are implemented or necessary. The following snippet shows the
basic structure of a `Product.xml`.

```xml

<project>
    <!-- This is NOT a MAVEN file it just uses a similar syntax -->

    <!-- base information about the product -->
    <name>EPD</name>
    <description>eMaritime Prototype Display</description>
    <version>1.0.0</version>

    <!-- local/offline repository, by default this can be the `.m2/repository` folder in the users home directory -->
    <localRepos>${HOME_FOLDER}/.m2/repository</localRepos>

    <!-- local projects, defined via an absolute path to the projects pom.xml. This loads class files during startup -->
    <workspaces>
        <workspace>/home/user/code/pom.xml</workspace>
    </workspaces>

    <!-- regular dependencies following the maven coordinate definition (group, artifact, version). -->
    <dependencies>
        <dependency>
            <groupId>de.dlr-se.emir.epd</groupId>
            <artifactId>MapView</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>

    <!-- online repository definition -->
    <repositories>
        <repository>
            <!-- unique id -->
            <id>MavenCentral</id>
            <url>https://repo1.maven.org/maven2/</url>
            <username></username>
            <passwort></passwort>
        </repository>
    </repositories>

</project>
```

### Local Repository

Path definition of local repositories can be absolute and relative to the current working directory. However, since
this is not (always) portable, some variables got introduces that simplify the URL definition:

- **CWD_FOLDER**: Current working directory set by the shell or other programs
- **HOME_FOLDER**: User home directory
- **PRODUCT_FOLDER**: Product folder (default is `/home/<User>/<ProductName>`)

When the `Product.xml` is loaded, these variables will be replaced with the corresponding value. **These variables
are not environmental variables!** Thus, this feature is limited only to these variables. Other environmental variables
are also ignored! If the resulting path does not exist, the default value is used (`/home/<User>/.m2/repository`). An
error log message will indicate if a path does not exist and if the default path is used instead.

### Artifacts

Within the EPF we differ between two types of artifacts, that could be either 1) plugins or 2) libraries. If a plugin
contains one specific class that implements the interface `de.emir.tuml.ucore.runtime.UCorePlugin`. This class will be
called if the plugin shall be started. A library on the other hand does not contain (or does not publish) such an
implementation.

Both plugins and libraries can be either loaded as:

- **Dependency (Release Mode)**: Within this mode, the plugin or library is available as an *.jar file within a local
  maven repository.
- **Workspace (Development Mode)**: Within this mode, the EPF uses *.class files provided by an IDE (for example Eclipse)
  instead of a file. This mode (at least in combination with Eclipse) allows the debugging and hot replacement of
  source code.

## Extensions by EPF Project

The EPF Project does the following extensions

- **PluginManager**: The `de.emir.tuml.runtime.epf.PluginManager` registers itself as accessible instance in the
  ExtensionManager. Using `UCoreExtensionManager.getExtensions(PluginManager.class)` returns the instance of the
  PluginManager that has been used to load all plugins during startup.

- **ClasspathEntry**: The `de.emir.tuml.runtime.epf.ClasspathEntry` created for a plugin or its dependencies will be
  registered as an extension within the ExtensionManager. Using the ClasspathEntry you get access to the classloader,
  as well as its dependencies at runtime.
  ```java
  Collection<ClasspathEntry> entries = UCoreExtensionManager.getExtensions(ClasspathEntry.class);
  ```

- **ClassPathProvider**: The `de.emir.tuml.runtime.epf.ClasspathEntry` implements the
  `de.emir.tuml.ucore.runtime.resources.ClassPathProvider` interface to allow access to the plugins classpath, e.g. to
  search for classes or resources inside the plugin
  ```java
  Collection<ClassPathProvider> provider = UCoreExtensionManager.getExtensions(ClassPathProvider.class);
  ```

