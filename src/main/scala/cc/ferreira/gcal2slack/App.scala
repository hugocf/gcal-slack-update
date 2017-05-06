package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.adapters.cli.CommandLine
import cc.ferreira.gcal2slack.core.{CalendarEvent, MessagingStatus, StatusCalculator}

import scala.collection.immutable.Seq

trait Services {
  val calendar: CalendarClient = new CalendarClient { override def getTodayEvents: Seq[CalendarEvent] = ??? }
  val messaging: MessagingClient = new MessagingClient { override def updateStatus(status: MessagingStatus): Unit = ??? }
}

trait Main {
  this: Services =>

  def main(args: Array[String]): Unit =
    StatusCalculator.chooseStatus(calendar.getTodayEvents, CommandLine.parseRules(args.toList)).foreach(messaging.updateStatus)
}

object App extends Main with Services
