package cc.ferreira.gcal2slack.calendar

import java.time.{LocalDate, LocalDateTime}

// Disallow direct access to the primary constructor: allDay is set only from the companion object
case class CalendarEvent private (title: String, begin: LocalDateTime, end: LocalDateTime, allDay: Boolean) {
  require(begin.compareTo(end) <= 0, s"Invalid event interval from $begin to $end")

  def contains(time: LocalDateTime): Boolean =
    begin.isEqual(time) || begin.isBefore(time) && end.isAfter(time) || end.isEqual(time)

  def contains(text: String): Boolean = title.contains(text)
}

object CalendarEvent {
  /** Makes an all-day event */
  def apply(title: String, day: LocalDate): CalendarEvent =
    new CalendarEvent(title, day.atStartOfDay, day.plusDays(1).atStartOfDay, allDay = true)

  /** Makes a regular event */
  def apply(title: String, start: LocalDateTime, end: LocalDateTime): CalendarEvent =
    new CalendarEvent(title, start, end, allDay = false)

  // Disallow access to the default apply method: allDay is set only from the other apply methods
  private def apply(title: String, start: LocalDateTime, end: LocalDateTime, allDay: Boolean): CalendarEvent =
    new CalendarEvent(title, start, end, allDay)
}
