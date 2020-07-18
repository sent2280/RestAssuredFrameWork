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


## Configuration

## First script


## Reference
Visit the [wiki page](https://github.com/sent2280/RestAssuredFrameWork/wiki) for complete details about this framework.
