### How to document

This document describes how project documentation should be done in the context of the epd. For our documentation
we rely on the Maven Site Plugin, beside classical javadoc. Thus, every Plug-In should contain a "src/site" folder 
as defined in the [Maven Site Plugin guide](https://maven.apache.org/plugins/maven-site-plugin/).

Documentation for a plugin should contain at least the following information:

* Use Case
  *  Describe the general use case for your project (what is the goal/purpose of this project)
* Build Instructions
    * What's needs to be done to build this project. In most cases this might be a simple `mvn clean install`, but
      may be more complex depending on your provides functionalities or setup (e.g. third party dependencies from a
      company repository)
* User Manual
    * This chapter serves as the users entry point. It describes how the project needs to be setup and gives an 
    introduction about its features. Its main purpose is to give new users (not necessarily developers) a tutorial
    about what the project does and how it can be used.
* Development Manual
  * This chapter is used to describe the project for developers. It should describe what the plugin provides as 
  functionalities and how features can be extended e.g. entry point for extending functionalities).

The documentation can be built with `mvn site:site`. An up-to-date version can be found here **TODO**.