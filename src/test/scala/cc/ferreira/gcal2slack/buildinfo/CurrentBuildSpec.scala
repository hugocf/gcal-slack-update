package cc.ferreira.gcal2slack.buildinfo

import cc.ferreira.gcal2slack.BaseSpec

class CurrentBuildSpec extends BaseSpec {

  "build date" - {
    "should convert build info to LocalDate correctly" in {
      buildinfo.BuildInfo.builtAtString should startWith(CurrentBuild.buildDate.toString)
    }
  }

}
