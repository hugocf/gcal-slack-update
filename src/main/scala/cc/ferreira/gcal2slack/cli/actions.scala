package cc.ferreira.gcal2slack.cli

import java.io.File

import cc.ferreira.gcal2slack.buildinfo.BuildInfo

sealed trait Action

sealed trait DisplayText extends Action {
  def value: String
}

case object ShowHelp extends DisplayText {
  override val value: String =
    """Gcal ➔ Slack:
      |Update your Slack status according to the current Google Calendar event.
      |
      |Usage:
      |    gcal-slack-update-cli [options] filename
      |
      |    --help         Show this help message
      |    --version      Show app version and build info
      |    filename       Path to file with mapping rules definitions (ex: rules.conf)
      |""".stripMargin
}

case class ShowVersion(info: BuildInfo) extends DisplayText {
  override def value: String =
    s"${info.name} ${info.version} (scala ${info.scalaVersion}, sbt ${info.sbtVersion}) @ ${info.buildDate}"
}

case class ReadFile(value: File) extends Action
