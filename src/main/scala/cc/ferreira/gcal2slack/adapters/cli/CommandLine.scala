package cc.ferreira.gcal2slack.adapters.cli

import java.io.File

import cc.ferreira.gcal2slack.core.MappingRule
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._
import scala.collection.immutable.Seq
import scala.util.Try

object CommandLine {
  def parseRules(args: Seq[String]): Seq[MappingRule] =
    // TODO: Expand to handle multiple arguments via ArgumentsParser
    ConfigFactory.parseFile(new File(args.headOption.getOrElse(""))).getConfigList("rules").asScala.toList.flatMap(asRule)

  private def asRule(c: Config): Option[MappingRule] =
    (for {
      m <- Try(c.getString("match-text"))
      e <- Try(c.getString("status-emoji"))
      t <- Try(c.getString("status-text"))
    } yield MappingRule(m, e, t)).toOption
}
