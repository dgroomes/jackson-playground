# jackson-playground

📚 Learning and exploring Jackson <https://github.com/FasterXML/jackson>.


## Description

**NOTE**: This project was developed on macOS. It is for my own personal use.

This project explores the Jackson (<https://github.com/FasterXML/jackson>) umbrella of serialization/deserialization
libraries which are often a core component of a Java application that deals with JSON. For example, it illustrates how
to use the new `Records` Java language feature <https://openjdk.java.net/jeps/359> with Jackson.

A number of Jackson projects are included in this project as Git submodules because we want to use the latest "edge
version" of Jackson to get the latest and greatest features. After all, a "📚 Learning and exploring" repository is
often about learning _new_ things! These Jackson projects must be built locally and saved to your local maven
repository (a.k.a the `.m2/`directory or `mavenLocal()` in Gradle terminology). Instructions to build these projects
are in [Instructions](#instructions).


## Instructions

Follow these instructions to build the project and run the demo programs:

1. After cloning the repo for the first time, you need to also clone the submodules:
    * ```shell
      git submodule update --init --recursive
      ```
2. Update the submodules
    * If you had previously cloned the project and now want to update the submodules, then use the following command.
    * ```shell
      git submodule update --remote
      ```
4. Switch to Java 11
5. Build the "edge version" of the Jackson artifacts:
    * ```shell
      ./build-jackson-artifacts.sh
      ```
6. Switch to Java 17
7. Generate some test data (some of the tests depend on this):
    * ```shell
      ./generate-test-data.sh
      ```
8. Build the project and execute the tests:
    * ```shell
      ./gradlew build
      ```
9. (Optional) If the tests had previously run and passed and no source code has changed then Gradle might choose to skip
   your tests when you would otherwise prefer to actually have your tests execute. Force Gradle to run the tests cleaning
   them out and then running the tests: 
    * ```shell
      ./gradlew cleanTest
      ```
    * ```shell
      ./gradlew build
      ```
    * or,
      ```shell
      ./gradlew test
      ```


## Wish List

General clean ups, TODOs and things I wish to implement for this project:

* [x] DONE Use Jackson's CSV support for serializing
* [x] DONE Use Jackson's CSV support for deserializing
* [x] DONE Experiment with serializing from Records to a string, not just deserializing from a string into Records
* [x] DONE Migrate to Gradle's Kotlin DSL (use <https://github.com/dgroomes/gradle-playground> as reference)
* [x] DONE Generate "Points" (x,y) in the test data generation script instead of the arbitrary "DummyType" type 
* [ ] Fix on a specific commit in the submodules instead of "master"
* [ ] Make an example test that uses Jackson' TOML support


## Reference

* [Pro Git Chapter 7.11: Git Tools - Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
* [jackson-dataformats-text: Limitations](https://github.com/FasterXML/jackson-dataformats-text/tree/2.14/csv#limitations)
  > Due to the tabular nature of CSV format, deeply nested data structures are not well supported.
