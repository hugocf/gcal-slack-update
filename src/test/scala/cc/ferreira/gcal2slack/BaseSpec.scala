package cc.ferreira.gcal2slack

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.PropertyChecks
import org.scalatest._

trait BaseSpec extends FreeSpec
  with PropertyChecks with Matchers with AppendedClues
  with OptionValues with EitherValues with ScalaFutures
