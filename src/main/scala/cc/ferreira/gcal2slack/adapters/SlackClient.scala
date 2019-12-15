package cc.ferreira.gcal2slack.adapters

import cc.ferreira.gcal2slack
import cc.ferreira.gcal2slack.Result
import cc.ferreira.gcal2slack.messaging.{MessagingClient, MessagingStatus}
import scalaj.http.{Http, HttpOptions, HttpResponse}

import scala.concurrent.duration._

case class SlackClient(token: String) extends MessagingClient {
  private val endpoint = "https://slack.com/api/"
  private val timeoutSeconds = 10.seconds

  def updateStatus(status: Option[MessagingStatus]): Result[Unit] = {
    val newStatus = status.getOrElse(MessagingStatus.clear)
    val json =
      s"""{
        |    "profile": {
        |        "status_text": "${newStatus.text}",
        |        "status_emoji": "${newStatus.emoji}"
        |    }
        |}""".stripMargin

    val result: HttpResponse[String] = Http(s"$endpoint/users.profile.set")
      .postData(json)
      .header("Content-Type", "application/json; charset=utf-8")
      .header("Authorization", s"Bearer $token")
      .option(HttpOptions.readTimeout(timeoutSeconds.toMillis.toInt)).asString

    Either.cond(result.isSuccess, (), gcal2slack.Error(s"Failed to set status => $result"))
  }
}
