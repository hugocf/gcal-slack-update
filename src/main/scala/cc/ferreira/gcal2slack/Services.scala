package cc.ferreira.gcal2slack

import cc.ferreira.gcal2slack.adapters.{GcalClient, SlackClient}
import cc.ferreira.gcal2slack.calendar.CalendarClient
import cc.ferreira.gcal2slack.messaging.MessagingClient

trait Services {
  val calendar: CalendarClient = new GcalClient
  val messaging: MessagingClient = SlackClient(Tokens.slackToken)
}
