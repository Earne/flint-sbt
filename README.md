### An example SBT project which uses [macro paradise](http://docs.scala-lang.org/overviews/macros/paradise.html) (Scala 2.10/2.11 with the macro paradise plugin, SBT 0.13)

To verify that everything works fine, do `sbt run`.

Note that currently SBT doesn't support recompilation of macro clients if the dependencies of the macro implementation have changed - macro clients are only recompiled when the macro definition itself is:  https://github.com/sbt/sbt/issues/399.

Huge thanks to Paul Butcher (https://github.com/paulbutcher/ScalaMock/blob/typemacros/project/Build.scala) and Adam Warski (https://github.com/adamw/scala-macro-debug) whose SBT projects I used as prototypes for this one.