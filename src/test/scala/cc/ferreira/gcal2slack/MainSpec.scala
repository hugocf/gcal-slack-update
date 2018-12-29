package cc.ferreira.gcal2slack

import java.io.{ByteArrayOutputStream, File}
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.HOURS

import cc.ferreira.gcal2slack.app.Error
import cc.ferreira.gcal2slack.calendar.{CalendarClient, CalendarEvent}
import cc.ferreira.gcal2slack.cli.{Action, ReadFile, ShowHelp}
import cc.ferreira.gcal2slack.messaging.{MessagingClient, MessagingStatus}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar

class MainSpec extends BaseSpec with MockitoSugar {

  "processing an action" - {
    "should update the status when the current event matches the rules" in new Context {
      val action = ReadFile(new File(resourcePath("rules-ok.conf")))
      when(calendarMock.fetchTodayEvents()) thenReturn Right(List(testEvent))
      when(messagingMock.updateStatus(any())) thenReturn Right(())

      TestApp.processAction(action)

      verify(messagingMock).updateStatus(MessagingStatus(":smile:", "ok!"))
    }

    "should display command line text actions" in new Context {
      val action: Action = ShowHelp

      Console.withOut(stream) {
        TestApp.processAction(action)
      }

      stream.toString.trim shouldBe ShowHelp.value.trim
    }

    "should display an error for failures to read the rules file" in new Context {
      val action = ReadFile(new File("missingfile"))

      Console.withOut(stream) {
        TestApp.processAction(action)
      }

      stream.toString.trim shouldBe "Error: File 'missingfile' does not exist"
    }

    "should display an error for failures to understand the rules" in new Context {
      val action = ReadFile(new File(resourcePath("rules-bad.conf")))

      Console.withOut(stream) {
        TestApp.processAction(action)
      }

      stream.toString.trim should startWith("Error: No configuration setting found")
    }

    "should display an error for failures to integrate with calendar" in new Context {
      val action = ReadFile(new File(resourcePath("rules-ok.conf")))
      when(calendarMock.fetchTodayEvents()) thenReturn Left(Error("Contacting calendar"))

      Console.withOut(stream) {
        TestApp.processAction(action)
      }

      stream.toString.trim should startWith("Error: Contacting calendar")
    }

    "should display an error for failures to integrate with messaging" in new Context {
      val action = ReadFile(new File(resourcePath("rules-ok.conf")))
      when(calendarMock.fetchTodayEvents()) thenReturn Right(List(testEvent))
      when(messagingMock.updateStatus(any())) thenReturn Left(Error("Contacting messaging"))

      Console.withOut(stream) {
        TestApp.processAction(action)
      }

      stream.toString.trim should startWith("Error: Contacting messaging")
    }
  }

  private trait Context {
    val calendarMock: CalendarClient = mock[CalendarClient](RETURNS_SMART_NULLS)
    val messagingMock: MessagingClient = mock[MessagingClient](RETURNS_SMART_NULLS)
    val t: LocalDateTime = LocalDateTime.now
    val testEvent = CalendarEvent("A matching test", t.minus(1, HOURS), t.plus(1, HOURS))
    val stream = new ByteArrayOutputStream

    def resourcePath(file: String): String = Option(getClass.getClassLoader.getResource(file)).fold("")(_.getPath)

    trait Mocks extends Services {
      override val calendar: CalendarClient = calendarMock
      override val messaging: MessagingClient = messagingMock
    }

    object TestApp extends Main with Mocks
  }

}
