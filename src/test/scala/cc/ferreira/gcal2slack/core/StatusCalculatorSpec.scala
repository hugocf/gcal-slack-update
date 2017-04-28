package cc.ferreira.gcal2slack.core

import java.time.{LocalDate, LocalDateTime}

import cc.ferreira.gcal2slack.BaseSpec

class StatusCalculatorSpec extends BaseSpec {

  "chooseStatus" - {
    val rules = Seq(MappingRule("matching test", ":smile:", "ok"))

    "should return the status when a rule matches the current event title" in {
      val events = Seq(CalendarEvent("This is a matching test", t.oneHourAgo, t.oneHourAfter))

      val result = StatusCalculator.chooseStatus(events, rules, t.now)

      result.value shouldBe MessagingStatus(":smile:", "ok")
    }

    "should ignore the status when the matching event is not current" in {
      val events = Seq(CalendarEvent("This is a matching test", t.twoHoursAgo, t.oneHourAgo))

      val result = StatusCalculator.chooseStatus(events, rules, t.now)

      result shouldBe None
    }
  }

  private object t {
    val today: LocalDate = LocalDate.now
    val now: LocalDateTime = today.atTime(13, 0)
    val twoHoursAgo: LocalDateTime = now.minusHours(2)
    val oneHourAgo: LocalDateTime = now.minusHours(1)
    val oneHourAfter: LocalDateTime = now.plusHours(1)
  }

}
