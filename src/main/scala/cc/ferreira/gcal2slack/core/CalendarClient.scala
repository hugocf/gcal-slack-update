package cc.ferreira.gcal2slack.core

import java.time.LocalTime

trait CalendarClient {
  def getCurrentEvents(when: LocalTime): Seq[CalendarEvent]
}
