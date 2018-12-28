package cc.ferreira.gcal2slack.rules

import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.ZoneOffset.UTC

import cc.ferreira.gcal2slack.calendar.CalendarEvent
import cc.ferreira.gcal2slack.messaging.MessagingStatus

object StatusCalculator {
  def chooseStatus(events: Seq[CalendarEvent], rules: Seq[MappingRule], time: LocalDateTime = now): Option[MessagingStatus] = {
    def distance(begin: LocalDateTime, end: LocalDateTime) =
      math.abs(end.toEpochSecond(UTC) - begin.toEpochSecond(UTC))

    events
      .filter(_.contains(time))
      .sortBy(e => distance(e.begin, time))
      .sortBy(_.allDay)   // false < true
      .flatMap(e => rules.find(r => e.contains(r.matchText)))
      .headOption
      .map(MessagingStatus(_))
  }
}
