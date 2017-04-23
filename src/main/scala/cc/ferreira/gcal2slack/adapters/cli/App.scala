package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.core.{ActionSetStatusFromCalendarEvent, CalendarClient, MappingRule, MessagingClient}

trait Services {
  def calendar: CalendarClient = ???
  def messaging: MessagingClient = ???
}

trait Commands { this: Services =>
  def main(args: Array[String]): Unit =
    ActionSetStatusFromCalendarEvent(Seq.empty[MappingRule], calendar, messaging)
}

object App extends Commands with Services
