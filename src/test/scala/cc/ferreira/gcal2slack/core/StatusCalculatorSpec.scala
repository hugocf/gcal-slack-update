package cc.ferreira.gcal2slack.core

import cc.ferreira.gcal2slack.BaseSpec

class StatusCalculatorSpec extends BaseSpec {
  "chooseStatus" - {
    "should set the status when the rules matches the active event title" in {
      val rules = Seq(MappingRule("matching test", ":smile", "ok"))
      val events = Seq(CalendarEvent("This is a matching test"))

      val result = StatusCalculator.chooseStatus(events, rules)

      result.value shouldBe MessagingStatus(":smile", "ok")
    }
  }
}
