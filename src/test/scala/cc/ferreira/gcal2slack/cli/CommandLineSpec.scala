package cc.ferreira.gcal2slack.cli

import cc.ferreira.gcal2slack.BaseSpec
import cc.ferreira.gcal2slack.buildinfo.{BuildInfo, CurrentBuild}

class CommandLineSpec extends BaseSpec {

  "parsing actions" - {
    "--help option should return a help action" in {
      val args = List("--help")

      val result = CommandLine.parseActions(args)

      result should have size 1
      result.head shouldBe ShowHelp
    }

    "--version option should return a version action" in {
      val args = List("--version")

      val result = CommandLine.parseActions(args)

      result should have size 1
      result.head shouldBe a[ShowVersion]
    }

    "rules file name should return an action to parse rules" in {
      val args = List("rules.conf")

      val result = CommandLine.parseActions(args)

      result should have size 1
      result.head shouldBe a[ReadFile]
      result.head.asInstanceOf[ReadFile].value.getName shouldBe "rules.conf"
    }
  }

  private implicit val testInfo: BuildInfo = CurrentBuild
}
