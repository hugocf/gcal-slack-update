package cc.ferreira.gcal2slack.core

import java.time.LocalDateTime

case class CalendarEvent(title: String, start: LocalDateTime, end: LocalDateTime) {
  def contains(time: LocalDateTime): Boolean =
    start.isEqual(time) || start.isBefore(time) && end.isAfter(time) || end.isEqual(time)

  def contains(text: String): Boolean = title.contains(text)
}
