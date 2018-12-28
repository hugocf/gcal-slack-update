package cc.ferreira.gcal2slack

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.PropertyChecks
import org.scalatest.{EitherValues, FreeSpec, Matchers, OptionValues}

trait BaseSpec extends FreeSpec with PropertyChecks with Matchers with OptionValues with EitherValues with ScalaFutures
