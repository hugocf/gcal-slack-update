package cc.ferreira.gcal2slack.calendar

import cc.ferreira.gcal2slack.Result

trait CalendarClient {
  def fetchTodayEvents(): Result[Seq[CalendarEvent]]
}
