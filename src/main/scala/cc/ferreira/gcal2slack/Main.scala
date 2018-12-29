package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.buildinfo.CurrentBuild
import cc.ferreira.gcal2slack.cli._
import cc.ferreira.gcal2slack.rules.{MappingRule, StatusCalculator}
import com.typesafe.config.ConfigFactory

trait Main {
  this: Services =>

  def main(args: Array[String]): Unit =
    CommandLine.parseActions(args.toList)(CurrentBuild).foreach(processAction)

  def processAction(action: Action): Unit = action match {
    case a: DisplayText =>
      println(a.value)
    case f: ReadFile =>
      if (!f.value.exists)
        println(s"Error: File '${f.value}' does not exist")
      else {
        val config = ConfigFactory.parseFile(f.value)

        val status = for {
          rules <- MappingRule.fromConfigList(config)
          events <- calendar.fetchTodayEvents()
        } yield StatusCalculator.chooseStatus(events, rules)

        status match {
          case Left(e) => println(s"Error: ${e.message}")
          case Right(s) => s.map(messaging.updateStatus).foreach(_.left.foreach(e => println(s"Error: ${e.message}")))
        }
      }
  }
}
