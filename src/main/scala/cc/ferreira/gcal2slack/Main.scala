package cc.ferreira.gcal2slack

import java.io.File

import cc.ferreira.gcal2slack.buildinfo.CurrentBuild
import cc.ferreira.gcal2slack.cli._
import cc.ferreira.gcal2slack.rules.{MappingRule, StatusCalculator}

trait Main {
  this: Services =>

  def main(args: Array[String]): Unit =
    CommandLine.parseActions(args.toList)(CurrentBuild).foreach(processAction)

  def processAction(action: Action): Unit = {
    action match {
      case t: DisplayText => println(t.value)
      case f: ReadFile => processRules(f.value)
    }

    def processRules(file: File): Unit = {
      val result = for {
        specs <- Settings.fetchConfig(file)
        rules <- MappingRule.fromConfigList(specs)
        events <- calendar.fetchTodayEvents()
        status <- Right(StatusCalculator.chooseStatus(events, rules))
        _ <- messaging.updateStatus(status)
      } yield ()

      result.left.foreach(e => println(s"Error: ${e.message}"))
    }
  }

}
