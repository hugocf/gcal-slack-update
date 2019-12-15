package cc.ferreira.gcal2slack

import java.io.File

import scala.util.Try

object Tokens {
  val file = "auth.conf"

  def slackToken: String = {
    val result = for {
      cfg <- Settings.fetchConfig(new File(file))
      token <- Try(cfg.getString("slack-token")).toResult
    } yield token

    result.getOrElse("Error: token not found")
  }
}
