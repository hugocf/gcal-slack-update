package cc.ferreira.gcal2slack.adapters.cli.core

object ArgumentsParser {
  def retrieveActions(args: Array[String])(implicit info: BuildInfo): Seq[Action] =
    args.map({
      case "--help" => ShowHelp
      case "--version" => ShowVersion(info)
    })
}
