name := "gcal-slack-update"
organization := "hugocf"

scalaVersion := "2.12.2"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:postfixOps")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test withSources(),
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test withSources())
