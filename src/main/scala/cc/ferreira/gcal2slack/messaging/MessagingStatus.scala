package cc.ferreira.gcal2slack.messaging

import cc.ferreira.gcal2slack.rules.MappingRule

case class MessagingStatus(emoji: String, text: String)

object MessagingStatus {
  def apply(rule: MappingRule): MessagingStatus = MessagingStatus(rule.statusEmoji, rule.statusText)
}
