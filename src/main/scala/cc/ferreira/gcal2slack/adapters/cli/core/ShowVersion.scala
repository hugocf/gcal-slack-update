package cc.ferreira.gcal2slack.adapters.cli.core

case class ShowVersion(info: BuildInfo) extends Action {
  override def content: String =
    s"${info.name} ${info.version} (scala ${info.scalaVersion}, sbt ${info.sbtVersion}) @ ${info.buildDate}"
}
