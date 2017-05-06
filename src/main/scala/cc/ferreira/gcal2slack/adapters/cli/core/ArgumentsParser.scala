package cc.ferreira.gcal2slack.adapters.cli.core

import scala.collection.immutable.Seq

object ArgumentsParser {
  def retrieveActions(args: Seq[String])(implicit info: BuildInfo): Seq[Action] =
    args.map({
      case "--help" => ShowHelp
      case "--version" => ShowVersion(info)
    })
}
