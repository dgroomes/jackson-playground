# jackson-playground

Learning and exploring Jackson <https://github.com/FasterXML/jackson>.

## Description

This project explores the Jackson library and predominantly focuses on the new `Records` language feature <https://openjdk.java.net/jeps/359>.
Jackson is often a core component of a Java application for serializing/deserializing to and from JSON. Does it work
with Java Records? (Answer: yes!).  

## Instructions

1. Use Java 15
1. Build and test with `./gradlew build`
1. (Optional) If the tests had previously run and passed and no source code has changed then Gradle might choose to skip
   your tests when you would otherwise prefer to actually have your tests execute. Force Gradle to run the tests by
   executing `./gradlew cleanTest` and then running your tests with `./gradlew build` or `./gradlew test`.
                        `
## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Use Jackson's CSV support
* Experiment with serializing from Records to a string, not just deserializing from a string into Records
