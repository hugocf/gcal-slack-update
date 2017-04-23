package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.core.{CalendarClient, CalendarEvent, MessagingClient, MessagingStatus}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._

class AppSpec extends BaseSpec {

  "App" - {
    "should update the status when the current event matches the rules" is pendingUntilFixed { new Context {
      val args = Array(resourcePath("rules.conf"))
      when(calendarMock.getCurrentEvents(any())) thenReturn Seq(CalendarEvent("This is a matching test"))

      TestApp.main(args)

      verify(messagingMock).updateStatus(MessagingStatus(":smile:", "Ok!"))
    }}
  }

  private trait Context {
    val calendarMock: CalendarClient = mock[CalendarClient](RETURNS_SMART_NULLS)
    val messagingMock: MessagingClient = mock[MessagingClient](RETURNS_SMART_NULLS)

    def resourcePath(file: String): String = Option(getClass.getClassLoader.getResource(file)).fold("")(_.getPath)

    trait Mocks extends Services {
      override val calendar: CalendarClient = calendarMock
      override val messaging: MessagingClient = messagingMock
    }

    object TestApp extends Commands with Mocks

  }

}
