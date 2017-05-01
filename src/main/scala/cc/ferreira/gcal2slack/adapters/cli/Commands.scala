package cc.ferreira.gcal2slack.adapters.cli

import java.io.File

import cc.ferreira.gcal2slack.core.MappingRule
import cc.ferreira.gcal2slack.core.StatusCalculator._
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._
import scala.util.Try

trait Commands {
  this: Services =>

  def main(args: Array[String]): Unit = {
    def parseRules(path: String) =
      ConfigFactory.parseFile(new File(path)).getConfigList("rules").asScala.flatMap(asRule)

    def asRule(c: Config) = (for {
      m <- Try(c.getString("match-text"))
      e <- Try(c.getString("status-emoji"))
      t <- Try(c.getString("status-text"))
    } yield MappingRule(m, e, t)).toOption

    chooseStatus(calendar.getTodayEvents, parseRules(args(0))).foreach(messaging.updateStatus)
  }

}
