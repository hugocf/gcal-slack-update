package cc.ferreira.gcal2slack.core

import java.time.LocalDateTime

case class CalendarEvent(title: String, start: LocalDateTime, end: LocalDateTime)
