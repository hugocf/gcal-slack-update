package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.adapters.cli.{CalendarClient, Commands, MessagingClient, Services}

trait SlackAndGcal extends Services {
  override val calendar: CalendarClient = ???
  override val messaging: MessagingClient = ???
}

object App extends Commands with SlackAndGcal
