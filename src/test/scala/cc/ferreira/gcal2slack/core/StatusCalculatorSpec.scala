package cc.ferreira.gcal2slack.core

import java.time.{LocalDate, LocalDateTime}

import cc.ferreira.gcal2slack.BaseSpec

class StatusCalculatorSpec extends BaseSpec {
  import StatusCalculator._

  "chooseStatus" - {

    "when there is a single matching event" - {
      val events = Seq(CalendarEvent("This is a matching test", t.oneHourAgo, t.oneHourAfter))
      val rules = Seq(MappingRule("matching test", ":smile:", "ok"))
      val statusSmile = MessagingStatus(":smile:", "ok")

      "should return the status" - {
        "while the event is in progress" in {
          chooseStatus(events, rules, t.now).value shouldBe statusSmile
        }

        "at the time the event begins" in {
          chooseStatus(events, rules, t.oneHourAgo).value shouldBe statusSmile
        }

        "at the time the event ends" in {
          chooseStatus(events, rules, t.oneHourAfter).value shouldBe statusSmile
        }

        "corresponding to the first matching rule" in {
          val otherRule = MappingRule("matching test", ":one:", "first!")
          val statusOne = MessagingStatus(":one:", "first!")

          chooseStatus(events, otherRule +: rules, t.oneHourAfter).value shouldBe statusOne
        }
      }

      "should ignore the status" - {
        "when the event is not current" in {
          chooseStatus(events, rules, t.lateHours) shouldBe None
        }
      }
    }

    "when there are multiple matching events" - {
      val rules = Seq(
        MappingRule("test one", ":one:", "1!"),
        MappingRule("test two", ":two:", "2!"))
      val statusOne = MessagingStatus(":one:", "1!")
      val statusTwo = MessagingStatus(":two:", "2!")

      "should return the status" - {
        "matching the current event with the closest start time" in {
          val events = Seq(
            CalendarEvent("Matching test two", t.twoHoursAgo, t.twoHoursAfter),
            CalendarEvent("Matching test one", t.oneHourAgo, t.oneHourAfter))

          chooseStatus(events, rules, t.now).value shouldBe statusOne
        }

        "matching the first current event if all start at the same time" in {
          val events = Seq(
            CalendarEvent("Matching test two", t.now, t.twoHoursAfter),
            CalendarEvent("Matching test one", t.now, t.oneHourAfter))

          chooseStatus(events, rules, t.now).value shouldBe statusTwo
        }
      }

      "should ignore the status" - {
        val events = Seq(
          CalendarEvent("Matching test two", t.twoHoursAgo, t.twoHoursAfter),
          CalendarEvent("Matching test one", t.oneHourAgo, t.oneHourAfter))

        "when none of the events are current" in {
          chooseStatus(events, rules, t.lateHours) shouldBe None
        }
      }
    }
  }

  private object t {
    val today: LocalDate = LocalDate.now
    val now: LocalDateTime = today.atTime(13, 0)
    val twoHoursAgo: LocalDateTime = now.minusHours(2)
    val oneHourAgo: LocalDateTime = now.minusHours(1)
    val oneHourAfter: LocalDateTime = now.plusHours(1)
    val twoHoursAfter: LocalDateTime = now.plusHours(2)
    val lateHours: LocalDateTime = today.atTime(22, 0)
  }

}
