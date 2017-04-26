package cc.ferreira.gcal2slack.core

object StatusCalculator {
  def chooseStatus(events: Seq[CalendarEvent], rules: Seq[MappingRule]): Option[MessagingStatus] = {
    Some(MessagingStatus(":smile", "ok"))
  }
}
