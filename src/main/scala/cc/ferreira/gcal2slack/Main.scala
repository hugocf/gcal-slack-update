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
        val rules = MappingRule.fromConfigList(ConfigFactory.parseFile(f.value))
        val events = calendar.getTodayEvents
        val status = StatusCalculator.chooseStatus(events, rules)
        status.foreach(messaging.updateStatus)
    }
  }
}
