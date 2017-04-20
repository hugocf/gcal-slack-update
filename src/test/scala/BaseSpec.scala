import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.PropertyChecks
import org.scalatest.{EitherValues, Matchers, OptionValues, FreeSpec}

trait BaseSpec extends FreeSpec with PropertyChecks with Matchers with OptionValues with EitherValues with ScalaFutures
