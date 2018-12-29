package cc.ferreira.gcal2slack.rules

import cc.ferreira.gcal2slack.{BaseSpec, Error}
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

class MappingRuleSpec extends BaseSpec {

  "loading from config" - {
    "returns an error when there is no config" in {
      val cfg = ConfigFactory.empty

      val result = MappingRule.fromConfigList(cfg)

      result.left.value shouldBe Error("No configuration setting found for key 'rules'")
    }

    "returns an error when config has no key for rules" in {
      val cfg = ConfigFactory.parseString("somekey = somevalue")

      val result = MappingRule.fromConfigList(cfg)

      result.left.value shouldBe Error("No configuration setting found for key 'rules'")
    }

    "returns errors when any of the rule fields are missing:" - {
      "matching text" in {
        val cfg = configTestRules(Map("status-emoji" -> ":smile:", "status-text" -> "ok!"))

        val result = MappingRule.fromConfigList(cfg)

        result.left.value shouldBe Error("No configuration setting found for key 'match-text'")
      }

      "status emoji" in {
        val cfg = configTestRules(Map("match-text" -> "matching", "status-text" -> "ok!"))

        val result = MappingRule.fromConfigList(cfg)

        result.left.value shouldBe Error("No configuration setting found for key 'status-emoji'")
      }

      "status text" in {
        val cfg = configTestRules(Map("match-text" -> "matching", "status-emoji" -> ":smile:"))

        val result = MappingRule.fromConfigList(cfg)

        result.left.value shouldBe Error("No configuration setting found for key 'status-text'")
      }
    }

    "returns a mapping rule when all items match" in {
      val cfg = configTestRules(Map("match-text" -> "matching", "status-emoji" -> ":smile:", "status-text" -> "ok!"))

      val result = MappingRule.fromConfigList(cfg)

      result.right.value shouldBe Seq(MappingRule("matching", ":smile:", "ok!")) withClue result
    }
  }

  private def configTestRules(rules: Map[String, String]) =
    ConfigFactory.parseMap(Map("rules" -> Seq(rules.asJava).asJava).asJava)
}
