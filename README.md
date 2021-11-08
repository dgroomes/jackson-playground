# jackson-playground

📚 Learning and exploring Jackson <https://github.com/FasterXML/jackson>.

## Description

This project explores the Jackson (<https://github.com/FasterXML/jackson>) umbrella of serialization/deserialization
libraries which are often a core component of a Java application that deals with JSON. For example, it illustrates how
to use the new `Records` Java language feature <https://openjdk.java.net/jeps/359> with Jackson.

A number of Jackson projects are included in this project as Git submodules because we want to use the latest "edge
version" of Jackson to get the latest and greatest features. After all, a "📚 Learning and exploring" repository is
often about learning _new_ things! These Jackson projects must be built locally and saved to your local maven
repository (a.k.a the `.m2/`directory or `mavenLocal()` in Gradle terminology). Instructions to build these projects
are in [Instructions](#instructions).

## Instructions

NOTE: I am using macOS and Bash 5.x

1. After cloning the repo for the first time, you need to also clone the submodules:
    * `git submodule update --init --recursive`
1. Switch to Java 11
1. Build the "edge version" of the Jackson artifacts:
    * `./build-jackson-artifacts.sh`
1. Switch to Java 17
1. Generate some test./generate-test-data.sh date (some of the tests depend on this):
    * ``
1. Build the project and execute the tests:
    * `./gradlew build`
1. (Optional) If the tests had previously run and passed and no source code has changed then Gradle might choose to skip
   your tests when you would otherwise prefer to actually have your tests execute. Force Gradle to run the tests cleaning
   them out and then running the tests: 
    * `./gradlew cleanTest`
    * `./gradlew build` or `./gradlew test`

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* DONE Use Jackson's CSV support for serializing
* DONE Use Jackson's CSV support for deserializing
* DONE Experiment with serializing from Records to a string, not just deserializing from a string into Records
* DONE Migrate to Gradle's Kotlin DSL (use <https://github.com/dgroomes/gradle-playground> as reference)
* DONE Generate "Points" (x,y) in the test data generation script instead of the arbitrary "DummyType" type 
* Fix on a specific commit in the submodules instead of "master"
