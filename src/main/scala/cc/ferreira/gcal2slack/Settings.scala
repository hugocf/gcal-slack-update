package cc.ferreira.gcal2slack

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

object Settings {
  def fetchConfig(file: File): Result[Config] =
    Either.cond(file.exists, ConfigFactory.parseFile(file), Error(s"File '$file' does not exist"))
}
