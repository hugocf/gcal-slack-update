package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.core.CalendarEvent

trait CalendarClient {
  def getTodayEvents: Seq[CalendarEvent]
}
