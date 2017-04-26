package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.core.MappingRule
import cc.ferreira.gcal2slack.core.StatusCalculator._

trait Commands {
  this: Services =>

  def main(args: Array[String]): Unit = {
    val rules = Seq.empty[MappingRule]
    chooseStatus(calendar.getCurrentEvents, rules).foreach(messaging.updateStatus)
  }
}
