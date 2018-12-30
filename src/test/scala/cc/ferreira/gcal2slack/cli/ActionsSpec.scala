package cc.ferreira.gcal2slack.cli

import java.time.LocalDate

import cc.ferreira.gcal2slack.BaseSpec
import cc.ferreira.gcal2slack.buildinfo.BuildInfo

class ActionsSpec extends BaseSpec {

  "show help" in {
    val result = ShowHelp.value

    result should include("gcal-slack-update-cli")
    result should include("[options] filename")
    result should include("--help")
    result should include("--version")
  }

  "show version" in {
    val testInfo: BuildInfo = new BuildInfo {
      override val name: String = "test-name"
      override val version: String = "1.2.3"
      override val sbtVersion: String = "1.2.3"
      override val scalaVersion: String = "1.2.3"
      override val buildDate: LocalDate = LocalDate.of(2017, 5, 4)
    }

    val result = ShowVersion(testInfo).value

    result should include regex "test-name 1.2.3"
    result should include regex "scala 1.2.3"
    result should include regex "sbt 1.2.3"
    result should include regex "2017-05-04"
  }

}
