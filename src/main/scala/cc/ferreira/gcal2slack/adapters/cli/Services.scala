package cc.ferreira.gcal2slack.adapters.cli

trait Services {
  def calendar: CalendarClient

  def messaging: MessagingClient
}
