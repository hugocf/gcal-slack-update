package cc.ferreira.gcal2slack.calendar

trait CalendarClient {
  def getTodayEvents: Seq[CalendarEvent]
}
