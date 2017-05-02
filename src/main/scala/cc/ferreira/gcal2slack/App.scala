package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.adapters.cli.{CalendarClient, Main, MessagingClient, Services}

trait SlackAndGcal extends Services {
  override val calendar: CalendarClient = ???
  override val messaging: MessagingClient = ???
}

object App extends Main with SlackAndGcal
