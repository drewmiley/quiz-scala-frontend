name := """quiz-scala-frontend"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  guice
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
