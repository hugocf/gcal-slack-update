package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.buildinfo.CurrentBuild
import cc.ferreira.gcal2slack.cli._
import cc.ferreira.gcal2slack.rules.{MappingRule, StatusCalculator}
import com.typesafe.config.ConfigFactory

trait Main {
  this: Services =>

  def main(args: Array[String]): Unit = {
    CommandLine.parseActions(args.toList)(CurrentBuild).foreach {
      case a: DisplayText =>
        println(a.value)
      case f: ReadFile =>
        val status = for {
          rules <- MappingRule.fromConfigList(ConfigFactory.parseFile(f.value))
          events <- calendar.fetchTodayEvents()
        } yield StatusCalculator.chooseStatus(events, rules)

        status match {
          case Left(e) => println(e.message)
          case Right(s) => s.foreach(messaging.updateStatus)
        }
    }
  }
}
