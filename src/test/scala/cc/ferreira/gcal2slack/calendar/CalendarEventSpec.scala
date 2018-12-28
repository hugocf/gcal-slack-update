package cc.ferreira.gcal2slack.calendar

import java.time.LocalDateTime

import cc.ferreira.gcal2slack.BaseSpec

class CalendarEventSpec extends BaseSpec {

  "apply" - {
    "should allow events with 0 time length" in {
      noException should be thrownBy CalendarEvent("test", t, t)
    }

    "should fail creating events with an invalid time interval" in {
      an [IllegalArgumentException] should be thrownBy
        CalendarEvent("test", t.plusHours(1), t.minusHours(1))
    }
  }

  "all day property" - {
    "should be true for all day events" in {
      CalendarEvent("test", t.toLocalDate) should be an 'allDay
    }

    "should be false" - {
      "for simple regular events" in {
        val allDay = CalendarEvent("test", t.minusHours(1), t.plusHours(1))
        CalendarEvent("test", allDay.begin, allDay.end) should not be 'allDay
      }

      "for regular events spanning an entire day" in {
        val allDay = CalendarEvent("test", t.toLocalDate)
        CalendarEvent("test", allDay.begin, allDay.end) should not be 'allDay
      }
    }
  }

  private val t = LocalDateTime.now
}
