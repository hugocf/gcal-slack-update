name := "gcal-slack-update"
organization := "hugocf"

scalaVersion := "2.12.2"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature") // https://blog.threatstack.com/useful-scala-compiler-options-part-2-advanced-language-features

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",

  "org.scalatest" %% "scalatest" % "3.0.1" % Test withSources(),
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test withSources(),
  "org.mockito" % "mockito-core" % "2.7.22" % Test withSources())
