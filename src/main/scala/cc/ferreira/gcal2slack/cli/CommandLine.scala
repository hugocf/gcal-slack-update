package cc.ferreira.gcal2slack.cli

import java.io.File

import cc.ferreira.gcal2slack.buildinfo.BuildInfo

object CommandLine {
  def parseActions(args: Seq[String])(implicit info: BuildInfo): Seq[Action] = {
    val actions = args.map {
      case "--help" => ShowHelp
      case "--version" => ShowVersion(info)
      case s if s.startsWith("-") => ShowHelp
      case filename => ReadFile(new File(filename))
    }
    if (actions.isEmpty) Seq(ShowHelp) else actions
  }
}
