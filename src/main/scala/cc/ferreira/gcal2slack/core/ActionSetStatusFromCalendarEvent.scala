package cc.ferreira.gcal2slack.core

case class ActionSetStatusFromCalendarEvent(calendar: CalendarClient, messaging: MessagingClient) {

  def execute(rules: Seq[MappingRule]): Unit = {
    messaging.updateStatus(MessagingStatus(rules.head))
  }

}
