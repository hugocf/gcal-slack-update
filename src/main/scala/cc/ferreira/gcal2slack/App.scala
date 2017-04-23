package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.core.{CalendarClient, MessagingClient}

trait Services {
  def calendar: CalendarClient = ???
  def messaging: MessagingClient = ???
}

trait Commands { this: App with Services =>
  def run(): Unit = ()
}

object App extends App with Commands with Services {
  run()
}
