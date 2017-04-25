package cc.ferreira.gcal2slack.core

case class MessagingStatus(emoji: String, text: String)

object MessagingStatus {
  def apply(rule: MappingRule): MessagingStatus = MessagingStatus(rule.statusEmoji, rule.statusText)
}
