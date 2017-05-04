package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.BaseSpec

class CurrentBuildSpec extends BaseSpec {

  "buildDate" - {
    "should convert build info to LocalDate correctly" in {
      buildinfo.BuildInfo.builtAtString should startWith(CurrentBuild.buildDate.toString)
    }
  }

}
