package cc.ferreira.gcal2slack.cli

import java.io.File

import cc.ferreira.gcal2slack.buildinfo.BuildInfo

object CommandLine {
  def parseActions(args: Seq[String])(implicit info: BuildInfo): Seq[Action] =
    args.map {
      case "--help" => ShowHelp
      case "--version" => ShowVersion(info)
      case filename => ReadFile(new File(filename))
    }
}
