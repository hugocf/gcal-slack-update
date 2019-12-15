package cc.ferreira.gcal2slack.adapters

import cc.ferreira.gcal2slack.Result
import cc.ferreira.gcal2slack.calendar.{CalendarClient, CalendarEvent}

class GcalClient extends CalendarClient {
  def fetchTodayEvents(): Result[Seq[CalendarEvent]] = {
    // TODO: implement connection to Google Calendar
    Right(Seq(CalendarEvent("not found", java.time.LocalDate.now)))
//    Right(Seq(CalendarEvent("testing this", java.time.LocalDate.now)))
//    Right(Seq(CalendarEvent("another test", java.time.LocalDate.now)))
  }
}
