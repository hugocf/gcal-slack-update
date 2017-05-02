package cc.ferreira.gcal2slack.adapters.cli

object ArgumentsParser {
  def retrieveActions(args: Array[String]): Seq[Action] = Seq(HelpAction)
}

sealed trait Action {
  def content: String
}

case object HelpAction extends Action {
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
