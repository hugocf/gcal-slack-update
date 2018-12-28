package cc.ferreira.gcal2slack.rules

import com.typesafe.config.Config

import scala.collection.JavaConverters._
import scala.util.Try

case class MappingRule(matchText: String, statusEmoji: String, statusText: String)

object MappingRule {
  def fromConfigList(cfg: Config): Seq[MappingRule] =
    cfg.getConfigList("rules").asScala.toList.flatMap(fromConfig)

  def fromConfig(cfg: Config): Option[MappingRule] =
    (for {
      m <- Try(cfg.getString("match-text"))
      e <- Try(cfg.getString("status-emoji"))
      t <- Try(cfg.getString("status-text"))
    } yield MappingRule(m, e, t)).toOption
}
