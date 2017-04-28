package cc.ferreira.gcal2slack.core

import java.time.{LocalDate, LocalDateTime}

import cc.ferreira.gcal2slack.BaseSpec

class StatusCalculatorSpec extends BaseSpec {
  import StatusCalculator._

  "chooseStatus" - {

    "when there is a single matching event" - {
      val events = Seq(CalendarEvent("This is a matching test", t.oneHourAgo, t.oneHourAfter))
      val rules = Seq(MappingRule("matching test", ":smile:", "ok"))
      val expectedStatus = MessagingStatus(":smile:", "ok")

      "should return the status" - {
        "while the event is in progress" in {
          chooseStatus(events, rules, t.now).value shouldBe expectedStatus
        }

        "at the time the event begins" in {
          chooseStatus(events, rules, t.oneHourAgo).value shouldBe expectedStatus
        }

        "at the time the event ends" in {
          chooseStatus(events, rules, t.oneHourAfter).value shouldBe expectedStatus
        }

        "corresponding to the first matching rule" in {
          val otherRule = MappingRule("matching test", ":one:", "first!")
          val firstStatus = MessagingStatus(":one:", "first!")

          chooseStatus(events, otherRule +: rules, t.oneHourAfter).value shouldBe firstStatus
        }
      }

      "should ignore the status" - {
        "when the event is not current" in {
          chooseStatus(events, rules, t.twoHoursAfter) shouldBe None
        }
      }
    }

    "when there are multiple matching events" - {

    }
  }

  private object t {
    val today: LocalDate = LocalDate.now
    val now: LocalDateTime = today.atTime(13, 0)
    val oneHourAgo: LocalDateTime = now.minusHours(1)
    val oneHourAfter: LocalDateTime = now.plusHours(1)
    val twoHoursAfter: LocalDateTime = now.plusHours(2)
  }

}
