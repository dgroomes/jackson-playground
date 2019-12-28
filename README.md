# jackson-playground

Learning and exploring Jackson <https://github.com/FasterXML/jackson>.

## WishList

General clean ups, TODOs and things I wish to implement for this project:

* Can I use Java 14 and use the new `Records` language feature <https://openjdk.java.net/jeps/359>?. Jackson would be 
  the first thing I need to make sure works with `Records`. I can't make much use of Records without Jackson so I'd like
  to start experimenting now. 
* Intellij is complaining of Text Blocks not being available at language level 13. This is an Intellij (bug/feature) 
  unfortunately. Track the issue for its resolution: <https://youtrack.jetbrains.com/issue/IDEA-212618>
* Why can I not deserialize using a constructor for a class that has only field? Strange, see `dgroomes.AppTest.withConstructor`
