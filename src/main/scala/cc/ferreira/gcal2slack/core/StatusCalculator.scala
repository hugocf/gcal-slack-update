package cc.ferreira.gcal2slack.core

import java.time.LocalDateTime
import java.time.LocalDateTime._
import java.time.ZoneOffset.UTC

object StatusCalculator {
  def chooseStatus(events: Seq[CalendarEvent], rules: Seq[MappingRule], time: LocalDateTime = now): Option[MessagingStatus] = {
    def distance(begin: LocalDateTime, end: LocalDateTime) =
      math.abs(end.toEpochSecond(UTC) - begin.toEpochSecond(UTC))

    events
      .filter(_.contains(time))
      .sortBy(e => distance(e.start, time))
      .sortBy(_.allDay)   // false < true
      .flatMap(e => rules.find(r => e.contains(r.matchText)))
      .headOption
      .map(MessagingStatus(_))
  }
}
