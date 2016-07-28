name := """my-scaldi-play-sample"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi-play" % "0.5.15",
  "org.specs2" %% "specs2-core" % "3.5" % "test",
  "org.specs2" %% "specs2-junit" % "3.5" % "test",
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

routesGenerator := InjectedRoutesGenerator
lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
