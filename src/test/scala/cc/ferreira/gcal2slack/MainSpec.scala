package cc.ferreira.gcal2slack

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit._

import cc.ferreira.gcal2slack.calendar.{CalendarClient, CalendarEvent}
import cc.ferreira.gcal2slack.messaging.{MessagingClient, MessagingStatus}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar

class MainSpec extends BaseSpec with MockitoSugar {

  "main" - {
    "should update the status when the current event matches the rules" in new Context {
      val args = Array(resourcePath("rules.conf"))
      val events = List(CalendarEvent("A matching test", t.minus(1, HOURS), t.plus(1, HOURS)))
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

    object TestApp extends Main with Mocks

  }

}
