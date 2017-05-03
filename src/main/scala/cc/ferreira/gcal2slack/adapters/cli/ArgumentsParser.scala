package cc.ferreira.gcal2slack.adapters.cli

import java.time.{LocalDate, LocalDateTime}
import java.time.ZoneOffset.UTC

import cc.ferreira.gcal2slack.BuildInfo._

object ArgumentsParser {
  def retrieveActions(args: Array[String]): Seq[Action] =
    args.map({
      case "--help" => ShowHelp
      case "--version" => ShowVersion
    })
}

sealed trait Action {
  def content: String
}

case object ShowHelp extends Action {
  override val content: String =
    """Gcal ➔ Slack:
      |Update your Slack status according to the current Google Calendar event.
      |
      |Usage:
      |    scala -jar gcal-slack-update.jar [options] file
      |
      |    --help         Show this help message
      |    file           Path to file with mapping rules definitions (ex: rules.conf)
      |""".stripMargin
}

case object ShowVersion extends Action {
  override def content: String = s"$name $version (scala $scalaVersion, sbt $sbtVersion) @ $buildDate"

  def buildDate: LocalDate = {
    val epoch = builtAtMillis / 1000
    LocalDateTime.ofEpochSecond(epoch, 0, UTC).toLocalDate
  }
}
