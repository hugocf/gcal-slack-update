package cc.ferreira.gcal2slack.buildinfo

import java.time.ZoneOffset.UTC
import java.time.{LocalDate, LocalDateTime}

object CurrentBuild extends BuildInfo {
  val name: String = buildinfo.BuildInfo.name
  val version: String = buildinfo.BuildInfo.version
  val scalaVersion: String = buildinfo.BuildInfo.scalaVersion
  val sbtVersion: String = buildinfo.BuildInfo.sbtVersion

  def buildDate: LocalDate = {
    val epoch = buildinfo.BuildInfo.builtAtMillis / 1000
    LocalDateTime.ofEpochSecond(epoch, 0, UTC).toLocalDate
  }
}
