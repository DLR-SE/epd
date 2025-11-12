# Building from Source

This document describes how to build the eMIRapplication from command line, but the same process is valid for all other products, developed using the eMIR libraries.
Later in this document you will also find a short description on how to set up the project in Eclipse.

\section Requirements
- [JDK 1.8.144 (+) or 10.0.1 (+)](http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)
    + Currently, it has to be a Java version with the form MAJOR . MINOR . REVISION
- [Maven 3.3.9 (+)](https://maven.apache.org/download.cgi)
- Internet access to download dependencies


The build process is based on [Maven](https://maven.apache.org/) and (currently) accesses private repositories that are protected by a password.
To give Maven the necessary permissions, Maven's Settings.xml must be modified ( for more information see [here](https://maven.apache.org/settings.html) ).

In particular, the repository servers must be entered.
A Minimum Settings file is shown below. If entries already exist in the local Maven file, they may need to be merged.

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">

      <...>
      
		<servers>
			<server>
				<id>emir</id>
				<username>user</username>
				<password>password</password>
			</server>			
			<server>
				<id>thirdparty</id>
				<username>user</username>
				<password>password</password>
			</server>
		</servers>
		
		<...>
		
    </settings>
```

## Build from command line

The actual build process is called by the command

	mvn clean install 

in the source code directory.

During this process, all required dependencies will be downloaded from the various repositories (to see a list of all used dependencies see @ref pageuiplugincoreLicenses), the projects are compiled and test cases are executed if necessary.

As a result of the build process, the file eMIRApplication-4.0.0-SNAPSHOT.jar is created in the directory

	[Source]/epd/Products/eMIRApplication/target/

which is an executable jar file.


## Running from command line
To execute the previously built file, first change to the current working directory of the application:

	cd epd/Products/eMIRApplication

and starts the application with:

	java -jar ./target/eMIRApplication-4.0.0-SNAPSHOT.jar

Note: Do not start the application from the target folder, as the default configuration expect the workspace to be a folder on the root level (e.g. starting point)

A splash screen will appear showing the current progress.


## Build with Eclipse

To build the eMIRApplication with Eclipse, the required projects must be imported into the Eclipse workspace.
This is done via Eclipse's Maven plugin.

![Import existing Maven Projects](images/EclipseMavenImportExistingProject.png)


In the case of the eMIRApplication all projects can be selected in the epd folder.

![Select projects to import](images/EclipseMavenImportIkimuni.png)

To start the application later, from Eclipse, at least one product must be imported. In this case it is the eMIRApplication product.


The eMIRApplication is started via the project "eMIRApplication", more precisely the class:

	de.emir.rcp.product.eMIRApplicationProduct



# First start

If the start is the first start on the current system (and the current user), a notification is also displayed that the system must first be configured, as shown in the following picture.

![Prompt to configure on first startup on a new system](images/FirstStart.png)

After clicking the OK button, a configuration software (eMIR PluginManager) is first extracted into the home directory of the application and then started.
Note: The home directory is by default:

	[User.Home]/eMIRApplication 



![eMIR PluginManager to configure on first startup](images/ResolveDependencyInformation.png)

The eMIRApplication configuration already contains predefined dependencies.
For a detailed description of the PluginManager, please refer to the corresponding documentation (@ref PluginManagerDocumentation).

## Download Dependencies  
Due to the build process executed above, not all dependencies that are necessary for the actual configuration have been installed.

The PluginManager is able to download them. To check whether all dependencies are present or to download those that do not yet exist, simply click on the "Resolve Dependency Information" button (see picture above).

In the following, all dependencies (and recursively their dependencies) are resolved and, if necessary, downloaded.
The local repository (see picture above) is used as download target.

Note: An active connection is required for this function.
Depending on the Internet connection, this process can take several minutes.

After the progress display has disappeared, the program can be closed with the 'Apply & Exit' button.
The eMIRApplication will then automatically continue loading.

##  Successful startup
If everything was loaded successfully, the following window is displayed.

![Successfully loaded eMIRApplication](images/EmptyIkimuniApplication.png)

