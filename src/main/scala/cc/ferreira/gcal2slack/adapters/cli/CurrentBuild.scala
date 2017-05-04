package cc.ferreira.gcal2slack.adapters.cli

import java.time.ZoneOffset.UTC
import java.time.{LocalDate, LocalDateTime}

import cc.ferreira.gcal2slack.adapters.cli.core.BuildInfo

object CurrentBuild extends BuildInfo {
  override val name: String = buildinfo.BuildInfo.name
  override val version: String = buildinfo.BuildInfo.version
  override val scalaVersion: String = buildinfo.BuildInfo.scalaVersion
  override val sbtVersion: String = buildinfo.BuildInfo.sbtVersion

  override def buildDate: LocalDate = {
    val epoch = buildinfo.BuildInfo.builtAtMillis / 1000
    LocalDateTime.ofEpochSecond(epoch, 0, UTC).toLocalDate
  }
}
