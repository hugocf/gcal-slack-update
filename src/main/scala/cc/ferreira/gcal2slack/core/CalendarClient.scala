package cc.ferreira.gcal2slack.core

trait CalendarClient {
  def getCurrentEvents: Seq[CalendarEvent]
}
