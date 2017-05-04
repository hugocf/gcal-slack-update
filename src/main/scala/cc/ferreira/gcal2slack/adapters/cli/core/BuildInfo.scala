package cc.ferreira.gcal2slack.adapters.cli.core

import java.time.LocalDate

trait BuildInfo {
  def name: String
  def version: String
  def scalaVersion: String
  def sbtVersion: String
  def buildDate: LocalDate
}
