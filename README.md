# jackson-playground

Learning and exploring Jackson <https://github.com/FasterXML/jackson>.

## Description

This project uses Java 14 and the new `Records` language feature <https://openjdk.java.net/jeps/359>. Gradle itself 
cannot run on Java 14. It cannot use Java 14 for execution tasks like `test`. Fortunately, Gradle does support compiling
Java 14 code because it can fork a process using a different JDK to execute the compile task.

## Instructions

1. Use Java 13
    * This is needed because Gradle supports up to 13, but not 14
1. Compile the project with `./gradlew compileJava`
1. Run the tests with `./test.sh`
    * This uses the Standalone JUnit Console Launcher <https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher>
      and Java 14
                        `
## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Intellij is complaining of Text Blocks not being available at language level 13. This is an Intellij (bug/feature) 
  unfortunately. Track the issue for its resolution: <https://youtrack.jetbrains.com/issue/IDEA-212618>
