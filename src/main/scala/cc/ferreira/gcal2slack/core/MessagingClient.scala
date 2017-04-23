package cc.ferreira.gcal2slack.core

trait MessagingClient {
  def updateStatus(status: MessagingStatus): Unit
}
