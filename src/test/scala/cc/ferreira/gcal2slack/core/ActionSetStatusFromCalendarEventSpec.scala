package cc.ferreira.gcal2slack.core

import cc.ferreira.gcal2slack.BaseSpec
import org.mockito.Mockito._

class ActionSetStatusFromCalendarEventSpec extends BaseSpec {

  "execute" - {
    "should set the status when the rules matches the active event title" in new Context {
      val rules = Seq(MappingRule("matching test", ":smile", "ok"))
      when(calendarMock.getCurrentEvents) thenReturn Seq(CalendarEvent("This is a matching test"))

      action.execute(rules)

      verify(messagingMock).updateStatus(MessagingStatus(":smile", "ok"))
    }
  }

  trait Context {
    val calendarMock: CalendarClient = mock[CalendarClient](RETURNS_SMART_NULLS)
    val messagingMock: MessagingClient = mock[MessagingClient](RETURNS_SMART_NULLS)

    def action = ActionSetStatusFromCalendarEvent(calendarMock, messagingMock)
  }

}
