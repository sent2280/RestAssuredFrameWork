# RestAssuredFrameWork

## Introduction

This document gives information about, how to install restAssured framework and setup. For more information about framework 
components,API,reports and capabilities; please visit the [wiki page](https://github.com/sent2280/RestAssuredFrameWork/wiki).
    
## Prerequisites
  
1. Java 1.8 (tested with java 1.8)
2. Maven
3. Eclipse or any other IDE which supports java
4. Other dependencies are packed as part of framework jar.

## Installation

1. This framework is published in central maven repository, so it can be downloaded as maven dependency. Add the below tags into
pom.xml file to download the framework.

``` xml
<dependency>
  <groupId>com.github.sent2280</groupId>
  <artifactId>RestAssuredFramework</artifactId>
  <version>1.0.0</version>
</dependency>
```

2. Update the maven project then Go to maven dependency and verify **RestAssuredFramework-1.0.0.jar** is downloaded successfully.

3. if you are new to maven, Then visit this link to learn [how to install and create maven project](https://www.toolsqa.com/java/maven/create-new-maven-project-eclipse/).
After that do the step 1 and 2.

4. Add the following dependency in pom.xml file
    
    1. RestAssured library
    2. TestNG
    3. Json simple
    4. hamcrest
    
 Below are the dependencies has to be added:
    
``` xml     
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
     <version>3.3.0</version>
</dependency>

<dependency>
   <groupId>org.testng</groupId>
   <artifactId>testng</artifactId>
   <version>7.1.0</version>
</dependency>

<dependency>
   <groupId>com.googlecode.json-simple</groupId>
   <artifactId>json-simple</artifactId>
   <version>1.1.1</version>
</dependency>

<dependency>
   <groupId>org.hamcrest</groupId>
   <artifactId>java-hamcrest</artifactId>
   <version>2.0.0.0</version>
</dependency>
		
<dependency>
   <groupId>org.hamcrest</groupId>
   <artifactId>hamcrest-core</artifactId>
   <version>1.3</version>
</dependency>
		
<dependency>
   <groupId>org.hamcrest</groupId>
   <artifactId>hamcrest-library</artifactId>
   <version>2.1</version>
   <scope>test</scope>
</dependency>
```

With this all necessory dependencies are linked to project. In next step, we will discuss about configurations.

## Configuration

Framework uses **Log4j** to display logs in console and to store in file. Different environment support is possible, So User can be able to configure different urls for QA, UAT and Prod. For more details about log4j please visit following [doc](https://logging.apache.org/log4j/1.2/).
   
For log4g and multi environment support, user has to create following files:

1. Create **config** folder under project
2. Create **log4j.properties** file inside config folder
3. Add the following configuration into log4j property file

```js
log4j.rootLogger=INFO, file, stdout
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=${user.dir}/output/logs/restAPI.log
log4j.appender.file.MaxFileSize=10MB
log4j.appender.file.MaxBackupIndex=10
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
log4j.appender.rollingFile.append=false
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```
4. Create **testproject.properties** file inside config
5. Add the following configuration into testproject property file

```js
testProject.QA = http://dummy.restapiexample.com/api/v1
testProject.Prod = http://dummy-prod.restapiexample.com/api/v1
testProject.UAT = http://dummy-uat.restapiexample.com/api/v1
```

This url will be taken by the framework automatically. Overriding this at runtime also possible, Which will be disussed in next section.


## First script


## Reference
Visit the [wiki page](https://github.com/sent2280/RestAssuredFrameWork/wiki) for complete details about this framework.
