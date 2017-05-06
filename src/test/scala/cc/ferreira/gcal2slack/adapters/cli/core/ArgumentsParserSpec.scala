package cc.ferreira.gcal2slack.adapters.cli.core

import java.time.LocalDate

import cc.ferreira.gcal2slack.BaseSpec

class ArgumentsParserSpec extends BaseSpec {

  "retrieveActions" - {
    "--help option should return a help action" in {
      val args = List("--help")

      val result = ArgumentsParser.retrieveActions(args)

      result should have size 1
      result.head shouldBe ShowHelp
      result.head.content should include("Usage:")
    }

    "--version option should return a version action" in {
      val args = List("--version")

      val result = ArgumentsParser.retrieveActions(args)

      result should have size 1
      result.head shouldBe a[ShowVersion]
      result.head.content should include regex "test-name 1.2.3"
      result.head.content should include regex "scala 1.2.3"
      result.head.content should include regex "sbt 1.2.3"
      result.head.content should include regex "2017-05-04"
    }
  }

  private implicit val testInfo: BuildInfo = new BuildInfo {
    override val name: String = "test-name"
    override val version: String = "1.2.3"
    override val sbtVersion: String = "1.2.3"
    override val scalaVersion: String = "1.2.3"
    override val buildDate: LocalDate = LocalDate.of(2017, 5 , 4)
  }

}
