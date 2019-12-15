name := "gcal-slack-update"
organization := "hugocf"
maintainer := "hugo@ferreira.cc"

scalaVersion := "2.12.2"

scalacOptions += "-deprecation"
scalacOptions += "-feature" // https://blog.threatstack.com/useful-scala-compiler-options-part-2-advanced-language-features
scalacOptions += "-Ypartial-unification" // https://typelevel.org/cats/

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3",
  "org.scalaj" %% "scalaj-http" % "2.4.2",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test withSources(),
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test withSources(),
  "org.mockito" % "mockito-core" % "2.23.4" % Test withSources())

enablePlugins(BuildInfoPlugin)
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)
buildInfoOptions += BuildInfoOption.BuildTime

enablePlugins(JavaAppPackaging)
mappings in Universal := (mappings in Universal).value.filter { case(jar, _) => !jar.getName.contains("scala-reflect") }

ghreleaseAssets := Seq((packageBin in Universal).value)
ghreleaseNotes := { tag => s"""See CHANGELOG [$tag](../master/CHANGELOG.md#${tag.replaceAll("[v.]", "")}) for details.""" }
