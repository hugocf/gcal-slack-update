package cc.ferreira.gcal2slack.adapters.cli

import cc.ferreira.gcal2slack.core.MessagingStatus

trait MessagingClient {
  def updateStatus(status: MessagingStatus): Unit
}
