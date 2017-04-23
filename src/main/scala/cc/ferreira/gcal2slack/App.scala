package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.core.{CalendarClient, MessagingClient}

trait Services {
  def calendar: CalendarClient = ???
  def messaging: MessagingClient = ???
}

trait Commands { this: Services =>
  def main(args: Array[String]): Unit = ()
}

object App extends Commands with Services
