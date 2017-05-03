package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.BaseSpec

class ArgumentsParserSpec extends BaseSpec {

  "retrieveActions" - {
    "--help option should return a help action" in {
      val args: Array[String] = Array("--help")

      val result = ArgumentsParser.retrieveActions(args)

      result should have size 1
      result.head shouldBe ShowHelp
      result.head.content should include("Usage:")
    }

    "--version option should return a version action" in {
      val args: Array[String] = Array("--version")

      val result = ArgumentsParser.retrieveActions(args)

      result should have size 1
      result.head shouldBe ShowVersion
      result.head.content should include regex """gcal-slack-update \d+\.\d+\.\d+"""
      result.head.content should include regex """@ \d\d\d\d-\d\d-\d\d"""
    }
  }

}
