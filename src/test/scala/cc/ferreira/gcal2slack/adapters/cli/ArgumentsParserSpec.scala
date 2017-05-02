package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.BaseSpec

class ArgumentsParserSpec extends BaseSpec {

  "retrieveActions" - {
    "--help option should return a help action text" in {
      val args: Array[String] = Array("--help")

      val result = ArgumentsParser.retrieveActions(args)

      result should have size 1
      result.head shouldBe HelpAction
      result.head.content should include("Usage:")
    }
  }

}
