package cc.ferreira.gcal2slack.messaging

trait MessagingClient {
  def updateStatus(status: MessagingStatus): Unit
}
