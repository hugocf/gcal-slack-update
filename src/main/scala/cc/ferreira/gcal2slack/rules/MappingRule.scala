package cc.ferreira.gcal2slack.rules

import cc.ferreira.gcal2slack.Result
import com.typesafe.config.Config

import scala.collection.JavaConverters._
import scala.util.Try

case class MappingRule(matchText: String, statusEmoji: String, statusText: String)

object MappingRule {
  def fromConfigList(cfg: Config): Result[Seq[MappingRule]] = {
    def fromConfig(cfg: Config): Try[MappingRule] = for {
      mt <- Try(cfg.getString("match-text"))
      se <- Try(cfg.getString("status-emoji"))
      st <- Try(cfg.getString("status-text"))
    } yield MappingRule(mt, se, st)

    Try(cfg.getConfigList("rules")).flatMap(_.asScala.toList.map(fromConfig).sequence).toResult
  }
}
