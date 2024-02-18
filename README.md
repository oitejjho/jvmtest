<h1 align="center"> HFtest-backend-jvm </h1> <br>

<p align="center">
  This service will serve two APIs
</p>


## Table of Contents

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Improvements](#improvements)




## Introduction

This service is responsible for recording events and providing statistical data as APIs

## Features
* * Save events in memory data source
* * Service statistical API


## Requirements
The application can be run locally. no database involved but abstraction is provided in case of having database

### Local
* [Java 18 SDK](https://www.oracle.com/java/technologies/downloads/#java18)
* [Maven](https://downloads.apache.org/maven/maven-3/3.8.1/binaries/)


## Quick Start
Make sure your maven is pointing to JAVA_HOME and JAVA_HOME is set to Java16 JDK

### Run Local
If your JAVA_HOME is set to Java18 JDK
```bash
$ mvn clean install
$ java -jar target/jvmtest-0.0.1-SNAPSHOT.jar
```

For multiple JDK issue
```bash
$ JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-18.0.2.jdk/Contents/Home
$ export JAVA_HOME
$ mvn clean install
$ java -jar target/jvmtest-0.0.1-SNAPSHOT.jar
```

Application will run by default on port `8080`


## Improvements
* Reactive way of coding
* Containerization
* Integration with metrics collector i.e. prometheus
* 100% Unit test code coverage
* Integration test
* Automation testing or behavioral testing i.e. RobotFramework, Selenium 