package cc.ferreira.gcal2slack.core

import java.time.LocalDateTime
import java.time.LocalDateTime._

object StatusCalculator {
  def chooseStatus(events: Seq[CalendarEvent], rules: Seq[MappingRule], time: LocalDateTime = now): Option[MessagingStatus] = {
    if (events.head.contains(time))
      Some(MessagingStatus(":smile:", "ok"))
    else
      None
  }
}
