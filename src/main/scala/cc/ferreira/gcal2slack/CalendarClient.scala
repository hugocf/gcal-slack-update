package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.core.CalendarEvent

import scala.collection.immutable.Seq

trait CalendarClient {
  def getTodayEvents: Seq[CalendarEvent]
}
