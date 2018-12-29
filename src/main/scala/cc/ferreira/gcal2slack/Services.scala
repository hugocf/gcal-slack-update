package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.calendar.{CalendarClient, CalendarEvent}
import cc.ferreira.gcal2slack.messaging.{MessagingClient, MessagingStatus}

trait Services {
  val calendar: CalendarClient = new CalendarClient { override def fetchTodayEvents(): Result[Seq[CalendarEvent]] = ??? }
  val messaging: MessagingClient = new MessagingClient { override def updateStatus(status: MessagingStatus): Result[Unit] = ??? }
}
