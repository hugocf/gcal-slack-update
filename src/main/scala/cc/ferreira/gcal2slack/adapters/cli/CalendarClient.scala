package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.core.CalendarEvent

trait CalendarClient {
  def getCurrentEvents: Seq[CalendarEvent]
}
