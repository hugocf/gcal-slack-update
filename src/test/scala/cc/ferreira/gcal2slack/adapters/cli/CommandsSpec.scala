package cc.ferreira.gcal2slack.adapters.cli

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit._

import cc.ferreira.gcal2slack.BaseSpec
import cc.ferreira.gcal2slack.core.{CalendarEvent, MessagingStatus}
import org.mockito.Mockito._

class CommandsSpec extends BaseSpec {

  "main" - {
    "should update the status when the current event matches the rules" in new Context {
      val args = Array(resourcePath("rules.conf"))
      val events = Seq(CalendarEvent("A matching test", t.minus(1, HOURS), t.plus(1, HOURS)))
      when(calendarMock.getTodayEvents) thenReturn events

      TestApp.main(args)

      verify(messagingMock).updateStatus(MessagingStatus(":smile:", "ok!"))
    }
  }

  private trait Context {
    val calendarMock: CalendarClient = mock[CalendarClient](RETURNS_SMART_NULLS)
    val messagingMock: MessagingClient = mock[MessagingClient](RETURNS_SMART_NULLS)
    val t: LocalDateTime = LocalDateTime.now

    def resourcePath(file: String): String = Option(getClass.getClassLoader.getResource(file)).fold("")(_.getPath)

    trait Mocks extends Services {
      override val calendar: CalendarClient = calendarMock
      override val messaging: MessagingClient = messagingMock
    }

    object TestApp extends Commands with Mocks

  }

}
