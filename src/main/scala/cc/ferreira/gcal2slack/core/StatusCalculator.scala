package cc.ferreira.gcal2slack.core

import java.time.LocalDateTime
import java.time.LocalDateTime._

object StatusCalculator {
  def chooseStatus(events: Seq[CalendarEvent], rules: Seq[MappingRule], time: LocalDateTime = now): Option[MessagingStatus] = {
    if (events.head.contains(time))
      rules.find(r => events.head.title.contains(r.matchText)).map(MessagingStatus(_))
    else
      None
  }
}
